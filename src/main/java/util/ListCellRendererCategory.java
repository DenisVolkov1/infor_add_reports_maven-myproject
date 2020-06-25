package util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import database.CategoryAndCode;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListCellRendererCategory extends JPanel implements ListCellRenderer<CategoryAndCode> {
	private JLabel category;
	private JLabel code;
	public ListCellRendererCategory() {
		
		setPreferredSize(new Dimension(360, 22));
		category = new JLabel();
		//category.setText("klklklk");
		
		category.setHorizontalAlignment(SwingConstants.LEFT);
		category.setFont(new Font("Dialog", Font.BOLD, 14));
		
		code = new JLabel();
		//code.setText("5");
	
		code.setForeground(Color.GRAY);
		code.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(code)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(category, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(category))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(code)))
					.addContainerGap(1, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends CategoryAndCode> list, CategoryAndCode value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		if (value != null) {
			category.setText(value.getCategory());
			code.setText("(" + value.getCategoryId().toString() + ")");
		}
	
		   if (isSelected) {
               setBackground(list.getSelectionBackground());
               setForeground(list.getSelectionForeground());
           } else {
               setBackground(list.getBackground());
               setForeground(list.getForeground());
           }
		
		return this;
	}
}
