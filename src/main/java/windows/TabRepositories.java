package windows;

import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import util.MyHoverButton;
import javax.swing.border.EtchedBorder;

public class TabRepositories extends JPanel {
	private static TabRepositories TAB_TO_REPOSITORIES = null;
	private JPanel panel;
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	private TabRepositories() {
		setBounds(new Rectangle(0, 0, 500, 250));
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setLayout(null);
	
		add(panel);
		
		panel.setBounds(50, 0, 481, 250);
		
		JComboBox<String> foldersProjectComboBox = new JComboBox<String>();
		foldersProjectComboBox.setMaximumRowCount(10);
		foldersProjectComboBox.setInheritsPopupMenu(true);
		foldersProjectComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		foldersProjectComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JLabel lblNewLabel = new JLabel("Project folder in repositories");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel fileReportLabel = new JLabel("");
		fileReportLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		fileReportLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel nameFileReportLabel = new JLabel("Name file report");
		nameFileReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		MyHoverButton fileReportButton = new MyHoverButton("File ...");
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JLabel nameReportLabel = new JLabel("Name report");
		nameReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
				.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
				.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
				.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGap(6)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGap(6)
					.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(137, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		/////////////Code
		///////
		primaryInit();
		/////

	}
	private void primaryInit() {
		// TODO Auto-generated method stub
		
	}
	public static TabRepositories getInstance() {
		if (TAB_TO_REPOSITORIES == null) {
			TAB_TO_REPOSITORIES = new TabRepositories();
			return TAB_TO_REPOSITORIES;
		} else {
			return TAB_TO_REPOSITORIES;
		}
	}
}
