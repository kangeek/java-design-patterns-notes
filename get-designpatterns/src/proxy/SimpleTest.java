package proxy;

public class SimpleTest {

	public static void consumer(Interface i) {
		i.doSomething();
		i.doSomethingElse("study");
	}
	public static void main(String[] args) {
		consumer(new RealObject());
		System.out.println("-----------------");
		consumer(new SimpleProxy(new RealObject()));
	}
}
