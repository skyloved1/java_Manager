package 王逸群.hrManagerSystem.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;

import 王逸群.hrManagerSystem.entity.Employee;
import 王逸群.hrManagerSystem.util.DBUtil;

public class EvaluationDao {
	UserDao userDao=new UserDao();
	public void addEvaluation(Evaluation eva) throws SQLException, ClassNotFoundException {
		String strSql="insert into evaluation(evaluationud,evaluatorid,evaluatedid,score,evaluationdate) values(evaluation_SEQ.nextval,?,?,?,?)";
		DBUtil dbUtil=new DBUtil();
		dbUtil.getConnection();
		String[] parameters= {String.valueOf(eva.getEvaluatorId()),String.valueOf(eva.getEvaluatedId()),String.valueOf(eva.getScore()),LocalDate.now().toString()};
		dbUtil.executeUpdate(strSql, parameters);
		dbUtil.closeAll();
	}
	public ArrayList<Evaluation> getEvaluationsByUserId(int userId) throws SQLException, ClassNotFoundException {
		ArrayList<Evaluation> evaList=new ArrayList<Evaluation>();
		String strSql="select evaluationid,evaluatorid,evaluatedid,score,evaluationdate from evaluation where evaluatedid=? order by evaluationid desc";
		String[] parameters= {String.valueOf(userId)};
		DBUtil dbUtil=new DBUtil();
		dbUtil.getConnection();
		ResultSet rs=dbUtil.executeStatement(strSql, parameters);
		while (rs.next()) {
			Evaluation eva=new Evaluation(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDouble(4),rs.getString(5));
			evaList.add(eva);
			
			
		}
		dbUtil.closeAll();
		return evaList;
		
	}
	public void fillReportsToTable(Employee emp,JTable table,String[] titles,int type) throws SQLException, ClassNotFoundException {
		Vector<String> vctTitle = new Vector<String>();
		if(titles.length == 0)
		    return;

		for(String item : titles)
		    vctTitle.add(item);

		Vector<Vector<String>> vctDatas = new Vector<Vector<String>>();
		ArrayList<Evaluation> evas = getEvaluationsByUserId(emp.getUserId());

		for(Evaluation eva : evas){
		    Vector<String> vctRow = new Vector<String>();
		    //被评测人
		    vctRow.add(userDao.getEmployeeByUserId(eva.getEvaluatedId()).getUsername());
		    //评测成绩
		    vctRow.add(String.valueOf(eva.getScore()));
		    //评测经理
		    vctRow.add(userDao.getEmployeeByUserId(eva.getEvaluatorId()).getUsername());
		    //评测时间
		    vctRow.add(eva.getEvaluationDate());
		    vctDatas.add(vctRow);
		}

		EditTable et = new EditTable(vctDatas, vctTitle);
		table.setModel(et);
	}
}
