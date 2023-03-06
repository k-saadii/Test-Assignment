package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {


    private static AppProperties appProperties;

    public static final String PROPERTY_FILE_NAME = "app.properties";

    private static final Properties properties = new Properties();


    private AppProperties() {

        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
            properties.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static AppProperties getInstance() {
        if (appProperties == null) {
            appProperties = new AppProperties();
        }
        return appProperties;
    }


    public  String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    // TODO add other methods to get individual datatype properties like
    // getInteger, etc

}

