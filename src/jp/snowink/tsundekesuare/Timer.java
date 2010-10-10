package jp.snowink.tsundekesuare;

import javax.swing.JPanel;

public class Timer extends Thread {
	
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
		
		
		while (!DataPool.gameover) {
			
			// �ڒn�O
			if (field.getNowMino().position.y != field.getNowMino().getDropPoint().y) {
				step++;
				if (step == 4) {
					field.getNowMino().moveBottom();
					panel.repaint();
					step = 0;
				}
			}
			// �ڒn���O
			else {
				step2++;
				if (step2 == 10) {
					field.setti();
					panel.repaint();
					step2 = 0;
				}
			}
			
			
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public void reset() {
		step = 0;
		step2 = 0;
	}
	
	
}
