package factory.simpleFactory;

public class American implements Person {

	@Override
	public String sayGoodbye(String name) {
		return "Hello," + name;
	}

	@Override
	public String sayHello(String name) {
		return "Goodbye," + name;
	}

}
