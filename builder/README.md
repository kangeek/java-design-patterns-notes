建造者模式（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象，这种类型的设计模式属于创建型模式。建造模式可以将一个复杂对象的内部组成部分，与该对象本身的创建分离开来，从而使得复杂对象的组装更灵活。

文绉绉的话不宜多说，其实这种模式还是挺常见的：  
* 比如我们在订手机套餐的时候，无论是自选还是电信公司配置好的，通常一个套餐包括：多少分钟市话、多少条短信、多少G的省内和省外流量等，这几样通常得有，但是具体选多少可以自由搭配。  
* 比如我们玩网游创建任务的时候，要选择职业、肤色、发型；还有游戏中给自己角色搭配装备的时候，要配置帽子、肩部、披风、上衣、裤子、鞋子等等，还有一系列配饰，十几个框框所有人都是一样的，不同的是放什么样的装备进去。

# 例子
就以刚才提到的网游人物的配置为例：
我们在创建游戏任务的时候，必须填写任务名称、选择职业或种族，然后可以自行配置角色的发型、发色、武器、铠甲等等，当然后边这些有可能是根据职业或种族随机分配的。

首先是角色的发型、发色、武器、铠甲，以及职业：

HairType.java

    public enum HairType {
        BALD("bald"), SHORT("short"), CURLY("curly"), LONG_STRAIGHT("long straight"), LONG_CURLY("long curly");
        private String title;
        HairType(String title) {
            this.title = title;
        }
        @Override
        public String toString() {
            return title;
        }
    }

HairColor.java

    public enum HairColor {
        WHITE, BLOND, RED, BROWN, BLACK;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

Weapon.java

    public enum Weapon {
        DAGGER, SWORD, AXE, WARHAMMER, BOW;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

Armor.java

    public enum Armor {
        CLOTHES("clothes"), LEATHER("leather"), CHAIN_MAIL("chain mail"), PLATE_MAIL("plate mail");
        private String title;
        Armor(String title) {
            this.title = title;
        }
        @Override
        public String toString() {
            return title;
        }
    }

Profession.java

    public enum Profession {
        WARRIOR, THIEF, MAGE, PRIEST;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

在设计具体实现的时候，我们参考《Effective Java》第二条”遇到多个构造器参数时要考虑用构建器“中的内容组织类之间的关系。将Builder类内置于角色内部。

Hero.java

    public final class Hero {
    
        private final Profession profession;
        private final String name;
        private final HairType hairType;
        private final HairColor hairColor;
        private final Armor armor;
        private final Weapon weapon;
        
        private Hero(Builder builder) {
            this.profession = builder.profession;
            this.name = builder.name;
            this.hairColor = builder.hairColor;
            this.hairType = builder.hairType;
            this.weapon = builder.weapon;
            this.armor = builder.armor;
        }
    
        // getters
    
        @Override
        public String toString() {
            ... ...
        }
    
        /**
         * The builder class.
         */
        public static class Builder {
    
            private final Profession profession;
            private final String name;
            private HairType hairType;
            private HairColor hairColor;
            private Armor armor;
            private Weapon weapon;
    
            /**
             * Constructor
             */
            public Builder(Profession profession, String name) {
                if (profession == null || name == null) {
                    throw new IllegalArgumentException("profession and name can not be null");
                }
                this.profession = profession;
                this.name = name;
            }
    
            public Builder withHairType(HairType hairType) {
                this.hairType = hairType;
                return this;
            }
    
            public Builder withHairColor(HairColor hairColor) {
                this.hairColor = hairColor;
                return this;
            }
    
            public Builder withArmor(Armor armor) {
                this.armor = armor;
                return this;
            }
    
            public Builder withWeapon(Weapon weapon) {
                this.weapon = weapon;
                return this;
            }
    
            public Hero build() {
                return new Hero(this);
            }
        }
    }

建造者模式的主要内容其实就在这个`Hero.java`类中了。

我们可以看到`Hero`类中有个子类`Hero.Builder`，具体`Hero`的创建由Builder来完成。可以看到`Hero`只有一个以`Build`为参数的构造函数，所以将只接受这种形式的创建。

> 是不是想到了[单例模式](../singleton)中的那个使用静态内部类来保管单一实例的例子了呢。其实是有相通之处的，我们说过，静态内部类可以看作恰好定义在另一个类内部的普通类。与单例不同的是，这个静态内部类`Builder`由于需要在外部使用，是`public`的。之所以放在内部是因为内部类同外部类之间可以访问所有的成员，更加方便。

那么在使用的使用的时候，就可以通过`Builder`定义的一系列“组装方法”来创建实例了：

    Hero warrior = new Hero.Builder(Profession.WARRIOR, "Amberjill").withHairColor(HairColor.BLOND)
            .withHairType(HairType.LONG_CURLY).withArmor(Armor.CHAIN_MAIL).withWeapon(Weapon.SWORD)
            .build();

这样一个“战士”就诞生了，突然有点想玩网游了呢～

# 总结
建造者模式通常应用于：将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。主要解决在软件系统中，有时候面临着"一个复杂对象"的创建工作，其通常由各个部分的子对象用一定的算法构成；由于需求的变化，这个复杂对象的各个部分经常面临着剧烈的变化，但是将它们组合在一起的算法却相对稳定。

最后给一个小作业可以考虑一下用建造者模式实现：
假设张三要盖房，交钥匙拎包入住那种。他找到服务商，说希望盖五间大瓦房、刮大白简装、配置便宜家居，谈好合同后，服务商找到施工队去盖房、装修和配便宜家具。另外又有个叫李四的人也找到这个服务商，说希望盖一个小洋楼、精装修、配实木家具，服务商又找到另一个擅长小洋楼的施工队去实施。






