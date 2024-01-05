package pl.igorstec.craftlink.utility;

import pl.igorstec.craftlink.config.Settings;

/**
 * Utility class for managing configuration settings.
 * From here you should operate on configurations.
 */
public class ConfigUtility {

    /**
     * Load configuration settings from the file.
     */
    public static void loadConfigs() {
        Settings.getInstance().load();
    }

    /**
     * Get the server IP from the configuration settings.
     *
     * @return The server IP as a string.
     */
    public static String getServerIp() {
        return String.valueOf(Settings.getInstance().get("server_ip"));
    }

    /**
     * Get the server name from the configuration settings.
     *
     * @return The server name as a string.
     */
    public static String getServerName() {
        return String.valueOf(Settings.getInstance().get("server_name"));
    }

    /**
     * Get the provider IP from the configuration settings.
     *
     * @return The provider IP as a string.
     */
    public static String getProviderIp() {
        return String.valueOf(Settings.getInstance().get("provider_ip"));
    }

    /**
     * Get the security key from the configuration settings.
     *
     * @return The security key as a string.
     */
    public static String getSecurityKey() {
        return String.valueOf(Settings.getInstance().get("security_key"));
    }
}
