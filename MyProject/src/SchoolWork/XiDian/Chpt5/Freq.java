package SchoolWork.Xidian.Chpt5;

import java.util.Map;
import java.util.TreeMap;

public class Freq {
    public static void main(String args[]) {
        String[] words = { "if", "it", "is", "to", "be", "it",
                "is", "up", "to", "me", "to", "delegate" };
        Integer freq;
        Map<String, Integer> m = new TreeMap<String, Integer>();

        for (String a : words) {
            freq = m.get(a);
            if (freq == null) {
                freq = new Integer(1);
            } else {
                freq = new Integer(freq + 1);
            }
            m.put(a, freq);
        }
        System.out.println(m.size() + " distinct words detected:");
        System.out.println(m);
    }
}