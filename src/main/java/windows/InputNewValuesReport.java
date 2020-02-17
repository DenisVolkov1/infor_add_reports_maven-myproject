package windows;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Dialog.ModalityType;

import javax.swing.border.EtchedBorder;

import database.CategoryAndCode;
import database.ReportRelatedData;
import log.LOg;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import util.DialogWindows;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InputNewValuesReport extends JDialog {

	private JTextField RPT_IDField;
	private JTextField nameReportField;
	private JTextField fileNameField;
																		
	private JLabel RPT_IDLabel;
	private JLabel categoryLabel;
	private JLabel nameReportLabel;
	private JLabel fileNameLabel;
	private JComboBox<String> categoriesComboBox;
	private JLabel lblDescr;
	private JLabel lblRptid;
	private JLabel lblCategoey;
	private JLabel lblTitle;
	private JLabel lblPath;
	private JPanel panel;
	//
	private String nameReport;
	private Integer categoryId;
	
	public InputNewValuesReport(String nameReport, Integer categoryId) {
		super(MainRunWindow.getInstance(), "Input values");
		this.setResizable(false);
		this.nameReport = nameReport;
		this.categoryId = categoryId;
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 770, 230);
		Point p = MainRunWindow.getInstance().getLocation();
		p.setLocation(p.getX(), p.getY()+100);
		this.setLocation(p);
		
		getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setAlignmentX(0.0f);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(13, 10, 727, 171);
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{70, 332, 320, 0};
		gbl_panel.rowHeights = new int[]{26, 27, 27, 27, 27, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblDescr = new JLabel("Descr");
		lblDescr.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescr.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblDescr.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		GridBagConstraints gbc_lblDescr = new GridBagConstraints();
		gbc_lblDescr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDescr.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescr.gridx = 0;
		gbc_lblDescr.gridy = 0;
		panel.add(lblDescr, gbc_lblDescr);
		
		JLabel lblNewLabel_4 = new JLabel("<Old values>");
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_4.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 0;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("New values");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 0;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblRptid = new JLabel("RPT_ID");
		lblRptid.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblRptid.setHorizontalAlignment(SwingConstants.CENTER);
		lblRptid.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblRptid.setBackground(Color.LIGHT_GRAY);
		lblRptid.setAlignmentX(1.8f);
		GridBagConstraints gbc_lblRptid = new GridBagConstraints();
		gbc_lblRptid.fill = GridBagConstraints.BOTH;
		gbc_lblRptid.insets = new Insets(0, 0, 5, 5);
		gbc_lblRptid.gridx = 0;
		gbc_lblRptid.gridy = 1;
		panel.add(lblRptid, gbc_lblRptid);
		
		RPT_IDLabel = new JLabel("");
		RPT_IDLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		RPT_IDLabel.setBackground(Color.LIGHT_GRAY);
		RPT_IDLabel.setAlignmentX(1.8f);
		RPT_IDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_RPT_IDLabel = new GridBagConstraints();
		gbc_RPT_IDLabel.fill = GridBagConstraints.BOTH;
		gbc_RPT_IDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RPT_IDLabel.gridx = 1;
		gbc_RPT_IDLabel.gridy = 1;
		panel.add(RPT_IDLabel, gbc_RPT_IDLabel);
		
		RPT_IDField = new JTextField();
		RPT_IDField.setFocusable(false);

			RPT_IDField.setBackground(Color.LIGHT_GRAY);
			RPT_IDField.setHorizontalAlignment(SwingConstants.CENTER);
			RPT_IDField.setText("<Old value>");
			RPT_IDField.setFont(new Font("Dialog", Font.PLAIN, 14));
			RPT_IDField.setPreferredSize(new Dimension(5, 27));
			RPT_IDField.setMinimumSize(new Dimension(5, 27));
			RPT_IDField.setColumns(10);
			GridBagConstraints gbc_RPT_IDField = new GridBagConstraints();
			gbc_RPT_IDField.anchor = GridBagConstraints.NORTHWEST;
			gbc_RPT_IDField.insets = new Insets(0, 0, 5, 0);
			gbc_RPT_IDField.gridx = 2;
			gbc_RPT_IDField.gridy = 1;
			panel.add(RPT_IDField, gbc_RPT_IDField);
		
		lblCategoey = new JLabel("Category");
		lblCategoey.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblCategoey.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoey.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCategoey.setBackground(Color.LIGHT_GRAY);
		lblCategoey.setAlignmentX(1.8f);
		GridBagConstraints gbc_lblCategoey = new GridBagConstraints();
		gbc_lblCategoey.fill = GridBagConstraints.BOTH;
		gbc_lblCategoey.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoey.gridx = 0;
		gbc_lblCategoey.gridy = 2;
		panel.add(lblCategoey, gbc_lblCategoey);
		
		categoryLabel = new JLabel("");
		categoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		categoryLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		GridBagConstraints gbc_categoryLabel = new GridBagConstraints();
		gbc_categoryLabel.fill = GridBagConstraints.BOTH;
		gbc_categoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_categoryLabel.gridx = 1;
		gbc_categoryLabel.gridy = 2;
		panel.add(categoryLabel, gbc_categoryLabel);
		
		categoriesComboBox = new JComboBox<String>();
		categoriesComboBox.setInheritsPopupMenu(true);
		categoriesComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		categoriesComboBox.setMaximumRowCount(10);
		categoriesComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		categoriesComboBox.setPreferredSize(new Dimension(31, 27));
		
		GridBagConstraints gbc_categoriesComboBox = new GridBagConstraints();
		gbc_categoriesComboBox.anchor = GridBagConstraints.NORTH;
		gbc_categoriesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_categoriesComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_categoriesComboBox.gridx = 2;
		gbc_categoriesComboBox.gridy = 2;
		panel.add(categoriesComboBox, gbc_categoriesComboBox);
		
		lblTitle = new JLabel("Title");
		lblTitle.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTitle.setBackground(Color.LIGHT_GRAY);
		lblTitle.setAlignmentX(1.8f);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 3;
		panel.add(lblTitle, gbc_lblTitle);
		
		nameReportLabel = new JLabel("");
		nameReportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameReportLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		GridBagConstraints gbc_nameReportLabel = new GridBagConstraints();
		gbc_nameReportLabel.fill = GridBagConstraints.BOTH;
		gbc_nameReportLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameReportLabel.gridx = 1;
		gbc_nameReportLabel.gridy = 3;
		panel.add(nameReportLabel, gbc_nameReportLabel);
		
		nameReportField = new JTextField();
		nameReportField.setFocusable(false);
		nameReportField.setFocusTraversalKeysEnabled(false);
		nameReportField.setText("<Old value>");
		nameReportField.setHorizontalAlignment(SwingConstants.CENTER);
		nameReportField.setBackground(Color.LIGHT_GRAY);
		
			nameReportField.setFont(new Font("Dialog", Font.PLAIN, 14));
			nameReportField.setPreferredSize(new Dimension(5, 27));
			nameReportField.setColumns(10);
			GridBagConstraints gbc_nameReportField = new GridBagConstraints();
			gbc_nameReportField.anchor = GridBagConstraints.NORTH;
			gbc_nameReportField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameReportField.insets = new Insets(0, 0, 5, 0);
			gbc_nameReportField.gridx = 2;
			gbc_nameReportField.gridy = 3;
			panel.add(nameReportField, gbc_nameReportField);
		
		lblPath = new JLabel("File");
		lblPath.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblPath.setHorizontalAlignment(SwingConstants.CENTER);
		lblPath.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPath.setBackground(Color.LIGHT_GRAY);
		lblPath.setAlignmentX(1.8f);
		GridBagConstraints gbc_lblPath = new GridBagConstraints();
		gbc_lblPath.fill = GridBagConstraints.BOTH;
		gbc_lblPath.insets = new Insets(0, 0, 0, 5);
		gbc_lblPath.gridx = 0;
		gbc_lblPath.gridy = 4;
		panel.add(lblPath, gbc_lblPath);
		
		fileNameLabel = new JLabel("");
		fileNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		fileNameLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		GridBagConstraints gbc_fileNameLabel = new GridBagConstraints();
		gbc_fileNameLabel.fill = GridBagConstraints.BOTH;
		gbc_fileNameLabel.insets = new Insets(0, 0, 0, 5);
		gbc_fileNameLabel.gridx = 1;
		gbc_fileNameLabel.gridy = 4;
		panel.add(fileNameLabel, gbc_fileNameLabel);
		
		fileNameField = new JTextField();
		fileNameField.setFocusable(false);
		fileNameField.setFocusTraversalKeysEnabled(false);
		fileNameField.setBackground(Color.LIGHT_GRAY);
		fileNameField.setHorizontalAlignment(SwingConstants.CENTER);
		fileNameField.setText("<Old value>");
		fileNameField.setFont(new Font("Dialog", Font.PLAIN, 14));
		fileNameField.setPreferredSize(new Dimension(5, 27));
		fileNameField.setColumns(10);
		GridBagConstraints gbc_fileNameField = new GridBagConstraints();
		gbc_fileNameField.anchor = GridBagConstraints.NORTH;
		gbc_fileNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_fileNameField.gridx = 2;
		gbc_fileNameField.gridy = 4;
		panel.add(fileNameField, gbc_fileNameField);
		
		//////////////////Code
		/////////////
		primaryInit();
		//////
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setVisible(true);
	}
	private void primaryInit() {
		Vector<CategoryAndCode> listCategoryAndCodes = new Vector( new TabSuperClass().listCategoryAndCodes);
		listCategoryAndCodes.add(0, new CategoryAndCode(null, "<Old value>"));
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel(listCategoryAndCodes);
		categoriesComboBox.setModel(model);
		
		RPT_IDField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (RPT_IDField.getText().length() >= 8) // limit textfield to 8 characters
		            e.consume(); 
			}
		});
		
		try {
			String[] reportFields = ReportRelatedData.getReportFields(this.nameReport, this.categoryId);
				RPT_IDLabel.setText(reportFields    [0]);
				categoryLabel.setText(reportFields  [1]);
				nameReportLabel.setText(reportFields[2]);
				fileNameLabel.setText(reportFields  [3]);
		} catch (Exception e) {
			DialogWindows.dialogWindowError(e);
				LOg.logToFile(e);
					return;
		}
		
			for (Component c : panel.getComponents()) {
				if (c instanceof JTextField) {
					final JTextField textField = (JTextField) c;
					textField.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							textField.setHorizontalAlignment(JTextField.LEADING);
							textField.setBackground(Color.WHITE);
							textField.setFocusable(true);
							textField.grabFocus();
							textField.setText("");
						}
					});
				}
			}
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				for (Component c : panel.getComponents()) {
					if (c instanceof JTextField) {
						if (fieldAlredyEdit((JTextField)c)) {
							String field = ((JTextField)c).getText().trim();
							if (field.isEmpty()) {
								JTextField textField = (JTextField) c;
								textField.setBackground(Color.LIGHT_GRAY);
								textField.setFocusable(false);
								textField.setFocusTraversalKeysEnabled(false);
								textField.setHorizontalAlignment(SwingConstants.CENTER);
								textField.setText("<Old value>");
							}
						}
					}
				}
				String newRPT_ID = getRPT_ID();
				Integer newCategoryId = getCategory();
				String newNameReport = getNameReport();
				String newNameFileReport = getNameFileReport();
					if (newRPT_ID ==null && newCategoryId ==null && newNameReport ==null && newNameFileReport ==null) TabUpdateRreport.getInstance().getInputNewValuesButton().setEmptyHover();
					else TabUpdateRreport.getInstance().getInputNewValuesButton().setStandartHover();
			}
		});
	}
	private boolean fieldAlredyEdit(JTextField jTextField) {
		return jTextField.getBackground() == Color.WHITE && jTextField.getText().trim().isEmpty();
	}
	/**
	 * @return - if null means don`t update this value
	 * */
	public String getRPT_ID() {
		if (RPT_IDField.getBackground() == Color.LIGHT_GRAY && RPT_IDField.getText().trim().equals("<Old value>")) return null;
		return RPT_IDField.getText().trim();
	}
	/**
	 * @return - if null means don`t update this value
	 * */
	public String getNameReport() {
		if (nameReportField.getBackground() == Color.LIGHT_GRAY && nameReportField.getText().trim().equals("<Old value>")) return null;
		return nameReportField.getText().trim();
	}
	/**
	 * @return - if null means don`t update this value
	 * */
	public String getNameFileReport() {
		if (fileNameField.getBackground() == Color.LIGHT_GRAY && fileNameField.getText().trim().equals("<Old value>")) return null;
		return fileNameField.getText().trim();
	}
	/**
	 * @return - if null means don`t update this value
	 * */
	public Integer getCategory() {
		CategoryAndCode newCategory = (CategoryAndCode) categoriesComboBox.getSelectedItem();
		if (newCategory.getCategoryId() == null) return null;
		return newCategory.getCategoryId();
	}
	
	
	
}
