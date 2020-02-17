package windows;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import database.CategoryAndCode;
import database.CategoryRelatedData;
import database.ReportRelatedData;
import log.LOg;
import util.DialogWindows;
import util.MyHoverButton;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import java.awt.ComponentOrientation;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;

public class TabCategories extends TabSuperClass {
	private static TabCategories TAB_CATWGORY_REPORT = null;
	
	private JTextField nameCategoryTextField;
	private JButton addCategoryButton;
	private JTextField newNameCategoryTextField;
	private JComboBox<String> renameCategoriesComboBox;
	private JButton renameCategoryButton;
	private JPanel panel_1;

	/**
	 * Create the panel.
	 */
	private TabCategories() {
		setBounds(new Rectangle(0, 0, 500, 250));
		setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(8, 0, 504, 321);
		add(panel_1);
		panel_1.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 504, 321);
		panel_1.add(panel);
		
		nameCategoryTextField = new JTextField();
		nameCategoryTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameCategoryTextField.setText("");
		nameCategoryTextField.setColumns(10);
		
		JLabel nameCategoryLabel = new JLabel("Name category");
		nameCategoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		addCategoryButton = new MyHoverButton("Add");
		addCategoryButton.setFont(new Font("Dialog", Font.BOLD, 12));
	
		JLabel renameCategoryLabel = new JLabel("Rename category");
		renameCategoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		renameCategoryButton = new MyHoverButton("Rename");
		
		newNameCategoryTextField = new JTextField();
		newNameCategoryTextField.setText("");
		newNameCategoryTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		newNameCategoryTextField.setColumns(10);
		
		JLabel newNameCategoryLabel = new JLabel("New name category");
		newNameCategoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		renameCategoriesComboBox = new JComboBox<String>();
		renameCategoriesComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		renameCategoriesComboBox.setMaximumRowCount(10);
		renameCategoriesComboBox.setInheritsPopupMenu(true);
		renameCategoriesComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		renameCategoriesComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(newNameCategoryTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
								.addComponent(renameCategoriesComboBox, Alignment.LEADING, 0, 351, Short.MAX_VALUE)
								.addComponent(nameCategoryTextField, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(renameCategoryButton, 0, 0, Short.MAX_VALUE)
								.addComponent(addCategoryButton, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
							.addGap(19))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(renameCategoryLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(382, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(newNameCategoryLabel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(366, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(nameCategoryLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(396, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(13)
					.addComponent(nameCategoryLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameCategoryTextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(addCategoryButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(renameCategoryLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(renameCategoriesComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(newNameCategoryLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(newNameCategoryTextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(renameCategoryButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(133, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		/////////////Code
		///////
		primaryInit();
		/////
	}
	private void primaryInit() {
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel(listCategoryAndCodes);
		renameCategoriesComboBox.setModel(model);
		
		addCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newCategory = nameCategoryTextField.getText().trim();
				if (newCategory.isEmpty()) {
					DialogWindows.dialogWindowError("Field name category is empty.");
					return;
				}
				Vector<String> listCategories;
				try {
					listCategories = CategoryRelatedData.getListOfCategoryNames();
					for (String nameExistCategory : listCategories) {
						if (nameExistCategory.equals(nameCategoryTextField.getText().trim())) {
							DialogWindows.dialogWindowError("This name category is already exist.");
							return;
						}
					}
					CategoryRelatedData.insertCategory(newCategory);		
				} catch (Exception e1) {
					DialogWindows.dialogWindowError(e1);
						LOg.logToFile(e1);
							return;
				}
				DialogWindows.dialogWindowWarning("Category successfully added!");
			}
		});
		renameCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (renameCategoriesComboBox.getSelectedItem() == null) {
					DialogWindows.dialogWindowError("Choose a category.");
					return;
				}
				String newNameCategory = newNameCategoryTextField.getText().trim();
				if (newNameCategory.isEmpty()) {
					DialogWindows.dialogWindowError("Field new name category is empty.");
					return;
				}
				int categoryId = ((CategoryAndCode) renameCategoriesComboBox.getSelectedItem()).getCategoryId();
				
				try {
					CategoryRelatedData.updateCategory(newNameCategory, categoryId);
				} catch (Exception e1) {
					DialogWindows.dialogWindowError(e1);
						LOg.logToFile(e1);
				}
				DialogWindows.dialogWindowWarning("Category successfully rename!");
			}
		});
	}
	public static TabCategories getInstance() {
		if (TAB_CATWGORY_REPORT == null) {
			TAB_CATWGORY_REPORT = new TabCategories();
			return TAB_CATWGORY_REPORT;
		} else {
			return TAB_CATWGORY_REPORT;
		}
	}
}
