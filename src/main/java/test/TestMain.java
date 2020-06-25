package test;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import database.CategoryRelatedData;
import database.ConnectionMSSQL;
import database.ParamsRelatedData;
import database.ReportRelatedData;
import files_repository.FilesRepository;
import jcifs.smb.SmbFile;
import parce_rptdesign.ParamFromRptDesign;
import parce_rptdesign.ReadXML;
import util.MyProperties;
import war.WarArchive;

public class TestMain {
	
	public static void main(String[] args) {}
	
	public static final void testMain() throws Exception {
		System.out.println("Category :");
		for (Iterator iterator = CategoryRelatedData.getCategories().iterator(); iterator.hasNext();) {
					System.out.println("  "+iterator.next()); 
		}
		System.out.println("-----------------------------------------------");
		System.out.println("Max category_id: " + CategoryRelatedData.getMaxCategory());
		System.out.println("-----------------------------------------------");
		System.out.println("Get list of category names :");
		for (Iterator iterator = CategoryRelatedData.getListOfCategoryNames().iterator(); iterator.hasNext();) {
			System.out.println("  "+iterator.next()); 
		}
		System.out.println("-----------------------------------------------");
		System.out.println("List name reports and translations (20 category)");
		for (Iterator iterator = ReportRelatedData.getListOfReportNamesAndTranslation(20).iterator(); iterator.hasNext();) {
			System.out.println("  "+iterator.next()); 
		}
		System.out.println("-----------------------------------------------");
		System.out.println("List name reports (20 category)");
		for (Iterator iterator = ReportRelatedData.getListOfReportNames(20).iterator(); iterator.hasNext();) {
			System.out.println("  "+iterator.next()); 
		}
		System.out.println("List file name reports  (fileReport ):");
		for (Iterator iterator = ReportRelatedData.getListOfReportNames("rep_example_1.rptdesign").iterator(); iterator.hasNext();) {
			System.out.println("  "+(String)iterator.next()); 
		}//rep_example_1.rptdesign
		System.out.println("-----------------------------------------------");
		System.out.println("Report RPT_ID: " + ReportRelatedData.getRPT_ID("Длительность выполнения задачи", 21));
		System.out.println("-----------------------------------------------");
		System.out.println("File path report: " + ReportRelatedData.getReportFilePath("Длительность выполнения задачи", 20));
		System.out.println("-----------------------------------------------");
		System.out.println("List file name reports (idCategory):");
		for (Iterator iterator = WarArchive.getListOfReportFilesNames().iterator(); iterator.hasNext();) {
			System.out.println("  "+(String)iterator.next()); 
		}
		System.out.println("-----------------------------------------------");
		String[] strings = ReportRelatedData.getReportFields("Скорость отгрузки товара со склада", 20);
		System.out.println("Filds report: ");
		for(String s : strings) {
			System.out.println("    "+s);
		}
		System.out.println("-----------------------------------------------");
		System.out.println("List param nam s (RPT_ID 11111111)");
		System.out.println(ParamsRelatedData.getListOfParamName("11111111"));
		System.out.println("-----------------------------------------------");
		System.out.println("List param DateBase (RPT_ID 11111111)");
		System.out.println(ParamsRelatedData.getListOfParam("11111111"));
		System.out.println("-----------------------------------------------");
	
		
		/*
		 * System.out.println("-----------------------------------------------");
		 * System.out.println("List names Folder Project"); for (Iterator iterator
		 * =FilesRepository.listNamesFolderProject().iterator(); iterator.hasNext();) {
		 * System.out.println("  "+(String)iterator.next()); }
		 */
		/*
		 * System.out.println("-----------------------------------------------");
		 * System.out.println("isOpenRepo"); try {
		 * System.out.println(FilesRepository.isOpenRepo());
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */	
		//FilesRepository.sendFilesToStorage("2.4 Простой отчёт","BOYARD/",new File("C:\\UPDATE\\rep\\rep_3_7_otchet_po_rashodam_s_razbivkoi\\rep_3_7_otchet_po_rashodam_s_razbivkoi.rptdesign"));
		
		//new SmbFile("smb:"+MyProperties.getProperty("repPathDir")+'/'+"BOYARD/Ghbdtn</",FilesRepository.getAuthentication()).mkdir();
		
		//invalid character <>:"/\|?*
//		System.out.println("-----------------------------------------------");
//		System.out.println("Exist table PBSRPT_REPORTS_PARAMS : " + ParamsRelatedData.isExistTableParams());
		
		//ParamsRelatedData.insertParam("11111111", "p_OrderID3", "Номер 3", "Text", "").commit();
	
	
//		  for (ParamFromRptDesign p : ReadXML.getListOfParamsFromRptDesign(new File("C:\\FacilityUserActivity.rptdesign"))) {
//			 if (p.getPARAM_TYPE() != null && p.getPARAM_LABEL() != null && p.getPARAM_NAME() != null) ParamsRelatedData.insertParam("27130616", p.getPARAM_NAME(), p.getPARAM_LABEL(), p.getPARAM_TYPE(), p.getPARAM_CONTENTS()).commit(); 
//			 
//			  
//		  }
		
		
		//List<ParamFromRptDesign> params = ReadXML.getListOfParamsFromRptDesign(new File("C:\\reports\\rep_22\\rep_2_2_monitoring_operacii.rptdesign"));
		//ParamsRelatedData.insertParam(params, "Zepoiuyd");

		
		
	}
}
