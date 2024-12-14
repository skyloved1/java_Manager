package 王逸群.hrManagerSystem.view.comm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import 王逸群.hrManagerSystem.db.UserDao;
import 王逸群.hrManagerSystem.entity.Employee;
import 王逸群.hrManagerSystem.view.staff.StaffMain;

public class Login extends JFrame {
    protected JPanel pnlBackground;
    private JButton btnLogin;
    private JButton btnRegister;
    private JButton btnCancel;
    private JLabel lblLogin;
    private JLabel lblPassword;
    private JTextField txtLogin;
    private JPasswordField txtPassword;

    public JPanel getPnlBackground() {
        return pnlBackground;
    }

    public void setPnlBackground(JPanel pnlBackground) {
        this.pnlBackground = pnlBackground;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(JButton btnLogin) {
        this.btnLogin = btnLogin;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public void setBtnRegister(JButton btnRegister) {
        this.btnRegister = btnRegister;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(JButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    public JLabel getLblLogin() {
        return lblLogin;
    }

    public void setLblLogin(JLabel lblLogin) {
        this.lblLogin = lblLogin;
    }

    public JLabel getLblPassword() {
        return lblPassword;
    }

    public void setLblPassword(JLabel lblPassword) {
        this.lblPassword = lblPassword;
    }

    public JTextField getTxtLogin() {
        return txtLogin;
    }

    public void setTxtLogin(JTextField txtLogin) {
        this.txtLogin = txtLogin;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    UserDao userDao = new UserDao();

    public Login() {

        setTitle("登录");
        // 设置 Frame 大小
        setSize(400, 300);
        // Frame 居中
        setLocationRelativeTo(null);
        // 禁止改变框架大小
        setResizable(false);
        // 设置为空布局
        getContentPane().setLayout(null);
        // 设置 JPanel 基本信息
        pnlBackground = new JPanel();
        // 容器设置为空布局
        pnlBackground.setLayout(null);
        pnlBackground.setBorder(BorderFactory.createTitledBorder(""));
        // 登录标签
        lblLogin = new JLabel();
        lblLogin.setText("用户名:");
        pnlBackground.add(lblLogin);

        // 登录文本框
        txtLogin = new JTextField();
        pnlBackground.add(txtLogin);

        // 密码标签
        lblPassword = new JLabel();
        lblPassword.setText("密码:");
        pnlBackground.add(lblPassword);

        // 密码输入框
        txtPassword = new JPasswordField();
        pnlBackground.add(txtPassword);

        // 登录按钮
        btnLogin = new JButton();

        // 登录按钮注册事件
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = Login.this.txtLogin.getText();
                char[] passwordChars = Login.this.txtPassword.getPassword();
                String password = new String(passwordChars);
                Employee emp = null;
                try {
                    // 参见实践10.1中的UserDao逻辑类
                    try {
                        emp = userDao.loginByDb(name, password);
                    } catch (SQLException | IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }  catch (InstantiationException e1) {
                    e1.printStackTrace();
                }
                if (emp == null) {
                    // 提示，用户名或者密码错误，用户名获取焦点
                    JOptionPane.showMessageDialog(Login.this.btnLogin,"用户名或密码错误，请重新输入");
                    // 用户名输入框得到焦点
                    Login.this.txtLogin.requestFocus();
                    return;
                }
                // 如果登录账号是 Staff 角色
                else if (emp.getRoleId() == 1) {
                    // 显示普通员工主界面窗体，后续实践实现
                    StaffMain sm=new StaffMain(emp);
                    sm.setVisible(true);
                }

                // 如果登录账号是 Manager 角色
                else if (emp.getRoleId() == 2) {
                    System.out.println("暫不实现");
                    // 显示经理主界面窗体，后续实践实现
                    //   ManagerMain mm = new ManagerMain(emp);
                    //    mm.setVisible(true);
                }

                // 如果登录账号是 Admin 角色
                else if (emp.getRoleId() == 3) {
                    // 显示管理员主界面窗体，后续实践实现
//                        AdminMain am = new AdminMain(emp);
//                        am.setVisible(true);
                    System.out.println("暫不实现");

                }

                // 成功登录后，关闭登录窗体
                Login.this.setVisible(false);
            }
        });

        btnLogin.setText("登录");
        pnlBackground.add(btnLogin);

        // 注册按钮
        btnRegister = new JButton();
        btnRegister.setText("注册");
        pnlBackground.add(btnRegister);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                register.setVisible(true);
                Login.this.setVisible(false);
            }
        });

        // 退出按钮
        btnCancel = new JButton();
        btnCancel.setText("退出");
        pnlBackground.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(Login.this, "确定退出？", "退出", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    Login.this.setVisible(false);
                }
            }
        });

        // 坐标布局，参数为左上点坐标、宽、高
        pnlBackground.setBounds(50, 30, 280, 180);
        lblLogin.setBounds(40, 30, 50, 15);
        txtLogin.setBounds(100, 26, 140, 20);
        lblPassword.setBounds(53, 70, 35, 15);
        txtPassword.setBounds(100, 66, 136, 21);
        btnLogin.setBounds(30, 120, 60, 23);
        btnRegister.setBounds(100, 120, 60, 23);
        btnCancel.setBounds(180, 120, 60, 23);

        // 主框架中默认按钮
        this.getRootPane().setDefaultButton(btnLogin);

        // 将内容面板加入框架容器
        getContentPane().add(pnlBackground);
    }
    // 设置登录框内容
    public void setTxtLogin(String value) {

    }

    // 设置密码框内容
    public void setTxtPassword(String value) {
        this.txtPassword.setText(value);
    }
    public static void main(String[] args) {
        Login f=new Login();
        f.setVisible(true);
    }

}




