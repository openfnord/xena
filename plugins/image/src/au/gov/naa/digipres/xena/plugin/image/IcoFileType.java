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
 * @author Matthew Oliver
 */
package au.gov.naa.digipres.xena.plugin.image;

import au.gov.naa.digipres.xena.kernel.type.FileType;

/**
 * Type to represent the ICO (Mircosoft Icon) image format
 *
 */
public class IcoFileType extends FileType {
	public static final String ICO_NAME = "ICO";

	@Override
	public String getName() {
		return ICO_NAME;
	}

	@Override
	public String getMimeType() {
		return "image/x-ico";
	}

}
