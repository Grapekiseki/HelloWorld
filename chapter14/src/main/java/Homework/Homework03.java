package Homework;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Homework03 {
	public static void main(String[] args) {
		HashMap<String, Integer> m = new HashMap<>();
		m.put("jack", 650);
		m.put("tom", 1200);
		m.put("smith", 2900);
		
		System.out.println(m);
		m.put("jack", 2600);
		System.out.println(m);
		
		//Set keySet = m.keySet();
		
		//在遍历 Map 时，使用 entrySet() 方法来获取键值对集合，
		//使用 Map.Entry 类型遍历集合，可以避免类型转换问题；
		for (Map.Entry<String, Integer> entry : m.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue() + 100;
			m.put(key, value);
		}
		System.out.println(m);
		
		//获取entrySet
		Set<Map.Entry<String, Integer>> entrySet = m.entrySet();
		Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)iterator.next();
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		
		System.out.println("===遍历所有的工资===");
		Collection values = m.values();
		for(Object value : values) {
			System.out.println("工资=" + value);
		}
	}
}

