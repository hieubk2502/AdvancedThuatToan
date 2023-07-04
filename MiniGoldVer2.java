package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MiniGoldVer2 {
    static int T, n,soMoVang,res,resCur;
    static int[][] a = new int[100][100];
    static int[][] visited = new int[100][100];
    static int[] mangVangX  = new int[5];
    static int[] mangVangY  = new int[5];

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    // queue
    static int font,rear;
    static int[] queueX = new int[10000];
    static int[] queueY = new int[10000];

    public static boolean checkBien(int x,int y){
        return x>=1&&x<=n&&y>=1&&y<=n;
    }
    public static void resetVisited(){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=n; j++) {
                visited[i][j]=-1;
            }
        }
    }

    public static void bfs(int x, int y) {
        font = rear = 0;
        queueX[rear % 10000] = x;
        queueY[rear % 10000] = y;
        rear++;
        visited[x][y] = 0;
        while (font != rear) {
            int tempX = queueX[font % 10000];
            int tempY = queueY[font % 10000];
            font++;
            for (int i = 0; i < 4; i++) {
                int xx = tempX + dx[i];
                int yy = tempY + dy[i];
                if (checkBien(xx, yy) && visited[xx][yy] == -1 && a[xx][yy] == 1) {
                    visited[xx][yy] = visited[tempX][tempY] + 1;
                    queueX[rear % 10000] = xx;
                    queueY[rear % 10000] = yy;
                    rear++;
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T  = scanner.nextInt();
        for (int tc = 1; tc <= T ; tc++) {
            n = scanner.nextInt();
            resetVisited();
            soMoVang = scanner.nextInt();
            for (int i = 0; i < soMoVang; i++) {
                mangVangX[i] = scanner.nextInt();
                mangVangY[i] = scanner.nextInt();
            }
            for (int i = 1; i <= n ; i++) {
                for (int j = 1; j <=n ; j++) {
                    a[i][j]=scanner.nextInt();
                }
            }
            res =999;
            for (int i = 1; i <=n ; i++) {
                for (int j = 1; j <=n ; j++) {
                    resetVisited();
                    if (a[i][j]==1){
                        bfs(i,j);
                        // tinh res
                        resCur = 0;
                        boolean is=true;
                        for (int k = 0; k < soMoVang; k++) {
                            if (visited[mangVangX[k]][mangVangY[k]]!=0){
                                if (visited[mangVangX[k]][mangVangY[k]]>resCur){
                                    resCur =visited[mangVangX[k]][mangVangY[k]];
                                }
                            }else{
                                is=false;
                                break;
                            }
                        }
                        if (!is){
                            continue;
                        }
                        if (resCur <res){
                            res =resCur;
                        }
                    }
                }
            }
            System.out.println("#"+tc+" "+res);
        }
    }
}
