package util;

import javax.swing.JOptionPane;

import windows.MainRunWindow;

public class DialogWindows {
	
	private DialogWindows() {}

	public static void dialogWindowError(String messageError) {
		 JOptionPane.showMessageDialog(MainRunWindow.getInstance(),
				 DialogWindows.trimLongMessage(messageError),
				         "Error",
				     JOptionPane.ERROR_MESSAGE);
	}
	public static void dialogWindowError(String messageErrorHeader, Exception e) {
		 JOptionPane.showMessageDialog(MainRunWindow.getInstance(),
				 DialogWindows.trimLongMessage(e.getMessage()),
			  messageErrorHeader,
				     JOptionPane.ERROR_MESSAGE);
	}
	public static void dialogWindowError(Exception e) {
		String pattern = "([à-ÿÀ-ßa-zA-Z'])(\\.)([' à-ÿÀ-ßa-zA-Z&&[^wrz]])";
		if (e.getMessage() == null) { e.printStackTrace(); return; }
		 JOptionPane.showMessageDialog(MainRunWindow.getInstance(),
				 DialogWindows.trimLongMessage(e.getMessage()).replaceAll(pattern,"$1\\.\n$3"),
			e.getClass().getName(),
				     JOptionPane.ERROR_MESSAGE);
	}
	public static void dialogWindowWarning(String message) {
		 JOptionPane.showMessageDialog(MainRunWindow.getInstance(),
				 DialogWindows.trimLongMessage(message),
				               "",
				     JOptionPane.WARNING_MESSAGE);
	}
	private static String trimLongMessage(String message) {
		if (message.length() >= 65) {
			return message.substring(0,43)+"...";
		} else return message;
	}
	/**
	 * @param messageQuestion 
	 * @param title
	 * @return 0 - Yes , 1 - No
	 */
	public static int dialogWindowConfirm(String messageQuestion) {
	    int res =  JOptionPane.showConfirmDialog(MainRunWindow.getInstance(),
	    		messageQuestion,
	    		"",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
		return res;
	}
}
