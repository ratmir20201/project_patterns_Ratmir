package practice;

public class template {
    public static void main(String[] args) {
        ReportGenerator pdf = new PdfReport();
        ReportGenerator excel = new ExcelReport();
        ReportGenerator html = new HtmlReport();

        System.out.println("=== Генерация PDF отчета ===");
        pdf.generateReport();

        System.out.println("\n=== Генерация Excel отчета ===");
        excel.generateReport();

        System.out.println("\n=== Генерация HTML отчета ===");
        html.generateReport();
    }
}

abstract class ReportGenerator {
    public void generateReport() {
        collectData();
        formatReport();
        exportReport();
    }

    protected abstract void collectData();

    protected abstract void formatReport();

    protected abstract void exportReport();
}

class PdfReport extends ReportGenerator {
    @Override
    protected void collectData() {
        System.out.println("Сбор данных для PDF-отчета");
    }

    @Override
    protected void formatReport() {
        System.out.println("Форматирование PDF-отчета");
    }

    @Override
    protected void exportReport() {
        System.out.println("Экспорт PDF-отчета");
    }
}

class ExcelReport extends ReportGenerator {
    @Override
    protected void collectData() {
        System.out.println("Сбор данных для Excel-отчета");
    }

    @Override
    protected void formatReport() {
        System.out.println("Форматирование Excel-отчета");
    }

    @Override
    protected void exportReport() {
        System.out.println("Экспорт Excel-отчета");
    }
}

class HtmlReport extends ReportGenerator {
    @Override
    protected void collectData() {
        System.out.println("Сбор данных для HTML-отчета");
    }

    @Override
    protected void formatReport() {
        System.out.println("Форматирование HTML-отчета");
    }

    @Override
    protected void exportReport() {
        System.out.println("Экспорт HTML-отчета");
    }
}

