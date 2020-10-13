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
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

public class WaitPanel extends JPanel {
	private JLabel text;

	/**
	 * Create the panel.
	 */
	public WaitPanel() {
		//setBounds(100, 500, 250, 80);
		setBorder(new BevelBorder(BevelBorder.RAISED, new Color(64, 64, 64), new Color(128, 128, 128), new Color(153, 180, 209), SystemColor.inactiveCaption));
		text = new JLabel();
		text.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.CYAN);
		progressBar.setIndeterminate(true);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(47, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(text)
							.addGap(85))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(43))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addComponent(text)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		setVisible(true);

	}

	public void setText(String text) {
		this.text.setText(text);
	}
}
