package jp.snowink.tka;

import java.awt.Point;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

public class Timer extends Thread implements Cloneable {
	
	public int step = 0; // 自動落下のステップ
	public int step2 = 0; // 接地のステップ
	public Field field;
	public JPanel panel;
	public long start_time = Calendar.getInstance().getTimeInMillis();
	
	public void setField(Field field) {
		this.field = field;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void run() {
		
		start_time = Calendar.getInstance().getTimeInMillis();
		
		while (!field.isGameOver()) {
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
			panel.repaint();
			
			try {
				this.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
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
	
	
}
