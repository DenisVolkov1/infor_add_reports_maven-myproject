package util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import log.LOg;
import windows.MainRunWindow;

public abstract class DisplayConnectionDelay extends NewTaskDelay {
	
	private  Component panelGlass1;
	private static List<Thread> listTheads = new ArrayList<Thread>();
	private String propertiesConnection;
	
	public DisplayConnectionDelay(String nameThread,String propertiesConnection, long delay) {
		super(nameThread, delay);
		this.propertiesConnection = propertiesConnection;
		
		for(Thread thread : listTheads) {
			if (thread.isAlive() && thread.getName().equals(nameThread)) return; // check if thread task already run.
		}
		startTimerTask();
		//System.out.println(listTheads.size());
		int indexDeadThread = -1;
		for(Thread thread : listTheads) {
			if (!thread.isAlive()) {
				indexDeadThread = listTheads.indexOf(thread);
				//System.out.println("thread=task");
				break;
			}
		}
		if (indexDeadThread != -1) {
			//System.out.println("addReplace");
			listTheads.remove(indexDeadThread);
		} 
		listTheads.add(taskThread);
		startTask();
		
	}
	protected Component setWindowDisable(String text) {
		text = (text.length() <= 10) ? text : text.substring(0,10);
		Util.setActivitySubComponents(MainRunWindow.getInstance().getContentPane(), false);
			return MainRunWindow.addPanelToGlassPanel("Connection to: "+text);
	}
	protected void setWindowEnable(Component panel) {
		//System.out.println(Thread.currentThread().getName());
		MainRunWindow.hideGlassPanel(panel);
		for (Thread thread : listTheads) {
			if (thread != taskThread) {
				if (thread.isAlive()) {
					//System.out.println("thread.IsAlive return");
					return;
				}
			}
		}
		//System.out.println("EnableWindows");
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
			timerTask.cancel();// 
		} finally {
			DialogWindows.dialogWindowError(e);
			setWindowEnable(panelGlass1);
		}	
	}
	@Override
	public void cancelTimerTask() {
    	try {
			timerTask.cancel();// 
		} finally {
			setWindowEnable(panelGlass1);
		}					
	}

	protected abstract Object taskThread() throws Exception;
}
