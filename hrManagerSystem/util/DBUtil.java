package 王逸群.hrManagerSystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs =null;
	public Connection getConnection()throws ClassNotFoundException,SQLException{
		try {
			String driver = Config.getValue("driver");
			String url = Config.getValue("url");
			String user = Config.getValue("user");
			String pwd = Config.getValue("pwd");
			try {
				Class.forName(driver);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException("驱动错误或连接失败！");
		}
	}
	public void closeAll() {
		try {
			if (rs != null){
				rs.close();
			}
			if (ps != null){
				ps.close();
			}
			if (conn != null){
				conn.close();
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public ResultSet executeQuery(String preparedSql,String[]param) {
		try {
			ps = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					ps.setString(i+1, param[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	public int executeUpdate(String preparedSql,String[]param) {
		int count = 0;
		try {
			ps = conn.prepareStatement(preparedSql);
			if (param !=null) {
				for (int i = 0; i < param.length; i++) {
					ps.setString(i+1, param[i]);
				}
			}
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public ResultSet executeStatement(String pr, String[] pa) {
		try {
			if (conn == null || conn.isClosed()) {
				throw new SQLException("数据库连接未建立");
			}
			ps = conn.prepareStatement(pr);
			if (pa != null) {
				for (int i = 0; i < pa.length; i++) {
					ps.setString(i + 1, pa[i]);
				}
			}
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
} 
