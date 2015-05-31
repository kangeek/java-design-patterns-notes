package strategy.comparatorExample;

import java.util.ArrayList;


public class CollectionClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student("zhangsan", 10));
		list.add(new Student("lisi", 8));
		list.add(new Student("wangwu", 9));
		
		System.out.println(list);
		
		Collections.sort(list, new MyComparator());

		System.out.println(list);
	}

}

class MyComparator implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		Student s1 = (Student)o1;
		Student s2 = (Student)o2;
		if(s1.getAge() > s2.getAge()){
			return 1;
		}else if(s1.getAge() < s2.getAge()){
			return -1;
		}
		return 0;
	}
	
}
