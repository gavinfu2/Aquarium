package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonListener extends MouseAdapter {

		boolean isEntered;
		boolean isPressed;

		@Override
		public void mouseReleased(MouseEvent e) {
			if (isPressed && isEntered) {
				triggerButton(e);
			}
			isPressed = false;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			isPressed = true;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			isEntered = false;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			isEntered = true;
		}
		
		public void triggerButton(MouseEvent e) {
			
		}
}
