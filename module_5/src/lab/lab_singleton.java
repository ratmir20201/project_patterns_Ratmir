package lab;

public class lab_singleton {
    public static void main(String[] args) {
        Logger my_logger = Logger.getInstance();
        Logger my_logger_2 = Logger.getInstance();

        System.out.println(my_logger);
        System.out.println(my_logger_2);

        my_logger.log("Информационное сообщение", LogLevel.INFO);

        my_logger.setLogLevel(LogLevel.WARNING);
        my_logger.log("Информационное сообщение", LogLevel.INFO);

        my_logger.log("Предупреждение", LogLevel.WARNING);
        my_logger_2.log("Ошбика", LogLevel.ERROR);
    }
}

enum LogLevel {
    INFO,
    WARNING,
    ERROR
}

class Logger {
    private static Logger _instance;
    private LogLevel logLevel = LogLevel.INFO;
    ;

    private Logger() {
    }

    public static Logger getInstance() {
        if (_instance == null) {
            _instance = new Logger();
        }
        return _instance;
    }

    public void log(String message, LogLevel level) {
        if (level.ordinal() < logLevel.ordinal()) {
            return;
        }

        System.out.println("[" + level + "] " + message);
    }

    public void setLogLevel(LogLevel level) {
        this.logLevel = level;
    }
}