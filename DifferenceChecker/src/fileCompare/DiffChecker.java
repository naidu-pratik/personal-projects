package fileCompare;

import configs.Configurations;
import configs.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import messages.ErrorMessage;
import utility.Utils;

public class DiffChecker {
    private Properties properties = new Properties();
    private Constants constants = new Constants();
    private ErrorMessage errorMessage = new ErrorMessage();
    private Integer lineCount = 1;

    public void compareFiles(String pathFile1, String pathFile2) throws IOException {
        try {
             properties = (new Configurations()).propertyLoader();
        } catch (Exception var10) {
            System.out.println(errorMessage.ERR_CONFIG_FILE);
        }

        BufferedReader readerForFile1 = (new Utils()).createReader(pathFile1);
        BufferedReader readerForFile2 = (new Utils()).createReader(pathFile2);



        BufferedWriter writerForReport = (new Utils()).createWriter(properties.getProperty(constants.PATH_REPORT));
        String lineFromFile1 = readerForFile1.readLine();
        String lineFromFile2 = readerForFile2.readLine();



        while(true) {
            while(lineFromFile1 != null || lineFromFile2 != null) {
                String lineNumber = constants.MISMATCH + lineCount.toString();
                if (lineFromFile1 != null && lineFromFile2 != null) {
                    Boolean isEqual;
                    if (Boolean.valueOf(properties.getProperty(constants.IGNORE_CASE))) {
                        isEqual = lineFromFile1.equalsIgnoreCase(lineFromFile2);

                    } else {
                        isEqual = lineFromFile1.equals(lineFromFile2);

                    }

                    if (!isEqual) {
                        writerForReport.write(lineNumber);
                        writerForReport.newLine();
                        writerForReport.write(lineFromFile1);
                        writerForReport.newLine();
                        writerForReport.write(lineFromFile2);
                        writerForReport.newLine();
                        this.lineCount = this.lineCount + 1;
                        lineFromFile1 = readerForFile1.readLine();
                        lineFromFile2 = readerForFile2.readLine();

                    } else {
                        this.lineCount = this.lineCount + 1;
                        lineFromFile1 = readerForFile1.readLine();
                        lineFromFile2 = readerForFile2.readLine();

                    }
                } else {
                    writerForReport.write(lineNumber);
                    writerForReport.newLine();
                    if (lineFromFile1 == null) {
                        writerForReport.write(constants.BLANK);
                        writerForReport.newLine();
                        writerForReport.write(lineFromFile2);
                        writerForReport.newLine();

                    } else {
                        writerForReport.write(lineFromFile1);
                        writerForReport.write(constants.BLANK);
                        writerForReport.newLine();
                        writerForReport.newLine();

                    }

                    this.lineCount = this.lineCount + 1;
                    lineFromFile1 = readerForFile1.readLine();
                    lineFromFile2 = readerForFile2.readLine();

                }
            }
            writerForReport.flush();
            writerForReport.close();
            return;
        }

    }
}
