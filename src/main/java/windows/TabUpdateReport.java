package windows;

import javax.swing.JPanel;

import database.ReportRelatedData;
import database.model.CategoryAndCode;
import exception.ConfirmException;
import exception.InfoException;
import files_repository.FilesRepository;
import log.LOg;

import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;

import util.DialogWindows;
import util.MyHoverButton;
import util.ReadXML;
import util.Verification;
import war.WarArchive;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import javax.swing.JToggleButton;

public class TabUpdateReport extends TabSuperClass {
	private static TabUpdateReport TAB_UPDADE_REPORT = null;
	private static InputNewValuesReport inputNewValuesReport = null;
	private JTextField nameReportField;
	private JComboBox<String> categoriesComboBox;
	private MyHoverButton updateReportButton;
	private JToggleButton updateDataBaseToggleButton;
	private JToggleButton updateFileArchiveToggleButton;
	private MyHoverButton fileReportButton;
	private JLabel categoryLabel;
	private JLabel nameReportLabel;
	private JLabel nameFileReportLabel;
	private JLabel fileReportLabel;
	private JFileChooser fileChooser;
	private MyHoverButton refreshServiceButton;
	private MyHoverButton inputNewValuesButton;
	private JLabel ipDataSrcLabel;
	private JComboBox<String> foldersProjectComboBox;
	private JLabel lblProjectFolderIn;
	//
	/**
	 * Create the panel.
	 */
	private TabUpdateReport() {
		
		setPreferredSize(new Dimension(582, 398));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 0, 504, 398);
		add(panel);
		
		categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		nameReportField = new JTextField();
		nameReportField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportField.setColumns(10);
		
		updateReportButton = new MyHoverButton("Update report");
		
		nameReportLabel = new JLabel("Name report");
		nameReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		categoriesComboBox = new JComboBox<String>();
		categoriesComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		categoriesComboBox.setMaximumRowCount(10);
		categoriesComboBox.setInheritsPopupMenu(true);
		categoriesComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		categoriesComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		fileReportLabel = new JLabel("");
		fileReportLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		fileReportLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		nameFileReportLabel = new JLabel("Name file report");
		nameFileReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		fileReportButton = new MyHoverButton("File ...");
		
		updateDataBaseToggleButton = new JToggleButton();
		updateDataBaseToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateDataBaseToggleButton.setSelected(true);
		updateDataBaseToggleButton.setBackground(Color.LIGHT_GRAY);
		
		updateFileArchiveToggleButton = new JToggleButton();
		updateFileArchiveToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateFileArchiveToggleButton.setSelected(true);
		updateFileArchiveToggleButton.setBackground(Color.LIGHT_GRAY);
		
		refreshServiceButton = new MyHoverButton("Refresh service");
		
		inputNewValuesButton = new MyHoverButton("Input new values...");
		inputNewValuesButton.setEmptyHover();
		
		ipDataSrcLabel = new JLabel();
		ipDataSrcLabel.setForeground(Color.GRAY);
		ipDataSrcLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		ipDataSrcLabel.setBorder(null);
		
		foldersProjectComboBox = new JComboBox<String>();
		foldersProjectComboBox.setMaximumRowCount(10);
		foldersProjectComboBox.setInheritsPopupMenu(true);
		foldersProjectComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		foldersProjectComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		lblProjectFolderIn = new JLabel("Project folder in repositories");
		lblProjectFolderIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(updateFileArchiveToggleButton, 0, 0, Short.MAX_VALUE)
								.addComponent(updateDataBaseToggleButton, GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE))
							.addGap(24)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(updateReportButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
											.addGap(12)
											.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
								.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
								.addComponent(inputNewValuesButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProjectFolderIn, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblProjectFolderIn, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(inputNewValuesButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(updateDataBaseToggleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(24)
							.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(updateFileArchiveToggleButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(updateReportButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		//////////////////Code
		/////////////
		primaryInit();
		//////
	}
	private void primaryInit() {
		updateDataBaseToggleButton.setSelected(false);
		
		if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
			foldersProjectComboBox.setEnabled(true);
		} else {
			foldersProjectComboBox.setEnabled(false);
			foldersProjectComboBox.setSelectedIndex(-1);
		}
		categoryLabel.setEnabled(false);
		categoriesComboBox.setEnabled(false);
		nameReportLabel.setEnabled(false);
		nameReportField.setEnabled(false);
		inputNewValuesButton.setEnabled(false);
		//

		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Birt Report (*.rptdesign)", "rptdesign");
		fileChooser.setFileFilter(filter);
		refreshServiceButton.addActionListener(refreshService);
		
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel(listCategoryAndCodes);
		categoriesComboBox.setModel(model);
		final DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel(listNamesFoldersProject);
		foldersProjectComboBox.setModel(model2);
		
		fileReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileChooser.setDialogTitle("Select file");
					int result = fileChooser.showDialog(MainRunWindow.getInstance(), "Select report");
					if (result == JFileChooser.APPROVE_OPTION) {
						fileReportLabel.setText(fileChooser.getSelectedFile().getName().replace(".rptdesign",""));
						ipDataSrcLabel.setText(ReadXML.getIpDataSource(fileChooser.getSelectedFile()));
					}
				} catch (Exception e2) {
					DialogWindows.dialogWindowError(e2);
						LOg.logToFile(e2);
				}
			}
		});
		inputNewValuesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					matchCheckingInputValues1();
					matchCheckingDataBase();
					String nameReport = nameReportField.getText().trim();
				Integer categoryId = ((CategoryAndCode)categoriesComboBox.getSelectedItem()).getCategoryId();
					if (inputNewValuesReport == null) inputNewValuesReport = new InputNewValuesReport(nameReport, categoryId);
					else inputNewValuesReport.setVisible(true);
					
				} catch (InfoException e1) {
					DialogWindows.dialogWindowError(e1);
						return;
				} catch (Exception e2) {
					DialogWindows.dialogWindowError(e2);
						LOg.logToFile(e2);
							return;
				} finally {setCursor(null);}
			}
		});
		updateReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connectionForCommit = null;
				String nameReport, updateNameFileReport = null, nameProgect = null;
				String[] oldValues = null;
				Integer categoryId = null;
				File selectedFile = null;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					matchCheckingValidInputData();
					if (updateDataBaseToggleButton.isSelected()) {
						nameReport = nameReportField.getText().trim();
						categoryId = ((CategoryAndCode) categoriesComboBox.getSelectedItem()).getCategoryId();
						//
						String newRPT_ID         = inputNewValuesReport.getRPT_ID();
						Integer newCategoryId    = inputNewValuesReport.getCategory();
						String newNameReport     = inputNewValuesReport.getNameReport();
						String newNameFileReport = inputNewValuesReport.getNameFileReport();
							connectionForCommit = ReportRelatedData.updateReport(nameReport, categoryId, newRPT_ID, newCategoryId, newNameReport, newNameFileReport);
								connectionForCommit.commit();
									inputNewValuesReport = null;
										DialogWindows.dialogWindowWarning("Report successfully update!");
					} else if (updateFileArchiveToggleButton.isSelected()) {
						updateNameFileReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
						nameProgect    = (String)foldersProjectComboBox.getSelectedItem();
						selectedFile = fileChooser.getSelectedFile();
						//
						 WarArchive.createBackup(selectedFile);
						 	WarArchive.addOrUpdateReportFileInArchive(selectedFile);
							if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
								nameReport = getNameReportFromBase();
								FilesRepository.sendFilesToStorage(nameReport, nameProgect, selectedFile);
									DialogWindows.dialogWindowWarning("Report file successfully update! For report:\r\n\n"+nameReport);
									return;
							}
						 	DialogWindows.dialogWindowWarning("Report file successfully update!");
					}
				} catch (InfoException ie) {
					 DialogWindows.dialogWindowError(ie); 
				} catch (ConfirmException ce) { 
					//return
				} catch (Exception e1) {
					 DialogWindows.dialogWindowError(e1);
					 	LOg.logToFile(e1);
				} finally {
					 setCursor(null);
						if (connectionForCommit != null) {
							try {
								connectionForCommit.close();
							} catch (SQLException e1) {
								DialogWindows.dialogWindowError(e1);
									LOg.logToFile(e1);
							}
						}
				}	
			}
		});
		updateDataBaseToggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (updateDataBaseToggleButton.isSelected()) {
					updateDataBaseToggleButtonSet(true);
					updateFileArchiveToggleButtonSet(false);
					updateFileArchiveToggleButton.setSelected(false);
				} else {
					updateDataBaseToggleButtonSet(false);
					updateFileArchiveToggleButtonSet(true);
					updateFileArchiveToggleButton.setSelected(true);
				}
				
			}
		});
		//////////////////////////////////////////////////////////////////////
		updateFileArchiveToggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (updateFileArchiveToggleButton.isSelected()) {
					updateFileArchiveToggleButtonSet(true);
					updateDataBaseToggleButtonSet(false);
					updateDataBaseToggleButton.setSelected(false);
				} else {
					updateFileArchiveToggleButtonSet(false);
					updateDataBaseToggleButtonSet(true);
					updateDataBaseToggleButton.setSelected(true);
				}
			}	
		});
	}

	protected void updateFileArchiveToggleButtonSet(boolean b) {
		if (b) {
			if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
				foldersProjectComboBox.setEnabled(true);
			} else {
				foldersProjectComboBox.setEnabled(false);
				foldersProjectComboBox.setSelectedIndex(-1);
			}
			//
			nameFileReportLabel.setEnabled(true);
			fileReportLabel.setEnabled(true);
			fileReportButton.setEnabled(true);
			ipDataSrcLabel.setVisible(true);
		} else {
			foldersProjectComboBox.setEnabled(false);
			foldersProjectComboBox.setSelectedIndex(-1);
			//
			nameFileReportLabel.setEnabled(false);
			fileReportLabel.setEnabled(false);
			fileReportButton.setEnabled(false);
			ipDataSrcLabel.setVisible(false);
		}
	}
	protected void updateDataBaseToggleButtonSet(boolean b) {
		if (b) {
			foldersProjectComboBox.setEnabled(false);
			foldersProjectComboBox.setSelectedIndex(-1);
			//
			categoryLabel.setEnabled(true);
			categoriesComboBox.setEnabled(true);
			nameReportLabel.setEnabled(true);
			nameReportField.setEnabled(true);
			inputNewValuesButton.setEnabled(true);
		} else {
			if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
				foldersProjectComboBox.setEnabled(true);
			} else {
				foldersProjectComboBox.setEnabled(false);
				foldersProjectComboBox.setSelectedIndex(-1);
			}
			
			categoryLabel.setEnabled(false);
			categoriesComboBox.setEnabled(false);
			nameReportLabel.setEnabled(false);
			nameReportField.setEnabled(false);
			inputNewValuesButton.setEnabled(false);
		}
	}
	private void matchCheckingValidInputData() throws Exception {

		if (updateDataBaseToggleButton.isSelected()) {
			matchCheckingInputValues1();
			matchCheckingDataBase();
			matchCheckingInputValues2();
		} else if (updateFileArchiveToggleButton.isSelected()) {
			if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
				matchCheckingProjectComboBox();
				String nameReport = getNameReportFromBase();
				String nameProgect = (String)foldersProjectComboBox.getSelectedItem();
				FilesRepository.isExistFolderReport(nameReport, nameProgect);
			}
			matchCheckingArchive();
		}
	}
	private void matchCheckingProjectComboBox() throws Exception {
		if (FilesRepository.isOpenRepo()) {
			if (foldersProjectComboBox.getSelectedItem() == null) throw new InfoException("Choose a project folder.");
		}
	}
	private String getNameReportFromBase() throws Exception {
		String updateNameFileReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
		Vector<String> reportNames = ReportRelatedData.getListOfReportNames(updateNameFileReport);
		if (reportNames.size() == 1) {
			 return reportNames.get(0);
		} else {
			String s = "";
			for (String name : reportNames) {
				s += name + "\n"; 
			}
			throw new InfoException("There is more than one report for this file.\n" + s);
		}
	}
	private void matchCheckingInputValues1() throws Exception {
		if (categoriesComboBox.getSelectedItem() == null) throw new InfoException("Choose a category.");
		String nameReport = nameReportField.getText().trim();
		if (nameReport.isEmpty()) throw new InfoException("Field name report is empty.");
	}
	private void matchCheckingInputValues2() throws Exception {
		if (inputNewValuesReport == null) throw new InfoException("Change at least one report attribute");
		String newRPT_ID = inputNewValuesReport.getRPT_ID();
		Integer newCategoryId = inputNewValuesReport.getCategory();
		String newNameReport = inputNewValuesReport.getNameReport();
		String newNameFileReport = inputNewValuesReport.getNameFileReport();
			if (newRPT_ID ==null && newCategoryId ==null && newNameReport ==null && newNameFileReport ==null) throw new InfoException("Change at least one report attribute");
		//
		if  (newNameReport ==null)	
		Verification.checkIvalidFilenamesWindows(newNameReport,newNameFileReport);
	}
	private void matchCheckingNameFile() throws Exception {
		String nameReport = nameReportField.getText().trim();
		int categoryId = ((CategoryAndCode) categoriesComboBox.getSelectedItem()).getCategoryId();
		String updateNameFileReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
		
		if (inputNewValuesReport.getNameFileReport() != null) {
			if (!inputNewValuesReport.getNameFileReport().equals(updateNameFileReport+".rptdesign")) throw new InfoException("The file name of this report does not match the update file");
		} else {
			String pathString = ReportRelatedData.getReportFilePath(nameReport, categoryId);
			String nameFileReport = pathString.replace("/frameset?__report=report/", "");
				if (!updateNameFileReport.equals(nameFileReport)) throw new InfoException("The file name of this report does not match the update file");
		}
	}
	private void matchCheckingDataBase() throws Exception {
		String nameReport = nameReportField.getText().trim();
		int categoryId = ((CategoryAndCode) categoriesComboBox.getSelectedItem()).getCategoryId();
		Vector<String> listReportStrings = ReportRelatedData.getListOfReportNames(categoryId);
			boolean b = false;
			for (String repNameExists : listReportStrings) {
				if (nameReport.equals(repNameExists.trim())) b = true;
			}
			if (!b) throw new InfoException("A report with the this name \""+nameReport+"\" absent.");
	}
	private void matchCheckingArchive() throws Exception {
		WarArchive.checkPathArchive();
		//
		if (fileChooser.getSelectedFile() == null) throw new InfoException("Select report file.");
	
		if (!fileChooser.getSelectedFile().exists()) throw new InfoException("File not found.");
		
		String updateFileNameReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
		Vector<String> listFileNameReport = WarArchive.getListOfReportFilesNames();
		boolean b = false;
		for (String fileNameReportExists : listFileNameReport) {
				if (updateFileNameReport.equals(fileNameReportExists)) b = true;
			}
		if(!b) throw new InfoException("A file report with the this name \""+updateFileNameReport+"\" absent.");
	}
	private void restoreBaseBeforeUpdate(String newRPT_ID, Integer newCategoryId, String[] oldValues) throws NumberFormatException, Exception {
		if (newRPT_ID != null && newCategoryId != null) ReportRelatedData.updateReport(newRPT_ID, newCategoryId, oldValues[0],Integer.valueOf(oldValues[1]), oldValues[2], oldValues[3]); 
		else if (newRPT_ID != null) ReportRelatedData.updateReport(newRPT_ID, Integer.valueOf(oldValues[1]), oldValues[0],null, oldValues[2], oldValues[3]);
		else if (newCategoryId != null) ReportRelatedData.updateReport(oldValues[0], newCategoryId, null,Integer.valueOf(oldValues[1]), oldValues[2], oldValues[3]);
		else ReportRelatedData.updateReport(oldValues[0], newCategoryId, null,Integer.valueOf(oldValues[1]), oldValues[2], oldValues[3]);
	}
	public static TabUpdateReport getInstance() {
		if (TAB_UPDADE_REPORT == null) {
			TAB_UPDADE_REPORT = new TabUpdateReport();
			return TAB_UPDADE_REPORT;
		} else {
			return TAB_UPDADE_REPORT;
		}
	}
	public MyHoverButton getInputNewValuesButton() {
		return inputNewValuesButton;
	}
	public JComboBox<String> getFoldersProjectComboBox() {
		return foldersProjectComboBox;
	}
	public JToggleButton getUpdateDataBaseToggleButton() {
		return updateDataBaseToggleButton;
	}
	public JToggleButton getUpdateFileArchiveToggleButton() {
		return updateFileArchiveToggleButton;
	}
}
