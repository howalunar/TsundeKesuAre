package jp.snowink.tka;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class AI extends Thread {
	
	private Field field;
	private JPanel panel;
	
	public AI(Field field, JPanel panel) {
		this.field = field;
		this.panel = panel;
	}
	
	public void run() {
		
		while (!field.isGameOver()) {
			if (field.ochihajime) {
				field.ochihajime = false;
				execute(field, panel);
			}
			
			try {
				this.sleep(100);
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
//			Tools.removeAnaMove(moves, Tools.getAna());
			int min_sukima = 99;
			int min_dekoboko = 99;
			ArrayList<Move> best_moves = new ArrayList<Move>();
			
			for (int i = 0; i < moves.size(); i++) {
				Field f = moves.get(i).getField();
				int sukima = Tools.getSukima(f.getBan());
				int dekoboko = 0;
//				int dekoboko = Tools.dekoboko(f.getBan(), Tools.getAna());
				if (sukima < min_sukima) {
					min_sukima = sukima;
					min_dekoboko = dekoboko;
					best_moves.clear();
					best_moves.add(moves.get(i));
				}
				else if (sukima == min_sukima) {
					if (dekoboko < min_dekoboko) {
						min_dekoboko = dekoboko;
						best_moves.clear();
						best_moves.add(moves.get(i));
					}
					else if (dekoboko == min_dekoboko) {
						best_moves.add(moves.get(i));
					}
				}
			}

			int rnd = new Random().nextInt(best_moves.size());
			Move szs = best_moves.get(rnd);
			System.out.println(szs);
			
			// 最善手の実行
			Tools.drop(field, szs.getPoint(), szs.getRotate(), 50);
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
