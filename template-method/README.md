在模板方法模式（Template Method Pattern）中，一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。

关于模板，大家生活中都有体会：
  * 我们总感觉新闻联播里的新闻有些固定的“套路”，比如`______在____的陪同下，不远万里，来到_____家中，为_____带来了节日的祝福和良好的祝愿，并饶有兴致的观看了_____。____握着___的手激动的说_______`。
  * 我们学生期间填了各种表格，写了不少报告，比如实验报告：`1、实验目的和要求；2、实验设备（环境）及要求；3、实验步骤；4、实验结果；5、讨论和分析`。

这样的例子能举出好多来，这就是模板，里边的空或步骤是我们具体要去补充的地方。为什么要搞这些模板出来呢？**规范**，为了希望大家都按照一定的规范约束来实现。这也是我们在类关系的设计中，采用这种设计模式的初衷。

# 例子

下边这个例子是通过学生或老师的自我介绍来演示模板方法模式的应用。

无论老师还是学生，在做自我介绍时通常都有自我基本情况介绍、获得过什么奖励、求学/教学经历等方面的内容，可以认为是一个模板。我们的例子中，自我介绍包含两部分，基本情况介绍和表决心（哈哈），如下：

SchoolPerson.java

    public abstract class SchoolPerson {
        protected String name;
        protected int age;
        protected String schoolName;
        protected String hometown;
    
        public SchoolPerson(String name, int age, String schoolName, String hometown) {
            this.name = name;
            this.age = age;
            this.schoolName = schoolName;
            this.hometown = hometown;
        }
    
        public void selfIntroduction() {
            myBasicInfo();
            myslogan();
        }
    
        public abstract void myBasicInfo();
    
        public abstract void mySlogan();
    }

我们看到，这个模板如果作为填空题的话，“空”就在`myBasicInfo`和`mySlogan`两个方法上。这两个方法是抽象的，要求子类去实现。

Student.java

    public class Student extends SchoolPerson {
        public Student(String name, int age, String schoolName, String hometown) {
            super(name, age, schoolName, hometown);
        }
    
        public void myBasicInfo() {
            System.out.println("我是一名学生，名叫" + this.name + "， 今年" + this.age + "岁， " + this.hometown + "人， 在" + this.schoolName + "上学。");
        }
    
        public void mySlogan() {
            System.out.println("在我在" + this.schoolName + "求学的过程中，我一定 好好学习，天天向上！");
        }
    }

Teacher.java

    public class Teacher extends SchoolPerson {
    
        public Teacher(String name, int age, String schoolName, String hometown) {
            super(name, age, schoolName, hometown);
        }
    
        public void myBasicInfo() {
            System.out.println("我是一名教师，名叫" + this.name + "， 今年" + this.age + "岁， " + this.hometown + "人， 在" + this.schoolName + "教书。");
        }
    
        public void mySlogan() {
            System.out.println("在我在" + this.schoolName + "教学的过程中，我一定 为人师表，诲人不倦！");
        }
    }

学生和老师对两个模板方法都有不同的实现。我们看一下执行效果：

Client.java

    public class Client {
        public static void main(String[] args) {
            SchoolPerson student = new Student("张三", 12, "光明小学", "山东济南");
            student.selfIntroduction();
    
            SchoolPerson teacher = new Teacher("李四", 32, "光明小学", "山东青岛");
            teacher.selfIntroduction();
        }
    }

输出为：

    我是一名学生，名叫张三， 今年12岁， 山东济南人， 在光明小学上学。
    在我在光明小学求学的过程中，我一定 好好学习，天天向上！
    我是一名教师，名叫李四， 今年32岁， 山东青岛人， 在光明小学教书。
    在我在光明小学教学的过程中，我一定 为人师表，诲人不倦！

# 总结

看这个例子，你可能会感觉，这就是继承嘛，有啥新鲜的？的确，虽然我们以前一直说类的组合（或说委托）优先于类的继承来使用。但是模板方法模式是为数不多的为我们示范关于继承的使用方式的设计模式。

模板方法模式的特点很好总结，它将一般性的可复用的行为由基类固化，而把特殊化的行为交由具体的子类来实现。具体来说：
1. 子类通常不关心全局（比如整个流程、提纲、步骤），而只负责”填空“；”填空“通过实现或重写父类的方法来实现。
2. 从父类角度，全局性的规范约束掌握在自己手中，具体来说通过模板方法来约束，从而能够尽量简化子类的复杂度。父类并不一定是抽象类，模板方法也并不一定是抽象方法。

再简单介绍一个实际的例子。我们知道，Servlet有特定的规范，以便Servlet的容器（比如tomcat）和Servlet的实现（我们开发的JavaEE应用）能够很好的合作。

其中`javax.servlet.http.HttpServlet`就是Servlet规范在Http协议方面的”践行者“。`HttpServlet`也是一种`Servlet`，因此也必须有`service`方法来提供服务。但是Http有其具体的服务分类（GET、POST、PUT等不同的请求方法），我们看一下`HttpServlet.service`方法的实现：

HttpServlet.java

    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        String method = req.getMethod();

        if (method.equals(METHOD_GET)) {
            ...
            doGet(req, resp);
            ...

        } else if (method.equals(METHOD_HEAD)) {
            ...
            doHead(req, resp);

        } else if (method.equals(METHOD_POST)) {
            doPost(req, resp);
            
        } else if (method.equals(METHOD_PUT)) {
            doPut(req, resp);
            
        } else if (method.equals(METHOD_DELETE)) {
            doDelete(req, resp);
            
        } else if (method.equals(METHOD_OPTIONS)) {
            doOptions(req,resp);
            
        } else if (method.equals(METHOD_TRACE)) {
            doTrace(req,resp);
            
        } else {
            ...
        }
    }

简单起见，用省略号代替了一些代码，可以看到，service接到请求后，会先判断请求方法的类型，如果是GET请求就交给doGet去实现，如果是POST请求就交给doPost去实现。

对于一个具体的servlet（`ConcreteHttpServlet`）来说，只需要继承`HttpServlet`并重写具体的方法就可以了。比如某个Servlet是用来处理GET请求的，那么只重写`doGet`方法填写处理逻辑就OK了。其它的处理不用care。

不过现在由于Spring等Web框架的出现，我们不需要一个一个servlet去写JavaWeb程序，而是由比如Spring的`DispatcherServlet`这样的统一的Servlet入口来处理了，它直接映射了”/"这样的请求URL，从而托管了所有的请求。但是既然是JavaEE框架，还是要遵循Servlet规范的，`DispatcherServlet`的父类`FrameworkServlet`就对`doGet`、`doPost`等模板方法进行了重写，以满足规范的要求。