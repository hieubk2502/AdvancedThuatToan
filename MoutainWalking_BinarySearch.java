package week4;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MoutainWalking {
    static int T,n,lowMap,highMap,high,low,fastest,lowest;
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
        for (int i =lowMap; i <= highMap-value ; i++) {
            resetVisited();
            if (bfs(i,i+value))
            {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/input.txt"));
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
            low = a[n-1][n-1]-a[0][0];// thoi gian  nhanh nhat co the di
            high=Math.abs(highMap-lowMap);// tgian toi nhat co the
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
// input
5
5
1 1 3 6 8
1 2 2 5 5
4 4 0 3 3
8 0 2 3 4
4 3 0 2 1
5
99 85 38 22 55
89 28 33 3 65
99 20 14 67 90
36 27 28 77 31
50 45 12 9 14
2
92 83
19 91
5
61 49 32 34 28
100 65 0 10 89
34 99 40 86 4
10 97 49 21 30
95 33 79 51 65
2
17 60
94 27
Output
#1 2
#2 85
#3 9
#4 50
#5 43