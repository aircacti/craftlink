package pl.igorstec.craftlink.utility;

import org.bukkit.Bukkit;
import pl.igorstec.craftlink.Craftlink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for logging messages to the console and a log file.
 */
public class LoggerUtility {

    private static final String FILE_NAME = "log.txt";

    /**
     * Log an informational message to the console and the log file.
     *
     * @param message The message to log.
     */
    public static void info(String message) {
        Bukkit.getLogger().info(message);
        writeToLogFile(message);
    }

    /**
     * Log a severe message to the console and the log file.
     *
     * @param message The severe message to log.
     */
    public static void severe(String message) {
        Bukkit.getLogger().severe(message);
        writeToLogFile(message);
    }

    /**
     * Get the PrintWriter for writing to the log file.
     *
     * @return The PrintWriter for writing to the log file, or null if an error occurs.
     */
    private static PrintWriter getPrintWriter() {
        File dataFolder = Craftlink.getInstance().getDataFolder();

        // Create the plugin log folder if it doesn't exist
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdir()) {
                Bukkit.getLogger().severe("Failed to create plugin log folder");
            } else {
                info("Created plugin log folder");
            }
        }

        File saveTo = new File(Craftlink.getInstance().getDataFolder(), FILE_NAME);

        // Create the log file if it doesn't exist
        if (!saveTo.exists()) {
            try {
                if (saveTo.createNewFile()) {
                    info("Created " + FILE_NAME + " log file");
                }
            } catch (IOException e) {
                Bukkit.getLogger().severe("Failed to create " + FILE_NAME + " log file. " + e.getMessage());
            }
        }

        FileWriter fw;
        try {
            fw = new FileWriter(saveTo, true);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failed to write " + FILE_NAME + " log file. " + e.getMessage());
            return null;
        }

        return new PrintWriter(fw);
    }

    /**
     * Write a message to the log file with a timestamp.
     *
     * @param message The message to write to the log file.
     */
    private static void writeToLogFile(String message) {
        try {
            PrintWriter pw = getPrintWriter();

            if (pw != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timestamp = sdf.format(new Date());

                pw.println("[" + timestamp + "] " + message);

                pw.flush();
                pw.close();
            } else {
                severe("Print writer is null");
            }
        } catch (Exception e) {
            severe("Failed to use write to log file method. " + e.getMessage());
        }
    }
}
