package main;

import java.security.SecureRandom;

import javax.swing.JPanel;

public class Turtle extends Creature {

	public Turtle(JPanel tank, int x, int y) {
		super(tank, x, y);
		this.bufferedImage = ImageOfCreature.turtleImages[this.generator.nextInt(ImageOfCreature.turtleImages.length)];
		this.image = bufferedImage;
		setBounds(x, y, (int) (bufferedImage.getWidth() * this.size), (int) (bufferedImage.getHeight() * this.size));
	}

	protected void checkBound() {
		if (0 > getX()) {
			setLocation(0, getY());
			vx *= -1;
		} else if (getX() + getWidth() > tank.getWidth()) {
			setLocation(tank.getWidth() - getWidth(), getY());
			vx *= -1;
		}

		if (0 > getY()) {
			setLocation(getX(), 0);
		} else if (getY() + getHeight() > tank.getHeight()) {
			setLocation(getX(), tank.getHeight() - getHeight());
		}
	}

	public void resetV() {
		SecureRandom generator = new SecureRandom();
		if (getY() + getHeight() >= tank.getHeight()) {
			vx = (generator.nextBoolean() ? 1 : -1) * generator.nextInt(v);
		}
		if (getY() + getHeight() >= tank.getHeight()) {
			this.vy = 0;
		} else {
			this.vy = 1;
		}
	}

}
