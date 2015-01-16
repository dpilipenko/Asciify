import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * License limitations: Steal me, Copy me, Sell me. 
 */
public class ASCIIfier {
	
	public static String asciify(BufferedImage image) {
		image = downscaleImage(image);
		String ascii = getStringFromImage(image);
		return ascii;
	}
	
	private static char getCharacterFromLuminosity(double luminosity) {
		char value;
		if (luminosity >= 240) { // lightest colors
			value = ' ';
		} else if (luminosity >= 210) {
			value = '.';
		} else if (luminosity >= 190) {
			value = '*';
		} else if (luminosity >= 170) {
			value = '+';
		} else if (luminosity >= 120) {
			value = '^';
		} else if (luminosity >= 110) {
			value = '&';
		} else if (luminosity >= 80) {
			value = '8';
		} else if (luminosity >= 60) {
			value = '#';
		} else {                 // darkest colors
			value = '@';
		}
		return value;
	}
	
	private static double getLuminosity(Color c) {
		// Weighted for human perceptions
		double luminosity =
				(double)c.getRed()*0.21 + 
				(double)c.getGreen()*0.72 +
				(double)c.getBlue()*0.07;
		return luminosity;
	}

	private static String getStringFromImage(BufferedImage image) {
		StringBuilder sb = new StringBuilder();
		for (int y=0; y<image.getHeight(); y++) {
			for (int x=0; x<image.getWidth(); x++) {
				Color color = new Color(image.getRGB(x, y));
				double luminosity = getLuminosity(color);
				char character = getCharacterFromLuminosity(luminosity);
				sb.append(character);
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	private static BufferedImage downscaleImage(BufferedImage image) {
		final int WIDTH = 100;
		double ratio = (double)image.getHeight() / image.getWidth();
		final int HEIGHT = (int)(ratio * WIDTH);
		BufferedImage newImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = newImage.createGraphics();
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		return newImage;
	}
}
