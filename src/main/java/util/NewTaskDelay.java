package util;

import java.util.TimerTask;
import javax.swing.SwingUtilities;

public abstract class NewTaskDelay {
	private long delayTimerTask;
	private Thread taskThread;
	private TimerTask timerTask;
	private java.util.Timer timer;

	//private static List<Thread> listTheads = new ArrayList<Thread>();

	public NewTaskDelay(String nameThread, long delayTimerTask) {
		this.delayTimerTask = delayTimerTask;
		//
//		for(Thread thread : listTheads) {
//			if (thread.isAlive() && thread.getName().equals(nameThread)) return; // check if thread task already run.
//		}
		//System.out.println(nameThread+" delay="+delay);
		timer = new java.util.Timer();
		timerTask = new TimerTask() {
			public void run() {
				timerTask();
			}
		};
		//timer.schedule(timerTask, delay);// run if task undone for delay millisecond.
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
		//System.out.println(listTheads.size());
//		int indexDeadThread = -1;
//		for(Thread thread : listTheads) {
//			if (!thread.isAlive()) {
//				indexDeadThread = listTheads.indexOf(thread);
//				//System.out.println("thread=task");
//				break;
//			}
//		}
//		if (indexDeadThread != -1) {
//			//System.out.println("addReplace");
//			listTheads.remove(indexDeadThread);
//		} 
//		listTheads.add(taskThread);
		//taskThread.start();
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
//	protected Component setWindowDisable(String text) {
//		text = (text.length() <= 10) ? text : text.substring(0,10);
//		Util.setActivitySubComponents(MainRunWindow.getInstance().getContentPane(), false);
//    		return MainRunWindow.addPanelToGlassPanel("Connection to: "+text);
//	}
//	protected void setWindowEnable(Component panel) {
//		//System.out.println(Thread.currentThread().getName());
//		MainRunWindow.hideGlassPanel(panel);
//		for (Thread thread : listTheads) {
//			if (thread != taskThread) {
//				if (thread.isAlive()) {
//					//System.out.println("thread.IsAlive return");
//					return;
//				}
//			}
//		}
//		//System.out.println("EnableWindows");
//		Util.setActivitySubComponents(MainRunWindow.getInstance().getContentPane(), true);
//	}
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
