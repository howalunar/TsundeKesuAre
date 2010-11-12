package jp.snowink.tka;

import java.awt.Point;
import java.util.ArrayList;

import jp.snowink.tka.mino.Mino;

public class Tools {
	
	public static ArrayList<Point> getAllDropPoint(Field field) throws CloneNotSupportedException {
		ArrayList<Point> dps = new ArrayList<Point>();
		for (int r = 0; r < 4; r++) {
			for (int rr = 0; rr < r; rr++) {
				field.getNowMino().rotateLeft();
			}
			for (int i = -field.getNowMino().getMinoSize() + 1; i <= field.getBan().length; i++) {
				Field f = (Field) field.clone();
				if (f.getNowMino().check(f.getNowMino().getPiece(), i, f.getNowMino().getPosition().y, f.getBan())) {
					dps.add(f.getNowMino().getDropPoint(new Point(i, f.getNowMino().getPosition().y)));
				}
			}
		}
		return dps;
	}
	
	public static ArrayList<Move> getAllMove(Field field) throws CloneNotSupportedException {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int r = 0; r < Mino.ROTATE_PATTERN; r++) {
			for (int i = -field.getNowMino().getMinoSize() + 1; i <= field.getBan().length; i++) {
				Field f = (Field) field.clone();
				for (int rr = 0; rr < r; rr++) {
					f.rotateRight();
				}
				Point dp = f.getNowMino().getDropPoint(new Point(i, f.getNowMino().getPosition().y));
				if (dp != null) {
					moves.add(new Move(null, dp, r));
//					System.out.println(f.getNowMino().getDropPoint(new Point(i, f.getNowMino().getPosition().y)) + " (" + r + ")");
				}
			}
		}
		return moves;
	}
	
	public static void drop(Field field, Point point, int rotate) {
		int wait = 500;
		switch (rotate) {
		case 0:
			break;
		case 1:
			field.rotateRight();
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			field.rotateRight();
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			field.rotateRight();
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			field.rotateLeft();
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		while (field.getNowMino().getPosition().x != point.x) {
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (field.getNowMino().getPosition().x > point.x) {
				if(!field.moveLeft()) {
					break;
				}
			}
			else {
				if(field.moveRight()) {
					break;
				}
			}
		}
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		field.hardDrop();
	}
	
	public static int getAna(Block[][] ban) {
		return 0;
	}

	public static int dekoboko(Block[][] ban) {
		int dekoboko = 0;
		int mae = 0;
		int ana = getAna(ban);
		for (int x = 0; x < ban.length; x++) {
			if (x != ana) {
				for (int y = - 1 ; y >= 0; y--) {
					if (ban[x][y].isBlock()) {
						if (x != 0) {
							dekoboko += Math.abs(mae - y);
						}
							mae = y;
					}
				}
			}
		}
		return dekoboko;
	}
	
}
