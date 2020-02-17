package windows;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.DialogWindows;
import util.MyHoverButton;
import util.MyProperties;

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
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingConstants;

class SettingsWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton saveButton;
	private JTextField schemaField;
	private JTextField pathArchiveField;
	private JTextField ipBaseField;
	/**
	 * Create the dialog.
	 */
	 SettingsWindow() {
		 super(MainRunWindow.getInstance(), "Settings");
		 this.setResizable(true);
		setTitle("Settings");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 550, 300);
		Point p = MainRunWindow.getInstance().getLocation();
		p.setLocation(p.getX(), p.getY()+100);
		
		setLocation(p);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel schemaLabel = new JLabel("Schema");
		schemaLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		schemaField = new JTextField();
		schemaField.setHorizontalAlignment(SwingConstants.CENTER);
		schemaField.setColumns(10);
		
		JLabel pathArchiveLabel = new JLabel("Path archive war");
		pathArchiveLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		pathArchiveField = new JTextField();
		pathArchiveField.setColumns(10);
		
		JLabel lblIpBaseSqlLabel = new JLabel("IP Base SQL");
		lblIpBaseSqlLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		ipBaseField = new JTextField();
		ipBaseField.setHorizontalAlignment(SwingConstants.CENTER);
		ipBaseField.setColumns(10);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(pathArchiveField, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
						.addComponent(pathArchiveLabel)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
								.addComponent(lblIpBaseSqlLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(ipBaseField, 0, 0, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
								.addComponent(schemaLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(schemaField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))))
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
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIpBaseSqlLabel)
						.addComponent(ipBaseField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(pathArchiveLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pathArchiveField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(107, Short.MAX_VALUE))
		);
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
				MyProperties.saveProperties(
						"schema" , schemaField.getText().trim(),
						"pathArchiveWar" , pathArchiveField.getText().trim(),
						"ipDataBase" , ipBaseField.getText().trim()
						);
				DialogWindows.dialogWindowWarning("Save settings");
			}
		});
		
		schemaField.setText(MyProperties.getProperty("schema"));
		pathArchiveField.setText(MyProperties.getProperty("pathArchiveWar"));
		ipBaseField.setText(MyProperties.getProperty("ipDataBase"));
	}
}
