package singleton;

/**
 * 枚举类型
 * 借助枚举的序列化机制，能够绝对防止多次实例化，即使是在面对复杂的序列化或反射攻击的时候
 */
public enum Singleton4 {
	INSTANCE;
	
	public void doSth() {}
}
