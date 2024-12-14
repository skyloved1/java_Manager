package 王逸群.hrManagerSystem.view.comm;

import 王逸群.hrManagerSystem.db.UserDao;
import 王逸群.hrManagerSystem.entity.Employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame{
	//面板访问属性protected
	protected JPanel pnlBackground;
     private JButton btnLogin;
     private JButton btnRegister;
     private JButton btnCancle;
     private JLabel lblLogin;
     private JLabel lblPassword;
     private JTextField txtLogin;
     private JPasswordField txtPassword;
     
     UserDao userDao = new UserDao();
     
     //构造，实现窗体初始化
     public Login() {
    	 setTitle("登录");
    	 setSize(400, 300);
    	 setLocationRelativeTo(null);//居中显示
         setResizable(false);
         //设置为空布局
         getContentPane().setLayout(null);
         pnlBackground = new JPanel();
         pnlBackground.setLayout(null);
         pnlBackground.setBorder(BorderFactory.createTitledBorder("登录"));
         //登录标签
         lblLogin = new JLabel("用户名");
         pnlBackground.add(lblLogin);
         txtLogin = new JTextField();
         pnlBackground.add(txtLogin);
         
         lblPassword = new JLabel("密码");
         pnlBackground.add(lblPassword);
         txtPassword = new JPasswordField();
         pnlBackground.add(txtPassword);
         //登录按钮
         btnLogin = new JButton("登录");
         //登录按钮添加监听
         btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取用户名和密码文本
				String name = txtLogin.getText();
				char[] passwordChars = txtPassword.getPassword();
				String password = new String(passwordChars);
				
				Employee emp = null;
				try {
					//当前登录用户
					emp = userDao.loginByDb(name, password);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (emp == null) {
					//提示，用户名和密码错误，用户名获取焦点
					JOptionPane.showMessageDialog(Login.this.btnLogin,"用户名或者密码错误,请重新输入");
					txtLogin.requestFocus();
					return;
				} else if(emp.getRoleId() == 1){
                    JOptionPane.showMessageDialog(Login.this.btnLogin,"员工成功登录! ");
                    return;
				}else if(emp.getRoleId() == 2){
                    JOptionPane.showMessageDialog(Login.this.btnLogin,"经理成功登录! ");
                    return;
				}else if(emp.getRoleId() == 3){
                    JOptionPane.showMessageDialog(Login.this.btnLogin,"管理成功登录! ");
                    return;
				}
				//成功登录后，关闭登录窗体
				Login.this.setVisible(false);
			}
		});
         
         pnlBackground.add(btnLogin);
         //注册登录按钮
         btnRegister = new JButton("注册");
         pnlBackground.add(btnRegister);
         btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
				Login.this.setVisible(false);
			}
		});
         //退出按钮
         btnCancle = new JButton("退出");
         pnlBackground.add(btnCancle);
         btnCancle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(btnCancle, "确认退出?", "退出",JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_NO_OPTION) {
					Login.this.setVisible(false);
				}
				
			}
		});
       //坐标布局
         pnlBackground.setBounds(50, 30, 280, 180);
         lblLogin.setBounds(40, 30, 50, 15);
         txtLogin.setBounds(100, 26, 140, 20);
         lblPassword.setBounds(53, 70, 35, 15);
         txtPassword.setBounds(100, 66, 136, 21);
         btnLogin.setBounds(30, 120, 60, 23);
         btnRegister.setBounds(100, 120, 60, 23);
         btnCancle.setBounds(180, 120, 60, 23);
         //主框架中默认按钮，输入用户名和密码，回车，即可登录
         //this.getRootPane().setDefaultButton(btnLogin);
         //将内容面板加入框架容器
         getContentPane().add(pnlBackground);
     }
     //设置登录框内容
     public void setTxtLogin(String value) {
		this.txtLogin.setText(value);
	}
     
     //设置密码框内容
     public void setTxtPassword(String value) {
    	 this.txtPassword.setText(value);
		
	}
     public static void main(String[] args) {
		new Login().setVisible(true);
	}
	
}
