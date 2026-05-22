package SchoolWork.Xidian.Chpt5;

import java.util.ArrayList;
import java.util.List;

public class UseArrayList {
    public static void main(String[] args) {
        List<String> scores = new ArrayList<String>();
        scores.add("86");
        scores.add("98");
        scores.add(1, "99");
        for (int i = 0; i < scores.size(); i++) {
            System.out.print(scores.get(i) + " ");
        }
        scores.set(1, "77");
        scores.remove(0);
        System.out.println("\n修改并删除之后");
        for (int i = 0; i < scores.size(); i++) {
            System.out.print(scores.get(i) + " ");
        }
        System.out.println("\n按字符串输出\n" + scores.toString());
    }
}