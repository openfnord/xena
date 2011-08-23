/**
 * This file is part of Xena.
 * 
 * Xena is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
 * 
 * Xena is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with Xena; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * 
 * This class represents the ImageMagick Convert command.  It is present to provide a common location for the reused
 * ImageCommand for the conversion and to abstract away details of setting up where the ImageMagick convert executable
 * resides.
 * 
 * This is currently just used statically but in the future Convert functionality of the Normalisers may be moved into
 * this class.
 *  
 * @author Terry O'Neill
 */

package au.gov.naa.digipres.xena.plugin.image;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.im4java.core.CommandException;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.ImageCommand;
import org.im4java.process.ErrorConsumer;
import org.im4java.process.OutputConsumer;

import au.gov.naa.digipres.xena.kernel.XenaException;

public class ImageMagicConverter {
	private static String convertPath = null;
	private static ImageCommand convertCommand = null;
	private static final IMOperation convertOp;
	private static final IMOperation convertOpAlphaOff;
	// message suffix for when path set is invalid.  Note that this has some fairly user friendly type text which really belongs further
	// towards the LiteMainFrame rather than here.  Note also that currently this message text is just displayed as an exception message
	// with stack trace whereas it should really be shown as a nice clean error text.
	private static final String NO_IMAGE_MAGICK_MSG_SUFFIX = "\" is not a valid location for the Image Magick Convert Executable.  " +
			"Please ensure that the Image Magick convert executable is specified in your system path or Xena Plugin Preferences " +
			"(Tools - Plugin Preferences - Image)";
	
	static {
		// set up the normal convert operation parameters, which just takes an input image and an output image to convert
		convertOp = new IMOperation();
		convertOp.addImage(2); // convert takes an input image file and an output image file
		// set up the alpha off convert operation parameters, which is just like convertOp but with the alpha parameter set to off
		convertOpAlphaOff = new IMOperation();
		convertOpAlphaOff.alpha("off");
		convertOpAlphaOff.addImage(2); // convert takes an input image file and and output image file
	}
	
	public static void setImageMagicConvertLocation(String path) throws IllegalArgumentException {
		// TODO check input and perhaps throw exception if not valid path
		// check if the path is valid by running the convert command with the version option only
		if (convertPath == null || !convertPath.equals(path)) {
			ImageCommand newConvertCommand;
			if (path.isEmpty()) {
				newConvertCommand = new ConvertCmd();
			} else {
				newConvertCommand = new ImageCommand();
				newConvertCommand.setCommand(path);
			}
			try {
				checkForImageMagickConvert(newConvertCommand);
			} catch (IOException e) {
				throw new IllegalArgumentException("\"" + path + NO_IMAGE_MAGICK_MSG_SUFFIX);
			} catch (InterruptedException e) {
				throw new IllegalArgumentException("\"" + path + NO_IMAGE_MAGICK_MSG_SUFFIX);
			} catch (IM4JavaException e) {
				throw new IllegalArgumentException("\"" + path + NO_IMAGE_MAGICK_MSG_SUFFIX);
			} catch (XenaException e) {
				throw new IllegalArgumentException("\"" + path + NO_IMAGE_MAGICK_MSG_SUFFIX);
			}
			convertCommand = newConvertCommand;
			convertPath = path;
		}
	}
	
	private static void checkForImageMagickConvert() throws IOException, InterruptedException, IM4JavaException, XenaException {
		checkForImageMagickConvert(getConvertCommand());
	}
	
	// check for the existence of a valid executable for Convert (of Image Magick)
	private static void checkForImageMagickConvert(ImageCommand convertCmd) throws IOException, InterruptedException, IM4JavaException, XenaException {
		// run the convert command with an operation to get the version.  This is done as the only real exception that should
		// occur in this case is if the executable is not present (note that this presents itself as a CommandException,
		// which is why this step is done separately rather than looking for a CommandException on our actual conversion,
		// for which case a CommandException could indicate other problems)
		// TODO really should look at the output to check that this actually is ImageMagick
		IMOperation getVersion = new IMOperation();
		getVersion.version();
		try {
			convertCmd.run(getVersion);
		} catch (CommandException e) {
			// Command Exception should only be caused by the convert executable not being present in this case
			// Provide a nice error message to the user
			System.out.println("not a valid location for the Image Magick Convert Executable: " + e.getMessage());
			throw new XenaException("Image Magick Convert cannot be found or is invalid.  Please ensure that the Image Magick Convert executable is " +
					"specified in your system path or in Xena Plugin Preferences (in Tools - Plugin Preferences - Image)", e);
		}
	}
	
	private static ImageCommand getConvertCommand() {
		if (convertCommand == null) {
			convertCommand = new ConvertCmd(); // this sets the command to just use convert which will only work if this is on the system path
		}
		return convertCommand;
	}
	
	private static void setConsumers() {
		ImageCommand cnvtCmd = getConvertCommand();
		// Create consumers of Error and Output for the conversion process
		// Note that these are essential as without consuming output from the process it is possible for the process to hang
		// (as its buffers for output can fill up)
		// TODO The output from these consumers particularly the error consumer should really be displayed to the user more obviously.
		//      Currently the error consumer output goes to the log, but would be better to have both go to the log and the error consumer
		//      go to an actual error or warning message in the table showing the conversion results).
		cnvtCmd.setErrorConsumer(new ErrorConsumer() {
			private final Logger logger = Logger.getLogger(ImageMagicConverter.class.getCanonicalName());
			
			public void consumeError(InputStream pInputStream) throws IOException {
				// Log error output
				// TODO should use buffer rather than doing one character at a time
				String msg = "";
				int nextByte = pInputStream.read();
				while (nextByte != -1) {
					msg += String.valueOf((char) nextByte); // no possibility of a different encoding here
					nextByte = pInputStream.read();
				}
				msg = msg.trim();
				if (msg.length() > 0) {
//					msg = "Image Magick error/warning output for conversion (" + inputImage.getAbsolutePath() + " -> " + outputImage.getAbsolutePath() + "):\n" + msg;
					logger.warning(msg); // TODO should really check if this is a warning or an error and use the appropriate call
				}
			}
		});
		cnvtCmd.setOutputConsumer(new OutputConsumer() {
			private final Logger logger = Logger.getLogger(ImageMagicConverter.class.getCanonicalName());
			
			public void consumeOutput(InputStream pInputStream) throws IOException {
				if (pInputStream.available() == 0) {
					// Return if there is no available data.  This is a dirty hack to get around a problem where when running
					// under windows the -1 for end of stream when using the read command never occurs.  This looks like it may be
					// some problem in the im4java library but difficult to tell.  The problem with this is that it makes it possible
					// to not get our output if the inputstream has not yet output it.  Based on the im4java code that calls this this
					// seems unlikely in the extreme.
					// Note that this has only been put on the output consumer.  Have not had any occurrence of this being necessary
					// for the error consumer thus far although it would seem reasonable to think that it is possible.
					// Note also that it is probably not a big deal if we miss some of this output; the error output is far more
					// important to us.
					// TODO Try and find some way around using this hack
					return;
				}
				// Log output
				// TODO should use buffer rather than doing one character at a time
				String msg = "";
				int nextByte = pInputStream.read();
				while (nextByte != -1) {
					msg += String.valueOf((char) nextByte); // no possibility of a different encoding here
					nextByte = pInputStream.read();
				}
				msg = msg.trim();
				if (msg.length() > 0) {
//					msg = "Image Magick standard output for conversion (" + inputImage.getAbsolutePath() + " -> " + outputImage.getAbsolutePath() + "):\n" + msg;
					logger.finer(msg);
				}		
			}
		});
	}
	
	private static void clearConsumers() {
		convertCommand.setErrorConsumer(null);
		convertCommand.setOutputConsumer(null);
	}
	
	public static void convert(final File inputImage, final File outputImage) throws IOException, InterruptedException, IM4JavaException, XenaException {
		checkForImageMagickConvert();
		setConsumers();
		// run the command
		getConvertCommand().run(convertOp, inputImage.getAbsolutePath(), outputImage.getAbsolutePath());
		clearConsumers();
	}
	
	public static void convertAlphaOff(File inputImage, File outputImage) throws IOException, InterruptedException, IM4JavaException, XenaException {
		checkForImageMagickConvert();
		setConsumers();
		// run the command
		getConvertCommand().run(convertOpAlphaOff, inputImage.getAbsolutePath(), outputImage.getAbsolutePath());
		clearConsumers();
	}
}