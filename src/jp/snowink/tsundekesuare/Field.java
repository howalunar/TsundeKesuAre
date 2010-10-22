package jp.snowink.tsundekesuare;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class Field {

	private Mino now_mino = null;
	private ArrayList<Mino> next_minos = new ArrayList<Mino>();
	private Mino hold_mino = null;
	private Block[][] blocks = new Block[10][24];
	private int view_line = 20;
	private boolean can_hold = true;
	private int next_mino_volume = 6;
	private int next_rize = 0;
	private Timer timer;
	private int ake = new Random().nextInt(blocks.length);
	private boolean btb = false;
	private String message = "";
	
	private boolean not_move = false;

	public boolean ochihajime = true;
	
	
	private Field your_field = null;
	
	public int getNextRize() {
		return next_rize;
	}

	public void setNextRize(int next_rize) {
		this.next_rize = next_rize;
	}

	public ArrayList<Mino> minos = new ArrayList<Mino>();
	
	public Field(Timer timer) {
		this.timer = timer;
		now_mino = getMino();
		for (int i = 0; i < next_mino_volume; i++) {
			next_minos.add(getMino());
		}
		
		for (int i = 0; i < blocks.length; i++) {
			
			for (int j = 0; j < blocks[0].length; j++) {
				blocks[i][j] = new Block();
			}
			
		}

	}
	
	public Mino getNowMino() {
		return now_mino;
	}

	public ArrayList<Mino> getNextMinos() {
		return next_minos;
	}

	public Mino getHoldMino() {
		return hold_mino;
	}

	public Block[][] getBlocks() {
		return blocks;
	}
	
	public void setBlocks(Block[][] blocks) {
		this.blocks = blocks;
	}
	
	public int getViewLine() {
		return view_line;
	}
	
	public boolean canHold() {
		return can_hold;
	}
	
	public int getNextMinoVolume() {
		return next_mino_volume;
	}
	
	public void setNextMinoVolume(int next_mino_volume) {
		this.next_mino_volume = next_mino_volume;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void moveLeft() {
		if (now_mino.moveLeft()) {
			not_move = false;
			timer.reset();
		}
	}
	
	public void moveRight() {
		if (now_mino.moveRight()) {
			not_move = false;
			timer.reset();
		}
	}
	
	public void moveBottom() {
		if (now_mino.moveBottom()) {
			not_move = false;
		}
	}
	
	public void rotateLeft() {
		if (now_mino.rotateLeft()) {
			not_move = true;
			timer.reset();
		}
	}

	public void rotateRight() {
		if (now_mino.rotateRight()) {
			not_move = true;
			timer.reset();
		}
	}
	
	public void hardDrop() {
		now_mino.hardDrop();
		setti();
		timer.reset();
	}

	public void lineCheck() {
		boolean tspin = false;
		int line = 0;
		int rize = 0;
//		message = "";
		
		if (now_mino instanceof MinoT && not_move) {
			int count = 0;
			Point[] kado = {new Point(0, 1), new Point(0, 3), new Point(2, 1), new Point(2, 3)};
			for (Point i : kado) {
				if (now_mino.position.x + i.x < 0 || now_mino.position.x + i.x >= blocks.length || now_mino.position.y + i.y < 0 || blocks[now_mino.position.x + i.x][now_mino.position.y + i.y].ume) {
					count++;
				}
			}
			if (count >= 3) {
				tspin = true;
			}
		}
		
		for (int i = 0; i < blocks[0].length; i++) {
			
			if (blocks[0][i].ume && blocks[1][i].ume && blocks[2][i].ume && blocks[3][i].ume && blocks[4][i].ume && blocks[5][i].ume && blocks[6][i].ume && blocks[7][i].ume && blocks[8][i].ume && blocks[9][i].ume) {
				line++;
			}
			else if (line >= 1) {
				for (int j = 0; j < blocks.length; j++) {
					blocks[j][i - line].color = blocks[j][i].color;
					blocks[j][i - line].color_dark = blocks[j][i].color_dark;
					blocks[j][i - line].color_light = blocks[j][i].color_light;
					blocks[j][i - line].shadow_color = blocks[j][i].shadow_color;
					blocks[j][i - line].shadow_color_dark = blocks[j][i].shadow_color_dark;
					blocks[j][i - line].shadow_color_light = blocks[j][i].shadow_color_light;
					blocks[j][i - line].ume = blocks[j][i].ume;
				}
			}
			
		}
		if (line >= 1) {
			for (int i = blocks[0].length; i >= blocks[0].length - line - 1; i--) {
				for (int j = 0; j < blocks.length; j++) {
					blocks[j][blocks[0].length - 1] = new Block();
				}
			}
		}
		
		// 相手に送るライン数
		if (your_field != null) {
			if (tspin) {
				switch (line) {
				case 1:
					rize = 2;
					break;
				case 2:
					rize = 4;
					break;
				case 3:
					rize = 6;
					break;
				}
				if (btb) {
					rize += 1;
				}
			}
			else {
				switch (line) {
				case 2:
					rize = 1;
					break;
				case 3:
					rize = 2;
					break;
				case 4:
					rize = 4;
					if (btb) {
						rize += 1;
					}
					break;
				}
			}
			
			if (next_rize > 0) {
				
				// 相殺しきれてない
				if (next_rize >= rize) {
					next_rize -= rize;
					rize = 0;
				}
				// 相殺できてさらに送れる
				else {
					rize -= next_rize;
					next_rize = 0;
				}
			}
			your_field.setNextRize(your_field.getNextRize() + rize);

		
		}
		
		// BTB, メッセージ
		if (tspin) {
			switch (line) {
			case 0:
				message = "T-SPIN";
				break;
			case 1:
				message = "T-SPIN SINGLE";
				break;
			case 2:
				message = "T-SPIN DOUBLE";
				break;
			case 3:
				message = "T-SPIN TRIPLE";
				break;
			}
			if (line >= 1) {
				if (btb) {
					message = "BACK TO BACK " +  message;
				}
				btb = true;
			}
		}
		else {
			switch (line) {
			case 1: case 2: case 3:
				message = "";
				btb = false;
				break;
			case 4:
				message = "4-LINES";
				if (btb) {
					message = "BACK TO BACK " +  message;
				}
				btb = true;
				break;
			}
		}
			
		System.out.println(tspin + " " + line);
			
	}
	
	public void rize() {
		if (next_rize > 0) {
			for (int y = blocks[0].length - 1; y >= next_rize; y--) {
				for (int x = 0; x < blocks.length; x++) {
					blocks[x][y].color = blocks[x][y - next_rize].color;
					blocks[x][y].color_dark = blocks[x][y - next_rize].color_dark;
					blocks[x][y].color_light = blocks[x][y - next_rize].color_light;
					blocks[x][y].shadow_color = blocks[x][y - next_rize].shadow_color;
					blocks[x][y].shadow_color_dark = blocks[x][y - next_rize].shadow_color_dark;
					blocks[x][y].shadow_color_light = blocks[x][y - next_rize].shadow_color_light;
					blocks[x][y].ume = blocks[x][y - next_rize].ume;
				}
			}
			int kakuritsu = 80;
			int rnd;
			for (int y = next_rize - 1; y >= 0; y--) {
				// 一定確率で穴の位置を変更
				if (new Random().nextInt(100) + 1 > kakuritsu ) {
					// 再抽選時に同じ値を防ぐ
					while (ake == (rnd = new Random().nextInt(blocks.length))) {}
					ake = rnd;
				}
				for (int x = 0; x < blocks.length; x++) {
					blocks[x][y] = new Block();
					if (x != ake) {
						blocks[x][y].color = new Color(128, 128, 128);
						blocks[x][y].color_dark = new Color(64, 64, 64);
						blocks[x][y].color_light = new Color(255, 255, 255);
						blocks[x][y].shadow_color = new Color(255, 255, 0);
						blocks[x][y].shadow_color_dark = new Color(255, 255, 0);
						blocks[x][y].shadow_color_light = new Color(255, 255, 0);
						blocks[x][y].ume = true;
					}
				}
			}
			next_rize = 0;
		}
	}
	
	
	public void setti() {
		
		for (int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 4; j++) {
				if (now_mino.piece[now_mino.now_piece][i][j].ume == true) {
					blocks[now_mino.position.x + i][now_mino.position.y + j].ume = true;
					blocks[now_mino.position.x + i][now_mino.position.y + j].color = now_mino.color;
					blocks[now_mino.position.x + i][now_mino.position.y + j].color_dark = now_mino.color_dark;
					blocks[now_mino.position.x + i][now_mino.position.y + j].color_light = now_mino.color_light;
					blocks[now_mino.position.x + i][now_mino.position.y + j].shadow_color = now_mino.shadow_color;
					blocks[now_mino.position.x + i][now_mino.position.y + j].shadow_color_dark = now_mino.shadow_color_dark;
					blocks[now_mino.position.x + i][now_mino.position.y + j].shadow_color_light = now_mino.shadow_color_light;
				}
			}
			
		}
		
		lineCheck();
		
		now_mino = next_minos.get(0);
		now_mino = next_minos.remove(0);
		next_minos.add(getMino());
		can_hold = true;
		if(!now_mino.check(now_mino.piece[now_mino.now_piece], now_mino.position.x, now_mino.position.y, blocks)) {
			TKA.window.gameOver();
		}
		rize();
		timer.reset();
		ochihajime = true;
	}
	
	public Mino getMino() {
		if (minos.isEmpty()) {
			minos.add(new MinoI(this, blocks));
			minos.add(new MinoJ(this, blocks));
			minos.add(new MinoL(this, blocks));
			minos.add(new MinoO(this, blocks));
			minos.add(new MinoS(this, blocks));
			minos.add(new MinoT(this, blocks));
			minos.add(new MinoZ(this, blocks));
		}
		int i = new Random().nextInt(minos.size());
		Mino m = minos.get(i);
		minos.remove(i);
		return m;
	}
	
	public void hold() {
		if (hold_mino == null) {
			hold_mino = now_mino;
			now_mino = next_minos.get(0);
			now_mino = next_minos.remove(0);
			next_minos.add(getMino());
			can_hold = false;
		}
		else if (can_hold) {
			Mino tmp = now_mino;
			now_mino = hold_mino;
			hold_mino = tmp;
			can_hold = false;
		}
		hold_mino.reset();
	}
	
	
	public void setYourField(Field field) {
		this.your_field = field;
	}

}
