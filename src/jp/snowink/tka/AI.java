package jp.snowink.tka;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class AI extends Thread {
	
	private Field field = DataPool.vs_field_2;
	private JPanel panel = MainWindow.vs_panel;
	
	public void run() {
		
		while (!DataPool.gameover) {
			if (field.ochihajime) {
				field.ochihajime = false;
				execute(field, panel);
			}
			
			
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
	public void execute(Field field, JPanel panel) {
		
		try {
			// 手の検索
			ArrayList<Move> moves = Tools.getAllMove(field);
			
			// 最善手の選択
			int rnd = new Random().nextInt(moves.size());
			Point szs = moves.get(rnd).getPoint();
			int szs_r = moves.get(rnd).getRotate();
			System.out.println(szs + " (" + szs_r + ")");
			
			// 最善手の実行
			Tools.drop(field, szs, szs_r);
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
