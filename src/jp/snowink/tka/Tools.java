package jp.snowink.tka;

import java.awt.Point;
import java.util.ArrayList;

public class Tools {
	
	public static ArrayList<Point> getAllDropPoint(Field field) {
		ArrayList<Point> dps = new ArrayList<Point>();
		for (int i = -field.getNowMino().getMinoSize() + 1; i <= field.getBan().length; i++) {
			
			
			
			
			
		}
		return dps;
	}
	
	public static int getAna(Block[][] ban) {
		return 0;
	}

	public static int dekoboko(Block[][] ban) {
		int dekoboko = 0;
		int mae = 0;
		int ana = getAna(ban);
		for (int x = 0; x < ban.length; x++) {
			if (x != ana) {
				for (int y = - 1 ; y >= 0; y--) {
					if (ban[x][y].isBlock()) {
						if (x != 0) {
							dekoboko += Math.abs(mae - y);
						}
							mae = y;
					}
				}
			}
		}
		return dekoboko;
	}
	
}
