package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EasyModifiedSoduku {
    static int[][] board = new int[8][8];
    static boolean[] visited = new boolean[10];
    static int[] count =new int[10];
    static int T;
    static int res;
    public static void resetVisited(){
        for (int i = 0; i < 9; i++) {
            visited[i] =false;
        }
    }
    public static void resetCount(){
        for (int i = 0; i < 10; i++) {
            count[i]=0;
        }
    }
    // check dieu kien dau vao
    public static boolean checkRow(int hang){
        resetVisited();
        for (int i = 0; i < 8; i++) {
            if (board[hang][i] != 0 && !visited[i]){
                visited[i]=true;
            } else if (visited[i]) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkCol(int cot){
        resetVisited();
        for (int i = 0; i < 8; i++) {
            if (board[i][cot] != 0 && !visited[i]){
                visited[i]=true;
            } else if (visited[i]) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkBlock3x3(int x, int y){
        resetCount();
        for (int i = x; i <x+4 ; i++) {
            for (int j = y; j < y+4; j++) {
                if (board[i][j]!=0){
                    count[board[i][j]]++;
                    if (count[board[i][j]]>2){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static boolean check(){
        for (int i = 0; i < 8; i++) {
            if (!checkRow(i)||!checkCol(i)){
                return false;
            }
        }
        for (int i = 0; i < 8; i+=4) {
            for (int j = 0; j < 8; j+=4) {
                if (!checkBlock3x3(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkHang(int hang,int value){
        resetCount();
        for (int i = 0; i < 8; i++) {
            if (board[hang][i]==value){
                count[value]++;
                if (count[value]>1){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkCot(int cot, int value){
        resetCount();
        for (int i = 0; i < 8; i++) {
            if (board[i][cot]==value) {
                count[value]++;
                if (count[value]>1){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean check3x3(int x, int y,int value){
        int startX = x/4;
        int startY = y/4;
        for (int i = startX*4; i <startX*3+4 ; i++) {
            for (int j = startY*4; j < startY*3+4; j++) {
                if (board[i][j]==value) {
                    count[value]++;
                    if (count[value]>1){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void bt(int x, int y){
        if (x>7){
            res=1;
            return;
        }
        if (board[x][y]!=0) {
            if (y < 7) {
                bt(x, y + 1);
            } else {
                bt(x + 1, 0);
            }
        }else {
            int pre =board[x][y];
            for (int i = 1; i <= 8; i++) {
                if (checkHang(x,i)&&checkCot(y,i)&&check3x3(x,y,i)){
                    board[x][y]=i;
                    if (y < 7) {
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

    public static void inRa(){
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
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
            resetCount();
            res=0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    board[i][j] = scanner.nextInt();
                }
            }
            if (!check()){
                System.out.println("-1");
                continue;
            }
            inRa();
            // xu ly
            bt(0,0);
            inRa();

        }
    }
}
