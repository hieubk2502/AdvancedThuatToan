package onTapVong2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MaTranHopLe {
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    public static void ff(char[][] a, int x, int y){
        a[x][y]='*';
        for (int i = 0; i < 4; i++) {
            int x1 = x+ dx[i];
            int y1 = y+ dy[i];
            if (x1>=0&&x1<a.length&&y1>=0&&y1<a[0].length&&a[x1][y1]=='.'){
                ff(a,x1,y1);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\onTapVong2\\input.txt");
        Scanner scanner = new Scanner(fileInputStream);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        char[][] a = new char[row][col];
        for (int i = 0; i < row; i++) {
            String line = scanner.next();
            for (int j = 0; j < col; j++) {
                a[i][j] = line.charAt(j);
            }
        }

        // check bien
        // check cot dau cuoi
        int count =0;
        for (int i = 0; i <row ; i++) {
            if (a[i][0]=='.'){
                count++;
            }
            if (a[i][col-1]=='.') {
                count++;
            }
        }
        // check hang dau cuoi
        for (int i = 0; i <col ; i++) {
            if (a[0][i]=='.'){
                count++;
            }
            if (a[row-1][i]=='.'){
               count++;
            }
        }
        if (count>2){
            // tiep tuc
        }


        // khi chi co 2 loi vao ra
        boolean is=false;
        for (int i = 0; i <row ; i++) {
            if (a[i][0]=='.'){
                ff(a,i,0);
                is=true;
                break;
            }
            if (a[i][col-1]=='.') {
                ff(a,i,col-1);
                is=true;
                break;
            }
        }
        if (!is){
            for (int i = 0; i <col ; i++) {
                if (a[0][i]=='.'){
                    ff(a,0,i);
                    break;
                }
                if (a[row-1][i]=='.'){
                    ff(a,row-1,i);
                    break;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
}
