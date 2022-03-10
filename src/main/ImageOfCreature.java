package main;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageOfCreature {

	static final String f1String = "/image/f1r.png";
	static final String f2String = "/image/f2r.png";
	static final String f3String = "/image/f3r.png";
	static final String f4String = "/image/4cR.png";
	static final String tString = "/image/tr.png";
	static final String cString = "/image/cookie.png";
	static final String pString = "/image/pant.png";

	static BufferedImage bf1;
	static BufferedImage bf2;
	static BufferedImage bf3;
	static BufferedImage bf4;
	static BufferedImage bt;
	static BufferedImage bc;

	static Image bg = Toolkit.getDefaultToolkit().createImage(ImageOfCreature.class.getResource("/image/bg.jpg"));

	static BufferedImage[] fishImages;
	static BufferedImage[] turtleImages;
	static BufferedImage[] foodImages;
	static BufferedImage bp;

	public static void read() {
		try {
			bf1 = ImageIO.read(ImageOfCreature.class.getResource(f1String));
			bf2 = ImageIO.read(ImageOfCreature.class.getResource(f2String));
			bf3 = ImageIO.read(ImageOfCreature.class.getResource(f3String));
			bf4 = ImageIO.read(ImageOfCreature.class.getResource(f4String));
			bt = ImageIO.read(ImageOfCreature.class.getResource(tString));
			bc = ImageIO.read(ImageOfCreature.class.getResource(cString));
			bp = ImageIO.read(ImageOfCreature.class.getResource(pString));

			fishImages = new BufferedImage[] { bf1, bf2, bf3, bf4 };
			turtleImages = new BufferedImage[] { bt };
			foodImages = new BufferedImage[] { bc ,bp};
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
