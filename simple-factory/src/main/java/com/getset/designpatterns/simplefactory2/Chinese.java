package com.getset.designpatterns.simplefactory2;

public class Chinese implements Person {

	public String sayHello(String name) {
		return "你好," + name;
	}

	public String sayGoodbye(String name) {
		return "再见," + name;
	}

}
