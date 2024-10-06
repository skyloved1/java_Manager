package 王逸群.hrManagerSystem.entity;

public class Report {
    private  int _reportID;
    private int _reporterID;
    private String _reportContent;


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

}
