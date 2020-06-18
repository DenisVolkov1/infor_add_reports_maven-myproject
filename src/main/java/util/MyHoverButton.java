package util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;

public class MyHoverButton extends JButton {
	private static final Color STANDARD_ENTERED = new Color(174,197,219);
	private static final Color STANDARD_EXITED = new Color(204,227,249);
	private static final Color STANDARD_BACKGROUND = new Color(204,227,249);
	
	private  Color entered = STANDARD_ENTERED;
	private  Color exited = STANDARD_EXITED;
	private  Color background = STANDARD_BACKGROUND;
	
	
	boolean isWhite = false;
	{
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isWhite) setBackground(new Color(200,230,230));
				else setBackground(entered);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (isWhite) setEmptyHover();
				else setBackground(exited);
			}
		});
	}
	public MyHoverButton() {
	}
	public MyHoverButton(String text,Color entered, Color exited) {
		super(text);
		this.entered = entered;
		this.exited = exited;
		this.background = exited;
		setFocusPainted(false);
		setBackground(background);
	}
	
	public void setEmptyHover() {
		isWhite = true;
		setBackground(new Color(253, 253, 253));
	}
	public void setStandartHover() {
		isWhite = false;
		setBackground(background);
	} 
	
	public MyHoverButton(String text) {
		super(text);
		setFocusPainted(false);
		setBackground(background);
	}
	public MyHoverButton(Icon icon) {
		super(icon);
		setFocusPainted(false);
		setBackground(background);
	}
	
	
}
