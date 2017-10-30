package working;

import java.awt.Frame;

import javax.swing.JFrame;

public class SpamMain {
	public static void main(String[] args) {
		JFrame mainFrame;
		mainFrame = new JFrame("SPAM");
		mainFrame.setUndecorated(true);//for full screen
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

		new Start(mainFrame);
	}
}
