package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

	private JPanel contentPane;
	private Mode mode;
	private LinkedList<Creature> creatures = new LinkedList<Creature>();
	private LinkedList<Drop> drops = new LinkedList<Drop>();
	private JPanel tank;

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private JLabel currentFunc;
	private JLabel fishAmount;
	private JLabel turtleAmount;

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));

		JPanel nPone = new JPanel();
		northPanel.add(nPone);
		nPone.setLayout(new GridLayout(0, 3, 0, 0));

		JButton addFishButton = new JButton("\u65B0\u589E\u9B5A");
		addFishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mode = Mode.ADD_FISH;
				updateInfo();
			}
		});
		nPone.add(addFishButton);

		JButton removeSelectButton = new JButton("\u79FB\u9664\u9078\u53D6");
		removeSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = Mode.REMOVE_SELECTED;
				removeCreature();
				updateInfo();
			}
		});
		nPone.add(removeSelectButton);

		JButton addTurtleButton = new JButton("\u65B0\u589E\u70CF\u9F9C");
		addTurtleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = Mode.ADD_TURTLE;
				removeCreature();
				updateInfo();
			}
		});
		nPone.add(addTurtleButton);

		JButton removeAllButton = new JButton("\u79FB\u9664\u5168\u90E8");
		removeAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = Mode.REMOVE_ALL;
				removeCreature();
				updateInfo();
			}
		});
		nPone.add(removeAllButton);

		JButton feedButton = new JButton("\u98FC\u6599\uFF08\u5DE6\u53F3\u9375\u4E0D\u4E00\u6A23\uFF09");
		feedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = Mode.FEED;
				updateInfo();
			}
		});
		nPone.add(feedButton);

		JPanel nPtwo = new JPanel();
		FlowLayout fl_nPtwo = (FlowLayout) nPtwo.getLayout();
		fl_nPtwo.setHgap(30);
		fl_nPtwo.setAlignment(FlowLayout.LEFT);
		nPtwo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		northPanel.add(nPtwo);

		currentFunc = new JLabel("\u76EE\u524D\u529F\u80FD\uFF1A");
		nPtwo.add(currentFunc);

		fishAmount = new JLabel("\u9B5A\u6578\u91CF\uFF1A");
		nPtwo.add(fishAmount);

		turtleAmount = new JLabel("\u70CF\u9F9C\u6578\u91CF\uFF1A");
		nPtwo.add(turtleAmount);

		tank = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(ImageOfCreature.bg, 0, 0, getWidth(), getHeight(), this);
			}
		};
		tank.addMouseListener(new ButtonListener() {
			public void triggerButton(MouseEvent e) {
				System.out.println("click tank!");
				switch (mode) {
				case ADD_FISH:
				case ADD_TURTLE:
					addCreature(e.getX(), e.getY());
					break;
				case FEED:
					if (e.isMetaDown()) {
						addFood("pant", e.getX(), e.getY());
					} else {
						addFood("cookie", e.getX(), e.getY());
					}
					break;
				default:
					break;
				}
			}
		});
		contentPane.add(tank, BorderLayout.CENTER);
		tank.setLayout(null);
	}

	private void addCreature(int x, int y) {
		// TODO Auto-generated method stub
		Creature creature = null;
		switch (mode) {
		case ADD_FISH:
			creature = new Fish(tank, x, y);
			break;
		case ADD_TURTLE:
			creature = new Turtle(tank, x, y);
			break;
		default:
			break;
		}
		if (creature != null) {
			tank.add(creature);
			creatures.add(creature);
			executorService.execute(creature);
			repaint();
			updateInfo();
		}
	}

	private void addFood(String type, int x, int y) {
		Drop drop = null;
		switch (type) {
		case "cookie":
			drop = new Food(tank, x, y, creatures);
			break;
		case "pant":
			drop = new Pant(tank, x, y, creatures);
			break;
		default:
			break;
		}
		if (drop != null) {
			tank.add(drop);
			drops.add(drop);
			executorService.execute(drop);
			repaint();
			updateInfo();
		}
	}

	private void removeCreature() {
		switch (mode) {
		case REMOVE_ALL:
			creatures.forEach(c -> {
				tank.remove(c);
				c.die();
				c.setEnabled(false);
			});
			break;
		case REMOVE_SELECTED:
			creatures.forEach(c -> {
				if (c.isSelected) {
					tank.remove(c);
					c.die();
					c.setEnabled(false);
				}
			});
			break;
		default:
			break;
		}
		creatures.removeIf(c -> !c.isEnabled());
		updateInfo();
		repaint();
	}

	private void updateInfo() {
		currentFunc.setText("目前功能：" + mode.getDescription());
		fishAmount.setText("魚數量：" + creatures.stream().filter(c -> c instanceof Fish).count());
		turtleAmount.setText("烏龜數量：" + creatures.stream().filter(c -> c instanceof Turtle).count());
	}

}
