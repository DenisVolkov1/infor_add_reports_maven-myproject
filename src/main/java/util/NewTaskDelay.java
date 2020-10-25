package util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import database.ParamsRelatedData;
import log.LOg;
import windows.MainRunWindow;


public abstract class NewTaskDelay {
	
	public Thread taskThread;
	public TimerTask taskShowGlassPanel;
	private static List<Thread> listTheads = new ArrayList<Thread>();

	public NewTaskDelay(String nameThread, long delay) {
		System.out.println(nameThread+" delay="+delay);
		java.util.Timer timer = new java.util.Timer();
	    taskShowGlassPanel = new TimerTask() {
			public void run() {
				timerTask();
			}
		};
		timer.schedule(taskShowGlassPanel, delay);// run if task undone for 0.2 seconds.
		
		taskThread = new Thread(new Runnable(){
		    public void run() {
			      // new Thread 
			    	try {		
			    		taskThread();
					    SwingUtilities.invokeLater(new Runnable(){
					    	public void run() {
					    		cancelTimerTask();
					    	}
					    });
					    
			    	} catch (Exception e) {
			    		catchTaskThread(e);
					}	
			  }
			},nameThread);// name thread baseThread
		System.out.println(listTheads.size());
		for(Thread thread : listTheads) {
			if (!thread.isAlive()) {
				System.out.println("thread=task");
				thread = taskThread;
				thread.start();
				return;
			}
		}
		System.out.println("add");
		listTheads.add(taskThread);
		taskThread.start();
		
	}
	protected Component setWindowDisable(String text) {
		text = (text.length() <= 10) ? text : text.substring(0,10);
		Util.setEnableRec(MainRunWindow.getInstance().getContentPane(), false);
    		return MainRunWindow.addPanelToGlassPanel("Connection to: "+text);
	}
	protected void setWindowEnable(Component panel) {
		System.out.println(Thread.currentThread().getName());
		MainRunWindow.hideGlassPanel(panel);
		for (Thread thread : listTheads) {
			if (thread != taskThread) {
				System.out.println("thread.IsAlive returne");
				if (thread.isAlive()) return;
			}
		}
		Util.setEnableRec(MainRunWindow.getInstance().getContentPane(), true);
		
//		if(Thread.currentThread().getName().equals("repoThread")) {
//			System.out.println("repoThread");
//			if (connectionToBaseThread.taskThread.isAlive()) return;
//		} else if (Thread.currentThread().getName().equals("baseThread")) {
//			System.out.println("baseThread");
//			if (connectionToRepoThread.taskThread.isAlive()) return;	
//		} else {
//			if ((connectionToRepoThread != null && connectionToRepoThread.taskThread.isAlive()) || (connectionToBaseThread != null && connectionToBaseThread.taskThread.isAlive())) return;	
//		}
//		Util.setEnableRec(MainRunWindow.getInstance().getContentPane(), true);
	}
	public abstract void timerTask();
	public abstract void taskThread() throws Exception;
	public abstract void cancelTimerTask();
	public abstract void catchTaskThread(Exception e);

}
