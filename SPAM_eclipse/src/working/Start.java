package working;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Start {

    JFrame mainFrame;
    JLayeredPane start;
    JTextField path;
    JTextField participantID;
    JTextField participantIDComf;
    JLabel error;
    JRadioButton images;
    JRadioButton labels;
    JButton welcomeScreenButton;

    public Start(JFrame mainFrame) {

        this.mainFrame = mainFrame;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenWidth = dimension.width;
        int screenHeight = dimension.height;

        start = new JLayeredPane();
        start.setOpaque(true);// for background color
        start.setBackground(Color.white);

        this.mainFrame.add(start);

        JLabel lblTipSplitCalci_5 = new JLabel(
                "<html>Hello and welcome to the experiment <br>  <br> <center>Please choose the type of experiment</center>");
        lblTipSplitCalci_5.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTipSplitCalci_5.setVerticalAlignment(SwingConstants.CENTER);
        lblTipSplitCalci_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipSplitCalci_5.setBounds(0, screenHeight / 25, screenWidth,
                screenHeight / 4);
        start.add(lblTipSplitCalci_5);

        images = new JRadioButton("Images");
        images.setFont(new Font("Tahoma", Font.BOLD, 12));
        images.setBackground(Color.white);
        images.setSelected(false);
        images.setBounds(screenWidth / 4, screenHeight / 2 - 20 , screenWidth / 9,
                screenHeight / 10);
        images.addActionListener(new Action());
        start.add(images);

        labels = new JRadioButton("Text");
        labels.setFont(new Font("Tahoma", Font.BOLD, 12));
        labels.setBackground(Color.white);
        labels.setSelected(true);
        labels.setBounds(screenWidth / 4, screenHeight / 2 -20 + screenHeight / 15,
                screenWidth / 9, screenHeight / 8);
        labels.addActionListener(new Action());
        start.add(labels);

        int deltaY = screenHeight / 20;

        JLabel filePathLabel = new JLabel("Please enter path of the Folder/File:");
        filePathLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        filePathLabel.setVerticalAlignment(SwingConstants.CENTER);
        filePathLabel.setHorizontalAlignment(SwingConstants.LEADING);
        filePathLabel.setBounds(screenWidth / 2 - screenWidth / 8, screenHeight / 2, screenWidth / 2, deltaY);
        start.add(filePathLabel);

        path = new JTextField("Enter File/Folder path");
        path.setFont(new Font("Tahoma", Font.BOLD, 15));
        path.setBounds(screenWidth / 2 - screenWidth / 8, screenHeight / 2 + deltaY, screenWidth / 2, deltaY);
        start.add(path);

        JLabel participantIDLabel = new JLabel("Please enter Participant ID:");
        participantIDLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        participantIDLabel.setVerticalAlignment(SwingConstants.CENTER);
        participantIDLabel.setHorizontalAlignment(SwingConstants.LEADING);
        participantIDLabel.setBounds(screenWidth / 2 - screenWidth / 8, screenHeight / 8 + deltaY * 3, screenWidth / 2, deltaY);
        start.add(participantIDLabel);

        participantID = new JTextField("Participant ID");
        participantID.setFont(new Font("Tahoma", Font.BOLD, 15));
        participantID.setBounds(screenWidth / 2 - screenWidth / 8, screenHeight / 8 + deltaY * 4, screenWidth / 2, deltaY);
        participantID.setTransferHandler(null);

        ((AbstractDocument) participantID.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d+");

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }

                super.replace(fb, offset, length, text, attrs);
            }
        });

        start.add(participantID);

        participantIDComf = new JTextField("Confirm Participant ID");
        participantIDComf.setFont(new Font("Tahoma", Font.BOLD, 15));
        participantIDComf.setBounds(screenWidth / 2 - screenWidth / 8, screenHeight / 8 + deltaY * 5, screenWidth / 2, deltaY);
        participantIDComf.setTransferHandler(null);

        ((AbstractDocument) participantIDComf.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d+");

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }

                super.replace(fb, offset, length, text, attrs);
            }
        });

        start.add(participantIDComf);

        error = new JLabel("");
        error.setFont(new Font("Tahoma", Font.ITALIC, 15));
        error.setVerticalAlignment(SwingConstants.CENTER);
        error.setHorizontalAlignment(SwingConstants.LEADING);
        error.setBounds(screenWidth / 2 - screenWidth / 8, screenHeight / 3 + deltaY * 6, screenWidth / 2, deltaY);
        start.add(error);

        welcomeScreenButton = new JButton("Start");
        welcomeScreenButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        welcomeScreenButton.addActionListener(new Action() {

        });
        welcomeScreenButton.setBounds((screenWidth / 2) - (screenWidth / 20),
                screenHeight * 7 / 10, screenWidth / 10, 30);
        start.add(welcomeScreenButton);

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
        start.add(abortButton);

        start.setFocusable(true);
        this.mainFrame.setVisible(true);
        start.setVisible(true);
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((labels == e.getSource()) && labels.isSelected()) {
                images.setSelected(false);

            } else if ((images == e.getSource()) && images.isSelected()) {
                labels.setSelected(false);
            } else if (welcomeScreenButton == e.getSource()) {
                File file = new File(path.getText());
                if ((!file.isFile() && labels.isSelected())
                        || (!file.isDirectory() && images.isSelected())) {
                    JOptionPane.showMessageDialog(null,
                            "Please check the File/Folder Path OR select the appropriate radio buttion");
                } else if (!labels.isSelected() && !images.isSelected()) {
                    JOptionPane.showMessageDialog(null,
                            "Please select the appropriate radio button");
                } else if (participantID.getText().length() != 6) {
                    JOptionPane.showMessageDialog(null,
                            "Participant ID must be 6 digits.");
                } else if (participantIDComf.getText().length() != 6) {
                    JOptionPane.showMessageDialog(null,
                            "Participant ID must be 6 digits.");
                } else {

                    String participantIdStr = participantID.getText();
                    String participantIdStrConf = participantIDComf.getText();

                    if (participantIdStr != null && participantIdStr.equals(participantIdStrConf)) {

                        start.setVisible(false);
                        start.setFocusable(false);
                        start.setFocusable(true);

                        new Welcome(mainFrame, path.getText(), participantIdStr,
                                images.isSelected() ? "I" : "L");
                    } else {

                        participantID.setText("Participant ID");
                        participantIDComf.setText("Confirm Participant ID");
                        error.setText("Participant ID is not matching.");

                    }
                }
            }
        }
    }
}
