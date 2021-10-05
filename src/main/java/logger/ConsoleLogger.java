package logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements Logger {

    @Override
    public boolean log(String message) {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm:ss.SSS");
        System.out.println(time.format(dtf) + "\t" + message);
        return false;
    }
}
