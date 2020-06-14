package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import windows.param.SettingParamsPanel;

public class S extends JDialog {

	private final SettingParamsPanel contentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			S dialog = new S();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public S() {
		setBounds(100, 100, 650, 137);
		contentPanel = new SettingParamsPanel();
		getContentPane().add(contentPanel);

	}

}
