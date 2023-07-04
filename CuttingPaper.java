package onTapVong2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CuttingPaper {
    static int n;
    static int[][] a = new int[100][100];
    static int res0=0;
    static int res1=0;

    public static boolean check(int xStart,int yStart,int n,int value){
        for (int i = xStart; i <xStart+n ; i++) {
            for (int j = yStart; j <yStart+n ; j++) {
                if (a[i][j] !=value) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void xuly(int xStart,int yStart,int n){
       // dieu kien dung
        if (check(xStart,yStart,n,0)){
            res0++;
            return;
        }
        if (check(xStart,yStart,n,1)){
            res1++;
            return;
        }
        xuly(xStart,yStart,n/2);
        xuly(xStart,yStart+n/2,n/2);
        xuly(xStart+n/2,yStart,n/2);
        xuly(xStart+n/2,yStart+n/2,n/2);
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\onTapVong2\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        int T =scanner.nextInt();
        for (int tc = 1; tc <=T ; tc++) {
            res0=0;
            res1=0;
            n = scanner.nextInt();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <=n ; j++) {
                    a[i][j] = scanner.nextInt();
                }
            }
            xuly(1,1,n);
            System.out.println(res0+"--"+res1);
        }

    }
}
