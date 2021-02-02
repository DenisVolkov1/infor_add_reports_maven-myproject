package util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import log.LOg;
import windows.MainRunWindow;

public abstract class DisplayConnectionDelay extends NewTaskDelay {
	
	private Component panelGlass1;
	private static List<Thread> listTheads = new ArrayList<Thread>(2);
	private String propertiesConnection;
	
	public enum TypeConnection {
		BASE_CONNECTION,
		REPO_CONNECTION
	}
	public DisplayConnectionDelay(TypeConnection nameThread,String propertiesConnection, long delay) {
		super(nameThread.toString(), delay);
		this.propertiesConnection = propertiesConnection;
		for(Thread thread : listTheads) {
			if (thread.isAlive() && thread.getName().equals(nameThread.toString())) return; // check if thread task already run.
		}
		startTimerTask();

		int indexDeadThread = -1;
		for(Thread thread : listTheads) {
			if (!thread.isAlive()) {
				indexDeadThread = listTheads.indexOf(thread);
				break;
			}
		}
		if (indexDeadThread != -1) {
			listTheads.remove(indexDeadThread);
		} 
		listTheads.add(getTaskThread());
		startTask();
	}
	protected Component setWindowDisable(String text) {
		text = (text.length() <= 11) ? text : text.substring(0,11);
		Util.setActivitySubComponents(MainRunWindow.getInstance().getContentPane(), false);
			return MainRunWindow.addPanelToGlassPanel("Connection to: "+text);
	}
	protected void setWindowEnable(Component panel) {
		MainRunWindow.hideGlassPanel(panel);
		for (Thread thread : listTheads) {
			if (thread != getTaskThread()) {
				if (thread.isAlive()) {
					return;
				}
			}
		}
		Util.setActivitySubComponents(MainRunWindow.getInstance().getContentPane(), true);
	}
	@Override
	public void timerTask() {
		String ipDataBase = MyProperties.getProperty(propertiesConnection);
		panelGlass1 = setWindowDisable(ipDataBase);
	}
	@Override
	public void catchTaskThread(Exception e) {
		LOg.logToFile(e);
		try {
			getTimerTask().cancel();//
		} finally {
			setWindowEnable(panelGlass1);
			DialogWindows.dialogWindowError(e);
		}	
	}
	@Override
	public void cancelTimerTask() {
    	try {
			getTimerTask().cancel();//
		} finally {
			setWindowEnable(panelGlass1);
		}
	}
	protected abstract Object taskThread() throws Exception;
}
