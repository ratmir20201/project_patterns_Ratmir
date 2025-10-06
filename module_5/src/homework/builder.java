package homework;

public class builder {
    public static void main(String[] args) {
        ReportDirector director = new ReportDirector();

        IReportBuilder textBuilder = new TextReportBuilder();
        director.constructReport(textBuilder, "Отчёт за неделю", "Продажи выросли на 20%.", "Конец отчёта");
        Report textReport = textBuilder.getReport();

        System.out.println(textReport);
        System.out.println();

        IReportBuilder htmlBuilder = new HtmlReportBuilder();
        director.constructReport(htmlBuilder, "Отчёт за неделю", "Продажи выросли на 20%.", "Конец отчёта");
        Report htmlReport = htmlBuilder.getReport();

        System.out.println(htmlReport);
    }
}


interface IReportBuilder {
    void setHeader(String header);

    void setContent(String content);

    void setFooter(String footer);

    Report getReport();
}

class Report {
    private String header;
    private String content;
    private String footer;

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public String toString() {
        return header + "\n" + content + "\n" + footer;
    }
}

class TextReportBuilder implements IReportBuilder {
    private Report report;

    public TextReportBuilder() {
        report = new Report();
    }

    @Override
    public void setHeader(String header) {
        report.setHeader("=== " + header + " ===");
    }

    @Override
    public void setContent(String content) {
        report.setContent(content);
    }

    @Override
    public void setFooter(String footer) {
        report.setFooter("--- " + footer + " ---");
    }

    @Override
    public Report getReport() {
        return report;
    }
}

class HtmlReportBuilder implements IReportBuilder {
    private Report report;

    public HtmlReportBuilder() {
        report = new Report();
    }

    @Override
    public void setHeader(String header) {
        report.setHeader("<h1>" + header + "</h1>");
    }

    @Override
    public void setContent(String content) {
        report.setContent("<p>" + content + "</p>");
    }

    @Override
    public void setFooter(String footer) {
        report.setFooter("<footer>" + footer + "</footer>");
    }

    @Override
    public Report getReport() {
        return report;
    }
}

class ReportDirector {
    public void constructReport(IReportBuilder builder, String header, String content, String footer) {
        builder.setHeader(header);
        builder.setContent(content);
        builder.setFooter(footer);
    }
}

