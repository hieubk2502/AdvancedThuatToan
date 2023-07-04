package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortDominoVer2 {
    static int T,res,value1, value2;
    static int[][] a = new int[7][8];
    static boolean[][] maTranDomino = new boolean[7][7];
    static boolean[][] visited = new boolean[7][8];
    static int[] dx =  {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void resetMatranDomino(){
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                maTranDomino[i][j] =false;
            }
        }
    }
    public static void resetVisited(){
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 8; j++) {
               visited[i][j] =false;
            }
        }
    }
    public static boolean checkBien(int x, int y){
        return x>=0&&x<7&&y>=0&&y<8;
    }
    public static void solve(int x, int y){
        System.out.println(x+" "+y);
        if(x==7){
            res++;
            return;
        }
        if (!visited[x][y]){
            value1 = a[x][y];
            for (int i = 0; i < 2; i++) {
                int xx= x+dx[i];
                int yy = y+dy[i];
                if (checkBien(xx,yy)&&!visited[xx][yy]){
                    value2 = a[xx][yy];
                    if (!maTranDomino[value1][value2]){
                        if (value1<value2){
                            maTranDomino[value1][value2]=true;
                            visited[xx][yy]=visited[x][y]=true;
                            if (y<7){
                                solve(x,y+1);
                            }
                            else {
                                solve(x+1,0);
                            }
                            maTranDomino[value1][value2]=false;
                            visited[xx][yy]=visited[x][y]=false;
                        } else {
                            maTranDomino[value2][value1]=true;
                            visited[xx][yy]=visited[x][y]=true;
                            if (y<7){
                                solve(x,y+1);
                            }
                            else {
                                solve(x+1,0);
                            }
                            maTranDomino[value2][value1]=false;
                            visited[xx][yy]=visited[x][y]=false;
                        }

                    }
                }
            }
        }else {
            if (y<7){
                solve(x,y+1);
            }
            else {
                solve(x+1,0);
            }
        }

    }
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T =scanner.nextInt();
        for (int tc = 1; tc <= T ; tc++) {
            // nhap input
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    a[i][j]= scanner.nextInt();
                }
            }
            resetMatranDomino();
            resetVisited();
            // xu ly
            res=0;
            solve(0,0);
            System.out.println(res);
        }
    }
}
