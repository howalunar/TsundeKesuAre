package jp.snowink.tka;
import java.util.ArrayList;
import java.util.Random;


public class DataPool {
	
	static Field endless_field;
	
	static Field vs_field_1;
	static Field vs_field_2;
	
	public static boolean gameover = true;
	
	public static void load() {
		Timer timer = new Timer();
		endless_field = new Field(timer);
		timer.setField(DataPool.endless_field);
		timer.setPanel(MainWindow.endless_panel);
		
		Timer timer_1 = new Timer();
		vs_field_1 = new Field(timer_1);
		timer_1.setField(DataPool.vs_field_1);
		timer_1.setPanel(MainWindow.vs_panel);
		
		Timer timer_2 = new Timer();
		vs_field_2 = new Field(timer_2);
		timer_2.setField(DataPool.vs_field_2);
		timer_2.setPanel(MainWindow.vs_panel);
	}
	

}
