package Homework;

public class Homework07 {
	public static void main(String[] args) {
		Car car = new Car(50);
		car.getAir().flow();
		Car car2 = new Car(-10);
		car2.getAir().flow();
		Car car3 = new Car(20);
		car3.getAir().flow();
	}
}

class Car {
	private int temperature;
	
	
	public Car(int temperature) {
		this.temperature = temperature;
	}


	class Air {
		public void flow() {
			if(temperature > 40) {
				System.out.println("温度大于40，吹冷气");
			}else if(temperature < 0) {
				System.out.println("温度小于0，吹暖气");
			}else {
				System.out.println("温度正常，空调停止");
			}
		}
	}
	//返回一个Air对象
	public Air getAir() {
		return new Air();
	}
}
