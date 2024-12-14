package 王逸群.hrManagerSystem.util;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import 王逸群.hrManagerSystem.view.comm.BaseFrame;

public class SwingHrHelper {
	public void setInit(BaseFrame bf, int frameWidth, int frameHeight, int panelX, int panelY, int panelWidth,
			int panelHeight, String title) {
// 设置主框架大小
		bf.setFrameWidth(frameWidth);
		bf.setFrameHeight(frameHeight);
// 设置内容面板大小
		bf.setPanelX(panelX);
		bf.setPanelY(panelY);
		bf.setPanelWidth(panelWidth);
		bf.setPanelHeight(panelHeight);
// 初始化数据，设置主框架不能修改等;
		bf.InitFrame(title);
	}

//向内容面板中添加控件


	public void addComponent(JPanel panel, JComponent component, int x, int y, int width, int height, String title) {
// 如果是JLabel
		if (component instanceof JLabel) {
// 设置显示内容
			((JLabel) component).setText(title);
		}

		if (component instanceof JButton) {
			// 设置显示内容
			((JButton) component).setText(title);
		}

		if (component instanceof JTextArea) {
			((JTextArea) component).setText(title);
		}

// 将控件加入显示面板
		panel.add(component);

// 设置控件相对内容面板的位置及控件大小
		component.setBounds(x, y, width, height);
	}

// 设置控件是否可编辑
	public void setComponentEnabled(Boolean flag, JComponent[] components) {
		for (JComponent component : components) {
			if (component instanceof JTextField) {
				((JTextField) component).setEnabled(flag);
			}
			if (component instanceof JPasswordField) {
				((JPasswordField) component).setEnabled(flag);
			}
			if (component instanceof JTextArea) {
				((JTextArea) component).setEnabled(flag);
			}
		}
	}
}
