package 王逸群.hrManagerSystem.util;

import 王逸群.hrManagerSystem.entity.*;

public class Data {
    public static Employee currentEmployee;
    //角色值 1 普通员工，2经理，3管理员
    //部门值 1 市场部 2开发部 3信息部
    public static Staff staff = new Staff(1, "Staff", "Staff", 1, "1001", 1, 3000.5);
    public static Manager manager = new Manager(2, "Manager", "Manager", 2, "1002", 2, 5000.5);
    public static Admin admin = new Admin(3, "Admin", "Admin", 3, "1003", 3, 4000.5);
    public static Report[] reports = new Report[1000];

    public static void init() {
        for (int i = 0; i < reports.length; i++) {
            reports[i] = new Report();
        }
    }

}
