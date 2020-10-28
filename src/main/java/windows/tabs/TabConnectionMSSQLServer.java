package windows.tabs;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import database.ConnectionMSSQL;
import log.LOg;
import util.DialogWindows;
import util.MyProperties;
import util.NewTaskDelay;
import util.my_components.MyHoverButton;
import java.awt.event.ActionListener;
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

public class TabConnectionMSSQLServer extends JPanel {
	
	private static JTextField loginField;
	private static JPasswordField passwordField;
	private JLabel lblNewLabel_1;
	private MyHoverButton connectionButton;
	private JPanel panel;
	private NewTaskDelay connectionToBaseThread;
	private Component panelGlass1;
	/**
	 * Create the panel.
	 */
	public TabConnectionMSSQLServer() {
		setBackground(UIManager.getColor("Button.background"));
		setLayout(null);
		
		panel = new JPanel();
		panel.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "MS SQL Server Authentication", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		panel.setBounds(13, 11, 553, 183);
		add(panel);
		
		JLabel loginLable = new JLabel("Login:");
		loginLable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel passwordLable = new JLabel("Password:");
		passwordLable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		loginField = new JTextField();
		loginField.setMargin(new Insets(3, 3, 3, 3));
		loginField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setMargin(new Insets(3, 3, 3, 3));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1 = new JLabel("");
		connectionButton = new MyHoverButton("Connection");
		connectionButton.setFont(new Font("Tahoma", Font.PLAIN, 14));	

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(87)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(passwordLable)
							.addGap(18))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(loginLable, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(34)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(connectionButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(loginField, Alignment.LEADING)
								.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
							.addGap(10)
							.addComponent(lblNewLabel_1)))
					.addContainerGap(114, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_1)
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
					.addComponent(connectionButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		panel.setLayout(gl_panel);
		//////////////Code
		///////////
		primaryInit();
		/////
	}
	private void primaryInit() {
		try {
			lblNewLabel_1.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/icon_eye.png"))));
			lblNewLabel_1.setVisible(false);
		} catch (IOException e2) {
			LOg.logToFile(e2);
		}
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				passwordField.setEchoChar((char)0);
				lblNewLabel_1.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				passwordField.setEchoChar('•');
				lblNewLabel_1.setVisible(false);
			}
			//•••••••••••••••••••••
		});
		connectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectionToBaseThread = new NewTaskDelay("baseThread",300L) {
					@Override
					public void timerTask() {
						String ipDataBase = MyProperties.getProperty("ipDataBase");
			    		panelGlass1 = setWindowDisable(ipDataBase);
					}
					@Override
					public void taskThread() throws Exception {
						ConnectionMSSQL.getInstanceConneectionJDBC();
					}
					@Override
					protected void afterTask() {
						DialogWindows.dialogWindowWarning("Connection successful!");
					}
					@Override
					public void catchTaskThread(Exception e) {
						LOg.logToFile(e);
						try {
			    			timerTask.cancel();// 
			    		} finally {
							DialogWindows.dialogWindowError(e);
							setWindowEnable(panelGlass1);
						}	
					}
					@Override
					public void cancelTimerTask() {
				    	try {
			    			timerTask.cancel();// 
			    		} finally {
			    			setWindowEnable(panelGlass1);
						}					
					}
				};
		    }
		});
		connectionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				connectionButton.setBackground(new Color(174,197,219));
				connectionButton.setToolTipText("Connection to: "+MyProperties.getProperty("ipDataBase"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				connectionButton.setBackground(new Color(204,227,249));
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
