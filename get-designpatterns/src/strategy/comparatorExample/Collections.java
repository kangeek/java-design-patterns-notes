package strategy.comparatorExample;

import java.util.ArrayList;
import java.util.ListIterator;

public class Collections {
	
	public static void sort(ArrayList<Student> list, Comparator c){
		
		if(c == null){
			return ;
		}else{
			Object[] v = list.toArray();
			Object temp = null;
			for(int i = 0; i < v.length - 1; i++){
				for(int j = i + 1; j < v.length; j++){
					if(c.compare(v[i], v[j]) > 0){
						temp = v[i];
						v[i] = v[j];
						v[j] = temp;
					}
				}
			}
			
			ListIterator<Student> i = list.listIterator();
			for (int j=0; j<v.length; j++) {
			    i.next();
			    i.set((Student)v[j]);
			}
		}
	}
}
