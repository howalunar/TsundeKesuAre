package jp.snowink.tsundekesuare;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame implements KeyListener {
	
	public static JPanel menu_panel = new MenuPanel();
	public static JPanel endless_panel = new EndlessPanel();
	public static JPanel vs_panel = new VsPanel();
	
	public static Field active_field;
	public static JPanel active_panel;
	
	public static boolean press = true;
	
	public static String[] menus = {"ENDLESS", "AUTO", "VS. COM", "CONFIG", "EXIT"};
	public static int now_menu = 0; 
	
	Timer timer_1 = null;;

	public MainWindow() {
				
		this.setTitle("TsundeKesuAre");
		this.setBounds(320, 220, 460, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.getContentPane().add(menu_panel, BorderLayout.CENTER);
		
		this.addKeyListener(this);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// メニュー画面の時のキー操作
		if (DataPool.gameover) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				now_menu = (now_menu + menus.length - 1) % menus.length;
				menu_panel.repaint();
			break;
			
			case KeyEvent.VK_DOWN:
				now_menu = (now_menu + menus.length + 1) % menus.length;
				menu_panel.repaint();
			break;
			
			case KeyEvent.VK_ENTER:
				switch (now_menu) {
				case 0:
					DataPool.load();
					DataPool.gameover = false;
					this.getContentPane().remove(menu_panel);
					this.getContentPane().add(endless_panel, BorderLayout.CENTER);
					this.getContentPane().validate();
					active_field = DataPool.endless_field;
					active_panel = endless_panel;
					DataPool.endless_field.getTimer().start();
					break;
				case 1:
					break;
				case 2:
					DataPool.load();
					DataPool.gameover = false;
					this.getContentPane().remove(menu_panel);
					this.getContentPane().add(vs_panel, BorderLayout.CENTER);
					this.getContentPane().validate();
					active_field = DataPool.vs_field_1;
					active_panel = vs_panel;
					DataPool.vs_field_1.getTimer().start();
					DataPool.vs_field_2.getTimer().start();
					DataPool.vs_field_1.setYourField(DataPool.vs_field_2);
					DataPool.vs_field_2.setYourField(DataPool.vs_field_1);
					new AI().start();
					break;
				case 3:
					break;
				case 4:
					System.exit(0);
					break;
				}
				break;
			}
		}
		
		else {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT: 
				active_field.moveLeft();
				active_panel.repaint();
				break;
			case KeyEvent.VK_DOWN:
				active_field.moveBottom();
				active_panel.repaint();
				break;
			case KeyEvent.VK_RIGHT:
				active_field.moveRight();
				active_panel.repaint();
				break;
			case KeyEvent.VK_UP:
				active_field.hardDrop();
				active_panel.repaint();
				break;
			case KeyEvent.VK_Z:
				active_field.hold();
				active_panel.repaint();
				break;
			case KeyEvent.VK_X:
				if (press) {
					active_field.rotateLeft();
					active_panel.repaint();
					press = false;
				}
				break;
			case KeyEvent.VK_C:
				if (press) {
					active_field.rotateRight();
					active_panel.repaint();
					press = false;
				}
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		press = true;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	public void gameOver() {
		DataPool.gameover = true;
//		System.out.println("GAME OVER");
		this.getContentPane().remove(active_panel);
		this.getContentPane().add(menu_panel, BorderLayout.CENTER);
		this.repaint();
	}
	
	
}
