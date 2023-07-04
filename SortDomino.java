package onTapVong2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortDomino {
    static int res = 0;
    static int[][] matrix = new int[7][8];
    static boolean[][] checkMatrix= new boolean[7][8];
    static boolean[][] checkDomain = new boolean[7][7];

    static int[] dx ={1,0};
    static int[] dy = {0,1};
    // ham reset
    public static void resetCheckDomain(){
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                checkDomain[i][j]= true;
            }
        }
    }
    public static void resetMatrix(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                checkMatrix[i][j] = false;
            }
        }
    }
    // set cho gia tri 
    public static void setPosDomain(int x, int y, boolean value){
        checkDomain[x][y]=value;
    }
    public static void setPosMatrix(int x,int y, boolean value){
        checkMatrix[x][y] = value;
    }
    // check ma tran thoa man
    public static boolean checkMatrixThoaMan(int ia, int ja){
        if(ia==6&&ja==7){
            return true;
        }
        return false ;
    }
    public static boolean checkBien(int x,int y){
        if (x>=0&&x<7&&y>=0&&y<8){
            return true;
        }
        return false;
    }
    // xu ly
    public static void solve(int x,int y){
        if (checkMatrixThoaMan(x,y)){
            res++;
            return;
        } else if (!checkMatrix[x][y]) {
            for (int i = 0; i < 2; i++) {
                int x1 =x+dx[i];
                int y1 =y+dy[i];
                if (checkBien(x1,y1)&&!checkMatrix[x1][y1]){
                    int a = matrix[x][y];
                    int b = matrix[x][y];
                    checkMatrix[x1][y1]=true;
                    checkDomain[a][b]=true;
                    if (y<7){
                        solve(x,y+1);
                    }else {
                        solve(x+1,0);
                    }
                    checkDomain[a][b]=false;
                    checkMatrix[x1][y1]=false;
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\onTapVong2\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        int T =scanner.nextInt();
        for (int tc = 1; tc <=T ; tc++) {
            res=0;
            // nhap thong tin
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    matrix[i][j] = scanner.nextInt(); 
                }
            }
            solve(0,0);
            System.out.println(res);
        }

    }
}
