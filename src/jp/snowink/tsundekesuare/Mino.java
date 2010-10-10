package jp.snowink.tsundekesuare;
import java.awt.Color;
import java.awt.Point;


public class Mino {
	
	public Block[][][] piece = new Block[4][4][4];
	protected Block[][] blocks;
	
	// ミノの色設定
	public Color color = Color.BLACK;
	public Color color_dark = Color.BLACK;
	public Color color_light = Color.BLACK;
	
	// 影の色設定
	public Color shadow_color = Color.BLACK;
	public Color shadow_color_dark = Color.BLACK;
	public Color shadow_color_light = Color.BLACK;
	
	public Point position = new Point(3, 17);
	public int now_piece = 0;
	
	// 初期化
	public Mino(Block[][] blocks) {
		this.blocks = blocks;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					piece[i][j][k] = new Block();
				}
			}
		}
	}
	
	public boolean check(Block[][] piece, int x, int y, Block[][] blocks) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (piece[i][j].ume == true) {
					// 場外判定
					if (x + i < 0 || x + i > blocks.length - 1 || y + j < 0 ) {
						return false;
					}
					// かぶり判定
					if (blocks[x + i][y + j].ume == true) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean moveLeft() {
		if (check(piece[now_piece], position.x - 1, position.y, blocks)) {
			position.x--;
			return true;
		}
		return false;
	}
	
	public boolean moveRight() {
		if (check(piece[now_piece], position.x + 1, position.y, blocks)) {
			position.x++;
			return true;
		}
		return false;
	}
	
	public boolean moveBottom() {
		if (check(piece[now_piece], position.x, position.y - 1, blocks)) {
			position.y--;
			return true;
		}
		return false;
	}
		
	public boolean rotateLeft() {
		switch (now_piece) {
		
		case 0:
			if (check(piece[3], position.x, position.y, blocks)) {
				now_piece = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y, blocks)) {
				position.x += 1;
				now_piece = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y + 1, blocks)) {
				position.x += 1;
				position.y += 1;
				now_piece = 3;
				return true;
			}
			else if (check(piece[3], position.x, position.y - 2, blocks)) {
				position.y -= 2;
				now_piece = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y - 2, blocks)) {
				position.x += 1;
				position.y -= 2;
				now_piece = 3;
				return true;
			}
			break;

		case 1:
			if (check(piece[0], position.x, position.y, blocks)) {
				now_piece = 0;
				return true;
			}
			else if (check(piece[0], position.x + 1, position.y, blocks)) {
				position.x += 1;
				now_piece = 0;
				return true;
			}
			else if (check(piece[0], position.x + 1, position.y - 1, blocks)) {
				position.x += 1;
				position.y -= 1;
				now_piece = 0;
				return true;
			}
			else if (check(piece[0], position.x, position.y + 2, blocks)) {
				position.y += 2;
				now_piece = 0;
				return true;
			}
			else if (check(piece[0], position.x + 1, position.y + 2, blocks)) {
				position.x += 1;
				position.y += 2;
				now_piece = 0;
				return true;
			}
			break;

		case 2:
			if (check(piece[1], position.x, position.y, blocks)) {
				now_piece = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y, blocks)) {
				position.x -= 1;
				now_piece = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y + 1, blocks)) {
				position.x -= 1;
				position.y += 1;
				now_piece = 1;
				return true;
			}
			else if (check(piece[1], position.x, position.y - 2, blocks)) {
				position.y -= 2;
				now_piece = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y - 2, blocks)) {
				position.x -= 1;
				position.y -= 2;
				now_piece = 1;
				return true;
			}
			break;
			
		case 3:
			if (check(piece[2], position.x, position.y, blocks)) {
				now_piece = 2;
				return true;
			}
			else if (check(piece[2], position.x - 1, position.y, blocks)) {
				position.x -= 1;
				now_piece = 2;
				return true;
			}
			else if (check(piece[2], position.x - 1, position.y - 1, blocks)) {
				position.x -= 1;
				position.y -= 1;
				now_piece = 2;
				return true;
			}
			else if (check(piece[2], position.x, position.y + 2, blocks)) {
				position.y += 2;
				now_piece = 2;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y + 2, blocks)) {
				position.x -= 1;
				position.y += 2;
				now_piece = 2;
				return true;
			}
			break;
			
		}
		return false;
	}
	
	public boolean rotateRight() {

		switch (now_piece) {
		
		case 0:
			if (check(piece[1], position.x, position.y, blocks)) {
				now_piece = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y, blocks)) {
				position.x -= 1;
				now_piece = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y + 1, blocks)) {
				position.x -= 1;
				position.y += 1;
				now_piece = 1;
				return true;
			}
			else if (check(piece[1], position.x, position.y - 2, blocks)) {
				position.y -= 2;
				now_piece = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y - 2, blocks)) {
				position.x -= 1;
				position.y -= 2;
				now_piece = 1;
				return true;
			}
			break;

		case 1:
			if (check(piece[2], position.x, position.y, blocks)) {
				now_piece = 2;
				return true;
			}
			else if (check(piece[2], position.x + 1, position.y, blocks)) {
				position.x += 1;
				now_piece = 2;
				return true;
			}
			else if (check(piece[2], position.x + 1, position.y - 1, blocks)) {
				position.x += 1;
				position.y -= 1;
				now_piece = 2;
				return true;
			}
			else if (check(piece[2], position.x, position.y + 2, blocks)) {
				position.y += 2;
				now_piece = 2;
				return true;
			}
			else if (check(piece[2], position.x + 1, position.y + 2, blocks)) {
				position.x += 1;
				position.y += 2;
				now_piece = 2;
				return true;
			}
			break;

		case 2:
			if (check(piece[3], position.x, position.y, blocks)) {
				now_piece = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y, blocks)) {
				position.x += 1;
				now_piece = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y + 1, blocks)) {
				position.x += 1;
				position.y += 1;
				now_piece = 3;
				return true;
			}
			else if (check(piece[3], position.x, position.y - 2, blocks)) {
				position.y -= 2;
				now_piece = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y - 2, blocks)) {
				position.x += 1;
				position.y -= 2;
				now_piece = 3;
				return true;
			}
			break;
			
		case 3:
			if (check(piece[0], position.x, position.y, blocks)) {
				now_piece = 0;
				return true;
			}
			else if (check(piece[0], position.x - 1, position.y, blocks)) {
				position.x -= 1;
				now_piece = 0;
				return true;
			}
			else if (check(piece[0], position.x - 1, position.y - 1, blocks)) {
				position.x -= 1;
				position.y -= 1;
				now_piece = 0;
				return true;
			}
			else if (check(piece[0], position.x, position.y + 2, blocks)) {
				position.y += 2;
				now_piece = 0;
				return true;
			}
			else if (check(piece[0], position.x - 1, position.y + 2, blocks)) {
				position.x -= 1;
				position.y += 2;
				now_piece = 0;
				return true;
			}
			break;
			
		}
		return false;
	}
	
	public void hardDrop() {
		Point point = getDropPoint();
		position.y = point.y;
	}
	
	public Point getDropPoint() {
		for (int i = position.y; i >= -4; i--) {
			if(!check(piece[now_piece], position.x, i - 1, blocks)) {
				return new Point(position.x, i);
			}
		}
		return new Point(position.x, -4);
	}
	
	public void reset() {
		position.x = 3;
		position.y = 17;
		now_piece = 0;
	}

	public void rize() {
		
	}
	
}
