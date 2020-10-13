package util.my_components;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;


public class WaitPanel extends JPanel {
	private JLabel text;

	/**
	 * Create the panel.
	 */
	public WaitPanel() {
		//setBounds(100, 500, 250, 80);
		setBorder(new BevelBorder(BevelBorder.RAISED, new Color(64, 64, 64), new Color(128, 128, 128), new Color(153, 180, 209), Color.DARK_GRAY));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		text = new JLabel();
		text.setText("444444444444444444444444444444444444");
		text.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(text);
		
		JLabel lblEmpty = new JLabel();
		lblEmpty.setForeground(SystemColor.control);
		lblEmpty.setText("EMPTYEMPTY");
		lblEmpty.setFont(new Font("Tahoma", Font.PLAIN, 5));
		add(lblEmpty);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(SystemColor.text);
		progressBar.setToolTipText("");
		progressBar.setForeground(SystemColor.activeCaption);
		progressBar.setIndeterminate(true);
		add(progressBar);
		setVisible(true);

	}

	public void setText(String text) {
		if(text.length() >= 36) {
			this.text.setText(text.substring(0,32)+"...");
		} else this.text.setText(text);
	}
}
