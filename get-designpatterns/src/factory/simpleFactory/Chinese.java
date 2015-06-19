package factory.simpleFactory;

public class Chinese implements Person {

	@Override
	public String sayHello(String name) {
		return "你好," + name;
	}

	@Override
	public String sayGoodbye(String name) {
		return "再见," + name;
	}

}
