package util;

import java.awt.Color;

import javax.swing.JLabel;

public class Util {
	
	private Util() {}
	
	public static void changeColorErrDataSource(String odaURL, JLabel ipDataSrcLabel) {
		if (!odaURL.matches(".*localhost.*")) ipDataSrcLabel.setForeground(Color.RED);
		else ipDataSrcLabel.setForeground(Color.GRAY);
	}
	
	

}
