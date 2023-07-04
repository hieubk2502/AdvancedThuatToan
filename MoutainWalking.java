package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MoutainWalking {
    static int T,n,lowMap,highMap,high,low;
    static int[][] a = new int[51][51];
    static boolean[][] visited = new boolean[51][51];
    static int font,rear;
    static int[] queueX =new int[10000];
    static int[] queueY = new int[10000];
    static int[] dx ={-1,0,1,0};
    static int[] dy ={0,1,0,-1};

    public static boolean checkBien(int x, int y){
        return x>=0&&x<n&&y>=0&&y<n;
    }

    public static boolean bfs(int min, int max){
        if (a[0][0]<min||a[0][0]>max)return false;
        font=rear=0;
        queueX[rear%10000]=0;
        queueY[rear%10000]=0;
        rear++;
        while (rear!=font){
            int tempX = queueX[font%10000];
            int tempY = queueY[font%10000];
            font++;
            for (int i = 0; i < 4; i++) {
                int xx = tempX+dx[i];
                int yy = tempY+dy[i];
                if (checkBien(xx,yy)&&a[xx][yy]>=min&&a[xx][yy]<=max&&!visited[xx][yy]){
                    visited[xx][yy]=true;
                    if(xx==n-1&&yy==n-1)return true;
                    queueX[rear%10000]=xx;
                    queueY[rear%10000]=yy;
                    rear++;
                }
            }
        }
        return false;
    }

    public static void resetVisited(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j]=false;
            }
        }
    }
    public static boolean check_kc(int value){
        for (int i = 0; i <= highMap-value ; i++) {
            resetVisited();
            if (bfs(i,i+value))
            {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T =scanner.nextInt();
        for (int tc = 1; tc <= T ; tc++) {
            n =scanner.nextInt();
            lowMap=999;
            highMap =0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j]=scanner.nextInt();
                    lowMap = Math.min(lowMap,a[i][j]);
                    highMap= Math.max(highMap,a[i][j]);
                }
            }
            //
            low=lowMap;
            high=highMap;
            while(low<high) {
                int mid = (low + high) / 2;
                if (check_kc(mid)) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            System.out.println(high);
        }
    }

}
