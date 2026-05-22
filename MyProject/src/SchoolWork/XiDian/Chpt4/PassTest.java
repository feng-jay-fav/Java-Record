package SchoolWork.Xidian.Chpt4;

public class PassTest {

    float fValue;
    public void changeInt(int num){
        num=55;
    }

    public void changeStr(String value) {  // 引用类型参数
        value = new String("change");  //方法中改变形参所指对象
    }

    void changeObjValue(PassTest ref){
        ref.fValue = 99.0f;
        return;
    }

    public static void main(String args[]){
        int num=1;
        PassTest test=new PassTest();
        test.changeInt(num);
        System.out.println("num:"+num);

        String str;
        str = new String("Hello");
        test.changeStr(str);
        System.out.println("Str value is: " + str);


            test.fValue = 101.0f;
            test.changeObjValue(test); // 引用类型参数的传递
            System.out.println("fvalue is: " + test.fValue);

        }


    }
