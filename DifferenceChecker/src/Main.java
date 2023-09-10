import configs.Configurations;
import configs.Constants;
import fileCompare.DiffChecker;
import messages.ErrorMessage;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = (new Configurations()).propertyLoader();
        Constants constants = new Constants();
        ErrorMessage errorMessage = new ErrorMessage();
        DiffChecker diffChecker = new DiffChecker();

        try {
            diffChecker.compareFiles(properties.getProperty(constants.PATH_FILE_1), properties.getProperty(constants.PATH_FILE_2));
            System.out.println(constants.REPORT_SUCCESS + properties.getProperty(constants.PATH_REPORT));
        } catch (Exception exception) {

            System.out.println(errorMessage.ERROR+ exception);
            System.out.println(errorMessage.REPORT_FAILED);
        }
    }
}