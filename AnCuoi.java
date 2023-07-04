package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnCuoi {
    static int T,n,m,res;
    static int[][] a =new int[21][21];
    static boolean[] visited = new boolean[21];

    public static void reset(){
        for (int i = 1; i <= n; i++) {
            visited[i] =false;
            for (int j = 1; j <= n; j++) {
                a[i][j]=0;
            }
        }
    }
    public static void resetVisited(){
        for (int i = 1; i <= n; i++) {
            visited[i] =false;
        }
    }
    public static void bt(int start, int stop, int count){
        if (res<count){
            return;
        }
        if (start==stop){
            if (res>count){
                res =count;
            }
            return;
        }
        for (int i = 1; i <=n; i++) {
            if (!visited[i]&&a[start][i]==1){
                visited[i]=true;
                bt(i,stop,count+1);
                visited[i] =false;
            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = scanner.nextInt();
            m = scanner.nextInt();
            reset();
            for (int i = 0; i < m; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                a[x][y] =1;
            }
            res =999;
            bt(1,2,0);
            int res1 =res;
            System.out.println(2*res1);
            res=999;
            resetVisited();
            bt(2,1,0);
            int res2 =res;
//            System.out.println(res2);
//            System.out.println(res1+res2);
        }
    }
}
//2
//        6 7
//        1 3
//        3 4
//        4 5
//        5 1
//        4 2
//        2 6
//        6 3
//        9 11
//        1 3
//        3 4
//        4 2
//        2 5
//        5 3
//        3 6
//        6 1
//        2 7
//        7 8
//        8 9
//        9 1