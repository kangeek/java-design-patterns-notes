桥接（Bridge）是用于把抽象化与实现化解耦，使得二者可以独立变化。这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。这种模式涉及到一个作为桥接的接口，使得实体类的功能独立于接口实现类。这两种类型的类可被结构化改变而互不影响。

上面这段是抄来的，读下来还是有点懵逼，不过并不是没用，我们还是老规矩，先从一个例子入手，然后回头再来回味这段话，就会有更深入的理解。

# 例子

相信大家都玩过或正在玩网游，不知道有没有玩过《魔兽世界》，啊～那是我逝去的青春啊～想当年，我是个快乐的，内心兼备光明与黑暗的牧师。当我使用“神圣”天赋的时候，我是一名救死扶伤的奶妈，给队友加血；当我使用“暗影”天赋的时候，我是一名嫉恶如仇的战士，给敌人伤害。

牧师可以使用多种武器，比如锤子和魔杖。一把锤子或魔杖在切换不同天赋的时候都能使用，在“神圣”天赋的时候，武器具备增加治疗的属性，使用的时候发出金黄色的光芒，可以给队友和自己加血；在“暗影”天赋的时候，武器具备增加伤害的属性，使用的时候发出黑紫色的光芒，可以对敌人造成伤害。

无论使用什么武器，都需要施法，施法前要吟唱，然后挥舞一下武器放出法术，最后收回。吟唱的时候微微发光，放出法术的时候光芒从武器到目标任务，收回的时候光芒消失。那么对于法术来说如下设计：

Enchantment.java

    public interface Enchantment {
        void onActivate();
        void apply();
        void onDeactivate();
    }

HolyEnchantment.java

    public class HolyEnchantment implements Enchantment {
        public void onActivate() {
            System.out.println("武器逐渐泛起金黄色的圣光...");
        }
    
        public void apply() {
            System.out.println("一道金黄色的圣光从武器发出，传到队友身上，队友加血1000");
        }
    
        public void onDeactivate() {
            System.out.println("武器的光芒迅速消失...");
        }
        
        @Override
        public String toString() {
            return "神圣魔法";
        }
    }

ShadowEnchantment.java

    public class ShadowEnchantment implements Enchantment {
        public void onActivate() {
            System.out.println("武器逐渐泛起黑紫色的暗影...");
        }
    
        public void apply() {
            System.out.println("一道黑紫色的暗影从武器发出，传到敌人身上，敌人掉血2000");
        }
    
        public void onDeactivate() {
            System.out.println("武器的暗影迅速消失...");
        }
        
        @Override
        public String toString() {
            return "暗影魔法";
        }
    }

对于武器来说，进行如下设计：

Weapon.java

    public abstract class Weapon {
        protected Enchantment enchantment;
        abstract void chant();
        abstract void wield();
        abstract void retrieve();
        public void setEnchantment(Enchantment enchantment) {
            this.enchantment = enchantment;
        }
        public Enchantment getEnchantment() {
            return this.enchantment;
        }
    }

Hammer.java

    public class Hammer extends Weapon {
    
        public Hammer(Enchantment enchantment) {
            this.enchantment = enchantment;
        }
    
        public void chant() {
            System.out.print("牧师拿出锤子，口中吟唱神圣治疗祷言...");
            enchantment.onActivate();
        }
    
        public void wield() {
            System.out.print("牧师将锤子举过头顶挥舞了一下...");
            enchantment.apply();
        }
    
        public void retrieve() {
            System.out.print("牧师收回锤子...");
            enchantment.onDeactivate();
        }
    }

Wand.java

    public class Wand extends Weapon {
        public Wand(Enchantment enchantment) {
            this.enchantment = enchantment;
        }
    
        public void chant() {
            System.out.print("牧师拿出魔杖，口中吟唱神圣治疗祷言...");
            enchantment.onActivate();
        }
    
        public void wield() {
            System.out.print("牧师将魔杖举过头顶挥舞了一下...");
            enchantment.apply();
        }
    
        public void retrieve() {
            System.out.print("牧师收回魔杖...");
            enchantment.onDeactivate();
        }
    }

测试一下效果：

Client.java

    public class Client {
        public static void main(String[] args) {
            System.out.println("进入副本，使用神圣天赋 >>>");
            Enchantment enchantment = new HolyEnchantment();
            Wand wand = new Wand(enchantment);
            wand.chant();
            wand.wield();
            wand.retrieve();
    
            System.out.println("野外打怪，使用暗影天赋 >>>");
            enchantment = new ShadowEnchantment();
            Hammer hammer = new Hammer(enchantment);
            hammer.chant();
            hammer.wield();
            hammer.retrieve();
        }
    }

输出如下：

    进入副本，使用神圣天赋 >>>
    牧师拿出魔杖，口中吟唱神圣治疗祷言...武器逐渐泛起金黄色的圣光...
    牧师将魔杖举过头顶挥舞了一下...一道金黄色的圣光从武器发出，传到队友身上，队友加血1000
    牧师收回魔杖...武器的光芒迅速消失...
    野外打怪，使用暗影天赋 >>>
    牧师拿出锤子，口中吟唱神圣治疗祷言...武器逐渐泛起黑紫色的暗影...
    牧师将锤子举过头顶挥舞了一下...一道黑紫色的暗影从武器发出，传到敌人身上，敌人掉血2000
    牧师收回锤子...武器的暗影迅速消失...

# 总结

我们再来看一下文章开头的话“把抽象化与实现化解耦”。在理解这句话前，我们先看看本例为什么这么设计。

本例在设计的时候，将魔法作为武器的成员进行了组合。如果不使用组合这种方式呢？先不考虑Java是否能多继承，对于一把武器一次特定的使用，需要继承某种`Weapon`和某种`Enchantment`，这样就有了“神圣锤子”、“暗影锤子”、“神圣魔杖”和“暗影魔杖”。

其实我们这里遇到的是一个`M x N`的问题。对于一把武器来说，可能是锤子可能是魔杖，而且比如有魔法才能使用，可能是神圣魔法，可能是暗影魔法，可见这是个`2 x 2`的问题。

那么如果游戏可玩性增加，牧师又多了个天赋（实际上牧师还有个“戒律”天赋），这个时候需要增加“戒律锤子”和“戒律魔杖”，现在是`2 x 3`了。后来牧师又可以拿剑和魔法书了，估计开发人员要一头撞死了。。。

实现系统可能有多个角度分类，每一种角度都可能变化，这里武器类型和天赋类型就是两个角度，它们分别会有变化。这两个角度都有不同的抽象（比如武器和天赋）和实现（比如锤子、魔杖；神圣、暗影）。把这种多角度分类给分离出来让他们独立变化，减少他们之间耦合，就能够有效提高扩展性，如何做到呢？

就如同本例的方式，**使用抽象化和实现化之间使用关联关系（组合或者聚合关系）而不是继承关系，从而使两者可以相对独立地变化**，这就是桥接模式的用意。所以解耦就是将抽象化和实现化之间的耦合解脱开，或者说是将它们之间的强关联（可以理解成编译时确定的关联）改换成弱关联（可以理解成运行时才确定的关联），将两个角色之间的继承关系改为关联关系。

所以，桥接模式的使用场景如下：
1. 如果一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性，避免在两个层次之间建立静态的继承联系，通过桥接模式可以使它们在抽象层建立一个关联关系。
2. 对于那些不希望使用继承或因为多层次继承导致系统类的个数急剧增加的系统，桥接模式尤为适用。
3. 一个类存在两个独立变化的维度，且这两个维度都需要进行扩展，就是`M x N`问题，让M和N自己玩儿。