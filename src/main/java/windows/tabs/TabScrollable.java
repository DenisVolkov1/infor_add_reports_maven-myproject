package windows.tabs;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import windows.tabs.add.TabAddReport;
import windows.tabs.update.TabUpdateReport;

public class TabScrollable extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public TabScrollable(final TabSuperClass tabPanel) {
		//	setBounds(tabPanel.getBounds());
		  //setPreferredSize(new Dimension(535, 400));
		  setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		  setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		  
//		if (tabPanel.getClass() == TabAddReport.class) {
//			this.addComponentListener(((TabAddReport)tabPanel).getAdapterNewParams());
//		}
//		if (tabPanel.getClass() == TabUpdateReport.class) {
//			this.addComponentListener(((TabUpdateReport)tabPanel).getAdapterParams());
//		}
		
		
		this.addComponentListener(tabPanel.getAdapterForWaitingPanels());
		//this.addComponentListener(tabPanel.getAdapterRepositories());
		setViewportView(tabPanel);
	}
}
