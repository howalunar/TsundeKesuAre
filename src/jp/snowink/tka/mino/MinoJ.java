package jp.snowink.tka.mino;
import java.awt.Color;
import java.awt.Point;

import jp.snowink.tka.Block;
import jp.snowink.tka.Field;


public class MinoJ extends Mino {
	
	public MinoJ(Field field, Block[][] blocks) {
		super(4, blocks, new Point(3, 17), 0);
		
		color = new Color(0, 0, 255);
		color_dark = new Color(0, 0, 128);
		color_light = new Color(128, 128, 255);
		
		shadow_color = new Color(0, 0, 255);
		shadow_color_dark = new Color(0, 0, 255);
		shadow_color_light = new Color(0, 0, 255);
		
		int pieces[][] = {
			{0, 0, 2}, {0, 0, 3}, {0, 1, 2}, {0, 2, 2},
			{1, 1, 1}, {1, 1, 2}, {1, 1, 3}, {1, 2, 3},
			{2, 0, 2}, {2, 1, 2}, {2, 2, 1}, {2, 2, 2},
			{3, 0, 1}, {3, 1, 1}, {3, 1, 2}, {3, 1, 3},
		};
		
		for (int[] i : pieces) {
			piece[i[0]][i[1]][i[2]].createBlock();
			piece[i[0]][i[1]][i[2]].setColors(color, color_light, color_dark);
			piece[i[0]][i[1]][i[2]].setShadowColors(shadow_color, shadow_color_light, shadow_color_dark);
		}
	}

	@Override
	public String getName() {
		return "J";
	}
	
	public boolean rotateLeft(Block[][] ban) {
		switch (rotate) {
		
		case 0:
			if (check(piece[3], position.x, position.y, ban)) {
				rotate = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y, ban)) {
				position.x += 1;
				rotate = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y + 1, ban)) {
				position.x += 1;
				position.y += 1;
				rotate = 3;
				return true;
			}
			else if (check(piece[3], position.x, position.y - 2, ban)) {
				position.y -= 2;
				rotate = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y - 2, ban)) {
				position.x += 1;
				position.y -= 2;
				rotate = 3;
				return true;
			}
			break;

		case 1:
			if (check(piece[0], position.x, position.y, ban)) {
				rotate = 0;
				return true;
			}
			else if (check(piece[0], position.x + 1, position.y, ban)) {
				position.x += 1;
				rotate = 0;
				return true;
			}
			else if (check(piece[0], position.x + 1, position.y - 1, ban)) {
				position.x += 1;
				position.y -= 1;
				rotate = 0;
				return true;
			}
			else if (check(piece[0], position.x, position.y + 2, ban)) {
				position.y += 2;
				rotate = 0;
				return true;
			}
			else if (check(piece[0], position.x + 1, position.y + 2, ban)) {
				position.x += 1;
				position.y += 2;
				rotate = 0;
				return true;
			}
			break;

		case 2:
			if (check(piece[1], position.x, position.y, ban)) {
				rotate = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y, ban)) {
				position.x -= 1;
				rotate = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y + 1, ban)) {
				position.x -= 1;
				position.y += 1;
				rotate = 1;
				return true;
			}
			else if (check(piece[1], position.x, position.y - 2, ban)) {
				position.y -= 2;
				rotate = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y - 2, ban)) {
				position.x -= 1;
				position.y -= 2;
				rotate = 1;
				return true;
			}
			break;
			
		case 3:
			if (check(piece[2], position.x, position.y, ban)) {
				rotate = 2;
				return true;
			}
			else if (check(piece[2], position.x - 1, position.y, ban)) {
				position.x -= 1;
				rotate = 2;
				return true;
			}
			else if (check(piece[2], position.x - 1, position.y - 1, ban)) {
				position.x -= 1;
				position.y -= 1;
				rotate = 2;
				return true;
			}
			else if (check(piece[2], position.x, position.y + 2, ban)) {
				position.y += 2;
				rotate = 2;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y + 2, ban)) {
				position.x -= 1;
				position.y += 2;
				rotate = 2;
				return true;
			}
			break;
			
		}
		return false;
	}
	
	public boolean rotateRight(Block[][] ban) {

		switch (rotate) {
		
		case 0:
			if (check(piece[1], position.x, position.y, ban)) {
				rotate = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y, ban)) {
				position.x -= 1;
				rotate = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y + 1, ban)) {
				position.x -= 1;
				position.y += 1;
				rotate = 1;
				return true;
			}
			else if (check(piece[1], position.x, position.y - 2, ban)) {
				position.y -= 2;
				rotate = 1;
				return true;
			}
			else if (check(piece[1], position.x - 1, position.y - 2, ban)) {
				position.x -= 1;
				position.y -= 2;
				rotate = 1;
				return true;
			}
			break;

		case 1:
			if (check(piece[2], position.x, position.y, ban)) {
				rotate = 2;
				return true;
			}
			else if (check(piece[2], position.x + 1, position.y, ban)) {
				position.x += 1;
				rotate = 2;
				return true;
			}
			else if (check(piece[2], position.x + 1, position.y - 1, ban)) {
				position.x += 1;
				position.y -= 1;
				rotate = 2;
				return true;
			}
			else if (check(piece[2], position.x, position.y + 2, ban)) {
				position.y += 2;
				rotate = 2;
				return true;
			}
			else if (check(piece[2], position.x + 1, position.y + 2, ban)) {
				position.x += 1;
				position.y += 2;
				rotate = 2;
				return true;
			}
			break;

		case 2:
			if (check(piece[3], position.x, position.y, ban)) {
				rotate = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y, ban)) {
				position.x += 1;
				rotate = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y + 1, ban)) {
				position.x += 1;
				position.y += 1;
				rotate = 3;
				return true;
			}
			else if (check(piece[3], position.x, position.y - 2, ban)) {
				position.y -= 2;
				rotate = 3;
				return true;
			}
			else if (check(piece[3], position.x + 1, position.y - 2, ban)) {
				position.x += 1;
				position.y -= 2;
				rotate = 3;
				return true;
			}
			break;
			
		case 3:
			if (check(piece[0], position.x, position.y, ban)) {
				rotate = 0;
				return true;
			}
			else if (check(piece[0], position.x - 1, position.y, ban)) {
				position.x -= 1;
				rotate = 0;
				return true;
			}
			else if (check(piece[0], position.x - 1, position.y - 1, ban)) {
				position.x -= 1;
				position.y -= 1;
				rotate = 0;
				return true;
			}
			else if (check(piece[0], position.x, position.y + 2, ban)) {
				position.y += 2;
				rotate = 0;
				return true;
			}
			else if (check(piece[0], position.x - 1, position.y + 2, ban)) {
				position.x -= 1;
				position.y += 2;
				rotate = 0;
				return true;
			}
			break;
			
		}
		return false;
	}

}
