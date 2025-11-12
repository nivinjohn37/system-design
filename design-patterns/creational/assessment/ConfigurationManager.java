package creational.assessment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static volatile ConfigurationManager instance = null;
    private Properties properties = null;
    private static final String PROPERTIES_FILE = "/Users/nivinsjohn/Documents/Study/system-design/design-patterns/creational/assessment/config.properties";
    
    private ConfigurationManager() {
        if(instance != null) {
            throw new IllegalStateException("ConfigurationManager has already been initialized.");
        }
        loadConfigs();
    }

    private static ConfigurationManager getInstance() {
        if(instance == null) {
            synchronized (ConfigurationManager.class) {
                if(instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    private void loadConfigs() {
        System.out.println("Loading configuration from file: " + PROPERTIES_FILE);
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Error loading configuration file: " + ex.getMessage());
            // In a real app, you might want to handle this more robustly
        }
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    // Example of a main method to run and test
    public static void main(String[] args) {
        // Simulate the config file existing (create a dummy one for testing)
        // Note: You would need a real file named 'app_config.properties' in the project root
        // Key=Value
        // DatabaseUrl=jdbc:mysql://localhost:3306/myapp

        System.out.println("Accessing ConfigManager for the first time...");
        ConfigurationManager manager = ConfigurationManager.getInstance();
        System.out.println("Database URL: " + manager.getProperty("DatabaseUrl", "Default_DB_URL"));

        System.out.println("\nAccessing ConfigManager again...");
        ConfigurationManager manager2 = ConfigurationManager.getInstance();
        System.out.println("Instances are the same object? " + (manager == manager2));
    }
}
