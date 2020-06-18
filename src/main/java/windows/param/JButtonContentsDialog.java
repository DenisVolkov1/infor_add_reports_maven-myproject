package windows.param;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import util.MyHoverButton;

public class JButtonContentsDialog extends MyHoverButton {
	
	private ContentsDialog contentDialog = null;
	
	
	public JButtonContentsDialog (String title) {
		super(title);	
		
		contentDialog = new ContentsDialog();	
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentDialog.setVisible(true);
			}
		});
	}

	public ContentsDialog getContentDialog() {
		return contentDialog;
	}
	

}
