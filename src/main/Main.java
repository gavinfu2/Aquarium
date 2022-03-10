//Java程式設計進階 作業六：水族箱
//
//姓名：藍中璟
//學號：107403541
//系級：資管二B

package main;

import java.awt.Dimension;
import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setLocationRelativeTo(null);
					frame.setMinimumSize(new Dimension(500, 400));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
