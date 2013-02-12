package org.snowink.tka.mino;
import java.awt.Color;
import java.awt.Point;

import org.snowink.tka.Block;



public abstract class Mino implements Cloneable {

	public static final int ROTATE_PATTERN = 4;
	
	private int mino_size;
	protected Point start_position;
	private int start_rotate;
	
	protected Point position = new Point();	
	protected int rotate;
	
	protected Block[][][] piece;
	
	
	// Blockの色設定よりもMinoの色設定が優先される……ようにする
	
	// ミノの色設定
	public Color color = Color.BLACK;
	public Color color_dark = Color.BLACK;
	public Color color_light = Color.BLACK;
	
	// 影の色設定
	public Color shadow_color = Color.BLACK;
	public Color shadow_color_dark = Color.BLACK;
	public Color shadow_color_light = Color.BLACK;
	
	// 初期化
	public Mino(int mino_size, Block[][] blocks, Point start_position, int start_rotate) {
		
		this.mino_size = mino_size;
		
		piece = new Block[ROTATE_PATTERN][mino_size][mino_size];
		for (int r = 0; r < ROTATE_PATTERN; r++) {
			for (int x = 0; x < mino_size; x++) {
				for (int y = 0; y < mino_size; y++) {
					piece[r][x][y] = new Block();
				}
			}
		}
		
		this.start_position = start_position;
		this.start_rotate = start_rotate;
		initMino();
		
		
		
	}
	
	public boolean check(Point point, Block[][] ban) {
		return check(getPiece(), point, ban);
	}
	
	public boolean check(Block[][] piece, Point point, Block[][] ban) {
		for (int x = 0; x < mino_size; x++) {
			for (int y = 0; y < mino_size; y++) {
				if (piece[x][y].isBlock()) {
					// 場外判定
					if (point.x + x < 0 || point.x + x > ban.length - 1 || point.y + y < 0 ) {
						return false;
					}
					// かぶり判定
					if (ban[point.x + x][point.y + y].isBlock()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean check(Block[][] piece, int pos_x, int pos_y, Block[][] ban) {
		return check(piece, new Point(pos_x, pos_y), ban);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Mino mino = (Mino) super.clone();
		mino.piece = new Block[piece.length][piece[0].length][piece[0][0].length];
		for (int r = 0; r < mino.piece.length; r++) {
			for (int x = 0; x < mino.piece[0].length; x++) {
				for (int y = 0; y < mino.piece[0][0].length; y++) {
					mino.piece[r][x][y] = (Block) piece[r][x][y].clone();
				}
			}
		}
		mino.position = (Point) position.clone();
		mino.start_position = (Point) start_position.clone();
		return mino;
	}
	
	public Point getDropPoint(Block[][] ban) {
		return getDropPoint(position, ban);
	}
	
	public Point getDropPoint(Point point, Block[][] ban) {
		if (!check(point, ban)) {
			return null;
		}
		for (int i = point.y; i >= -mino_size + 1; i--) {
			if(!check(getPiece(), point.x, i - 1, ban)) {
				return new Point(point.x, i);
			}
		}
		return new Point(point.x, -mino_size + 1);
	}	
	
	public int getMinoSize() {
		return mino_size;
	}
	
	abstract public String getName();

	public Block[][] getPiece() {
		return piece[rotate];
	}
		
	public Block[][] getPiece(int rotate) {
		if (rotate >= 0 && rotate < ROTATE_PATTERN) {
			return piece[rotate];
		}
		else {
			return getPiece();
		}
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void hardDrop(Block[][] ban) {
		Point point = getDropPoint(ban);
		position.y = point.y;
	}
	
	public void initMino() {
		position.x = start_position.x;
		position.y = start_position.y;
		rotate = start_rotate;
	}
	
	public boolean moveBottom(Block[][] ban) {
		if (check(getPiece(), position.x, position.y - 1, ban)) {
			position.y--;
			return true;
		}
		return false;
	}
	
	public boolean moveLeft(Block[][] ban) {
		if (check(getPiece(), position.x - 1, position.y, ban)) {
			position.x--;
			return true;
		}
		return false;
	}
	
	public boolean moveRight(Block[][] ban) {
		if (check(getPiece(), position.x + 1, position.y, ban)) {
			position.x++;
			return true;
		}
		return false;
	}
		
	public abstract boolean rotateLeft(Block[][] ban);
	
	public abstract boolean rotateRight(Block[][] ban);
	
}
