package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Domino {
    static int[][] a =new int[8][8];
    static boolean[][] visited = new boolean[8][8];
    static int[] dx ={0,0,0,0,0,0,0,1,1,1,1,1,1,2,2,2,2,2,3,3,3,3,4,4,4,5,5,6};
    static int[] dy ={0,1,2,3,4,5,6,1,2,3,4,5,6,2,3,4,5,6,3,4,5,6,4,5,6,5,6,6};


    public static void bt(int x, int y, int dem){
        
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int tc = 0; tc < T; tc++) {
            // in put
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    a[i][j]= scanner.nextInt();
                }
            }
            // xu ly
            bt(0,0,0);

        }
    }
}
