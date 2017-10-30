package working;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GoodBye {

	JLayeredPane goodByePanel;
	JFrame mainFrame;

	public GoodBye(final JFrame mainFrame) {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenWidth = dimension.width;
		int screenHeight = dimension.height;

		goodByePanel = new JLayeredPane();
		goodByePanel.setOpaque(true);// for background color
		goodByePanel.setBackground(Color.white);

		this.mainFrame = mainFrame;
		this.mainFrame.add(goodByePanel);

		JLabel lblTipSplitCalci_5 = new JLabel(
				"<html>Thank you for participating. <br>  <br> <center>Goodbye!</center>");
		lblTipSplitCalci_5.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTipSplitCalci_5.setVerticalAlignment(SwingConstants.CENTER);
		lblTipSplitCalci_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipSplitCalci_5.setBounds(0, screenHeight/25, screenWidth, screenHeight);
		goodByePanel.add(lblTipSplitCalci_5);

		goodByePanel.setFocusable(true);
		this.mainFrame.setVisible(true);
		goodByePanel.setVisible(true);

		JButton abortButton = new JButton("Abort");
		abortButton.setBackground(Color.RED);
		abortButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		abortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		abortButton.setBounds(screenWidth - (screenWidth) / 20, 0,
				screenWidth / 20, screenHeight/25);
		goodByePanel.add(abortButton);
		
		Timer timer = new Timer(1000, new CloseAction());
		timer.setRepeats(false);
		timer.start();

	}

	class CloseAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
