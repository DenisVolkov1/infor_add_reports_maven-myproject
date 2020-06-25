package windows;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.DialogWindows;
import util.MyProperties;
import util.my_components.MyHoverButton;
import windows.tabs.TabRepositories;
import windows.tabs.add.TabAddReport;
import windows.tabs.update.TabUpdateReport;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;

public class SettingsWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton saveButton;
	private JTextField schemaField;
	private JTextField pathArchiveField;
	private JTextField ipBaseField;
	private JTextField repPasswordField;
	private JTextField repUsernameField;
	private JTextField repPathDirField;
	private JTextField portField;
	private JCheckBox enableAddToRepositoriesCheckBox;
	private JPanel repSettingsPanel;
	/**
	 * Create the dialog.
	 */
	 public SettingsWindow() {
		 super(MainRunWindow.getInstance(), "Settings");
		 this.setResizable(true);
		setTitle("Settings");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 550, 350);
		Point p = MainRunWindow.getInstance().getLocation();
		p.setLocation(p.getX(), p.getY()+100);
		
		setLocation(p);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel schemaLabel = new JLabel("Schema");
		schemaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		schemaField = new JTextField();
		schemaField.setHorizontalAlignment(SwingConstants.CENTER);
		schemaField.setColumns(10);
		
		JLabel pathArchiveLabel = new JLabel("Path archive war");
		pathArchiveLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		pathArchiveField = new JTextField();
		pathArchiveField.setColumns(10);
		
		JLabel lblIpBaseSqlLabel = new JLabel("IP Base SQL");
		lblIpBaseSqlLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		ipBaseField = new JTextField();
		ipBaseField.setHorizontalAlignment(SwingConstants.CENTER);
		ipBaseField.setColumns(10);
		
		repSettingsPanel = new JPanel();
		repSettingsPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Repository files", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		portField = new JTextField();
		portField.setText((String) null);
		portField.setHorizontalAlignment(SwingConstants.CENTER);
		portField.setColumns(10);
		
		JLabel lblPort = new JLabel("port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		enableAddToRepositoriesCheckBox = new JCheckBox("Enable add to Repositories");
		enableAddToRepositoriesCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(repSettingsPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
						.addComponent(enableAddToRepositoriesCheckBox, Alignment.LEADING)
						.addComponent(pathArchiveField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
						.addComponent(pathArchiveLabel, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblIpBaseSqlLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(1)
									.addComponent(ipBaseField, 0, 0, Short.MAX_VALUE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(schemaLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(schemaField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(lblPort)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(portField, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(schemaLabel)
						.addComponent(schemaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(ipBaseField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIpBaseSqlLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addComponent(pathArchiveLabel))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblPort, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(portField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pathArchiveField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(enableAddToRepositoriesCheckBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(repSettingsPanel, GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		repPasswordField = new JTextField();
		repPasswordField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewLabel_1_1 = new JLabel("Username");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		repUsernameField = new JTextField();
		repUsernameField.setColumns(10);
		
		repPathDirField = new JTextField();
		repPathDirField.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Path rep");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_repSettingsPanel = new GroupLayout(repSettingsPanel);
		gl_repSettingsPanel.setHorizontalGroup(
			gl_repSettingsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_repSettingsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_repSettingsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_repSettingsPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(repPasswordField, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_repSettingsPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(repUsernameField, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_repSettingsPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(repPathDirField, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_repSettingsPanel.setVerticalGroup(
			gl_repSettingsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_repSettingsPanel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_repSettingsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(repPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_repSettingsPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(repUsernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_repSettingsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(repPathDirField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		repSettingsPanel.setLayout(gl_repSettingsPanel);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setPreferredSize(new Dimension(10, 35));
			buttonPane.setSize(new Dimension(0, 20));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				saveButton = new MyHoverButton("Save");
				saveButton.setFont(new Font("Dialog", Font.BOLD, 13));
		
			}
			
			JLabel lblNewLabel = new JLabel("Version 1.0");
			lblNewLabel.setForeground(Color.GRAY);
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(215)
						.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 104, Short.MAX_VALUE)
						.addGap(138)
						.addComponent(lblNewLabel)
						.addGap(22))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(4)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE)
							.addComponent(lblNewLabel))
						.addGap(4))
			);
			buttonPane.setLayout(gl_buttonPane);
			//////////////////Code
			/////////////
			primaryInit();
			//////
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	private void primaryInit() {
		if (enableAddToRepositoriesGetSaveSelected()) {
			repSettingsPanel.setVisible(true);
		} else repSettingsPanel.setVisible(false);
		
		// Repo selected?
		enableAddToRepositoriesCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (enableAddToRepositoriesCheckBox.isSelected()) {
					repSettingsPanel.setVisible(true);
				} else {
					repSettingsPanel.setVisible(false);
				}
			}
		});	
		portField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (portField.getText().length() >= 4) // limit textfield to 4 characters
		            e.consume(); 
			}
		});
		
		//SAVE BUTTON
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				for (Component c : contentPanel.getComponents()) {
					if (c instanceof JTextField) {
						String field = ((JTextField)c).getText().trim();
						if (field.isEmpty()) {
							DialogWindows.dialogWindowError("Field is empty.");
							return;
						}
					}
				}
				if (!enableAddToRepositoriesCheckBox.isSelected()) {
					TabAddReport.getInstance().getFoldersProjectComboBox().setSelectedIndex(-1);
					TabAddReport.getInstance().getFoldersProjectComboBox().setEnabled(false);
					TabUpdateReport.getInstance().getFoldersProjectComboBox().setSelectedIndex(-1);
					TabUpdateReport.getInstance().getFoldersProjectComboBox().setEnabled(false);
					MainRunWindow.getInstance();
					//Remove repositories tab from interface
					if (MainRunWindow.getTabbedPane().getTabCount() == 6) {
						MainRunWindow.getInstance();
						MainRunWindow.getTabbedPane().remove(5);
					}
				} else {
					MainRunWindow.getInstance();
					//Add repositories tab to interface	
					MainRunWindow.getTabbedPane().addTab("Repositories", TabRepositories.getInstance());
					//
					boolean isSelectedAddArchiveToggleButton = TabAddReport.getInstance().getAddArchiveToggleButton().isSelected();
					boolean isSelectedAddDataBaseToggleButton = TabAddReport.getInstance().getAddDataBaseToggleButton().isSelected();
					if (isSelectedAddArchiveToggleButton && isSelectedAddDataBaseToggleButton) {
						TabAddReport.getInstance().getFoldersProjectComboBox().setEnabled(true);
					}
					boolean isSelectedUpdateDataBaseToggleButton = TabUpdateReport.getInstance().getUpdateDataBaseToggleButton().isSelected();
					boolean isSelectedUpdateFileArchiveToggleButton = TabUpdateReport.getInstance().getUpdateFileArchiveToggleButton().isSelected();
					if (isSelectedUpdateDataBaseToggleButton && !isSelectedUpdateFileArchiveToggleButton) TabUpdateReport.getInstance().getFoldersProjectComboBox().setEnabled(false);
					
				}
				
				
				String repPathDir = repPathDirField.getText().trim();
				repPathDir = repPathDir.replace('\\', '/');
				//Save properties
				MyProperties.saveProperties(
						"schema" , schemaField.getText().trim(),
						"pathArchiveWar" , pathArchiveField.getText().trim(),
						"ipDataBase" , ipBaseField.getText().trim(),
						"portDataBase",portField.getText().trim(),
						"repPassword", repPasswordField.getText().trim(),
						"repUsername", repUsernameField.getText().trim(),
						"repPathDir", repPathDir,
						"enableAddToRepositories", enableAddToRepositoriesIsSelectedToText()
						);
				DialogWindows.dialogWindowWarning("Save settings");
			}
		});
		//Set saved properties
		schemaField.setText(MyProperties.getProperty("schema"));
		pathArchiveField.setText(MyProperties.getProperty("pathArchiveWar"));
		ipBaseField.setText(MyProperties.getProperty("ipDataBase"));
		portField.setText(MyProperties.getProperty("portDataBase"));
		repPasswordField.setText(MyProperties.getProperty("repPassword"));
		repUsernameField.setText(MyProperties.getProperty("repUsername"));
		repPathDirField.setText(MyProperties.getProperty("repPathDir"));
		enableAddToRepositoriesCheckBox.setSelected(enableAddToRepositoriesGetSaveSelected());
	}
	private String enableAddToRepositoriesIsSelectedToText() {
		if (enableAddToRepositoriesCheckBox.isSelected()) return "true";
		else return "false";
	}
	public static boolean enableAddToRepositoriesGetSaveSelected() {
		String saveProp = MyProperties.getProperty("enableAddToRepositories");
		if (saveProp.equals("true")) return true;
		else return false;
	}
	
}
