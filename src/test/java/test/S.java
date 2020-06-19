package test;

import javax.swing.JDialog;


public class S extends JDialog {



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			S dialog = new S();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public S() {
		super(null,ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 652, 288);
		
	
		SettingParamsPanel settingParamsPanel = new SettingParamsPanel();
	
	
		add(settingParamsPanel);
		setVisible(true);
		

	}
}
