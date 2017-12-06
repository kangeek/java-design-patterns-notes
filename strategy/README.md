策略（Strategy）模式是对算法的一种封装，是把使用算法的责任和算法本身分割开来，委托给不同的对象管理，从而可以实现算法的互换，从而一个类的行为或其算法可以在运行时更改，这种设计模式属于行为型模式。

策略模式在生活中和架构设计中非常常见。
  * 比如，商场打折，对于同样一类商品，不同的时间，促销方式和打折力度是不同的，这时候，促销方式和打折力度就是策略，对于这种应用场景来说，策略需要是可以随时变更的。
  * 还有个例子，比如排序，不同的对象，其排序依据不同。对于学生而言，有时候根据身高排序安排坐位，有时候根据成绩排序安排考场，这时候把这两种排序方式独立出来是一种比较好的方法，当需要安排坐位时，就使用“按照身高排序”的策略，当需要安排考场时就使用“按照成绩排序”的策略。从而一旦有新的需求和新的策略，只需要变更另外一个策略（比如“按年龄排序”）就OK了。
  * 再举个例子，说到插卡游戏机，估计不少朋友没见过，80后还是有印象的，游戏机本身不能玩，需要再购买不同的游戏卡插上才能玩，比如魂斗罗、超级玛丽等等。

# 例子

我们用一个简单的计算的例子来阐述这种模式。需求很简单，提供两个数字，给出这两个数字的加减乘除的结果。

在这里，加减乘除就是不同的策略，我们统一用`Operation`抽象。

Operation.java

    public interface Operation {
        int calculate(int x, int y);
    }

AddOperation.java

    public class AddOperation implements Operation {
        public int calculate(int x, int y) {
            return x + y;
        }
    }

SubOperation.java

    public class SubOperation implements Operation {
        public int calculate(int x, int y) {
            return x - y;
        }
    }

MulOperation.java

    public class MulOperation implements Operation {
        public int calculate(int x, int y) {
            return x * y;
        }
    }

MulOperation.java

    public class DivOperation implements Operation {
        public int calculate(int x, int y) {
            return x / y;
        }
    }

我们的计算器比较旧，来自上世纪，就像插卡游戏机，需要先设定计算方法，然后才能进行计算。

Calculator.java

    public class Calculator {
        private Operation operation;
    
        public Calculator(Operation operation) {
            this.operation = operation;
        }
    
        public void setOperation(Operation operation) {
            this.operation = operation;
        }
    
        public int calculate(int x, int y) {
            return operation.calculate(x, y);
        }
    }

下面我们用一下这个计算器：

Client.java

    public class Client {
        public static void main(String[] args) {
            int x = 6, y = 3;
            Calculator calculator = new Calculator(new AddOperation());
            System.out.println("6+3=" + calculator.calculate(x, y));
            calculator.setOperation(new SubOperation());
            System.out.println("6-3=" + calculator.calculate(x, y));
            calculator.setOperation(new MulOperation());
            System.out.println("6*3=" + calculator.calculate(x, y));
            calculator.setOperation(new DivOperation());
            System.out.println("6/3=" + calculator.calculate(x, y));
        }
    }

输出如下：

    6+3=9
    6-3=3
    6*3=18
    6/3=2

# 总结

策略模式是如何做到随意切换的呢？很显然，仍然是采用了面向接口编程的思路，所有的策略都实现了同样的接口，策略消费者只需要基于接口调用即可，不同的接口实现提供了不同的策略。这也是“历史替换原则”的具体体现。

在具体的开发中，策略模式的例子经常见到：
  * 我们在使用SpringMVC开发的时候，前端页面的渲染可能采用不同的技术，比如JSP、Thymeleaf等，对于Spring框架来说，要能够接纳不同的页面渲染技术，因此提供了`ViewResolver`的接口，如果是JSP的渲染方法，那么就使用`InternalResourceViewResolver`；如果是Thymeleaf的渲染方法，就配置使用`ThymeleafViewResolver`。
  * Java中观察`Comparable`和`Comparator`两个接口的应用，也是理解策略模式的好思路。对于`Comparable`来说，其应用于被比较的对象上，从接口方法`int compareTo(T o);`可以看出，是由当前对象调起的与另一个对象的比较；而`Comparator`接口的用于比较的接口方法为`int compare(T o1, T o2);`，不同点在于参数个数，一个`Comparator`是独立于`o1`和`o2`两个被比较对象之外的第三方，因此做到了排序算法与调用算法的解耦，不同的`Comparator`实现就是不同的策略，可见`Comparator`的应用方式是策略模式。

最后总结一下策略模式的使用场景：1、如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。 2、一个系统需要动态地在几种算法中选择一种。 3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。
