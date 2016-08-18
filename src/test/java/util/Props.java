package util;


import java.io.IOException;
import java.util.Properties;

public class Props {
    private static final String APP_PROPERTIES = "/app.properties";

    private static Properties loadProperty() throws IOException {
        Properties props = new Properties();
        props.load(Props.class.getResourceAsStream(APP_PROPERTIES));
        return props;
    }

    public static String getKey (String key){
        try {
            return loadProperty().getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
