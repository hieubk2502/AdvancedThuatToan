package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MienLienThongDFS {
    static int T;
    static int n;
    static int[][] a =new int[100][100];
    static boolean[][] visited = new boolean[100][100];
    static int[] dx = {-1,0,0,1};
    static int[] dy ={0,1,-1,0};
    static int dem;
    static int count;
    public static void reset(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] =false;
            }
        }
    }
    public static boolean checkBien(int x, int y){
        if (x>=0&&x<n&&y>=0&&y<n){
            return  true;
        }
        return false;
    }

    // dfs
    public static void dfs(int x,int y){
        System.out.println(x+"-"+y);
        visited[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int xx = x+dx[i];
            int yy = y+dy[i];
            if (checkBien(xx,yy)&&!visited[xx][yy]&&a[xx][yy]==1){
                dem++;
                dfs(xx,yy);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner  =new Scanner(System.in);
        T =scanner.nextInt();
        for (int tc = 1; tc <= T ; tc++) {
            n = scanner.nextInt();
            reset();
            count=0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j]= scanner.nextInt();
                }
            }
            // xu ly
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j]==1&&!visited[i][j]){
                        dem=1;
                        count++;
                        dfs(i,j);
                        System.out.println("Mien "+count+" : "+dem);
                    }
                }
            }
        }
    }
}
//1
//        6
//        1 1 1 0 0 0
//        0 0 1 0 0 0
//        0 1 1 1 0 0
//        0 0 0 0 0 1
//        1 1 0 1 0 1
//        1 0 1 1 0 1
