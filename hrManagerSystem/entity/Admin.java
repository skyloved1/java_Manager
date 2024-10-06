package 王逸群.hrManagerSystem.entity;

import 王逸群.hrManagerSystem.util.HrHelper;

public class Admin extends Employee {
    public Admin() {
        super();
    }

    public Admin(int _userID, String _userName, String _password, int _roldID, String _empNO, int _departID, double _salary) {
        super(_userID, _userName, _password, _roldID, _empNO, _departID, _salary);
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
}
