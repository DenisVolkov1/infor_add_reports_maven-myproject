package test;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import util.CategoryAndId;
import util.my_components.MyField;
import util.my_components.MyHoverButton;
import util.my_components.MyHoverToggleButton;
import windows.tabs.update.InputNewValuesReport;
import windows.tabs.update.ParamsPanelUpdate;
import windows.tabs.update.TabUpdateReport;

public class RRRRRRRRRR extends JPanel {
	private static TabUpdateReport TAB_UPDADE_REPORT = null;
	private static InputNewValuesReport inputNewValuesReport = null;
	
	private MyField nameReportField;
	
	private JComboBox<CategoryAndId> categoriesComboBox;
	private MyHoverButton updateReportButton;
	private MyHoverToggleButton updateDataBaseToggleButton;
	private MyHoverToggleButton updateFileArchiveToggleButton;
	private MyHoverButton fileReportButton;
	private JLabel categoryLabel;
	private JLabel nameReportLabel;
	private JLabel nameFileReportLabel;
	private JLabel fileReportLabel;
	private JFileChooser fileChooser;
	private MyHoverButton refreshServiceButton;
	private MyHoverButton inputNewValuesButton;
	private MyHoverButton paramButton;
	private JLabel ipDataSrcLabel;
	private JComboBox<String> foldersProjectComboBox;
	private JLabel lblProjectFolderIn;
	//
	protected ComponentAdapter adapterParams;
	protected ParamsPanelUpdate params;

	/**
	 * Create the panel.
	 */
	public RRRRRRRRRR() {
		setPreferredSize(new Dimension(582, 398));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 0, 504, 398);
		add(panel);
		
		categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		nameReportField = new MyField("name report");
		nameReportField.setFont(new Font("Dialog", Font.PLAIN, 14));
		nameReportField.setColumns(10);
		
		updateReportButton = new MyHoverButton("Update report");
		updateReportButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		nameReportLabel = new JLabel("Name report");
		nameReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		categoriesComboBox = new JComboBox<CategoryAndId>();
		categoriesComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		categoriesComboBox.setMaximumRowCount(10);
		categoriesComboBox.setInheritsPopupMenu(true);
		categoriesComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		categoriesComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		fileReportLabel = new JLabel("");
		fileReportLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		fileReportLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		nameFileReportLabel = new JLabel("Name file report");
		nameFileReportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		fileReportButton = new MyHoverButton("File ...");
		fileReportButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		updateDataBaseToggleButton = new MyHoverToggleButton("");
		updateDataBaseToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateDataBaseToggleButton.setSelected(true);
		
		
		updateFileArchiveToggleButton = new MyHoverToggleButton("");
		updateFileArchiveToggleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateFileArchiveToggleButton.setSelected(true);
		
		
		refreshServiceButton = new MyHoverButton("Refresh service");
		refreshServiceButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		inputNewValuesButton = new MyHoverButton("Input new values...");
		inputNewValuesButton.setFont(new Font("Dialog", Font.BOLD, 12));
		inputNewValuesButton.setEmptyHover();
		
		ipDataSrcLabel = new JLabel();
		ipDataSrcLabel.setForeground(Color.GRAY);
		ipDataSrcLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		ipDataSrcLabel.setBorder(null);
		
		foldersProjectComboBox = new JComboBox<String>();
		foldersProjectComboBox.setMaximumRowCount(10);
		foldersProjectComboBox.setInheritsPopupMenu(true);
		foldersProjectComboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		foldersProjectComboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		lblProjectFolderIn = new JLabel("Project folder in repositories");
		lblProjectFolderIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		paramButton = new MyHoverButton("Params");
		paramButton.setText("Params");
		paramButton.setFont(new Font("Dialog", Font.BOLD, 12));
		paramButton.setEmptyHover();
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(updateFileArchiveToggleButton, 0, 0, Short.MAX_VALUE)
								.addComponent(updateDataBaseToggleButton, GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(updateReportButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
											.addComponent(nameFileReportLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(paramButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(inputNewValuesButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
										.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
										.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
										.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProjectFolderIn, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(lblProjectFolderIn, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(foldersProjectComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(categoriesComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameReportField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(inputNewValuesButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(paramButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addComponent(updateDataBaseToggleButton, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(23)
							.addComponent(nameFileReportLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(fileReportLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(fileReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(updateFileArchiveToggleButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(updateReportButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(refreshServiceButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ipDataSrcLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}

}
