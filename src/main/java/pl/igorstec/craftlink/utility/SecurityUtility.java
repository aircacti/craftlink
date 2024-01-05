package pl.igorstec.craftlink.utility;

import pl.igorstec.craftlink.config.Settings;

import java.security.SecureRandom;

/**
 * Utility class for security-related operations.
 */
public class SecurityUtility {

    // Character set for generating random keys
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    /**
     * Checks if a security key exists.
     *
     * @return true if the security key exists, false otherwise.
     */
    public static boolean keyExists() {
        return !ConfigUtility.getSecurityKey().isEmpty();
    }

    /**
     * Generates a new security key and sets it in the settings.
     */
    public static void generateNewKey() {
        // Generate a random password of length 16 using the specified character set
        StringBuilder password = new StringBuilder(16);
        SecureRandom random = new SecureRandom();

        // Add random characters until the password reaches the desired length
        while (password.length() < 16) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        // Set the generated security key in the settings
        Settings.getInstance().set("security_key", password.toString());
    }
}
