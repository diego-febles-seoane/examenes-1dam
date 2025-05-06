package es.ies.puerto.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigManager {
    public static class ConfigProperties {
        private static String path;
        private static final Properties properties = new Properties();

        public static String getPath(){
            return path;
        }

        public static void setPath(String pathProperties){
            File file = new File(pathProperties);
            if (!file.exists()) {
                throw new IllegalArgumentException();
            }
            path = pathProperties;
            try {
                FileInputStream input = new FileInputStream(path);
                InputStreamReader isr = new InputStreamReader(input, "UTF-8");
                properties.load(isr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static String getProperties(String clave){
            return properties.getProperty(clave);
        }
    }
}
