package windows;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import database.ConnectionMSSQL;
import util.DialogWindows;
import util.MyHoverButton;
import util.MyProperties;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JToggleButton;

public class TabConnectionMSSQLServer extends JPanel {
	
	private static JTextField loginField;
	private static JPasswordField passwordField;
	private JLabel lblNewLabel;
	private MyHoverButton testButton;
	private JPanel panel;
	/**
	 * Create the panel.
	 */
	TabConnectionMSSQLServer() {
		setBackground(UIManager.getColor("Button.background"));
		setLayout(null);
		
		 panel = new JPanel();
		panel.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "MS SQL Server Authentication", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setBounds(10, 11, 480, 183);
		add(panel);
		
		JLabel loginLable = new JLabel("Login:");
		loginLable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel passwordLable = new JLabel("Password:");
		passwordLable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		loginField = new JTextField();
		loginField.setMargin(new Insets(3, 3, 3, 3));
		loginField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginField.setColumns(10);
		lblNewLabel = new JLabel("");

		passwordField = new JPasswordField();
		passwordField.setMargin(new Insets(3, 3, 3, 3));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel = new JLabel("");
		testButton = new MyHoverButton("Test Connection");
		testButton.setFont(new Font("Tahoma", Font.PLAIN, 14));	

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(testButton, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(loginLable, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(loginField, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(passwordLable)
							.addGap(18)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addComponent(lblNewLabel)
					.addContainerGap(108, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(loginLable, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(loginField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(8)
									.addComponent(passwordLable)))))
					.addGap(18)
					.addComponent(testButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		//////////////Code
		///////////
		primaryInit();
		/////
	}
	private void primaryInit() {
		try {
			lblNewLabel.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/icon_eye.png"))));
			lblNewLabel.setVisible(false);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				passwordField.setEchoChar((char)0);
				lblNewLabel.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				passwordField.setEchoChar('•');
				lblNewLabel.setVisible(false);
			}
			//•••••••••••••••••••••
		});
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		        try (Connection con = ConnectionMSSQL.getInstanceConneectionJDBC()) {
		        	DialogWindows.dialogWindowWarning("Connection successful!");
		   
				} catch (Exception e1 ) {
					 DialogWindows.dialogWindowError(e1);
				}
		        setCursor(null);
		    }
		});
		testButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				testButton.setBackground(new Color(174,197,219));
				testButton.setToolTipText("Connection to: "+MyProperties.getProperty("ipDataBase"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				testButton.setBackground(new Color(204,227,249));
			}
		});
		String saveLogin = MyProperties.getProperty("login");
		String savePassword = MyProperties.getProperty("password");
		if (saveLogin != "" && savePassword != "") {
			loginField.setText(saveLogin);
			passwordField.setText(savePassword);
		}
	}
	public static JTextField getLoginField() {		return loginField;
	}
	public static JPasswordField getPasswordField() {
		return passwordField;
	}
}
