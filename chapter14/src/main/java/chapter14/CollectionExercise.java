package chapter14;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionExercise {
    public static void main(String[] args) {
        @SuppressWarnings({"all"})
        List<CollectionExercise.Dog> list = new ArrayList<>();
        list.add(new Dog("富贵", 6));
        list.add(new Dog("大黄", 10));
        list.add(new Dog("小狗", 2));
        
        //使用For增强
        for(Object dog : list) {
            System.out.println("list=" + dog);
        }
        
        //使用迭代器遍历
        Iterator<CollectionExercise.Dog> iterator = list.iterator();
        while(iterator.hasNext()) {
        	Object dog = iterator.next();
        	System.out.println("dog=" + dog);
        }
    }

    static class Dog {
        private String name;
        private int age;
        public Dog(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        @Override
        public String toString() {
            return "Dog [name=" + name + ", age=" + age + "]";
        }
    }
}
