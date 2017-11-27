> 本文源码见：https://github.com/get-set/get-designpatterns/tree/master/prototype

原型模式（Prototype Pattern）用于创建重复的对象，这种类型的设计模式属于创建型模式，与工厂模式类似，不同在于工厂模式通过`new`的方式创建对象，而原型模式通过复制既有对象的方式创建对象。当直接创建对象的代价比较大时，则采用这种模式。例如，一个对象需要在一个高代价的数据库操作之后被创建。我们可以缓存该对象，在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用。

# 例子
既然原型模式也是创建型模式，那么我们就用之前工厂模式的例子，也方便比较不同点。

我们在用画图软件或做PPT的时候经常用到的操作就是复制图形。为什么复制呢？因为现画一个图形比较麻烦嘛，需要调整大小、设置填充色、边框颜色、图形内字体大小颜色等等。这也是原型模式用到的一种场景，那就是当类属性多而且复杂的时候，在new的时候传入各种参数给构造方法，不如直接用一个现有的对象直接复制一个来的痛快。

假设我们在用PPT作图，无论是圆形、矩形还是三角形，都有填充颜色和边框颜色，以及图形内文字，因此可以抽象出一个`Shape`的抽象类，并且重写了`clone`方法（关于Java的`clone`，请参考“[番外篇 - Java的clone](ABOUT_CLONE.md)”）。

Shape.java

    public abstract class Shape implements Cloneable {
        protected String type;
        protected String fillColor;
        protected String frameColor;
        protected String innerText;
    
        @Override
        public Shape clone() throws CloneNotSupportedException {
            return (Shape)super.clone();
        }
    
        public abstract void draw();
        
        // getters & setters ... ...
        
    }

各种不同的图形均继承`Shape`，同样也继承了其`clone()`方法。

Circle.java

    public class Circle extends Shape {
        public Circle() {
            this.type = "circle";
        }
    
        public void draw() {
            System.out.println("Draw a " + type + ", fill with " + fillColor + ", frame color is " + frameColor + ", inner text is [" + innerText + "].");
        }
    }

Rectangle.java

    public class Rectangle extends Shape {
        public Rectangle() {
            this.type = "rectangle";
        }
    
        public void draw() {
            System.out.println("Draw a " + type + ", fill with " + fillColor + ", frame color is " + frameColor + ", inner text is [" + innerText + "].");
        }
    }

Triangle.java

    public class Triangle extends Shape {
        public Triangle() {
            this.type = "triangle";
        }
    
        public void draw() {
            System.out.println("Draw a " + type + ", fill with " + fillColor + ", frame color is " + frameColor + ", inner text is [" + innerText + "].");
        }
    }

下面我们来在main方法中使用一下这个原型复制的能力。

Client.java

    public static void main(String[] args) throws CloneNotSupportedException {
        Shape circle1 = new Circle();
        circle1.setFillColor("red");
        circle1.setFrameColor("green");
        circle1.setInnerText("Design patterns");
        circle1.draw();

        Shape circle2 = circle1.clone();
        circle2.draw();
    }

输出为：

    Draw a circle, fill with red, frame color is green, inner text is [Design patterns].
    Draw a circle, fill with red, frame color is green, inner text is [Design patterns].

这就是原型模式。

# 总结
通过上述内容，你大概也知道了原型模型的应用场景，那就是“当new一个对象成本较高的时候”，再举几个例子：

1. 当一个系统应该独立于它的产品创建，构成和表示时。 
2. 当要实例化的类是在运行时指定时，例如，通过动态装载。
3. 为了避免创建一个与产品类层次平行的工厂类层次时。
4. 当一个类的实例只能有几个不同状态组合中的一种时。建立相应数目的原型并克隆它们可能比每次用合适的状态手工实例化该类更方便一些。

除此之外，还有别的好处哟：

1. 性能好。在[番外篇 - Java的clone](ABOUT_CLONE.md)中说到，`clone()`是源自`Object`类中的`native`方法，其执行效率相对较高。
2. 可以逃避构造方法的束缚，因为不需要`new`了嘛。

使用原型模式有几点注意事项：

1. 类本身或其父类必须实现`Cloneable`接口，并用`public`的`clone()`方法覆盖`Object`的同名方法；
2. 根据实际情况判断是”浅复制“还是”深复制“，若深复制用序列化方式实现，则类本身或其父类须实现`Serializable`接口；
3. 实际开发过程中注意业务逻辑，比如id等成员要保证不重复。

其实结合最近几个设计模式的例子，你可能会发现，我前边介绍到的设计模式的类关系模型，有些是同GoF的一致的，有些是有差别的。其实看网上其他的设计模式的博文介绍也有这种感觉，那就是似乎感觉同样一个设计模式，不同的文章介绍出来类关系怎么不一样。比如对于抽象，有的是用接口，有的是用抽象类；比如对于继承关系，有的是一层，有的是两层。这时候总有些不知所措：到底哪个是正宗的呢？

其实对于设计模式，大可不必追求“正宗”，就像到了长沙，各种臭豆腐店，尝遍之后也很难说哪个是正宗的，但是它们都有共同点：基本是黑色的黑暗料理，闻起来有点臭但是吃起来香，烹饪方法都是油炸等等。抓住这些基本特点，只要好吃，那就OK咯～说回设计模式，归根到底是对“面向对象思想”应用的一些成熟的模型，不要“拿来主义”生搬硬套，抓住核心特征，符合实际情况即可，所谓“无招胜有招”嘛。

那么对于原型模式，其核心特征在于`clone`。