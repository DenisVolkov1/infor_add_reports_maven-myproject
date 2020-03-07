package util;

import javax.swing.JTextField;

import exception.InfoException;

public class Verification {
	
	private Verification () {}
	
	public static void checkIvalidFilenamesWindows(JTextField... fields) throws InfoException {
		for (JTextField field : fields) {
			if (field.getText().trim().matches(".*[<>:\"/\\\\|?*].*")) throw new InfoException("Fild contains invalid characters.");
				String invalidFileWindowNames = "CON|PRN|AUX|NUL|COM1|COM2|COM3|COM4|COM5|COM6|COM7|COM8|COM9|LPT1|LPT2|LPT3|LPT4|LPT5|LPT6|LPT7|LPT8|LPT9";
			if (field.getText().trim().matches(invalidFileWindowNames)) throw new InfoException("Names \"CON, PRN, AUX, NUL, COM1,\"..etc are invalid.");
		}
	}

}