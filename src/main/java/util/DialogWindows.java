package util;

import javax.swing.JOptionPane;

import windows.MainRunWindow;

public class DialogWindows {
	
	private DialogWindows() {}

	public static void dialogWindowError(String messageError) {
		 JOptionPane.showMessageDialog(MainRunWindow.getInstance(),
				    messageError,
				         "Error",
				     JOptionPane.ERROR_MESSAGE);
	}
	public static void dialogWindowError(String messageErrorHeader, Exception e) {
		 JOptionPane.showMessageDialog(MainRunWindow.getInstance(),
				  e.getMessage(),
			  messageErrorHeader,
				     JOptionPane.ERROR_MESSAGE);
	}
	public static void dialogWindowError(Exception e) {
		 JOptionPane.showMessageDialog(MainRunWindow.getInstance(),
				    e.getMessage().replaceAll("[à-ÿÀ-ßa-zA-Z]\\.[ à-ÿÀ-ßa-zA-Z&&[^wrz]]", "."+"\r\n"),
			e.getClass().getName(),
				     JOptionPane.ERROR_MESSAGE);
	}
	public static void dialogWindowWarning(String message) {
		 JOptionPane.showMessageDialog(MainRunWindow.getInstance(),
				          message,
				               "",
				     JOptionPane.WARNING_MESSAGE);
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
