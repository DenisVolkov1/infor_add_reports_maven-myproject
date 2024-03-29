package windows.tabs.update;

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
import javax.swing.border.EtchedBorder;

import database.ReportRelatedData;
import exception.InfoException;
import log.LOg;
import util.CategoryAndId;
import util.DialogWindows;
import util.ListCellRendererCategory;
import util.my_components.MyField;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

import windows.MainRunWindow;
import windows.tabs.TabSuperClass;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InputNewValuesReport extends JDialog {

	private MyField RPT_IDField;
	private MyField nameReportField;
	private MyField fileNameField;
												
	private JLabel RPT_IDLabel;
	private JLabel categoryLabel;
	private JLabel nameReportLabel;
	private JLabel fileNameLabel;
	private JComboBox<CategoryAndId> categoriesComboBox;
	private JLabel lblDescr;
	private JLabel lblRptid;
	private JLabel lblCategoey;
	private JLabel lblTitle;
	private JLabel lblPath;
	private JPanel panel;
	//
	private String previousNameReport;
	private Integer previousCategoryId;
	private JCheckBox rptActiveCheckBox;
	//

	private boolean isRPTActiveUses;
	
	public InputNewValuesReport(String nameReport, Integer categoryId, Vector<CategoryAndId> listCategoryAndCodes) throws Exception {

		super(MainRunWindow.getInstance(), "Input values");
		this.previousNameReport = nameReport;
		this.previousCategoryId = categoryId;
	
		//ADD <OLD VALUE>
		Vector<CategoryAndId> list = new Vector<CategoryAndId>(listCategoryAndCodes);
		list.add(0, new CategoryAndId(null, "<Old value>"));
		listCategoryAndCodes = list;
		//
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 674, 234);
		Point p = MainRunWindow.getInstance().getLocation();
		p.setLocation(p.getX(), p.getY()+100);
		this.setLocation(p);
		panel = new JPanel();
		panel.setAutoscrolls(true);
		panel.setAlignmentX(0.0f);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{70, 195, 377};
		gbl_panel.rowHeights = new int[]{26, 27, 27, 27, 27};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
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
		lblNewLabel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 14));
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
		gbc_RPT_IDLabel.anchor = GridBagConstraints.WEST;
		gbc_RPT_IDLabel.fill = GridBagConstraints.VERTICAL;
		gbc_RPT_IDLabel.insets = new Insets(0, 0, 5, 5);
		gbc_RPT_IDLabel.gridx = 1;
		gbc_RPT_IDLabel.gridy = 1;
		panel.add(RPT_IDLabel, gbc_RPT_IDLabel);
		
		RPT_IDField = new MyField("RPT_ID");
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
		gbc_categoryLabel.anchor = GridBagConstraints.WEST;
		gbc_categoryLabel.fill = GridBagConstraints.VERTICAL;
		gbc_categoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_categoryLabel.gridx = 1;
		gbc_categoryLabel.gridy = 2;
		panel.add(categoryLabel, gbc_categoryLabel);
		
		categoriesComboBox = new JComboBox<CategoryAndId>(listCategoryAndCodes);
		//categoriesComboBox.setInheritsPopupMenu(true);
		//categoriesComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		categoriesComboBox.setMaximumRowCount(10);
		//categoriesComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		//categoriesComboBox.setPreferredSize(new Dimension(31, 27));
		
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
		gbc_nameReportLabel.anchor = GridBagConstraints.WEST;
		gbc_nameReportLabel.fill = GridBagConstraints.VERTICAL;
		gbc_nameReportLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameReportLabel.gridx = 1;
		gbc_nameReportLabel.gridy = 3;
		panel.add(nameReportLabel, gbc_nameReportLabel);
		
		nameReportField = new MyField("name report");
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
		gbc_fileNameLabel.anchor = GridBagConstraints.WEST;
		gbc_fileNameLabel.fill = GridBagConstraints.VERTICAL;
		gbc_fileNameLabel.insets = new Insets(0, 0, 0, 5);
		gbc_fileNameLabel.gridx = 1;
		gbc_fileNameLabel.gridy = 4;
		panel.add(fileNameLabel, gbc_fileNameLabel);
		
		fileNameField = new MyField("name file");
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
		
		rptActiveCheckBox = new JCheckBox("RPT_ACTIVE");
	
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(rptActiveCheckBox)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rptActiveCheckBox)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		//////////////////Code
		/////////////
		primaryInit();
		//////
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setVisible(true);
	}
	private void primaryInit() throws Exception {

		final ListCellRendererCategory listCellRendererCategory = new ListCellRendererCategory(); 
		categoriesComboBox.setRenderer(listCellRendererCategory);
		categoriesComboBox.setSelectedIndex(0);
		
	//	final DefaultComboBoxModel<String> model = new DefaultComboBoxModel(listCategoryAndCodes);
	//	categoriesComboBox.setModel(model);
		
		RPT_IDField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (RPT_IDField.getText().length() >= 8) // limit textfield to 8 characters
		            e.consume(); 
			}
		});	
		rptActiveCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isRPTActiveUses = true;
				System.out.println(isRPTActiveUses);
			}
		});
		// set data from report 
			//set RPT_ACTIVE
			rptActiveCheckBox.setSelected(ReportRelatedData.getRPT_ACTIVE(this.previousNameReport, this.previousCategoryId));
			//set report attr
			String[] reportFields = ReportRelatedData.getReportFields(this.previousNameReport, this.previousCategoryId);
				RPT_IDLabel.setText(reportFields    [0]);
				categoryLabel.setText(reportFields  [1]);
				nameReportLabel.setText(reportFields[2]);
				fileNameLabel.setText(reportFields  [3]);
	
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
			
					 String  newRPT_ID = getRPT_ID_private();
					 Integer newCategoryId =  getCategory();
					 String  newNameReport = getNameReport_private();
					 String newNameFileReport = getNameFileReport_private();
					 
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
				
						if (newRPT_ID ==null && newCategoryId ==null && newNameReport ==null && newNameFileReport ==null) TabUpdateReport.getInstance().getInputNewValuesButton().setEmptyHover();
						else TabUpdateReport.getInstance().getInputNewValuesButton().setStandartHover();	
					
				}
			});
	}
	private boolean fieldAlredyEdit(JTextField jTextField) {
		return jTextField.getBackground() == Color.WHITE && jTextField.getText().trim().isEmpty();
	}
	/**
	 * @return - if null means don`t update this value
	 * @throws InfoException 
	 * */
	public String getRPT_ID() throws InfoException {
		if (RPT_IDField.getBackground() == Color.LIGHT_GRAY && RPT_IDField.getText().trim().equals("<Old value>")) return null;
		return RPT_IDField.getTextWithCheck();
	}
	/**
	 * @return - if null means don`t update this value
	 * @throws InfoException 
	 * */
	public String getNameReport() throws InfoException {
		if (nameReportField.getBackground() == Color.LIGHT_GRAY && nameReportField.getText().trim().equals("<Old value>")) return null;
		return nameReportField.getTextWithCheck();
	}
	/**
	 * @return - if null means don`t update this value
	 * @throws InfoException 
	 * */
	public String getNameFileReport() throws InfoException {
		if (fileNameField.getBackground() == Color.LIGHT_GRAY && fileNameField.getText().trim().equals("<Old value>")) return null;
		return fileNameField.getTextWithCheck();
	}
	/**
	 * @return - if null means don`t update this value
	 * */
	public Integer getCategory() {
		CategoryAndId newCategory = (CategoryAndId) categoriesComboBox.getSelectedItem();
		if (newCategory.getCategoryId() == null) return null;
		return newCategory.getCategoryId();
	}
	/**
	 * @return - if null means don`t update this value
	 * */
	public Boolean getRPT_ACTIVE() {
		if (!isRPTActiveUses) return null;
		return rptActiveCheckBox.isSelected();
	}
	//private////////////
	private String getRPT_ID_private()  {
		if (RPT_IDField.getBackground() == Color.LIGHT_GRAY && RPT_IDField.getText().trim().equals("<Old value>")) return null;
		return RPT_IDField.getText();
	}
	private String getNameReport_private()  {
		if (nameReportField.getBackground() == Color.LIGHT_GRAY && nameReportField.getText().trim().equals("<Old value>")) return null;
		return nameReportField.getText();
	}
	private String getNameFileReport_private(){
		if (fileNameField.getBackground() == Color.LIGHT_GRAY && fileNameField.getText().trim().equals("<Old value>")) return null;
		return fileNameField.getText();
	}
	///////////////////
	public JTextField getFileNameField() {
		return fileNameField;
	}
	public JTextField getNameReportField() {
		return nameReportField;
	}
	/**
	 * @return name report for init this obj
	 * */
	public String getPreviousNameReport() {
		return previousNameReport;
	}
	/**
	 * @return category for init this obj
	 * */
	public Integer getPreviousCategoryId() {
		return previousCategoryId;
	}
}
