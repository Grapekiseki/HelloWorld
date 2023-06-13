package Homework01;

import java.util.List;

public class Homework01 {
	public static void main(String[] args) {
		
		
		
		
		DAO<User> dao = new DAO<>();
		dao.save("001", new User(1, 10, "jack"));
		dao.save("002", new User(2, 18, "king"));
		dao.save("003", new User(3, 38, "smith"));
		
		List<User> list = dao.list();
		
		System.out.println("list" + list);
		
		dao.update("003", new User(3, 58, "milan"));
		dao.delete("001");
		System.out.println("修改后");
		list = dao.list();
		System.out.println("list" + list);
	}
	
}