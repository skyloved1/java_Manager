package 王逸群.hrManagerSystem.util;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*import java.lang.foreign.PaddingLayout;*/

import javax.crypto.spec.DHGenParameterSpec;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DialogDemo extends JFrame implements ActionListener{
       private JMenuBar menuBar;
       private JMenu menuManage;
       private JMenuItem miAddUser;
       //声明一个滚动面板
       private JScrollPane sp;
       private JTextArea txtContent;
       //声明对话框
       private UserDialog userDialog;
       public DialogDemo() {
    	   super("对话框");
    	   menuBar = new JMenuBar();
    	   this.setJMenuBar(menuBar);
    	   menuManage = new JMenu("用户管理");
    	   menuBar.add(menuManage);
    	   miAddUser = new JMenuItem("添加新用户");
    	   menuManage.add(miAddUser);
    	   //注册监听
    	   miAddUser.addActionListener(this);
    	   txtContent = new JTextArea(20,30);
    	   //实例化一个滚动面板，置入文本域
    	   sp = new JScrollPane(txtContent);
    	   //将滚动面板添加到窗体中
    	   this.add(sp);
    	   this.setSize(300, 200);
    	   this.setLocation(100, 100);
    	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       }
       //重写事件处理方法
       public void actionPerformed(ActionEvent e){
		//判断是否实例化对话框
    	   if(userDialog == null) {
    		   userDialog = new UserDialog(this);
    	   }
    	   //显示对话框
    	   userDialog.setVisible(true);
	}
       public static void main(String[] args) {
    	   DialogDemo f = new DialogDemo();
    	   f.setVisible(true);
		
	}
       //创建一个对话框类
       class UserDialog extends JDialog implements ActionListener{
    	   JPanel p;
    	   JLabel lblName,lblPwd,lblRepwd,lblType;
    	   JTextField txtName;
    	   JPasswordField txtPwd;
    	   JPasswordField txtRepwd;
    	   JComboBox cmbType;
    	   JButton btnOK,btnCancle;
    	   public UserDialog(JFrame f) {
    		   super(f,"添加新用户",true);
    		   p = new JPanel(new GridLayout(5,2));
    		   lblName = new JLabel("用户名");
    		   lblPwd = new JLabel("密码");
    		   lblRepwd = new JLabel("确认密码");
    		   lblType = new JLabel("类型");
    		   txtName = new JTextField(10);
    		   txtPwd = new JPasswordField(10);
    		   txtRepwd = new JPasswordField(10);
    		   String str[] = {"教师","学生","管理员"};
    		   cmbType = new JComboBox(str);
    		   btnOK = new JButton("确定");
    		   btnCancle = new JButton("取消");
    		   //注册监听
    		   btnOK.addActionListener(this);
    		   btnCancle.addActionListener(this);
    		   p.add(lblName);
    		   p.add(txtName);
    		   p.add(lblPwd);
    		   p.add(txtPwd);
    		   p.add(lblRepwd);
    		   p.add(txtRepwd);
    		   p.add(lblType);
    		   p.add(cmbType);
    		   p.add(btnOK);
    		   p.add(btnCancle);
    		   this.add(p);
    		   //设置合适大小
    		   this.pack();
    	   }
    	   //重写事件处理方法
    	   public void actionPerformed(ActionEvent e) {
    		   //单击确定按钮时
    		   if (e.getSource() == btnOK) {
				//获取信息
    			   String strName = txtName.getText();
    			   String strPwd = new String(txtPwd.getPassword());
    			   String strRepwd = new String(txtRepwd.getPassword());
    			   //验证
    			   if (strName.equals("")) {
    				   JOptionPane.showMessageDialog(btnOK, "用户名不能为空! ");
    				   return;
				}
    			   if (strPwd.equals("")) {
					JOptionPane.showMessageDialog(btnOK, "密码不能为空! ");
					return;
				}
    			   if (strPwd.length()<6||strPwd.length()>10) {
					JOptionPane.showMessageDialog(btnOK, "密码长度应在6~10之间! ");
					return;
				}
    			   if (strRepwd.equals("")) {
    				JOptionPane.showMessageDialog(btnOK, "确认密码不能为空! ");
   					return;
				}
    			   if (strRepwd.length()<6||strRepwd.length()>10) {
    				JOptionPane.showMessageDialog(btnOK, "确认密码的密码长度应在6~10之间! ");
   					return;
				}
    			   if (!strRepwd.equals(strPwd)) {
					JOptionPane.showMessageDialog(btnOK, "密码和确认密码输入不一致! ");
					return;
				}
    			   String strType = cmbType.getSelectedItem().toString();
    			   //数据库访问
    			   DBUtil db = new DBUtil();
    			   try {
					db.getConnection();
					String sql = "INSERT INTO userdetail VALUES(?,?,?)";
					if (db.executeUpdate(sql, new String[] {
							strName,strPwd,strType }) == 1) {
						txtContent.append("新用户添加成功!\n\n");
					}else {
						txtContent.append("新用户添加失败! 请检查数据是否正确,再重新添加! \n\n");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}finally {
					db.closeAll();
				}
    			 //隐藏对话框
    			   this.setVisible(false);
			}
    		   if (e.getSource() == btnCancle) {
				//清空文本框
    			   txtName.setText("");
    			   txtPwd.setText("");
    			   //隐藏
    			   this.setVisible(false);
			}
    	   }
       }
}
