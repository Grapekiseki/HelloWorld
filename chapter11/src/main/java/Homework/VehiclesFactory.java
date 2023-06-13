package Homework;

public class VehiclesFactory {
	//马始终是同一匹
	private static Horse horse = new Horse();
	private VehiclesFactory() {
		
	}
	public static Horse getHorse() {
		//return new Horse();
		return horse;
	}
	
	public static Boat getBoat() {
		return new Boat();
	}
}
