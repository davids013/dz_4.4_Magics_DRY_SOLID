package logger;

import java.util.List;

public class ConsoleLogger implements Logger {

    @Override
    public boolean log(String message) {
        System.out.println(message);
        return true;
    }
}
