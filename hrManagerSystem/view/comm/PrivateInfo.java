package 王逸群.hrManagerSystem.view.comm;

import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import 王逸群.hrManagerSystem.db.DepartmentDao;
import 王逸群.hrManagerSystem.db.RoleDao;
import 王逸群.hrManagerSystem.entity.Staff;
import 王逸群.hrManagerSystem.util.SwingHrHelper;

public class PrivateInfo extends BaseFrame {
	private JLabel lblInfoTitle;
	private JTextArea ta;

	// 业务逻辑类
	SwingHrHelper helper = new SwingHrHelper();
	RoleDao rd = new RoleDao();
	DepartmentDao deptDao = new DepartmentDao();

	public PrivateInfo(Staff emp) {
        super(emp);
        // 设置初始框架及面板大小
        helper.setInit(this, 410, 310, 0, 0, 330, 240, "个人信息");
        lblInfoTitle = new JLabel();
        ta = new JTextArea();
        StringBuilder sb = new StringBuilder();
        sb.append("员工ID: " + String.valueOf(getCurrentEmp().get_userID()) + "\r\n");
        sb.append("编号: " + emp.get_empNO() + "\r\n");
        sb.append("姓名: " + emp.getUsername() + "\r\n");
        		sb.append("密码: " + emp.getPassword() + "\r\n");
        		try {
        		    sb.append("角色: " + rd.getRoleNameById(getCurrentEmp().getRoleId()) + "\r\n");
        		    sb.append("部门: " + deptDao.getDapartIddByDepartName(getCurrentEmp().getDepartId()) + "\r\n");
        		    System.out.println("测试："+getCurrentEmp().getDepartId());
        		}  catch (SQLException e) {
        		    e.printStackTrace();
        		} catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
        sb.append("薪水: " + String.valueOf(getCurrentEmp().getSalary()) + "\r");

        		// 得到内容面板
        		JPanel pnl = getPnlBackGround();
        		// 向内容面板中加入控件及设置控件布局
        		helper.addComponent(pnl, lblInfoTitle, 35, 18, 100, 15, "个人信息如下: ");
        		helper.addComponent(pnl, ta, 35, 43, 300, 300, sb.toString());
	}
	public static void main(String[] args) {
		Staff s;
		try {
			s = new Staff(1,"DH001","Staff","Staff",2,1,3000.5);
			try {
				PrivateInfo f=new PrivateInfo(s);
			} catch (Exception e) {
				System.out.println(2);
			}
		} catch (Exception e) {
			System.out.println("1");
			e.printStackTrace();
		}
		
		//f.setVisible(true);
	}
}
