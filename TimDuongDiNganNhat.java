package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TimDuongDiNganNhat {
    static int T;
    static int n;
    static int[][] a =new int[100][100];
    static int[][] visited = new int[100][100];
    static int[] dx = {-1,0,0,1};
    static int[] dy ={0,1,-1,0};
    static int x1,y1,x2,y2;
    static int res;
    static int[] queueX = new int[10000];
    static int[] queueY =new int[10000];
    static int font,rear;

    public static void resetVisited(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j]=0;
            }
        }
    }
    public static boolean checkBao(int x,int y){
        if (x>=0&&x<n&&y>=0&&y<n){
            return true;
        }
        return false;
    }
    public static void bfs(int x, int y){
        visited[x][y] = 0;
        font =rear = 0;
        queueX[rear] =x;
        queueY[rear] =y;
        rear++;
        while (font!=rear){
            int tmpX = queueX[font];
            int tmpY = queueY[font];
            font++;
            for (int i = 0; i < 4; i++) {
                int xx = tmpX+dx[i];
                int yy = tmpY+dy[i];
                if (checkBao(xx,yy)&&visited[xx][yy]==0&&a[xx][yy] !=-1 ){
                    visited[xx][yy] = visited[tmpX][tmpY]+1;
                    if (xx==x2&&yy==y2){
                        return;
                    }
                    queueX[rear]=xx;
                    queueY[rear] =yy;
                    rear++;
                }
            }
        }


    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner  =new Scanner(System.in);
        T =scanner.nextInt();
        for (int tc = 1; tc <= T ; tc++) {
            n = scanner.nextInt();
            resetVisited();
            res=0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j]= scanner.nextInt();
                    if (a[i][j]==1){
                        x1=i;y1=j;
                    }
                    if (a[i][j] ==2){
                        x2=i;y2=j;
                    }
                }
            }
            // xu ly
            bfs(x1,y1);
            System.out.println(visited[x2][y2]);
        }
    }
}
