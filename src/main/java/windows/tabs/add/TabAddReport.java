package windows.tabs.add;

import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.DateTime;
import org.xml.sax.SAXException;

import database.ConnectionMSSQL;
import database.ParamsRelatedData;
import database.ReportRelatedData;
import exception.ConfirmException;
import exception.InfoException;
import files_repository.FilesRepository;
import log.LOg;
import parce_rptdesign.ParamFromRptDesign;
import parce_rptdesign.ReadXML;
import util.CategoryAndId;
import util.DialogWindows;
import util.ListCellRendererCategory;
import util.MyProperties;
import util.Util;
import util.my_components.MyField;
import util.my_components.MyHoverButton;
import util.my_components.MyHoverToggleButton;
import war.WarArchive;
import windows.MainRunWindow;
import windows.SettingsWindow;
import windows.param.ParamsPanel;
import windows.param.ParamFromParamsPanel;
import windows.tabs.TabSuperClass;

import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
	
	private MyField nameReportField;
	private MyField RPT_IDField;
	private MyField nameReportFileField;
	
	private JFileChooser fileChooser;
	private JLabel fileReportLabel;
	private JComboBox<CategoryAndId> categoriesComboBox;
	private JButton fileReportButton;
	private JButton addReportButton;
	private JButton refreshServiceButton;
	private MyHoverToggleButton addDataBaseToggleButton;
	private MyHoverToggleButton addArchiveToggleButton;
	private JLabel categoryLabel;
	private JLabel nameReportLabel;
	private JLabel nameFileReportLabel;
	private JLabel nameFileReportLabelTF;
	private JLabel ipDataSrcLabel;
	private JLabel rptIdLabel;
	private JCheckBox autoInsertCheckBox;
	private JComboBox<String> foldersProjectComboBox;
	private JLabel lblNewLabel;
	private MyHoverButton newParamButton;

	protected ParamsPanelAdd newParam;

	protected ComponentAdapter adapterNewParams;

	/**
	 * Create the panel.
	 */
	private TabAddReport() {
			
		setBackground(UIManager.getColor("Button.background"));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		//setPreferredSize(new Dimension(520, 390));
		setLayout(null);
		JPanel addReportPanel = new JPanel();
		addReportPanel.setBounds(0, 0, 560, 418);
		add(addReportPanel);
		setPreferredSize(new Dimension(560, 418));

		nameReportField = new MyField("name report");
		nameReportField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportField.setColumns(10);

		categoriesComboBox = new JComboBox<CategoryAndId>(listCategoryAndCodes);
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
		fileReportButton.setFont(new Font("Dialog", Font.BOLD, 12));
		fileReportLabel = new JLabel("");
		fileReportLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		fileReportLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		addReportButton = new MyHoverButton("Add report");
		addReportButton.setFont(new Font("Dialog", Font.BOLD, 12));

		refreshServiceButton = new MyHoverButton("Refresh service");
		refreshServiceButton.setFont(new Font("Dialog", Font.BOLD, 12));
		nameFileReportLabel = new JLabel("Name file report");
		nameFileReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		addDataBaseToggleButton = new MyHoverToggleButton("");
		addDataBaseToggleButton.setSelected(true);
		addArchiveToggleButton = new MyHoverToggleButton("");
		addDataBaseToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addArchiveToggleButton.setSelected(true);
		addArchiveToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		nameFileReportLabelTF = new JLabel("Name file report");
		nameFileReportLabelTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		nameReportFileField = new MyField("neme report file");
		nameReportFileField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportFileField.setColumns(10);
		
		ipDataSrcLabel = new JLabel();
		ipDataSrcLabel.setBorder(null);
		ipDataSrcLabel.setForeground(Color.GRAY);
		ipDataSrcLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		
		rptIdLabel = new JLabel("RPT_ID");
		rptIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		RPT_IDField = new MyField("RPT_ID");
		RPT_IDField.setText("Auto");
	
		RPT_IDField.setHorizontalAlignment(SwingConstants.CENTER);
		RPT_IDField.setFont(new Font("Dialog", Font.PLAIN, 14));
		RPT_IDField.setColumns(10);
		
		autoInsertCheckBox = new JCheckBox("Auto insert");
		autoInsertCheckBox.setSelected(true);
		autoInsertCheckBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		foldersProjectComboBox = new JComboBox<String>();
		foldersProjectComboBox.setMaximumRowCount(10);
		foldersProjectComboBox.setInheritsPopupMenu(true);
		foldersProjectComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		foldersProjectComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		lblNewLabel = new JLabel("Project folder in repositories");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		newParamButton = new MyHoverButton("New Param");
		newParamButton.setFont(new Font("Dialog", Font.BOLD, 12));
		newParamButton.setEmptyHover();
	
		
		GroupLayout gl_addReportPanel = new GroupLayout(addReportPanel);
		gl_addReportPanel.setHorizontalGroup(
			gl_addReportPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addReportPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_addReportPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
							.addGap(212))
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_addReportPanel.createSequentialGroup()
									.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(addArchiveToggleButton, 0, 0, Short.MAX_VALUE)
										.addComponent(addDataBaseToggleButton, GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE))
									.addGap(26)
									.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(nameFileReportLabelTF, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(rptIdLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
													.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
													.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_addReportPanel.createParallelGroup(Alignment.TRAILING)
															.addGroup(gl_addReportPanel.createSequentialGroup()
																.addComponent(fileReportLabel, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
																.addGap(6)
																.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
															.addGroup(gl_addReportPanel.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
																.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED))
															.addGroup(gl_addReportPanel.createSequentialGroup()
																.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
																.addComponent(addReportButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
															.addGroup(gl_addReportPanel.createSequentialGroup()
																.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
																	.addComponent(nameReportLabel)
																	.addComponent(RPT_IDField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(autoInsertCheckBox)
																.addPreferredGap(ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
																.addComponent(newParamButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
															.addComponent(nameReportField, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
															.addComponent(nameReportFileField, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))))))))
								.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE))
							.addGap(27)))
					.addGap(8))
		);
		gl_addReportPanel.setVerticalGroup(
			gl_addReportPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addReportPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addReportPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addComponent(categoryLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_addReportPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_addReportPanel.createSequentialGroup()
									.addComponent(rptIdLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_addReportPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(RPT_IDField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addComponent(autoInsertCheckBox))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(nameReportLabel))
								.addGroup(gl_addReportPanel.createSequentialGroup()
									.addComponent(newParamButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(8)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameFileReportLabelTF, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(nameReportFileField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(addDataBaseToggleButton, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_addReportPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_addReportPanel.createSequentialGroup()
									.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(addArchiveToggleButton, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)))
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_addReportPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
							.addComponent(addReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_addReportPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addGap(8)
					.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(9))
		);
		addReportPanel.setLayout(gl_addReportPanel);
		//////////////////Code
		/////////////
		primaryInit();
		//////
	}
	private void primaryInit() {
		
		nameReportFileField.setEnabled(false);
		nameReportFileField.setEditable(false);
		nameFileReportLabelTF.setEnabled(false);
		newParamButton.setEnabled(false);
		newParamButton.setVisible(false);
		RPT_IDField.setEnabled(false);
		RPT_IDField.setEditable(false);
		
		if (!SettingsWindow.enableAddToRepositoriesGetSaveSelected()) foldersProjectComboBox.setEnabled(false);
		
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Birt Report (*.rptdesign)", "rptdesign");
		fileChooser.setFileFilter(filter);
		refreshServiceButton.addActionListener(refreshService);
		
		//final DefaultComboBoxModel<CategoryAndCode> model = new DefaultComboBoxModel(listCategoryAndCodes);
		//categoriesComboBox.setModel(model);
		final ListCellRendererCategory listCellRendererCategory = new ListCellRendererCategory(); 
		categoriesComboBox.setRenderer(listCellRendererCategory);
		
		
		
		final DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel(listNamesFoldersProject);
		foldersProjectComboBox.setModel(model2);
		
		RPT_IDField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (RPT_IDField.getText().length() >= 8) // limit textfield to 8 characters
		            e.consume(); 
			}
		});
		 adapterNewParams = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					if(!ConnectionMSSQL.isGoodLastsConnection) return;
					if(!ParamsRelatedData.isExistTableParams()) newParamButton.setVisible(false);
					else newParamButton.setVisible(true);
					
				} catch (ClassNotFoundException | SQLException e1) {
					DialogWindows.dialogWindowError(e1);
						LOg.logToFile(e1);
				}
			}
		};
		fileReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileChooser.setDialogTitle("Select file");
					int result = fileChooser.showDialog(MainRunWindow.getInstance(), "Select report");
					if (result == JFileChooser.APPROVE_OPTION) {
						fileReportLabel.setText(fileChooser.getSelectedFile().getName().replace(".rptdesign",""));
						ipDataSrcLabel.setText(ReadXML.getIpDataSource(fileChooser.getSelectedFile()));
							Util.changeColorErrDataSource(ReadXML.getIpDataSource(fileChooser.getSelectedFile()), ipDataSrcLabel);
					}
				} catch (Exception e2) {
					DialogWindows.dialogWindowError(e2);
						LOg.logToFile(e2);
				}
			}
		});
		newParamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (newParam == null) newParam = new ParamsPanelAdd();
				else newParam.setVisible(true);
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
					foldersProjectComboBox.setEnabled(false);
						foldersProjectComboBox.setSelectedIndex(-1);
					categoriesComboBox.setEnabled(false);
					nameReportField.setEditable(false);
					nameReportField.setEnabled(false);
					categoryLabel.setEnabled(false);
					nameReportLabel.setEnabled(false);
					nameReportFileField.setEnabled(false);
					nameReportFileField.setEditable(false);
					nameFileReportLabelTF.setEnabled(false);
					newParamButton.setEnabled(false);
		
					autoInsertCheckBox.setEnabled(false);
					rptIdLabel.setEnabled(false);
					RPT_IDField.setEnabled(false);
					RPT_IDField.setEditable(false);
				} else {
					if (addArchiveToggleButton.isSelected()) {
						if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) foldersProjectComboBox.setEnabled(true);
					} else {
						foldersProjectComboBox.setEnabled(false);
						foldersProjectComboBox.setSelectedIndex(-1);
					}
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
						nameReportFileField.setEnabled(true);
						nameReportFileField.setEditable(true);
						nameFileReportLabelTF.setEnabled(true);
						newParamButton.setEnabled(true);
					}
				}
			}
		});
		addArchiveToggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!addArchiveToggleButton.isSelected()) {
					foldersProjectComboBox.setEnabled(false);
						foldersProjectComboBox.setSelectedIndex(-1);
					fileReportButton.setEnabled(false);
					nameFileReportLabel.setEnabled(false);
					fileReportLabel.setEnabled(false);
					newParamButton.setEnabled(false);
					ipDataSrcLabel.setVisible(false);
					if (addDataBaseToggleButton.isSelected()) {
						nameReportFileField.setEnabled(true);
						nameReportFileField.setEditable(true);
						nameFileReportLabelTF.setEnabled(true);
						newParamButton.setEnabled(true);
					}
				} else {
					if (addDataBaseToggleButton.isSelected()) {
						if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) foldersProjectComboBox.setEnabled(true);
					} else {
						foldersProjectComboBox.setEnabled(false);
						foldersProjectComboBox.setSelectedIndex(-1);
					}
					fileReportButton.setEnabled(true);
					nameFileReportLabel.setEnabled(true);
					fileReportLabel.setEnabled(true);
					ipDataSrcLabel.setVisible(true);
					if (addDataBaseToggleButton.isSelected()) {
						nameReportFileField.setEnabled(false);
						nameReportFileField.setEditable(false);
						nameFileReportLabelTF.setEnabled(false);
						newParamButton.setEnabled(false);
					}
					
				}
			}
		});
		
	
		addReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String RPT_ID , nameReport = null , nameFileReport = null , nameProgect = null;
				Integer categoryId = null;
				File selectedFile = null;
				boolean isExistTableParams = false;
				List<ParamFromRptDesign> paramsFromDesign = null;
				List<ParamFromParamsPanel> paramsFromPanel = null;
				
				String autoRPT_ID = new DateTime().toString("ddHHmmss");
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					matchCheckingValidInputData();
					isExistTableParams = ParamsRelatedData.isExistTableParams();
					if(addDataBaseToggleButton.isSelected() && addArchiveToggleButton.isSelected()) {
						//
						RPT_ID         = RPT_IDField.getTextWithCheck();
						nameReport     = nameReportField.getTextWithCheck();
						categoryId     = ((CategoryAndId) categoriesComboBox.getSelectedItem()).getCategoryId();
						selectedFile   = fileChooser.getSelectedFile();
						nameFileReport = selectedFile.toPath().getFileName().toString();
						nameProgect    = (String)foldersProjectComboBox.getSelectedItem();
						paramsFromDesign = ReadXML.getListOfParamsFromRptDesign(selectedFile);
						
						if (autoInsertCheckBox.isSelected()) {
							ReportRelatedData.insertReport(autoRPT_ID, nameReport, categoryId, nameFileReport);
							if (isExistTableParams) ParamsRelatedData.insertParam(paramsFromDesign, autoRPT_ID);
						} else {
							ReportRelatedData.insertReport(RPT_ID, nameReport, categoryId, nameFileReport);
							if (isExistTableParams) ParamsRelatedData.insertParam(paramsFromDesign, RPT_ID);
						}
						
						WarArchive.createBackup(selectedFile);
							WarArchive.addOrUpdateReportFileInArchive(selectedFile);
							if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
								FilesRepository.sendFilesToStorage(nameReport, nameProgect, selectedFile);
							}
							if (newParam != null) {
								newParam = null;
								newParamButton.setEmptyHover();
							}
								DialogWindows.dialogWindowWarning("Report successfully added!");
					} else if (addDataBaseToggleButton.isSelected()) {
						//
						RPT_ID         = RPT_IDField.getTextWithCheck();
						nameReport     = nameReportField.getTextWithCheck();
						nameFileReport = nameReportFileField.getTextWithCheck();
						categoryId     = ((CategoryAndId) categoriesComboBox.getSelectedItem()).getCategoryId();
						if (newParam != null) paramsFromPanel = newParam.getSettingParamsPanel().getlistOfParams();
					
						//
						if (autoInsertCheckBox.isSelected()) {
							ReportRelatedData.insertReport(autoRPT_ID, nameReport, categoryId, nameFileReport);
							if (isExistTableParams && newParam != null) {
								ParamsRelatedData.insertParam(paramsFromPanel, autoRPT_ID);
							}
						
						} else {
							ReportRelatedData.insertReport(RPT_ID, nameReport, categoryId, nameFileReport);
							if (isExistTableParams && newParam != null) ParamsRelatedData.insertParam(paramsFromPanel, RPT_ID);
						}
						if (newParam != null) {
							newParam = null;
							newParamButton.setEmptyHover();
						}
								DialogWindows.dialogWindowWarning("Report successfully added!");
					} else if (addArchiveToggleButton.isSelected()) {
						selectedFile = fileChooser.getSelectedFile();
						nameFileReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
				
						 WarArchive.createBackup(selectedFile);
						 	WarArchive.addOrUpdateReportFileInArchive(selectedFile);
						 		DialogWindows.dialogWindowWarning("Report file successfully added!");
						 		
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
				}	
			}	
		});
	}

	private void matchCheckingValidInputData() throws Exception {
		if (addDataBaseToggleButton.isSelected() && addArchiveToggleButton.isSelected()) {
			if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
				matchCheckingProjectComboBox();
				String nameReport = nameReportField.getText().trim();
				String nameProgect = (String)foldersProjectComboBox.getSelectedItem();
					FilesRepository.isNotExistFolderReport(nameReport, nameProgect);
			}
			if (categoriesComboBox.getSelectedItem() == null) throw new InfoException("Choose a category.");
			matchCheckingDataBase();
				matchCheckingArchive();
		} else if (addDataBaseToggleButton.isSelected()) {
			if (categoriesComboBox.getSelectedItem() == null) throw new InfoException("Choose a category.");
			matchCheckingInputValueFileName();
				matchCheckingDataBase();
		} else if (addArchiveToggleButton.isSelected()) {
			matchCheckingArchive();
		}
	}
	private void matchCheckingInputValueFileName() throws Exception {
		String newFileNameReport = nameReportFileField.getTextWithCheck();
		//
		Vector<String> listFileNameReport = null;
		try {
			 listFileNameReport = WarArchive.getListOfReportFilesNames();
		} catch (InfoException ie) {
			return;
		}
		for (String fileNameReportExists : listFileNameReport) {
			if (newFileNameReport.equals(fileNameReportExists.replace(".rptdesign",""))) {
				int d = DialogWindows.dialogWindowConfirm("A file report with the same name already exists.\n Continue?");
				if (d == 0) return;
				else throw new ConfirmException();
			}
		}
	}
	private void matchCheckingProjectComboBox() throws Exception {
		if (FilesRepository.isOpenRepo()) {
			if (foldersProjectComboBox.getSelectedItem() == null) throw new InfoException("Choose a project folder.");
		}
	}

	private void matchCheckingDataBase() throws Exception {
		String nameNewReport = nameReportField.getTextWithCheck();
		int categoryId = ((CategoryAndId) categoriesComboBox.getSelectedItem()).getCategoryId();
		Vector<String> listReportStrings;
		listReportStrings = ReportRelatedData.getListOfReportNamesAndTranslation(categoryId);
		for (String repNameExists : listReportStrings) {
			if (nameNewReport.equals(repNameExists.trim())) throw new InfoException("A report with the same name already exists.");
		}			
	}
	private void matchCheckingArchive() throws Exception {
		WarArchive.checkPathArchive();
		if (fileChooser.getSelectedFile() == null) throw new InfoException("Select report file.");
			if (!fileChooser.getSelectedFile().exists()) throw new InfoException("File not found.");
		
		String newFileNameReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
		Vector<String> listFileNameReport = WarArchive.getListOfReportFilesNames();
		for (String fileNameReportExists : listFileNameReport) {
			if (newFileNameReport.equals(fileNameReportExists)) throw new InfoException("A file report with the same name already exists.");
		}
		Util.checkWarPathAndReportParh(fileChooser.getSelectedFile().toPath().getParent());
		
	}
	public static TabAddReport getInstance() {
		if (TAB_ADD_REPORT == null) {
			TAB_ADD_REPORT = new TabAddReport();
			return TAB_ADD_REPORT;
		} else {
			return TAB_ADD_REPORT;
		}
	}
	public JComboBox<String> getFoldersProjectComboBox() {
		return foldersProjectComboBox;
	}
	public JToggleButton getAddDataBaseToggleButton() {
		return addDataBaseToggleButton;
	}
	public JToggleButton getAddArchiveToggleButton() {
		return addArchiveToggleButton;
	}
	public MyHoverButton getNewParamButton() {
		return newParamButton;
	}
	public ComponentAdapter getAdapterNewParams() {
		return adapterNewParams;
	}
}
