package 王逸群.hrManagerSystem.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import 王逸群.hrManagerSystem.entity.Admin;
import 王逸群.hrManagerSystem.entity.Employee;
import 王逸群.hrManagerSystem.entity.Manager;
import 王逸群.hrManagerSystem.entity.Staff;
import 王逸群.hrManagerSystem.util.DBUtil;

public class UserDao {
	public boolean registerUser(String userName,String password,
			int roleId,String empNo,int departId,double salary)
	        throws ClassNotFoundException{
		    String strSql = "insert into user(userName,"
		    		+ "password,roleId,empNo,departId,salary)"
		    		+"values(?,?,?,?,?,?)";
		    String[]parameters = new String[] {
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
				// TODO: handle exception
				successed = false;
			}finally {
				dbUtil.closeAll();
			}
		    return successed;
	}
	public int getRoleIdByRoleName(String roleName) throws SQLException {
        int result = 0;
        String strSql="select roleId from role where roleName = ?";
        String[] parameters =new String[]{ roleName };
        DBUtil dbUtil = new DBUtil();
        try {
            dbUtil.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs =dbUtil.executeQuery(strSql,parameters);
        if (rs.next()) 
            result = rs.getInt(1);
        dbUtil.closeAll();
        return result;    
    }
    public Employee loginByDb(String name, String password)throws
    ClassNotFoundException,SQLException,InstantiationException,
    IllegalAccessException{
        String strSql ="select userId,empNo,userName,password,roleId,departId,"
                +"salary from user where userName =? and password = ?";
        String[] parameters =new String[] {
                name,
                password
        };
        int roleId = 0;
        DBUtil dbUtil =new DBUtil();
        dbUtil.getConnection();
        System.out.println("数据库连接成功！");
        ResultSet rs = dbUtil.executeQuery(strSql, parameters);
        if (rs.next()) {
            roleId = rs.getInt(5);
        }
        if (roleId==1) {
            return new Staff(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getInt(4),rs.getString(5),
                    rs.getInt(6),rs.getDouble(7));}
        if (roleId==2) {
            return new Manager(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getInt(4),rs.getString(5),
                    rs.getInt(6),rs.getDouble(7));
        }
        if (roleId==3) {
            return new Admin(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getInt(4),rs.getString(5),
                    rs.getInt(6),rs.getDouble(7));
        }
        dbUtil.closeAll();
        return null;
    }
}