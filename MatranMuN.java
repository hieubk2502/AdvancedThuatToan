package onTapVong2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatranMuN {
    static long mod = 1000000007;
    public static long[][] tich(long[][] a, long[][] b){
        long[][] res = new long[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                long result=0;
                for (int k = 0; k < a.length; k++) {
                    result+=(a[i][k]*b[k][j])%mod;
                    result %=mod;
                }
                res[i][j] = result;
            }
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                a[i][j] = res[i][j];
            }
        }
        return a;
    }
    public static long[][] pow(long[][] a,long[][] b, long mu){
        if (mu<=1){
            return a;
        }
        if (mu%2==0){
            pow(a,b,mu/2);
            return tich(a,a);
        }
        else {
            pow(a,b,mu-1);
            return tich(a,b);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\onTapVong2\\input.txt");
        Scanner scanner = new Scanner(fileInputStream);
        int T =scanner.nextInt();
        for (int tc = 1; tc <=T ; tc++) {
            int n  = scanner.nextInt();
            long mu= scanner.nextInt();
            long[][] a = new long[n][n];
            long[][] b = new long[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j]= scanner.nextInt();
                    b[i][j]=a[i][j];
                }
            }
            pow(a,b,mu);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(a[i][j]+" ");
                }
                System.out.println();
            }
        }

    }
}
