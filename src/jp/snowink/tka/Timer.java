package jp.snowink.tka;

import java.awt.Point;

import javax.swing.JPanel;

public class Timer extends Thread implements Cloneable {
	
	public int step = 0;
	public int step2 = 0;
	public Field field;
	public JPanel panel;
	
	public void setField(Field field) {
		this.field = field;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public void run() {
		
		
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
		
		
	}
	
	public void reset() {
		step2 = 0;
	}
	
	public void init() {
		step = 0;
		step2 = 0;
	}

	
}
