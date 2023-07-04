package onTapVong2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class DFSstack {
    static boolean[] visited = new boolean[1000];
    static Stack<Integer>[] stack = new Stack[10];
    public static void dfs(int u){
        visited[u] = true;
        for (Integer  x:stack[u]) {
            if (!visited[x]){
                System.out.print(" "+x);
                dfs(x);
            }
        }
    }
    public static void connectedCompomement(int n){
        int ans =0;
        // reset 1 lan
        for (int i = 0; i < 1000; i++) {
            visited[i] =false;
        }
        for (int i = 1; i <= n ; i++) {
            if (!visited[i]){
                ++ans;
                System.out.println("cac dinh thuoc thanh phan lien thong: "+i);
                dfs(i);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\onTapVong2\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 1; i++) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            int soCap = scanner.nextInt();
            for (int j = 0; j < soCap; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                System.out.println(x+"_"+y);
//                stack[x].push(y);
                stack[y].push(x);
            }
            connectedCompomement(n);


        }

    }
}
