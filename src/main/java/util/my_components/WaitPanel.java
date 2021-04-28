package util.my_components;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;


public class WaitPanel extends JPanel {
	private JLabel text;

	/**
	 * Create the panel.
	 */
	public WaitPanel() {
		setBackground(SystemColor.controlHighlight);
		setBounds(100, 500, 450, 55);
		setPreferredSize(new Dimension(450,55));
		setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(191, 205, 219)), new BevelBorder(BevelBorder.LOWERED, new Color(100, 100, 100), new Color(0, 0, 0), new Color(153, 180, 209), new Color(191, 205, 219))));
		text = new JLabel();
		text.setText("<html>Deploying scprd_scereports.war <br> archive please wait.</html>");
		text.setFont(text.getFont().deriveFont(text.getFont().getStyle() | Font.BOLD, 13f));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(SystemColor.text);
		progressBar.setToolTipText("");
		progressBar.setForeground(SystemColor.activeCaption);
		progressBar.setIndeterminate(true);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(text, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(text, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 15, Short.MAX_VALUE)
							.addGap(16)))
					.addGap(1))
		);
		setLayout(groupLayout);
		setVisible(false);

	}

	public void setText(String text) {
		
		 this.text.setText(text);
	}
}
