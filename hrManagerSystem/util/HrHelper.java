package 王逸群.hrManagerSystem.util;

import 王逸群.hrManagerSystem.entity.Employee;
import 王逸群.hrManagerSystem.entity.Evaluation;
import 王逸群.hrManagerSystem.entity.Report;

import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

public class HrHelper {
    //登入检测
    public Employee login(String name, String password) {
        if (name.equals(Data.staff.get_password())) {
            Data.currentEmployee = Data.staff;
        } else if (name.equals(Data.admin.get_password())) {
            Data.currentEmployee = Data.admin;
        } else if (name.equals(Data.manager.get_password())) {
            Data.currentEmployee = Data.manager;
        }
        return Data.currentEmployee;
    }

    public int getReportsCount() {
        int count = 0;
        for (Report item : Data.reports) {
            if (item.get_reportID() <= 0) {
                break;
            }
            count++;
        }
        return count;
    }

    public Employee getEmployeeByUserID(int userId) {
        if (userId == Data.staff.get_userID()) {
            return Data.staff;
        } else if (userId == Data.admin.get_userID()) {
            return Data.admin;
        } else if (userId == Data.manager.get_userID()) {
            return Data.manager;
        } else {
            return null;
        }
    }

    public Employee getEmployeeUserIdByEmpNo(String empNo) {
        if (empNo.equals(Data.staff.get_empNO())) {
            return Data.staff;
        } else if (empNo.equals(Data.manager.get_empNO())) {
            return Data.manager;
        } else if (empNo.equals(Data.admin.get_empNO())) {
            return Data.admin;
        } else {
            return null;
        }
    }

    //显示所有汇报
    public void displayReports() {
        System.out.println("汇报如下:");
        System.out.println("******************************************");
        for (int i = 0; i < Data.reports.length; ++i) {
            //如果遍历完所有汇报，则退出
            if (Data.reports[i] == null) {
                break;
            }
            //得到汇报人相关信息
            Employee employee = getEmployeeByUserID(Data.reports[i].get_reporterID());
            //如果汇报人存在
            if (employee != null) {
                System.out.println("编号:" +
                        Data.reports[i].get_reportID()
                        + "\t汇报人:"
                        + employee.get_userName()
                        + "\t汇报内容:"
                        + Data.reports[i].get_reportContent());
            }
        }

    }

    //添加汇报
    public void addReport(Report report) {
        int count = getReportsCount();
        if (count >= Data.reports.length) {
            System.out.println("汇报数量已满，无法添加");
            return;
        } else {
            Data.reports[count] = report;
            System.out.println("汇报添加成功");
        }
    }

    /*
     *修改员工角色
     * @param empNo 员工编号
     * @param roleName 角色名称
     */
    public void modifyEmployeeRole(String empNo, String roleName) {
        int roleId = 0;
        switch (roleName) {
            case "Staff":
                roleId = 1;
                break;
            case "Manager":
                roleId = 2;
                break;
            case "Admin":
                roleId = 3;
                break;
            default:
                System.out.println("角色名称输入错误");
                break;
        }
        if (empNo.equals(Data.staff.get_empNO())){
            Data.staff.set_roldID(roleId);
        }
        else if(empNo.equals(Data.manager.get_empNO())){
            Data.manager.set_roldID(roleId);
        }
        else if(empNo.equals(Data.admin.get_empNO())){
            Data.admin.set_roldID(roleId);
        }
        else{
            System.out.println("不存在该员工！");
        }
    }

    /**
     * 显示员工信息
     *
     * @param employee 员工对象
     */
    public void displayEmployeeInfo(Employee employee) {
        employee.displayInfo();
    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return year + "年" + month + "月" + day + "日";
    }

    /**
     * 添加考评
     * 获取考评数量
     */
    public int getEvaluationCount() {
        return Data.evaluation.size();
    }

    /**
     * 添加考评
     *
     * @param evaluation 考评对象
     */
    public void addEvaluation(Evaluation evaluation) {
        Data.evaluation.add(evaluation);
    }

    /**
     * 显示所有评测
     */
    public void displayEvaluations() {
        System.out.println("评测如下 ：");
        System.out.println("******************************************");
        for (Evaluation evaluation : Data.evaluation) {
            System.out.println("评测经理: " + getEmployeeByUserID(evaluation.getEvaluatorID()).get_userName()
                    + "\t被评测员工: " + getEmployeeByUserID(evaluation.getEvaluatedID()).get_userName()
                    + "\t评测分数: " + evaluation.getScore());
        }
        //由高到低排序

    }

    public void displayEvaluationsDesc() {
        System.out.println("按评测成绩由高到低排序，排序如下 ：");
        System.out.println("******************************************");
        Collections.sort(Data.evaluation);
        for (Evaluation evaluation : Data.evaluation) {
            System.out.println("评测经理: " + getEmployeeByUserID(evaluation.getEvaluatorID()).get_userName()
                    + "\t被评测员工: " + getEmployeeByUserID(evaluation.getEvaluatedID()).get_userName()
                    + "\t评测分数: " + evaluation.getScore());
        }
    }
}
