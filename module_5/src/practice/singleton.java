package practice;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class singleton {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            new Thread(() -> {
                Logger log = Logger.getInstance();
                log.log("Поток " + threadId + " — информационное сообщение", LogLevel.INFO);
                log.log("Поток " + threadId + " — предупреждение", LogLevel.WARNING);
                log.log("Поток " + threadId + " — ошибка", LogLevel.ERROR);
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Ошибки из лога ===");
        LogReader.readLogs("./my_logs.log", LogLevel.ERROR);
    }
}

enum LogLevel {
    INFO,
    WARNING,
    ERROR
}

class Logger {
    private static Logger _instance;
    private LogLevel _logLevel;
    private String logFilePath;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Logger() {
        _logLevel = LogLevel.INFO;
        logFilePath = "./my_logs.log";
    }

    public static synchronized Logger getInstance() {
        if (_instance == null) {
            _instance = new Logger();
        }
        return _instance;
    }

    public void log(String message, LogLevel level) {
        if (level.ordinal() < _logLevel.ordinal()) {
            return;
        }

        String formattedMessage = String.format("[%s] [%s] %s",
                dateFormat.format(new Date()), level, message);

        try (FileWriter fw = new FileWriter(logFilePath, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(formattedMessage);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка при записи лога: " + e.getMessage());
        }
    }

    public void setLogLevel(LogLevel level) {
        this._logLevel = level;
        log("Изменён уровень логирования на: " + level, LogLevel.INFO);
    }

}

class LogReader {

    public static void readLogs(String filePath, LogLevel minLevel) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (LogLevel level : LogLevel.values()) {
                    if (line.contains("[" + level + "]") && level.ordinal() >= minLevel.ordinal()) {
                        System.out.println(line);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении логов: " + e.getMessage());
        }
    }
}