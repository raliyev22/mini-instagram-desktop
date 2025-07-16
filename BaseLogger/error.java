package BaseLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class error {
    private static final String LOG_FILE_PATH = "/Users/Raul/IdeaProjects/COMP132-Project/COMP132_Project/BaseLogger/application_error.txt";

    public static void log(String message) {
        String formattedMessage = generateLogMessage(message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(formattedMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    private static String generateLogMessage(String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        String timestamp = dateFormat.format(new Date());
        return String.format("[%s][ERROR] %s", timestamp, message);
    }
}
