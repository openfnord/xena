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
 * @author Andrew Keeling
 * @author Chris Bitmead
 * @author Justin Waddell
 */

package au.gov.naa.digipres.xena.plugin.office;

import au.gov.naa.digipres.xena.kernel.type.FileType;

/**
 * Type to represent a flat XML OpenOffice.org file.
 *
 */
public class FlatOOoFileType extends FileType {
	@Override
    public String getName() {
		return "Flat OpenOffice.org";
	}

	@Override
    public String fileExtension() {
		return "xml";
	}

	@Override
    public String getMimeType() {
		return "text/xml";
	}
}
