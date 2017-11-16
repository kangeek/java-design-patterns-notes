
单例模式（Singleton Pattern）是Java中最简单的设计模式之一，但也是一个很值得玩味儿的设计模式，这是一个创建型的模式。

单例模式的目的在于，对于一些类，需要保证其仅有一个实例。比如一个Web中的计数器，不用每次刷新在数据库里记录一次，可以用一个单例的对象先缓存起来。但是这个计数器不能有多个实例，否则岂不是不准了。

具体来说，该设计模式有如下特点或要求：

1. 单例类只能有一个实例。
2. 单例类必须自己创建自己的唯一实例。
3. 单例类必须给所有其他对象提供这一实例。

# 例子
## 饿汉式

我们考虑一下上述的三个要求。

首先，单例类只能有一个实例。我们知道，一个类的`static`的成员变量或方法可以看作归属于类本身的，可以被这个类所有的实例共享，可以认为是”独一份儿“的。所以用`static`关键字修饰这个类的对象，就可以做到。

然后，单例类必须自己创建自己的唯一实例。那也好办，单例类自己定义一个类型为自己的成员变量，然后设置为`static`的就可以了。然后把构造方法设置为`private`的，这样就不能被其他类或对象`new`了。

最后，单例类必须给所有其他对象提供这一实例。那就是提供一个get方法可以获取到这个类型为自己的成员变量。

分析过后，先撸一版代码：

SingleObject.java

    public class SingleObject {
    
        private final static SingleObject INSTANCE = new SingleObject();
    
        private SingleObject() {}
    
        public static SingleObject getInstance() {
            return instance;
        }
    }

我们搞一个Client检验一下：

Client.java

    public class Client {
        public static void main(String[] args) {
            System.out.println(SingleObject.getInstance());
            System.out.println(SingleObject.getInstance());
        }
    }

返回结果：

    com.getset.designpatterns.singleton.SingleObject@5e2de80c
    com.getset.designpatterns.singleton.SingleObject@5e2de80c

根据对象的hash可以看出，两次调用`getInstance()`返回的是同一个对象。

这种是比较常用的方式，叫做“饿汉式”，为啥叫这名自己体会哟～ 我们知道，类加载时，static的成员变量会初始化，所以一旦类加载，那么`INSTANCE`所指向的对象就被创建了。其实这就是个常亮了，所以可以用大写，然后`final`修饰。

## 懒汉式

在有些情况下，如果单例类本身比较消耗资源或加载缓慢，希望能够在使用的时候才创建实例，那么可以采用懒加载的方式，如下：

LazySingleObject.java

    // 本例是线程不安全的
    public class LazySingleObject {
        private static LazySingleObject instance;
        private LazySingleObject() {}
    
        public static LazySingleObject getInstance() {
            if (instance == null) {
                instance = new LazySingleObject();
            }
            return instance;
        }
    }

如此，只有在调用`getInstance()`的时候才会创建实例。

但是这种方式是线程不安全的，假设有两个线程同时调用了`getInstance()`，可能都会检测到`instance == null`。所以可以把`getInstance()`方法使用`synchronized`修饰，以便保证线程安全。

但是在实际使用过程中，一旦实例被创建后，`getInstance()`方法只是返回实例，是不需要同步的，而加锁会影响效率，因此我们考虑不对整个方法加锁，而仅仅只对`new`实例的过程加锁，如下：

LazySingleObject.java

    // 双检锁/双重校验锁（DCL，即 double-checked locking）
    public class LazySingleObject {
        // 使用volatile修饰单例变量
        private static volatile LazySingleObject instance;
        private LazySingleObject() {}
        public static LazySingleObject getInstance() {
            // 第一次判断，此时是未加锁的
            if (instance == null) {
                synchronized (LazySingleObject.class) {
                    // 第二次判断，此时是线程安全的
                    if (instance == null) {
                        instance = new LazySingleObject();
                    }
                }
            }
            return instance;
        }
    }

可见，只有在实例未创建（`instance == null`）的时候才同步创建实例对象。在`synchronized`代码库内部，再次检查实例是否创建，因为第一次检查并不是线程安全的。也因此，这种方式叫做“双检锁/双重校验锁”。这样，一旦实例创建之后，就不再进入同步代码块了，从而效率更高。

要特别注意的是，单例变量`instance`要用`volatile`进行修饰。原因在于编译器出于优化需要会对内存的读写操作重排序，因此LazySingleObject对象初始化时的写操作与写入instance字段的操作可以是无序的。导致的结果就是如果某个线程调用getInstance()可能看到instance字段指向了一个LazySingleObject对象，但看到该对象里的字段值却是默认值，而不是在LazySingleObject构造方法里设置的那些值。而使用`volatile`字段修饰后，编译器和运行时都会注意到这是个共享变量，因此不会将该变量上的操作和其他内存操作一起重排序，真正保证线程安全。

以上两种方式可根据场景选择。

## 登记式/静态内部类

如果感觉“双检锁/双重校验锁”这种方式复杂难学，可以采用静态内部类来实现线程安全的懒加载。

LazyHandlerSingleObject.java

    public class LazyHandlerSingleObject {
        private static class SingleObjectHandler {
            private final static LazyHandlerSingleObject instance = new LazyHandlerSingleObject();
        }
        private static LazyHandlerSingleObject instance;
    
        public static LazyHandlerSingleObject getInstance() {
            return SingleObjectHandler.instance;
        }
    }

我们知道，类在使用的时候才会被classloder加载，静态内部类`SingleObjectHandler`正是利用了这个特点，来懒加载其外部对象`LazyHandlerSingleObject`的实例。

> 类嵌套一向是一种比较难以理解的概念，静态内部类是最简单的一种嵌套类，最好把他看作是普通的类，只是碰巧被声明在另一个类内部而已。唯一与普通类不同的是，静态内部类和其外部类可以互相访问所有成员，包括私有成员。

当第一次调用`getInstance()`方法的时候，`SingleObjectHandler`才第一次被使用到，从而加载它，自然也就创建了`LazyHandlerSingleObject`的实例，`SingleObjectHandler`通过`static`保证这个实例是唯一的。

## 枚举
这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。

这种方式是《Effective Java》作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。推荐在需要使用单例的时候使用这种方式。

EnumSingleton.java

    public enum EnumSingleton {
        INSTANCE;
        public void doSomething() {
            ...
        }
    }

就是这么简单！直接使用`EnumSingleton.INSTANCE`就可以了，比如`EnumSingleton.INSTANCE.doSomething()`。Enum类型类似于类，也可以有成员变量和成员方法。

# 总结
首先，请尝试使用枚举的方式来实现单例，枚举机制本身对单例有很好的支持。

如果觉得枚举方式不熟悉，那么：

通常，比较轻量的单例直接用饿汉式即可；

重量级的单例对象，最好通过懒加载的方式构建，根据线程安全性的要求选择以上两种懒汉式的方式；当然静态内部类也是一种不错的懒加载方式。