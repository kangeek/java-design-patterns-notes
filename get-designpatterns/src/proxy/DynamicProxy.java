package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

	private Object proxied;
	
	public DynamicProxy(Object proxied) {
		super();
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("DynamicProxy : " + proxy.getClass() +  ", method: " + method + ", args: " + args);
		if(method.getName().equals("doSomething"))
			System.out.println("doSomething() invocation detected");
		if(args != null) 
			for(Object arg : args)
				System.out.println(" " + arg);
		return method.invoke(proxied, args);
	}

}
