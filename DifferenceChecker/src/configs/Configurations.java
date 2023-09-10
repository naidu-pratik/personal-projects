package configs;

import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;

public class Configurations {

    public Properties propertyLoader() throws IOException {
        Properties properties = new Properties();
        FileReader reader = new FileReader((new Constants()).PROPERTY_FILE_PATH);
        properties.load(reader);
        return properties;
    }
}
