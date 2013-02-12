package org.snowink.tka;

import java.awt.Color;
import java.awt.Point;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JPanel;

import org.snowink.tka.mino.Mino;
import org.snowink.tka.mino.MinoT;


public class Controller extends Thread implements Cloneable {
	
	private Field field;
	private JPanel panel;
	private boolean gameover = true;
	private long start_time = Calendar.getInstance().getTimeInMillis();
	private int step = 0; // 自動落下のステップ
	private int step2 = 0; // 接地のステップ
	private boolean clone = false;
	
	public Controller(Field field) {
		try {
			this.field = (Field) field.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		this.panel = null;
		clone = true;
	}	
	public Controller(Field field, JPanel panel) {
		this.field = field;
		this.panel = panel;
	}
	
	public boolean isGameOver() {
		return gameover;
	}
	
	public void gameStart() {
		gameover = false;
	}
	
	public Field getField() {
		return field;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void run() {
		
		start_time = Calendar.getInstance().getTimeInMillis();
		if (panel instanceof EndlessPanel) {
			EndlessPanel ep = (EndlessPanel) panel;
			ep.setStartTime(start_time);
		}
		
		while (!gameover) {
			
			Point dp = field.getNowMino().getDropPoint(field.getBan());
			
			if (dp == null) {
				System.out.println("落下地点がNULLです");
				System.out.println(field.clone);
				System.out.println(field.getNowMino().getName());
				System.out.println(field.getNowMino().getPosition());
				dp = field.getNowMino().getDropPoint(field.getBan());
//				break;
			}
			
			// 空中
			if (field.getNowMino().getPosition().y != dp.y) {
				step++;
				if (step == 40) {
					moveBottom();
					panel.repaint();
					step = 0;
				}
			}
			// 接地直前
			else {
				step2++;
				if (step2 == 80) {
					setti();
					panel.repaint();
					step2 = 0;
				}
			}
			
			if (panel != null) {
				panel.repaint();
			}
			
			try {
				this.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		if (panel != null) {
			panel.repaint();
		}
		
		System.out.println("自動落下ループから抜けました");
		
		
	}
	
	public void reset() {
		step2 = 0;
	}
	
	public void gameOver() {
		System.out.println("GAME OVER");
		gameover = true;
		field.gameOver();
		if (!clone) {
			System.out.println("NOT CLONE");
			DataPool.joutai = 2;
		}
	}

	public long getStartTime() {
		return start_time;
	}

	public void init() {
		step = 0;
		step2 = 0;
	}
	

	public void setti() {
		
		for (int x = 0; x < field.getNowMino().getMinoSize(); x++) {
			for (int y = 0; y < field.getNowMino().getMinoSize(); y++) {
				if (field.getNowMino().getPiece()[x][y].isBlock()) {
					try {
						field.getBan()[field.getNowMino().getPosition().x + x][field.getNowMino().getPosition().y + y] = (Block) field.getNowMino().getPiece()[x][y].clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		lineCheck();
		rize();
		
		// 新しいミノ出現
		field.setNowMino(field.getNextMinos().get(0));
		field.getNextMinos().remove(0);
		field.getNextMinos().add(field.getMino());
		if(!field.getNowMino().check(field.getNowMino().getPosition(), field.getBan())) {
			field.setMessage("GAME OVER");
			gameOver();
		}
		else {
			field.setCanHold(true);
			if (!clone) {
				init();
			}
			field.setOchihajime(true);
		}
	}

	
	public void rotateLeft() {
		if (field.getNowMino().rotateLeft(field.getBan())) {
			field.setNotMove(true);
			if (!clone) {
				reset();
			}
		}
	}

	public void rotateRight() {
		if (field.getNowMino().rotateRight(field.getBan())) {
			field.setNotMove(true);
			if (!clone) {
				reset();
			}
		}
	}
	
	public boolean moveLeft() {
		if (field.getNowMino().moveLeft(field.getBan())) {
			field.setNotMove(false);
			if (!clone) {
				reset();
			}
			return true;
		}
		return false;
	}
	
	public boolean moveRight() {
		if (field.getNowMino().moveRight(field.getBan())) {
			field.setNotMove(false);
			if (!clone) {
				reset();
			}
			return true;
		}
		return false;
	}
	
	public boolean moveBottom() {
		if (field.getNowMino().moveBottom(field.getBan())) {
			field.setNotMove(false);
			return true;
		}
		return false;
	}
	
	public void rize() {
		if (field.getNextRize() > 0) {
			for (int y = field.getBan()[0].length - 1; y >= field.getNextRize(); y--) {
				for (int x = 0; x < field.getBan().length; x++) {
					try {
						field.getBan()[x][y] = (Block) field.getBan()[x][y - field.getNextRize()].clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
			int rnd;
			for (int y = field.getNextRize() - 1; y >= 0; y--) {
				// 一定確率で穴の位置を変更
				if (new Random().nextInt(100) + 1 > field.getKakuritsu()) {
					// 再抽選時に同じ値を防ぐ
					while (field.getAke() == (rnd = new Random().nextInt(field.getBan().length))) {}
					field.setAke(rnd);
				}
				for (int x = 0; x < field.getBan().length; x++) {
					field.getBan()[x][y] = new Block();
					if (x != field.getAke()) {
						field.getBan()[x][y].createBlock();
						field.getBan()[x][y].setColors(new Color(128, 128, 128), new Color(255, 255, 255), new Color(64, 64, 64));
						field.getBan()[x][y].setShadowColors(new Color(255, 255, 0), new Color(255, 255, 0), new Color(255, 255, 0));
					}
				}
			}
			field.setNextRize(0);
		}
	}
	
	public void lineCheck() {
		boolean tspin = false;
		int line = 0;
		int rize = 0;
//		message = "";
		
		if (field.getNowMino() instanceof MinoT && field.isNotMove()) {
			int count = 0;
			Point[] kado = {new Point(0, 1), new Point(0, 3), new Point(2, 1), new Point(2, 3)};
			for (Point i : kado) {
				if (field.getNowMino().getPosition().x + i.x < 0 || field.getNowMino().getPosition().x + i.x >= field.getBan().length || field.getNowMino().getPosition().y + i.y < 0 || field.getBan()[field.getNowMino().getPosition().x + i.x][field.getNowMino().getPosition().y + i.y].isBlock()) {
					count++;
				}
			}
			if (count >= 3) {
				tspin = true;
			}
		}
		
		for (int i = 0; i < field.getBan()[0].length; i++) {
			boolean line_clear_flag = true;
			
			for (int k = 0; k < field.getBan().length; k++) {
				if (!field.getBan()[k][i].isBlock()) {
					line_clear_flag = false;
				}
			}
			
			
			if (line_clear_flag) {
				line++;
			}
			else if (line >= 1) {
				for (int j = 0; j < field.getBan().length; j++) {
					try {
						field.getBan()[j][i - line] = (Block) field.getBan()[j][i].clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		if (line >= 1) {
			for (int i = field.getBan()[0].length; i >= field.getBan()[0].length - line - 1; i--) {
				for (int j = 0; j < field.getBan().length; j++) {
					field.getBan()[j][field.getBan()[0].length - 1] = new Block();
				}
			}
		}
		
		// 相手に送るライン数
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
			if (field.isBTB()) {
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
				if (field.isBTB()) {
					rize += 1;
				}
				break;
			}
		}

		if (field.getNextRize() > 0) {
				
			// 相殺しきれてない
			if (field.getNextRize() >= rize) {
				field.setNextRize(field.getNextRize() - rize);
				rize = 0;
			}
			// 相殺できてさらに送れる
			else {
				rize -= field.getNextRize();
				field.setNextRize(0);
			}
		}
		
		field.addTotalLines(line);
		field.addTotalAttack(rize);
			
		if (field.getYourFiled() != null) {
			field.getYourFiled().setNextRize(field.getYourFiled().getNextRize() + rize);
		}
		
		// BTB, メッセージ
		if (tspin) {
			switch (line) {
			case 0:
				field.setMessage("T-SPIN");
				break;
			case 1:
				field.setMessage("T-SPIN SINGLE");
				break;
			case 2:
				field.setMessage("T-SPIN DOUBLE");
				break;
			case 3:
				field.setMessage("T-SPIN TRIPLE");
				break;
			}
			if (line >= 1) {
				if (field.isBTB()) {
					field.setMessage("BACK TO BACK " + field.getMessage());
				}
				field.setBTB(true);
			}
		}
		else {
			switch (line) {
			case 1: case 2: case 3:
				field.setMessage("");
				field.setBTB(false);
				break;
			case 4:
				field.setMessage("4-LINES");
				if (field.isBTB()) {
					field.setMessage("BACK TO BACK " + field.getMessage());
				}
				field.setBTB(true);
				break;
			}
		}
			
//		System.out.println(tspin + " " + line);
			
	}
		
	public void hardDrop() {
		field.getNowMino().hardDrop(field.getBan());
		setti();
		if (!clone) {
			reset();
		}
	}
	
	public void hold() {
		if (field.getHoldMino() == null) {
			field.setHoldMino(field.getNowMino());
			field.setNowMino(field.getNextMinos().get(0));
			field.getNextMinos().remove(0);
			field.getNextMinos().add(field.getMino());
			field.setCanHold(false);
		}
		else if (field.isCanHold()) {
			Mino tmp = field.getNowMino();
			field.setNowMino(field.getHoldMino());
			field.setHoldMino(tmp);
			field.setCanHold(false);
		}
		field.getHoldMino().initMino();
	}
	
}
