package windows.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import windows.MainRunWindow;

public class ProgressDialog {
	  private final JFrame frame = new JFrame();
	  private final JDialog dialog = new JDialog(frame, "���������� ������", false);
	  private final JProgressBar progressBar = new JProgressBar();
	  /** ����������� progreeBar � �������, � ������� �� ���������� */
	  public ProgressDialog(){
		frame.setUndecorated(true);
	    // ���� �� �� �����, ������� ������� ������ ��������, 
	    // ������ progressBar � ���� "����������� �������", � �� "��������".
	    progressBar.setIndeterminate(true);
	    // ��� �������, ������ ����.
	    progressBar.setForeground( new Color(210,105,030));
	    // ��� �������, ������ ���������� �����. � ������ ������, progressBar ����� ����� ������,
	    // ������� ����������� � ������ ProgressUI.
	    progressBar.setUI(new ProgressUI());
	    // ������� ����� �� �������, ����� ��� ����� ������ progressBar ��� ������ ���������� �����.
	    dialog.setUndecorated(true);
	    // ��������� ������������ progressBar � ������.
	    dialog.getContentPane().add(progressBar);
	    dialog.pack();
	    dialog.setDefaultCloseOperation(0);
	    // ������ ��������� �������.
	    Toolkit kit = dialog.getToolkit();
	    GraphicsEnvironment ge = GraphicsEnvironment. getLocalGraphicsEnvironment();
	    GraphicsDevice[] gs = ge.getScreenDevices();
	    Insets in = kit.getScreenInsets(gs[0].getDefaultConfiguration());
	    Dimension d = kit.getScreenSize();
	    int max_width = (d.width - in.left - in.right);
	    int max_height = (d.height - in.top - in.bottom);
	    dialog.setLocation((int) (max_width - dialog.getWidth()) / 2, (int) (max_height - dialog.getHeight() ) / 2);
	    // ���������� ������ � progressBar
	    dialog.setVisible(true);
	    progressBar.setVisible(true);
	    dialog.setAlwaysOnTop(true);
	  }
	  /** �����, ������������ ������ */
	  public void showDialog(){
	    dialog.setVisible(true);
	    dialog.setAlwaysOnTop(true);
	  }
	  /** �����, ����������� ������ */
	  public void closeDialog(){
	    if (dialog.isVisible()){
	      dialog.getContentPane().remove(progressBar);
	      dialog.getContentPane().validate();
	      dialog.setVisible(false);
	    }
	  }
	  /** �����, �������� ������� ��� progressBar */
	  public static class ProgressUI extends BasicProgressBarUI {
	    private Rectangle r = new Rectangle();
	    protected void paintIndeterminate(Graphics g, JComponent c) {
	      Graphics2D g2d = (Graphics2D) g;
	      g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	      r = getBox(r);
	      g.setColor(progressBar.getForeground());
	      g.fillOval(r.x, r.y, r.height, r.height);
	    }
	  }
	}

