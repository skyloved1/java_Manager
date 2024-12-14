package 王逸群.hrManagerSystem.view.comm;

import 王逸群.hrManagerSystem.db.DepartmentDao;
import 王逸群.hrManagerSystem.db.UserDao;
import 王逸群.hrManagerSystem.entity.Department;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Register extends JFrame {
    //标签
    private JLabel lblEmpno;
    private JLabel lblName;
    private JLabel lblPassword;
    private JLabel lblPasswordConfirm;
    private JLabel lblDepartment;
    private JLabel lblSalary;
    //文本框
    private JTextField txtEmpno;
    private JTextField txtName;
    private JTextField txtSalary;
    //密码框
    private JPasswordField txtPassword;
    private JPasswordField txtPasswordConfirm;
    //按钮
    private JButton btnRegister;
    private JButton btnReturn;
    //下拉列表框
    private JComboBox cobDepartment;
    //内容面板
    private JPanel pnlBackground;
    //(1)定义UserDao,DepartmentDao 类的对象，是为了通过对象调用该类方法
    UserDao userDao = new UserDao();
    DepartmentDao deptDao = new DepartmentDao();

    //构造初始化
    public Register() {
        setTitle("用户注册");
        setSize(450, 360);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        pnlBackground = new JPanel();
        pnlBackground.setLayout(null);
        lblEmpno = new JLabel();
        lblEmpno.setText("员工编号:");
        pnlBackground.add(lblEmpno);
        txtEmpno = new JTextField();
        pnlBackground.add(txtEmpno);
        lblName = new JLabel();
        lblName.setText("员工姓名:");
        pnlBackground.add(lblName);
        txtName = new JTextField();
        pnlBackground.add(txtName);
        lblPassword = new JLabel();
        lblPassword.setText("密码:");
        pnlBackground.add(lblPassword);
        txtPassword = new JPasswordField();
        pnlBackground.add(txtPassword);
        lblPasswordConfirm = new JLabel();
        lblPasswordConfirm.setText("确认密码:");
        pnlBackground.add(lblPasswordConfirm);
        txtPasswordConfirm = new JPasswordField();
        pnlBackground.add(txtPasswordConfirm);
        lblDepartment = new JLabel();
        lblDepartment.setText("部门:");
        pnlBackground.add(lblDepartment);
        cobDepartment = new JComboBox();
        pnlBackground.add(cobDepartment);
        try {
            deptDao.getDepartment(cobDepartment);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lblSalary = new JLabel();
        lblSalary.setText("薪资:");
        pnlBackground.add(lblSalary);
        txtSalary = new JTextField();
        pnlBackground.add(txtSalary);
        btnRegister = new JButton();
        btnRegister.setText("注册");
        pnlBackground.add(btnRegister);
        btnRegister.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                char[] passwordChar = Register.this.txtPassword.getPassword();
                char[] passwordConfirmChar = Register.this.txtPasswordConfirm.getPassword();
                String empNo = Register.this.txtEmpno.getText();
                String userName = Register.this.txtName.getText();
                String password = new String(passwordChar);
                String passwordConfirm = new String(passwordConfirmChar);
                double salary;
                int departId =
                        ((Department) Register.this.cobDepartment.getSelectedItem()).getDepartId();
                if (!password.equals(passwordConfirm)) {
                    JOptionPane.showMessageDialog(Register.this.btnRegister, "密码和确认密码不一致");
                    Register.this.txtPassword.requestFocus();
                    return;
                }
                try {
                    salary = Double.parseDouble(Register.this.txtSalary.getText());
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(Register.this.btnRegister, "薪资必须是数字");
                    Register.this.txtSalary.requestFocus();
                    return;
                }
                try {
                    if (userDao.registerUser(userName, password, 1, empNo, departId, salary)) {
                        JOptionPane.showMessageDialog(Register.this.btnRegister, "注册成功,确定后返回登录窗体");
                    } else {
                        JOptionPane.showMessageDialog(Register.this.btnRegister, "注册失败");
                        return;
                    }

                } catch (HeadlessException e2) {
                    e2.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                Login login = new Login();
                login.setTxtLogin(Register.this.txtName.getText());
                login.setTxtPassword(password);
                login.setVisible(true);
                Register.this.setVisible(false);
            }
        });
        btnReturn = new JButton();
        btnReturn.setText("返回");
        pnlBackground.add(btnReturn);
        pnlBackground.setBounds(40, 20, 360, 290);
        lblEmpno.setBounds(40, 30, 60, 15);
        txtEmpno.setBounds(110, 26, 180, 21);
        lblName.setBounds(40, 60, 60, 15);
        txtName.setBounds(110, 56, 180, 21);
        lblPassword.setBounds(66, 90, 36, 15);
        txtPassword.setBounds(110, 86, 180, 21);
        lblPasswordConfirm.setBounds(40, 120, 60, 15);
        txtPasswordConfirm.setBounds(110, 116, 180, 21);
        lblDepartment.setBounds(66, 150, 36, 15);
        cobDepartment.setBounds(110, 146, 180, 21);
        lblSalary.setBounds(66, 180, 36, 15);
        txtSalary.setBounds(110, 176, 180, 21);
        btnRegister.setBounds(80, 220, 60, 23);
        btnReturn.setBounds(190, 220, 60, 23);
        this.getRootPane().setDefaultButton(btnRegister);
        getContentPane().add(pnlBackground);
    }
}
