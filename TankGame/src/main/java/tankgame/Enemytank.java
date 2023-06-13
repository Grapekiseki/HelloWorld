package tankgame;

import java.util.Vector;

public class Enemytank extends Tank implements Runnable {
	Vector<Shot> shots = new Vector<>();
	Vector<Enemytank> enemytanks = new Vector<>();
	boolean isLive = true;

	public Enemytank(int x, int y) {
		super(x, y);
	}
	//这里提供一个方法，可以将MyPanel的成员Vector<Enemytank> enemytanks = new Vector<>();
	//设置到Enemytank的成员enemytanks
	public void setEnemytanks(Vector<Enemytank> enemytanks) {
		this.enemytanks = enemytanks;
	}

	//编写方法，判断当前的这个敌人坦克，是否和enemytanks中的其他坦克发生了重叠或碰撞
	public boolean isTouchEnemyTank() {
		//判断当前敌人坦克(this) 方向
		switch (this.getDirect()) {
		case 0://上
			//让当前敌人坦克和其他所有的敌人坦克比较
			for(int i = 0; i < enemytanks.size(); i++) {
				//从vector中取出一个敌人坦克
				Enemytank enemytank = enemytanks.get(i);
				//不和自己比较
				if(enemytank != this) {
					//如果敌人坦克是上/下
					//x的范围[enemytank.getX(), enemytank.getX() + 40]
					//y的范围[enemytank.getY(), enemytank.getY() + 60]
					if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
						// 当前坦克左上角的坐标[this.getX(), this.getY()]
						if (this.getX() >= enemytank.getX() 
								&& this.getX() <= enemytank.getX() + 40
								&& this.getY() >= enemytank.getY() 
								&& this.getY() <= enemytank.getY() + 60) {
							return true;
						}
						//当前坦克右上角的坐标[this.getX() + 40, this.getY()]
						if (this.getX() +40 >= enemytank.getX() 
								&& this.getX()+40 <= enemytank.getX() + 40
								&& this.getY() >= enemytank.getY() 
								&& this.getY() <= enemytank.getY() + 60) {
							return true;
						}
					}
					//如果敌人坦克是右/左
					if(enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
						//当前坦克左上角的坐标[this.getX(), this.getY()]
						if (this.getX() >= enemytank.getX() 
								&& this.getX() <= enemytank.getX() + 60
								&& this.getY() >= enemytank.getY() 
								&& this.getY() <= enemytank.getY() + 40) {
							return true;
						}
						//当前坦克右上角的坐标[this.getX() + 40, this.getY()]
						if (this.getX() + 40 >= enemytank.getX() 
								&& this.getX() +40 <= enemytank.getX() + 60
								&& this.getY() >= enemytank.getY() 
								&& this.getY() <= enemytank.getY() + 40) {
							return true;
						}
					}
				}
			}
			break;
		case 1://右
			//让当前敌人坦克和其他所有的敌人坦克比较
			for(int i = 0; i < enemytanks.size(); i++) {
				//从vector中取出一个敌人坦克
				Enemytank enemytank = enemytanks.get(i);
				//不和自己比较
				if(enemytank != this) {
					//如果敌人坦克是上/下
					//x的范围[enemytank.getX(), enemytank.getX() + 40]
					//y的范围[enemytank.getY(), enemytank.getY() + 60]
					if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
						// 当前坦克右上角的坐标[this.getX(), this.getY()]
						if (this.getX() +60 >= enemytank.getX() 
								&& this.getX() +60 <= enemytank.getX() + 40
								&& this.getY() >= enemytank.getY() 
								&& this.getY() <= enemytank.getY() + 60) {
							return true;
						}
						//当前坦克右下角的坐标[this.getX() + 60, this.getY()+40]
						if (this.getX() +60 >= enemytank.getX() 
								&& this.getX()+60 <= enemytank.getX() + 40
								&& this.getY()+40 >= enemytank.getY() 
								&& this.getY()+40 <= enemytank.getY() + 60) {
							return true;
						}
					}
					//如果敌人坦克是右/左
					if(enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
						//当前坦克右上角的坐标[this.getX() + 60, this.getY()]
						if (this.getX() +60 >= enemytank.getX() 
								&& this.getX() +60 <= enemytank.getX() + 60
								&& this.getY()>= enemytank.getY() 
								&& this.getY() <= enemytank.getY() + 40) {
							return true;
						}
						//当前坦克右下角的坐标[this.getX() + 60, this.getY() +40]
						if (this.getX() + 60 >= enemytank.getX() 
								&& this.getX() +60 <= enemytank.getX() + 60
								&& this.getY() +40 >= enemytank.getY() 
								&& this.getY() +40 <= enemytank.getY() + 40) {
							return true;
						}
					}
				}
			}
			break;
		case 2://下
			//让当前敌人坦克和其他所有的敌人坦克比较
			for(int i = 0; i < enemytanks.size(); i++) {
				//从vector中取出一个敌人坦克
				Enemytank enemytank = enemytanks.get(i);
				//不和自己比较
				if(enemytank != this) {
					//如果敌人坦克是上/下
					//x的范围[enemytank.getX(), enemytank.getX() + 40]
					//y的范围[enemytank.getY(), enemytank.getY() + 60]
					if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
						// 当前坦克左下角的坐标[this.getX(), this.getY() +60]
						if (this.getX() >= enemytank.getX() 
								&& this.getX() <= enemytank.getX() + 40
								&& this.getY() +60 >= enemytank.getY() 
								&& this.getY() +60 <= enemytank.getY() + 60) {
							return true;
						}
						//当前坦克右下角的坐标[this.getX() + 40, this.getY() +60 ]
						if (this.getX() +40 >= enemytank.getX() 
								&& this.getX()+40 <= enemytank.getX() + 40
								&& this.getY()+60 >= enemytank.getY() 
								&& this.getY()+60 <= enemytank.getY() + 60) {
							return true;
						}
					}
					//如果敌人坦克是右/左
					if(enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
						//当前坦克左下角的坐标[this.getX(), this.getY() + 60]
						if (this.getX() >= enemytank.getX() 
								&& this.getX() <= enemytank.getX() + 60
								&& this.getY() + 60>= enemytank.getY() 
								&& this.getY() + 60<= enemytank.getY() + 40) {
							return true;
						}
						//当前坦克右下角的坐标[this.getX() + 40, this.getY() + 60]
						if (this.getX() + 40 >= enemytank.getX() 
								&& this.getX() + 40 <= enemytank.getX() + 60
								&& this.getY() + 60 >= enemytank.getY() 
								&& this.getY() + 60 <= enemytank.getY() + 40) {
							return true;
						}
					}
				}
			}
			break;
		case 3://左
			//让当前敌人坦克和其他所有的敌人坦克比较
			for(int i = 0; i < enemytanks.size(); i++) {
				//从vector中取出一个敌人坦克
				Enemytank enemytank = enemytanks.get(i);
				//不和自己比较
				if(enemytank != this) {
					//如果敌人坦克是上/下
					//x的范围[enemytank.getX(), enemytank.getX() + 40]
					//y的范围[enemytank.getY(), enemytank.getY() + 60]
					if (enemytank.getDirect() == 0 || enemytank.getDirect() == 2) {
						// 当前坦克左上角的坐标[this.getX(), this.getY()]
						if (this.getX() >= enemytank.getX() 
								&& this.getX() <= enemytank.getX() + 40
								&& this.getY() >= enemytank.getY() 
								&& this.getY() <= enemytank.getY() + 60) {
							return true;
						}
						//当前坦克左下角的坐标[this.getX() , this.getY() + 40]
						if (this.getX() >= enemytank.getX() 
								&& this.getX() <= enemytank.getX() + 40
								&& this.getY() + 40 >= enemytank.getY() 
								&& this.getY() + 40 <= enemytank.getY() + 60) {
							return true;
						}
					}
					//如果敌人坦克是右/左
					if(enemytank.getDirect() == 1 || enemytank.getDirect() == 3) {
						//当前坦克左上角的坐标[this.getX(), this.getY()]
						if (this.getX() >= enemytank.getX() 
								&& this.getX() <= enemytank.getX() + 60
								&& this.getY() >= enemytank.getY() 
								&& this.getY() <= enemytank.getY() + 40) {
							return true;
						}
						//当前坦克左下角的坐标[this.getX(), this.getY() + 40]
						if (this.getX() >= enemytank.getX() 
								&& this.getX() <= enemytank.getX() + 60
								&& this.getY() + 40 >= enemytank.getY() 
								&& this.getY() + 40 <= enemytank.getY() + 40) {
							return true;
						}
					}
				}
			}
			break;
		}
		return false;
	}

	@Override
	public void run() {
		while (true) {
			// 判断如果shots size() = 0,
			// 创建一颗子弹，让入到shots集合，并启动
			if (isLive && shots.size() < 3) {
				Shot s = null;
				
				switch (getDirect()) {
				case 0:
					s = new Shot(getX() + 20, getY(), 0);
					break;
				case 1:
					s = new Shot(getX() + 60, getY() + 20, 1);
					break;
				case 2:
					s = new Shot(getX() + 20, getY() + 60, 2);
					break;
				case 3:
					s = new Shot(getX(), getY() + 20, 3);
					break;
				}
				shots.add(s);
				new Thread(s).start();
			}

			// 根据坦克的方向来继续移动
			switch (getDirect()) {
			case 0:// 上
				for (int i = 0; i < 30; i++) {
					if (getY() > 0 && !isTouchEnemyTank()) {
						moveUp();
					}
					// 休眠50毫秒
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 1:// 右
				for (int i = 0; i < 30; i++) {
					if (getX() + 60 < 1000 && !isTouchEnemyTank()) {
						moveRight();
					}
					// 休眠50毫秒
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 2:// 下
				for (int i = 0; i < 30; i++) {
					if (getY() + 60 < 750 && !isTouchEnemyTank()) {
						moveDown();
					}
					// 休眠50毫秒
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 3:// 左
				for (int i = 0; i < 30; i++) {
					if (getX() > 0 && !isTouchEnemyTank()) {
						moveLeft();
					}
					// 休眠50毫秒
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			}
			// 然后随机的改变坦克方向
			setDirect((int) (Math.random() * 4));

			if (!isLive) { // 如果已被击中
				break; // 退出线程
			}
		}
	}

}
