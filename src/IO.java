/** IO manager for CaptainsLog.
 * @author James Murphy
 * CPSC 240 / Object Oriented Programming
 * Twitter Project
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class IO {
	/** Obtains ASCII art from a file.
	 * @param filename The name of the file containing the ASCII art.
	 * @param avatar Whether the ASCII art is an avatar and must be square.
	 * @throws IOException When file is missing or if avatar text not square
	 * @return String[] containing the text lines of the ASCII art.
	*/
	public static String[] getASCIIArt(String filename, boolean avatar)
		throws IOException {
		
		/* Obtain ASCII art from file by line */
		ArrayList<String> picture = new ArrayList<>();
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new IOException(e.getMessage());
		}
		while (fileIn.hasNextLine()) {
			picture.add(fileIn.nextLine());
		}
		
		/* Ensure that avatar is a perfectly square ASCII art portrait. */
		int height = picture.size();
		if (avatar) {
			for (String line : picture) {
				if (line.length() != height) {
					throw new IOException(
						"Avatar is not a perfect square!");
				}
			}
		}
		
		Object[] temp = picture.toArray();
		String[] output = new String[temp.length];		
		for (int i = 0; i < temp.length; i++) {
			output[i] = (String) temp[i];
		}
		return output;
	}
	
	public static void saveTransmissions(String filename,
		ArrayList<Transmission> transmissions) throws FileNotFoundException,
		UnsupportedEncodingException {
		PrintWriter tout = new PrintWriter(filename, "UTF-8");
		for (Transmission t : transmissions) {
			tout.println(t.toRecord());
		}
		tout.close();
	}
	
	public static void loadTransmissions(String filename,
		ArrayList<Transmission> transmissions) {
		
		
	}
}
