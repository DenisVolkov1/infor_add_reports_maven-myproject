package windows.tabs;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import database.ParamsRelatedData;
import database.ReportRelatedData;
import exception.ConfirmException;
import exception.InfoException;
import log.LOg;

import javax.swing.border.EtchedBorder;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import util.CategoryAndId;
import util.DialogWindows;
import util.ListCellRendererCategory;
import util.my_components.MyField;
import util.my_components.MyHoverButton;
import war.WarArchive;

import javax.swing.JComboBox;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TabDeleteReport extends TabSuperClass {
	private static TabDeleteReport TAB_DELETE_REPORT = null;
	
	private MyField nameReportField;
	private JComboBox<CategoryAndId> categoriesComboBox;
	private MyHoverButton deleteReportButton;
	private JCheckBox deleteFromArchiveCheckBox;
	private MyField nameFileTextField;
	private MyHoverButton deleteFileButton;
	/**
	 * Create the panel.
	 */
	private TabDeleteReport() {
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(8, 0, 504, 323);
		add(panel);
		
		deleteReportButton = new MyHoverButton("Delete report");
		deleteReportButton.setFont(new Font("Dialog", Font.BOLD, 12));

		categoriesComboBox = new JComboBox<CategoryAndId>(listCategoryAndCodes);
		categoriesComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		categoriesComboBox.setMaximumRowCount(10);
		categoriesComboBox.setInheritsPopupMenu(true);
		categoriesComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		categoriesComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		nameReportField = new MyField("name report");
		nameReportField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportField.setColumns(10);
		
		JLabel categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel nameLabel = new JLabel("Name report");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		deleteFromArchiveCheckBox = new JCheckBox("Delete from archive");
		deleteFromArchiveCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteFromArchiveCheckBox.setFont(new Font("Dialog", Font.BOLD, 13));
		deleteFromArchiveCheckBox.setSelected(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Delete from war archive", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
								.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(deleteFromArchiveCheckBox)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(deleteReportButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
									.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(deleteReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deleteFromArchiveCheckBox)))
					.addGap(18)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		deleteFileButton = new MyHoverButton("Delete file");
		deleteFileButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		nameFileTextField = new MyField("name file report");
		nameFileTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameFileTextField.setColumns(10);
		
		JLabel lblNameFileReport = new JLabel("Name file report");
		lblNameFileReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(deleteFileButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameFileTextField, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
							.addGap(24))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNameFileReport)
							.addContainerGap(366, Short.MAX_VALUE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNameFileReport, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameFileTextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(deleteFileButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
	//////////////////Code
	/////////////
	primaryInit();
	//////
	}
	private void primaryInit() {
		//final DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel(listCategoryAndCodes);
		//categoriesComboBox.setModel(model3);
		final ListCellRendererCategory listCellRendererCategory = new ListCellRendererCategory(); 
		categoriesComboBox.setRenderer(listCellRendererCategory);
		
		deleteReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pathString = null;
				String nameReport = null;
				Integer categoryId = null;
				String nameFileReport = null;
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					matchCheckingDeleteReport();
					//
					nameReport = nameReportField.getTextWithCheck();
					categoryId = ((CategoryAndId) categoriesComboBox.getSelectedItem()).getCategoryId();
					if (ParamsRelatedData.isExistTableParams()) {
						String RPT_ID = ReportRelatedData.getRPT_ID(nameReport, categoryId);
							ParamsRelatedData.deleteParam(RPT_ID);
					}
						ReportRelatedData.deleteReport(nameReport, categoryId);
					if (deleteFromArchiveCheckBox.isSelected()) {
						matchCheckingDeleteReportFromArchive();
						//
						pathString = ReportRelatedData.getReportFilePath(nameReport, categoryId);
						nameFileReport = (pathString.replace("/frameset?__report=report/", "")).replace(".rptdesign", "");
								WarArchive.deleteReportFileFromArchive(nameFileReport);
					}
					DialogWindows.dialogWindowWarning("Successfully delete!");
				} catch (InfoException ie) {
					 DialogWindows.dialogWindowError(ie); 
				} catch (Exception e1) {
					 DialogWindows.dialogWindowError(e1);
					 	LOg.logToFile(e1);
				} finally {
					 setCursor(null);
				}	
			}
		});
		deleteFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					matchCheckingDeleteReportFileFromArchive();
					//
					String nameFileReport = nameFileTextField.getTextWithCheck();
					WarArchive.deleteReportFileFromArchive(nameFileReport);
						DialogWindows.dialogWindowWarning("Successfully delete!");
				} catch (InfoException ie) {
					 DialogWindows.dialogWindowError(ie); 
				} catch (Exception e1) {
					 DialogWindows.dialogWindowError(e1);
					 	LOg.logToFile(e1);
				} finally {
					 setCursor(null);
				}	
			}
		});
	}
	private void matchCheckingDeleteReport() throws Exception {
		if (categoriesComboBox.getSelectedItem() == null) throw new InfoException("Choose a category.");
		
		String nameReport = nameReportField.getTextWithCheck();
	
		int categoryId = ((CategoryAndId) categoriesComboBox.getSelectedItem()).getCategoryId();
		Vector<String> listReportStrings = ReportRelatedData.getListOfReportNames(categoryId);
	
		boolean isMatch = false;
		for (String repNameExists : listReportStrings) {
			if (nameReport.equals(repNameExists.trim())) isMatch = true;
		}
		if (!isMatch) throw new InfoException("A report with the this name \""+nameReport+"\" absent.");
		
	}
	private void matchCheckingDeleteReportFromArchive() throws Exception {
		String nameReport = nameReportField.getTextWithCheck();
		int categoryId = ((CategoryAndId) categoriesComboBox.getSelectedItem()).getCategoryId();
		String pathString = ReportRelatedData.getReportFilePath(nameReport, categoryId);
		//
		String nameFileReport = pathString.replace("/frameset?__report=report/", "");
		Vector<String> listFileNameReport = WarArchive.getListOfReportFilesNames();
		boolean b = false;
		for (String fileNameReportExists : listFileNameReport) {
			if (nameFileReport.equals(fileNameReportExists)) b = true;
		}
		if(!b) throw new InfoException("A file report absent.");			
	}
	private void matchCheckingDeleteReportFileFromArchive() throws Exception {
		String nameReport = nameFileTextField.getTextWithCheck();
		//
		String nameFileReport = nameReport + ".rptdesign";
		Vector<String> listFileNameReport = WarArchive.getListOfReportFilesNames();
	
		boolean b = false;
		for (String fileNameReportExists : listFileNameReport) {
			if (nameFileReport.equals(fileNameReportExists)) b = true;
		}
		if(!b) throw new InfoException("A file report absent.");
	}
	public static TabDeleteReport getInstance() {
		if (TAB_DELETE_REPORT == null) {
			TAB_DELETE_REPORT = new TabDeleteReport();
			return TAB_DELETE_REPORT;
		} else {
			return TAB_DELETE_REPORT;
		}
	}
}