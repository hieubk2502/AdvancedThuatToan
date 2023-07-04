 package week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PipeNetwork {
	static int T, n,m,x1,y1,cover,res;
	static int[][] a = new int[51][51];
	static int[][] visited = new int [51][51];
	static int[][] top ={
		{0,0,0,0,0,0,0,0},
		{0,1,2,0,0,5,6,0},
		{0,1,2,0,0,5,6,0},
		{0,0,0,0,0,0,0,0},
		{0,1,2,0,0,5,6,0},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0},
		{0,1,2,0,0,5,6,0}
	};
	static int[][] bottom ={
		{0,0,0,0,0,0,0,0},
		{0,1,2,0,4,0,0,7},
		{0,1,2,0,4,0,0,7},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0},
		{0,1,2,0,4,0,0,7},
		{0,1,2,0,4,0,0,7},
		{0,0,0,0,0,0,0,0}
	};
	static int[][] left ={
		{0,0,0,0,0,0,0,0},
		{0,1,0,3,4,5,0,0},
		{0,0,0,0,0,0,0,0},
		{0,1,0,3,4,5,0,0},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0},
		{0,1,0,3,4,5,0,0},
		{0,1,0,3,4,5,0,0}
	};
	static int[][] right ={
		{0,0,0,0,0,0,0,0},
		{0,1,0,3,0,0,6,7},
		{0,0,0,0,0,0,0,0},
		{0,1,0,3,0,0,6,7},
		{0,1,0,3,0,0,6,7},
		{0,1,0,3,0,0,6,7},
		{0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0}
	};
	static int font,rear,count;
	static int[] queueX = new int[10000];
	static int[] queueY = new int[10000];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	
	public static boolean checkBien(int x,int y){
		return x>=0&&x<n&&y>=0&&y<m;
	}
	public static void bfs(int x,int y){
		font=rear=0;
		queueX[rear%10000]=x;
		queueY[rear%10000]=y;
		rear++;
		visited[x][y]=1;
		while(font!=rear){
			int tempX = queueX[font%10000];
			int tempY = queueY[font%10000];
			font++;
			if (visited[tempX][tempY]==cover) {
				return;
			}
//			inVisited();
			for (int i = 0; i < 4; i++) {
				int xx = tempX+dx[i];
				int yy = tempY+dy[i];
				if (checkBien(xx, yy)&&visited[xx][yy]==-1) {
					switch (i) {
					case 0:
						if (top[a[tempX][tempY]][a[xx][yy]]!=0) {
							visited[xx][yy]=visited[tempX][tempY]+1;
							queueX[rear%10000]=xx;
							queueY[rear%10000]=yy;
							rear++;
						}
						break;
					
					case 1:
						if (bottom[a[tempX][tempY]][a[xx][yy]]!=0) {
							visited[xx][yy]=visited[tempX][tempY]+1;
							queueX[rear%10000]=xx;
							queueY[rear%10000]=yy;
							rear++;
						}
						break;
					case 2:
						if (left[a[tempX][tempY]][a[xx][yy]]!=0) {
							visited[xx][yy]=visited[tempX][tempY]+1;
							queueX[rear%10000]=xx;
							queueY[rear%10000]=yy;
							rear++;
						}
						break;
					case 3:
						if (right[a[tempX][tempY]][a[xx][yy]]!=0) {
							visited[xx][yy]=visited[tempX][tempY]+1;
							queueX[rear%10000]=xx;
							queueY[rear%10000]=yy;
							rear++;
						}
						break;
					}
				}
			}
		}
	}
	public static void inVisited(){
		System.out.println();
		for (int i = 0; i <n ; i++) {
			for (int j = 0; j <m; j++) {
				System.out.print(visited[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void inArray(){
		System.out.println();
		for (int i = 0; i <n ; i++) {
			for (int j = 0; j <m; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner scanner = new Scanner(System.in);
		T =scanner.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n=scanner.nextInt();
			m = scanner.nextInt();
			x1 =scanner.nextInt();
			y1 = scanner.nextInt();
			cover = scanner.nextInt();
//			System.out.println(n+" "+m);
			for (int i = 0; i <n ; i++) {
				for (int j = 0; j <m; j++) {
					a[i][j] =scanner.nextInt();
					visited[i][j]=-1;
				}
			}
//			inArray();
			bfs(x1,y1);
			res=0;
			for (int i = 0; i <n ; i++) {
				for (int j = 0; j <m; j++) {
					if (visited[i][j]!=-1) {
						res++;
					}
				}
			}
			System.out.println("#"+tc+" "+res);
		}
	}
}3
5 6 2 1 3 
0 0 5 3 6 0 
0 0 2 0 2 0 
3 3 1 3 7 0
0 0 0 0 0 0
0 0 0 0 0 0
5 6 2 2 6
3 0 0 0 0 3
2 0 0 0 0 6
1 3 1 1 3 1
2 0 2 0 0 2
0 0 4 3 1 1
10 10 4 3 9
0 0 0 0 0 0 0 0 0 0
0 0 0 7 5 0 5 0 0 0
0 0 3 2 2 6 0 0 0 0
0 4 7 2 2 2 7 0 0 4
0 3 0 1 1 2 2 0 0 5
0 5 6 1 1 1 1 6 2 5
7 4 1 2 0 0 4 6 0 0
5 3 1 7 0 2 2 6 5 7
7 3 2 1 1 7 1 0 2 7
3 4 0 0 4 0 5 1 0 1
// dang bom nuoc
1
010
111
010
2
010
010
010
3
000
111
000
4
010
011
000
5
000
011
010
6
000
110
010
7
010
110
000


