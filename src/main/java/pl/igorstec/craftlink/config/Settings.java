package pl.igorstec.craftlink.config;

import org.bukkit.configuration.file.YamlConfiguration;
import pl.igorstec.craftlink.Craftlink;
import pl.igorstec.craftlink.utility.LoggerUtility;

import java.io.File;

public class Settings {

    private final static Settings instance = new Settings();
    private File file;
    private static final String FileName = "settings.yml";
    private YamlConfiguration config;


    public void load() {
        file = new File(Craftlink.getInstance().getDataFolder(), FileName);

        if (!file.exists()) {
            Craftlink.getInstance().saveResource(FileName, false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception ex) {
            LoggerUtility.severe("Failed to load "+FileName+" configuration file");
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception ex) {
            LoggerUtility.severe("Failed to save "+FileName+" configuration file");
        }
    }

    public void set(String path, Object value) {
        config.set(path, value);
        save();
    }

    public Object get(String path) {
        return config.get(path);
    }

    public static Settings getInstance() {
        return instance;
    }
}
