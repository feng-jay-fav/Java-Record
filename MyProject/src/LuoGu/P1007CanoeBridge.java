package LuoGu;

import java.util.Scanner ;

public class P1007CanoeBridge {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int maxtime=0,mintime=0;
        int L=sc.nextInt();
        int N=sc.nextInt();
        for (int i=0;i<N;i++){
            int pos=sc.nextInt();
            int nowmax=Math.max(L-pos+1,pos);
            int nowmin =Math.min(L-pos+1,pos);
            maxtime=Math.max(nowmax,maxtime);
            mintime=Math.max(nowmin,mintime);//最算时间也需要取最慢的士兵速度
        }
        System.out.println(mintime+" "+maxtime);

    }
}
