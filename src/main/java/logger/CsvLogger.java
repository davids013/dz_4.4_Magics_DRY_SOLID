package logger;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvLogger implements Logger {
    private final String fileName;

    public CsvLogger() {
        fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy HH.mm")) + ".csv";
    }

    public CsvLogger(String fileName) {this.fileName = fileName;}

    @Override
    public boolean log(String message) {
        String[] messageStrings = message.split("\r\n");
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName, false))) {
            for (String s : messageStrings) {
                String[] items = s.split("\t");
                for (int i = 0; i < items.length; i++) {
                    items[i] = items[i].trim();
                }
                writer.writeNext(items);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
