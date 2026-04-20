package LuoGu;

import java.util.Scanner;
import java.util.Arrays;

public class P1047TreeOutsideSchoolGate {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int l=sc.nextInt();
        int num =l+1;
        int[] tree=new int [num];
        Arrays.fill(tree,1);
        int areanum=sc.nextInt();
        for(int i=0;i<areanum;i++){
            int start=sc.nextInt();
            int end=sc.nextInt();
            for(int j=start;j<=end;j++){
                tree[j]=0;
            }
        }
        int count =0 ;
        for(int i=0;i<num;i++){
            if(tree[i]==1){
                count++;
            }
        }

        System.out.println(count);
    }

}
