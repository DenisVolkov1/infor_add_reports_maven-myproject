package windows.param;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dialog.ModalityType;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class ContentsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;

	/**
	 * Create the dialog.
	 */
	public ContentsDialog() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 465, 348);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 437, 296);
		contentPanel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);
	}

	public String getText() {
		return textArea.getText();
	}

	public void setText(String text) {
		this.textArea.setText(text);
	}
	
	
	
}
