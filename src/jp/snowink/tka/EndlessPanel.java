package jp.snowink.tka;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import jp.snowink.tka.mino.Mino;


public class EndlessPanel extends JPanel {

	public EndlessPanel() {
		
		
	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		Field field = DataPool.endless_field;
		
		// 基点
		Point cp = new Point(70, 10);
		
		// 枠
		g.setColor(Color.BLACK);
		g.drawRect(cp.x - 1, cp.y - 1, field.getBlocks().length * 10 + 1, field.getViewLine() * 10 + 1);
		
		// テトリスフィールド
		Block[][] nullpo = field.getBlocks();
		for (int i = 0; i < field.getBlocks().length; i++) {
			
			for (int j = 0; j < field.getViewLine(); j++) {
				g.setColor(nullpo[i][j].getColor());
				
				if (nullpo[i][j].isBlock()) {
					drawMino(g, new Point(cp.x + i * 10, 200 - j * 10), nullpo[i][j].getColor(), nullpo[i][j].getColorDark(), nullpo[i][j].getColorLight());
				}
				
			}
			
		}
		
		// ミノ関連
		Mino mino = field.getNowMino();
		Block[][] ga = mino.getPiece();
		int x = mino.getPosition().x;
		int y = mino.getPosition().y;
		
		// 影
		if (mino.getPosition().y != mino.getDropPoint().y) {
			int drop_y = mino.getDropPoint().y;
			for (int i = 0; i < 4; i++) {
			
				for (int j = 0; j < 4; j++) {
				
					if (ga[i][j].isBlock()) {
						drawMino(g, new Point(cp.x + (x + i) * 10, 200 - (drop_y + j) * 10), mino.shadow_color, mino.shadow_color_dark, mino.shadow_color_light);
					}
				}
			}
		}
		
		// なうみの	
		for (int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 4; j++) {
				
				if (ga[i][j].isBlock()) {
					drawMino(g, new Point(cp.x + (x + i) * 10, 200 - (y + j) * 10), mino.color, mino.color_dark, mino.color_light);
				}
				
			}
			
		}
		
		
		// HOLD
		Mino mino2 = field.getHoldMino();
		if (mino2 != null) {
			g.setColor(mino2.color);
			Block[][] ga2 = mino2.getPiece();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (ga2[i][j].isBlock()) {
						drawMino(g, new Point(10 + i * 10, 50 - j * 10), mino2.color, mino2.color_dark, mino2.color_light);
					}
				}
			}
		}
		
		// NEXT
		for (int h = 0; h < field.getNextMinoVolume(); h++) {
			int next_x = (h == 0) ? 180 : 230;
			int next_y = (h == 0) ? 0 : h - 1;
			Mino mino3 = field.getNextMinos().get(h);
			g.setColor(mino3.color);
			Block[][] ga3 = mino3.getPiece();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (ga3[i][j].isBlock()) {
						drawMino(g, new Point(next_x + i * 10, 50 * next_y + 50 - j * 10), mino3.color, mino3.color_dark, mino3.color_light);
					}
				}
			}
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		g.drawString(field.getMessage(), 100, 250);
		
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
