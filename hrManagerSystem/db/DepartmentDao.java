package 王逸群.hrManagerSystem.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import 王逸群.hrManagerSystem.entity.Department;
import 王逸群.hrManagerSystem.util.*;

import javax.swing.*;

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

	public void getDepartment(JComboBox<Department> cbx) throws SQLException, ClassNotFoundException{
		String strSql = "select departId,name from department";
		//实例化数据库工具
		DBUtil dbUtil = new DBUtil();
		//打开数据库，获取连接对象
		dbUtil.getConnection();
		//查看是否存在登录账号
		ResultSet rs = dbUtil.executeQuery(strSql, null);
		cbx.removeAll();
		while (rs.next()) {
			cbx.addItem(new Department(rs.getInt(1), rs.getString(2)));

		}
		dbUtil.closeAll();
	}
}
