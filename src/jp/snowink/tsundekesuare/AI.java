package jp.snowink.tsundekesuare;

import java.util.Random;

import javax.swing.JPanel;

public class AI extends Thread {
	
	private Field field = DataPool.vs_field_2;
	private JPanel panel = MainWindow.vs_panel;
	
	public void run() {
		
		while (!DataPool.gameover) {
			
			if (field.ochihajime) {
				execute(field, panel);
				field.ochihajime = false;
			}
			
			
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
	public void execute(Field field, JPanel panel) {
		
		while (true) {
		
			int x = new Random().nextInt(field.getBlocks().length + 3) - 3;
		
			if (field.getNowMino().check(field.getNowMino().piece[field.getNowMino().now_piece], x, field.getNowMino().position.y, field.getBlocks())) {

				if (field.getNowMino().position.x > x) {
					for (int i = 1; i <= field.getNowMino().position.x - x; i++) {
						field.moveLeft();
					}
				}
				else {
					for (int i = 1; i <= x - field.getNowMino().position.x; i++) {
						field.moveRight();
					}
				}
//				field.getNowMino().hardDrop();
				break;
			}
		}
		
	}
	
	
	
}
