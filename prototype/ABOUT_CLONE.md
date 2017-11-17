本文是为下一篇“Java设计模式百例 - 原型模式”做铺垫，讨论一下Java中的对象克隆。本文内容综合了《Effective Java》、《Java与模式》以及其他网上相关资料，希望能够对您也有所帮助。

Java中，对象的创建除了用`new`关键字，还可以使用既有对象的`clone()`方法来复制自身达到创建一个新对象的目的。

关于对象克隆，Java中有通用约定：
# 通用约定1： x.clone() != x 必须为真。

对象克隆与引用的复制是有本质区别的，区别就在于x.clone()后产生的对象与x并不位于同一块内存上，两者是独立的，修改两者任何一方的成员都不会导致另一方发生变化。就像克隆羊多利（Dolly）不会因为其“基因母亲”（很遗憾，它没有名字，我们暂且谐音基因，就叫Jane吧）受伤或死亡而受伤或死亡。代码举例：

Sheep.java

    public class Sheep implements Cloneable {
        private String name;    //名字
        private int age;        //年龄
        private String breed;   //品种
        private EarTag earTag;  //耳牌
    
        // 构造方法
        public Sheep(String name, int age, String breed, EarTag earTag) {
            this.name = name;
            this.age = age;
            this.breed = breed;
            this.earTag = earTag;
        }
        
        // getters & setters
    
        @Override
        public Sheep clone() throws CloneNotSupportedException {
            return (Sheep) super.clone();
        }
        
        @Override
        public String toString() {
            return this.name + "是一只" + this.age + "岁的" + this.breed + ", 它的" + this.earTag.getColor() + "色耳牌上写着" + this.earTag.getId() + "号。";
        }
    }

每只羊身上有个耳牌：

EarTag.java

    public class EarTag implements Cloneable {
        private int id;         //耳牌编号
        private String color;   //耳牌颜色
    
        // 构造方法
        public EarTag(int id, String color) {
            this.id = id;
            this.color = color;
        }
        
        // getters & setters
    }

注意，

1. 以上两个类均需要实现`Cloneable`接口，否则执行`clone()`方法会报`CloneNotSupportedException`异常。
2. 若某个类允许其对象可以克隆，那么需要重写`clone()`方法，并且声明为`public`的，因为`Object`的`clone()`方法是`protected`，无法被非子类和不在当前包的其他类或对象调用。
3. 派生类的`clone()`方法中，要调用`super.clone()`，以便能够最终调用到`Object.clone()`，后者是个native方法，效率更高。

克隆过程如下：

    Sheep jane = new Sheep("简", 5, "多塞特白面绵羊", new EarTag(12345, "黄色"));
    System.out.println(jane);
    Sheep dolly = jane.clone();
    System.out.println("克隆后...");
    dolly.setName("多利");
    dolly.getEarTag().setId(12346);
    System.out.println(dolly);

输出结果为：

    简是一只5岁的多塞特白面绵羊, 它的黄色色耳牌上写着12345号。
    克隆后...
    多利是一只5岁的多塞特白面绵羊, 它的黄色色耳牌上写着12346号。

仿佛很完美，所有的信息都克隆过来了，但是，我们在看一下`jane`这个对象（最后增加两个输出）：

    System.out.println(jane);
    System.out.println(jane.getEarTag() == dolly.getEarTag());

输出结果为：

    简是一只5岁的多塞特白面绵羊, 它的黄色色耳牌上写着12346号。
    true

这就不对了，简的耳牌号也变了，而且我们看到两只羊的耳牌是”==“的，也就是`jane.earTag`和`dolly.earTag`指向的是同一个对象。这在现实中是毫无道理的。可见，`earTag`这个成员变量是引用复制。

## 浅复制
上边例子中，最终调用到的`Object.clone()`就是浅复制。所谓浅复制，可以理解为只复制成员变量的”值“。

1. 对于原生类型，其”值“就是实实在在的值，比如`int age`，是直接复制的；
2. 对于引用类型，其”值“就是引用本身，比如`EarTag earTag`，引用原来指向的是”黄色编号为12345的牌子“，引用复制过来仍然是指向同样的牌子，所以只是复制的值，而并未复制引用指向的对象；
3. （补充）对于引用类型，如果引用本身指向的是不可变类，比如`String`、`Integer`等，引用指向的对象内容是不可变的，一旦需要改变，其实就是从新`new`了一个对象，因此可以认为复制了引用指向的对象。其效果”看起来“和原生类型的待遇是一样的。

总结来说，**被复制对象的所有原生类型变量和不可变类的引用都复制与原来的对象相同的值，而所有的对其他对象（不包含不可变类的对象）的引用仍然指向原来的对象。**

## 深复制

相对于浅复制，更进一步，深拷贝把要复制的对象所引用的对象都复制一遍。

实现深复制有两种方式。一种是继续利用clone()方法，另一种是利用对象序列化。

对于第一种方法，进一步手动将指向可变对象的引用再复制一遍即可。比如对于`Sheep`我们增加`deepClone()`方法，在该方法中明确将`EarTag`对象也复制一下。因此`EarTag`也需要重写`clone()`方法。

Sheep.java增加`deepClone()`方法

    public Sheep deepClone() throws CloneNotSupportedException {
        Sheep s = (Sheep)super.clone();
        s.setEarTag(s.getEarTag().clone());
        return s;
    }

EarTag.java增加`clone()`方法，别忘了实现`Cloneable`接口

    @Override
    public EarTag clone() throws CloneNotSupportedException {
        return (EarTag) super.clone();
    }

这时候再测试一遍看输出：

    简是一只5岁的多塞特白面绵羊, 它的黄色色耳牌上写着12345号。
    克隆后...
    多利是一只6岁的多塞特白面绵羊, 它的黄色色耳牌上写着12346号。
    简是一只5岁的多塞特白面绵羊, 它的黄色色耳牌上写着12345号。
    false

可见，EarTag对象也被克隆了。

这时，其实还需要注意一个问题，我们这个例子中，`EarTag`的对象没有指向其他对象的引用，假设有的话，是否要调用`EarTag`的`deepClone()`方法呢，如果是一个引用链，深度复制要达到什么样的深度呢？是否有循环引用呢（比如`EarTag`中又有对`Sheep`的引用）？这都是在具体的使用过程中需要谨慎考虑的。

第二种方法是通过对象序列化来实现对象的深克隆。在Sheep.java中增加如下方法：

    public Sheep serializedClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bao);
        oo.writeObject(this);
        ByteArrayInputStream bai = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bai);
        return (Sheep) oi.readObject();
    }

注意的是，`Sheep`和`EarTag`都需要实现`Serializable`接口，以便打开对序列化的支持。

测试一下：

    Sheep jane = new Sheep("简", 5, "多塞特白面绵羊", new EarTag(12345, "黄色"));
    System.out.println(jane);
    Sheep dolly = jane.serializedClone();
    System.out.println("克隆后...");
    dolly.setName("多利");
    dolly.setAge(6);
    dolly.getEarTag().setId(12346);
    System.out.println(dolly);

    System.out.println(jane);
    System.out.println(jane.getEarTag() == dolly.getEarTag());

输出如下：

    简是一只5岁的多塞特白面绵羊, 它的黄色色耳牌上写着12345号。
    克隆后...
    多利是一只6岁的多塞特白面绵羊, 它的黄色色耳牌上写着12346号。
    简是一只5岁的多塞特白面绵羊, 它的黄色色耳牌上写着12345号。
    false

可见也确实实现了深克隆。

# 通用约定2： x.clone().getClass() == x.getClass() 必须为真。

指的是克隆后的对象其类型是一致的。这一点没有问题，及时在有继承关系的情况下。

ClassA.java

    public class ClassA implements Cloneable {
        public int getA() {
            return a;
        }
    
        public void setA(int a) {
            this.a = a;
        }
    
        private int a;
    
        @Override
        public ClassA clone() throws CloneNotSupportedException {
            return (ClassA) super.clone();
        }
    }

ClassB.java（继承ClassA）

    public class ClassB extends ClassA {
        private String b;
    
        public String getB() {
            return b;
        }
    
        public void setB(String b) {
            this.b = b;
        }
    
        public void test() {
            System.out.println(super.getClass().getCanonicalName());
        }
    }

测试一下：

    ClassB b = new ClassB();
    b.setA(1);
    b.setB("b");
    ClassB b1 = (ClassB) b.clone();
    System.out.println(b1.getB());

结果为：

    b
    
可见，即使子类没有重写`clone()`方法，只要其各层父类中有重新了`public`的`clone()`方法的，那么`clone()`方法都能正确克隆调起该方法的对象，且类型正确。话说回来，毕竟`clone()`的动作最终都是源于`Object`的那个native方法的。

# 通用约定3： x.clone().equals(x)为真

这一条并非强制约束，但尽量保证做到。因为从一般认识上来讲，克隆的两个对象虽然是不相等（==）的，但应该是相同（equal）的。

重写Sheep.java和EarTag.java的`equals()`方法：

Sheep.java

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (!(obj instanceof Sheep))
                return false;
            Sheep s = (Sheep) obj;
            return s.name.equals(this.name) &&
                    s.age == this.age &&
                    s.breed.equals(this.breed) &&
                    s.earTag.equals(this.earTag);
        }

EarTag.java

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (!(obj instanceof Sheep))
                return false;
            Sheep s = (Sheep) obj;
            return s.name.equals(this.name) &&
                    s.age == this.age &&
                    s.breed.equals(this.breed) &&
                    s.earTag.equals(this.earTag);
        }

测试一下：

    Sheep jane = new Sheep("简", 5, "多塞特白面绵羊", new EarTag(12345, "黄色"));
    Sheep dolly = jane.serializedClone();
    System.out.println("克隆后...");
    System.out.println(jane.equals(dolly));

输出为`true`，表示两个对象是相同的。


# 总结

最后，我们总结一下，实现clone的方法：
1）在派生类中实现Cloneable借口；
2）在派生类中覆盖基类的clone方法，声明为public；
3）在派生类的clone方法中，调用super.clone()；
4）若要深克隆对象，则需要增加对引用为非不可变对象的克隆。
















