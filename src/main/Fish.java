package main;

import java.io.IOException;

import javax.swing.JPanel;

public class Fish extends Creature {
	
	boolean is4C = false;

	public Fish(JPanel tank, int x, int y) {
		super(tank, x, y);
		int n = this.generator.nextInt(ImageOfCreature.fishImages.length);
		if(n == 3) {
			is4C = true;
		}
		this.bufferedImage = ImageOfCreature.fishImages[n];
		this.image = bufferedImage;
		setBounds(x, y, (int) (bufferedImage.getWidth() * this.size), (int) (bufferedImage.getHeight() * this.size));
	}

}
