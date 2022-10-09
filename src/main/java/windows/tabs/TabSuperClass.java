package windows.tabs;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.Phaser;

import javax.swing.JPanel;

import database.CategoryRelatedData;
import database.ConnectionMSSQL;
import database.ParamsRelatedData;
import exception.InfoException;
import files_repository.FilesRepository;
import jcifs.smb.SmbFile;
import log.LOg;
import util.CategoryAndId;
import util.DialogWindows;
import util.DisplayConnectionDelay;
import util.DisplayWaitingForWorkingTask;
import util.DisplayConnectionDelay.TypeConnection;
import util.MyProperties;
import util.ServiceWindow;
import util.my_components.MyHoverButton;
import war.WarArchive;
import windows.MainRunWindow;
import windows.SettingsWindow;
import windows.tabs.add.TabAddReport;
import windows.tabs.update.TabUpdateReport;

public class TabSuperClass extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static Vector<CategoryAndId> listCategoryAndCodes = new Vector<>();
	protected static Vector<String> listNamesFoldersProject = new Vector<>();
	
	protected static ActionListener refreshService;
	private static ComponentAdapter adapterForWaitingPanels;
	
	
	
	protected MyHoverButton paramButton;

	
	public TabSuperClass() {
		//paramButton = new MyHoverButton();
		//
		adapterForWaitingPanels = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				listCategoryAndCodes.clear();
				if(!ConnectionMSSQL.isGoodLastsConnection) return;
				
				new DisplayConnectionDelay(TypeConnection.BASE_CONNECTION,"ipDataBase", 1000L) {
					
					@Override
					protected Object taskThread() throws Exception {
				    		refreshCategory();
				    		if(paramButton != null ) {
					    		if(!ParamsRelatedData.isExistTableParams()) paramButton.setVisible(false);
					    		else paramButton.setVisible(true);
				    		}
						return null;
					}
				};
			}
		};
		// add adapter listeners
		//this.addComponentListener(adapterRepositories);
		this.addComponentListener(adapterForWaitingPanels);
		
		refreshService = new ActionListener() { 
		@Override									
		public void actionPerformed(ActionEvent e) {
			//Infor SCE Reports Server scprd-reports1 - default
			new DisplayWaitingForWorkingTask("<html>Deploying scprd_scereports.war <br> archive please wait.</html>") {
				Phaser phaser = new Phaser(1);// Waiting until scprd_scereports.war.isdeploying file is disappear.
				 final TimerTask uploadCheckerTimerTask = new TimerTask(){
					 
					  {
						 phaser.register();// register our phase
					  }
					 
						//int n = 0;
					  
						File targetWarArchiveFile = Paths.get(MyProperties.getProperty("pathArchiveWar")+"\\scprd_scereports.war.isdeploying").toFile();
						 public void run() {
							 
							 if(!targetWarArchiveFile.exists()) {
								 phaser.arriveAndDeregister();
								 	uploadCheckerTimerTask.cancel();
							 }
						 }
					};
				
				@Override
				public Object taskThread() throws Exception {
					String nameServiceReport = MyProperties.getProperty("nameServiceReport"); 
					try {	
						WarArchive.checkPathArchive();
						ServiceWindow.stopService(nameServiceReport);
							Thread.sleep(800L);// Delay between finish service and start
						ServiceWindow.startService(nameServiceReport);
							Thread.sleep(4000L);// Delay after start
						Timer uploadCheckerTimer = new Timer(true);
						uploadCheckerTimer.scheduleAtFixedRate(uploadCheckerTimerTask, 0, 900L );
						
				        phaser.arriveAndAwaitAdvance();

					} catch (InfoException e1) {
						hideWaitPanel();
						DialogWindows.dialogWindowError(e1);			
					} catch (Exception e2) {
						hideWaitPanel();
						DialogWindows.dialogWindowError(e2);
							LOg.logToFile(e2);
							
					}
				return null;
				}
			};
			

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
	
	protected void checkBaseConnection() throws ClassNotFoundException, SQLException {
		ConnectionMSSQL.getInstanceConneectionJDBC();
	}
	protected void checkRepoConnection() throws IOException {
		boolean isSetRepo = SettingsWindow.enableAddToRepositoriesGetSaveSelected();
		if(isSetRepo) {
			String repPathDir = MyProperties.getProperty("repPathDir");
			SmbFile smbFile = FilesRepository.getSmbFileObject("smb:"+repPathDir+'/');
			smbFile.connect();
		}
	}
//	protected void checkConnection() throws Exception {
//		checkBaseConnection();
//		checkRepoConnection();
//	}
	public ComponentAdapter getAdapterForWaitingPanels() {
		return adapterForWaitingPanels;
	}

}
