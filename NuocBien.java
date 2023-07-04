package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NuocBien {
    static int n,m,highMap,count,lowMap;
    static boolean check;
    static int[][] a  =new int[100][100];
    static int[][] visited = new int[100][100];
    static boolean[][] visited2 =new boolean[100][100];
    static int[] dx={-1,0,1,0};
    static int[] dy={0,1,0,-1};

    public static void resetVisted1(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j]=1;
                visited2[i][j]=false;
            }
        }
    }
    public static void inVisted1(){
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(visited[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static boolean checkBien(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
    public static void dequy(int x,int y, int value){
        visited[x][y]=0;
        if (checkBien(x-1,y)&&visited[x-1][y]==1&&a[x-1][y]<=value){
            dequy(x-1,y,value);
        }
        if (checkBien(x+1,y)&&visited[x+1][y]==1&&a[x+1][y]<=value){
            dequy(x+1,y,value);
        }
        if (checkBien(x,y-1)&&visited[x][y-1]==1&&a[x][y-1]<=value){
            dequy(x,y-1,value);
        }
        if (checkBien(x,y+1)&&visited[x][y+1]==1&&a[x][y+1]<=value){
            dequy(x,y+1,value);
        }
    }
    public static void dfs(int x,int y){
        visited2[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int xx = x+dx[i];
            int yy = y+dy[i];
            if (checkBien(xx,yy)&&!visited2[xx][yy]&&visited[xx][yy]==1){
                dfs(xx,yy);
            }
        }
    }
    public static void createVisited(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j]<=lowMap&&visited[i][j]==1&&(i==0||i==n-1||j==0||j==m-1)) {
                    dequy(i, j, lowMap);
                }
            }
        }
    }
    public static void demSoVung(){
        check =true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]==1&&!visited2[i][j]){
                    dfs(i,j);
                    count++;
                    if (count==2) {
                        check=false;
                        break;
                    }
                }
            }
            if (!check)break;
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/input.txt"));
        Scanner scanner= new Scanner(System.in);
        double curStart =System.currentTimeMillis();
        while (true){
            n = scanner.nextInt();
            m = scanner.nextInt();
            highMap=0;
            lowMap=99999;
            if (n==0||m==0)break;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j]= scanner.nextInt();
                    highMap = Math.max(highMap, a[i][j]);
                    if (a[i][j]!=0){
                        lowMap = Math.min(lowMap, a[i][j]);
                    }
                }
            }
            // xu ly gia tri minMap va maxMap
            int res=0;
            while (lowMap<=highMap){
                resetVisted1();
                // tao mang visit
                createVisited();
                // sau khi co bang thi dem so duong bien
                count=0;
                demSoVung();
                if (count==2) {
                    res =lowMap;
                    break;
                }
                lowMap++;
            }
            if (res==0){
                System.out.println("Island never splits.");
            }else{
                System.out.println("Island splits when ocean rises "+res+" feet.");
            }
        }
        double curEnd =System.currentTimeMillis();
        System.out.println("Time: "+(curEnd-curStart)+"ms");
    }
}