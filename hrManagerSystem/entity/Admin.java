package 王逸群.hrManagerSystem.entity;

import 王逸群.hrManagerSystem.util.HrHelper;

public class Admin extends Employee {
    public Admin() {
        super();
    }

    public Admin(int _userID, String _userName, String _password, int _roldID, String _empNO, int _departID, double _salary) {
        super(_userID, _userName, _password, _roldID, _empNO, _departID, _salary);
    }
    public Admin(int userId, String empNo, String username, String password, int departId, int roleId, double salary) {
        super(userId, empNo, username, password, departId, roleId, salary);
    }

    public void modifyEmployeeRole(String empNo,String role) {
        HrHelper helper = new HrHelper();
        /**
         * 修改员工角色
         * @param empNo 员工编号
         * @param role  角色名称，只能是Staff,Manager,Admin
         */
        helper.modifyEmployeeRole(empNo,role);
    }

    @Override
    public void displaySalaryRange() {
        System.out.println("员工的薪资范围是：4000-6000");
    }

    /**
     * 显示员工信息
     *
     * @param employee 员工对象
     */
    public void displayEmployeeInfo(Employee employee) {
        HrHelper helper = new HrHelper();
        helper.displayEmployeeInfo(employee);
    }

    @Override
    public void doWork() {
        HrHelper helper = new HrHelper();
        System.out.println(helper.getDate() + ",Admin工作任务是:");
        System.out.println("==================================");
        System.out.println("\t维护员工基本信息!");
        System.out.println("==================================");
    }
}
