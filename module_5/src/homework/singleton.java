package homework;


import java.util.HashMap;
import java.util.Map;

public class singleton {
    public static void main(String[] args) {
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        ConfigurationManager config2 = ConfigurationManager.getInstance();

        config1.loadSettings();
        System.out.println(config1.getSetting("app.name"));

        config2.setSetting("app.name", "New name");

        System.out.println(config1.getSetting("app.name"));
        System.out.println(config2.getSetting("app.name"));
    }
}


class ConfigurationManager {
    private static ConfigurationManager _instance;
    private Map<String, String> settings;

    private ConfigurationManager() {
        settings = new HashMap<>();
    }

    public static ConfigurationManager getInstance() {
        if (_instance == null) {
            _instance = new ConfigurationManager();
        }
        return _instance;
    }

    public void loadSettings() {
        settings.put("app.name", "MyApplication");
        settings.put("app.version", "1.0");
        settings.put("log.level", "INFO");
    }

    public String getSetting(String key) {
        return settings.getOrDefault(key, "Не найдено");
    }

    public void setSetting(String key, String value) {
        settings.put(key, value);
    }

}

