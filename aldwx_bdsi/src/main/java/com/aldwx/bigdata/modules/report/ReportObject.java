package com.aldwx.bigdata.modules.report;

import java.util.List;

/**
 * 参考对象
 */
public class ReportObject {
    private String reportName;
    private String dateRange;
    private List<ReportItem> reportItems;
    private List<ReportItem> reportTable;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public List<ReportItem> getReportItems() {
        return reportItems;
    }

    public void setReportItems(List<ReportItem> reportItems) {
        this.reportItems = reportItems;
    }

    public List<ReportItem> getReportTable() {
        return reportTable;
    }

    public void setReportTable(List<ReportItem> reportTable) {
        this.reportTable = reportTable;
    }
}
