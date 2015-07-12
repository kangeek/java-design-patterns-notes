/**
 * 
 */
package proxy;

/**
 * 
 */
public class RealObject implements Interface {

	@Override
	public void doSomething() {
		System.out.println("RealObject doSomething");
	}

	@Override
	public void doSomethingElse(String work) {
		System.out.println("RealObject doSomething else: " + work);
	}

}
