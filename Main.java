import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedImage image = loadImageAsGrayScale(args[0]);
		
		// the guts and glory...
		String ascii = ASCIIfier.asciify(image);
		
		saveAsciiFile(ascii, "output.txt");
	}

	private static BufferedImage loadImageAsGrayScale(String path) throws IOException {
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	    ColorConvertOp op = new ColorConvertOp(cs, null);
		return op.filter(ImageIO.read(new File(path)), null);
	}
	
	private static void saveAsciiFile(String ascii, String path) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(path);
		writer.print(ascii);
		writer.flush();
		writer.close();
	}
}
