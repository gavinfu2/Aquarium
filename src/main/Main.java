//Java�{���]�p�i�� �@�~���G���ڽc
//
//�m�W�G�Ť��[
//�Ǹ��G107403541
//�t�šG��ޤGB

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
