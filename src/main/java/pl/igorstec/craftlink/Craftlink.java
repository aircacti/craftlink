package pl.igorstec.craftlink;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.igorstec.craftlink.command.CraftlinkCommand;
import pl.igorstec.craftlink.utility.ConfigUtility;
import pl.igorstec.craftlink.utility.LoggerUtility;
import pl.igorstec.craftlink.utility.SecurityUtility;

import java.util.Objects;


/**
 * Craftlink - Plugin for Bukkit/Spigot servers to enable full Craftlink functionality.
 * This class serves as the main entry point for the Craftlink plugin.
 * Plugin author: Igor Stec
 * Website: igorstec.pl
 */
public final class Craftlink extends JavaPlugin {

    @Override
    public void onEnable() {
        // Load all configs
        ConfigUtility.loadConfigs();

        // Check security key
        if (!SecurityUtility.keyExists()) {
            SecurityUtility.generateNewKey();
            LoggerUtility.info("Generated new security key");
        }

        // Register existing commands
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Registration of plugin commands
     *
     */
    public void registerCommands() {
        Objects.requireNonNull(getCommand("craftlink")).setExecutor(new CraftlinkCommand());
    }

    /**
     * Retrieves the Craftlink plugin instance.
     *
     * @return The Craftlink plugin instance.
     */
    public static Plugin getInstance() {
        return JavaPlugin.getPlugin(Craftlink.class);
    }




}
