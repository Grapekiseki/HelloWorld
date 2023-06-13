package Homework;

public class Person_ {
	private String name;
	private Vehicles vehicles;
	public Person_(String name, Vehicles vehicles) {
		this.name = name;
		this.vehicles = vehicles;
	}
	
	public void passRiver() {
		//instanceof用来判断是不是某一类型
		if(!(vehicles instanceof Boat)) {
			vehicles = VehiclesFactory.getBoat();
		}
		vehicles.work();
	}
	
	public void common() {
		if(!(vehicles instanceof Horse)) {
			vehicles = VehiclesFactory.getHorse();
		}
		//使用接口调用
		vehicles.work();
	}
	
}
