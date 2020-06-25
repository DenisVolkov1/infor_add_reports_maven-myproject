package util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListCellRendererCategory extends JPanel implements ListCellRenderer<CategoryAndId> {
	private JLabel categoryJLabel;
	private JLabel idJLabel;
	public ListCellRendererCategory() {
		
		setPreferredSize(new Dimension(360, 22));
		categoryJLabel = new JLabel();
		//category.setText("klklklk");
		
		categoryJLabel.setHorizontalAlignment(SwingConstants.LEFT);
		categoryJLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		
		idJLabel = new JLabel();
		//code.setText("5");
	
		idJLabel.setForeground(Color.GRAY);
		idJLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(idJLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(categoryJLabel, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(categoryJLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(idJLabel)))
					.addContainerGap(1, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends CategoryAndId> list, CategoryAndId value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		if (value != null) {
			categoryJLabel.setText(value.getCategory());
			idJLabel.setText("(" + value.getCategoryId().toString() + ")");
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
