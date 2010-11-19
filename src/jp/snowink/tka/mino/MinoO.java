package jp.snowink.tka.mino;
import java.awt.Color;
import java.awt.Point;

import jp.snowink.tka.Block;
import jp.snowink.tka.Field;


public class MinoO extends Mino {
	
	public MinoO(Field field, Block[][] blocks) {
		super(4, blocks, new Point(3, 17), 0);
		
		color = new Color(255, 255, 0);
		color_dark = new Color(128, 128, 0);
		color_light = new Color(255, 255, 128);
		
		shadow_color = new Color(255, 255, 0);
		shadow_color_dark = new Color(255, 255, 0);
		shadow_color_light = new Color(255, 255, 0);
		
		int pieces[][] = {
			{0, 1, 2}, {0, 1, 3}, {0, 2, 2}, {0, 2, 3},
			{1, 1, 2}, {1, 1, 3}, {1, 2, 2}, {1, 2, 3},
			{2, 1, 1}, {2, 1, 3}, {2, 2, 2}, {2, 2, 3},
			{3, 1, 2}, {3, 1, 3}, {3, 2, 2}, {3, 2, 3},
		};
		
		for (int[] i : pieces) {
			piece[i[0]][i[1]][i[2]].createBlock();
			piece[i[0]][i[1]][i[2]].setColors(color, color_light, color_dark);
			piece[i[0]][i[1]][i[2]].setShadowColors(shadow_color, shadow_color_light, shadow_color_dark);
		}
	}

	@Override
	public String getName() {
		return "O";
	}
	
	public boolean rotateLeft(Block[][] blocks) {
		return true;
	}

	public boolean rotateRight(Block[][] blocks) {
		return true;
	}
	
}
