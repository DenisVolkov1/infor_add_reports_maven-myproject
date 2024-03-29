package windows.tabs;

import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import util.CategoryAndId;
import util.DialogWindows;
import util.DisplayConnectionDelay;
import util.DisplayConnectionDelay.TypeConnection;
import util.my_components.MyHoverButton;
import windows.MainRunWindow;
import windows.SettingsWindow;

import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import exception.InfoException;
import exception.WarningException;
import files_repository.FilesRepository;
import log.LOg;
import parce_rptdesign.ReadXML;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TabRepositories extends TabSuperClass {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static TabRepositories TAB_TO_REPOSITORIES = null;
	private JPanel panel;
	private JTextField nameReportField;
	private JComboBox<String> foldersProjectComboBox;
	private JLabel fileReportLabel;
	private MyHoverButton repConnectButton;
	private MyHoverButton fileReportButton;
	private MyHoverButton createToRepositoriesButton;
	private JFileChooser fileChooser;
	private MyHoverButton updateToRepositoriesButton;
	private final MyHoverButton upButton = new MyHoverButton(Character.toString('\u2191')); 
	private LinkedList<String> listNamereports = new LinkedList<String>();
	private String lastNameProgect;
	private String lastInputPattern;

	/**
	 * Create the panel.
	 */
	private TabRepositories() {
		setBounds(new Rectangle(0, 0, 500, 250));
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);
	
		add(panel);
		
		panel.setBounds(55, 45, 437, 261);
		
		foldersProjectComboBox = new JComboBox<String>();
		foldersProjectComboBox.setMaximumRowCount(10);
		foldersProjectComboBox.setInheritsPopupMenu(true);
		foldersProjectComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		foldersProjectComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JLabel lblNewLabel = new JLabel("Project folder in repositories");
		lblNewLabel.setLocation(0, 10);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		fileReportLabel = new JLabel("");
		fileReportLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		fileReportLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel nameFileReportLabel = new JLabel("Name file");
		nameFileReportLabel.setLocation(0, 137);
		nameFileReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		fileReportButton = new MyHoverButton("File ...");
		fileReportButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		nameReportField = new JTextField();
		nameReportField.setToolTipText("press '\u2191'-up arrow to find on pattern like(sql) '%+(your input)+%'");
		nameReportField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportField.setColumns(10);
		
		JLabel nameReportLabel = new JLabel("Name report");
		nameReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		createToRepositoriesButton = new MyHoverButton("File ...");
		createToRepositoriesButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		createToRepositoriesButton.setText("Create to Repo...");
		
		updateToRepositoriesButton = new MyHoverButton("File ...");
		updateToRepositoriesButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		updateToRepositoriesButton.setText("Update to Repo...");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
						.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(updateToRepositoriesButton, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(createToRepositoriesButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(createToRepositoriesButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(updateToRepositoriesButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		upButton.setToolTipText("find name rep like(sql) '%+(your input in field)+%'");
		upButton.setMargin(new Insets(2, 2, 2, 2));
		upButton.setBounds(497, 141, 25, 26);
		add(upButton);
		
		repConnectButton = new MyHoverButton("\u2191");
		repConnectButton.setText("Connection");

		repConnectButton.setToolTipText("Connection to SMB repositories server. ");
		repConnectButton.setMargin(new Insets(2, 2, 2, 2));
		repConnectButton.setBounds(55, 12, 82, 26);
		add(repConnectButton);
		/////////////Code
		///////
		primaryInit();
		/////

	}
	private void primaryInit() {
		
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel(listNamesFoldersProject);
		foldersProjectComboBox.setModel(model);
		
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Birt Report (*.rptdesign)", "rptdesign");
		FileNameExtensionFilter filterSQL = new FileNameExtensionFilter("SQL file (*.sql)", "sql");
		
		fileChooser.addChoosableFileFilter(filter1);
		fileChooser.addChoosableFileFilter(filterSQL);
		// Connection to report server
		repConnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listNamesFoldersProject.clear();
				
				boolean isSetRepo = SettingsWindow.enableAddToRepositoriesGetSaveSelected();
				if(isSetRepo) {
					new DisplayConnectionDelay(TypeConnection.REPO_CONNECTION,"repPathDir", 370L) {
						
						@Override
						protected Object taskThread() throws Exception {
							refresListNameProjects();//task...
							return null;
						}
					};
				}		
			}
		});
		//FILE...
		fileReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileChooser.setDialogTitle("Select file");
					int result = fileChooser.showDialog(MainRunWindow.getInstance(), "Select file");
					if (result == JFileChooser.APPROVE_OPTION) {
						fileReportLabel.setText(fileChooser.getSelectedFile().getName().replaceFirst("\\.rptdesign$", "").replaceFirst("\\.sql$", ""));
					}
				} catch (Exception e2) {
					DialogWindows.dialogWindowError(e2);
						LOg.logToFile(e2);
				}
			}
		});
		//
		
		//UP LISTENER focus name Report field , click button 'up arrow'. 
		nameReportField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_UP) {
					upListener();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				lastInputPattern = null;
			}
		});
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upListener();
			}
		});
		//CREATE folder report in repositories 
		createToRepositoriesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameReport = null , nameProgect = null;
				File selectedFile = null;
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					nameReport     = nameReportField.getText().trim();
					selectedFile   = fileChooser.getSelectedFile();
					nameProgect    = (String)foldersProjectComboBox.getSelectedItem();
					//
					matchCheckingValidInputData();
						FilesRepository.isNotExistFolderReport(nameReport, nameProgect, selectedFile);
					//
					FilesRepository.sendFilesToStorage(nameReport, nameProgect, selectedFile, FilesRepository.Type.CREATE);
						foldersProjectComboBox.setSelectedIndex(-1);
						DialogWindows.dialogWindowWarning("Report files successfully added in repositories:\n"+listFilesNameSend());
				} catch (InfoException ie) {
					DialogWindows.dialogWindowError(ie);
				} catch (Exception e) {
					DialogWindows.dialogWindowError(e);
					LOg.logToFile(e);
				} finally {
					setCursor(null);
				}
			}	
		});
		//UPDATE folder report in repositories
		updateToRepositoriesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nameReport = null , nameProgect = null;
				File selectedFile = null;
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					nameReport     = nameReportField.getText().trim();
					selectedFile   = fileChooser.getSelectedFile();
					nameProgect    = (String)foldersProjectComboBox.getSelectedItem();
					//
					matchCheckingValidInputData();
						FilesRepository.checkExistFolderReport(nameReport, nameProgect, selectedFile);
					//
					FilesRepository.sendFilesToStorage(nameReport, nameProgect, selectedFile,FilesRepository.Type.UPDATE);
						foldersProjectComboBox.setSelectedIndex(-1);
						DialogWindows.dialogWindowWarning("Report files successfully updated in repositories:\n"+listFilesNameSend());
				} catch (InfoException ie) {
					DialogWindows.dialogWindowError(ie);
				} catch (Exception e) {
					DialogWindows.dialogWindowError(e);
					LOg.logToFile(e);
				} finally {
					setCursor(null);
				}
			}
		});
	}
	private String findNameReportOnPattern(String nameProgect, String inputPattern) throws Exception {
		if ((!nameProgect.equals(lastNameProgect)) || (lastInputPattern == null)) {// if field keyTyped then lastInputPattern = null
			listNamereports.clear();
			listNamereports.addAll(FilesRepository.getNameReportsAndRepdesign(nameProgect));
			lastInputPattern = inputPattern;
			lastNameProgect = nameProgect;
		}
		while(!listNamereports.isEmpty()) {
			String res = listNamereports.pollFirst();
			if (res.matches(".*"+lastInputPattern+".*")) {
				return res.replaceFirst(""+'\u0020'+'\u0020'+'\u0020'+'\u0020'+".+$", "");
			}
		}
		return null;
	}
	private void upListener() {
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			matchCheckingNameRepAndNameProj();
			String inputPattern = nameReportField.getText().trim();
			String nameProgect = (String)foldersProjectComboBox.getSelectedItem();
			String findOnPatternNameRep = findNameReportOnPattern(nameProgect, inputPattern);
			if (findOnPatternNameRep != null) {
				nameReportField.setText(findOnPatternNameRep);
			} else {
				throw new InfoException("Filename on this pattern '"+lastInputPattern+"' is absent.");
			}
			
		} catch (WarningException we) {
			DialogWindows.dialogWindowWarning(we);
		} catch (InfoException ie) {
			DialogWindows.dialogWindowError(ie);
		} catch (Exception e) {
			DialogWindows.dialogWindowError(e);
			LOg.logToFile(e);
		} finally {
			setCursor(null);
		}

		
	}
	private String listFilesNameSend() {
		File selectedFile   = fileChooser.getSelectedFile();
		File[] files = selectedFile.getParentFile().listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().matches(".+\\.(sql|rptdesign)$");
			}
		});
		String res = "";
		for (File f : files) {
			res += f.getName() + "\n";
		}
		return res;
	}
	private void matchCheckingNameRepAndNameProj() throws Exception {
		String nameReport = nameReportField.getText().trim();
		if (foldersProjectComboBox.getSelectedItem() == null) throw new InfoException("Choose a project folder.");
		if (nameReport.isEmpty()) throw new InfoException("Field name report is empty.");
	}
	private void matchCheckingFile() throws Exception {
		if (fileChooser.getSelectedFile() == null) throw new InfoException("Select report file.");
		if (!fileChooser.getSelectedFile().exists()) throw new InfoException("File not found.");
	}
	private void matchCheckingValidInputData() throws Exception {
		matchCheckingNameRepAndNameProj();
		matchCheckingFile();
	}
	public static TabRepositories getInstance() {
		if (TAB_TO_REPOSITORIES == null) {
			TAB_TO_REPOSITORIES = new TabRepositories();
			return TAB_TO_REPOSITORIES;
		} else {
			return TAB_TO_REPOSITORIES;
		}
	}
}
