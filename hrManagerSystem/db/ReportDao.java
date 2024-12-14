package 王逸群.hrManagerSystem.db;

import 王逸群.hrManagerSystem.entity.Employee;
import 王逸群.hrManagerSystem.entity.Report;
import 王逸群.hrManagerSystem.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;



public class ReportDao {
	UserDao userDao=new UserDao();
	public ArrayList<Report> getReports() throws SQLException, ClassNotFoundException {
		ArrayList<Report> reports=new ArrayList<Report>();
		String strSql="select reportid,reporterid,content,reportDate from report order by reportid desc";
		DBUtil db=new DBUtil();
		db.getConnection();
		ResultSet rs=db.executeStatement(strSql, null);
		while (rs.next()) {
			Report report=new Report(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
			reports.add(report);
		}
		db.closeAll();
		return reports;
	}
	public ArrayList<Report> getReportsByUserId(int userId) throws SQLException, ClassNotFoundException {
		ArrayList<Report> reports=new ArrayList<Report>();
		String strSql="select reportid,reporterid,content,reportdate from report where reporterid=? order by reportid desc";
		DBUtil db=new DBUtil();
		String[] parameters= {String.valueOf(userId)};
		
		db.getConnection();
		ResultSet rs=db.executeStatement(strSql, parameters);
		while (rs.next()) {
			Report report=new Report(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
					reports.add(report)	;	};
			db.closeAll();
		return reports;
		
	}
	public ArrayList<Report> getReportsByDepartId(int departId) throws SQLException, ClassNotFoundException {
		ArrayList<Report> reports=new ArrayList<Report>();
		String strSql="select reportid,reporterid,content,reportdate from report where reporterid in (select userid from user where departid=?)";
		String[] parameters= {String.valueOf(departId)};
		DBUtil db=new DBUtil();
		db.getConnection();
		ResultSet rs=db.executeStatement(strSql, parameters);
		while (rs.next()) {
			Report report=new Report(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));
			reports.add(report);
		}
		db.closeAll();
		return reports;
		
	}
	//添加汇报
	public int addReport(Report report) throws ClassNotFoundException,
	        SQLException, InstantiationException, IllegalAccessException {
	    String strSql = "INSERT INTO REPORT "
	                   + "( REPORTERID, CONTENT, REPORTDATE) "
	                   + "VALUES ( ?, ?, ?)";
	    String[] parameters = {
	        String.valueOf(report.getReporterId()),
	        report.getContent(),
	        LocalDate.now().toString()
	    };
	    DBUtil util = new DBUtil();
	    util.getConnection();
	    int result = util.executeUpdate(strSql, parameters);
	    util.closeAll();
	    return result;
	}
	public void fillReportsToTable(Employee emp, JTable table, String[] titles, int type) throws SQLException, ClassNotFoundException {
		Vector<String> vctTitle=new Vector<String>();
		if (titles.length==0) {
			return;
		}
		for (String item : titles) {
			vctTitle.add(item);
		}
		Vector<Vector<String>> vctDatas=new Vector<Vector<String>>();
		ArrayList<Report> reports=new ArrayList<Report>();
		switch (type) {
		case 1:
			reports=getReportsByUserId(emp.getUserId());
			break;
			case 2:
				reports=getReportsByDepartId(Integer.parseInt(emp.getDepartId()));
			break;
			case 3: 
				reports=getReports();
				break;
		default:
			break;
		}
		for (Report report : reports) {
			
			Vector<String> vctRow =new Vector<String>();
			vctRow.add(userDao.getEmployeeByEmployeeNo(report.getReporterId()).getUsername());
			vctRow.add(report.getContent());
			vctRow.add(report.getReportdate());
			vctDatas.add(vctRow);
			
		}
		EditTable et=new EditTable(vctDatas, vctTitle);
		table.setModel(et);
	}
}
