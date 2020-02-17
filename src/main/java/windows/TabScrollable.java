package windows;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TabScrollable extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public TabScrollable(final TabSuperClass tabPanel) {
		
		  setPreferredSize(new Dimension(535, 200));
		  setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		  setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		 
		this.addComponentListener(tabPanel.getAdapter());
		setViewportView(tabPanel);
	}
}
