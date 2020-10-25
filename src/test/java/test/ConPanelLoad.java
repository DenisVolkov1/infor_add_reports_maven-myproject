package test;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;

import util.my_components.WaitPanel;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.FlowLayout;

public class ConPanelLoad extends JPanel {

	/**
	 * Create the panel.
	 */
	public ConPanelLoad() {
		setLayout(null);

		WaitPanel waitPanel = new WaitPanel();
		waitPanel.setBounds(3, 100, 454, 30);
		add(waitPanel);
		WaitPanel waitPanel_1 = new WaitPanel();
		waitPanel_1.setBounds(3, 140, 454, 30);
		add(waitPanel_1);
		setVisible(true);

	}

}
