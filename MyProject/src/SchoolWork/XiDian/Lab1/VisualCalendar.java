package SchoolWork.Xidian.Lab1;

import java.text.DateFormat;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.*;  
/** 
 *可视化日历
 */  
public class VisualCalendar {  
    public static void main(String[] args) {  
        System.out.println("请输入日期（年-月-日）");
        createCalendar();  
    }  
  
    private static void createCalendar() {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//规定用户输入的日期格式
        boolean flag = true;  
        Date d = null;  
        while(flag) {  
            Scanner sc = new Scanner(System.in);  
            String datetime = sc.next();  
            try {  
                d = df.parse(datetime);//把用户输入的日期转化成Date类型
                flag = false;  
            } catch (ParseException e) {  
                System.out.println("日期格式不正确，请重新输入：");
            }  
        }  
        Calendar c = new GregorianCalendar();  
        c.setTime(d);//使用用户输入的目的的日期作为Calender的时间
        int day = c.get(Calendar.DATE);//保存用户输入的日
          
        c.set(Calendar.DATE, 1);//把Calender的日子设为一
        int week = c.get(Calendar.DAY_OF_WEEK);//求出该月一号是星期几
        int days = c.getActualMaximum(Calendar.DATE);//求出该月的总天数
        System.out.println("日\t一\t二\t三\t四\t五\t六");
        for(int j=1;j<week;j++) {  
            System.out.print("\t");  
        }  
        for(int i=1;i<=days;i++) {  
            if(day == i) {  
                System.out.print("*");//如果用户输入的是日子，表明当前日期，使其更醒目
            }  
            System.out.print(i+"\t");  
            //如果是星期六则换行
            if(c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) {  
                System.out.println();  
            }  
            c.add(Calendar.DATE, 1);//打印一个日子后让日期加一
        }  
    }  
}  