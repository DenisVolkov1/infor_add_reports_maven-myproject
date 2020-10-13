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
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;

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
