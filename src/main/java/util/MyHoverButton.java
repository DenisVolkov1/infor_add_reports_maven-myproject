package util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;

public class MyHoverButton extends JButton {
	boolean isWhite = false;
	{
		setFocusPainted(false);
		setBackground(new Color(204,227,249));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isWhite) setBackground(new Color(200,230,230));
				else setBackground(new Color(174,197,219));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (isWhite) setEmptyHover();
				else setBackground(new Color(204,227,249));
			}
		});
	}
	public MyHoverButton() {
	}
	
	public void setEmptyHover() {
		isWhite = true;
		setBackground(new Color(253, 253, 253));
	}
	public void setStandartHover() {
		isWhite = false;
		setBackground(new Color(204,227,249));
	} 
	
	public MyHoverButton(String text) {
		super(text);
	}
	public MyHoverButton(Icon icon) {
		super(icon);
	}
	
	
}
