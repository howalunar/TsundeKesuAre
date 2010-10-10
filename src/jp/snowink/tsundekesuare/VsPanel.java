package jp.snowink.tsundekesuare;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;


public class VsPanel extends JPanel {

	public VsPanel() {
		
		
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		Field my_field = DataPool.vs_field_1;
		Field your_field = DataPool.vs_field_2;
		
		// 基点
		Point cp_1 = new Point(70, 10);
		Point cp_2 = new Point(300, 10);
		
		// 枠
		g.setColor(Color.BLACK);
		g.drawRect(cp_1.x - 1, cp_1.y - 1, my_field.getBlocks().length * 10 + 1, my_field.getViewLine() * 10 + 1);
		g.drawRect(cp_2.x - 1, cp_2.y - 1, your_field.getBlocks().length * 10 + 1, your_field.getViewLine() * 10 + 1);
		
		// フィールド
		Block[][] nullpo = my_field.getBlocks();
		for (int i = 0; i < my_field.getBlocks().length; i++) {
			
			for (int j = 0; j < my_field.getViewLine(); j++) {
				g.setColor(nullpo[i][j].color);
				
				if (nullpo[i][j].ume == true) {
					drawMino(g, new Point(cp_1.x + i * 10, 200 - j * 10), nullpo[i][j].color, nullpo[i][j].color_dark, nullpo[i][j].color_light);
				}
				
			}
			
		}
		
		Block[][] nullpo_2 = your_field.getBlocks();
		for (int i = 0; i < your_field.getBlocks().length; i++) {
			
			for (int j = 0; j < your_field.getViewLine(); j++) {
				g.setColor(nullpo_2[i][j].color);
				
				if (nullpo_2[i][j].ume == true) {
					drawMino(g, new Point(cp_2.x + i * 10, 200 - j * 10), nullpo_2[i][j].color, nullpo_2[i][j].color_dark, nullpo_2[i][j].color_light);
				}
				
			}
			
		}
		
		// ミノ関連
		Mino mino = my_field.getNowMino();
		Block[][] ga = mino.piece[mino.now_piece];
		int x = mino.position.x;
		int y = mino.position.y;
		
		Mino mino_2 = your_field.getNowMino();
		Block[][] ga_2 = mino_2.piece[mino_2.now_piece];
		int x_2 = mino_2.position.x;
		int y_2 = mino_2.position.y;
		
		// 影
		if (mino.position.y != mino.getDropPoint().y) {
			int drop_y = mino.getDropPoint().y;
			for (int i = 0; i < 4; i++) {
			
				for (int j = 0; j < 4; j++) {
				
					if (ga[i][j].ume == true) {
						drawMino(g, new Point(cp_1.x + (x + i) * 10, 200 - (drop_y + j) * 10), mino.shadow_color, mino.shadow_color_dark, mino.shadow_color_light);
					}
				}
			}
		}
		
		if (mino_2.position.y != mino_2.getDropPoint().y) {
			int drop_y = mino_2.getDropPoint().y;
			for (int i = 0; i < 4; i++) {
			
				for (int j = 0; j < 4; j++) {
				
					if (ga_2[i][j].ume == true) {
						drawMino(g, new Point(cp_2.x + (x_2 + i) * 10, 200 - (drop_y + j) * 10), mino_2.shadow_color, mino_2.shadow_color_dark, mino_2.shadow_color_light);
					}
				}
			}
		}
		
		// なうみの	
		for (int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 4; j++) {
				
				if (ga[i][j].ume == true) {
					drawMino(g, new Point(cp_1.x + (x + i) * 10, 200 - (y + j) * 10), mino.color, mino.color_dark, mino.color_light);
				}
				
			}
			
		}
		
		for (int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 4; j++) {
				
				if (ga_2[i][j].ume == true) {
					drawMino(g, new Point(cp_2.x + (x_2 + i) * 10, 200 - (y_2 + j) * 10), mino_2.color, mino_2.color_dark, mino_2.color_light);
				}
				
			}
			
		}
		
		
		// HOLD
		Mino mino2 = my_field.getHoldMino();
		if (mino2 != null) {
			g.setColor(mino2.color);
			Block[][] ga2 = mino2.piece[mino2.now_piece];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (ga2[i][j].ume == true) {
						drawMino(g, new Point(10 + i * 10, 50 - j * 10), mino2.color, mino2.color_dark, mino2.color_light);
					}
				}
			}
		}
		
		// NEXT
		for (int h = 0; h < my_field.getNextMinoVolume(); h++) {
			int next_x = (h == 0) ? 180 : 230;
			int next_y = (h == 0) ? 0 : h - 1;
			Mino mino3 = my_field.getNextMinos().get(h);
			g.setColor(mino3.color);
			Block[][] ga3 = mino3.piece[mino3.now_piece];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (ga3[i][j].ume == true) {
						drawMino(g, new Point(next_x + i * 10, 50 * next_y + 50 - j * 10), mino3.color, mino3.color_dark, mino3.color_light);
					}
				}
			}
		}
		
		
	}
	
	private void drawMino(Graphics g, Point point, Color color, Color color_dark, Color color_light) {
		g.setColor(color);
		g.fillRect(point.x, point.y, 10, 10);
		g.setColor(color_dark);
		g.drawLine(point.x + 1, point.y, point.x + 8, point.y);
		g.drawLine(point.x + 2, point.y + 1, point.x + 8, point.y + 1);
		g.drawLine(point.x, point.y + 1, point.x, point.y + 8);
		g.drawLine(point.x + 1, point.y + 1, point.x + 1, point.y + 8);
		g.setColor(color_light);
		g.drawLine(point.x, point.y + 9, point.x + 8, point.y + 9);
		g.drawLine(point.x + 9, point.y, point.x + 9, point.y + 8);
	}
	
	
}
