package SchoolWork.Xidian.Chpt4.pkg2;
import SchoolWork.Xidian.Chpt4.pkg1.*;



public class ProtectedVsPackageAndPublic {
    public static void main(String[] args){
        C obj=new C();
        CSub csub=new CSub();
        //! obj.func();	//不是C的子类，且与C非同一个包
    }
}

class CSub extends SchoolWork.Xidian.Chpt4.pkg1.C{		//C的子类，可以访问C的func()方法
    void mtd(C parent, CSub sub){
        func();
        //! parent.func();  //应通过子类引用而非父类引用访问func()
        sub.func();
    }

}
