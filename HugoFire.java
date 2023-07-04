package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HugoFire{
    static int T, n,m,xHugo,yHugo,nFire,nLake,res,xCur,yCur,nExit;
    static int[][] visited_Hugo = new int[17][17];
    static int[][] fire = new int[17][17];
    static int[][] lake = new int[17][17];
    static int[][] exit = new int[17][17];
    static int[][] diamond = new int[17][17];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    // queue
    static int font,rear;
    static int[] queueX = new int[10000];
    static int[] queueY = new int[10000];

    public static boolean checkBien(int x,int y){
        return x>=1&&x<=n&&y>=1&&y<=m;
    }
    public static void reset(){
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=m; j++) {
                visited_Hugo[i][j]=0;
                fire[i][j]=999;
                lake[i][j] =0;
                exit[i][j]=0;

            }
        }
    }
    public static void BFS_Fire(){
        while(font!=rear){
            int tempX = queueX[font%10000];
            int tempY = queueY[font%10000];
            font++;
            for (int i = 0; i < 4; i++) {
                int xx = tempX+dx[i];
                int yy = tempY +dy[i];
                if (checkBien(xx,yy)&&fire[xx][yy]==999&&lake[xx][yy]!=1){
                    fire[xx][yy] =fire[tempX][tempY]+1;
                    queueX[rear%10000] =xx;
                    queueY[rear%10000] =yy;
                    rear++;
                }
            }
        }
    }
    public static void BT(int curDiamond,int xStart,int yStart,boolean isExit,int timeHugo){
        if (isExit){
            res = Math.max(curDiamond,res);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int xx =xStart + dx[i];
            int yy =yStart + dy[i];
            if (checkBien(xx,yy)&&visited_Hugo[xx][yy]==0){
                // neu k phai loi ra
                if(exit[xx][yy]==0){
                    visited_Hugo[xx][yy]=1;
                    // neu khong phai la ho nuoc va lua chua lan toi
                    if (lake[xx][yy]==0&&timeHugo+1<fire[xx][yy]){
                        BT(curDiamond+diamond[xx][yy],xx,yy,false,timeHugo+1);
                    }
                    else if(lake[xx][yy]==1){
                        BT(curDiamond+diamond[xx][yy],xx,yy,false,timeHugo+2);
                    }
                    visited_Hugo[xx][yy]=0;
                }
                else{
                    visited_Hugo[xx][yy]=1;
                    // neu khong phai la ho nuoc va lua chua lan toi
                    if (lake[xx][yy]==0&&timeHugo+1<fire[xx][yy]){
                        BT(curDiamond+diamond[xx][yy],xx,yy,true,timeHugo+1);
                        BT(curDiamond+diamond[xx][yy],xx,yy,false,timeHugo+1);
                    }
                    else if(lake[xx][yy]==1){
                        BT(curDiamond+diamond[xx][yy],xx,yy,true,timeHugo+2);
                        BT(curDiamond+diamond[xx][yy],xx,yy,false,timeHugo+2);
                    }
                    visited_Hugo[xx][yy]=0;
                }
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T =scanner.nextInt();
        for (int tc = 1; tc <= T ; tc++) {

            n = scanner.nextInt();
            m = scanner.nextInt();
            font=rear=0;
            reset();
            xHugo = scanner.nextInt();
            yHugo = scanner.nextInt();
            visited_Hugo[xHugo][yHugo] = 1;
            nFire = scanner.nextInt();

            for (int i = 0; i < nFire; i++) {
                xCur= scanner.nextInt();
                yCur = scanner.nextInt();
                fire[xCur][yCur] = 0;
                // xu ly
                queueX[rear%10000] = xCur;
                queueY[rear%10000] = yCur;
                rear++;
            }

            nLake = scanner.nextInt();
            for (int i = 0; i < nLake; i++) {
                xCur = scanner.nextInt();
                yCur = scanner.nextInt();
                lake[xCur][yCur] = 1;
            }
            nExit = scanner.nextInt();
            for (int i = 0; i < nExit; i++) {
                xCur = scanner.nextInt();
                yCur = scanner.nextInt();
                exit[xCur][yCur] = 1;
            }

            // nhap diamond
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    diamond[i][j] = scanner.nextInt();
                }
            }
            // xu ly BFS fire
            BFS_Fire();
            res =0;
            BT(diamond[xHugo][yHugo],xHugo,yHugo,false,0);
            System.out.println("Case #"+tc);
            if (res==0){
                System.out.println(-1);
            }else{
                System.out.println(res);
            }

        }
    }
}
