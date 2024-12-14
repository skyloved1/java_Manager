package 王逸群.hrManagerSystem.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import 王逸群.hrManagerSystem.util.DBUtil;
//实践10.1，p551
public class RoleDao {
	public String getRoleNameById(int roleId) throws SQLException, ClassNotFoundException {
		String strSql="select rolename from role where roleid=?";
		String[] parameters=new String[] {String.valueOf(roleId)};
		DBUtil db=new DBUtil();
		db.getConnection();
		String result =null;
		try {
			ResultSet rs=db.executeStatement(strSql, parameters);
			if (rs.next()) {
				result=rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			db.closeAll();
		}
		return result;

	}
}