package 王逸群.hrManagerSystem.db;

import 王逸群.hrManagerSystem.entity.Admin;
import 王逸群.hrManagerSystem.entity.Employee;
import 王逸群.hrManagerSystem.entity.Manager;
import 王逸群.hrManagerSystem.entity.Staff;
import 王逸群.hrManagerSystem.util.DBUtil;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class UserDao {
    public boolean registerUser(String userName, String password,
                                int roleId, String empNo, int departId, double salary)
            throws ClassNotFoundException {
        String strSql = "insert into user(userName,"
                + "password,roleId,empNo,departId,salary)"
                + "values(?,?,?,?,?,?)";
        String[] parameters = new String[]{
                userName,
                password,
                String.valueOf(roleId),
                empNo,
                String.valueOf(departId),
                String.valueOf(salary)
        };
        DBUtil dbUtil = new DBUtil();
        try {
            dbUtil.getConnection();
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean successed = true;
        try {
            dbUtil.executeUpdate(strSql, parameters);
            System.out.println("注册成功，默认身份为普通员工Staff角色！");
        } catch (Exception ex) {
            successed = false;
        } finally {
            dbUtil.closeAll();
        }
        return successed;
    }

    public int getRoleIdByRoleName(String roleName) throws SQLException {
        int result = 0;
        String strSql = "select roleId from role where roleName = ?";
        String[] parameters = new String[]{roleName};
        DBUtil dbUtil = new DBUtil();
        try {
            dbUtil.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = dbUtil.executeQuery(strSql, parameters);
        if (rs.next())
            result = rs.getInt(1);
        dbUtil.closeAll();
        return result;
    }

    public Employee loginByDb(String name, String password) throws
            ClassNotFoundException, SQLException, InstantiationException,
            IllegalAccessException {
        String strSql = "select userId,empNo,userName,password,roleId,departId,"
                + "salary from user where userName =? and password = ?";
        String[] parameters = new String[]{
                name,
                password
        };
        int roleId = 0;
        DBUtil dbUtil = new DBUtil();
        dbUtil.getConnection();
        System.out.println("数据库连接成功！");
        ResultSet rs = dbUtil.executeQuery(strSql, parameters);
        if (rs.next()) {
            roleId = rs.getInt(5);
        }
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String columnType = metaData.getColumnTypeName(i);
            System.out.println(columnName + " - " + columnType);
        }
        if (roleId == 1) {
            return new Staff(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getInt(5),
                    rs.getInt(6), rs.getDouble(7));
        }
        if (roleId == 2) {
            return new Manager(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getInt(4), rs.getString(5),
                    rs.getInt(6), rs.getDouble(7));
        }
        if (roleId == 3) {
            return new Admin(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getInt(4), rs.getString(5),
                    rs.getInt(6), rs.getDouble(7));
        }
        dbUtil.closeAll();
        return null;
    }

    public int getroleId(String name) throws SQLException {
        int result = 0;
        String strsql = "select departid from department where Name=?";
        String[] paraments = new String[]{name};
        DBUtil db = new DBUtil();
        try {
            db.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultSet rs = db.executeStatement(strsql, paraments);
        if (name.equals("信息部")) {
            return 3;
        }
        if (name.equals("市场部")) {
            return 3;
        }
        if (name.equals("开发部")) {
            return 3;
        }
        return 0;

    }

    public Employee getEmployeeByUserId(int userId) throws SQLException, ClassNotFoundException {
        String strSql = "select userid,username,roleid,departid,salary,password,empno from user where userid=?";
        String[] parameters = new String[]{String.valueOf(userId)};
        DBUtil dbUtil = new DBUtil();
        dbUtil.getConnection();
        ResultSet rs = dbUtil.executeStatement(strSql, parameters);
        Employee employee = null;
        try {
            if (rs.next()) {
                int roleId = rs.getInt(3);
                String empNo = rs.getString(7);
                String userName = rs.getString(2);
                int departId = rs.getInt(4);
                double salary = rs.getDouble(5);
                String password = rs.getString(6);
                if (roleId == 1) return new Staff(userId, empNo, userName, password, departId, roleId, salary);
                if (roleId == 2) return new Manager(userId, empNo, userName, password, departId, roleId, salary);
                if (roleId == 3) return new Admin(userId, empNo, userName, password, departId, roleId, salary);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return employee;

    }

    //修改过
    public Employee getEmployeeByEmployeeNo(int userId) throws SQLException, ClassNotFoundException {
        String strSql = "select userid,username,roleid,departid,salary,password,empno from user where userid=?";
        String[] parameters = new String[]{String.valueOf(userId)};
        DBUtil dbUtil = new DBUtil();
        dbUtil.getConnection();
        ResultSet rs = dbUtil.executeStatement(strSql, parameters);
        Employee employee = null;
        try {
            if (rs.next()) {
                int roleId = rs.getInt(3);
                String empNo = rs.getString(7);
                String userName = rs.getString(2);
                int departId = rs.getInt(4);
                double salary = rs.getDouble(5);
                String password = rs.getString(6);
                if (roleId == 1) return new Staff(userId, empNo, userName, password, departId, roleId, salary);
                if (roleId == 2) return new Manager(userId, empNo, userName, password, departId, roleId, salary);
                if (roleId == 3) return new Admin(userId, empNo, userName, password, departId, roleId, salary);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return employee;

    }

    public Manager getDepartmentManagerByUserId(int userId) throws SQLException, ClassNotFoundException {
        Manager Manager = null;
        String[] parameters = new String[]{String.valueOf(userId)};
        String strSql = "select user.username,user.password,user.roleid,user.empno,user.departid,user.salary,role.rolename from user,role,(select departid from user where userid=?) s1 where role.roleid=user.roleid and role.rolename='Manager' and user.departid=s1.departid";
        DBUtil dbUtil = new DBUtil();
        dbUtil.getConnection();
        ResultSet rs = dbUtil.executeStatement(strSql, parameters);
        Employee employee = null;
        try {
            if (rs.next()) {
                String empNo = rs.getString(4);
                String userName = rs.getString(1);
                String password = rs.getString(2);
                int departId = rs.getInt(5);
                int roleId = rs.getInt(3);
                double salary = rs.getDouble(6);
                Manager = new Manager(userId, empNo, userName, password, departId, roleId, salary);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeAll();
        }
        return Manager;

    }

    public int modifyPassWord(Employee emp, String newPassword) throws SQLException, ClassNotFoundException {
        String strSql = "update user set password=? where userid=?";
        String[] parameters = {newPassword, String.valueOf(emp.get_userID())};
        DBUtil db = new DBUtil();
        db.getConnection();
        int result = db.executeUpdate(strSql, parameters);
        db.closeAll();
        return result;

    }

    public ArrayList<Employee> getEmployeesByDepartId(int departid) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> reports = new ArrayList<Employee>();
        DBUtil db = new DBUtil();
        db.getConnection();
        String strSql = null;
        ResultSet rs = null;
        if (departid > 0) {
            strSql = "select userid,username,roleid,departid,salary,password,empno from user where repartid=?";
            String[] parameters = {String.valueOf(departid)};
            rs = db.executeStatement(strSql, parameters);

        } else {
            strSql = "select userid,username,roleid,departid,salary,password,empno from user";
            rs = db.executeStatement(strSql, null);
        }
        while (rs.next()) {
            Employee employee = null;
            int roleId = rs.getInt(3);
            int userId = rs.getInt(1);
            String userName = rs.getString(2);
            String empNo = rs.getString(7);
            double salary = rs.getDouble(5);
            String password = rs.getString(6);
            int depId = rs.getInt(4);
            if (roleId == 1) employee = new Staff(userId, empNo, userName, password, depId, roleId, salary);
            if (roleId == 2) employee = new Manager(userId, empNo, userName, password, depId, roleId, salary);
            if (roleId == 3) employee = new Admin(userId, empNo, userName, password, depId, roleId, salary);
            reports.add(employee);
        }
        db.closeAll();
        return reports;

    }

    public void fillEmployeesToTable(int departId, JTable table, String[] titles) throws SQLException, ClassNotFoundException {
        RoleDao rd = new RoleDao();
        DepartmentDao deptDao = new DepartmentDao();
        Vector<String> vctTitle = new Vector<String>();
        if (titles.length == 0) {
            return;
        }
        Collections.addAll(vctTitle, titles);
        Vector<Vector<String>> vctDatas = new Vector<Vector<String>>();
        ArrayList<Employee> employees = getEmployeesByDepartId(departId);
        for (Employee employee : employees) {
            Vector<String> vctRow = new Vector<String>();
            vctRow.add(employee.get_empNO());
            vctRow.add(employee.getUsername());
            vctRow.add(rd.getRoleNameById(employee.getRoleId()));
            vctRow.add(deptDao.getDepartmentNameById(Integer.parseInt(employee.getDepartId())));
            vctDatas.add(vctRow);
        }
        EditTable et = new EditTable(vctDatas, vctTitle);
        table.setModel(et);
    }

    public void updateUserRole(int newRoleId, int userId) throws SQLException, ClassNotFoundException {
        String strSql = "update user set roleid=? where userid=?";
        DBUtil db = new DBUtil();
        db.getConnection();
        String[] parameters = {String.valueOf(newRoleId), String.valueOf(userId)};
        db.executeStatement(strSql, parameters);
        db.closeAll();
    }

    public String getUserNameEm() throws SQLException, ClassNotFoundException {
        String strSql = "select username from user,report where reporterid=? and reporterid=userid";
        DBUtil db = new DBUtil();
        db.getConnection();
        return null;

    }
}