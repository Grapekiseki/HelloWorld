package tankgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JPanel;


//为了监听键盘事件,实现KeyListener
//为了让Panel不停的重绘子弹，需要将Mypanel实现Runnable，当做一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {
	private static final long serialVersionUID = 1L;

	// 定义我的坦克
	Mytank mytank = null;
	// 定义敌人坦克，放入到Vector
	Vector<Enemytank> enemytanks = new Vector<>();
	// 定义一个存放Node对象的Vector，用于恢复敌人坦克的坐标和方向
	Vector<Node> nodes = new Vector<>();
	// 定义一个Vector，用于存放炸弹
	// 当子弹击中坦克时，加入一个Bomb对象到bombs
		Vector<Bomb> bombs = new Vector<>();
	int enemytankSize = 3;  //生成3个坦克

	// 定义三张图片，用于显示爆炸效果
	Image image1 = null;
	Image image2 = null;
	Image image3 = null;

	public MyPanel(String key) {
		nodes = Recorder.getNodesAndEnemyTankRec();
		//将MyPanel对象的 enemytanks 设置给Recorder的enemytanks
		Recorder.setEnemytanks(enemytanks);
		mytank = new Mytank(500, 100); // 初始化自己坦克
		mytank.setSpeed(5);
		
		switch(key) {
		case "1":
			for (int i = 0; i < enemytankSize; i++) {
				// 创建一个敌人的坦克
				Enemytank enemytank = new Enemytank((100 * (i + 1)), 0);
				//将enemytanks 设置给 enemytank
				enemytank.setEnemytanks(enemytanks);
				// 设置方向
				enemytank.setDirect(2);

				// 启动敌人坦克线程
				new Thread(enemytank).start();

				// 给该enemytank加入一颗子弹
				Shot shot = new Shot(enemytank.getX() + 20, enemytank.getY() + 60, enemytank.getDirect());
				// 加入enemytank的Vector成员
				enemytank.shots.add(shot);
				// 启动shot 对象
				new Thread(shot).start();
				// 加入
				enemytanks.add(enemytank);

			}
			break;
		case "2"://继续上局游戏
			for (int i = 0; i < nodes.size(); i++) {
				Node node = nodes.get(i);
				// 创建一个敌人的坦克
				Enemytank enemytank = new Enemytank(node.getX(), node.getY());
				//将enemytanks 设置给 enemytank
				enemytank.setEnemytanks(enemytanks);
				// 设置方向
				enemytank.setDirect(node.getDirect());

				// 启动敌人坦克线程
				new Thread(enemytank).start();

				// 给该enemytank加入一颗子弹
				Shot shot = new Shot(enemytank.getX() + 20, enemytank.getY() + 60, enemytank.getDirect());
				// 加入enemytank的Vector成员
				enemytank.shots.add(shot);
				// 启动shot 对象
				new Thread(shot).start();
				// 加入
				enemytanks.add(enemytank);

			}
			break;
		default:
			System.out.println("你的输入有误..");
		}
		
		// 初始化敌人坦克
		
		// 初始化图片对象
		image1 = Toolkit.getDefaultToolkit().getImage("E:\\edu\\bomb_1.gif");
		image2 = Toolkit.getDefaultToolkit().getImage("E:\\edu\\bomb_2.gif");
		image3 = Toolkit.getDefaultToolkit().getImage("E:\\edu\\bomb_3.gif");

	}
	
	//编写方法，显示我方击毁敌方坦克信息
	public void showInfo(Graphics g) {
		g.setColor(Color.BLACK);
		Font font = new Font("宋体", Font.BOLD, 25);
		g.setFont(font);
		
		g.drawString("Kill Tank", 1020, 30);
		drawTank(1020, 60, g, 0 , 1);
		g.setColor(Color.BLACK);
		g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 1000, 750); // 填充矩形，默认为黑色
		showInfo(g);
		if (mytank != null && mytank.isLive) {
			// 画出坦克-封装方法
			drawTank(mytank.getX(), mytank.getY(), g, mytank.getDirect(), 0);
		}

		// 画出mytank射击的子弹
		for (int i = 0; i < mytank.shots.size(); i++) {
			Shot shot = mytank.shots.get(i);
			if (shot != null && shot.isLive == true) {
				// g.fill3DRect(mytank.shot.x, mytank.shot.y, 2, 2, false);
				g.draw3DRect(shot.x, shot.y, 1, 1, false);
			} else {
				mytank.shots.remove(shot);
			}
		}
		// 如果bombs集合中有对象，就画出
		for (int i = 0; i < bombs.size(); i++) {
			// 取出炸弹
			Bomb bomb = bombs.get(i);
			// 根据当前这个bomb对象的life 值去画出对应的图片
			if (bomb.life > 6) {
				g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
			} else if (bomb.life > 3) {
				g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
			} else {
				g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
			}
			// 让炸弹的生命值减少
			bomb.lifeDown();
			// 如果bomb life为0,就从bombs的集合中删除
			if (bomb.life == 0) {
				bombs.remove(bomb);
			}
		}
		// 画出敌人坦克，遍历Vector
		for (int i = 0; i < enemytanks.size(); i++) {
			// 取出坦克
			Enemytank enemytank = enemytanks.get(i);
			// 判断当前坦克是否还存活
			if (enemytank.isLive) {
				drawTank(enemytank.getX(), enemytank.getY(), g, enemytank.getDirect(), 1);
				// 画出enemytank所有子弹
				for (int j = 0; j < enemytank.shots.size(); j++) {
					// 取出子弹
					Shot shot = enemytank.shots.get(j);
					// 绘制
					if (shot.isLive) {// isLive==true
						g.draw3DRect(shot.x, shot.y, 1, 1, false);
					} else {
						// 从Vector移除
						enemytank.shots.remove(shot);
					}
				}
			}
		}
	}

	// 编写方法，画出坦克
	/*
	 * x 坦克的左上角x坐标 y 坦克的左上角y坐标 g 画笔 direct 坦克方向(上下左右) type 坦克类型
	 */
	public void drawTank(int x, int y, Graphics g, int direct, int type) {
		// 根据不同类型坦克，来设置不同颜色
		switch (type) {
		case 0: // 玩家的坦克
			g.setColor(Color.cyan);
			break;
		case 1: // 敌人的坦克
			g.setColor(Color.yellow);
			break;
		}

		// 根据坦克方向，来绘制坦克
		// direct 表示方向(0:向上 1:右 2:下 3:左
		switch (direct) {
		case 0: // 表示向上
			g.fill3DRect(x, y, 10, 60, false); // 画出坦克左轮
			g.fill3DRect(x + 30, y, 10, 60, false); // 画出坦克右轮
			g.fill3DRect(x + 10, y + 10, 20, 40, false); // 画出坦克盖子
			g.fillOval(x + 10, y + 20, 20, 20); // 画出坦克圆形盖子
			g.drawLine(x + 20, y + 30, x + 20, y - 5); // 画出炮筒
			break;
		case 1:// 表示向右
			g.fill3DRect(x, y, 60, 10, false); // 画出坦克左轮
			g.fill3DRect(x, y + 30, 60, 10, false); // 画出坦克右轮
			g.fill3DRect(x + 10, y + 10, 40, 20, false); // 画出坦克盖子
			g.fillOval(x + 20, y + 10, 20, 20); // 画出坦克圆形盖子
			g.drawLine(x + 30, y + 20, x + 65, y + 20); // 画出炮筒
			break;
		case 2:// 表示向下
			g.fill3DRect(x, y, 10, 60, false); // 画出坦克左轮
			g.fill3DRect(x + 30, y, 10, 60, false); // 画出坦克右轮
			g.fill3DRect(x + 10, y + 10, 20, 40, false); // 画出坦克盖子
			g.fillOval(x + 10, y + 20, 20, 20); // 画出坦克圆形盖子
			g.drawLine(x + 20, y + 30, x + 20, y + 65); // 画出炮筒
			break;
		case 3:// 表示向左
			g.fill3DRect(x, y, 60, 10, false); // 画出坦克左轮
			g.fill3DRect(x, y + 30, 60, 10, false); // 画出坦克右轮
			g.fill3DRect(x + 10, y + 10, 40, 20, false); // 画出坦克盖子
			g.fillOval(x + 20, y + 10, 20, 20); // 画出坦克圆形盖子
			g.drawLine(x + 30, y + 20, x - 5, y + 20); // 画出炮筒
			break;
		default:
			System.out.println("暂时没有处理");
		}
	}

	public void hitenemytank() {
		// 遍历mytank的所有子弹
		for (int j = 0; j < mytank.shots.size(); j++) {
			Shot shot = mytank.shots.get(j);
			// 判断是否击中敌人坦克
			if (shot != null && shot.isLive) { // 当我的子弹还存活
				// 遍历敌人所有的坦克
				for (int i = 0; i < enemytanks.size(); i++) {
					Enemytank enemytank = enemytanks.get(i);
					hittank(mytank.shot, enemytank);
				}
			}
		}
	}

	// 编写方法，判断敌人坦克是否击中我的坦克
	public void hitmytank() {
		// 遍历所有的敌人坦克
		for (int i = 0; i < enemytanks.size(); i++) {
			// 取出敌人坦克
			Enemytank enemytank = enemytanks.get(i);
			// 遍历enemytank对象所有子弹
			for (int j = 0; j < enemytank.shots.size(); j++) {
				// 取出子弹
				Shot shot = enemytank.shots.get(j);
				// 判断shot 是否击中我的坦克
				if (mytank.isLive && shot.isLive) {
					hittank(shot, mytank);
				}
			}
		}
	}

	// 编写方法，判断我方子弹是否击中敌人坦克
	public void hittank(Shot s, Tank enemytank) {
		// 判断s 是否击中坦克
		switch (enemytank.getDirect()) {
		case 0:// 坦克向上
		case 2:// 坦克向下
			if (s.x > enemytank.getX() && s.x < enemytank.getX() + 40 && s.y > enemytank.getY()
					&& s.y < enemytank.getY() + 60) {
				s.isLive = false;
				enemytank.isLive = false;
				// 当我方子弹击中敌人坦克后，将enemytank从 Vector拿掉
				enemytanks.remove(enemytank);
				//当我方击毁一个敌人坦克后，就对数据allEnemyTankNum++
				//因为enemytank可以是Mytank也可以是Enemytank
				if(enemytank instanceof Enemytank) {
					Recorder.addAllEnemyTankNum();
				}
				// 创建Bomb对象，加入到bombs集合
				Bomb bomb = new Bomb(enemytank.getX(), enemytank.getY());
				bombs.add(bomb);

			}
			break;
		case 1:
		case 3:
			if (s.x > enemytank.getX() && s.x < enemytank.getX() + 60 && s.y > enemytank.getY()
					&& s.y < enemytank.getY() + 40) {
				s.isLive = false;
				enemytank.isLive = false;
				// 当我方子弹击中敌人坦克后，将enemytank从 Vector拿掉
				enemytanks.remove(enemytank);
				//因为enemytank可以是Mytank也可以是Enemytank
				if(enemytank instanceof Enemytank) {
					Recorder.addAllEnemyTankNum();
				}
				// 创建Bomb对象，加入到bombs集合
				Bomb bomb = new Bomb(enemytank.getX(), enemytank.getY());
				bombs.add(bomb);
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	// 处理WDSA键按下的情况
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) { // 向上
			// 改变坦克的方向
			mytank.setDirect(0);
			// 修改坦克的坐标
			if (mytank.getY() > 0) {
				mytank.moveUp();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_D) { // 向右
			mytank.setDirect(1);
			if (mytank.getX() + 70 < 1000) {
				mytank.moveRight();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) { // 向下
			mytank.setDirect(2);
			if (mytank.getY() + 90 < 750) {
				mytank.moveDown();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_A) { // 向左
			mytank.setDirect(3);
			if (mytank.getX() > 0) {
				mytank.moveLeft();
			}
		}

		// 如果用户按下的是J，就发射
		if (e.getKeyCode() == KeyEvent.VK_J) {

			// 判断mytank的子弹是否销毁
			// if (mytank.shot == null || !(mytank.shot.isLive)) {
			// mytank.shotEnemytank();
			// }
			// 发射多颗子弹
			mytank.shotEnemytank();
		}

		// 面板重绘
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void run() {// 每隔100毫秒，重绘区域
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 判断是否击中敌人坦克
			if (mytank.shot != null && mytank.shot.isLive) { // 当我的子弹还存活
				// 遍历敌人所有的坦克
				for (int i = 0; i < enemytanks.size(); i++) {
					Enemytank enemytank = enemytanks.get(i);
					hittank(mytank.shot, enemytank);
				}
			}
			// 判断敌人坦克是否击中我们
			hitmytank();
			hitenemytank();
			this.repaint();

		}
	}

}
