package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.TimerTask;

import javax.swing.JComboBox;
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
	 * @param container - Upper Container
	 * @param b - enable(true) or Disable(false)
	 * */
	static int t = 0;
	static int t1 = 0;
	public static synchronized void setActivitySubComponents(Component container, boolean b) {
		//
		if (b) {
			if (saveActivityComponents.isEmpty()) {
				   System.out.println("saveActivityComponents.EMPTY");
				   return;
			} else {
				t1 = 0;
				setEnabledGETRec(container);
				System.out.println("saveActivityComponents.GET t1="+t1);
				System.out.println();
			}
		} else {
			if (!saveActivityComponents.isEmpty()) {
				   System.out.println("saveActivityComponents.NOT_EMPTY");
				   return;
			} else {
				t = 0;
				setDisabledPUTRec(container);
				System.out.println("saveActivityComponents.PUT t="+t);
				System.out.println();
			}
		}
		container.repaint();
		container.revalidate();
		
	}
	private static void setEnabledGETRec(Component container) {
		//container.setEnabled(saveActivityComponents.get(container) != null ? saveActivityComponents.get(container) : false);
		if (container instanceof JComboBox) {
			container.setVisible(true);
		} else {
			container.setEnabled(saveActivityComponents.get(container));
			saveActivityComponents.remove(container);
		}
		//
		t1++;
        Component[] components= ((Container) container).getComponents();
        for (int i = 0; i < components.length; i++) {
        	setEnabledGETRec(components[i]);
        }
	}
	private static void setDisabledPUTRec(Component container) {
		if (container instanceof JComboBox) {
			container.setVisible(false);
		} else {
			saveActivityComponents.put(container, container.isEnabled());
			container.setEnabled(false);
		}
		t++;
        Component[] components= ((Container) container).getComponents();
        for (int i = 0; i < components.length; i++) {
        	setDisabledPUTRec(components[i]);
        }
	}
}
