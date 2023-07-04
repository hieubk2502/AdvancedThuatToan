package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HeThongDien {
    static int T, soDao, soDaoCoTramPhatDien, soKetNoi;
    static int[][] a = new int[1001][1001];
    static boolean[] daoCoTramDien = new boolean[1000];
    static int[] visited = new int[1001];


    public static void dfs(int i) {
        for (int j = 0; j < soDao; j++) {
            if (j != i && !daoCoTramDien[j] && a[i][j] == 1) {
                int cur = visited[i] + 1;
                if (cur < visited[j]) {
                    visited[j] = cur;
                    dfs(j);
                }
            }
        }
    }

    public static void reset() {
        for (int i = 0; i < soDao; i++) {
            visited[i] = 999;
            daoCoTramDien[i]=false;
            for (int j = 0; j < soDao; j++) {
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
    public static void inMaTran() {
        System.out.println();
        for (int i = 0; i < soDao; i++) {
            for (int j = 0; j < soDao; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            soDao = scanner.nextInt();
            soDaoCoTramPhatDien = scanner.nextInt();
            soKetNoi = scanner.nextInt();
            reset();
            inVisit();
            inMaTran();
//            inVisit();
            for (int i = 0; i < soDaoCoTramPhatDien; i++) {
                int x = scanner.nextInt();
                daoCoTramDien[x] = true;
                visited[x] = 0;
            }
            for (int i = 0; i < soKetNoi; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                a[x][y] = 1;
                a[y][x] = 1;
            }
            inVisit();
            inMaTran();
            // xu ly
            for (int i = 0; i < soDao; i++) {
                if (daoCoTramDien[i]) {
                    dfs(i);
                }
            }
            inVisit();
            inMaTran();
            // IN ra
            int res = -1;
            int index = 0;
//            inVisit();
            for (int i = 0; i < soDao; i++) {
                if (res < visited[i]&&visited[i]!=0) {
//                    System.out.println("vai");
                    res=visited[i];
                    index=i;
                }
            }
            System.out.println(index);
        }
    }
}
