package 王逸群.hrManagerSystem.entity;

import 王逸群.hrManagerSystem.util.Data;
import 王逸群.hrManagerSystem.util.HrHelper;

import java.util.Scanner;

public class Manager extends  Employee{
    public Manager() {
        super();
    }

    public Manager(int _userID, String _userName, String _password, int _roldID, String _empNO, int _departID, double _salary) {
        super(_userID, _userName, _password, _roldID, _empNO, _departID, _salary);
    }

    HrHelper helper= new HrHelper();
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
    public void displayReports() {
        helper.displayReports();
    }

    @Override
    public void displaySalaryRange() {
        System.out.println("员工的薪资范围是：5000-8000");
    }

    @Override
    public void doWork() {
        HrHelper helper = new HrHelper();
        System.out.println(helper.getDate() + ",Manager工作任务是:");
        System.out.println("==================================");
        System.out.println("\t指定出差计划!");
        System.out.println("==================================");
    }
}
