package practice;

import java.util.Date;

public class decorator {
    public static void main(String[] args) {
        IReport report = new SalesReport();
        IReport report2 = new UserReport();
        System.out.println(report.generate());
        System.out.println(report2.generate());

        Date start = new Date(2025, 0, 1);
        Date end = new Date(2025, 10, 3);

        report = new SortingDecorator(report, "amount");
        System.out.println(report.generate());

        report2 = new DateFilterDecorator(report2, start, end);
        System.out.println(report2.generate());

    }
}

interface IReport {
    String generate();
}

class SalesReport implements IReport {

    @Override
    public String generate() {
        return "Sales report data";
    }
}

class UserReport implements IReport {

    @Override
    public String generate() {
        return "User report data";
    }
}

abstract class ReportDecorator implements IReport {
    protected IReport report;

    protected ReportDecorator(IReport report) {
        this.report = report;
    }

    public String generate() {
        return report.generate();
    }
}

class DateFilterDecorator extends ReportDecorator {
    private Date startDate;
    private Date endDate;

    protected DateFilterDecorator(IReport report, Date startDate, Date endDate) {
        super(report);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String generate() {
        return "Filtered by dates " + startDate + " " + endDate + report.generate();
    }
}

class SortingDecorator extends ReportDecorator {
    private String criteria;

    protected SortingDecorator(IReport report, String criteria) {
        super(report);
        this.criteria = criteria;
    }

    @Override
    public String generate() {
        return "Sorted by " + criteria + " " + report.generate();
    }
}