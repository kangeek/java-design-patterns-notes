在访问者模式（Visitor Pattern）中，通过一个访问者类，来封装对数据结构中不同类型元素的执行算法。通过这种方式，元素的执行算法可以随着访问者改变而改变。这种类型的设计模式属于行为型模式。

# 例子

我们假设有一些形状，包括三角形、矩形和圆形这三种不同的几何形状。我们知道不同的形状其参数是不同的：
  * 三角形：三条边的长度确定一个三角形；
  * 矩形：长和宽确定一个矩形；
  * 圆形就一个参数——半径。

那么任务来了，我们把一系列类型和参数不同的形状用`ArrayList`来管理，然后依次遍历，并计算出它们的周长。

## 一种计算任务

这个任务非常简单，由于用`ArrayList`来管理，因此需要各个不同形状抽象出统一的接口`Shape`。这个接口定义一个共同的方法`getPerimeter`，然后这三种形状都实现这个方法就OK了嘛。其代码如下：

Shape.java

    public interface Shape {
        double getPerimeter();
    }

Triangle.java（三个属性：三条边的长度）

    public class Triangle implements Shape {
        private double edgeA;
        private double edgeB;
        private double edgeC;
    
        public Triangle(double edgeA, double edgeB, double edgeC) {
            this.edgeA = edgeA;
            this.edgeB = edgeB;
            this.edgeC = edgeC;
        }
    
        public double getEdgeA() {
            return edgeA;
        }
    
        public double getEdgeB() {
            return edgeB;
        }
    
        public double getEdgeC() {
            return edgeC;
        }
    
        public double getPerimeter() {
            return edgeA + edgeB + edgeC;
        }
    }

Rectangle.java（两个属性：长和宽）

    public class Rectangle implements Shape {
        private double length;
        private double width;
    
        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }
    
        public double getLength() {
            return length;
        }
    
        public double getWidth() {
            return width;
        }
    
        public double getPerimeter() {
            return (length + width) * 2;
        }
    }

Circle.java（一个属性：半径）

    public class Circle implements Shape {
        private double radius;
    
        public Circle(double radius) {
            this.radius = radius;
        }
    
        public double getRadius() {
            return radius;
        }
    
        public void getPerimeter() {
            return 2 * Math.PI * radius;
        }
    }

齐活儿~ 写个`Client`交卷了：

Client.java

    public class Client {
        public static void main(String[] args) {
            List<Shape> shapes = new ArrayList<Shape>();
            shapes.add(new Triangle(1.3, 2.2, 3.1));
            shapes.add(new Circle(1.2));
            shapes.add(new Triangle(2.4, 3.3, 4.2));
            shapes.add(new Ractangle(2.1, 3.2));
            shapes.add(new Circle(5.6));
            
            for (Shape shape : shapes) {
                System.out.println(shape.getPerimeter());
            }
        }
    }

这是面向接口编程的最基本用法了吧。不过别忙着高兴，如果这个时候再加一个任务——“求面积”呢？那就在接口里再增加一个`getArea`方法，然后所有的形状都实现了呗~

好吧，也可以，不过任务还远没有结束，如果还要算出能够包住每个形状的最小的圆的直径呢？以及能够置于形状内的最大的圆的直径呢？等等等等。。。

每次提出新的计算任务后，频繁修改接口及其实现类，这显然是不符合“开闭”原则的，而且明显也是不优雅的。况且，天知道将来还会需要计算什么幺蛾子！

## 多种计算任务，策略模式

必须要调整一下设计思路以满足灵活性。

我们曾经遇到过对于同一个对象进行不同运算的设计——比如策略模式，无论是计算周长、面积都是不同的策略，我们将不同的策略作为对象传递给形状，就可以得到该策略相应的结果。比如对于矩形：

    // 对于矩形，在Rectangle.java
    public double accept(Strategy strategy) {
        return strategy.calculate(this);
    }

    // 所有的策略都实现统一的接口 Strategy
    public interface Strategy {
        double calculate(Rectangle rect);
    }

    // 对于计算周长的策略，在PerimeterStrategy.java
    public class PerimeterStrategy implements Strategy {
        public double calculate(Rectangle rect) {
            return 2 * (rect.getLength() + rect.getWidth());
        }
    }
    
    // 对于计算面积的策略，在AreaStrategy.java
    public class AreaStrategy implements Strategy {
        public double calculate(Rectangle rect) {
            return rect.getLength() * rect.getWidth();
        }
    }

## 不同类型对象的多种计算任务，访问者模式

上边的例子是针对矩形的，那么三角形和圆形也都实现相应的策略类的话，类的数量就很快增长起来了，似乎也不优雅嘛！ 其实很简单，因为无论是周长策略还是面积策略，各个形状都要实现，那对于同一种策略打个包不就OK了吗：

Calculator.java

    public interface Calculator {
        double ofShape(Triangle triangle);
        double ofShape(Circle circle);
        double ofShape(Square square);
    }

Perimeter.java（各种形状周长策略的打包）

    public class Perimeter implements Calculator {
        public double ofShape(Triangle triangle) {
            return triangle.getEdgeA() + triangle.getEdgeB() + triangle.getEdgeC();
        }
    
        public double ofShape(Circle circle) {
            return circle.getRadius() * Math.PI * 2;
        }
    
        public double ofShape(Square square) {
            return square.getEdge() * 4;
        }
    }

Area.java（各种形状面积策略的打包）

    public class Area implements Calculator {
        public double ofShape(Triangle triangle) {
            double a = triangle.getEdgeA(), b = triangle.getEdgeB(), c = triangle.getEdgeC();
            double p = (a + b + c) / 2;
            return Math.sqrt(p * (p - a) *  (p - b) * (p - c));
        }
    
        public double ofShape(Circle circle) {
            return Math.PI * circle.getRadius() * circle.getRadius();
        }
    
        public double ofShape(Square square) {
            return Math.pow(square.getEdge(), 2);
        }
    }

两种策略的打包都实现自`Calculator`接口，以后还有啥计算需求，起个类实现这个接口就可以了。

那对于各个形状，刚才的代码也要稍微调整一下：

Shape.java

    public interface Shape {
        // double getPerimeter();
        // 对于不同的计算策略来者不拒
        double accept(Calculator calculator);
    }

Triangle.java（三个属性：三条边的长度）

    public class Triangle implements Shape {
        private double edgeA;
        private double edgeB;
        private double edgeC;
    
        public Triangle(double edgeA, double edgeB, double edgeC) {
            this.edgeA = edgeA;
            this.edgeB = edgeB;
            this.edgeC = edgeC;
        }
    
        public double getEdgeA() {
            return edgeA;
        }
    
        public double getEdgeB() {
            return edgeB;
        }
    
        public double getEdgeC() {
            return edgeC;
        }
    
    //    public double getPerimeter() {
    //        return edgeA + edgeB + edgeC;
    //    }
    
        // 方法接受策略对象为参数，方法内将自身作为参数再传给策略的方法
        public double accept(Calculator calculator) {
            return calculator.ofShape(this);
        }
    }

在`accept`方法中，接受策略对象为参数，方法内将自身作为参数再传给策略的具体方法，这种方式叫做“双重分派”，高大上的名字往往不好记也不好理解，哈哈，其实不记也罢，通过这种巧妙的回调方式实现不同策略对不同类型对象的计算任务。

我们再测试一下：

Client.java

    public class Client {
        public static void main(String[] args) {
            // 一个含有5个元素的List，包含三种不同的形状
            List<Shape> shapes = new ArrayList<Shape>();
            shapes.add(new Triangle(1.3, 2.2, 3.1));
            shapes.add(new Circle(1.2));
            shapes.add(new Triangle(2.4, 3.3, 4.2));
            shapes.add(new Rectangle(2.1, 3.2));
            shapes.add(new Circle(5.6));
    
            // 计算周长和面积的不同策略（访问者）
            Perimeter perimeter = new Perimeter();
            Area area = new Area();
    
            // 将周长和面积的计算策略传入（接受不同访问者的访问）
            for (Shape shape : shapes) {
                System.out.printf("周长 : %5.2f\t 面积 : %5.2f\n", shape.accept(perimeter), shape.accept(area));
            }
        }
    }

将不同的策略对象传递给各个元素，从而对不同类型的元素进行不同策略的计算。是不是感觉代码优雅了不少呢^_^

测试结果：

    周长 :  6.60	 面积 :  1.20
    周长 :  7.54	 面积 :  4.52
    周长 :  9.90	 面积 :  3.95
    周长 : 10.60	 面积 :  6.72
    周长 : 35.19	 面积 : 98.52

# 总结

上边的例子就是应用了访问者模式。为啥叫访问者模式呢？

其实例子中的策略就相当于依次访问各个元素的访问者，每个元素可以接受(`accept`)不同访问者作为参数，从而交由访问者做出不同的操作。

我们也可以看出访问者模式的应用场景具有如下特点：
  * 通常用于处理数据结构中不同类型元素的遍历处理问题。这里所说的数据结构比如例子中的列表，或者数组、Map、Stack、Set，甚至复杂的树，重点不在于数据结构，而在于不同类型的元素放到一起，要“因材施教”。
  * 即使对于每种类型的元素，也有不同的“访问方式”，将不同的“访问方式”作为不同的对象传递给元素。“访问者”相当于对各种类型的元素的同一种“访问方式”的打包。
  * 使用到了双重分派，在`accept`方法中，接受策略对象为参数，方法内将自身作为参数再传给策略的具体方法。

所以，这是一个 `m x n` 的问题，多种元素对应多种“访问方式”。