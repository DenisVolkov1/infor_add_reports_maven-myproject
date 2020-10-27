package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import database.ParamsRelatedData;
import exception.InfoException;
import log.LOg;
import windows.MainRunWindow;

public class Util {
	private static HashMap<Component, Boolean> saveActivityComponents = new HashMap<Component, Boolean>();
	
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
	
	/** Disable or Enable all sub Component. 
	 * 
	 * @param container - Upper Container
	 * @param b - enable(true) or Disable(false)
	 * */
	public static void setActivitySubComponents(Component container, boolean b) {
		//
		if (b) {
			//container.setEnabled(saveActivityComponents.get(container) != null ? saveActivityComponents.get(container) : false);
			container.setEnabled(saveActivityComponents.get(container));
	
	        Component[] components= ((Container) container).getComponents();
	        for (int i = 0; i < components.length; i++) {
	            setActivitySubComponents(components[i], true);
	        }
	        saveActivityComponents.clear();
		} else {
			saveActivityComponents.put(container, container.isEnabled());
			container.setEnabled(false);

	        Component[] components= ((Container) container).getComponents();
	        for (int i = 0; i < components.length; i++) {
	            setActivitySubComponents(components[i], false);
	        }
	        
		}
	}
	
}
