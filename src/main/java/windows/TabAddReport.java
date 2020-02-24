package windows;

import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import database.CategoryAndCode;
import database.ReportRelatedData;
import exception.ConfirmException;
import exception.InfoException;
import log.LOg;
import util.DialogWindows;
import util.MyHoverButton;
import util.ReadXML;
import war.WarArchive;

import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToggleButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.CardLayout;

public class TabAddReport extends TabSuperClass {
	
	private static TabAddReport TAB_ADD_REPORT = null;
	
	private JTextField nameReportField;
	private JFileChooser fileChooser;
	private JLabel fileReportLabel;
	private JComboBox<String> categoriesComboBox;
	private JButton fileReportButton;
	private JButton addReportButton;
	private JButton refreshServiceButton;
	private JToggleButton addDataBaseToggleButton;
	private JToggleButton addArchiveToggleButton;
	private JLabel categoryLabel;
	private JLabel nameReportLabel;
	private JLabel nameFileReportLabel;
	private JLabel nameFileReportLabelTF;
	private JTextField nameReportFileTextField;
	private JLabel ipDataSrcLabel;
	private JLabel rptIdLabel;
	private JTextField RPT_IDField;
	private JCheckBox autoInsertCheckBox;
	private JComboBox<String> folersProjectComboBox;

	/**
	 * Create the panel.
	 */
	private TabAddReport() {

		setBackground(UIManager.getColor("Button.background"));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		//setPreferredSize(new Dimension(520, 390));
		setLayout(null);
		JPanel addReportPanel = new JPanel();
		addReportPanel.setBounds(0, 0, 520, 407);
		add(addReportPanel);
		setPreferredSize(addReportPanel.getSize());

		nameReportField = new JTextField();
		nameReportField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportField.setColumns(10);

		categoriesComboBox = new JComboBox<String>();
		categoriesComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		categoriesComboBox.setInheritsPopupMenu(true);
		categoriesComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		categoriesComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		categoriesComboBox.setMaximumRowCount(10);

		categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		nameReportLabel = new JLabel("Name report");
		nameReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileReportButton = new MyHoverButton("File ...");
		fileReportLabel = new JLabel("");
		fileReportLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		fileReportLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		addReportButton = new MyHoverButton("Add report");
		addReportButton.setFont(new Font("Dialog", Font.BOLD, 12));

		refreshServiceButton = new MyHoverButton("Refresh service");
		nameFileReportLabel = new JLabel("Name file report");
		nameFileReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		addDataBaseToggleButton = new JToggleButton();
		addDataBaseToggleButton.setSelected(true);
		addArchiveToggleButton = new JToggleButton("");
		addDataBaseToggleButton.setBackground(Color.LIGHT_GRAY);
		addDataBaseToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addArchiveToggleButton.setSelected(true);
		addArchiveToggleButton.setBackground(Color.LIGHT_GRAY);
		addArchiveToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		nameFileReportLabelTF = new JLabel("Name file report");
		nameFileReportLabelTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		nameReportFileTextField = new JTextField();
		nameReportFileTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportFileTextField.setColumns(10);
		
		ipDataSrcLabel = new JLabel();
		ipDataSrcLabel.setBorder(null);
		ipDataSrcLabel.setForeground(Color.GRAY);
		ipDataSrcLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		
		rptIdLabel = new JLabel("RPT_ID");
		rptIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		RPT_IDField = new JTextField();
		RPT_IDField.setText("Auto");
	
		RPT_IDField.setHorizontalAlignment(SwingConstants.CENTER);
		RPT_IDField.setFont(new Font("Dialog", Font.PLAIN, 14));
		RPT_IDField.setColumns(10);
		
		autoInsertCheckBox = new JCheckBox("Auto insert");
		autoInsertCheckBox.setSelected(true);
		autoInsertCheckBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		folersProjectComboBox = new JComboBox<String>();
		folersProjectComboBox.setMaximumRowCount(10);
		folersProjectComboBox.setInheritsPopupMenu(true);
		folersProjectComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		folersProjectComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		GroupLayout gl_addReportPanel = new GroupLayout(addReportPanel);
		gl_addReportPanel.setHorizontalGroup(
			gl_addReportPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addReportPanel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(addArchiveToggleButton, 0, 0, Short.MAX_VALUE)
								.addComponent(addDataBaseToggleButton, GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
							.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_addReportPanel.createSequentialGroup()
									.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_addReportPanel.createSequentialGroup()
									.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_addReportPanel.createSequentialGroup()
											.addGroup(gl_addReportPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_addReportPanel.createSequentialGroup()
													.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE))
												.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_addReportPanel.createSequentialGroup()
													.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
													.addComponent(addReportButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
											.addGap(14)))
									.addGap(22))
								.addGroup(gl_addReportPanel.createSequentialGroup()
									.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(rptIdLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
										.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
									.addGap(102))
								.addGroup(gl_addReportPanel.createSequentialGroup()
									.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(nameReportFileTextField, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
										.addComponent(nameFileReportLabelTF, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
										.addComponent(nameReportField, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
										.addGroup(gl_addReportPanel.createSequentialGroup()
											.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(nameReportLabel)
												.addComponent(RPT_IDField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(autoInsertCheckBox)))
									.addGap(38))))
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addComponent(folersProjectComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(149, Short.MAX_VALUE))))
		);
		gl_addReportPanel.setVerticalGroup(
			gl_addReportPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addReportPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(folersProjectComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addReportPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addComponent(categoryLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rptIdLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_addReportPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(RPT_IDField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(autoInsertCheckBox))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(nameReportLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameFileReportLabelTF, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameReportFileTextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(addDataBaseToggleButton, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addGroup(gl_addReportPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addComponent(addArchiveToggleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(addReportButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		addReportPanel.setLayout(gl_addReportPanel);
		//////////////////Code
		/////////////
		primaryInit();
		//////
	}
	private void primaryInit() {
		
		nameReportFileTextField.setEnabled(false);
		nameReportFileTextField.setEditable(false);
		nameFileReportLabelTF.setEnabled(false);
		RPT_IDField.setEnabled(false);
		RPT_IDField.setEditable(false);
		
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Birt Report (*.rptdesign)", "rptdesign");
		fileChooser.setFileFilter(filter);
		refreshServiceButton.addActionListener(refreshService);
		
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel(listCategoryAndCodes);
		categoriesComboBox.setModel(model);
		
		RPT_IDField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (RPT_IDField.getText().length() >= 8) // limit textfield to 8 characters
		            e.consume(); 
			}
		});
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
		autoInsertCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
					if(autoInsertCheckBox.isSelected()) {
						RPT_IDField.setEnabled(false);
						RPT_IDField.setEditable(false);
						RPT_IDField.setText("Auto");
					} else {
						RPT_IDField.setEnabled(true);
						RPT_IDField.setEditable(true);
						RPT_IDField.setText("");
					}
				}
		});
		addDataBaseToggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(!addDataBaseToggleButton.isSelected()) {
					categoriesComboBox.setEnabled(false);
					nameReportField.setEditable(false);
					nameReportField.setEnabled(false);
					categoryLabel.setEnabled(false);
					nameReportLabel.setEnabled(false);
					nameReportFileTextField.setEnabled(false);
					nameReportFileTextField.setEditable(false);
					nameFileReportLabelTF.setEnabled(false);
		
					autoInsertCheckBox.setEnabled(false);
					rptIdLabel.setEnabled(false);
					RPT_IDField.setEnabled(false);
					RPT_IDField.setEditable(false);
				} else {
					autoInsertCheckBox.setEnabled(true);
					rptIdLabel.setEnabled(true);
						if (autoInsertCheckBox.isSelected()) {
							RPT_IDField.setEnabled(false);
							RPT_IDField.setEditable(false);
						} else {
							RPT_IDField.setEnabled(true);
							RPT_IDField.setEditable(true);
						}
					categoriesComboBox.setEnabled(true);	
					nameReportField.setEditable(true);
					nameReportField.setEnabled(true);
					categoryLabel.setEnabled(true);
					nameReportLabel.setEnabled(true);
					if (!addArchiveToggleButton.isSelected()) {
						nameReportFileTextField.setEnabled(true);
						nameReportFileTextField.setEditable(true);
						nameFileReportLabelTF.setEnabled(true);
					}
				}
			}
		});
		addArchiveToggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!addArchiveToggleButton.isSelected()) {
					fileReportButton.setEnabled(false);
					nameFileReportLabel.setEnabled(false);
					fileReportLabel.setEnabled(false);
					ipDataSrcLabel.setVisible(false);
					if (addDataBaseToggleButton.isSelected()) {
						nameReportFileTextField.setEnabled(true);
						nameReportFileTextField.setEditable(true);
						nameFileReportLabelTF.setEnabled(true);
					}
				} else {
					fileReportButton.setEnabled(true);
					nameFileReportLabel.setEnabled(true);
					fileReportLabel.setEnabled(true);
					ipDataSrcLabel.setVisible(true);
					if (addDataBaseToggleButton.isSelected()) {
						nameReportFileTextField.setEnabled(false);
						nameReportFileTextField.setEditable(false);
						nameFileReportLabelTF.setEnabled(false);
					}
					
				}
			}
		});
		addReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connectionForCommit = null;
				String RPT_ID , nameReport = null , nameFileReport = null;
				Integer categoryId = null;
				File selectedFile = null;
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					if(addDataBaseToggleButton.isSelected() && addArchiveToggleButton.isSelected()) {
						matchCheckingDataBase();
						matchCheckingArchive();
						//
						RPT_ID         = RPT_IDField.getText().trim();
						nameReport     = nameReportField.getText().trim();
						categoryId     = ((CategoryAndCode) categoriesComboBox.getSelectedItem()).getCategoryId();
						selectedFile   = fileChooser.getSelectedFile();
						nameFileReport = selectedFile.toPath().getFileName().toString();
						
						if (autoInsertCheckBox.isSelected()) {
							connectionForCommit = ReportRelatedData.insertReport(null, nameReport, categoryId, nameFileReport);
						} else {
							connectionForCommit = ReportRelatedData.insertReport(RPT_ID, nameReport, categoryId, nameFileReport);
						}
						WarArchive.createBackup(selectedFile);
							WarArchive.addOrUpdateReportFileInArchive(selectedFile);
								connectionForCommit.commit();
									DialogWindows.dialogWindowWarning("Report successfully added!");
					} else if (addDataBaseToggleButton.isSelected()) {
						matchCheckingDataBase();
						matchCheckingFileName();
						//
						RPT_ID         = RPT_IDField.getText().trim();
						nameReport     = nameReportField.getText().trim();
						categoryId     = ((CategoryAndCode) categoriesComboBox.getSelectedItem()).getCategoryId();
						nameFileReport = nameReportFileTextField.getText().trim();
						//
						if (autoInsertCheckBox.isSelected()) {
							connectionForCommit = ReportRelatedData.insertReport(null, nameReport, categoryId, nameFileReport);
						} else {
							connectionForCommit = ReportRelatedData.insertReport(RPT_ID, nameReport, categoryId, nameFileReport);
						}
							connectionForCommit.commit();
								DialogWindows.dialogWindowWarning("Report successfully added!");
					} else if (addArchiveToggleButton.isSelected()) {
						matchCheckingArchive();
						//
						selectedFile = fileChooser.getSelectedFile();
						nameFileReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
						Vector<String> reportNames = ReportRelatedData.getListOfReportNames(nameFileReport);
						String names = "";
							for (String name : reportNames) names += name+"\r\n"; 
							
						 WarArchive.createBackup(selectedFile);
						 	WarArchive.addOrUpdateReportFileInArchive(selectedFile);
						 		DialogWindows.dialogWindowWarning("Report file successfully added! For report(s):\r\n\n"+names);
					} else DialogWindows.dialogWindowWarning("No one toggle button is pressed!");
					//
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
	}
	private void matchCheckingFileName() throws Exception {
		String newFileNameReport = nameReportFileTextField.getText().trim();
		if (newFileNameReport.isEmpty()) throw new InfoException("Field file name report is empty.");
		
		Vector<String> listFileNameReport = WarArchive.getListOfReportFilesNames();
		for (String fileNameReportExists : listFileNameReport) {
			if (newFileNameReport.equals(fileNameReportExists.replace(".rptdesign",""))) {
				int d = DialogWindows.dialogWindowConfirm("A file report with the same name already exists.\n Continue?");
				if (d == 0) return;
				else throw new ConfirmException();
			}
		}
	}
	private void matchCheckingDataBase() throws Exception {
		if (categoriesComboBox.getSelectedItem() == null) throw new InfoException("Choose a category.");
		
		String RPT_ID = RPT_IDField.getText().trim();
		if (RPT_ID.isEmpty()) throw new InfoException("Field RPT_ID is empty.");
		
		String nameNewReport = nameReportField.getText().trim();
		if (nameNewReport.isEmpty()) throw new InfoException("Field name report is empty.");
		
		int categoryId = ((CategoryAndCode) categoriesComboBox.getSelectedItem()).getCategoryId();
		Vector<String> listReportStrings;
		listReportStrings = ReportRelatedData.getListOfReportNamesAndTranslation(categoryId);
		for (String repNameExists : listReportStrings) {
			if (nameNewReport.equals(repNameExists.trim())) throw new InfoException("A report with the same name already exists.");
		}			
	}
	private void matchCheckingArchive() throws Exception {
		if (fileChooser.getSelectedFile() == null) throw new InfoException("Select report file.");
			if (!fileChooser.getSelectedFile().exists()) throw new InfoException("File not found.");
		
		String newFileNameReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
		Vector<String> listFileNameReport = WarArchive.getListOfReportFilesNames();
		for (String fileNameReportExists : listFileNameReport) {
			if (newFileNameReport.equals(fileNameReportExists)) throw new InfoException("A file report with the same name already exists.");
		}
	}
	public static TabAddReport getInstance() {
		if (TAB_ADD_REPORT == null) {
			TAB_ADD_REPORT = new TabAddReport();
			return TAB_ADD_REPORT;
		} else {
			return TAB_ADD_REPORT;
		}
	}
}
