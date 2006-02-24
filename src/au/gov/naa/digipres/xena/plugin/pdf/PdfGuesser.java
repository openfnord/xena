package au.gov.naa.digipres.xena.plugin.pdf;
import java.io.IOException;

import au.gov.naa.digipres.xena.kernel.XenaException;
import au.gov.naa.digipres.xena.kernel.XenaInputSource;
import au.gov.naa.digipres.xena.kernel.guesser.Guess;
import au.gov.naa.digipres.xena.kernel.guesser.Guesser;
import au.gov.naa.digipres.xena.kernel.guesser.GuesserUtils;
import au.gov.naa.digipres.xena.kernel.type.FileType;
import au.gov.naa.digipres.xena.kernel.type.TypeManager;

/**
 * Guesser to guess PDF files.
 *
 * @author Chris Bitmead
 */
public class PdfGuesser extends Guesser {
    static byte[] pdfmagic = {'%', 'P', 'D', 'F'};
    public Guess guess(XenaInputSource source) throws IOException, XenaException {
        
        Guess rtn = new Guess((FileType) TypeManager.singleton().lookup(PdfFileType.class));
        String type = source.getMimeType();
        byte[] first = new byte[pdfmagic.length];
        
        source.getByteStream().read(first);
        String id = source.getSystemId().toLowerCase();
        if (type != null && type.equals("application/pdf")) {
            rtn.setMimeMatch(true);
        }
        if (id.endsWith(".pdf")) {
            rtn.setExtensionMatch(true);
        }
        if (GuesserUtils.compareByteArrays(first, pdfmagic)) {
            rtn.setMagicNumber(true);
        } else {
            rtn.setDataMatch(false);
            rtn.setPossible(false);
        }
        return rtn;
    }
    
    public String getName() {
        return "PDFGuesser";
    }
    
	@Override
	protected Guess createBestPossibleGuess()
	{
		Guess guess = new Guess();
		guess.setMagicNumber(true);
		guess.setMimeMatch(true);
		guess.setExtensionMatch(true);
		return guess;
	}
    
}
