package singleton;

/**
 * 懒汉式单例模式
 * 使用静态内部类延迟加载实例，因为加载Singleton3的时候，并不一定初始化静态内部类SingletonHolder，所以其实是在懒汉式单例里边嵌套了饿汉式单例
 */
public class Singleton3 {

	private Singleton3() {}
	
	private static class SingletonHolder {
		private final static Singleton3 INSTANCE = new Singleton3();
	}
	
	public Singleton3 getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
