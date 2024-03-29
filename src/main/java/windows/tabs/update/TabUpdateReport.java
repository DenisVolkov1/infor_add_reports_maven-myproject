package windows.tabs.update;

import javax.swing.JPanel;

import database.ConnectionMSSQL;
import database.ParamsRelatedData;
import database.ReportRelatedData;
import exception.ConfirmException;
import exception.InfoException;
import exception.WarningException;
import files_repository.FilesRepository;
import log.LOg;
import parce_rptdesign.ReadXML;

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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import util.CategoryAndId;
import util.DialogWindows;
import util.DisplayWaitingForWorkingTask;
import util.ListCellRendererCategory;
import util.Util;
import util.my_components.MyField;
import util.my_components.MyHoverButton;
import util.my_components.MyHoverToggleButton;
import war.WarArchive;
import windows.MainRunWindow;
import windows.SettingsWindow;
import windows.param.ParamFromParamsPanel;
import windows.tabs.TabSuperClass;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import javax.swing.JToggleButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Insets;

public class TabUpdateReport extends TabSuperClass {
	private static TabUpdateReport TAB_UPDADE_REPORT = null;
	private static InputNewValuesReport inputNewValuesReport = null;
	
	private MyField nameReportField;
	
	private JComboBox<CategoryAndId> categoriesComboBox;
	private MyHoverButton updateReportButton;
	private MyHoverToggleButton updateDataBaseToggleButton;
	private MyHoverToggleButton updateFileArchiveToggleButton;
	private MyHoverButton fileReportButton;
	private JLabel categoryLabel;
	private JLabel nameReportLabel;
	private JLabel nameFileReportLabel;
	private JLabel fileReportLabel;
	private JFileChooser fileChooser;
	private MyHoverButton refreshServiceButton;
	private MyHoverButton inputNewValuesButton;
	private MyHoverButton paramsButton;
	private JLabel ipDataSrcLabel;
	private JComboBox<String> foldersProjectComboBox;
	private JLabel lblProjectFolderIn;
	private MyHoverButton upButton;
	//
	protected ComponentAdapter adapterParams;
	protected ParamsPanelUpdate params;
	
	private Map<String,Integer> listNamereports = new HashMap<String,Integer>();
	private String lastInputPattern;
	/**
	 * Create the panel.
	 */
	public TabUpdateReport() {
		
		setPreferredSize(new Dimension(582, 398));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 0, 514, 398);
		add(panel);
		
		categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		nameReportField = new MyField("name report");
		nameReportField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportField.setColumns(10);
		
		updateReportButton = new MyHoverButton("Update report");
		updateReportButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		nameReportLabel = new JLabel("Name report");
		nameReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		categoriesComboBox = new JComboBox<CategoryAndId>(listCategoryAndCodes);
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
		fileReportButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		updateDataBaseToggleButton = new MyHoverToggleButton("");
		updateDataBaseToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateDataBaseToggleButton.setSelected(true);
		
		
		updateFileArchiveToggleButton = new MyHoverToggleButton("");
		updateFileArchiveToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateFileArchiveToggleButton.setSelected(true);
		
		
		refreshServiceButton = new MyHoverButton("Refresh service");
		refreshServiceButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		inputNewValuesButton = new MyHoverButton("Input new values...");
		inputNewValuesButton.setFont(new Font("Dialog", Font.BOLD, 12));
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
		
		paramsButton = new MyHoverButton("Params");
		super.paramButton = paramsButton;
		paramsButton.setText("Params");
		paramsButton.setFont(new Font("Dialog", Font.BOLD, 12));
		paramsButton.setEmptyHover();
		
		upButton = new MyHoverButton("\u2191");	
		upButton.setToolTipText("find name rep like(sql) '%+(your input in field)+%'");
		upButton.setMargin(new Insets(2, 2, 2, 2));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(updateFileArchiveToggleButton, 0, 0, Short.MAX_VALUE)
								.addComponent(updateDataBaseToggleButton, GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(updateReportButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
										.addComponent(nameFileReportLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(paramsButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(inputNewValuesButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
								.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(upButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
						.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProjectFolderIn, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblProjectFolderIn, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(updateDataBaseToggleButton, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(upButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
							.addGap(36)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(inputNewValuesButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(paramsButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(23)
							.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(updateFileArchiveToggleButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(updateReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
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
		paramButton.setEnabled(false);
		paramButton.setVisible(false);
		
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
		upButton.setEnabled(false);
		//

		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Birt Report (*.rptdesign)", "rptdesign");
		fileChooser.setFileFilter(filter);
		refreshServiceButton.addActionListener(refreshService);
		
		//final DefaultComboBoxModel<String> model = new DefaultComboBoxModel(listCategoryAndCodes);
		//categoriesComboBox.setModel(model);
		final ListCellRendererCategory cellRendererCategory = new ListCellRendererCategory();
		categoriesComboBox.setRenderer(cellRendererCategory);
		
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
						ipDataSrcLabel.setToolTipText(ReadXML.getIpDataSource(fileChooser.getSelectedFile()));
						Util.changeColorErrDataSource(ReadXML.getIpDataSource(fileChooser.getSelectedFile()), ipDataSrcLabel);
					}
				} catch (Exception e2) {
					DialogWindows.dialogWindowError(e2);
						LOg.logToFile(e2);
				}
			}
		});
		
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upListener();
			}
		});
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
		//if change this field refresh params and input object	
		nameReportField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (params != null) params = null;
				if (inputNewValuesReport != null) inputNewValuesReport = null;
			}
		});
		//if change category refresh params and input object	
		categoriesComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (params != null) params = null;
				if (inputNewValuesReport != null) inputNewValuesReport = null;
			}
		});
		paramButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {	
					if (categoriesComboBox.getSelectedItem() == null) throw new InfoException("Choose a category.");
					matchCheckingDataBase();
					
					String nameReport = nameReportField.getTextWithCheck();
					Integer categoryId = ((CategoryAndId)categoriesComboBox.getSelectedItem()).getCategoryId();
					String RPT_ID = ReportRelatedData.getRPT_ID(nameReport, categoryId);
					//
					if (params != null) {
						String previousRPT_ID = params.getRPT_IDInput();
						if (!previousRPT_ID.equals(RPT_ID)) params = new ParamsPanelUpdate(RPT_ID);
						else params.setVisible(true);
					}
					else params = new ParamsPanelUpdate(RPT_ID);
					
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
		
		inputNewValuesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					if (categoriesComboBox.getSelectedItem() == null) throw new InfoException("Choose a category.");
					matchCheckingDataBase();
					//
					String nameReport = nameReportField.getTextWithCheck();
					Integer categoryId = ((CategoryAndId)categoriesComboBox.getSelectedItem()).getCategoryId();
					//
					if (inputNewValuesReport != null) {
						String prevNameRep = inputNewValuesReport.getPreviousNameReport();
						Integer prevCatId = inputNewValuesReport.getPreviousCategoryId();
						if (prevNameRep.equals(nameReport) && prevCatId.equals(categoryId)) {
							inputNewValuesReport.setVisible(true);
						} else inputNewValuesReport = new InputNewValuesReport(nameReport, categoryId,listCategoryAndCodes);
						
					} else inputNewValuesReport = new InputNewValuesReport(nameReport, categoryId,listCategoryAndCodes);
			
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
				try {
					matchCheckingValidInputDataAndConnections();
				} catch (InfoException ie) {
					 DialogWindows.dialogWindowError(ie); 
					 return;
				} catch (Exception e1) {
					 DialogWindows.dialogWindowError(e1);
					 	LOg.logToFile(e1);
					 return;
				} 
				new DisplayWaitingForWorkingTask("Updating report... please wait. ") {
					
					@Override
					public Object taskThread() throws Exception {
						String nameReport, updateNameFileReport = null, nameProgect = null;
						String[] oldValues = null;
						Integer categoryId = null;
						File selectedFile = null;
			
						try {
			
							String newRPT_ID         = null;
							Integer newCategoryId    = null;
							String newNameReport     = null;
							String newNameFileReport = null;
							Boolean newRptActive 	 = null;
							
							if (updateDataBaseToggleButton.isSelected()) {
								nameReport = nameReportField.getTextWithCheck();
								categoryId = ((CategoryAndId) categoriesComboBox.getSelectedItem()).getCategoryId();
								//
								if (inputNewValuesReport != null) {
									 newRPT_ID         = inputNewValuesReport.getRPT_ID();
									 newCategoryId     = inputNewValuesReport.getCategory();
									 newNameReport     = inputNewValuesReport.getNameReport();
									 newNameFileReport = inputNewValuesReport.getNameFileReport();
									 newRptActive 	   = inputNewValuesReport.getRPT_ACTIVE();
								}
								if (ParamsRelatedData.isExistTableParams()) {
									if (isChangeRepAttr()) {
											ReportRelatedData.updateReport(nameReport, categoryId, newRPT_ID, newCategoryId, newNameReport, newNameFileReport, newRptActive);
													inputNewValuesReport = null;
									}
									if(isChangeParams()) {
										List<ParamFromParamsPanel> listParamsForUpdate = params.getSettingParamsPanel().getlistOfParams();
										String RPT_ID = ReportRelatedData.getRPT_ID(newNameReport != null ? newNameReport : nameReport,
																					newCategoryId != null ? newCategoryId : categoryId);
											ParamsRelatedData.updateParam(listParamsForUpdate, RPT_ID);
											params = null;
												paramButton.setEmptyHover();
									}
									hideWaitPanel();
									DialogWindows.dialogWindowWarning("Report successfully update!");
									
								} else {
										ReportRelatedData.updateReport(nameReport, categoryId, newRPT_ID, newCategoryId, newNameReport, newNameFileReport, newRptActive);
												inputNewValuesReport = null;
												inputNewValuesButton.setEmptyHover();
												hideWaitPanel();
													DialogWindows.dialogWindowWarning("Report successfully update!");
								}
							} else if (updateFileArchiveToggleButton.isSelected()) {
								updateNameFileReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
								nameProgect    = (String)foldersProjectComboBox.getSelectedItem();
								selectedFile = fileChooser.getSelectedFile();
								//
								 WarArchive.createBackup(selectedFile);
								 	WarArchive.addOrUpdateReportFileInArchive(selectedFile);
									if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
										nameReport = FilesRepository.getNameReport(getNameRptFile(), nameProgect);
										FilesRepository.sendFilesToStorage(nameReport, nameProgect, selectedFile,FilesRepository.Type.UPDATE);
										hideWaitPanel();
											DialogWindows.dialogWindowWarning("Report file successfully update! For report:\r\n\n"+nameReport);
											return null;
									}
									hideWaitPanel();
								 	DialogWindows.dialogWindowWarning("Report file successfully update!");
							}
						} catch (InfoException ie) {
							hideWaitPanel();
							 DialogWindows.dialogWindowError(ie); 
						} catch (ConfirmException ce) { 
							//return
						} catch (Exception e1) {
							hideWaitPanel();
							 DialogWindows.dialogWindowError(e1);
							 	LOg.logToFile(e1);
						} finally {
							hideWaitPanel();
						}
					return null;	
					}
				};
				
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
			upButton.setEnabled(true);
			categoryLabel.setEnabled(true);
			categoriesComboBox.setEnabled(true);
			nameReportLabel.setEnabled(true);
			nameReportField.setEnabled(true);
			inputNewValuesButton.setEnabled(true);
			paramButton.setEnabled(true);
		} else {
			if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
				foldersProjectComboBox.setEnabled(true);
			} else {
				foldersProjectComboBox.setEnabled(false);
				foldersProjectComboBox.setSelectedIndex(-1);
			}
			upButton.setEnabled(false);
			categoryLabel.setEnabled(false);
			categoriesComboBox.setEnabled(false);
			nameReportLabel.setEnabled(false);
			nameReportField.setEnabled(false);
			inputNewValuesButton.setEnabled(false);
			paramButton.setEnabled(false);
		}
	}
	private void upListener() {
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			String inputPattern = nameReportField.getText().trim();
			Entry<String, Integer> findOnPatternNameRep = findNameReportOnPattern(inputPattern);
			if (findOnPatternNameRep != null) {
				nameReportField.setText(findOnPatternNameRep.getKey());
				categoriesComboBox.setSelectedItem(new CategoryAndId(findOnPatternNameRep.getValue(), null));
			} else {
				throw new InfoException("Name report on this pattern '"+lastInputPattern+"' is absent.");
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
	private Entry<String, Integer> findNameReportOnPattern(String inputPattern) throws Exception {
		if (lastInputPattern == null) {// if field keyTyped then lastInputPattern = null
			listNamereports.clear();
			listNamereports.putAll(ReportRelatedData.getNameReports_LIKE_Pattern(inputPattern));
			lastInputPattern = inputPattern;
			//lastNameProgect = nameProgect;
		}
		Collection<String> keys = listNamereports.keySet();
		Entry<String, Integer> entry = null;
		if(!keys.isEmpty()) {
			String key = (String) keys.toArray()[0];
			entry = new AbstractMap.SimpleEntry<String, Integer>(key, listNamereports.remove(key));
		}
		return entry;
	}
	
	private void matchCheckingValidInputDataAndConnections() throws Exception {
		if (updateDataBaseToggleButton.isSelected()) {
			checkBaseConnection();
			if (categoriesComboBox.getSelectedItem() == null) throw new InfoException("Choose a category.");
			checkNewInputCategory();
			matchCheckingInputValues();
		} else if (updateFileArchiveToggleButton.isSelected()) {
			if (SettingsWindow.enableAddToRepositoriesGetSaveSelected()) {
				matchCheckingProjectComboBox();
				String nameRptFile = getNameRptFile();
				String nameProgect = (String)foldersProjectComboBox.getSelectedItem();
				File selectedFile   = fileChooser.getSelectedFile();
				FilesRepository.checkExistFolderReport(FilesRepository.getNameReport(nameRptFile, nameProgect), nameProgect, selectedFile);
			}
			matchCheckingArchive();
		}
	}
	private void matchCheckingProjectComboBox() throws Exception {
		if (foldersProjectComboBox.getSelectedItem() == null) throw new InfoException("Choose a project folder.");
	}
	private String getNameRptFile() {
		String updateNameFileReport = fileChooser.getSelectedFile().toPath().getFileName().toString();
		return updateNameFileReport.replace(".rptdesign", "");
	}

	private boolean isChangeRepAttr() throws InfoException {
		if (inputNewValuesReport != null) {
			String newRPT_ID = inputNewValuesReport.getRPT_ID();
			Integer newCategoryId = inputNewValuesReport.getCategory();
			String newNameReport = inputNewValuesReport.getNameReport();
			String newNameFileReport = inputNewValuesReport.getNameFileReport();
			Boolean rptActive = inputNewValuesReport.getRPT_ACTIVE();
				if (newRPT_ID ==null && newCategoryId ==null && newNameReport ==null && newNameFileReport ==null && rptActive ==null) {
					return false;
				} else return true;
		} else return false;		
	}
	private void matchCheckingInputValues() throws Exception {
		if (ParamsRelatedData.isExistTableParams()) {
			if (inputNewValuesReport == null && params == null) {
				throw new InfoException("Change at least one report attribute or param");
			} else if (inputNewValuesReport != null && params == null) {
				if (!isChangeRepAttr()) throw new InfoException("Change at least one report attribute or param");	
			} else if (inputNewValuesReport == null && params != null) {
				if (!isChangeParams()) throw new InfoException("Change at least one report attribute or param");
			} else {
				if (!isChangeRepAttr() && !isChangeParams()) throw new InfoException("Change at least one report attribute or param");
			}		
		} else {
			if (!isChangeRepAttr()) throw new InfoException("Change at least one report attribute");
		}
	}
	private boolean isChangeParams() throws InfoException {
		if (params != null) return params.isChangeParams(); 
		else return false;		
	}
	private void matchCheckingDataBase() throws Exception {
		String nameReport = nameReportField.getTextWithCheck();
		int categoryId = ((CategoryAndId) categoriesComboBox.getSelectedItem()).getCategoryId();
		Vector<String> listReportStrings = ReportRelatedData.getListOfReportNames(categoryId);
			boolean b = false;
			//LOg.logToFile_SQL(listReportStrings.toString());
			for (String repNameExists : listReportStrings) {
				if (nameReport.equals(repNameExists.trim())) b = true;
			}
			if (!b) throw new InfoException("A report with the this name \""+nameReport+"\" absent.");
	}
	private void checkNewInputCategory() throws Exception {
		if (inputNewValuesReport != null) {
			String nameReport = nameReportField.getTextWithCheck();
			Integer newCategoryId = inputNewValuesReport.getCategory();
			if (newCategoryId != null) {
				String newNameReport = inputNewValuesReport.getNameReport();
				if (newNameReport != null) {
					String RPT_ID = ReportRelatedData.getRPT_ID(newNameReport, newCategoryId);
					if (RPT_ID != null) throw new InfoException("A report with the this name \""+newNameReport+"\" already exist in category \""+newCategoryId+"\".");
				} else {
					String RPT_ID = ReportRelatedData.getRPT_ID(nameReport, newCategoryId);
					if (RPT_ID != null) throw new InfoException("A report with the this name \""+nameReport+"\" already exist in category \""+newCategoryId+"\".");
				}
			}
		}
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
		//
		Util.checkWarPathAndReportParh(fileChooser.getSelectedFile().toPath().getParent());
	}
//	private void restoreBaseBeforeUpdate(String newRPT_ID, Integer newCategoryId, String[] oldValues) throws NumberFormatException, Exception {
//		if (newRPT_ID != null && newCategoryId != null) ReportRelatedData.updateReport(newRPT_ID, newCategoryId, oldValues[0],Integer.valueOf(oldValues[1]), oldValues[2], oldValues[3]); 
//		else if (newRPT_ID != null) ReportRelatedData.updateReport(newRPT_ID, Integer.valueOf(oldValues[1]), oldValues[0],null, oldValues[2], oldValues[3]);
//		else if (newCategoryId != null) ReportRelatedData.updateReport(oldValues[0], newCategoryId, null,Integer.valueOf(oldValues[1]), oldValues[2], oldValues[3]);
//		else ReportRelatedData.updateReport(oldValues[0], newCategoryId, null,Integer.valueOf(oldValues[1]), oldValues[2], oldValues[3]);
//	}
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
	public ComponentListener getAdapterParams() {
		return adapterParams;
	}
	public MyHoverButton getParamsButton() {
		return paramButton;
	}
}
