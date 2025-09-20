public void main() {
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


// -----------------------------------------------
public void processNumbers(int[] numbers) {
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
public void printPositiveNumbers(int[] numbers) {
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
public int divide(int a, int b) {
    if (b == 0) {
        return 0;
    }
    return a / b;
}


// ------------------------------------------------------
public class User {
    private String name;
    private String email;

    public User(String name, String email) {
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
public class FileReader {
    public String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }
}


// --------------------------------------------------------------
public interface ReportGenerator {
    void generate(String data);
}

public class ExcelReportGenerator implements ReportGenerator {

    @Override
    public void generate(String data) {
        System.out.println("Генерация Excel отчета: " + data);
    }
}
// и т.д.


