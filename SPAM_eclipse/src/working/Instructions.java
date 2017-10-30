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

public class Instructions {

	JFrame mainFrame;
	String path;
        String fileName;
	String type;
	JLayeredPane instructions;

	public Instructions(JFrame mainFrame, String path, String fileName,String type) {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenWidth = dimension.width;
		int screenHeight = dimension.height;

		instructions = new JLayeredPane();
		instructions.setOpaque(true);// for background color
		instructions.setBackground(Color.white);

		this.mainFrame = mainFrame;
		this.mainFrame.add(instructions);
		this.path = path;
                this.fileName = fileName;
		this.type = type;

		JLabel lblTipSplitCalci_5 = new JLabel(
				"<html> <center>You will be shown a set of items.<br> <br><br><br>"
						+ "Each item represents people who have <b>Negative Attitudes</b> toward another group of people.<br><br><br><br> "
						+ "Your job is to move the items around so that those that are similar to each other are close.<br>"
						+ "The more similar two items are, the closer they should be to one another.<br>"
						+ "You can move the items by simply click/dragging them. <br>"
						+ "When you are finished, press the RIGHT mouse button to conclude the task. <br> <br><br><br>"
						+ "REMEMBER: You're evaluating the similarity between people who have <b>Negative Attitudes</b> <br> towards another group of people, not the groups themselves. <br><br>"
						+ "Please click  'Accept'<br>");

		lblTipSplitCalci_5.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTipSplitCalci_5.setVerticalAlignment(SwingConstants.CENTER);
		lblTipSplitCalci_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipSplitCalci_5.setBounds(0, screenHeight/25, screenWidth, screenHeight * 7 / 10);
		instructions.add(lblTipSplitCalci_5);

		JButton instructionsScreenButton = new JButton("Accept");
		instructionsScreenButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		instructionsScreenButton.addActionListener(new Action());
		instructionsScreenButton.setBounds((screenWidth / 2)
				- (screenWidth / 20), screenHeight * 7 / 10, screenWidth / 10,
				30);
		instructions.add(instructionsScreenButton);
		
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
		instructions.add(abortButton);
		
		this.mainFrame.setVisible(true);
		instructions.setVisible(true);
	}

	class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			instructions.setVisible(false);
			instructions.setFocusable(false);
			instructions.setFocusable(true);
			new Experiment(mainFrame, path,fileName, type);
		}

	}
}
