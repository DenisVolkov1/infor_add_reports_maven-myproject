package windows.param;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import windows.MainRunWindow;
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

public class NewParam extends JDialog {
	
	
	private SettingParamsPanel settingParamsPanel;

	/**
	 * Create the dialog.
	 */
	public NewParam() {
		super(MainRunWindow.getInstance(), "New Params");
		setBounds(100, 100, 652, 300);
		//setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setVisible(true);
		settingParamsPanel = new SettingParamsPanel();
		getContentPane().add(settingParamsPanel);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int size = settingParamsPanel.getlistOfParams().size();
				if(size != 0) TabAddReport.getInstance().getNewParamButton().setStandartHover();
				else TabAddReport.getInstance().getNewParamButton().setEmptyHover();
				
			}
		
		});
	}

	public SettingParamsPanel getSettingParamsPanel() {
		return settingParamsPanel;
	}
}
