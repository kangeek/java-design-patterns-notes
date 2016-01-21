package singleton;

/**
 * 懒汉式单例模式
 * 调用getInstance方法时创建对象
 */
public class Singleton1 {

	private static Singleton1 instance;
	private Singleton1() {}
	
	// 静态工厂方法，线程不安全
//	public Singleton1 getInstance() {
//		if (instance == null) {
//			instance = new Singleton1();
//		}
//		return instance;
//	}
	
	// 静态工厂方法，线程安全，但是每次都要同步，会影响性能，毕竟99%的情况下是不需要同步的
//	public synchronized Singleton1 getInstance() {
//		if (instance == null) {
//			instance = new Singleton1();
//		}
//		return instance;
//	}
	
	// 静态工厂方法，线程安全，只有当需要创建对象的时候才进行同步，从而
	public Singleton1 getInstance() {
		if (instance == null) {
			synchronized(Singleton1.class) {
				if (instance == null) {
					instance = new Singleton1();
				}
			}
		}
		return instance;
	}
}
