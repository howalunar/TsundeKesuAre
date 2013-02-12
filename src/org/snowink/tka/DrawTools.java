package org.snowink.tka;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.math.BigDecimal;
import java.util.Calendar;

import org.snowink.tka.mino.Mino;


public class DrawTools {
	
	public static void drawBlock(Graphics g, int size, Point point, Color color, Color color_dark, Color color_light) {
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
	
	public static void drawField(Graphics g, Field field, int size, Point point) {
		Block[][] ban = field.getBan();
		for (int x = 0; x < ban.length; x++) {
			for (int y = 0; y < field.getViewLine(); y++) {
				if (ban[x][y].isBlock()) {
					drawBlock(g, size, new Point(point.x + x * size, point.y + field.getViewLine() * size - (y + 1) * size), ban[x][y].getColor(), ban[x][y].getColorDark(), ban[x][y].getColorLight());
				}
			}
		}
	}
	
	public static void drawHold(Graphics g, Field field, int size, Point point) {
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
	
	public static void drawMessage(Graphics g, Field field, int size, Point point) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("MS UI Gothic", Font.PLAIN, size));
		g.drawString(field.getMessage(), point.x, point.y);
	}
	
	public static void drawMino(Graphics g, Field field, int size, Point point) {
		Mino mino = field.getNowMino();
		for (int x = 0; x < mino.getMinoSize(); x++) {
			for (int y = 0; y < mino.getMinoSize(); y++) {
				if (mino.getPiece()[x][y].isBlock()) {
					drawBlock(g, size, new Point(point.x + (mino.getPosition().x + x) * size, point.y + field.getViewLine() * size - (mino.getPosition().y + y + 1) * size), mino.color, mino.color_dark, mino.color_light);
				}
			}
		}
	}
	
	public static void drawNext(Graphics g, Field field, int size, Point point, int interval) {
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
	
	public static void drawNextRize(Graphics g, Field field, int size, Point point) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("MS UI Gothic", Font.PLAIN, size));
		g.drawString("RIZE: " + field.getNextRize(), point.x, point.y);
	}
	
	public static void drawShadow(Graphics g, Field field, int size, Point point) {
		Mino mino = field.getNowMino();
		Point dp = mino.getDropPoint(field.getBan());
		if (dp != null) {
			for (int x = 0; x < mino.getMinoSize(); x++) {
				for (int y = 0; y < mino.getMinoSize(); y++) {
					if (mino.getPiece()[x][y].isBlock()) {
						drawBlock(g, size, new Point(point.x + (dp.x + x) * size, point.y + field.getViewLine() * size - (dp.y + y + 1) * size), mino.shadow_color, mino.shadow_color_dark, mino.shadow_color_light);
					}
				}
			}
		}
	}
	
	public static void drawTime(Graphics g, Field field, int size, Point point, long start_time) {
		long time = Calendar.getInstance().getTimeInMillis() - start_time;
		double min = time / (double) 60000;
		g.setColor(Color.BLACK);
		g.setFont(new Font("MS UI Gothic", Font.PLAIN, size));
		g.drawString("TIME: " + time, point.x, point.y);
		g.drawString("LINE(LPM): " + (field.total_line > 0 ? (field.total_line + "(" + new BigDecimal(field.total_line / min).setScale(1, BigDecimal.ROUND_HALF_UP) + ")") : "0(0)"), point.x, point.y + 20);
		g.drawString("ATTACK(APM): " + (field.total_attack > 0 ? (field.total_attack + "(" + new BigDecimal(field.total_attack / min).setScale(1, BigDecimal.ROUND_HALF_UP) + ")") : "0(0)"), point.x, point.y + 40);
	}
	
	private static int[] getMinoWidth(Mino mino) {
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
	
	private static int[] getMinoHeight(Mino mino) {
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
