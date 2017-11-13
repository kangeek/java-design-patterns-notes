package com.getset.designpatterns.simplefactory2;

public class PersonFactory {
	
	public Person getPerson(String name) {
		if("Chinese".equals(name)){
			return new Chinese();
		}else if("American".equals(name)){
			return new American();
		}else{
			return null;
		}
		
	}

}
