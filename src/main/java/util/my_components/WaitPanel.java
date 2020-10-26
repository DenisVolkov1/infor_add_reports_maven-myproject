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


public class WaitPanel extends JPanel {
	private JLabel text;

	/**
	 * Create the panel.
	 */
	public WaitPanel() {
		setBackground(SystemColor.controlHighlight);
		setBounds(100, 500, 450, 45);
		setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(191, 205, 219)), new BevelBorder(BevelBorder.LOWERED, new Color(100, 100, 100), new Color(0, 0, 0), new Color(153, 180, 209), new Color(191, 205, 219))));
		text = new JLabel();
		text.setText("444444444444444444444444444444444444");
		text.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(SystemColor.text);
		progressBar.setToolTipText("");
		progressBar.setForeground(SystemColor.activeCaption);
		progressBar.setIndeterminate(true);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(text)
					.addGap(18, 18, Short.MAX_VALUE)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(text, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		setVisible(false);

	}

	public void setText(String text) {
		if(text.length() >= 36) {
			this.text.setText(text.substring(0,32)+"...");
		} else this.text.setText(text);
	}
}
