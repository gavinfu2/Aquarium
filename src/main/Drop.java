package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Drop extends JPanel implements Runnable {

	double size;
	int vy;
	final int v = 1;
	final int sleepTime = 10;
	int cycleTime = 1000;
	int passTime = 0;
	boolean isOnGround = false;
	Image image;
	BufferedImage bufferedImage;
	Drop thiss = this;

	SecureRandom generator = new SecureRandom();

	protected JPanel tank;

	public Drop(JPanel tank, int x, int y) {
		this.tank = tank;

		this.size = 1 + 0.5 * Math.random();
		setBounds(x, y, (int) (80 * this.size), (int) (80 * this.size));

		setBackground(new Color(0, 0, 0, 0));

		this.cycleTime += generator.nextInt(1000);
		
		ImageOfCreature.read();
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(sleepTime);
				passTime += sleepTime;
				if (passTime >= cycleTime) {
					passTime = 0;
				}
				checkBound();
				if (getY() + getHeight() > tank.getHeight()) {
					isOnGround = true;
				}else {
					isOnGround = false;
				}
				if (!isOnGround) {
					setLocation(getX(), getY() + v);
				}
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
			Thread.currentThread().interrupt();
		}

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		super.paintComponent(g2);
		g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	protected void checkBound() {
		if (0 > getX()) {
			setLocation(0, getY());
		} else if (getX() + getWidth() > tank.getWidth()) {
			setLocation(tank.getWidth() - getWidth(), getY());
		}

		if (0 > getY()) {
			setLocation(getX(), 0);
		} else if (getY() + getHeight() > tank.getHeight()) {
			setLocation(getX(), tank.getHeight() - getHeight());
		}
	}

	public void die() {
		Thread.currentThread().interrupt();
	}

}
