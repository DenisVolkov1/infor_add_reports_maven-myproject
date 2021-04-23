package util;

import java.awt.Component;

import javax.swing.SwingUtilities;

import log.LOg;
import windows.MainRunWindow;

public abstract class DisplayWaitingForWorkingTask {
	
	private Component waitPanel;
	
	public DisplayWaitingForWorkingTask(final String waitingMessage) {
		SwingUtilities.invokeLater(new Runnable(){
	    	public void run() {
	    		Util.setActivitySubComponents(MainRunWindow.getInstance().getContentPane(), false);
	    	}
	    });
		Thread thread = new Thread(new  Runnable() {
			public void run() {
				 waitPanel = MainRunWindow.addPanelToGlassPanel(waitingMessage);
				 try {
					 	// run Task
						taskThread();
						//
				} catch (Exception e) {
					e.printStackTrace();
					hideWaitPanel();
						LOg.logToFile(e);
				}
				hideWaitPanel();
			}
		});
	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		thread.start();
	}
	
	public void hideWaitPanel() {
		MainRunWindow.hideGlassPanel(waitPanel);
		Util.setActivitySubComponents(MainRunWindow.getInstance().getContentPane(), true);
	}
	
	public abstract Object taskThread() throws Exception;
	 

}
