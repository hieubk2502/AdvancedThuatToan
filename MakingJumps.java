package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// su dung DFS+backtracking

public class MakingJumps {
    static int n;
    static int[][] arr = new int[10][10];
    static boolean[][] visited = new boolean[10][10];
    static int sum;
    static int start, end;
    static int res,count;
    static int[] dx ={-1,-2,-2,-1,1,2,2,1};
    static int[] dy ={-2,-1,1,2,2,1,-1,-2};

    public static void inRa(){
        System.out.println();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
    public static void resetArray(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                arr[i][j] =0;
            }
        }
    }
    public static void resetVisited(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] =false;
            }
        }
    }
    public static boolean checkBien(int x, int y){
        return x>=0&&x<10&&y>=0&&y<10;
    }
    public static void dfsBackTracking(int x, int y, int count){
        if (count>res){
            res=count;
        }
        visited[x][y] =true;
        for (int i = 0; i < 8; i++) {
            int xx =x+dx[i];
            int yy =y+dy[i];
            if (checkBien(xx,yy)&&arr[xx][yy]==1&&!visited[xx][yy]){
                dfsBackTracking(xx,yy,count+1);
            }
        }
        // reset lai nuoc di hien tai
        // back track lai
        visited[x][y]=false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        int T  =scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = scanner.nextInt();
            sum=0;
            res=0;
            count=0;
            resetArray();
            resetVisited();
            for (int i = 0; i < n; i++) {
                start =scanner.nextInt();
                end = scanner.nextInt();
                sum +=end;
                for (int j = start; j < start+ end; j++) {
                    arr[i][j]=1;
                }
            }
//            inRa();
            dfsBackTracking(0,0,1);
            if (sum-res==1){
                System.out.println("Case "+tc+", "+1 +" square can not be reached.");
            }else{
                System.out.println("Case "+tc+", "+(sum-res) +" squares can not be reached.");
            }
        }

    }
}
//3
//        7 0 3 0 3 0 4 0 4 1 3 1 7 4 4
//        3 0 3 0 3 0 3
//        2 0 1 2 1
//        0
