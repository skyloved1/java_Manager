package 王逸群.hrManagerSystem.view.comm;

import 王逸群.hrManagerSystem.db.UserDao;
import 王逸群.hrManagerSystem.entity.Employee;
import 王逸群.hrManagerSystem.util.SwingHrHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;



public class ModifyPassword extends BaseFrame{
	 private JLabel lblOldPassword;
	    private JLabel lblNewPassword;
	    private JLabel lblConfirmPassword;
	    private JPasswordField txtOldPassword;
	    private JPasswordField txtNewPassword;
	    private JPasswordField txtConfirmPassword;
	    private JButton btnModify;

	    // 服务业务逻辑类
	    SwingHrHelper helper = new SwingHrHelper();
	    UserDao userDao = new UserDao();

	    public ModifyPassword(Employee emp) {
	        super(emp);
	        // 设置主框架及内容面板大小、位置
	        helper.setInit(this, 420, 350, 20, 20, 340, 310, "修改密码");
	        // 实例化
	        lblOldPassword = new JLabel();
	        lblNewPassword = new JLabel();
	        lblConfirmPassword = new JLabel();
	        txtOldPassword = new JPasswordField();
	        txtNewPassword = new JPasswordField();
	        txtConfirmPassword = new JPasswordField();
	        btnModify = new JButton();

	        btnModify.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent ex) {
	                String pass1 = new String(txtNewPassword.getPassword());
	                String pass2 = new String(txtConfirmPassword.getPassword());
	                String oldPass = new String(txtOldPassword.getPassword());
	                if (!oldPass.equals(getCurrentEmp().getPassword())) {
	                    JOptionPane.showMessageDialog(btnModify, "原始密码不正确");
	                    txtOldPassword.requestFocus();
	                    return;
	                }
	                if(!pass1.equals(pass2)){
	                    JOptionPane.showMessageDialog(btnModify, "密码与确认密码不相同");
	                    lblNewPassword.requestFocus();
	                    return;
	                }

	                try {
	                    userDao.modifyPassWord(getCurrentEmp(), new String(txtNewPassword.getPassword()));
	                    JOptionPane.showMessageDialog(btnModify, "修改成功");
	                } catch (SQLException | ClassNotFoundException e) {
	                    JOptionPane.showMessageDialog(btnModify, "修改失败");
	                }
	            }
	        });
	     // 得到内容面板
	        JPanel pnl = getPnlBackGround();

	        // 向内容面板中加入控件及设置控件布局
	        helper.addComponent(pnl, lblOldPassword, 60, 40, 48, 15, "旧密码:");
	        helper.addComponent(pnl, lblNewPassword, 60, 90, 48, 15, "新密码:");
	        helper.addComponent(pnl, lblConfirmPassword, 48, 140, 60, 15, "确认密码:");

	        // 加入可编辑文本框
	        helper.addComponent(pnl, txtOldPassword, 110, 36, 160, 21, null);
	        helper.addComponent(pnl, txtNewPassword, 110, 86, 160, 21, null);
	        helper.addComponent(pnl, txtConfirmPassword, 110, 136, 160, 21, null);

	        // 加入按钮
	        helper.addComponent(pnl, btnModify, 120, 210, 60, 23, "修改");
	    }
}
