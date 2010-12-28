package jp.snowink.tka.ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import jp.snowink.tka.Controller;
import jp.snowink.tka.Field;
import jp.snowink.tka.Move;
import jp.snowink.tka.mino.Mino;
import jp.snowink.tka.mino.MinoI;

public class AI extends Thread {
	
	private Controller field;
	private JPanel panel;
	
	public AI(Controller field, JPanel panel) {
		this.field = field;
		this.panel = panel;
	}
	
	public void run() {
		
		while (!field.getField().isGameOver()) {
			if (field.getField().ochihajime) {
				field.getField().ochihajime = false;
				execute(field, panel);
			}
			
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	public void execute(Controller field, JPanel panel) {
		Move szs;

		try {
			
			if (field.getField().getNowMino() instanceof MinoI && AITools.minHeight(field.getField().getBan(), AITools.getAna()) >= 4) {
				szs = new Move(field.getField(), field.getField().getNowMino(), new Point(7, 17), 1);
			}
			
			else {

			// 手の検索
			ArrayList<Move> moves = AITools.getAllMove(field);
			
			// 最善手の選択
//			AITools.removeAnaMove(moves, AITools.getAna());
			int min_sukima = 99;
			int min_dekoboko = 99;
			ArrayList<Move> best_moves = new ArrayList<Move>();
			
			for (int i = 0; i < moves.size(); i++) {
				Field f = moves.get(i).getField();
				int sukima = AITools.getSukima(f.getBan());
				int dekoboko = AITools.dekoboko(f.getBan(), AITools.getAna());
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
			AITools.drop(field, szs.getPoint(), szs.getRotate(), 50);
			

			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
