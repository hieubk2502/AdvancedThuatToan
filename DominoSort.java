package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DominoSort {
    static int T,res;
    static int[][] a = new int[7][8];
    static int[] dxDomino = {0,0,0,0,0,0,0,1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,4,4,4,5,5,6};
    static int[] dyDomino = {0,1,2,3,4,5,6,1,2,3,4,5,6,2,3,4,5,6,3,4,5,6,4,5,6,5,6,6};
    static int[] dx =  {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static boolean[][] visited = new boolean[7][8];

    public static void resetVisited(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                visited[i][j] =false;
            }
        }
    }
    public static boolean checkBien(int x,int y){
        return x>=0&&x<7&&y>=0&&y<8;
    }

    public static void solve(int index, int count){
        // dieu kien dung
        if (count==28){
            res++;
            return;
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                if (a[i][j]==dxDomino[index]&&!visited[i][j]){
                    int indexK = (dyDomino[index]==dxDomino[index])?2:4;
                    for (int k = 0; k < indexK; k++) {
                        int xx = i+dx[k];
                        int yy = j+dy[k];
                        if (checkBien(xx,yy)&&!visited[xx][yy]&&a[xx][yy]==dyDomino[index]){
                            visited[i][j]=true;
                            visited[xx][yy]=true;
                            solve(index+1,count+1);
                            visited[i][j]=false;
                            visited[xx][yy]=false;
                        }
                    }
                }

            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner  = new Scanner(System.in);
        T  =scanner.nextInt();
        for (int tc = 0; tc < T; tc++) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    a[i][j] = scanner.nextInt();
                }
            }
            resetVisited();
            res=0;
            solve(0,0);
            System.out.println(res);
        }
    }
}
