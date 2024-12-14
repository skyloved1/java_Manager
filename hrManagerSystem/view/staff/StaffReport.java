package 王逸群.hrManagerSystem.view.staff;

import 王逸群.hrManagerSystem.db.ReportDao;
import 王逸群.hrManagerSystem.db.UserDao;
import 王逸群.hrManagerSystem.entity.Employee;
import 王逸群.hrManagerSystem.entity.Manager;
import 王逸群.hrManagerSystem.entity.Report;
import 王逸群.hrManagerSystem.util.HrHelper;
import 王逸群.hrManagerSystem.util.SwingHrHelper;
import 王逸群.hrManagerSystem.view.comm.BaseFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class StaffReport extends BaseFrame {
	private JLabel lblReporterEmpNo;  // 标签
	private JLabel lblReporterName;   // 标签
	private JLabel lblReportManager;  // 标签
	private JLabel lblReportContent;  // 标签
	private JTextField txtReporterEmpNo;  // 文本字段
	private JTextField txtReporterName;   // 文本字段
	private JTextField txtReportManager;  // 文本字段
	private JTextArea txtReportContent;   // 多行文本区域
	private JScrollPane sp;               // 滚动条容器
	private JButton btnSubmit;            // 按钮
	private StaffMain staffMain;          // 自定义类
	Manager manager = null;
	UserDao userDao = new UserDao();
	ReportDao rd = new ReportDao();

	// Swing界面业务逻辑类，具体参见
	SwingHrHelper helper = new SwingHrHelper();

	public StaffReport(Employee emp, StaffMain parent) {
	    super(emp);
	    // 设置初始框架及面板大小
	    helper.setInit(this, 460, 400, 0, 0, 460, 400, "汇报工作");
	    staffMain = parent;
	    lblReporterEmpNo = new JLabel();
	    lblReporterName = new JLabel();
	    lblReportManager = new JLabel();
	    lblReportContent = new JLabel();
	    txtReporterEmpNo = new JTextField();
	    txtReporterEmpNo.setText(getCurrentEmp().get_empNO());
	    txtReporterName = new JTextField();
	    txtReporterName.setText(getCurrentEmp().getUsername());
	    try {
	        manager = userDao.getDepartmentManagerByUserId(emp.getRoleId());
	    } catch (SQLException | ClassNotFoundException e) {
	    	System.out.println("stffreport p59");
	        e.printStackTrace();
	    }
	    txtReportManager = new JTextField();
	    if(manager != null)
	        txtReportManager.setText(manager.getUsername());
	    System.out.println("测试三："+manager.getUsername());
	    //设置以下文本框不能编辑
	    helper.setComponentEnabled(false, new JComponent[]{ 
	        txtReporterEmpNo, txtReporterName, txtReportManager});
	    txtReportContent = new JTextArea();
	    txtReportContent.setColumns(20);
	    txtReportContent.setRows(5);
	    sp = new JScrollPane();
	    sp.setViewportView(txtReportContent);
	    btnSubmit = new JButton();
	    btnSubmit.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Report report = new Report(0,
	                getCurrentEmp().getUserId(),
	                txtReportContent.getText());
	            int affectedCount = 0;
	            try {
	                affectedCount = rd.addReport(report);
	            } catch (ClassNotFoundException e1) {
	                e1.printStackTrace();
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            } catch (InstantiationException e1) {
	                e1.printStackTrace();
	            } catch (IllegalAccessException e1) {
	                e1.printStackTrace();
	            }
	            if (affectedCount > 0) {
	                JOptionPane.showMessageDialog(
	                    StaffReport.this.btnSubmit, "汇报成功");
	                StaffReport.this.setVisible(false);
	                staffMain.reFillTable();
	            } else {
	                JOptionPane.showMessageDialog(
	                    StaffReport.this.btnSubmit, "汇报失败");
	            }
	        }
	        });
	  //panel 容器
	    JPanel pnl = getPnlBackGround();
	    //设置标签布局，调用业务逻辑类中的方法
	    helper.addComponent(pnl, lblReporterEmpNo, 36, 38, 72, 15, "汇报人编号:");
	    helper.addComponent(pnl, lblReporterName, 60, 77, 48, 15, "汇报人:");
	    helper.addComponent(pnl, lblReportManager, 50, 115, 60, 15, "汇报经理:");
	    helper.addComponent(pnl, lblReportContent, 50, 151, 60, 15, "汇报内容:");
	    //设置输入控件布局，调用业务逻辑类中的方法
	    helper.addComponent(pnl, txtReporterEmpNo, 118, 35, 175, 21, null);
	    helper.addComponent(pnl, txtReporterName, 118, 74, 175, 21, null);
	    helper.addComponent(pnl, txtReportManager, 118, 112, 175, 21, null);
	    helper.addComponent(pnl, sp, 118, 163, 299, 146, null);
	    //按钮
	    helper.addComponent(pnl, btnSubmit, 174, 330, 81, 23, "提交");
	}
	public static void main(String[] args) {
		//StaffReport f=new StaffReport(new Staff(), new StaffMain());
		//f.setVisible(true);
	}
}
