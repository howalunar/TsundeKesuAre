package jp.snowink.tsundekesuare;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class MenuPanel extends JPanel {

	
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setFont(new Font("メイリオ", Font.BOLD, 24));
		g.drawString("積んで消すアレ", 50, 100);
		
		
		g.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		
		g.drawString("ﾆｱ", 60, 150);
		g.drawString(MainWindow.menus[(MainWindow.now_menu + MainWindow.menus.length - 1) % MainWindow.menus.length], 80, 130);
		g.drawString(MainWindow.menus[MainWindow.now_menu], 85, 150);
		g.drawString(MainWindow.menus[(MainWindow.now_menu + MainWindow.menus.length + 1) % MainWindow.menus.length], 80, 170);
		
		
		g.drawString("PRESS ENTER KEY", 80, 250);
		
	}
	
	
	
	
	
	
}
