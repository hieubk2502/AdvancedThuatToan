package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IphoneS6 {
    static int T, n,font,rear,res,index;
    static int[][] a = new int[1000][1000];
    static boolean[][] visited = new boolean[1000][1000];
    static int[] queueX = new int[10000];
    static int[] queueY = new int[10000];
    static int[] luuViTri0DaDuyetX = new int[1000];
    static int[] luuViTri0DaDuyetY = new int[1000];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[] count = new int[6];

    public static boolean checkBien(int x,int y){
        return x>=0&&x<n&&y>=0&&y<n;
    }

    public static void resetVisited(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j]=false;
            }
        }
        // gan cac gia tri 0 da duyet lai bang true
        for (int i = 0; i < index; i++) {
            visited[luuViTri0DaDuyetX[i]][luuViTri0DaDuyetY[i]]=true;
        }
        // reset counting
        for (int i = 1; i < 6; i++) {
            count[i]=0;
        }
    }
    public static void resetAllVisited(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j]=false;
            }
        }
    }

    public static void dfs(int x,int y,int value){
        a[x][y]=value;
        for (int i = 0; i < 4; i++) {
            int xx =x+dx[i];
            int yy = y+dy[i];
            if (checkBien(xx,yy)&&a[xx][yy]==0){
                dfs(xx,yy,value);
            }
        }
    }
    public static void dfs1(int x,int y,int value){
        visited[x][y]=true;
        for (int i = 0; i < 4; i++) {
            int xx =x+dx[i];
            int yy = y+dy[i];
            if (checkBien(xx,yy)&&!visited[xx][yy]&&a[xx][yy]==value){
                dfs1(xx,yy,value);
            }
        }
    }
    public static void bfs(int x,int y){
        font=rear=0;
        queueX[rear%10000] = x;
        queueY[rear%10000] = y;
        rear++;
        // add diem nay vao mang vi tri 0 da xet
        luuViTri0DaDuyetX[index] = x;
        luuViTri0DaDuyetY[index++] =y;
        visited[x][y]=true;
        while (rear!=font){
            int tempX = queueX[font%10000];
            int tempY = queueY[font%10000];
            font++;
            for (int i = 0; i < 4; i++) {
                int xx = tempX + dx[i];
                int yy = tempY + dy[i];
                if (checkBien(xx,yy)&&!visited[xx][yy]&&(a[tempX][tempY]==0||a[xx][yy]==a[tempX][tempY])){
                    visited[xx][yy]=true;
                    queueX[rear%10000] = xx;
                    queueY[rear%10000] = yy;
                    rear++;
                    if (a[xx][yy]==0){
                        luuViTri0DaDuyetX[index] = xx;
                        luuViTri0DaDuyetY[index++] =yy;
                    }
                    // countting
                    count[a[xx][yy]]++;
                }
            }
        }
        // sau khi da co countting chon vi tri de dien
        int maxCur = count[1];
        int dienSo = 1;
        for (int i = 1; i < 6 ; i++) {
            if (count[i]>=maxCur){
                maxCur = count[i];
                dienSo=i;
            }
        }
        // sau khi co gia tri max
        dfs(x,y,dienSo);
    }
    public static void dienSoNao(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j]==0&&!visited[i][j]){
                    resetVisited();
                    bfs(i,j);
//                    inVisited();
//                    inArr();
//                    inCount();
                }
            }
        }
    }
    public static void demSoMienNao(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]){
                    dfs1(i,j,a[i][j]);
                    res++;
                }
            }
        }
    }

    public static void solve(){
        //
        index = 0;
        res=0;
        resetAllVisited();
        dienSoNao();
        // sau khi da dien
        resetAllVisited();
        demSoMienNao();
    }
    public static void inVisited(){
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print( visited[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void inArr(){
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print( a[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void inCount(){
        System.out.println();
        for (int i = 1; i < 6; i++) {
            System.out.print(count[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn( new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T =scanner.nextInt();
        for (int tc = 1; tc <= T ; tc++) {
            n =scanner.nextInt();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = scanner.nextInt();
                }
            }
            // xu ly
            solve();
            System.out.println("Case #"+tc);
            System.out.println(res);
        }
    }
}
//4
//        5
//        5 5 1 4 4
//        4 0 2 4 2
//        5 0 0 2 0
//        5 4 3 0 1
//        1 3 3 2 1
//        7
//        0 0 0 0 0 0 0
//        0 0 0 0 0 0 0
//        0 0 0 0 0 0 0
//        5 0 5 0 5 0 5
//        0 0 0 0 0 0 0
//        0 0 0 0 0 0 0
//        0 0 0 0 0 0 0
//        10
//        1 3 5 1 4 0 0 4 2 1
//        1 1 2 1 1 0 5 0 2 1
//        5 0 2 0 4 4 4 0 1 1
//        0 2 2 4 0 5 4 2 1 3
//        1 1 2 2 2 3 3 2 1 1
//        5 1 1 2 0 3 3 2 2 1
//        3 1 1 1 0 0 1 2 2 5
//        3 1 4 1 2 0 4 0 0 5
//        4 0 3 3 1 3 3 0 0 1
//        5 0 3 1 4 3 3 1 2 3
//        20
//        0 2 1 0 1 3 3 3 3 4 4 1 1 1 4 1 1 4 1 4
//        0 2 3 5 1 2 3 3 3 3 4 2 1 3 2 2 1 1 1 3
//        0 2 1 5 1 2 2 2 0 3 2 3 4 2 1 3 3 0 1 1
//        1 5 4 5 1 2 5 5 5 5 2 5 5 2 0 0 3 3 5 4
//        1 0 5 5 3 5 3 0 4 5 5 5 1 0 1 1 3 4 0 0
//        4 5 3 4 4 5 3 3 0 0 3 3 1 3 1 3 2 5 0 0
//        3 2 0 0 0 3 4 1 1 1 1 3 5 2 1 3 1 4 0 5
//        2 2 0 2 3 3 3 0 1 5 1 3 5 2 5 3 4 4 0 5
//        3 4 0 1 1 1 0 4 4 4 4 1 5 0 4 5 3 4 4 4
//        3 2 0 1 4 4 3 0 0 0 3 5 1 0 1 4 1 3 4 4
//        0 3 1 1 1 1 1 1 2 2 3 3 1 0 5 4 4 4 4 4
//        4 2 5 3 4 4 4 1 2 5 5 5 4 1 1 1 4 1 0 5
//        4 2 5 1 4 2 5 2 0 1 5 2 4 1 4 1 1 0 0 0
//        1 2 3 0 1 4 2 2 4 1 1 0 4 4 4 5 4 1 0 0
//        3 2 0 1 1 1 2 1 3 1 0 4 1 4 4 2 4 3 3 0
//        3 3 5 4 1 1 1 1 3 2 4 4 4 4 4 3 3 3 3 4
//        4 3 1 1 1 1 1 1 1 3 2 2 0 5 4 4 4 5 0 0
//        3 3 2 0 5 0 3 3 1 3 2 4 0 4 0 4 4 2 4 4
//        1 4 4 3 4 5 2 4 4 4 4 4 0 5 0 4 0 3 1 0
//        5 4 4 5 2 3 3 0 0 5 2 4 3 3 2 3 4 4 5 0