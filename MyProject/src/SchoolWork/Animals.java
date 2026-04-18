package SchoolWork;

class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    public void showName() {
        System.out.println("动物名字：" + name);
    }
}

class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    // 重写
    @Override
    public void showName() {
        System.out.println("小猫名字：" + name);
    }

    // 子类里调用父类原本的方法
    public void testSuper() {
        // 调用父类的 showName
        super.showName();
    }
}

public class Animals {
    public static void main(String[] args) {
        // 多态：父类引用指向子类对象
        Animal a = new Cat("小花");

        // 动态绑定：运行执行子类重写的方法
        a.showName();

        // instanceof 判断类型
        System.out.println(a instanceof Cat); // true

        // 传参：父类参数可以传子类
        test(a);
    }

    public static void test(Animal animal) {
        animal.showName();
    }
}

class A {
    // 类里面可以在方法里 new 自己
    public void test() {
        A a = new A();
    }
}