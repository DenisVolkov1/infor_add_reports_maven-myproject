package windows;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPanel;

import org.omg.PortableServer.AdapterActivator;

import database.CategoryAndCode;
import database.CategoryRelatedData;
import database.ConnectionMSSQL;
import database.ReportRelatedData;
import exception.InfoException;
import files_repository.FilesRepository;
import log.LOg;
import util.DialogWindows;
import util.MyProperties;
import util.ServiceWindow;

public class TabSuperClass extends JPanel {
	
	private static final long serialVersionUID = 1L;
	protected static Vector<CategoryAndCode> listCategoryAndCodes = new Vector<>();
	protected static Vector<String> listNamesFoldersProject = new Vector<>();
	protected static ActionListener refreshService;
	private ComponentAdapter adapterCategories;
	private ComponentAdapter adapterListProjectsNames;
	
	protected TabSuperClass() {
		
		adapterCategories = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				if(!ConnectionMSSQL.isGoodLastsConnection) {
					listCategoryAndCodes.clear();
					return;
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					refreshCategory();
				setCursor(null);
			}
		};
		adapterListProjectsNames = new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					if(!FilesRepository.isOpenRepo()) {
						listNamesFoldersProject.clear();
						return;
					}
				} catch (Exception e1) {
					LOg.logToFile(e1);
					return;
				}
				refresListNameProjects();
			}
		};
		
		this.addComponentListener(adapterCategories);
		
		refreshService = new ActionListener() { 
			@Override									//BranchCache //Infor SCE Reports Server scprd-reports1
			public void actionPerformed(ActionEvent e) {
				try {	
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						ServiceWindow.stopService("Infor SCE Reports Server scprd-reports1");
						ServiceWindow.startService("Infor SCE Reports Server scprd-reports1");
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
		}
	}
	public void refreshCategory() {
		listCategoryAndCodes.clear();
		Vector<CategoryAndCode> categoriesMap;
		try {
			categoriesMap = CategoryRelatedData.getCategories();
			listCategoryAndCodes.addAll(categoriesMap);
		} catch (Exception e) {
			DialogWindows.dialogWindowError(e);
				LOg.logToFile(e);
		}
	}
	public ComponentAdapter getCategoriesAdapter() {
		return adapterCategories;
	}
	public ComponentAdapter getListProjectsNamesAdapter() {
		return adapterListProjectsNames;
	}
}
