package util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import javax.swing.SwingUtilities;
import windows.MainRunWindow;

public abstract class NewTaskDelay {
	
	private Thread taskThread;
	protected TimerTask timerTask;
	private static List<Thread> listTheads = new ArrayList<Thread>();

	public NewTaskDelay(String nameThread, long delay) {
		for(Thread thread : listTheads) {
			if (thread.isAlive() && thread.getName().equals(nameThread)) return; // check if thread task already run.
		}
		//System.out.println(nameThread+" delay="+delay);
		java.util.Timer timer = new java.util.Timer();
		timerTask = new TimerTask() {
			public void run() {
				timerTask();
			}
		};
		timer.schedule(timerTask, delay);// run if task undone for delay millisecond.
		
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
					    afterTask();
			    	} catch (Exception e) {
			    		catchTaskThread(e);
					}	
			  }
			},nameThread);// name thread baseThread
		//
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
		taskThread.start();
		
	}
	protected void afterTask() {
		// if needs
		
	}
	protected Component setWindowDisable(String text) {
		text = (text.length() <= 10) ? text : text.substring(0,10);
		Util.setEnableRec(MainRunWindow.getInstance().getContentPane(), false);
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
		Util.setEnableRec(MainRunWindow.getInstance().getContentPane(), true);
	}
	
	public abstract void timerTask();
	public abstract void taskThread() throws Exception;
	public abstract void cancelTimerTask();
	public abstract void catchTaskThread(Exception e);
}
