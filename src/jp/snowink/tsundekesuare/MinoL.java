package jp.snowink.tsundekesuare;
import java.awt.Color;


public class MinoL extends Mino {
	
	public MinoL(Field field, Block[][] blocks) {
		super(blocks);
		piece[0][0][2].ume = true;
		piece[0][1][2].ume = true;
		piece[0][2][2].ume = true;
		piece[0][2][3].ume = true;

		piece[1][1][1].ume = true;
		piece[1][1][2].ume = true;
		piece[1][1][3].ume = true;
		piece[1][2][1].ume = true;

		piece[2][0][1].ume = true;
		piece[2][0][2].ume = true;
		piece[2][1][2].ume = true;
		piece[2][2][2].ume = true;

		piece[3][0][3].ume = true;
		piece[3][1][1].ume = true;
		piece[3][1][2].ume = true;
		piece[3][1][3].ume = true;
		
		color = new Color(255, 165, 0);
		color_dark = new Color(128, 83, 0);
		color_light = new Color(255, 165, 128);
		
		shadow_color = new Color(255, 165, 0);
		shadow_color_dark = new Color(255, 165, 0);
		shadow_color_light = new Color(255, 165, 0);
	}

}
