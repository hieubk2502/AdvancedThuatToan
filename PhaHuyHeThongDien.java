package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhaHuyHeThongDien {
    static int T, soDao, soVung, soVungCur;
    static int[][] a = new int[1001][1001];
    static int[][] visited = new int[1001][1001];
    static boolean[] check =new boolean[1001];


    public static void dfs(int i) {
        check[i]=true;
        for (int j = 1; j <= soDao; j++) {
            if (!check[j] && a[i][j] == 1) {
                dfs(j);
            }
        }
    }

    public static void resetCheckDao() {
        for (int i = 1; i <= soDao; i++) {
            check[i]=false;
        }
    }
    public static void resetMatran() {
        for (int i = 1; i <= soDao; i++) {
            for (int j = 1; j <= soDao; j++) {
                a[i][j] = 0;
            }
        }
    }

    public static void inVisit() {
        System.out.println();
        for (int i = 0; i < soDao; i++) {
            System.out.print(visited[i] + " ");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            soDao = scanner.nextInt();
            resetMatran();
            for (int i = 1; i <= soDao; i++) {
                for (int j = 1; j <= soDao; j++) {
                    a[i][j]= scanner.nextInt();
                }
            }
            resetCheckDao();
            soVung=0;
            // check xem co may hon dao
            for (int i = 1; i <= soDao; i++) {
                if (!check[i]){
                    dfs(i);
                    soVung++;
                }
            }
//            System.out.println(soVung);
            // khi pha 1 dao di thi so vung phan tach max
            resetCheckDao();
            soVungCur=0;
            int resDao=0;
            for (int i = 1; i <=soDao ; i++) {
                resetCheckDao();
                check[i]=true;
                int soVungCurrent =0;
                for (int j = 1; j <= soDao; j++) {
                    if (!check[j]){
                        dfs(j);
                        soVungCurrent++;
                    }
                }
                if (soVungCurrent>soVungCur){
                    soVungCur=soVungCurrent;
                    resDao=i;
                }
                check[i]=false;
            }
            int res =soVungCur-soVung;
            System.out.println(res==0?res:resDao);
        }
    }
}
