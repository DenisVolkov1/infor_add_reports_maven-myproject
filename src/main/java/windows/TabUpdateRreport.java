package windows;

import javax.swing.JPanel;

import database.CategoryAndCode;
import database.ReportRelatedData;
import exception.ConfirmException;
import exception.InfoException;
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
import war.WarArchive;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import javax.swing.JToggleButton;

public class TabUpdateRreport extends TabSuperClass {
	private static TabUpdateRreport TAB_UPDADE_REPORT = null;
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
	//
	/**
	 * Create the panel.
	 */
	private TabUpdateRreport() {
		
		setPreferredSize(new Dimension(520, 340));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(8, 0, 504, 340);
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
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
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
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(updateReportButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
						.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputNewValuesButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
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
					.addGap(19))
		);
		panel.setLayout(gl_panel);
		//////////////////Code
		/////////////
		primaryInit();
		//////
	}
	private void primaryInit() {
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Birt Report (*.rptdesign)", "rptdesign");
		fileChooser.setFileFilter(filter);
		refreshServiceButton.addActionListener(refreshService);
		
		final DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel(listCategoryAndCodes);
		categoriesComboBox.setModel(model2);
		
		fileReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Select file");
				int result = fileChooser.showDialog(MainRunWindow.getInstance(), "Select report");
				if (result == JFileChooser.APPROVE_OPTION) {
					fileReportLabel.setText(fileChooser.getSelectedFile().getName().replace(".rptdesign",""));
					ipDataSrcLabel.setText(ReadXML.getIpDataSource(fileChooser.getSelectedFile()));
				}
			}
		});
		inputNewValuesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					matchCheckingUpdateReport();
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
				String nameReport, updateNameFileReport = null;
				String[] oldValues = null;
				Integer categoryId = null;
				File selectedFile = null;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					if (updateDataBaseToggleButton.isSelected() && updateFileArchiveToggleButton.isSelected()) {
						matchCheckingIsNullValues();
						matchCheckingArchive();
						matchCheckingNameFile();
						//
						nameReport = nameReportField.getText().trim();
						categoryId = ((CategoryAndCode) categoriesComboBox.getSelectedItem()).getCategoryId();
						oldValues = ReportRelatedData.getReportFields(nameReport,categoryId);
						selectedFile = fileChooser.getSelectedFile();
						updateNameFileReport = selectedFile.toPath().getFileName().toString();
						
						String newRPT_ID         = inputNewValuesReport.getRPT_ID();
						Integer newCategoryId    = inputNewValuesReport.getCategory();
						String newNameReport     = inputNewValuesReport.getNameReport();
						String newNameFileReport = inputNewValuesReport.getNameFileReport();	
							connectionForCommit = ReportRelatedData.updateReport(nameReport, categoryId, newRPT_ID, newCategoryId, newNameReport, newNameFileReport);
								 WarArchive.createBackup(selectedFile);
								 	WarArchive.addOrUpdateReportFileInArchive(selectedFile);
								 		connectionForCommit.commit();
								 			DialogWindows.dialogWindowWarning("Report successfully update!");
					} else if (updateDataBaseToggleButton.isSelected()) {
						matchCheckingUpdateReport();
						//
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
						matchCheckingArchive();
						//
						updateNameFileReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
						Vector<String> reportNames = ReportRelatedData.getListOfReportNames(updateNameFileReport);
						String names = "";
							for (String name : reportNames) names += name+"\r\n"; 
							
						selectedFile = fileChooser.getSelectedFile();
						//
						 WarArchive.createBackup(selectedFile);
						 	WarArchive.addOrUpdateReportFileInArchive(selectedFile);
						 		DialogWindows.dialogWindowWarning("Report file successfully update! For report(s):\r\n\n"+names);
					} else {
							DialogWindows.dialogWindowWarning("No one toggle button is pressed!");
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
				if(!updateDataBaseToggleButton.isSelected()) {
					categoryLabel.setEnabled(false);
					categoriesComboBox.setEnabled(false);
					nameReportLabel.setEnabled(false);
					nameReportField.setEnabled(false);
					inputNewValuesButton.setEnabled(false);
				} else {
					categoryLabel.setEnabled(true);
					categoriesComboBox.setEnabled(true);
					nameReportLabel.setEnabled(true);
					nameReportField.setEnabled(true);
					inputNewValuesButton.setEnabled(true);
				}
			}
		});
		updateFileArchiveToggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(!updateFileArchiveToggleButton.isSelected()) {
					nameFileReportLabel.setEnabled(false);
					fileReportLabel.setEnabled(false);
					fileReportButton.setEnabled(false);
					ipDataSrcLabel.setVisible(false);
				} else {
					nameFileReportLabel.setEnabled(true);
					fileReportLabel.setEnabled(true);
					fileReportButton.setEnabled(true);
					ipDataSrcLabel.setVisible(true);
				}
			}
		});
	}
	private void matchCheckingIsNullValues() throws InfoException {
		String newRPT_ID = inputNewValuesReport.getRPT_ID();
		Integer newCategoryId = inputNewValuesReport.getCategory();
		String newNameReport = inputNewValuesReport.getNameReport();
		String newNameFileReport = inputNewValuesReport.getNameFileReport();
			if (newRPT_ID ==null && newCategoryId ==null && newNameReport ==null && newNameFileReport ==null) throw new InfoException("Change at least one report attribute");
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
	private void matchCheckingUpdateReport() throws Exception {
	
		if (categoriesComboBox.getSelectedItem() == null) throw new InfoException("Choose a category.");

		String nameReport = nameReportField.getText().trim();
		if (nameReport.isEmpty()) throw new InfoException("Field name report is empty.");

		int categoryId = ((CategoryAndCode) categoriesComboBox.getSelectedItem()).getCategoryId();
		Vector<String> listReportStrings = ReportRelatedData.getListOfReportNames(categoryId);
			boolean b = false;
			for (String repNameExists : listReportStrings) {
				if (nameReport.equals(repNameExists.trim())) b = true;
			}
			if (!b) throw new InfoException("A report with the this name \""+nameReport+"\" absent.");
	}
	private void matchCheckingArchive() throws Exception {
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
	public static TabUpdateRreport getInstance() {
		if (TAB_UPDADE_REPORT == null) {
			TAB_UPDADE_REPORT = new TabUpdateRreport();
			return TAB_UPDADE_REPORT;
		} else {
			return TAB_UPDADE_REPORT;
		}
	}
	public MyHoverButton getInputNewValuesButton() {
		return inputNewValuesButton;
	}
}
