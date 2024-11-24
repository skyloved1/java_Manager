package 王逸群.hrManagerSystem.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import 王逸群.hrManagerSystem.util.*;

public class DepartmentDao {
	public int getDapartIddByDepartName(String departName)throws SQLException,ClassNotFoundException {
		String strSql = "select departId from department where name = ?";
		String[] parameters = new String[] {departName};
		DBUtil dbUtil = new DBUtil();
		dbUtil.getConnection();
		System.out.println("数据库连接成功！");
		ResultSet rs = dbUtil.executeQuery(strSql, parameters);
		int result = 0;
		if (rs.next()) {
			result = rs.getInt(1);
		}
		dbUtil.closeAll();
		return result;
	}
}
