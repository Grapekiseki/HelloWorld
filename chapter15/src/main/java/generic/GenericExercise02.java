package generic;
import java.util.ArrayList;
import java.util.Comparator;

public class GenericExercise02 {
	public static void main(String[] args) {
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(new Employee("tom", 20000, new MyDate(1980, 12, 11)));
		employees.add(new Employee("jack", 12000, new MyDate(2001, 12, 12)));
		employees.add(new Employee("tom", 50000, new MyDate(1980, 12, 10)));
		
		System.out.println(employees);
		
		employees.sort(new Comparator<>() {
			@Override
			public int compare(Employee emp1, Employee emp2) {
				//先按照name排序
				//先对传入的参数进行认证
				if(!(emp1 instanceof Employee && emp2 instanceof Employee)) {
					System.out.println("类型不正确");
					return 0;
				}
				//再比较name
				int i = emp1.getName().compareTo(emp2.getName());
				if(i != 0) {
					return i;
				}
				
				return emp1.getBirthday().compareTo(emp2.getBirthday());
			}
		});
		System.out.println("===对雇员进行排序后===");
		System.out.println(employees);
		
		
	}
}

class Employee {
	private String name;
	private double sal;
	private MyDate birthday;
	public Employee(String name, double sal, MyDate birthday) {
		super();
		this.name = name;
		this.sal = sal;
		this.birthday = birthday;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public MyDate getBirthday() {
		return birthday;
	}
	public void setBirthday(MyDate birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "\nEmployee [name=" + name + ", sal=" + sal + ", birthday=" + birthday + "]";
	}
	
}

class MyDate implements Comparable<MyDate>{
	private int year;
	private int month;
	private int day;
	public MyDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "[year=" + year + ", month=" + month + ", day=" + day + "]";
	}
	@Override
	public int compareTo(MyDate o) { //把对年月日的比较放在这里
		//由于下面是对birthday的比较，因此最好放在MyDate()里完成
		//如果name相同,就比较 birthday - year
		int yearMinus = year - o.getYear();
		if(yearMinus != 0) {
			return yearMinus;
		}
		
		//如果year相同,就比较 birthday - month
		int monthMinus = month - o.getMonth();
		if(monthMinus != 0) {
			return monthMinus;
		}
		
		//如果year,month都相同,就比较 birthday - day
		return day - o.getDay();
	}
	
	
}