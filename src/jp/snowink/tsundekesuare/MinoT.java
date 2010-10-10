package jp.snowink.tsundekesuare;

import java.awt.Color;


public class MinoT extends Mino {
	
	public MinoT(Field field, Block[][] blocks) {
		super(blocks);
		piece[0][0][2].ume = true;
		piece[0][1][2].ume = true;
		piece[0][1][3].ume = true;
		piece[0][2][2].ume = true;

		piece[1][1][1].ume = true;
		piece[1][1][2].ume = true;
		piece[1][1][3].ume = true;
		piece[1][2][2].ume = true;

		piece[2][0][2].ume = true;
		piece[2][1][1].ume = true;
		piece[2][1][2].ume = true;
		piece[2][2][2].ume = true;

		piece[3][0][2].ume = true;
		piece[3][1][1].ume = true;
		piece[3][1][2].ume = true;
		piece[3][1][3].ume = true;
		
		color = new Color(255, 0, 255);
		color_dark = new Color(128, 0, 128);
		color_light = new Color(255, 128, 255);
		
		shadow_color = new Color(255, 0, 255);
		shadow_color_dark = new Color(255, 0, 255);
		shadow_color_light = new Color(255, 0, 255);
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
	
}
