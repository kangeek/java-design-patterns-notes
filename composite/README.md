组合模式（Composite Pattern），又叫部分整体模式，依据树形结构来组合对象，是用来表示部分以及整体层次的一种递归式结构的模式。这种类型的设计模式属于结构型模式，它创建了对象组的树形结构。

其实现实世界中，这种树状结构的组合还是挺普遍的：
1. 组织结构，从CEO到基层组长，都有下属，他们都相当于“枝干”，对于基层员工就是“叶子”节点，不过说到底他们都是公司员工；
2. 目录结构，这个更好理解了，目录里包含子目录和文件，各层目录相当于树状结构的“枝干”，文件相当于“叶子”节点，说到底目录和文件都是文件系统的组件（在Linux里，都可以看作“文件”这个大的概念，甚至鼠标键盘等设备）；
3. 一句文字，无论是书信还是微博，都是由字、词构成的，词呢又是由字构成的，字和词都可以看作句子的组成元素，二者又有包含关系。

看到这里是不是觉得已经不用再看具体例子的代码了呢，聪明的你估计脑海中已经构思除了一个小的demo了。

# 例子

就以文件系统目录结构为例吧。刚才说到，无论是目录还是文件，在Linux中我们都认为是广义的“文件”，为了方便阐述，我们把这个广义的“文件”叫做`Entry`吧，显然目录`Directory`和`File`都是一种`Entry`。如下：

Entry.java

    public abstract class Entry {
        public abstract String getName();
        public abstract int getSize();
        public abstract Entry add(Entry entry) {
            throw new RuntimeException();
        }
        public void printList() {
            printList("");
        }
        public abstract void printList(String prefix);
    
        @Override
        public String toString() {
            return getName() + "(" + getSize() + ")";
        }
    }

File.java

    public class File extends Entry {
        private String name;
        private int size;
    
        public File(String name, int size) {
            this.name = name;
            this.size = size;
        }
    
        public String getName() {
            return this.name;
        }
    
        public int getSize() {
            return this.size;
        }
    
        public void printList(String prefix) {
            System.out.println(prefix + "/" + this);
        }
    }

Directory.java

    public class Directory extends Entry {
        private String name;
        private List<Entry> items = new ArrayList<Entry>();
    
        public Directory(String name) {
            this.name = name;
        }
    
        public String getName() {
            return this.name;
        }
    
        public int getSize() {
            int size = 0;
            Iterator it = this.items.iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                size += entry.getSize();
            }
            return size;
        }
    
        public Entry add(Entry entry) {
            items.add(entry);
            return this;
        }
    
        public void printList(String prefix) {
            System.out.println(prefix + "/" + getName());
            Iterator it = items.iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                entry.printList(prefix + "/" + this.name);
            }
        }
    }


测试一下：

Client.java

    public class Client {
        public static void main(String[] args) {
            Entry bindir = new Directory("bin");
            Entry usrdir = new Directory("usr");
            Entry tmpdir = new Directory("lib");
    
            bindir
                    .add(new File("bash", 4))
                    .add(new File("ls", 6))
                    .add(new File("ip", 8));
    
            usrdir
                    .add(new Directory("bin")
                        .add(new File("top", 10))
                        .add(new File("ssh", 12)))
                    .add(new Directory("local")
                        .add(new Directory("bin")
                            .add(new File("eclipse", 4))
                            .add(new File("idea", 4)))
                        .add(new Directory("src")));
    
            tmpdir
                    .add(new File("test.txt", 12));
    
            Entry rootdir = new Directory("root");
            rootdir.add(bindir).add(usrdir).add(tmpdir);
            rootdir.printList();
        }
    }

输出结果：

    /root
    /root/bin
    /root/bin/bash(4)
    /root/bin/ls(6)
    /root/bin/ip(8)
    /root/usr
    /root/usr/bin
    /root/usr/bin/top(10)
    /root/usr/bin/ssh(12)
    /root/usr/local
    /root/usr/local/bin
    /root/usr/local/bin/eclipse(4)
    /root/usr/local/bin/idea(4)
    /root/usr/local/src
    /root/lib
    /root/lib/test.txt(12)

# 总结

合成设计模式是一种辨识度比较高，应用场景相对比较明确的设计模式，因此相对来说也比较好理解。主要特征就两条：

1. 无论是“枝干“还是”叶子“，都是树的“组成部分”，因此都有共同的抽象，而“枝干”中的list成员变量引用的也是这个共同抽象的列表。概括地说就是“整体”和“部分”具有一致性，是一种递归包含关系；
2. 通常有一个“添加方法”，类似于本例的`add()`，因为是递归操作，一视同仁，这个方法通常定义在抽象类中，默认抛出异常，以便适用“叶子”节点无法添加子节点的情况。