package observer;

public class ConcreteObserver implements Observer {

	private String name;
	
	public ConcreteObserver() {
	}
	
	public ConcreteObserver(String name) {
		this.name = name;
	}



	@Override
	public void update() {
		System.out.println(name + " updated...");
	}

}
