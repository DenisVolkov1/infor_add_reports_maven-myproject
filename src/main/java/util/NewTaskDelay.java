package util;

import java.util.TimerTask;
import javax.swing.SwingUtilities;

public abstract class NewTaskDelay {
	private long delayTimerTask;
	private Thread taskThread;
	private TimerTask timerTask;
	private java.util.Timer timer;


	public NewTaskDelay(String nameThread, long delayTimerTask) {
		this.delayTimerTask = delayTimerTask;

		timer = new java.util.Timer();
		timerTask = new TimerTask() {
			public void run() {
				timerTask();
			}
		};
	
		taskThread = new Thread(new Runnable(){
		    public void run() {
			    	try {		
			    		taskThread();//task...
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
			},nameThread);// name thread 
		//

	}
	protected final void startTask() {
		taskThread.start();
	}
	protected final void startTimerTask() {
		timer.schedule(timerTask, delayTimerTask);
	}
	protected void afterTask() {
		// if needs
	}
	protected void catchTaskThread(Exception e) {
		// if needs
	}

	protected abstract void timerTask();
	protected abstract Object taskThread() throws Exception;
	protected abstract void cancelTimerTask();
	
	protected Thread getTaskThread() {
		return taskThread;
	}
	protected TimerTask getTimerTask() {
		return timerTask;
	}
}
