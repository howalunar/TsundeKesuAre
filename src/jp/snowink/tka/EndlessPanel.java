package jp.snowink.tka;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import jp.snowink.tka.mino.Mino;


public class EndlessPanel extends JPanel {

	public int block_size = 10;
	public int small_block_size = 8;
	
	public static String[] menus = {"リトライ", "メニューに戻る", "つ△"};
	public static int now_menu = 0;

	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		Field my_field = DataPool.endless_field;
		
		// 基点
		Point cp_1 = new Point(70, 60);
		
		// 枠
		g.setColor(Color.BLACK);
		g.drawRect(cp_1.x - 1, cp_1.y - 1, my_field.getBan().length * block_size + 1, my_field.getViewLine() * block_size + 1);
		
//		g.fillRect(cp_1.x - 1, cp_1.y - 1, my_field.getBlocks().length * block_size + 2, my_field.getViewLine() * block_size + 2);
		
		if (my_field.isGameOver()) {
			g.drawString("ﾆｱ", 60, 150);
			g.drawString(menus[(now_menu + menus.length - 1) % menus.length], 80, 130);
			g.drawString(menus[now_menu], 85, 150);
			g.drawString(menus[(now_menu + menus.length + 1) % menus.length], 80, 170);
		}
		else {
			// フィールド
			DrawTools.drawField(g, my_field, block_size, cp_1);				
		
			// 影
			DrawTools.drawShadow(g, my_field, block_size, cp_1);				
		
			// なうみの	
			DrawTools.drawMino(g, my_field, block_size, cp_1);				
		
			// HOLD
			DrawTools.drawHold(g, my_field, small_block_size, new Point(cp_1.x - 40, cp_1.y));
		
			// NEXT
			DrawTools.drawNext(g, my_field, small_block_size, new Point(cp_1.x + 28, cp_1.y - 40), small_block_size);
		
			// Message
			DrawTools.drawMessage(g, my_field, 12, new Point(cp_1.x, cp_1.y + 240));
		
			// TIME
			DrawTools.drawTime(g, my_field, 12, new Point(240, 40));
		}
		
	}
		
}
