import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class homework {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}

//------------------------------------------
enum LogLevel {
    INFO,
    WARNING,
    ERROR
}

class Logger {
    public void log(String message, LogLevel level) {
        System.out.println(level.name() + ": " + message);
    }
}

//-----------------------------------------------
class AppConfig {
    public static final String CONNECTION_STRING = "Server=myServer;Database=myDb;User=myUser;Password=myPass;";
}

class DatabaseService {
    public void connect() {
        String connectionString = AppConfig.CONNECTION_STRING;
        System.out.println("Подключение к базе: " + connectionString);
        // Логика подключения
    }
}

class LoggingService {
    public void log(String message) {
        String connectionString = AppConfig.CONNECTION_STRING;
        System.out.println("Сохраняем лог в БД: " + message);
        // Логика записи лога в БД
    }
}

class Utils {
    // -----------------------------------------------
    void processNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return;
        }

        for (int number : numbers) {
            if (number > 0) {
                System.out.println(number);
            }
        }
    }


    // ---------------------------------------------------
    void printPositiveNumbers(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return;
        }

        Arrays.sort(numbers); // сортируем один раз

        for (int number : numbers) {
            if (number > 0) {
                System.out.println(number);
            }
        }
    }


    // ----------------------------------------------------
    int divide(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }
}

// ------------------------------------------------------
class User__1 {
    private String name;
    private String email;

    public User__1(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}


//-----------------------------------------------------------------
class FileReader {
    public String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }
}


// --------------------------------------------------------------
interface ReportGenerator {
    void generate(String data);
}

class ExcelReportGenerator implements ReportGenerator {

    @Override
    public void generate(String data) {
        System.out.println("Генерация Excel отчета: " + data);
    }
}
// и т.д.

