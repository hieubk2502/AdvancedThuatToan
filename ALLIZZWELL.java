package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ALLIZZWELL {
    static int T,row, col;
    static char[][] arr = new char[100][100];
    static boolean[][] visited = new boolean[100][100];
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy ={-1,0,1,-1,1,-1,0,1};
    static String s1 ="ALLIZZWELL";
    static int leng= s1.length();
    static int check;
    static int count;

    public static void resetVisited(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                visited[i][j]=false;
            }
        }
    }

    public static boolean checkBien(int x, int y){
        return x>=0&&x<row&&y>=0&&y<col;
    }

    public static void bt(int x, int y, int count){
       // dieu kien dung
        if (count==leng){
            check=1;
            return;
        }
        visited[x][y]=true;
        for (int i = 0; i < 8; i++) {
            int xx = x+dx[i];
            int yy = y+dy[i];
            if (checkBien(xx,yy)&&!visited[xx][yy]&&arr[xx][yy]==s1.charAt(count)){
                bt(xx,yy,count+1);
                if (check==1)break;
            }
        }
        visited[x][y]=false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            row = scanner.nextInt();
            col = scanner.nextInt();
            for (int i = 0; i < row; i++) {
                String line  = scanner.next();
                for (int j = 0; j < col; j++) {
                    arr[i][j] = line.charAt(j);
                }
            }
            check=0;
            count=0;
            resetVisited();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                   if (arr[i][j]=='A'){
                       bt(i,j,1);
                   }
                }
            }
            if (check==1){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }
    }
}
