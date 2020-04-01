package windows;

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

import util.DialogWindows;
import util.MyHoverButton;
import util.ReadXML;

import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.CategoryAndCode;
import exception.InfoException;
import files_repository.FilesRepository;
import log.LOg;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.awt.event.ActionEvent;

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
	private MyHoverButton fileReportButton;
	private MyHoverButton createToRepositoriesButton;
	private JFileChooser fileChooser;
	private MyHoverButton updateToRepositoriesButton;

	/**
	 * Create the panel.
	 */
	private TabRepositories() {
		setBounds(new Rectangle(0, 0, 500, 250));
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);
	
		add(panel);
		
		panel.setBounds(55, 0, 481, 288);
		
		foldersProjectComboBox = new JComboBox<String>();
		foldersProjectComboBox.setMaximumRowCount(10);
		foldersProjectComboBox.setInheritsPopupMenu(true);
		foldersProjectComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		foldersProjectComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JLabel lblNewLabel = new JLabel("Project folder in repositories");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		fileReportLabel = new JLabel("");
		fileReportLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		fileReportLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel nameFileReportLabel = new JLabel("Name file report");
		nameFileReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		fileReportButton = new MyHoverButton("File ...");
		
		nameReportField = new JTextField();
		nameReportField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportField.setColumns(10);
		
		JLabel nameReportLabel = new JLabel("Name report");
		nameReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		createToRepositoriesButton = new MyHoverButton("File ...");
		
		createToRepositoriesButton.setText("Create to Repo...");
		
		updateToRepositoriesButton = new MyHoverButton("File ...");
		
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Birt Report (*.rptdesign)", "rptdesign");
		fileChooser.setFileFilter(filter);
		
		fileReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileChooser.setDialogTitle("Select file");
					int result = fileChooser.showDialog(MainRunWindow.getInstance(), "Select report");
					if (result == JFileChooser.APPROVE_OPTION) {
						fileReportLabel.setText(fileChooser.getSelectedFile().getName().replace(".rptdesign",""));
					}
				} catch (Exception e2) {
					DialogWindows.dialogWindowError(e2);
						LOg.logToFile(e2);
				}
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
						FilesRepository.isNotExistFolderReport(nameReport, nameProgect);
					//
					FilesRepository.sendFilesToStorage(nameReport, nameProgect, selectedFile);
						foldersProjectComboBox.setSelectedIndex(-1);
						DialogWindows.dialogWindowWarning("Report files successfully added in repositories");
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
						FilesRepository.isExistFolderReport(nameReport, nameProgect);
					//
					FilesRepository.sendFilesToStorage(nameReport, nameProgect, selectedFile);
						foldersProjectComboBox.setSelectedIndex(-1);
						DialogWindows.dialogWindowWarning("Report files successfully updated in repositories");
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
	private void matchCheckingValidInputData() throws Exception {
		String nameReport = nameReportField.getText().trim();
		if (FilesRepository.isOpenRepo()) {
			if (foldersProjectComboBox.getSelectedItem() == null) throw new InfoException("Choose a project folder.");
		}
		if (nameReport.isEmpty()) throw new InfoException("Field name report is empty.");
		
		if (fileChooser.getSelectedFile() == null) throw new InfoException("Select report file.");
			if (!fileChooser.getSelectedFile().exists()) throw new InfoException("File not found.");
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
