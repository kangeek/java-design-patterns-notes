> 本文源码见：https://github.com/get-set/get-designpatterns/tree/master/facade

门面模式（Facade Pattern）用于隐藏系统的复杂性，并向客户端提供一些简化访问方法和对现有系统类方法的委托调用。这种类型的设计模式属于结构型模式，用来隐藏系统的复杂性。

现在政府办事越来越方便了，很多城市、区县都有统一的办事大厅，里边有各个部门的窗口，一般进去一圈该办的事情就齐活了。想想之前，政府办个事情，这个部门盖个章，那个部门开个证明，不同的部门分散在城市的不同位置，一天有时候都办不妥当。先为这种进步点个赞！

各个部门还在各自的地方，但是都会在办事大厅开一个窗口。其实这个办事大厅就相当于各个部门的一个总的门面，就是用到了“门面模式”。我们现在的系统通常会越来越复杂，类之间的依赖调用关系逐渐变复杂，接口越来越多，这时候通常会用一个专门的类对某个模块或子系统进行一个包装，归置归置，简化对外提供服务的方法调用，较少方法数量。

# 例子

好久没有用那个画图的例子了，这里再次搬出来。对于门面模式来说，没有固化的类关系模型，一切以简化和封装为出发点，所以这个例子也比较简洁，领会意思即可。

这个例子中，有圆形、矩形和三角形三种形状，它们都继承自`Shape`：

    # Shape.java
    public interface Shape {
        void draw();
    }
    
    # Circle.java
    public class Circle implements Shape {
        public void draw() {
            System.out.println("Draw a circle.");
        }
    }
    
    # Rectangle.java
    public class Rectangle implements Shape {
        public void draw() {
            System.out.println("Draw a ectangle.");
        }
    }
    
    # Triangle.java
    public class Triangle implements Shape{
        public void draw() {
            System.out.println("Draw a triangle.");
        }
    }

随着形状的增多，类数量也会显著增加，这个时候如果有一个统一的类来提供各个图形的`draw`功能就好了。我们之前用这个例子解释过工厂模式，工厂模式主要是用来获取类实例的，而门面模式是统一对外提供接口调用的。本例的门面类如下：

ShapeDrawer.java

    public class ShapeDrawer {
        private ShapeDrawer() {}
        public static void drawCircle() {
            new Circle().draw();
        }
        public static void drawTrangle() {
            new Triangle().draw();
        }
        public static void drawRectangle() {
            new Rectangle().draw();
        }
    }

这里，`ShapeDrawer`是作为类来使用了，因为其方法都是`static`的，因此也就不用创建其对象，所以构造方法声明为私有的。使用时直接调用静态方法即可：`ShapeDrawer.drawCircle();`。

当然，这只是一种方式，并不是说门面模式就要这样做。
  * 门面也可以是单例的，由一个单一的对象负责提供服务；
  * 门面也可以是普通的多例的，比如其要包装的目标是多个对象，那么每个对象可能会需要一个门面实例，这个时候，门面通常也是起到一种隔离的作用，比如被包装的对象有许多接口是系统内调用的，并不对其他模块或子系统开放，而门面对象起到了一层代理的作用。

# 总结

门面模式更多是一种设计思想，而不是具体模型。目的是为子系统中的一组接口提供一个一致的界面，通过定义一个高层接口，降低访问复杂系统的内部子系统时的复杂度，使得这一子系统更加容易使用。