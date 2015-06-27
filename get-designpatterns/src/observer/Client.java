package observer;

public class Client {

	public static void main(String[] args) {

		Subject s = new ConcreteSubject();
		Observer a = new ConcreteObserver("a");
		Observer b = new ConcreteObserver("b");
		Observer c = new ConcreteObserver("c");
		
		s.addObserver(a);
		s.addObserver(b);
		s.addObserver(c);
		
		s.notifyAllObservers();
		
		s.deleteObserver(b);
		s.notifyAllObservers();
	}

}
