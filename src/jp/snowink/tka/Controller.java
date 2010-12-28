package jp.snowink.tka;

import java.awt.Point;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import jp.snowink.tka.mino.Mino;

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
	
	public void gameOver() {
		gameover = true;
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
		
		while (!gameover) {
/*			
			Point dp = field.getNowMino().getDropPoint(field.getBan());
			
			if (dp == null) {
				System.out.println(field.getNowMino().getPosition());
				break;
			}
			
			// 空中
			if (field.getNowMino().getPosition().y != dp.y) {
				step++;
				if (step == 40) {
					field.moveBottom();
					panel.repaint();
					step = 0;
				}
			}
			// 接地直前
			else {
				step2++;
				if (step2 == 80) {
					field.setti();
					panel.repaint();
					step2 = 0;
				}
			}
*/			
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
		
		System.out.println("GAME OVER");
		
		
	}
	
	public void reset() {
		step2 = 0;
	}
	
	public long getStartTime() {
		return start_time;
	}

	public void init() {
		step = 0;
		step2 = 0;
	}
	
/*
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
		
		field.lineCheck();
		field.rize();
		
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
*/
	
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
	
	public void hardDrop() {
		field.getNowMino().hardDrop(field.getBan());
		field.setti();
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
