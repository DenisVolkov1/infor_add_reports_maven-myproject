package windows.param;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import windows.MainRunWindow;
import windows.tabs.TabSuperClass;
import windows.tabs.add.TabAddReport;
import windows.tabs.update.TabUpdateReport;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ParamsPanel extends JDialog {
	
	protected SettingParamsPanel settingParamsPanel;
	
	/**
	 * Create the dialog.
	 */
	public ParamsPanel() {
		super(MainRunWindow.getInstance(),ModalityType.APPLICATION_MODAL);
		
		settingParamsPanel = new SettingParamsPanel();
		GroupLayout gl_settingParamsPanel = new GroupLayout(settingParamsPanel);
		gl_settingParamsPanel.setHorizontalGroup(
			gl_settingParamsPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 615, Short.MAX_VALUE)
		);
		gl_settingParamsPanel.setVerticalGroup(
			gl_settingParamsPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 240, Short.MAX_VALUE)
		);
		settingParamsPanel.setLayout(gl_settingParamsPanel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addComponent(settingParamsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(8))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(settingParamsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		
		//pack();
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	}

	public SettingParamsPanel getSettingParamsPanel() {
		return settingParamsPanel;
	}
}
