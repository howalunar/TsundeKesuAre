package jp.snowink.tsundekesuare;
import java.awt.Color;


public class MinoO extends Mino {
	
	public MinoO(Field field, Block[][] blocks) {
		super(blocks);
		piece[0][1][2].ume = true;
		piece[0][1][3].ume = true;
		piece[0][2][2].ume = true;
		piece[0][2][3].ume = true;

		piece[1][1][2].ume = true;
		piece[1][1][3].ume = true;
		piece[1][2][2].ume = true;
		piece[1][2][3].ume = true;

		piece[2][1][2].ume = true;
		piece[2][1][3].ume = true;
		piece[2][2][2].ume = true;
		piece[2][2][3].ume = true;

		piece[3][1][2].ume = true;
		piece[3][1][3].ume = true;
		piece[3][2][2].ume = true;
		piece[3][2][3].ume = true;
		
		color = new Color(255, 255, 0);
		color_dark = new Color(128, 128, 0);
		color_light = new Color(255, 255, 128);
		
		shadow_color = new Color(255, 255, 0);
		shadow_color_dark = new Color(255, 255, 0);
		shadow_color_light = new Color(255, 255, 0);
	}
	
	public boolean rotateLeft() {
		return true;
	}

	public boolean rotateRight() {
		return true;
	}
	
}
