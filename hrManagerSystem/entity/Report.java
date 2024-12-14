package 王逸群.hrManagerSystem.entity;

public class Report {
    private  int _reportID;
    private int _reporterID;
    private String _reportContent;
    private String reportdate;
    public Report(int reportId, int reporterId, String content, String reportdate) {
        this._reportID = reportId;
        this._reporterID = reporterId;
        this._reportContent = content;
        this.reportdate = reportdate;
    }

    public Report() {

    }

    public Report(int i, int userId, String text) {
        this._reportID = i;
        this._reporterID = userId;
        this._reportContent = text;
    }

    //setter getter
    public int get_reportID() {
        return _reportID;
    }

    public int get_reporterID() {
        return _reporterID;
    }

    public String get_reportContent() {
        return _reportContent;
    }

    public void set_reportID(int _reportID) {
        this._reportID = _reportID;
    }

    public void set_reporterID(int _reporterID) {
        this._reporterID = _reporterID;
    }

    public void set_reportContent(String _reportContent) {
        this._reportContent = _reportContent;
    }


    public int getReporterId() {
        return _reporterID;
    }

    public String getContent() {
        return _reportContent;
    }

    public void setReportId(int i) {
        this._reportID = i;
    }

    public void setReporterId(int i) {
        this._reporterID = i;
    }
    public void setContent(String s) {
        this._reportContent = s;
    }

    public String getReportdate() {
        return reportdate;
    }
}
