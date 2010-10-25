package jp.snowink.tka;

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
		
			if (field.getNowMino().check(field.getNowMino().getPiece(), x, field.getNowMino().getPosition().y, field.getBlocks())) {

				if (field.getNowMino().getPosition().x > x) {
					for (int i = 1; i <= field.getNowMino().getPosition().x - x; i++) {
						field.moveLeft();
					}
				}
				else {
					for (int i = 1; i <= x - field.getNowMino().getPosition().x; i++) {
						field.moveRight();
					}
				}
//				field.getNowMino().hardDrop();
				break;
			}
		}
		
	}
	
	
	
}
