package 王逸群.hrManagerSystem.view.staff;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import 王逸群.hrManagerSystem.db.EvaluationDao;
import 王逸群.hrManagerSystem.entity.Employee;

public class DisplayEvaluation extends JFrame{
	private static final long serivaVersionUID=1L;
	private JPanel contentPane;
	private JTable tbContent;
	private JScrollPane sp;
	Employee employee;
	EvaluationDao rd = new EvaluationDao();
	public DisplayEvaluation(Employee emp){
	    setTitle("查看评测成绩");
	    employee = emp;
	    setBounds(100, 100, 450, 300);
	    setLocationRelativeTo(null);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(new BorderLayout(0,0));
	    JLabel lblInfo = new JLabel(employee.getUsername() + "评测成绩如下：");
	    contentPane.add(lblInfo, BorderLayout.NORTH);
	    tbContent = new JTable();
	    try {
	        rd.fillReportsToTable(employee, tbContent,
	            new String[]{"被评测人", "评测成绩", "评测经理", "评测日期"}, 2);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sp = new JScrollPane(tbContent);
	    contentPane.add(sp, BorderLayout.CENTER);
	}
}
