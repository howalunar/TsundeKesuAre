package org.snowink.tka;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.snowink.tka.ai.AI;



public class MainWindow extends JFrame implements KeyListener {
	
	public static JPanel menu_panel = new MenuPanel();
	public static JPanel endless_panel = new EndlessPanel();
	public static JPanel vs_panel = new VsPanel();
	
	public static Field active_field;
	public static Controller active_timer;
	public static JPanel active_panel;
	
	public static boolean press = true;
	
	public static String[] menus = {"MAN ENDLESS", "COM ENDLESS", "MAN VS. COM", "COM VS. COM", "CONFIG", "ABOUT", "EXIT"};
	public static int now_menu = 0; 
	
	Controller timer_1 = null;

	public MainWindow() {
				
		this.setTitle("TsundeKesuAre");
		this.setBounds(320, 220, 460, 345);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.getContentPane().add(menu_panel, BorderLayout.CENTER);
		
		this.addKeyListener(this);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// メニュー画面の時のキー操作
		if (DataPool.joutai == 0) {
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
					active_timer = DataPool.endless_timer;
					active_field = DataPool.endless_field;
					active_panel = endless_panel;
					active_timer.gameStart();
					active_timer.start();
					DataPool.joutai = 1;
					break;
				case 1:
					DataPool.load();
					DataPool.gameover = false;
					this.getContentPane().remove(menu_panel);
					this.getContentPane().add(endless_panel, BorderLayout.CENTER);
					this.getContentPane().validate();
					active_timer = DataPool.endless_timer;
					active_field = DataPool.endless_field;
					active_panel = endless_panel;
					active_timer.gameStart();
					active_timer.start();
					new AI(DataPool.endless_timer).start();
					break;
				case 2:
					DataPool.load();
					DataPool.gameover = false;
					this.getContentPane().remove(menu_panel);
					this.getContentPane().add(vs_panel, BorderLayout.CENTER);
					this.getContentPane().validate();
					active_timer = DataPool.vs_ftimer_1;
					active_field = DataPool.vs_field_1;
					active_panel = vs_panel;
					active_timer.start();
					DataPool.joutai = 1;
					DataPool.vs_ftimer_2.gameStart();
					DataPool.vs_ftimer_2.start();
					DataPool.vs_field_1.setYourField(DataPool.vs_field_2);
					DataPool.vs_field_2.setYourField(DataPool.vs_field_1);
					new AI(DataPool.vs_ftimer_2).start();
					break;
				case 3:
					break;
				case 6:
					System.exit(0);
					break;
				}
				break;
			}
		}
		
		else if (DataPool.joutai == 1) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT: 
				active_timer.moveLeft();
				active_panel.repaint();
				break;
			case KeyEvent.VK_DOWN:
				active_timer.moveBottom();
				active_panel.repaint();
				break;
			case KeyEvent.VK_RIGHT:
				active_timer.moveRight();
				active_panel.repaint();
				break;
			case KeyEvent.VK_UP:
				active_timer.hardDrop();
				active_panel.repaint();
				break;
			case KeyEvent.VK_Z:
				active_timer.hold();
				active_panel.repaint();
				break;
			case KeyEvent.VK_X:
				if (press) {
					active_timer.rotateLeft();
					active_panel.repaint();
					press = false;
				}
				break;
			case KeyEvent.VK_C:
				if (press) {
					active_timer.rotateRight();
					active_panel.repaint();
					press = false;
				}
				break;
			}
		}
		else if (DataPool.joutai == 2) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				EndlessPanel.now_menu = (EndlessPanel.now_menu + EndlessPanel.menus.length - 1) % menus.length;
				endless_panel.repaint();
			break;
			
			case KeyEvent.VK_DOWN:
				EndlessPanel.now_menu = (EndlessPanel.now_menu + EndlessPanel.menus.length + 1) % EndlessPanel.menus.length;
				endless_panel.repaint();
			break;
			
			case KeyEvent.VK_ENTER:
				switch (EndlessPanel.now_menu) {
				case 0:
//					this.getContentPane().remove(active_panel);
//					this.getContentPane().add(menu_panel, BorderLayout.CENTER);
					this.repaint();
					DataPool.joutai = 0;
					EndlessPanel.now_menu = 0;
					DataPool.load();
					DataPool.gameover = false;
//					this.getContentPane().remove(menu_panel);
//					this.getContentPane().add(endless_panel, BorderLayout.CENTER);
					this.getContentPane().validate();
					active_field = DataPool.endless_field;
					active_timer = DataPool.endless_timer;
					active_panel = endless_panel;
					active_timer.start();
					DataPool.joutai = 1;
					EndlessPanel.menus[2] = "つ△";
				break;
				case 1:
					this.getContentPane().remove(active_panel);
					this.getContentPane().add(menu_panel, BorderLayout.CENTER);
					this.repaint();
					DataPool.joutai = 0;
					EndlessPanel.now_menu = 0;
					EndlessPanel.menus[2] = "つ△";
				break;
				case 2:
					EndlessPanel.menus[2] = "ﾏﾃｗｗｗ";
					this.repaint();
				break;
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
		System.out.println("GAME OVER");
//		this.getContentPane().remove(active_panel);
//		this.getContentPane().add(menu_panel, BorderLayout.CENTER);
//		this.repaint();
	}
	
	
}
