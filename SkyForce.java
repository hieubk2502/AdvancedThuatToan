 package week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SkyForce {
	
	static int T,n, res;
	static int[] dx ={-1,-1,-1};
	static int[] dy ={1,0,-1};
	static int[][] a =new int[100][5];
	static int[] mangDem1 =new int[100];
	static int[] mangDem2 =new int[100];
	
	public static boolean checkBien(int x,int y){
		return x>=0&&x<n&&y>=0&&y<=4;
	}
	public static void inArr(){
		System.out.println();
		for (int i = 0; i <n; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void bt(int x,int y,int sum,int vungBom){
		if (x==0) {
			if (sum>res) {
				res=sum;
			}
			return;
		}
		if (sum<0) {
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int xx = x+dx[i];
			int yy = y+dy[i];
			if (checkBien(xx, yy)) {
				if (a[xx][yy]==0) {
					bt(xx, yy,  sum,vungBom);
				}else if (a[xx][yy]==1) {
					bt(xx, yy,  sum+1,vungBom);
				}else{
					if (xx>=vungBom&&xx<=vungBom+4) {
						bt(xx, yy,  sum,vungBom);
					}else{
						bt(xx, yy,  sum-1,vungBom);
					}
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner scanner = new Scanner(System.in);
		T =scanner.nextInt();
		for (int tc = 1; tc <= T ; tc++) {
			n  = scanner.nextInt();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < 5; j++) {
					a[i][j]=scanner.nextInt();
				}
			}
			res=-1;
//			inArr();
			for (int i = 0; i <= n-5; i++) {
				bt(n, 2, 0,i);
			}
			System.out.println("#"+tc+" "+res);
			
		}
	}

}
