package jp.snowink.tka.ai;

import java.awt.Point;
import java.util.ArrayList;

import jp.snowink.tka.Block;
import jp.snowink.tka.Field;
import jp.snowink.tka.Move;
import jp.snowink.tka.Controller;
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
	
	public static void drop(Controller c, Point point, int rotate, int wait) {
		switch (rotate) {
		case 0:
			break;
		case 1:
			c.rotateRight();
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			c.rotateRight();
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.rotateRight();
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			c.rotateLeft();
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		while (c.getField().getNowMino().getPosition().x != point.x) {
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (c.getField().getNowMino().getPosition().x > point.x) {
				if(!c.moveLeft()) {
					break;
				}
			}
			else {
				if(!c.moveRight()) {
					break;
				}
			}
		}
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		c.hardDrop();
	}
	
	public static ArrayList<Move> getAllMove(Controller c) throws CloneNotSupportedException {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		int[] rp;
		Mino m = c.getField().getNowMino();
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
			for (int x = -c.getField().getNowMino().getMinoSize() + 1; x <= c.getField().getBan().length; x++) {
				Controller f = new Controller(c.getField());
				for (int rr = 0; rr < r; rr++) {
					f.rotateRight();
				}
				Mino mino = (Mino) f.getField().getNowMino().clone();
				Point dp = f.getField().getNowMino().getDropPoint(new Point(x, f.getField().getNowMino().getPosition().y), f.getField().getBan());
				if (dp != null) {

					while (f.getField().getNowMino().getPosition().x != x) {
						if (f.getField().getNowMino().getPosition().x > x) {
							f.moveLeft();
						}
						else {
							f.moveRight();
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
		return 9;
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
