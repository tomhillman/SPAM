package working;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import jxl.write.WriteException;

public class Experiment {

	private JFrame mainFrame;
	String path;
        String excelFileName;
	String type;

	private ImageIcon starIcon;
	private ArrayList<JLabel> lLabelList = new ArrayList<JLabel>();
	private JLabel image;
	private JLabel title;
	private JLayeredPane imagePanel = new JLayeredPane();
	private File[] listOfFiles;

	public Image buffer = null;

	public Experiment(JFrame mainFrame, final String path, String excelFileName, String type) {

		this.path = path;
                this.excelFileName = excelFileName;
		this.mainFrame = mainFrame;
		this.type = type;
		//titlePanel.setOpaque(false);
	//	titlePanel.setVisible(true);
//		titlePanel.setFocusable(true);
	//	this.mainFrame.add(titlePanel);

		
		imagePanel.setOpaque(true);// for background color
		imagePanel.setBackground(Color.white);
		this.mainFrame.add(imagePanel);

		imagePanel.setVisible(true);
		imagePanel.setFocusable(true);

		if (type.equals("L")) {
			getLabelsFromFile();
		} else
			getImagesFromFolder();
		displayImagesAndLabels();
		this.mainFrame.add(imagePanel);

		this.mainFrame.setFocusable(true);
		imagePanel.addMouseListener(new MouseAction());
	}

	private void getLabelsFromFile() {
		File labelsFile = new File(path);
		JLabel label;
		Scanner labelScanner;
		// Border border = BorderFactory.createLineBorder(Color.blue); for label
		// border. can use this for image border too
		try {
			labelScanner = new Scanner(labelsFile);
			while (labelScanner.hasNext()) {
				label = new JLabel(labelScanner.nextLine());
				label.addMouseListener(new MouseAction());
				label.addMouseMotionListener(new moveIconHandler());
				label.setVerticalAlignment(SwingConstants.CENTER);
				// label.setBorder(border);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lLabelList.add(label);
			}
		} catch (FileNotFoundException e) {
		}

	}
	


	private void getImagesFromFolder() {
		File folder = new File(path);
		listOfFiles = folder.listFiles();
		String file;
		for (int i = 0; i < listOfFiles.length; i++) {
			file = listOfFiles[i].getAbsolutePath();
			starIcon = new ImageIcon(file);
			image = new JLabel(starIcon, JLabel.CENTER);
			image.addMouseListener(new MouseAction());
			image.addMouseMotionListener(new moveIconHandler());
			image.setVerticalAlignment(SwingConstants.CENTER);
			image.setHorizontalAlignment(SwingConstants.CENTER);
			lLabelList.add(image);
		}
	}

	private void displayImagesAndLabels() {

		int totalImagesAndLabels = lLabelList.size();
		int firstRow = 0;
		int secondRow = 0;
		int thirdRow = totalImagesAndLabels / 3;

		if (totalImagesAndLabels % 3 == 2 || totalImagesAndLabels % 3 == 1) {
			firstRow = totalImagesAndLabels / 3 + 1;
		} else
			firstRow = totalImagesAndLabels / 3;

		int maxImagesOnRow = firstRow;
		int imageIndexInRow = 0;

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenWidth = dimension.width;
		int screenHeight = dimension.height;

		
		JLabel title = new JLabel(
				"<html> <center>People with Negative Attitudes toward: <br> <br>"
						);

		title.setFont(new Font("Tahoma", Font.BOLD, 23));
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(0, screenHeight/20, screenWidth, screenHeight / 20 + 20);
		imagePanel.add(title);
	
		
		int widthPerImage = screenWidth / maxImagesOnRow;
		int heightPerImage = screenHeight / 3;

		int imagewidth;
		int imageHeight;
		int firstImageX;
		int firstImageY;
		System.out.println(type);
		if (type.equals("L")) {
			imagewidth = screenWidth / 6;
			imageHeight = screenHeight / 30;
			firstImageX = widthPerImage / 2 - imagewidth / 2;
			firstImageY = heightPerImage / 2 - imageHeight / 2;
		} else {
			imagewidth = screenWidth / 10;
			imageHeight = screenWidth / 10;
			firstImageX = widthPerImage / 2 - imagewidth / 2;
			firstImageY = heightPerImage / 2 - imageHeight / 2;
		}

		// System.out.println(type + "" + imageHeight + "" + imagewidth);

		for (int LabelNumber = 0; LabelNumber < firstRow; LabelNumber++) {

			image = lLabelList.get(LabelNumber);
			image.setBounds(firstImageX + (imageIndexInRow * widthPerImage),
					firstImageY, imagewidth, imageHeight);
			imageIndexInRow++;
			imagePanel.add(image);
		}

		if (totalImagesAndLabels % 3 == 2) {
			secondRow = totalImagesAndLabels / 3 + 1;
		} else
			secondRow = totalImagesAndLabels / 3;

		imageIndexInRow = 0;
		for (int LabelNumber = firstRow; LabelNumber < firstRow + secondRow; LabelNumber++) {
			image = lLabelList.get(LabelNumber);
			image.setBounds(firstImageX + (imageIndexInRow * widthPerImage),
					firstImageY + heightPerImage, imagewidth, imageHeight);
			imageIndexInRow++;
			imagePanel.add(image);
		}

		imageIndexInRow = 0;
		for (int LabelNumber = firstRow + secondRow; LabelNumber < firstRow
				+ secondRow + thirdRow; LabelNumber++) {
			image = lLabelList.get(LabelNumber);
			image.setBounds(firstImageX + (imageIndexInRow * widthPerImage),
					firstImageY + (2 * heightPerImage), imagewidth, imageHeight);
			imageIndexInRow++;
			imagePanel.add(image);
		}

	}

	private class moveIconHandler extends MouseMotionAdapter {

		public void mouseDragged(MouseEvent e) {
			Component c = e.getComponent();
			c.setLocation(c.getX() - (c.getWidth() / 2) + e.getX(), c.getY()
					- (c.getHeight() / 2) + e.getY());

			imagePanel.moveToFront(e.getComponent());
			mainFrame.repaint();
		}
	}

	class MouseAction extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.isMetaDown()) {
				//
				try {
					new ExcelWriter().writeToExcel(lLabelList, listOfFiles,
							path,excelFileName);
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				imagePanel.setVisible(false);
				imagePanel.setFocusable(false);
				imagePanel.setFocusable(false);
				// mainFrame.setFocusable(false);
				new Finish(mainFrame, imagePanel, path,excelFileName, type);
			}
		}
	}

}
