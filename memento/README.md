备忘录模式（Memento pattern）又叫快照模式（Snapshot pattern），是对象的行为模式。用于保存一个对象的某个状态，以便在适当的时候恢复对象。

# 例子

我比较喜欢“快照模式”这个名词，因为比较形象。今天的例子也从“快照”说开去。

虚拟机估计大多数人都用过，比如我去年就开始使用 Deepin Linux（没错，就是这么硬的植入广告，Deepin确实很好用，强烈推荐~） 作为主要操作系统。不过Windows中还是有不少应用必不可少的，比如Office系列和Adobe系列，我又不想Linux和Windows双系统切换，那最好的办法就是在Linux中安装Windows虚拟机。

虚拟机有一个很不错的功能就是“打快照”，把系统调到最舒服的状态，装好该装的软件，然后打个快照，就可以把当前的系统状态保存下来，一旦哪一天系统搞坏了，再用这个快照恢复一下就好了。

虚拟机可以在开机和关机状态下打快照。
  * 关机状态下，保存虚拟磁盘的状态就好了，就像我们物理机把硬盘保存好，换到别的物理机上启动；
  * 开机状态下，除了虚拟磁盘的存储快照，还会将内存的状态保存为内存快照到物理存储上，恢复快照后的系统仍然是运行中的状态，内存快照会重新加载到内存中，因此所打开的应用会继续快照时候的状态执行，就像物理机的休眠。

如果要模拟这个过程，就可以使用备忘录模式/快照模式（以下叫“快照模式”吧）。

下手写代码之前，我们先看一下用户在使用快照功能的时候的特点：
  * 用户不必关心打快照的细节。用户只需要在需要保存虚拟机状态的时候点“打快照”的按钮就可以了，具体保存了哪些内容不care；恢复快照也是同样。
  * 用户不能随意修改快照中的内容。无论是Virtualbox还是VMware都不会提供给用户修改快照中内容的功能，事实上用户也很难插手。用户只需要知道自己所做的快照的“快照树”或“快照列表”就可以了。

这是快照模式的应用场景的典型特点。那就是**对于对象状态（备忘录/快照）的使用方来说，并不关心如何具体保存和恢复目标对象的状态，况且多数情况下，为了安全起见，并不会暴露太多基于状态的处理细节给使用方。**

基于此，对于用户来说，只需要知道有“快照”这么个神奇好用的玩意儿就好：

Snapshot.java

    public interface Snapshot {
    }

没错，就是这样一个空的接口，或者只包含必要的操作即可。

对于虚拟机来说，支持多种操作，包括打快照和恢复到某个快照：

VirtualMachine.java

    public class VirtualMachine {
        // 虚拟机名称
        private String name;
        // 虚拟机配置
        private String devices;
        // 虚拟机内存内容，简化为一个String的列表
        private List<String> memory;
        // 虚拟机存储内容，简化为一个String的列表
        private List<String> storage;
        // 虚拟机状态
        private String state;
    
        public VirtualMachine(String name, String devices) {
            this.name = name;
            this.devices = devices;
            this.memory = new ArrayList<String>();
            this.storage = new ArrayList<String>();
            this.state = "created";
        }
    
        /**
         * 创建虚拟机
         * @param name 虚拟机名称
         * @param devices 虚拟机配置
         */
        public VirtualMachine createVM(String name, String devices) {
            return new VirtualMachine(name, devices);
        }
    
        // 开机、关机、暂停、恢复等功能。。。 略

        /**
         * 打开应用，加载到内存，用来模拟内存中的内容
         */
        public void openApp(String appName) {
            if ("running".equals(state)) {
                this.memory.add(appName);
                System.out.println("虚拟机" + name + "打开应用： " + appName);
            }
        }
    
        /**
         * 关闭应用，从内存中删除，用来模拟内存中的内容
         */
        public void closeApp(String appName) {
            if ("running".equals(state)) {
                this.memory.remove(appName);
                System.out.println("虚拟机" + name + "关闭应用： " + appName);
            }
        }
    
        /**
         * 保存文件，写入虚拟磁盘，用来模拟存储中的内容
         */
        public void saveFile(String file) {
            if ("running".equals(state)) {
                this.storage.add(file);
                System.out.println("虚拟机" + name + "中保存文件： " + file);
            }
        }
    
        /**
         * 删除文件，从虚拟磁盘中删除，用来模拟存储中的内容
         */
        public void delFile(String file) {
            if ("running".equals(state)) {
                this.storage.remove(file);
                System.out.println("虚拟机" + name + "中删除文件： " + file);
            }
        }
    
        /**
         * 打快照，如果是开机状态会保存内存快照和存储快照；如果是关机状态则仅保存存储快照即可。
         */
        public Snapshot takeSnapshot() {
            if ("shutdown".equals(state)) {
                return new VMSnapshot(null, new ArrayList<String>(storage));
            } else {
                return new VMSnapshot(new ArrayList<String>(memory), new ArrayList<String>(storage));
            }
        }
    
        /**
         * 恢复快照
         */
        public void restoreSnapshot(Snapshot snapshot) {
            VMSnapshot tmp = (VMSnapshot)snapshot;
            this.memory = new ArrayList<String>(tmp.getMemory());
            this.storage = new ArrayList<String>(tmp.getStorage());
            if (tmp.getMemory() == null) {
                this.state = "shutdown";
            }
        }
    
        @Override
        public String toString() {
            StringBuffer stringBuffer =  new StringBuffer();
            stringBuffer.append("------\n[虚拟机“" + name + "”] 配置为“" + devices + "”，" + "目前状态为：" + state + "。");
            if ("running".equals(state)) {
                stringBuffer.append("\n    目前运行中的应用有：" + memory.toString());
                stringBuffer.append("\n    最近保存的文件有：" + storage.toString());
            }
            stringBuffer.append("\n------");
            return stringBuffer.toString();
        }

}

如上是虚拟机的相关操作，其中开机、关机、暂停、恢复等略，可参考源码。简化起见，这里用打开和关闭应用，来影响内存中内容的加载和删除；用保存和删除文件，来影响磁盘等存储介质上内容的增删。

`takeSnapshot()`方法和`restoreSnapshot(Snapshot)`方法用来保存和恢复虚拟机的状态，这里的状态也就是内存快照和存储快照。所以具体的`Snapshot`的实现类需要有内存和存储的属性。这里就不考虑存储的增量快照了哈。

VMSnapshot.java

    public class VMSnapshot implements Snapshot {
        private List<String> memory;
        private List<String> storage;

        public VMSnapshot(List<String> memory, List<String> storage) {
            this.memory = memory;
            this.storage = storage;
        }

        // getters & setters
    }

但是这种实现有个问题，那就是对于“用户”来说，也就暴露了虚拟机快照的内容和相关操作，用户就可以自己不适用虚拟化软件自己创建快照（new VMSnapshot）或随意修改快照内容了，这显然不是VMware或Virtualbox希望的。因此`VMSnapshot`对用户来说必须是不可见的。

这时候就需要将`VMSnapshot`类置于`VirtualMachine`类的内容，并声明为*“私有的静态内部类”*：

VirtualMachine.java
    
    public class VirtualMachine {
        ...
        private static class VMSnapshot implements Snapshot {
            ...
        }
    }

> 简单捋一捋内部类：
>   * 静态内部类是最简单的内部类，可以理解为普通的类，只不过恰好放到了另一个类内部，与普通类唯一的不同是内部类可以访问其外部类的私有成员；
>   * 非静态内部类更加复杂一些，它的对象与外部类的对象有一一对应的关系，不可以独立于外部类的对象之外而存在，同样也可以访问其外部类的私有成员；
>   * 匿名内部类，是为了临时实例化一个接口或抽象类，因此必须补全接口或抽象类中的抽象方法，由于是临时的，所以就没必要再命名了。对于只有一个方法的接口，其匿名内部类可以用lambda表达式来代替，更加简练。

对于用户来说，这样使用虚拟机的快照功能：

User.java

    public class User {
        public static void main(String[] args) {
            Stack<Snapshot> snapshots = new Stack<Snapshot>();
    
            VirtualMachine ubuntu = new VirtualMachine("ubuntu", "1个4核CPU，8G内存，80G硬盘");
    
            ubuntu.startup();
            ubuntu.openApp("网易云音乐");
            ubuntu.openApp("谷歌浏览器");
            ubuntu.saveFile("/tmp/test.txt");
            System.out.println(ubuntu);
    
            // 打快照
            snapshots.push(ubuntu.takeSnapshot());
    
            ubuntu.closeApp("网易云音乐");
            ubuntu.openApp("IntelliJ IDEA");
            ubuntu.delFile("/tmp/test.txt");
            ubuntu.saveFile("/workspace/hello.java");
            System.out.println(ubuntu);
    
            // 恢复快照
            ubuntu.restoreSnapshot(snapshots.peek());
            System.out.println("恢复到最近的快照...");
    
            System.out.println(ubuntu);
        }
    }

输出如下：

    虚拟机ubuntu已启动
    虚拟机ubuntu打开应用： 网易云音乐
    虚拟机ubuntu打开应用： 谷歌浏览器
    虚拟机ubuntu中保存文件： /tmp/test.txt
    ------
    [虚拟机“ubuntu”] 配置为“1个4核CPU，8G内存，80G硬盘”，目前状态为：running。
        目前运行中的应用有：[网易云音乐, 谷歌浏览器]
        最近保存的文件有：[/tmp/test.txt]
    ------
    虚拟机ubuntu关闭应用： 网易云音乐
    虚拟机ubuntu打开应用： IntelliJ IDEA
    虚拟机ubuntu中删除文件： /tmp/test.txt
    虚拟机ubuntu中保存文件： /workspace/hello.java
    ------
    [虚拟机“ubuntu”] 配置为“1个4核CPU，8G内存，80G硬盘”，目前状态为：running。
        目前运行中的应用有：[谷歌浏览器, IntelliJ IDEA]
        最近保存的文件有：[/workspace/hello.java]
    ------
    恢复到最近的快照...
    ------
    [虚拟机“ubuntu”] 配置为“1个4核CPU，8G内存，80G硬盘”，目前状态为：running。
        目前运行中的应用有：[网易云音乐, 谷歌浏览器]
        最近保存的文件有：[/tmp/test.txt]
    ------

课件恢复快照之后，内存和磁盘中的内容均被恢复。

# 总结

通过上边的例子，总结一下备忘录模式的几个特点：

  * 在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。从实现上来说，使用静态内部类，而不是非静态内部类。
  * 状态（备忘录/快照）保证其内容不被除了被保存状态的对象之外的其他对象所读取或操作。从实现上来说，使用私有的静态内部类。例子中，`User`无法访问或操作`VirtualMachine.VMSnapshot`。
  * 备忘录模式的角色：
    * Originator，也就是被保存状态的类，例子中的`VirtualMachine`；
    * Memento，也就是保存的状态，例子中的`VirtualMachine.VMSnapshot`；
    * Caretaker，在有些实现场景中，还会有一个专门负责保存Memento对象的类，可以想象成上边的例子在用户和虚拟机中间增加一个“虚拟机管理软件”的角色（比如Virtualbox或VMware），由虚拟机管理软件来维护所有的快照，这也是很好理解的。设计模式不用死记硬背类关系和角色~

通过上边的特点介绍，可以看出备忘录模式通常应用于**Originator的状态必须保存在其以外的地方，同时又必须由Originator进行状态读写**的场景下。备忘录模式的好处就在于能够有效对外屏蔽Originator内部信息。不好处也是显而易见的，就拿快照来说，如果不考虑“增量快照”，那么快照的保存和恢复有可能是非常消耗资源的一种操作。