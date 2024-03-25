package dev.galliard.contasoc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import dev.galliard.contasoc.Contasoc;

public class ConfigManager {

    private Properties properties;
    private String configFilePath;

    public ConfigManager() {
        this.configFilePath = System.getProperty("user.home") + File.separator + ".contasoc" + File.separator + "contasoc.properties";
        this.properties = new Properties();
    }

    public void loadConfig() {
        File configFile = new File(configFilePath);
        if (configFile.exists()) {
            try (InputStream inputStream = new FileInputStream(configFile)) {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createDefaultConfig();
        }
    }

    private void createDefaultConfig() {
        try {
            File configFile = new File(configFilePath);
            File defaultConfigFile = new File(ConfigManager.class.getClassLoader().getResource("default.properties").toURI());
            configFile.getParentFile().mkdirs();

            // Copiar el archivo default.properties al directorio .contasoc si no existe
            if (!configFile.exists()) {
                try (InputStream inputStream = new FileInputStream(defaultConfigFile);
                     OutputStream outputStream = new FileOutputStream(configFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                }
            }
            
            new File(System.getProperty("user.home")+File.separator+".contasoc"+File.separator+"backups").mkdir();

            // Cargar las propiedades
            try (InputStream inputStream = new FileInputStream(configFile)) {
                properties.load(inputStream);
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        try {
            File configFile = new File(configFilePath);
            configFile.getParentFile().mkdirs();
            try (OutputStream outputStream = new FileOutputStream(configFile)) {
                properties.store(outputStream, "Updated contasoc.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String withPlaceholders(String key, String[] placeholders) {
        String value = properties.getProperty(key);
        for (int i = 0; i < placeholders.length; i++) {
            value = value.replace("%" + (i + 1) + "%", placeholders[i]);
        }
        return value;
    }
}
