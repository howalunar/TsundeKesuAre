package jp.snowink.tka;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import jp.snowink.tka.mino.Mino;
import jp.snowink.tka.mino.MinoI;

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
		Move szs;
		
		try {
			
			if (field.getNowMino() instanceof MinoI && Tools.minHeight(field.getBan(), Tools.getAna()) >= 4) {
				szs = new Move(field, field.getNowMino(), new Point(7, 17), 1);
			}
			
			else {
			// 手の検索
			ArrayList<Move> moves = Tools.getAllMove(field);
			
			// 最善手の選択
			Tools.removeAnaMove(moves, Tools.getAna());
			int min_sukima = 99;
			int min_dekoboko = 99;
			ArrayList<Move> best_moves = new ArrayList<Move>();
			
			for (int i = 0; i < moves.size(); i++) {
				Field f = moves.get(i).getField();
				int sukima = Tools.getSukima(f.getBan());
				int dekoboko = Tools.dekoboko(f.getBan(), Tools.getAna());
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
			szs = best_moves.get(rnd);
			
			}
			
			System.out.println(szs);
			
			// 最善手の実行
			Tools.drop(field, szs.getPoint(), szs.getRotate(), 200);
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
