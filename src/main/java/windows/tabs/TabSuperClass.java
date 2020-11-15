package windows.tabs;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Vector;

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
import util.DisplayConnectionDelay.TypeConnection;
import util.MyProperties;
import util.ServiceWindow;
import util.my_components.MyHoverButton;
import windows.SettingsWindow;

public class TabSuperClass extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static Vector<CategoryAndId> listCategoryAndCodes = new Vector<>();
	protected static Vector<String> listNamesFoldersProject = new Vector<>();
	
	protected static ActionListener refreshService;
	private static ComponentAdapter adapterDataBase;
	private static ComponentAdapter adapterRepositories;
	protected MyHoverButton paramButton;

	
	public TabSuperClass() {
		paramButton = new MyHoverButton();
		//
		adapterDataBase = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				listCategoryAndCodes.clear();
				if(!ConnectionMSSQL.isGoodLastsConnection) return;
				
				new DisplayConnectionDelay(TypeConnection.BASE_CONNECTION,"ipDataBase", 400L) {
					
					@Override
					protected Object taskThread() throws Exception {
				    		refreshCategory();
				    		if(!ParamsRelatedData.isExistTableParams()) paramButton.setVisible(false);
				    		else paramButton.setVisible(true);
						return null;
					}
				};
			}
		};
		adapterRepositories = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				listNamesFoldersProject.clear();
				
				boolean isSetRepo = SettingsWindow.enableAddToRepositoriesGetSaveSelected();
				if(isSetRepo) {
					new DisplayConnectionDelay(TypeConnection.REPO_CONNECTION,"repPathDir", 400L) {
						
						@Override
						protected Object taskThread() throws Exception {
							refresListNameProjects();//task...
							return null;
						}
					};
				}					
			}
		};
		this.addComponentListener(adapterRepositories);
		this.addComponentListener(adapterDataBase);
		
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
		System.out.println("listNamesFoldersProject.clear()");
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
	protected void checkConnection() throws Exception {
		checkBaseConnection();
		checkRepoConnection();
	}
	public ComponentAdapter getAdapterDataBase() {
		return adapterDataBase;
	}
	public ComponentAdapter getAdapterRepositories() {
		return adapterRepositories;
	}
}
