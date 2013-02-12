package org.snowink.tka;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import org.snowink.tka.mino.Mino;



public class VsPanel extends JPanel {

	public int block_size = 10;
	public int small_block_size = 8;
	
	public VsPanel() {
		
		
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		Field my_field = DataPool.vs_field_1;
		Field your_field = DataPool.vs_field_2;
		
		// 基点
		Point cp_1 = new Point(70, 60);
		Point cp_2 = new Point(280, 60);
		
		// 枠
		g.setColor(Color.BLACK);
		g.drawRect(cp_1.x - 1, cp_1.y - 1, my_field.getBan().length * block_size + 1, my_field.getViewLine() * block_size + 1);
		g.drawRect(cp_2.x - 1, cp_2.y - 1, your_field.getBan().length * block_size + 1, your_field.getViewLine() * block_size + 1);
		
//		g.fillRect(cp_1.x - 1, cp_1.y - 1, my_field.getBlocks().length * block_size + 2, my_field.getViewLine() * block_size + 2);
//		g.fillRect(cp_2.x - 1, cp_2.y - 1, your_field.getBlocks().length * block_size + 2, your_field.getViewLine() * block_size + 2);
		
		// フィールド
		DrawTools.drawField(g, my_field, block_size, cp_1);				
		DrawTools.drawField(g, your_field, block_size, cp_2);		
		
		// 影
		DrawTools.drawShadow(g, my_field, block_size, cp_1);				
		DrawTools.drawShadow(g, your_field, block_size, cp_2);		
		
		// なうみの	
		DrawTools.drawMino(g, my_field, block_size, cp_1);				
		DrawTools.drawMino(g, your_field, block_size, cp_2);		
		
		// HOLD
		DrawTools.drawHold(g, my_field, small_block_size, new Point(cp_1.x - 40, cp_1.y));
		DrawTools.drawHold(g, your_field, small_block_size, new Point(cp_2.x - 40, cp_2.y));
		
		// NEXT
		DrawTools.drawNext(g, my_field, small_block_size, new Point(cp_1.x + 28, cp_1.y - 40), small_block_size);
		DrawTools.drawNext(g, your_field, small_block_size, new Point(cp_2.x + 28, cp_2.y - 40), small_block_size);
		
		// RIZE
		DrawTools.drawNextRize(g, my_field, 12, new Point(cp_1.x, cp_1.y + 220));
		DrawTools.drawNextRize(g, your_field, 12, new Point(cp_2.x, cp_2.y + 220));
		
		// Message
		DrawTools.drawMessage(g, my_field, 12, new Point(cp_1.x, cp_1.y + 240));
		DrawTools.drawMessage(g, your_field, 12, new Point(cp_2.x, cp_2.y + 240));
		
	}

}
