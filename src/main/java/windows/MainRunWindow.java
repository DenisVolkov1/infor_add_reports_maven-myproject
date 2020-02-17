package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import log.LOg;
import test.TestMain;
import util.DialogWindows;

import java.awt.Cursor;
import javax.swing.JToggleButton;
import javax.swing.border.CompoundBorder;

public class MainRunWindow extends JFrame {
	private static MainRunWindow MAIN_RUN_WINDOW = null;
	private static ImageIcon ICON_SETTING;
	private static Image ICON_WINDOW;
	{
		try {
			ICON_SETTING = new ImageIcon(ImageIO.read(getClass().getResource("/icon_settings.gif")));
			ICON_WINDOW = ImageIO.read(getClass().getResource("/icon_window.png"));
		} catch (IOException e) {
			LOg.logToFile(e);
		}
	}
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainRunWindow frame = MainRunWindow.getInstance();
	TestMain.testMain();
						frame.setVisible(true);
					} catch (Exception e) {
						LOg.logToFile(e);
					}
				}
			});
	}
	/**
	 * Create the frame.
	 */
	private MainRunWindow() {
		this.setResizable(false);
		setTitle("Infor add report");
		setIconImage(ICON_WINDOW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		//Tabs
		//Connection tab
		TabConnectionMSSQLServer tabConnectionMSSQLServer = new TabConnectionMSSQLServer();
		tabbedPane.addTab("Connection", tabConnectionMSSQLServer);
		//Report tab
		tabbedPane.addTab("Add Report", new TabScrollable(TabAddReport.getInstance()));
		//Update tab
		tabbedPane.addTab("Update Report", new TabScrollable(TabUpdateRreport.getInstance()));
		//Delete tab
		tabbedPane.addTab("Delete Report", new TabScrollable(TabDeleteReport.getInstance()));
		//Categories tab
		tabbedPane.addTab("Categories", TabCategories.getInstance());
		//////////////
		tabbedPane.setLocation(0, 74);
		contentPane.add(tabbedPane);
		tabbedPane.setSize(529, 406);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));

		final JButton settings = new JButton(ICON_SETTING);
		settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		settings.setBackground(SystemColor.activeCaption);
		settings.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("icon_settings.gif")));
		settings.setPreferredSize(new Dimension(30, 3));
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SettingsWindow();
			}
		});
		settings.setFont(new Font("Dialog", Font.BOLD, 11));
		settings.setBounds(477, 17, 32, 31);
		contentPane.add(settings);
		
		JLabel lblNewLabel = new JLabel("Server:");
		lblNewLabel.setBorder(new EmptyBorder(1, 1, 1, 1));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(12, 12, 55, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Dialog", Font.BOLD, 13));
		lblIp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIp.setBounds(12, 32, 55, 16);
		contentPane.add(lblIp);
		
		JLabel server;
		JLabel ip;
			try {
				InetAddress inetAddress = InetAddress.getLocalHost();
				
				server = new JLabel(inetAddress.getHostName());
				server.setForeground(new Color(105, 105, 105));
				server.setFont(new Font("Dialog", Font.BOLD, 14));
				server.setBounds(72, 12, 330, 16);
				contentPane.add(server);
				
				ip = new JLabel(inetAddress.getHostAddress());
				ip.setBackground(SystemColor.scrollbar);
				ip.setForeground(new Color(105, 105, 105));
				ip.setFont(new Font("Dialog", Font.BOLD, 14));
				ip.setBounds(72, 31, 330, 17);
				contentPane.add(ip);
			} catch (UnknownHostException e1) {
				LOg.logToFile(e1);
			}
		
		setVisible(true);
	}
	public static MainRunWindow getInstance() {
		if (MAIN_RUN_WINDOW == null) {
			MAIN_RUN_WINDOW = new MainRunWindow();
			return MAIN_RUN_WINDOW;
		} else {
			return MAIN_RUN_WINDOW;
		}
	}
}
