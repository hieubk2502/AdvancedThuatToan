package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EasySudoku {
    static int[][] board = new int[9][9];
    static boolean[] visited = new boolean[10];
    static int T;
    static int res;
    public static void resetVisited(){
        for (int i = 0; i < 9; i++) {
            visited[i] =false;
        }
    }
    public static boolean checkHang(int hang,int value){
        resetVisited();
        for (int i = 0; i < 9; i++) {
            if (board[hang][i]==value){
                return false;
            }
        }
        return true;
    }
    public static boolean checkCot(int cot, int value){
        resetVisited();
        for (int i = 0; i < 9; i++) {
            if (board[i][cot]==value) {
                return false;
            }
        }
        return true;
    }
    public static boolean check3x3(int x, int y,int value){
        resetVisited();
        int startX = x/3;
        int startY = y/3;
        for (int i = startX*3; i <startX*3+3 ; i++) {
            for (int j = startY*3; j < startY*3+3; j++) {
                if (board[i][j]==value){
                    return false;
                }
            }
        }
        return true;
    }
    public static void bt(int x, int y){
        if (x>8){
            res=1;
            return;
        }
        if (board[x][y]!=0) {
            if (y < 8) {
                bt(x, y + 1);
            } else {
                bt(x + 1, 0);
            }
        }else {
            int pre =board[x][y];
            for (int i = 1; i <= 9; i++) {
                if (checkHang(x,i)&&checkCot(y,i)&&check3x3(x,y,i)){
                    board[x][y]=i;
                    if (y < 8) {
                        bt(x, y + 1);
                    } else {
                        bt(x + 1, 0);
                    }
                    if (res==1){
                        return;
                    }
                    board[x][y]=pre;
                }
            }
        }
    }

    public static void backtrack(int x, int y){
        // dieu kien dung
        if (x>8){
            res=1;
            return;
        }
        if (board[x][y] !=0){
            if (y<8){
                backtrack(x,y+1);
            }
            else {
                backtrack(x+1,0);
            }
        }else{
            int pre = board[x][y];
            for(int i = 1; i <=9 ; i++) {
               if (checkHang(x,i)&&checkCot(y,i)&&check3x3(x,y,i)){
                   board[x][y] =i;
                   if (y<8){
                       backtrack(x,y+1);
                   }
                   else {
                       backtrack(x+1,0);
                   }
                   if (res==1){
                       return;
                   }
                   board[x][y]=pre;
               }
            }
        }
    }

    public static void inRa(){
        System.out.println();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));;
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            resetVisited();
            res=0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }
            inRa();
            // xu ly
//            bt(0,0);
            backtrack(0,0);
            inRa();

        }
    }
}
