package 王逸群.hrManagerSystem.view.comm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import 王逸群.hrManagerSystem.entity.Employee;

public class MainBaseFrame extends JFrame{
	public static final long serialVersionUID=1L;
	//内容面板
	private JPanel pnlBackGround;

	//菜单容器
	private JMenuBar topMenuBar;

	//功能管理菜单
	private JMenu menuFunction;

	//系统管理菜单
	private JMenu menuSysoper;

	//重新登录菜单条
	private JMenuItem miReLogin;

	//表格
	private JTable contentTable;

	//顶端工具条
	private JToolBar toolBarTop;

	//底端状态工具条
	private JToolBar toolBarBottom;

	//表格容器
	private JScrollPane sp;

	//当前登录员工
	private Employee currentEmp;

	private void init(){
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 800, 600);

	    //菜单容器
	    topMenuBar = new JMenuBar();
	    setJMenuBar(topMenuBar);

	    //创建菜单
	    menuFunction = new JMenu("功能管理");
	    topMenuBar.add(menuFunction);

	    menuSysoper = new JMenu("系统管理");
	    topMenuBar.add(menuSysoper);

	    miReLogin = new JMenuItem("重新登录");
	    menuSysoper.add(miReLogin);

	    miReLogin.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Login login = new Login();
	            login.setVisible(true);
	            MainBaseFrame.this.setVisible(false);
	        }
	    });
	  //内容面板
	    pnlBackGround = new JPanel();
	    setContentPane(pnlBackGround);
	    pnlBackGround.setLayout(new BorderLayout());
	    //创建工具条，停靠框架顶端
	    toolBarTop = new JToolBar();
	    pnlBackGround.add(toolBarTop, BorderLayout.PAGE_START);
	    //创建表格
	    DefaultTableModel model = new DefaultTableModel();
	    contentTable = new JTable(model);
	    sp = new JScrollPane(contentTable);
	    pnlBackGround.add(sp, BorderLayout.CENTER);
	    //创建框架底端状态条
	    toolBarBottom = new JToolBar();
	    toolBarBottom.setLayout(new BorderLayout());
	    pnlBackGround.add(toolBarBottom, BorderLayout.PAGE_END);
	}
	    public MainBaseFrame() {
	        init();
	    }

	    //emp 当前登录员工
	    public MainBaseFrame(Employee emp) {
	        init();
	        setCurrentEmp(emp);
	    }
		public JPanel getPnlBackGround() {
			return pnlBackGround;
		}
		public void setPnlBackGround(JPanel pnlBackGround) {
			this.pnlBackGround = pnlBackGround;
		}
		public JMenuBar getTopMenuBar() {
			return topMenuBar;
		}
		public void setTopMenuBar(JMenuBar topMenuBar) {
			this.topMenuBar = topMenuBar;
		}
		public JMenu getMenuFunction() {
			return menuFunction;
		}
		public void setMenuFunction(JMenu menuFunction) {
			this.menuFunction = menuFunction;
		}
		public JMenu getMenuSysoper() {
			return menuSysoper;
		}
		public void setMenuSysoper(JMenu menuSysoper) {
			this.menuSysoper = menuSysoper;
		}
		public JMenuItem getMiReLogin() {
			return miReLogin;
		}
		public void setMiReLogin(JMenuItem miReLogin) {
			this.miReLogin = miReLogin;
		}
		public JTable getContentTable() {
			return contentTable;
		}
		public void setContentTable(JTable contentTable) {
			this.contentTable = contentTable;
		}
		public JToolBar getToolBarTop() {
			return toolBarTop;
		}
		public void setToolBarTop(JToolBar toolBarTop) {
			this.toolBarTop = toolBarTop;
		}
		public JToolBar getToolBarBottom() {
			return toolBarBottom;
		}
		public void setToolBarBottom(JToolBar toolBarBottom) {
			this.toolBarBottom = toolBarBottom;
		}
		public JScrollPane getSp() {
			return sp;
		}
		public void setSp(JScrollPane sp) {
			this.sp = sp;
		}
		public Employee getCurrentEmp() {
			return currentEmp;
		}
		public void setCurrentEmp(Employee currentEmp) {
			this.currentEmp = currentEmp;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	    public static void main(String[] args) {
			MainBaseFrame f=new MainBaseFrame();
			f.setVisible(true);
		}
	     
}