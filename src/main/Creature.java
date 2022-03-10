package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import javafx.scene.shape.Rectangle;

public class Creature extends JPanel implements Runnable {

	double size;
	int vx;
	int vy;
	final int v = 3;
	final int sleepTime = 10;
	int cycleTime = 1000;
	int passTime = 0;
	boolean isSelected = false;
	Image image;
	BufferedImage bufferedImage;
	Creature thiss = this;
	Rectangle bounds;

	SecureRandom generator = new SecureRandom();

	protected JPanel tank;

	public Creature(JPanel tank, int x, int y) {
		this.tank = tank;

		this.size = 0.3 + 0.5 * Math.random();

		setOpaque(false);

		resetV();
		this.cycleTime += generator.nextInt(1000);
		addMouseListener(new ButtonListener() {
			public void triggerButton(MouseEvent e) {
				isSelected = !isSelected;
				if (isSelected) {
					setBorder(BorderFactory.createLineBorder(Color.pink, 5));
				} else {
					setBorder(BorderFactory.createEmptyBorder());
				}
			}
		});

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
					resetV();
				}
				checkBound();
				setLocation(getX() + vx, getY() + vy);
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
		if (vx > 0) {
			g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		} else {
			g2.drawImage(image, getWidth(), 0, -getWidth(), getHeight(), this);
		}
	}

	public void resetV() {
		SecureRandom generator = new SecureRandom();
		vx = (generator.nextBoolean() ? 1 : -1) * (generator.nextInt(v) + 1);
		vy = (generator.nextBoolean() ? 1 : -1) * (generator.nextInt(v) + 1);
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
			vy *= -1;
		} else if (getY() + getHeight() > tank.getHeight()) {
			setLocation(getX(), tank.getHeight() - getHeight());
			vy *= -1;
		}
	}

	public void die() {
		Thread.currentThread().interrupt();
	}

}
