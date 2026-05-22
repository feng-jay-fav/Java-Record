package SchoolWork.Xidian.Chpt4.pkg1;

public class A {
    public A(){ System.out.println("A's constructor"); }
    void func(){ System.out.println("A‘s method"); } //func()为包权限

}

class B{//B为包权限
    public B(){ System.out.println("B's constructor"); }
}

