package jp.snowink.tka;

public class Tools {
	
	public static int getAna(Block[][] blocks) {
		return 0;
	}

	public static int dekoboko(Block[][] blocks) {
		int dekoboko = 0;
		int mae = 0;
		int ana = getAna(blocks);
		for (int x = 0; x < blocks.length; x++) {
			if (x != ana) {
				for (int y = - 1 ; y >= 0; y--) {
					if (blocks[x][y].isBlock()) {
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
