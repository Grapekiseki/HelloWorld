package tankgame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JFrame;

public class TankGame extends JFrame{
	//定义MyPanel
	MyPanel mp = null;
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		TankGame tankgame = new TankGame();
	}
	
	public TankGame() {
		System.out.println("选择 1：New game 2:Continuing");
		String key = scanner.next();
		mp = new MyPanel(key);
		//将mp放入到Thread，并启动
		Thread thread = new Thread(mp);
		thread.start();
		this.add(mp); //把面板(就是游戏的绘图区域)
		this.setSize(1300, 750);
		this.addKeyListener(mp);//让JFrame监听mp的键盘事件
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Recorder.keepRecord();
				System.exit(0);
			}
		});
	}
}
