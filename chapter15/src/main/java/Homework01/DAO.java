package Homework01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DAO<T> {
	//定义个泛型类，在其中定义一个Map成员变量，Map的键为String类型，值为T类型
	private Map<String, T> map = new HashMap<>();
	
	public T get(String id) {
		return map.get(id);
	}
	//替换map中key为id的内容，改为entity对象
	public void update(String id, T entity) {
		map.put(id, entity);
	}
	//返回map中存放的所有T对象
	//遍历map，将map的所有value(T entity),封装到ArrayList返回即可
	public List<T> list() {
		//先创建一个ArrayList
		List<T> list = new ArrayList<>();
		
		//遍历map
		Set<String> keySet = map.keySet();
		for(String key : keySet) {
			list.add(map.get(key));
		}
		return list;
	}
	//删除指定id对象
	public void delete(String id) {
		map.remove(id);
	}
	//保存T类型的对象到Map成员变量中
	public void save(String id, T entity) {
		map.put(id, entity);
	}
}
