package singleton;

/**
 * 饿汉式单例模式
 * 类加载时创建对象
 */
public class Singleton2 {
	private static Singleton2 instance = new Singleton2();
	private Singleton2() {}
	// 静态工厂方法
	public Singleton2 getInstance() {
		return instance;
	}
}
