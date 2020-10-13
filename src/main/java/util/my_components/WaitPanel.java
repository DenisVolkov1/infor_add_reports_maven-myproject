package util.my_components;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.SystemColor;

public class WaitPanel extends JPanel {
	private JLabel text;

	/**
	 * Create the panel.
	 */
	public WaitPanel(String textStr) {
		//setBounds(100, 500, 250, 80);
		setBorder(new BevelBorder(BevelBorder.RAISED, new Color(64, 64, 64), new Color(128, 128, 128), new Color(153, 180, 209), SystemColor.inactiveCaption));
		text = new JLabel(textStr);
		text.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(115)
					.addComponent(text)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(text)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		setVisible(true);

	}

}
