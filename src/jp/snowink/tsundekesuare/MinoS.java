package jp.snowink.tsundekesuare;
import java.awt.Color;


public class MinoS extends Mino {
	
	public MinoS(Field field, Block[][] blocks) {
		super(blocks);
		piece[0][0][2].ume = true;
		piece[0][1][2].ume = true;
		piece[0][1][3].ume = true;
		piece[0][2][3].ume = true;

		piece[1][1][2].ume = true;
		piece[1][1][3].ume = true;
		piece[1][2][1].ume = true;
		piece[1][2][2].ume = true;

		piece[2][0][1].ume = true;
		piece[2][1][1].ume = true;
		piece[2][1][2].ume = true;
		piece[2][2][2].ume = true;

		piece[3][0][2].ume = true;
		piece[3][0][3].ume = true;
		piece[3][1][1].ume = true;
		piece[3][1][2].ume = true;
		
		
		color = new Color(0, 255, 0);
		color_dark = new Color(0, 128, 0);
		color_light = new Color(128, 255, 128);
		
		shadow_color = new Color(0, 255, 0);
		shadow_color_dark = new Color(0, 255, 0);
		shadow_color_light = new Color(0, 255, 0);
	}

}
