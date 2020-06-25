package util.my_components;

import javax.swing.JTextField;

import exception.InfoException;

public class MyField extends JTextField {
	
	private String nameField;
	/**
	 * 
	 * @param nameField - for error messages
	 */
	public MyField(String nameField ) {
		super();
		this.nameField = nameField;
	}
	/**
	*/
	public String getTextWithCheck() throws InfoException {
		String textField = this.getText().trim();
	// field empty -> Error
		if (textField.isEmpty()) throw new InfoException("Field '"+nameField+"' is empty."); 
	//  field consist incorrect char SQL (') -> Error
		if (textField.matches(".*'.*")) throw new InfoException("Incorrect character (') in field '"+nameField+"'");
	// invalid for Filenames window -> error
		if (textField.matches(".*[<>:\"/\\\\|?*].*")) throw new InfoException("Field '"+nameField+"' contains invalid characters.\n<, >, :, /, \\, |, ?, *");
			String invalidFileWindowNames = "CON|PRN|AUX|NUL|COM1|COM2|COM3|COM4|COM5|COM6|COM7|COM8|COM9|LPT1|LPT2|LPT3|LPT4|LPT5|LPT6|LPT7|LPT8|LPT9";
		if (textField.matches(invalidFileWindowNames)) throw new InfoException("Input in field '"+nameField+"' \"CON, PRN, AUX, NUL, COM1,\"..etc are invalid.");
		
		return textField;
	}
}
