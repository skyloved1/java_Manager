package 王逸群.hrManagerSystem.view.comm;

import 王逸群.hrManagerSystem.entity.Employee;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BaseFrame extends JFrame {
	private JPanel pnlBackGround;
	private int frameWidth;
	private int frameHeight;
	private int panelWidth;
	private int panelHeight;
	private int panelX;
	private int panelY;
	private Employee currentEmp;

	public void InitFrame(String title) {
		setSize(frameWidth, frameHeight);
		setTitle(title);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		pnlBackGround = new JPanel();
		pnlBackGround.setLayout(null);
		pnlBackGround.setBounds(panelX, panelY, panelWidth, panelHeight);
		getContentPane().add(pnlBackGround);
	}

	public BaseFrame() {
	}

	public BaseFrame(Employee emoNo) {
		currentEmp = emoNo;
	}

	public JPanel getPnlBackGround() {
		return pnlBackGround;
	}

	public void setPnlBackGround(JPanel pnlBackGround) {
		this.pnlBackGround = pnlBackGround;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getPanelWidth() {
		return panelWidth;
	}

	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}

	public int getPanelHeight() {
		return panelHeight;
	}

	public void setPanelHeight(int panelHeight) {
		this.panelHeight = panelHeight;
	}

	public int getPanelX() {
		return panelX;
	}

	public void setPanelX(int panelX) {
		this.panelX = panelX;
	}

	public int getPanelY() {
		return panelY;
	}

	public void setPanelY(int panelY) {
		this.panelY = panelY;
	}

	public Employee getCurrentEmp() {
		return currentEmp;
	}

	public void setCurrentEmp(Employee currentEmp) {
		this.currentEmp = currentEmp;
	}
	
}
