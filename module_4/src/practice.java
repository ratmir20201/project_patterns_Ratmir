import java.util.Scanner;

public class practice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите тип документа: ");
        String user_input = scanner.nextLine();

        Document document = DocumentService.getDocument(user_input);
        System.out.println(document);
    }
}

class DocumentService {
    public static Document getDocument(String documentType) {
        DocumentCreator documentCreator = null;

        if ("report".equalsIgnoreCase(documentType)) {
            documentCreator = new ReportCreator();
        } else if ("resume".equalsIgnoreCase(documentType)) {
            documentCreator = new ResumeCreator();
        } else if ("letter".equalsIgnoreCase(documentType)) {
            documentCreator = new LetterCreator();
        } else {
            throw new IllegalArgumentException("Unknown transport type: " + documentType);
        }

        return documentCreator.createDocument();
    }
}

interface Document {
    void open();
}

class Report implements Document {

    @Override
    public void open() {
        System.out.println("Отчет открывается");
    }
}

class Resume implements Document {

    @Override
    public void open() {
        System.out.println("Резюме открывается");
    }
}

class Letter implements Document {

    @Override
    public void open() {
        System.out.println("Письмо открывается");
    }
}

abstract class DocumentCreator {
    abstract Document createDocument();
}

class ReportCreator extends DocumentCreator {

    @Override
    Document createDocument() {
        return new Report();
    }
}

class ResumeCreator extends DocumentCreator {

    @Override
    Document createDocument() {
        return new Resume();
    }
}

class LetterCreator extends DocumentCreator {

    @Override
    Document createDocument() {
        return new Letter();
    }
}

