package main;

import java.util.LinkedList;

import javax.swing.JPanel;

public class Pant extends Drop {

	private LinkedList<Creature> creatures;
	boolean isDead = false;

	public Pant(JPanel tank, int x, int y, LinkedList<Creature> creatures) {
		super(tank, x, y);
		this.creatures = creatures;
		this.bufferedImage = ImageOfCreature.bp;
		this.image = bufferedImage;
		this.size = 0.1;
		setBounds(x, y, (int) (bufferedImage.getWidth() * this.size), (int) (bufferedImage.getHeight() * this.size));
	}

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
				} else {
					isOnGround = false;
				}
				if (!isOnGround) {
					setLocation(getX(), getY() + v);
				}
				eat();
			}
		} catch (InterruptedException exception) {
//			exception.printStackTrace();
			Thread.currentThread().interrupt();
		}

	}

	public void eat() {
		for (Creature c : creatures) {
			if (c.getBounds().intersects(getBounds())) {
				if (c instanceof Fish) {
					if(((Fish)c).is4C) {
						c.setBounds(c.getX(), c.getY(), (int)(c.getWidth() * 1.5), (int)(c.getHeight() * 1.5));
						isDead = true;
						break;
					}else {
						tank.remove(c);
						c.die();
						isDead = true;
						break;
					}
				}else {
					tank.remove(c);
					c.die();
					isDead = true;
					break;
				}
			}
		}
		if (isDead) {
			tank.remove(this);
			tank.repaint();
			die();
		}
	}
}
