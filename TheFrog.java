 package bai7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TheFrog {
	static int T;
	static int n;
	static int countXa;
	static int countGan;
	static int[] dx = new int[200];
	static int[] dy = new int[200];
	static int[] dr = new int[200];
	
	static boolean[] visited =new boolean[200];
	static int[] distance = new int[200];// khoang cach tu diem xet toi dinh i
	static int inf =Integer.MAX_VALUE;
	
	public static void resetVisited(){
		for (int i = 0; i < 200; i++) {
			visited[i] =false;
		}
	}
	
	public static int khoangCach(int x1,int y1, int r1, int x2, int y2, int r2){
		double b =  (double) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		double distinct = Math.abs(b-r1-r2);
		int ret =100000;
		if (distinct <= 40&&distinct>0) ret = 1;
		else {
			if (distinct > 40&&distinct <= 90) ret = 1000;
		}
		return ret;
		
	}
	public static void main(String[] args) throws FileNotFoundException{
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner scanner = new Scanner(System.in);
		T =scanner.nextInt();
		for (int tc = 1; tc <= T ; tc++) {
			countGan =0;
			countXa=0;
			resetVisited();
			// nhap
			n =scanner.nextInt();
			for (int i = 0; i < n; i++) {
				dx[i] = scanner.nextInt();
				dy[i] = scanner.nextInt();
				dr[i] =scanner.nextInt();
			}
			// khoi tao
			int start =0;
			int finish =n-1;
			for (int i = 0; i < n; i++) {
				distance[i] = inf;
			}
			int count =0;
			distance[start] =0;
			while(count<n){
				// buoc 2 chon dinh de xet
				// va co khoang cach min
				int dangXet =0;
				int minDist = inf;
				for (int i = 0; i < n; i++) {
					if (!visited[i]&&distance[i]<minDist) {
						minDist = distance[i];
						dangXet =i;
					}
				}
				if (dangXet==finish) {
					break;
				}
				// buoc 3" tu dinh dang xet 
				// update khoang cach
				for (int i = 0; i < n; i++) {
					if (!visited[i]) {
						int newDistance = distance[dangXet]+khoangCach(dx[dangXet], dy[dangXet],dr[dangXet], dx[i],dy[i],dr[i]);
						if (newDistance<distance[i]) {
							distance[i]=newDistance;
						}
					}
				}
				visited[dangXet]=true;
				count++;
			}
			int res = distance[n - 1];
//			System.out.println(res);
			if (res>=100000) {
				System.out.println(-1);
			}else{
				countGan =res%1000;
				countXa =res/1000;
				System.out.println(countXa+" "+countGan);
			}
		}	
	}
}
