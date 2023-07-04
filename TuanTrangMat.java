 package week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TuanTrangMat {
	static int T,n,tuyenDuong,diaDiemCanDiQua,valueX,valueY;
	static long res,resCur;
	static long[][] a = new long[1001][1001];
	static long[][] matrankc = new long[1001][1001];
	
	static int[] dxDiaDiemCanQua = new int[1001];
	static boolean[] visited2 = new boolean[1001];
	
	// danh cho tt dijkstra
	static long[] distance = new long[1001];
	static boolean[] visited = new boolean[1001];
	static long inf = Long.MAX_VALUE;
	
	public static void resetMaTran(){
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j]=0;
			}
		}
	}
	public static void resetVisited(){
		for (int i = 1; i <=n; i++) {
			visited[i]=false;
		}
	}
	
	public static void resetVisited2(){
		for (int i = 1; i <=n; i++) {
			visited2[i]=false;
		}
	}
	public static void inMaTranKc(){
		System.out.println();
		for (int i = 0; i <= diaDiemCanDiQua  ; i++) {
			for (int j = 0; j <= diaDiemCanDiQua; j++) {
				System.out.print(matrankc[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void inMaTranInput(){
		System.out.println();
		for (int i = 1; i <= n ; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static long dijkstra(int start, int stop){
		resetVisited();
		// khoi tao nao
		int x_Start = start;
		for (int i = 1; i <= n; i++) {
			distance[i]=inf;
		}
		int count=0;
		distance[x_Start]=0;
		while(count<n){
			// buoc 2 chon dinh de xet
			// va co khoang cach min nhat
			int dangXet = 0;
			long minDist = inf;
			for (int i = 1; i <= n; i++) {
				if (!visited[i]&&distance[i]<minDist) {
					minDist=distance[i];
					dangXet = i;
				}
			}
			// 
			if (dangXet==stop) {
				return distance[dangXet];
			}
			// buoc 3 tu dinh dang xet update khoang cach
			for (int i = 1; i <= n; i++) {
				if (!visited[i]&&a[dangXet][i]!=0) {
					long newDistance =  (distance[dangXet]+ a[dangXet][i]);
					if (newDistance<distance[i]) {
						distance[i] =newDistance;
					}
				}
			}
			visited[dangXet]=true;
			count++;
		}
		return 0;
	}
	public static void bt(int index,long sum,int count){
		// dieu kien dung
		if (count==diaDiemCanDiQua) {
			if (sum+matrankc[index][0]<res) {
				res=sum+matrankc[index][0];
			}
			return;
		}
		if (sum+matrankc[index][1]>res) {
			return;
		}
		// xu ly
		for (int i = 0; i <= diaDiemCanDiQua; i++) {
			if (!visited2[dxDiaDiemCanQua[i]]) {
				visited2[dxDiaDiemCanQua[i]]=true;
				bt(i, sum+matrankc[index][i], count+1);
				visited2[dxDiaDiemCanQua[i]]=false;
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner scanner = new Scanner(System.in);
		T =scanner.nextInt();
		for (int tc = 1; tc <= T ; tc++) {
			// input
			n =scanner.nextInt();
			tuyenDuong =scanner.nextInt();
			diaDiemCanDiQua =scanner.nextInt();
			resetMaTran();
			dxDiaDiemCanQua[0]=1;
			for (int i = 1; i <= diaDiemCanDiQua; i++) {
				dxDiaDiemCanQua[i]=scanner.nextInt();
			}
			
			for (int i = 1; i <= tuyenDuong ; i++) {
				valueX =scanner.nextInt();
				valueY = scanner.nextInt();
				a[valueX][valueY] =scanner.nextInt();
			}
			///// xu ly
			// tao ma tran khoang cach
//			inMaTranInput();
			for (int i = 0; i <=diaDiemCanDiQua; i++) {
				for (int j = 0; j <=diaDiemCanDiQua; j++) {
					if (i==j) {
						matrankc[i][j]=0;
					}else{
						matrankc[i][j]=dijkstra(dxDiaDiemCanQua[i],dxDiaDiemCanQua[j]);
					}
				}
			}
			resetVisited2();
//			inMaTranKc();
			res=999999999;
			visited2[dxDiaDiemCanQua[0]]=true;
			bt(0,0,0);
			System.out.println("Case #"+tc);
			System.out.println(res);	
			System.out.println();
		}
	}
}
