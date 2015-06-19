package factory.simpleFactory;

public class ClientWithoutFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Person chinesePerson = new Chinese();
		System.out.println(chinesePerson.sayHello("张三"));
		System.out.println(chinesePerson.sayGoodbye("张三"));
		
		Person americanPerson = new American();
		System.out.println(americanPerson.sayHello("Tom"));
		System.out.println(americanPerson.sayGoodbye("Tom"));
	}

}
