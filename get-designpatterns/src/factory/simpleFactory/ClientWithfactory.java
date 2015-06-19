package factory.simpleFactory;

public class ClientWithfactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PersonFactory factory = new PersonFactory();
		Person chinese = factory.getPerson("Chinese");
		System.out.println(chinese.sayHello("张三"));
		System.out.println(chinese.sayGoodbye("张三"));
	}

}
