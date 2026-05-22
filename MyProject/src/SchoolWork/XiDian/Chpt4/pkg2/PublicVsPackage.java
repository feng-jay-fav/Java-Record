package SchoolWork.Xidian.Chpt4.pkg2;
import SchoolWork.Xidian.Chpt4.pkg1.*;


public class PublicVsPackage {
    public static void main(String[] args){
        A obj=new A();
        //B obj2=new B(); 	//在pkg1之外不能创建B的对象
        //obj.func(); 		//在pkg1之外不能访问func()方法

        C obj1=new C();	//默认构造方法的权限与类的访问权限相同		obj.func2();
        //obj1.func1();	//与C在同一包中的其他类无法访问func1()

    }

}

class C {
    private void func1() {
        System.out.println("C's method 1");
    }

    void func2() {
        System.out.println("C's method 2");
        this.func1(); //同一个类内可以调用func1()
    }
}
