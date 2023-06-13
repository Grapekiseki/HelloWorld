package threaduse;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallMove extends JFrame {
	private static final long serialVersionUID = 1L;

	MyPanel mp = null;

	public static void main(String[] args) {
		BallMove ballMove = new BallMove();
	}

	// 构造器
	public BallMove() {
		mp = new MyPanel();
		this.add(mp);
		this.setSize(400, 300);
		// 窗口JFrame,对象可以监听键盘事件，即可以监听到面板发生的键盘事件
		this.addKeyListener(mp);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

//面板，可以画出小球
//KeyListener是监听器，可以监听键盘事件
class MyPanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;

	// 为了让小球可以移动，把他的左上角的坐标(x,y)设置变量
	int x = 10;
	int y = 10;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillOval(x, y, 20, 20);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println((char)e.getKeyCode() + "被按下..");
		if (e.getKeyCode() == KeyEvent.VK_S) { //向下
			y++;
		} else if (e.getKeyCode() == KeyEvent.VK_W) { //向上
			y--;
		} else if (e.getKeyCode() == KeyEvent.VK_A) { //向左
			x--;
		} else if (e.getKeyCode() == KeyEvent.VK_D) { //向右
			x++;
		}
		this.repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
