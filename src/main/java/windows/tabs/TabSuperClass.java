package windows.tabs;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import database.CategoryRelatedData;
import database.ConnectionMSSQL;
import exception.InfoException;
import files_repository.FilesRepository;
import log.LOg;
import util.CategoryAndId;
import util.DialogWindows;
import util.MyProperties;
import util.ServiceWindow;
import windows.MainRunWindow;
import windows.SettingsWindow;

public class TabSuperClass extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static Vector<CategoryAndId> listCategoryAndCodes = new Vector<>();
	protected static Vector<String> listNamesFoldersProject = new Vector<>();
	protected static ActionListener refreshService;
	private ComponentAdapter adapterCategories;
	private ComponentAdapter adapterListProjectsNames;
	
	public TabSuperClass() {
		
		adapterCategories = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				if(!ConnectionMSSQL.isGoodLastsConnection) {
					listCategoryAndCodes.clear();
					return;
				}
				refreshCategory();
			}
		};
		adapterListProjectsNames = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
				    @Override
				    public Void doInBackground() throws InterruptedException {
				    	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						return null;
				    }
				};
				worker.execute();
				//
	
				try {
					boolean isSetRepo = SettingsWindow.enableAddToRepositoriesGetSaveSelected();
					if(isSetRepo) {
						if (FilesRepository.isOpenRepo()) refresListNameProjects();
					}
				} catch (Exception e1) {
					LOg.logToFile(e1);
				} finally {
					setCursor(null);
		
				}
			
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
	public void refreshCategory() {
		listCategoryAndCodes.clear();
		Vector<CategoryAndId> categoriesMap;
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
			categoriesMap = CategoryRelatedData.getCategories();
			listCategoryAndCodes.addAll(categoriesMap);
		} catch (Exception e) {
			DialogWindows.dialogWindowError(e);
				LOg.logToFile(e);
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


}
