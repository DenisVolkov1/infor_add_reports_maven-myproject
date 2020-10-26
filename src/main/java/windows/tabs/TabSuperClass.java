package windows.tabs;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import database.CategoryRelatedData;
import database.ConnectionMSSQL;
import database.ParamsRelatedData;
import exception.InfoException;
import files_repository.FilesRepository;
import log.LOg;
import util.CategoryAndId;
import util.DialogWindows;
import util.MyProperties;
import util.NewTaskDelay;
import util.ServiceWindow;
import util.Util;
import util.my_components.MyHoverButton;
import windows.MainRunWindow;
import windows.SettingsWindow;

public class TabSuperClass extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static Vector<CategoryAndId> listCategoryAndCodes = new Vector<>();
	protected static Vector<String> listNamesFoldersProject = new Vector<>();
	protected static ActionListener refreshService;
	private ComponentAdapter adapterCategories;
	private ComponentAdapter adapterListProjectsNames;
	//private static Thread connectionToRepoThread;
	//private static Thread connectionToBaseThread;
	protected MyHoverButton paramButton;
	private Component panelGlass1;
	private Component panelGlass2;
	private NewTaskDelay connectionToBaseThread;
	private NewTaskDelay connectionToRepoThread;
	
	public TabSuperClass() {
		paramButton = new MyHoverButton("New Param");
		
		adapterCategories = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				
				if(!ConnectionMSSQL.isGoodLastsConnection) {
					listCategoryAndCodes.clear();
					return;
				}
				//if (connectionToBaseThread != null && connectionToBaseThread.taskThread.isAlive()) return;// check if thread connection task already run
				connectionToBaseThread = new NewTaskDelay("baseThread",200) {
					@Override
					public void timerTask() {
						String ipDataBase = MyProperties.getProperty("ipDataBase");
			    		panelGlass1 = setWindowDisable(ipDataBase);
					}
					@Override
					public void taskThread() throws Exception {
			    		System.out.println(Thread.currentThread().getName());
			    		refreshCategory();//task...
			    		System.out.println("newParamButton task");
			    		if(!ParamsRelatedData.isExistTableParams()) paramButton.setVisible(false);
			    		else paramButton.setVisible(true);
					}
					@Override
					public void catchTaskThread(Exception e) {
						System.out.println(Thread.currentThread().getName());
						LOg.logToFile(e);
						try {
			    			taskShowGlassPanel.cancel();// 
			    		} finally {
							DialogWindows.dialogWindowError(e);
							setWindowEnable(panelGlass1);
						}	
					}
					@Override
					public void cancelTimerTask() {
				    	try {
			    			taskShowGlassPanel.cancel();// 
			    		} finally {
			    			setWindowEnable(panelGlass1);
						}					
					}
				};
				
//				System.out.println("connectionToBaseThread");
//				java.util.Timer timer = new java.util.Timer();
//				final TimerTask taskShowGlassPanel = new TimerTask() {
//					public void run() {
//							String ipDataBase = MyProperties.getProperty("ipDataBase");
//				    		panelGlass1 = setWindowDisable(ipDataBase);
//					}
//				};
//				timer.schedule( taskShowGlassPanel, 200 );// run if task undone for 0.2 seconds.
//				
//				connectionToBaseThread = new Thread(new Runnable(){
//				    public void run() {
//					      // new Thread 
//					    	try {		
//					    		System.out.println(Thread.currentThread().getName());
//					    		refreshCategory();//task...
//					    		System.out.println("newParamButton task");
//					    		if(!ParamsRelatedData.isExistTableParams()) paramButton.setVisible(false);
//					    		else paramButton.setVisible(true);
//								//
//							    SwingUtilities.invokeLater(new Runnable(){
//							    	public void run() {
//							    		try {
//							    			taskShowGlassPanel.cancel();// 
//							    		} finally {
//							    			setWindowEnable(panelGlass1);
//										}					
//							    	}
//							    });
//							} catch (Exception e) {
//								System.out.println(Thread.currentThread().getName());
//								LOg.logToFile(e);
//								try {
//					    			taskShowGlassPanel.cancel();// 
//					    		} finally {
//									DialogWindows.dialogWindowError(e);
//									setWindowEnable(panelGlass1);
//								}	
//							}	
//					  }
//					},"baseThread");// name thread
//				connectionToBaseThread.start();
			}
		};
		adapterListProjectsNames = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				
				//if (connectionToRepoThread != null && connectionToRepoThread.taskThread.isAlive()) return;// check if thread connection task already run
				connectionToRepoThread = new NewTaskDelay("repoThread",200) {
					@Override
					public void timerTask() {
						String repPathDir = MyProperties.getProperty("repPathDir");
			    		panelGlass2 = setWindowDisable(repPathDir);
					}
					@Override
					public void taskThread() throws Exception {
						System.out.println(Thread.currentThread().getName());
						if(FilesRepository.isOpenRepo()) refresListNameProjects();//task...
					}
					@Override
					public void catchTaskThread(Exception e) {
						System.out.println(Thread.currentThread().getName());
						LOg.logToFile(e);
						try {
			    			taskShowGlassPanel.cancel();// 
			    		} finally {
							DialogWindows.dialogWindowError(e);
							setWindowEnable(panelGlass2);
						}	
					}
					@Override
					public void cancelTimerTask() {
				    	try {
			    			taskShowGlassPanel.cancel();// 
			    		} finally {
			    			setWindowEnable(panelGlass2);
						}					
					}
				};
				
//				System.out.println("connectionToRepoThread");
//				boolean isSetRepo = SettingsWindow.enableAddToRepositoriesGetSaveSelected();
//				if(isSetRepo) {
//					java.util.Timer timer = new java.util.Timer();
//					final TimerTask taskShowGlassPanel = new TimerTask() {
//						public void run() {
//							String repPathDir = MyProperties.getProperty("repPathDir");
//					    	panelGlass2 = setWindowDisable(repPathDir);
//						}
//					};
//					timer.schedule( taskShowGlassPanel, 200 );// run if task undone for 0.2 seconds.
//					//
//					connectionToRepoThread = new Thread(new Runnable(){
//					    public void run() {
//					      // new Thread 
//					    	try {						    		
//					    		if(FilesRepository.isOpenRepo()) refresListNameProjects();//task...
//								//
//							    SwingUtilities.invokeLater(new Runnable(){
//							    	public void run() {
//							    		try {
//							    			taskShowGlassPanel.cancel();// 
//							    		} finally {
//							    			setWindowEnable(panelGlass2);
//										}					
//							    	}
//							    });
//							} catch (Exception e) {
//								LOg.logToFile(e);
//								try {
//					    			taskShowGlassPanel.cancel();// 
//					    			DialogWindows.dialogWindowError(e);
//					    		} finally {
//					    			setWindowEnable(panelGlass2);
//								}	
//								
//							}	
//					  }
//					},"repoThread");//name thread
//					connectionToRepoThread.start();
//				}					
			}
		};
		this.addComponentListener(adapterListProjectsNames);
		this.addComponentListener(adapterCategories);
		
		refreshService = new ActionListener() { 
			@Override									
			public void actionPerformed(ActionEvent e) {
				//Infor SCE Reports Server scprd-reports1 - default
				String nameServiceReport = MyProperties.getProperty("nameServiceReport"); 
				try {	
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						ServiceWindow.stopService(nameServiceReport);
						ServiceWindow.startService(nameServiceReport);
				} catch (InfoException e1) {
					DialogWindows.dialogWindowError(e1);			
				} catch (Exception e2) {
					DialogWindows.dialogWindowError(e2);
						LOg.logToFile(e2);
				} finally {setCursor(null);}
			}
		};
	}
	protected void refresListNameProjects() {
		listNamesFoldersProject.clear();
		Vector<String> listNameProject;
		try {
			listNameProject = FilesRepository.listNamesFolderProject();
			listNamesFoldersProject.addAll(listNameProject);
		} catch (Exception e) {
			LOg.logToFile(e);
			DialogWindows.dialogWindowError(e);
		}
	}
	public void refreshCategory() throws ClassNotFoundException, SQLException {
		listCategoryAndCodes.clear();
		Vector<CategoryAndId> categoriesMap;
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
			categoriesMap = CategoryRelatedData.getCategories();
			listCategoryAndCodes.addAll(categoriesMap);
	
		} finally {
			setCursor(null);
		}
	}
	public ComponentAdapter getCategoriesAdapter() {
		return adapterCategories;
	}
	public ComponentAdapter getListProjectsNamesAdapter() {
		return adapterListProjectsNames;
	}
//	private void setWindowEnable(Component panel) {
//		System.out.println(Thread.currentThread().getName());
//		MainRunWindow.hideGlassPanel(panel);
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
//	}
//	private Component setWindowDisable(String text) {
//		text = (text.length() <= 10) ? text : text.substring(0,10);
//		Util.setEnableRec(MainRunWindow.getInstance().getContentPane(), false);
//    		return MainRunWindow.addPanelToGlassPanel("Connection to: "+text);
//	}
}
