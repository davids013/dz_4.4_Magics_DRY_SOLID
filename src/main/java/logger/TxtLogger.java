package logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TxtLogger implements Logger {
    private final String fileName;

    public TxtLogger() {
        fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy HH.mm")) + ".txt";
    }

    public TxtLogger(String fileName) {this.fileName = fileName;}

    @Override
    public boolean log(String message) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(message);
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
