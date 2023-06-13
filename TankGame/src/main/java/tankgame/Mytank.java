package tankgame;

import java.util.Vector;

public class Mytank extends Tank {
	// 定义一个Shot对象，表示一个射击(线程)
	Shot shot = null;
	Vector<Shot> shots = new Vector<>();

	public Mytank(int x, int y) {
		super(x, y);
	}

	// 射击
	public void shotEnemytank() {
		if(shots.size() == 5) {
			return;
		}
		
		// 创建Shot对象,根据当前Mytank对象的位置和方向来创建Shot
		switch (getDirect()) {// 得到Mytank对象方向
		case 0:
			shot = new Shot(getX() + 20, getY(), 0);
			break;
		case 1:
			shot = new Shot(getX() + 60, getY() + 20, 1);
			break;
		case 2:
			shot = new Shot(getX() + 20, getY() + 60, 2);
			break;
		case 3:
			shot = new Shot(getX(), getY() + 20, 3);
			break;

		}
		
		//把新创建的shot加入到集合中
		shots.add(shot);
		//启动Shot线程
		new Thread(shot).start();
	}
}
