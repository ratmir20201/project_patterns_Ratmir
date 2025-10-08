package practice;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


public class builder {
    public static void main(String[] args) throws IOException {
        ReportDirector director = new ReportDirector();

        ReportStyle style1 = new ReportStyle("#ffffff", "#000000", 14);
        ReportStyle style2 = new ReportStyle("#f0f0f0", "#003366", 16);

        IReportBuilder textBuilder = new TextReportBuilder();
        director.constructReport(textBuilder, style1);
        Report textReport = textBuilder.getReport();
        textReport.export("report_text", "text");

        IReportBuilder htmlBuilder = new HtmlReportBuilder();
        director.constructReport(htmlBuilder, style2);
        Report htmlReport = htmlBuilder.getReport();
        htmlReport.export("report_html", "html");

        System.out.println("Отчеты успешно созданы: TXT, HTML");
    }

}


interface IReportBuilder {
    void setHeader(String header);

    void setContent(String content);

    void setFooter(String footer);

    void addSection(String sectionName, String sectionContent);

    void setStyle(ReportStyle style);

    Report getReport();
}

class ReportStyle {
    public String backgroundColor;
    public String fontColor;
    public int fontSize;

    public ReportStyle(String backgroundColor, String fontColor, int fontSize) {
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
    }

    @Override
    public String toString() {
        return String.format("Стиль [фон=%s, цвет шрифта=%s, размер=%d]", backgroundColor, fontColor, fontSize);
    }
}


class Report {
    private String header;
    private String content;
    private String footer;
    private ReportStyle style;
    private Map<String, String> sections = new LinkedHashMap<>();

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void setStyle(ReportStyle style) {
        this.style = style;
    }

    public void addSection(String name, String content) {
        sections.put(name, content);
    }

    public void export(String fileName, String format) throws IOException {
        switch (format.toLowerCase()) {
            case "text":
                try (FileWriter fw = new FileWriter(fileName + ".txt")) {
                    fw.write(toPlainText());
                }
                break;
            case "html":
                try (FileWriter fw = new FileWriter(fileName + ".html")) {
                    fw.write(toHtml());
                }
                break;
            case "pdf":
                // Тут логика
                break;
            default:
                throw new IllegalArgumentException("Неизвестный формат: " + format);
        }
    }

    private String toPlainText() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(header).append(" ===\n\n");
        sb.append(content).append("\n\n");
        sections.forEach((name, content) -> {
            sb.append("-- ").append(name).append(" --\n").append(content).append("\n\n");
        });
        sb.append("=== ").append(footer).append(" ===\n");
        return sb.toString();
    }

    private String toHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><style>").append(String.format("body { background-color:%s; color:%s; font-size:%dpx; }", style.backgroundColor, style.fontColor, style.fontSize)).append("</style></head><body>");
        sb.append("<h1>").append(header).append("</h1>");
        sb.append("<p>").append(content).append("</p>");
        sections.forEach((name, content) -> {
            sb.append("<h2>").append(name).append("</h2>");
            sb.append("<p>").append(content).append("</p>");
        });
        sb.append("<footer>").append(footer).append("</footer>");
        sb.append("</body></html>");
        return sb.toString();
    }
}

class PdfReportBuilder implements IReportBuilder {
    private Report report;

    public PdfReportBuilder() {
        this.report = new Report();
    }

    public void setHeader(String header) {
        report.setHeader(header);
    }

    public void setContent(String content) {
        report.setContent(content);
    }

    public void setFooter(String footer) {
        report.setFooter(footer);
    }

    public void addSection(String sectionName, String sectionContent) {
        report.addSection(sectionName, sectionContent);
    }

    public void setStyle(ReportStyle style) {
        report.setStyle(style);
    }

    public Report getReport() {
        return report;
    }
}

class TextReportBuilder implements IReportBuilder {
    private Report report;

    public TextReportBuilder() {
        this.report = new Report();
    }

    public void setHeader(String header) {
        report.setHeader(header);
    }

    public void setContent(String content) {
        report.setContent(content);
    }

    public void setFooter(String footer) {
        report.setFooter(footer);
    }

    public void addSection(String sectionName, String sectionContent) {
        report.addSection(sectionName, sectionContent);
    }

    public void setStyle(ReportStyle style) {
        report.setStyle(style);
    }

    public Report getReport() {
        return report;
    }
}

class HtmlReportBuilder implements IReportBuilder {
    private Report report;

    public HtmlReportBuilder() {
        this.report = new Report();
    }

    public void setHeader(String header) {
        report.setHeader(header);
    }

    public void setContent(String content) {
        report.setContent(content);
    }

    public void setFooter(String footer) {
        report.setFooter(footer);
    }

    public void addSection(String sectionName, String sectionContent) {
        report.addSection(sectionName, sectionContent);
    }

    public void setStyle(ReportStyle style) {
        report.setStyle(style);
    }

    public Report getReport() {
        return report;
    }
}

class ReportDirector {
    public void constructReport(IReportBuilder builder, ReportStyle style) throws IOException {
        builder.setStyle(style);
        builder.setHeader("Отчет о продажах за 2025 год");
        builder.setContent("Основное содержание отчета с ключевыми метриками.");
        builder.addSection("Продажи", "Объем продаж увеличился на 20%.");
        builder.addSection("Расходы", "Расходы остались на уровне прошлого года.");
        builder.setFooter("Отчет подготовлен системой BuilderReports © 2025");
    }
}
