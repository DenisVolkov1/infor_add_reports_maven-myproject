package windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import log.LOg;
import util.MyProperties;
import util.my_components.WaitPanel;
import windows.tabs.TabCategories;
import windows.tabs.TabConnectionMSSQLServer;
import windows.tabs.TabDeleteReport;
import windows.tabs.TabRepositories;
import windows.tabs.TabScrollable;
import windows.tabs.add.TabAddReport;
import windows.tabs.update.TabUpdateReport;

import java.awt.Cursor;

public class MainRunWindow extends JFrame {

	private static final long serialVersionUID = 1710679467847343991L;
	private static MainRunWindow MAIN_RUN_WINDOW = null;
	private static ImageIcon ICON_SETTING;
	private static Image ICON_WINDOW;
	private static JTabbedPane tabbedPane;
	private static JPanel glassPanel;	
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
		  try {
			  String slaf = MyProperties.getProperty("windowsTheme");
			  if (slaf == null) slaf = "javax.swing.plaf.metal.MetalLookAndFeel";
	            UIManager.setLookAndFeel(slaf.equals("true")?"com.sun.java.swing.plaf.windows.WindowsLookAndFeel":"javax.swing.plaf.metal.MetalLookAndFeel");
	        } catch (UnsupportedLookAndFeelException ex) {
	            ex.printStackTrace();
	        } catch (IllegalAccessException ex) {
	            ex.printStackTrace();
	        } catch (InstantiationException ex) {
	            ex.printStackTrace();
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainRunWindow frame = MainRunWindow.getInstance();
						//Test main
							//TestMain.testMain();
							////////////////////////////////////////
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
		setBounds(100, 100, 601, 527);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		//Tabs
		//Connection tab
		TabConnectionMSSQLServer tabConnectionMSSQLServer = new TabConnectionMSSQLServer();
		tabbedPane.addTab("Connection", tabConnectionMSSQLServer);
		//Report tab
		tabbedPane.addTab("Add Report", new TabScrollable(TabAddReport.getInstance()));
		//Update tab
		tabbedPane.addTab("Update Report", new TabScrollable(TabUpdateReport.getInstance()));
		//Delete tab
		tabbedPane.addTab("Delete Report", new TabScrollable(TabDeleteReport.getInstance()));
		//Categories tab
		tabbedPane.addTab("Categories", TabCategories.getInstance());
		//Repositories tab
		if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) tabbedPane.addTab("Repositories", TabRepositories.getInstance());
		
		// Set glass panel for top level JFrame comp.
				glassPanel = (JPanel) getGlassPane();	
				
				glassPanel.setLayout(null);
				
					// WaitPanel set this panel
				WaitPanel waitPanel = new WaitPanel();
				waitPanel.setBounds(72, 202, 454, 30);
				glassPanel.add(waitPanel);
				WaitPanel waitPanel_1 = new WaitPanel();
				waitPanel_1.setBounds(72, 242, 454, 30);
				glassPanel.add(waitPanel_1);
				glassPanel.setVisible(true);
		
		//////////////
		tabbedPane.setLocation(0, 60);
		contentPane.add(tabbedPane);
		tabbedPane.setSize(586, 425);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));

		final JButton settings = new JButton(ICON_SETTING);
		settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		settings.setBackground(Color.LIGHT_GRAY);
		settings.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("icon_settings.gif")));
		settings.setPreferredSize(new Dimension(30, 3));
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SettingsWindow();
			}
		});
		settings.setFont(new Font("Dialog", Font.BOLD, 11));
		settings.setBounds(542, 12, 36, 35);
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
	public static synchronized Component addPanelToGlassPanel(String text) {
		
		for (Component iterable_element : glassPanel.getComponents()) {
			if (!iterable_element.isVisible()) {
				WaitPanel waitPanel = (WaitPanel) iterable_element;
				waitPanel.setText(text);
				waitPanel.setVisible(true);
				return (Component)waitPanel;
			}
		}
		return null;
	}
	public static synchronized void hideGlassPanel(Component panel) {
		for (Component iterable_element : glassPanel.getComponents()) {
			if (panel == iterable_element) {
				panel.setVisible(false);
			}
		}
	}
	public static MainRunWindow getInstance() {
		if (MAIN_RUN_WINDOW == null) {
			MAIN_RUN_WINDOW = new MainRunWindow();
			return MAIN_RUN_WINDOW;
		} else {
			return MAIN_RUN_WINDOW;
		}
	}
	public static JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
