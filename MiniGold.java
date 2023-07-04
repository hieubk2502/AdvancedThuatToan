package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MiniGold {
    static int T,n,soMoVang,font,rear,res,resCur;
    static int[][] arrayVang = new int[2][6];
    static int[][] a = new int[21][21];
    static int[][] visited =new int[21][21];
    static int[] queueX =new int[100000];
    static int[] queueY = new int[100000];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
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
            for (int i = 0; i < 4; i++) {
                int xx = tempX+dx[i];
                int yy = tempY+dy[i];
                if (checkBien(xx,yy)&&a[xx][yy]!=0&&visited[xx][yy]==-1){
                    visited[xx][yy] =visited[tempX][tempY]+1;
                    queueX[rear%10000]=xx;
                    queueY[rear%10000]=yy;
                    rear++;
                }
            }
        }
    }
    public static void timDuongDiDaiNhat(){
        for (int k = 1; k <= n; k++) {
            for (int l = 1; l <=n ; l++) {
                if (a[k][l]==2&&visited[k][l]>resCur){
                    resCur=visited[k][l];
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = scanner.nextInt();
            soMoVang =scanner.nextInt();
            for (int i = 0; i < soMoVang; i++) {
                arrayVang[0][i] = scanner.nextInt();
                arrayVang[1][i] = scanner.nextInt();
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <=n; j++) {
                    a[i][j] = scanner.nextInt();
                }
            }
            // gan mo vang
            for (int i = 0; i < soMoVang; i++) {
               a[arrayVang[0][i]][arrayVang[1][i]]=2;
            }
            res=999;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <=n; j++) {
                    if (a[i][j]==1){
                        resetVisited();
                        bfs(i,j);
                        // xu ly tim quang duong lon nhat phai di
                        resCur = 0;
                        timDuongDiDaiNhat();
                        if (resCur<res&&resCur!=0){
                            res=resCur;
                        }
                    }
                }
            }
            System.out.println("#"+tc+" "+res);
        }
    }
}
