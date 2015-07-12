/**
 * 
 */
package proxy;

public class SimpleProxy implements Interface {

	private Interface object;
	
	public SimpleProxy(Interface object) {
		this.object = object;
	}

	@Override
	public void doSomething() {
		System.out.println("SimpleProxy do something");
		object.doSomething();
	}

	@Override
	public void doSomethingElse(String work) {
		System.out.println("SimpleProxy do something else: " + work);
		object.doSomethingElse(work);
	}

}
