package jp.snowink.tka.ai;

import java.awt.Point;
import java.util.ArrayList;

import jp.snowink.tka.Block;
import jp.snowink.tka.Field;
import jp.snowink.tka.Move;
import jp.snowink.tka.mino.*;

public class AITools {

	public static int dekoboko(Block[][] ban, int ana) {
		int dekoboko = 0;
		int mae = 0;
		for (int x = 0; x < ban.length; x++) {
			if (x != ana) {
				int ima = 0;
				for (int y = ban[0].length - 1; y >= 0; y--) {
					if (ban[x][y].isBlock()) {
						ima = y + 1;
						break;
					}
				}
				if (x != 0) {
					dekoboko += Math.abs(mae - ima);
				}
					mae = ima;
			}
		}
		return dekoboko;
	}
	
	public static void drop(Field field, Point point, int rotate, int wait) {
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
				if(!field.moveRight()) {
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
	
	public static ArrayList<Move> getAllMove(Field field) throws CloneNotSupportedException {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		int[] rp;
		Mino m = field.getNowMino();
		if (m instanceof MinoO) {
			rp = new int[]{0};
		}
		else if (m instanceof MinoI || m instanceof MinoS || m instanceof MinoZ) {
			rp = new int[]{0, 1};
		}
		else {
			rp = new int[]{0, 1, 2, 3};
		}
		
		
		
		for (int r : rp) {
			for (int x = -field.getNowMino().getMinoSize() + 1; x <= field.getBan().length; x++) {
				Field f = (Field) field.clone();
				for (int rr = 0; rr < r; rr++) {
//					f.rotateRight();
					f.getNowMino().rotateRight(f.getBan());
				}
				Mino mino = (Mino) f.getNowMino().clone();
				Point dp = f.getNowMino().getDropPoint(new Point(x, f.getNowMino().getPosition().y), f.getBan());
				if (dp != null) {

					while (f.getNowMino().getPosition().x != x) {
						if (f.getNowMino().getPosition().x > x) {
//							f.moveLeft();
							f.getNowMino().moveLeft(f.getBan());
						}
						else {
//							f.moveRight();
							f.getNowMino().moveRight(f.getBan());
						}
					}
					f.hardDrop();
				
					moves.add(new Move(f, mino, dp, r));
//					System.out.println(f.getNowMino().getDropPoint(new Point(i, f.getNowMino().getPosition().y)) + " (" + r + ")");
				}
			}
		}
		return moves;
	}
	
	public static int getAna() {
		return 99;
	}
	
	public static int getSukima(Block[][] ban) {
		int count = 0;
		for (int x = 0; x < ban.length; x++) {
			boolean hajime = false;
			for (int y = ban[0].length - 1; y >= 0; y--) {
				if (hajime && !ban[x][y].isBlock()) {
					count++;
				}
				else if (ban[x][y].isBlock()) {
					hajime = true;
				}
			}
		}
		return count;
	}

	public static int minHeight(Block[][] ban, int ana) {
		int min = 99;
		for (int x = 0; x < ban.length; x++) {
			if (x != ana) {
				for (int y = ban[0].length - 1; y >= 0; y--) {
					if (ban[x][y].isBlock()) {
						if (y < min) {
							min = y;
						}
						break;
					}
					else if (y == 0) {
						return 0;
					}
				}
			}
		}
		return min + 1;
	}
	
	public static void removeAnaMove(ArrayList<Move> moves, int ana) {
		for (int i = moves.size() - 1; i >= 0 ; i--) {
			Mino mino = moves.get(i).getMino();
			boolean remove = false;
			for (int x = 0; x < mino.getMinoSize(); x++) {
				for (int y = 0; y < mino.getMinoSize(); y++) {
					if (mino.getPiece(moves.get(i).getRotate())[x][y].isBlock() && moves.get(i).getPoint().x + x == ana) {
						remove = true;
						break;
					}
				}
				if (remove) {
					moves.remove(i);
					break;
				}
			}
		}
	}
	
	// 作りかけ
	public static void removeAnaMove2(ArrayList<Move> moves, int ana) {
		for (int i = moves.size() - 1; i >= 0 ; i--) {
			Block[][] ban = moves.get(i).getField().getBan();
			for (int y = ban[0].length - 1; y >= 0; y--) {
				if (ban[ana][y].isBlock()) {
					moves.remove(i);
					break;
				}
			}
		}
	}
	
}
