package threaduse;

public class Thread01 {
	public static void main(String[] args) {
		Cat cat = new Cat();
		cat.start();
	}
}

class Cat extends Thread {
	
	int times = 0;
	
	@Override
	public void run() {// 重写run方法，写上自己的业务逻辑
		while (true) {
			System.out.println("你好" + (++times));
			// 让该线程休眠
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(times == 10) {
				break;
			}
		}
	}

}