package windows.param;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import util.MyHoverButton;

import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SettingParamsPanel extends JPanel {
	
	private List<JTextField> paramNameField = new Vector<JTextField>(15);
	private List<JTextField> paramLabelField = new Vector<JTextField>(15);
	private List<JComboBox<String>> paramTypeComboBox = new Vector<JComboBox<String>>(15);
	private List<JButtonContentsDialog > contentsButton = new Vector<JButtonContentsDialog >(15);
	private List<JButton> deleteButton = new Vector<JButton>(15);

	
	private JButton addButton;

	private JPanel panelGrid;
	private ActionListener deleteButtonListener;
	private GridBagLayout gbl_panelGrid;

	/**
	 * Create the panel.
	 */
	public SettingParamsPanel() {
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panelGrid = new JPanel();
		panelGrid.setBounds(7, 47, 515, 2);
	    gbl_panelGrid = new GridBagLayout();
		gbl_panelGrid.columnWidths = new int[] {130, 130, 115, 40, 40};
		gbl_panelGrid.rowHeights = new int[] {30, 0};
		gbl_panelGrid.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panelGrid.rowWeights = new double[]{0.0, 4.9E-324};
		panelGrid.setLayout(gbl_panelGrid);
		
		addButton = new MyHoverButton("+",new Color(50,205,50),new Color(119,221,119));
		addButton.setBounds(539, 12, 48, 26);
		addButton.setFont(new Font("Dialog", Font.BOLD, 14));
		setLayout(null);

		JPanel header = new JPanel();
		header.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		header.setBounds(7, 12, 515, 30);
		
		GridBagLayout gbl_header = new GridBagLayout();
		gbl_header.columnWidths = new int[] {130, 130, 115, 40, 40};
		gbl_header.rowHeights = new int[]{30, 0};
		gbl_header.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_header.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		header.setLayout(gbl_header);
		
		JLabel lblNewLabel_4 = new JLabel("Param name");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		header.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_1_1 = new JLabel("Param label");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 0;
		header.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Param type");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2_1.gridx = 2;
		gbc_lblNewLabel_2_1.gridy = 0;
		header.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Contents");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_3_1 = new GridBagConstraints();
		gbc_lblNewLabel_3_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3_1.gridx = 3;
		gbc_lblNewLabel_3_1.gridy = 0;
		header.add(lblNewLabel_3_1, gbc_lblNewLabel_3_1);
		
		add(header);
		add(panelGrid);
		add(addButton);
		
		addButton.addActionListener(new ActionListener() {
	    
			public void actionPerformed(ActionEvent e) {
				if (paramNameField.size() >= 15) return;
				//add new param
				addParam(null , null, null, null);
			}
		});
		
		 deleteButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		
				int c = Integer.parseInt(e.getActionCommand());
				
				panelGrid.removeAll();
				panelGrid.setBounds(7, 47, 515, 2);
		
				paramNameField.remove(c);
				paramLabelField.remove(c);
				paramTypeComboBox.remove(c);
				contentsButton.remove(c);
				deleteButton.remove(c);
		
				int count = deleteButton.size();
				for (int i = 0; count > i; i++) {
					addParamDinamic(paramNameField.get(i),
									paramLabelField.get(i),
									paramTypeComboBox.get(i),
									contentsButton.get(i),
									deleteButton.get(i),
									i);
				}
				panelGrid.revalidate();
				panelGrid.repaint();
				
				for (JButton deleteBtn : deleteButton) {
					deleteBtn.setActionCommand(Integer.toString((deleteButton.indexOf(deleteBtn))));
				}
			}
		};
	}
	
	public void addParam(String PARAM_NAME,String PARAM_LABEL, String PARAM_TYPE,String PARAM_CONTENTS) {
		
		panelGrid.setBounds(7, 47, 515, panelGrid.getHeight()+34);
			
		JTextField nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameField.setText(PARAM_NAME == null ? "" : PARAM_NAME);
		paramNameField.add(nameField);
		GridBagConstraints gbc_paramNameField = new GridBagConstraints();
		
		gbc_paramNameField.insets = new Insets(0, 0, 5, 5);
		gbc_paramNameField.fill = GridBagConstraints.BOTH;
		gbc_paramNameField.gridx = 0;
		gbc_paramNameField.gridy = paramNameField.size()-1;
		panelGrid.add(nameField, gbc_paramNameField);
		nameField.setColumns(10);
		
		JTextField labelField = new JTextField();
		labelField.setHorizontalAlignment(SwingConstants.CENTER);
		labelField.setFont(new Font("Dialog", Font.PLAIN, 14));
		labelField.setText(PARAM_LABEL == null ? "" : PARAM_LABEL);
		paramLabelField.add(labelField);
		GridBagConstraints gbc_paramLabelField = new GridBagConstraints();
	
		gbc_paramLabelField.insets = new Insets(0, 0, 5, 5);
		gbc_paramLabelField.fill = GridBagConstraints.BOTH;
		gbc_paramLabelField.gridy = paramLabelField.size()-1;
		panelGrid.add(labelField, gbc_paramLabelField);
		labelField.setColumns(10);
		
		JComboBox<String> typeComboBox = new JComboBox<String>(new String[] {"Date" , "Dropdown" , "MultiSelect" , "Number" , "Text" , "Boolean" , "Time"});
		typeComboBox.setSelectedItem(PARAM_TYPE == null ? "Text" : PARAM_TYPE);
		paramTypeComboBox.add(typeComboBox);
		GridBagConstraints gbc_paramTypeComboBox = new GridBagConstraints();
	
		gbc_paramTypeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_paramTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_paramTypeComboBox.gridy = paramTypeComboBox.size()-1;
		panelGrid.add(typeComboBox, gbc_paramTypeComboBox);
		
		JButtonContentsDialog contBtn  = new JButtonContentsDialog ("...");
		contBtn.getContentDialog().setText(PARAM_CONTENTS == null ? "" : PARAM_CONTENTS);
		contentsButton.add(contBtn);
		contBtn.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_contentsButton = new GridBagConstraints();
		gbc_contentsButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_contentsButton.insets = new Insets(0, 0, 5, 5);
		gbc_contentsButton.gridx = 3;
		gbc_contentsButton.gridy = contentsButton.size()-1;
		panelGrid.add(contBtn, gbc_contentsButton);
		
		JButton deleteBtn  = new MyHoverButton("-",new Color(230,103,97),new Color(238,144,134));
		deleteButton.add(deleteBtn);
		deleteBtn.setActionCommand(Integer.toString((deleteButton.indexOf(deleteBtn))));
		deleteBtn.addActionListener(deleteButtonListener);
		deleteBtn.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_deleteButton.gridx = 4;
		gbc_deleteButton.gridy = deleteButton.size()-1;
		panelGrid.add(deleteBtn, gbc_deleteButton);
		
		//gridBagConstraints.add(gbcArray);
		panelGrid.revalidate();
		panelGrid.repaint();
	}
	
	private void addParamDinamic(JTextField nameField, JTextField labelField, JComboBox<String> typeComboBox,JButtonContentsDialog contBtn, JButton deleteBtn, int gridy) {

		panelGrid.setBounds(7, 47, 515, panelGrid.getHeight()+34);
		
		GridBagConstraints gbc_paramNameField = new GridBagConstraints();
		gbc_paramNameField.insets = new Insets(0, 0, 5, 5);
		gbc_paramNameField.fill = GridBagConstraints.BOTH;
		gbc_paramNameField.gridx = 0;
		gbc_paramNameField.gridy = gridy;
		panelGrid.add(nameField, gbc_paramNameField);

		
		GridBagConstraints gbc_paramLabelField = new GridBagConstraints();
		gbc_paramLabelField.insets = new Insets(0, 0, 5, 5);
		gbc_paramLabelField.fill = GridBagConstraints.BOTH;
		gbc_paramLabelField.gridx = 1;
		gbc_paramLabelField.gridy = gridy;
		panelGrid.add(labelField, gbc_paramLabelField);
		
		GridBagConstraints gbc_paramTypeComboBox = new GridBagConstraints();
		gbc_paramTypeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_paramTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_paramTypeComboBox.gridx = 2;
		gbc_paramTypeComboBox.gridy = gridy;
		panelGrid.add(typeComboBox, gbc_paramTypeComboBox);
		
		GridBagConstraints gbc_contentsButton = new GridBagConstraints();
		gbc_contentsButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_contentsButton.insets = new Insets(0, 0, 5, 5);
		gbc_contentsButton.gridx = 3;
		gbc_contentsButton.gridy = gridy;
		panelGrid.add(contBtn, gbc_contentsButton);
		
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteButton.insets = new Insets(0, 0, 5, 5);
		gbc_deleteButton.gridx = 4;
		gbc_deleteButton.gridy = gridy;
		panelGrid.add(deleteBtn, gbc_deleteButton);
		
		panelGrid.revalidate();
		panelGrid.repaint();
	}
	public Vector<ParamFromParamsPanel> getlistOfParams() {
		Vector<ParamFromParamsPanel> res = new Vector<ParamFromParamsPanel>();
		//
		for(int i = 0;paramNameField.size() > i;i++) {
			String pARAM_NAME     = paramNameField.get(i).getText();
			String pARAM_LABEL    = paramLabelField.get(i).getText();
			String pARAM_TYPE     = (String)(paramTypeComboBox.get(i).getSelectedItem()); 
			String pARAM_CONTENTS = contentsButton.get(i).getContentDialog().getText();
			//
			ParamFromParamsPanel p = new ParamFromParamsPanel(pARAM_NAME, pARAM_LABEL, pARAM_TYPE, pARAM_CONTENTS);
			res.add(p);
		}
		return res;
	}
}
