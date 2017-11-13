package com.getset.designpatterns.simplefactory2;

public class American implements Person {

	public String sayGoodbye(String name) {
		return "Hello," + name;
	}

	public String sayHello(String name) {
		return "Goodbye," + name;
	}

}
