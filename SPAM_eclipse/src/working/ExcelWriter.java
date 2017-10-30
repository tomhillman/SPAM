package working;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JLabel;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelWriter {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private File[] listOfFiles;
    private ArrayList<JLabel> lLabelList = new ArrayList<JLabel>();

    public void writeToExcel(ArrayList<JLabel> lLabelList, File[] listOfFiles,
            String path, String fileName) throws WriteException, IOException {

        if (fileName == null || fileName.isEmpty()) {
            fileName = "SPAMExperiment";
        }
        this.listOfFiles = listOfFiles;
        this.lLabelList = lLabelList;
        File givenPath = new File(path);
        String excelDirectory;
        excelDirectory = (givenPath.isFile() ? new File(givenPath.getParent())
                .getAbsolutePath() : new File(givenPath.getParent())
                        .getAbsolutePath());
        File file = new File(excelDirectory + "/" + fileName+".xls");
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createHeaders(excelSheet);
        createContent(excelSheet);

        workbook.write();
        workbook.close();
    }

    private void createHeaders(WritableSheet sheet) throws WriteException {

        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // Create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        addCaption(sheet, 0, 0, "ImageFrom");
        addCaption(sheet, 1, 0, "ImageTo");
        addCaption(sheet, 2, 0, "Distance");

    }

    private void createContent(WritableSheet sheet) throws WriteException,
            RowsExceededException {
        int rowNumber = 1;
        int firstRowFileNumber = 0;
        boolean flag = false;

        if (listOfFiles == null) {
            for (int i = 0; i < lLabelList.size(); i++) {
                for (int j = 0; j < lLabelList.size(); j++) {
                    if (((JLabel) lLabelList.get(i)) != ((JLabel) lLabelList
                            .get(j))) {
                        writeComponentName(sheet, 0, rowNumber,
                                (lLabelList.get(i).getText()));
                        writeComponentName(sheet, 1, rowNumber,
                                (lLabelList.get(j).getText()));
                        rowNumber++;
                    }
                }
            }

        } else {
            for (int i = 0; i < lLabelList.size(); i++) {
                int secRowFileNumber = 0;
                for (int j = 0; j < lLabelList.size(); j++) {

                    if (((JLabel) lLabelList.get(i)) != ((JLabel) lLabelList
                            .get(j))) {

                        if (i >= lLabelList.size() - listOfFiles.length) {
                            writeComponentName(sheet, 0, rowNumber,
                                    listOfFiles[firstRowFileNumber].getName());
                            flag = true;
                        } else {
                            writeComponentName(sheet, 0, rowNumber,
                                    (lLabelList.get(i).getText()));
                        }

                        if (j >= lLabelList.size() - listOfFiles.length) {
                            if (flag == true
                                    && (listOfFiles[firstRowFileNumber]
                                            .getName())
                                            .equalsIgnoreCase((listOfFiles[secRowFileNumber]
                                                    .getName()))) {
                                secRowFileNumber++;
                            }
                            writeComponentName(sheet, 1, rowNumber,
                                    listOfFiles[secRowFileNumber].getName());
                            secRowFileNumber++;
                        } else {
                            writeComponentName(sheet, 1, rowNumber,
                                    (lLabelList.get(j).getText()));
                        }
                        rowNumber++;
                    }
                }
                if (i >= lLabelList.size() - listOfFiles.length) {
                    firstRowFileNumber++;
                }
            }
        }
        writeDistances(sheet);
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void writeComponentName(WritableSheet sheet, int column, int row,
            String s) throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
            Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void writeDistances(WritableSheet sheet)
            throws RowsExceededException, WriteException {

        int rowNumber = 1;

        for (int i = 0; i < lLabelList.size(); i++) {
            int iLAbelX = getX(i);
            int iLAbelY = getY(i);

            for (int j = 0; j < lLabelList.size(); j++) {
                if (((JLabel) lLabelList.get(i)) != ((JLabel) lLabelList.get(j))) {
                    int jLAbelX = getX(j);
                    int jLAbelY = getY(j);

                    int distance = getDistanceBetweenCentres(iLAbelX, iLAbelY,
                            jLAbelX, jLAbelY);
                    addNumber(sheet, 2, rowNumber, distance);
                    rowNumber++;
                }
            }
        }
    }

    private int getDistanceBetweenCentres(int iLAbelX, int iLAbelY,
            int jLAbelX, int jLAbelY) {
        int xdistance = Math.abs(iLAbelX - jLAbelX);
        int ydistance = Math.abs(iLAbelY - jLAbelY);

        int distance = (int) Math.sqrt((xdistance * xdistance)
                + (ydistance * ydistance));
        return distance;
    }

    private int getY(int i) {
        return ((JLabel) lLabelList.get(i)).getY()
                + (((JLabel) lLabelList.get(i)).getHeight() / 2);
    }

    private int getX(int i) {
        return ((JLabel) lLabelList.get(i)).getX()
                + (((JLabel) lLabelList.get(i)).getWidth() / 2);
    }

}
