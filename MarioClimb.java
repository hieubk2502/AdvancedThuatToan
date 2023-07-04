package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarioClimb {
    static int T,n,m,res,font,rear,x1,y1,x2,y2;
    static int[][] a = new int[1000][1000];
    static int[][] visited =new int[1000][1000];
    static int[] queueX =new int[100000];
    static int[] queueY = new int[100000];
    static int[] dx = {0,0,1,-1};
    static int[] dy = {-1,1,0,0};
    public static boolean checkBien(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
    public static void resetVisited(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j]=-1;
            }
        }
    }
    public static void inVisited(){
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(visited[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void bfs(int x, int y){
        font=rear=0;
        queueX[rear%10000]=x;
        queueY[rear%10000] =y;
        rear++;
        visited[x][y]=0;
        while(font!=rear){
            int tempX = queueX[font%10000];
            int tempY = queueY[font%10000];
            font++;
            // sang ngang
            for (int i = 0; i < 2; i++) {
                int xx = tempX+dx[i];
                int yy = tempY+dy[i];
                if (checkBien(xx,yy)&&a[xx][yy]!=0&&(visited[xx][yy]==-1||visited[xx][yy]>visited[tempX][tempY])){
                    visited[xx][yy] =visited[tempX][tempY]==0?Math.abs(yy-tempY):visited[tempX][tempY];
                    queueX[rear%10000]=xx;
                    queueY[rear%10000]=yy;
                    rear++;
                }
            }
            // len xuong
            for (int i = 2; i < 4; i++) {
                for (int j = 1;; j++) {
                    int xx = tempX+dx[i]*j;
                    int yy = tempY+dy[i]*j;
                    if (checkBien(xx,yy)&&a[xx][yy]!=0&&(visited[xx][yy]==-1||visited[xx][yy]>visited[tempX][tempY])){
                        visited[xx][yy] = Math.max(visited[tempX][tempY], Math.abs(xx - tempX));
                        queueX[rear%10000]=xx;
                        queueY[rear%10000]=yy;
                        rear++;
                        break;
                    } else if (!checkBien(xx,yy)) {
                        break;
                    }
                }
            }
//            inVisited();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = scanner.nextInt();
            m = scanner.nextInt();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <m; j++) {
                    a[i][j] = scanner.nextInt();
                    visited[i][j]=-1;
                    if (a[i][j]==2){
                        x1=i;y1=j;
                    }
                    if (a[i][j]==3){
                        x2=i;y2=j;
                    }
                }
            }
            bfs(x1,y1);
            res=visited[x2][y2];
            System.out.println("#"+tc+" "+res);
        }
    }
}
