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

public class Finish {

    JFrame mainFrame;
    String path;
    String excelFileName;
    String type;
    JLayeredPane imagePanel;

    JButton finishButton;
    JButton backButton;
    JButton startOverButton;
    JLayeredPane finish;

    public Finish(JFrame mainFrame, JLayeredPane imagePanel, String path, String excelFileName, String type) {

        this.mainFrame = mainFrame;
        this.path = path;
        this.excelFileName = excelFileName;
        this.type = type;
        this.imagePanel = imagePanel;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenWidth = dimension.width;
        int screenHeight = dimension.height;

        finish = new JLayeredPane();
        finish.setOpaque(true);// for background color
        finish.setBackground(Color.white);

        //this.mainFrame.add(imagePanel);
        this.mainFrame.add(finish);

        JLabel lblTipSplitCalci_5 = new JLabel(
                "<html> <center>Have you finished adjusting the placement of the images?<br> <br><br>"
                + "You have 3 options: <br> <br>"
                + "\"Yes, I'm finished\" - Click Button \'Finish\' <br>"
                + "\"No, I still need more time\" - Click Button \'Go Back\' <br>"
                + "\"I would like to start over completely\" - Click Button \'Start Over\' ");

        lblTipSplitCalci_5.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblTipSplitCalci_5.setVerticalAlignment(SwingConstants.CENTER);
        lblTipSplitCalci_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipSplitCalci_5.setBounds(0, screenHeight / 25, screenWidth, screenHeight * 7 / 10);
        finish.add(lblTipSplitCalci_5);

        finishButton = new JButton("Finish");
        finishButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        finishButton.addActionListener(new ButtonAction());
        finishButton.setBounds((screenWidth / 2) - (screenWidth / 20),
                screenHeight * 7 / 10, screenWidth / 10, 30);
        finish.add(finishButton);

        backButton = new JButton("Go Back");
        backButton.addActionListener(new ButtonAction());
        backButton.setBounds((screenWidth / 4), screenHeight * 7 / 10,
                screenWidth / 10, 30);
        finish.add(backButton);

        startOverButton = new JButton("Start Over");
        startOverButton.addActionListener(new ButtonAction());
        startOverButton.setBounds((screenWidth * 3 / 4) - screenWidth / 10,
                screenHeight * 7 / 10, screenWidth / 10, 30);
        finish.add(startOverButton);

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
        finish.add(abortButton);

        //mainFrame.setVisible(true);
        finish.setVisible(true);
    }

    class ButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent eventButton) {
            if (eventButton.getSource() == finishButton) {
                finish.setVisible(false);
                finish.setFocusable(false);
                finish.setFocusable(true);
                new GoodBye(mainFrame);
            } else if (eventButton.getSource() == startOverButton) {
                finish.setVisible(false);
                finish.setFocusable(false);
                finish.setFocusable(true);
                new Experiment(mainFrame, path,excelFileName, type);
            } else if (eventButton.getSource() == backButton) {
                finish.setVisible(false);
                finish.setFocusable(false);
                finish.setFocusable(true);
                imagePanel.setVisible(true);
            }

        }
    }

}
