package 王逸群.hrManagerSystem.view.staff;

import 王逸群.hrManagerSystem.db.ReportDao;
import 王逸群.hrManagerSystem.entity.Employee;
import 王逸群.hrManagerSystem.entity.Staff;
import 王逸群.hrManagerSystem.view.comm.MainBaseFrame;
import 王逸群.hrManagerSystem.view.comm.ModifyPassword;
import 王逸群.hrManagerSystem.view.comm.PrivateInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class StaffMain extends MainBaseFrame {
    // 菜单项
    private JMenuItem miDisplayWork; // 显示工作记录菜单项
    private JMenuItem miReport; // 报表菜单项
    private JMenuItem miEvaluation; // 评价菜单项
    private JMenuItem miModifyPassword; // 修改密码菜单项

    // 按钮
    private JButton btnDisplayWork; // 显示工作记录按钮
    private JButton btnReport; // 报表按钮
    private JButton btnEvaluation; // 评价按钮
    private JButton btnChat; // 聊天按钮
    private JButton btnModifyPassword; // 修改密码按钮

    // 标签
    private JLabel lblInfo1; // 信息标签1
    private JLabel lblInfo2; // 信息标签2

    // 表格
    JTable employeeTable; // 员工表格
    ReportDao rd = new ReportDao();

    public StaffMain() {
    }

    public StaffMain(Employee emp) {
        super(emp);
        setTitle("普通员工体验面板");
// 得到基类框架中菜单对象
        JMenu functionMenu = getMenuFunction();
// 得到基类框架中顶端工具条对象
        JToolBar tbTop = getToolBarTop();
// 得到基类框架中底端工具条对象
        JToolBar tbBottom = getToolBarBottom();
// 得到基类框架中表格对象
//employeeTable=new JTable();
        employeeTable = getContentTable();
//		JScrollPane scrollPane = new JScrollPane(employeeTable);

        ActionListener privateListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PrivateInfo pi = new PrivateInfo((Staff) getCurrentEmp());
                pi.setVisible(true);
            }
        };
        ActionListener reportListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StaffReport sr = new StaffReport(getCurrentEmp(), StaffMain.this);
                sr.setVisible(true);
            }
        };
        ActionListener evaluationListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// 查看评测
                DisplayEvaluation eva = new DisplayEvaluation(getCurrentEmp());
                eva.setVisible(true);
            }
        };

        ActionListener chatListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// 在线交流，后面实践课实现
                JOptionPane.showMessageDialog(new JButton("确定"), "你要认王子龙当义父吗？");
            }
        };

        ActionListener modifyPasswordListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// 修改密码，后续实现
                ModifyPassword mp = new ModifyPassword(getCurrentEmp());
                mp.setVisible(true);
            }
        };

// 添加菜单条子条目
        miDisplayWork = new JMenuItem("查看个人信息");
        functionMenu.add(miDisplayWork);
        miDisplayWork.addActionListener(privateListener);
        miReport = new JMenuItem("汇报工作");
        functionMenu.add(miReport);
        miReport.addActionListener(reportListener);
        miEvaluation = new JMenuItem("查看测评");
        functionMenu.add(miEvaluation);
        miEvaluation.addActionListener(evaluationListener);
        miModifyPassword = new JMenuItem("修改密码");
        functionMenu.add(miModifyPassword);
        miModifyPassword.addActionListener(modifyPasswordListener);

// 添加顶端工具条子按钮
        btnDisplayWork = new JButton();
        btnDisplayWork.setText("查看个人信息");
        btnDisplayWork.addActionListener(privateListener);
        btnReport = new JButton();
        btnReport.setText("汇报工作");
        btnReport.addActionListener(reportListener);
        btnEvaluation = new JButton();
        btnEvaluation.setText("查看评测");
        btnEvaluation.addActionListener(evaluationListener);
        btnChat = new JButton();
        btnChat.setText("在线交流");
        btnChat.addActionListener(chatListener);
        btnModifyPassword = new JButton();
        btnModifyPassword.setText("修改密码");
        btnModifyPassword.addActionListener(modifyPasswordListener);

        tbTop.add(btnDisplayWork);
        tbTop.add(btnEvaluation);
        tbTop.add(btnChat);
        tbTop.add(btnReport);
        tbTop.add(btnModifyPassword);

// 添加基类框架中底端工具条子对象
        lblInfo1 = new JLabel();
        lblInfo1.setText("当前登录用户:" + getCurrentEmp().getUsername());
        lblInfo2 = new JLabel();
        lblInfo2.setText("当前角色: Staff");
        tbBottom.add(lblInfo1, BorderLayout.WEST);
        tbBottom.add(lblInfo2, BorderLayout.EAST);

        try {
            rd.fillReportsToTable(getCurrentEmp(), employeeTable, new String[]{"汇报人", "汇报内容", "汇报日期"}, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 重新装载汇报信息，添加一条汇报后需要即时刷新主窗体显示表格
    public void reFillTable() {
        try {
            rd.fillReportsToTable(getCurrentEmp(), employeeTable, new String[]{"汇报人", "汇报内容", "汇报日期"}, 3);
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StaffMain f = new StaffMain(new Staff());
        f.setVisible(true);
    }
}