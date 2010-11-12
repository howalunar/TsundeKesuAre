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
			int min_sukima = 99;
			ArrayList<Move> min_sukima_moves = new ArrayList<Move>();
			
			for (int i = 0; i < moves.size(); i++) {
				Field f = moves.get(i).getField();
				int sukima = Tools.getSukima(f.getBan());
				System.out.print(sukima);
				if (sukima < min_sukima) {
					min_sukima = sukima;
					min_sukima_moves.clear();
					min_sukima_moves.add(moves.get(i));
				}
				else if (sukima == min_sukima) {
					min_sukima_moves.add(moves.get(i));
				}
			}
			
			for (Move i : min_sukima_moves) {
				System.out.println(i.getPoint() + " (" + i.getRotate() + ")");
			}
			
			int rnd = new Random().nextInt(min_sukima_moves.size());
			Point szs = min_sukima_moves.get(rnd).getPoint();
			int szs_r = min_sukima_moves.get(rnd).getRotate();
			System.out.println(szs + " (" + szs_r + ")");
			
			// 最善手の実行
			Tools.drop(field, szs, szs_r);
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
