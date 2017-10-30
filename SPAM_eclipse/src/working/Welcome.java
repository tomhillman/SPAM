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

public class Welcome {

    JFrame mainFrame;
    String path;
    String fileName;
    String type;
    JLayeredPane welcome;

    public Welcome(JFrame mainFrame, String path, String participantID, String type) {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenWidth = dimension.width;
        int screenHeight = dimension.height;

        welcome = new JLayeredPane();
        welcome.setOpaque(true);// for background color
        welcome.setBackground(Color.white);

        this.mainFrame = mainFrame;
        this.path = path;

        this.type = type;
        this.mainFrame.add(welcome);

        long sequence = System.currentTimeMillis() % 100000000000l;
        this.fileName = participantID + "_" + sequence;

        JLabel lblTipSplitCalci_5 = new JLabel(
                "<html>Hello and welcome to the experiment <br>  <br> <center>Please click 'Begin' to continue</center>");
        lblTipSplitCalci_5.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTipSplitCalci_5.setVerticalAlignment(SwingConstants.CENTER);
        lblTipSplitCalci_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipSplitCalci_5.setBounds(0, screenHeight / 25, screenWidth, screenHeight * 7 / 10);
        welcome.add(lblTipSplitCalci_5);

        JButton instructionsScreenButton = new JButton("Begin");
        instructionsScreenButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        instructionsScreenButton.addActionListener(new Action());
        instructionsScreenButton.setBounds((screenWidth / 2)
                - (screenWidth / 20), screenHeight * 7 / 10, screenWidth / 10,
                30);
        welcome.add(instructionsScreenButton);

        welcome.setFocusable(true);

        JButton abortButton = new JButton("Abort");
        abortButton.setBackground(Color.RED);
        abortButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        abortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        abortButton.setBounds(screenWidth - (screenWidth) / 20, 0,
                screenWidth / 20, screenHeight / 25);
        welcome.add(abortButton);

        this.mainFrame.setVisible(true);
        welcome.setVisible(true);
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            welcome.setVisible(false);
            welcome.setFocusable(false);
            new Instructions(mainFrame, path, fileName, type);
        }

    }
}
