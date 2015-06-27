package observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

	private List<Observer> list = new ArrayList<>();
	public ConcreteSubject() {
	}

	@Override
	public void addObserver(Observer observer) {
		list.add(observer);
	}

	@Override
	public void deleteObserver(Observer observer) {
		list.remove(observer);
	}

	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
		for(Observer observer : list) {
			observer.update();
		}
	}

}
