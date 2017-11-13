
为了自己能够系统有效地学习设计模式，也希望能够帮助到其他想对设计模式有了解的同学，我边学习边做了这个系列的设计模式笔记。  

本系列主要由Java语言来实现。内容来自国外网站[Java-desing-patterns](http://java-design-patterns.com/patterns/)，并结合了其他相关内容，如[设计模式|菜鸟教程](http://www.runoob.com/design-pattern/design-pattern-intro.html)。所以——

* 如果你在找一个详细的、靠谱的、认真的设计模式系列文档，
* 如果你觉得设计模式的学习总是虎头蛇尾、难以坚持，
* 如果你觉得许多文档只是为了设计模式而设计，并非从问题或需求出发，过后很难学以致用，
* ……

那么，希望这个系列的笔记能够帮到你。我们一起努力！  


# 设计模式简介

设计模式（Design pattern）代表了最佳的实践，通常被有经验的面向对象的软件开发人员所采用。设计模式是软件开发人员在软件开发过程中面临的一般问题的解决方案。这些解决方案是众多软件开发人员经过相当长的一段时间的试验和错误总结出来的。  

设计模式是一套被反复使用的、多数人知晓的、经过分类编目的、代码设计经验的总结。使用设计模式是为了重用代码、让代码更容易被他人理解、保证代码可靠性。 毫无疑问，设计模式于己于他人于系统都是多赢的，设计模式使代码编制真正工程化，设计模式是软件工程的基石，如同大厦的一块块砖石一样。项目中合理地运用设计模式可以完美地解决很多问题，每种模式在现实中都有相应的原理来与之对应，每种模式都描述了一个在我们周围不断重复发生的问题，以及该问题的核心解决方案，这也是设计模式能被广泛应用的原因。  

## Gang of Four
说到设计模式，就不能不提到大名鼎鼎的“四人帮”(Gang of Four)。在 1994 年，由 Erich Gamma、Richard Helm、Ralph Johnson 和 John Vlissides 四人合著出版了一本名为 **Design Patterns - Elements of Reusable Object-Oriented Software（中文译名：设计模式 - 可复用的面向对象软件元素）** 的书，该书首次提到了软件开发中设计模式的概念。
四位作者合称 GOF（四人帮，全拼 Gang of Four）。他们所提出的设计模式主要是基于以下的面向对象设计原则：  

* 对接口编程而不是对实现编程。
* 优先使用对象组合而不是继承。

## And more ...
除了Gang of Four提到的创建型、结构型、行为型的三大类共23个设计模式，还会涉及到JavaEE、Spring、I/O与性能、业务层与持久层等不同方面所涉及到的一些典型的设计模式，总数达百余个。设计模式是内功中的精华，技多不压身，多研究一些总是没有坏处的，不是吗？

# 设计模式六大原则

1. 开闭原则（Open Close Principle）
开闭原则的意思是：对扩展开放，对修改关闭。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。简言之，是为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，后面的具体设计中我们会提到这点。
2. 里氏代换原则（Liskov Substitution Principle）
里氏代换原则是面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。LSP 是继承复用的基石，只有当派生类可以替换掉基类，且软件单位的功能不受到影响时，基类才能真正被复用，而派生类也能够在基类的基础上增加新的行为。里氏代换原则是对开闭原则的补充。实现开闭原则的关键步骤就是抽象化，而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。
3. 依赖倒转原则（Dependence Inversion Principle）
这个原则是开闭原则的基础，具体内容：针对接口编程，依赖于抽象而不依赖于具体。
4. 接口隔离原则（Interface Segregation Principle）
这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。它还有另外一个意思是：降低类之间的耦合度。由此可见，其实设计模式就是从大型软件架构出发、便于升级和维护的软件设计思想，它强调降低依赖，降低耦合。
5. 迪米特法则，又称最少知道原则（Demeter Principle）
最少知道原则是指：一个实体应当尽量少地与其他实体之间发生相互作用，使得系统功能模块相对独立。
6. 合成复用原则（Composite Reuse Principle）
合成复用原则是指：尽量使用合成/聚合的方式，而不是使用继承。

# 例子
1. [简单工厂模式](simple-factory)