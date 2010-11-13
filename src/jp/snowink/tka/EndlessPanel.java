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
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		Field my_field = DataPool.endless_field;
		
		// 基点
		Point cp_1 = new Point(70, 60);
		
		// 枠
		g.setColor(Color.BLACK);
		g.drawRect(cp_1.x - 1, cp_1.y - 1, my_field.getBan().length * block_size + 1, my_field.getViewLine() * block_size + 1);
		
//		g.fillRect(cp_1.x - 1, cp_1.y - 1, my_field.getBlocks().length * block_size + 2, my_field.getViewLine() * block_size + 2);
		
		// フィールド
		drawField(g, my_field, block_size, cp_1);				
		
		// 影
		drawShadow(g, my_field, block_size, cp_1);				
		
		// なうみの	
		drawMino(g, my_field, block_size, cp_1);				
		
		// HOLD
		drawHold(g, my_field, small_block_size, new Point(cp_1.x - 40, cp_1.y));
		
		// NEXT
		drawNext(g, my_field, small_block_size, new Point(cp_1.x + 28, cp_1.y - 40), small_block_size);
		
	}
	
	private void drawBlock(Graphics g, int size, Point point, Color color, Color color_dark, Color color_light) {
		g.setColor(color);
		g.fillRect(point.x, point.y, size, size);
		g.setColor(color_dark);
		g.drawLine(point.x + 1, point.y, point.x + size - 2, point.y);
		g.drawLine(point.x + 2, point.y + 1, point.x + size - 2, point.y + 1);
		g.drawLine(point.x, point.y + 1, point.x, point.y + size - 2);
		g.drawLine(point.x + 1, point.y + 1, point.x + 1, point.y + size - 2);
		g.setColor(color_light);
		g.drawLine(point.x, point.y + size - 1, point.x + size - 2, point.y + size - 1);
		g.drawLine(point.x + size - 1, point.y, point.x + size - 1, point.y + size - 2);
	}
	
	private void drawField(Graphics g, Field field, int size, Point point) {
		Block[][] ban = field.getBan();
		for (int x = 0; x < ban.length; x++) {
			for (int y = 0; y < field.getViewLine(); y++) {
				if (ban[x][y].isBlock()) {
					drawBlock(g, size, new Point(point.x + x * size, point.y + field.getViewLine() * size - (y + 1) * size), ban[x][y].getColor(), ban[x][y].getColorDark(), ban[x][y].getColorLight());
				}
			}
		}
	}
	
	private void drawHold(Graphics g, Field field, int size, Point point) {
		g.setColor(Color.BLACK);
		g.drawRect(point.x - 1, point.y - 1, size * field.getMaxMinoSize() + 1, size * field.getMaxMinoSize() + 1);
		
		Mino mino;
		if ((mino = field.getHoldMino()) != null) {
			for (int x = 0; x < mino.getMinoSize(); x++) {
				for (int y = 0; y < mino.getMinoSize(); y++) {
					if (mino.getPiece()[x][y].isBlock()) {
						int[] xx = getMinoWidth(mino);
						int[] yy = getMinoHeight(mino);
						int fix_x = Math.round((((float) (mino.getMinoSize() - xx[1]) / 2 - xx[0]) * size));
						int fix_y = Math.round((((float) (mino.getMinoSize() - yy[1]) / 2 - yy[0]) * size));
						drawBlock(g, size, new Point(point.x + size * x + fix_x, point.y + size * mino.getMinoSize() - size * (y + 1) + fix_y), mino.color, mino.color_dark, mino.color_light);
					}
				}
			}
		}
	}
	
	private void drawMino(Graphics g, Field field, int size, Point point) {
		Mino mino = field.getNowMino();
		for (int x = 0; x < mino.getMinoSize(); x++) {
			for (int y = 0; y < mino.getMinoSize(); y++) {
				if (mino.getPiece()[x][y].isBlock()) {
					drawBlock(g, size, new Point(point.x + (mino.getPosition().x + x) * size, point.y + field.getViewLine() * size - (mino.getPosition().y + y + 1) * size), mino.color, mino.color_dark, mino.color_light);
				}
			}
		}
	}
	
	private void drawNext(Graphics g, Field field, int size, Point point, int interval) {
		for (int h = 0; h < field.getNextMinoVolume(); h++) {
			Mino mino = field.getNextMinos().get(h);
			
			int next_x;
			int next_y;
			
			switch (h) {
			case 0:
				next_x = point.x;
				next_y = point.y;
				break;
			case 1:
				next_x = point.x + size * mino.getMinoSize() + interval;
				next_y = point.y;
				break;
			default: 
				next_x = point.x + (size * mino.getMinoSize() + interval) * 2;
				next_y = point.y + (size * mino.getMinoSize() + interval) * (h - 2);
				break;
			}
			
			g.setColor(Color.BLACK);
			g.drawRect(next_x - 1, next_y - 1, size * field.getMaxMinoSize() + 1, size * field.getMaxMinoSize() + 1);
			Block[][] ga3 = mino.getPiece();
			for (int i = 0; i < mino.getMinoSize(); i++) {
				for (int j = 0; j < mino.getMinoSize(); j++) {
					if (ga3[i][j].isBlock()) {
						int[] xx = getMinoWidth(mino);
						int[] yy = getMinoHeight(mino);
						int fix_x = Math.round((((float) (mino.getMinoSize() - xx[1]) / 2 - xx[0]) * size));
						int fix_y = Math.round((((float) (mino.getMinoSize() - yy[1]) / 2 - yy[0]) * size));
						drawBlock(g, size, new Point(next_x + size * i + fix_x, next_y + size * mino.getMinoSize() - size * (j + 1) + fix_y), mino.color, mino.color_dark, mino.color_light);
					}
				}
			}
		}
	}
	
	private void drawShadow(Graphics g, Field field, int size, Point point) {
		Mino mino = field.getNowMino();
		for (int x = 0; x < mino.getMinoSize(); x++) {
			for (int y = 0; y < mino.getMinoSize(); y++) {
				if (mino.getPiece()[x][y].isBlock()) {
					drawBlock(g, size, new Point(point.x + (mino.getDropPoint().x + x) * size, point.y + field.getViewLine() * size - (mino.getDropPoint().y + y + 1) * size), mino.shadow_color, mino.shadow_color_dark, mino.shadow_color_light);
				}
			}
		}
	}
	
	private int[] getMinoWidth(Mino mino) {
		int start_x = -1;
		int count = 0;
		Block piece[][] = mino.getPiece();
		for (int x = 0; x < mino.getMinoSize(); x++) {
			for (int y = 0; y < mino.getMinoSize(); y++) {
				if (piece[x][y].isBlock()) {
					if (start_x == -1) {
						start_x = x;
					}
					count++;
					break;
				}
			}
		}
		return new int[]{start_x, count};
	}
	
	private int[] getMinoHeight(Mino mino) {
		int start_y = -1;
		int count = 0;
		int ms = mino.getMinoSize();
		Block piece[][] = mino.getPiece();
		for (int y = 0; y < ms; y++) {
			for (int x = 0; x < ms; x++) {
				if (piece[x][ms - 1 - y].isBlock()) {
					if (start_y == -1) {
						start_y = y;
					}
					count++;
					break;
				}
			}
		}
		return new int[]{start_y, count};
	}
	
}
