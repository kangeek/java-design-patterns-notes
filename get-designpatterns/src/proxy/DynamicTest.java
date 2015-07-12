package proxy;

import java.lang.reflect.Proxy;

public class DynamicTest {

	public static void consumer(Interface i) {
		i.doSomething();
		i.doSomethingElse("study");
	}
	public static void main(String[] args) {
		RealObject real = new RealObject();
		consumer(real);
		System.out.println("-----------------");
		Interface i = (Interface) Proxy.newProxyInstance(real.getClass().getClassLoader(), new Class[]{Interface.class}, new DynamicProxy(real));
		consumer(i);
		
	}
}
