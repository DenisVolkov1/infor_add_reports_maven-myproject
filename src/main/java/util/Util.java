package util;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JLabel;

import exception.InfoException;

public class Util {
	
	private Util() {}
	
	public static void changeColorErrDataSource(String odaURL, JLabel ipDataSrcLabel) {
		if (!odaURL.matches(".*localhost.*")) ipDataSrcLabel.setForeground(Color.RED);
		else ipDataSrcLabel.setForeground(Color.GRAY);
	}
	
	public static void checkWarPathAndReportParh(Path reportFile) throws InfoException {
	
		String warPathOrigin = MyProperties.getProperty("pathArchiveWar");
		Path originalPathArchive = Paths.get(warPathOrigin);
	
		if (reportFile.equals(originalPathArchive)) throw new InfoException("War archive and file report in one and the same dir."); 
		

	}
	
}
