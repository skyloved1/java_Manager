package 王逸群.hrManagerSystem.entity;

import 王逸群.hrManagerSystem.util.Data;
import 王逸群.hrManagerSystem.util.HrHelper;

import java.util.Scanner;

public class Staff extends Employee {
    public Staff() {
        super();
    }

    public Staff(int _userID, String _userName, String _password, int _roldID, String _empNO, int _departID, double _salary) {
        super(_userID, _userName, _password, _roldID, _empNO, _departID, _salary);
    }

    /**
     * 添加汇报
     */
    HrHelper helper = new HrHelper();

    public void addReport() {
        System.out.println("请输入汇报信息\n");
        Scanner input = new Scanner(System.in);
        String content = input.next();
        Report report = new Report();
        report.set_reportID(helper.getReportsCount() + 1);
        report.set_reportContent(content);
        report.set_reporterID(Data.currentEmployee.get_userID());
        helper.addReport(report);
    }
}
