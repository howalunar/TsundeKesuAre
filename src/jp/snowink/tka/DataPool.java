package jp.snowink.tka;
import java.util.ArrayList;
import java.util.Random;


public class DataPool {
	
	public static Field endless_field;
	public static Controller endless_timer;
	
	public static Field vs_field_1;
	public static Controller vs_ftimer_1;
	public static Field vs_field_2;
	public static Controller vs_ftimer_2;
	
	public static int joutai = 0;
	// 0: メニュー画面
	// 1: ゲーム操作
	// 2: ゲームオーバー
	
	public static boolean gameover = true;
		
	public static void load() {
		endless_field = new Field();
		endless_timer = new Controller(endless_field, MainWindow.endless_panel);
		
		vs_field_1 = new Field();
		vs_ftimer_1 = new Controller(vs_field_1, MainWindow.vs_panel);
		
		vs_field_2 = new Field();
		vs_ftimer_2 = new Controller(vs_field_2, MainWindow.vs_panel);
	}

}
