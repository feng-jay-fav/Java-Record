package LuoGu;


import java.util.Scanner;
public class P1035SeriesSummation {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a=sc.nextDouble();
        double sum=0;
        int i=1;
        for( i=1;;i++){
            sum+=1.0/i;
            if(sum>a){
                break;
            }
        }
        System.out.println(i);
    }
}