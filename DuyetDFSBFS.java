package onTapVong2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DuyetDFSBFS {
    static int col;
    static int row;
    static char[][] a =new char[1001][1001];
    static boolean[][] visited =new boolean[1001][1001];
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,1,-1,0};

    public static void resetvisited(){
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                visited[i][j] =false;
            }
        }
    }

    public static void dfs(int i,int j){
        System.out.println(i+" "+j);
        visited[i][j] =true;
        // duyet cac dinh ke
        for (int k = 0; k < 4; k++) {
            int i1 = i+dx[i];
            int j1 = j+dy[i];
            if (i1>=0&&i1<row&&j1>=0&&j1<col){
                dfs(i1,j1);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\onTapVong2\\input.txt"));

    }
}
