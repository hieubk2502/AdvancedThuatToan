package week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarioClimb {
    static int T,n,m,res,font,rear,x1,y1,x2,y2;
    static int[][] a = new int[1000][1000];
    static int[][] visited =new int[1000][1000];
    static int[] queueX =new int[100000];
    static int[] queueY = new int[100000];
    static int[] dx = {0,0,1,-1};
    static int[] dy = {-1,1,0,0};
    public static boolean checkBien(int x,int y){
        return x>=0&&x<n&&y>=0&&y<m;
    }
    public static void resetVisited(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j]=0;
            }
        }
    }
    public static void inVisited(){
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(visited[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void bfs(int x, int y){
        font=rear=0;
        queueX[rear%100000]=x;
        queueY[rear%100000] =y;
        rear++;
        visited[x][y]=1;
        while(font!=rear){
            int tempX = queueX[font%100000];
            int tempY = queueY[font%100000];
            font++;
//            inVisited();
            // sang ngang
            xuly1(tempX,tempY);
            // len xuong
            xuly2(tempX,tempY);
        }
    }
    public static void xuly1(int tempX, int tempY){
    	for (int i = 0; i < 2; i++) {
            int xx = tempX+dx[i];
            int yy = tempY+dy[i];
            if (checkBien(xx,yy)&&a[xx][yy]!=0&&(visited[xx][yy]==0||visited[xx][yy]>visited[tempX][tempY])){
                visited[xx][yy] =visited[tempX][tempY];
                queueX[rear%100000]=xx;
                queueY[rear%100000]=yy;
                rear++;
            }
        }
    	
    }
    public static void xuly2(int tempX,int tempY){
    	for (int i = 2; i < 4; i++) {
            for (int j = 1;; j++) {
                int xx = tempX+dx[i]*j;
                int yy = tempY+dy[i]*j;
                if (checkBien(xx,yy)&&a[xx][yy]!=0&&(visited[xx][yy]==0||visited[xx][yy]>visited[tempX][tempY])){
                    if (visited[xx][yy]!=0) {
                    	int maxValue =Math.max(visited[tempX][tempY], Math.abs(xx - tempX));
                        visited[xx][yy] =Math.min(visited[xx][yy], maxValue);
                        queueX[rear%100000]=xx;
                        queueY[rear%100000]=yy;
                        rear++;
                        break;
					}else{
						visited[xx][yy]=Math.max(visited[tempX][tempY], Math.abs(xx-tempX));
                        queueX[rear%100000]=xx;
                        queueY[rear%100000]=yy;
                        rear++;
                        break;
					}
                	
                } else if (!checkBien(xx,yy)) {
                    break;
                }
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/input.txt"));
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
//        double timeCur = System.currentTimeMillis();
        for (int tc = 1; tc <= T; tc++) {
            n = scanner.nextInt();
            m = scanner.nextInt();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <m; j++) {
                    a[i][j] = scanner.nextInt();
                    visited[i][j]=0;
                    if (a[i][j]==2){
                        x1=i;y1=j;
                    }
                    if (a[i][j]==3){
                        x2=i;y2=j;
                    }
                }
            }
            bfs(x1,y1);
//            inVisited();
            res=visited[x2][y2];
            System.out.println("#"+tc+" "+res);
        }
//        double timeEnd = System.currentTimeMillis();
//        System.out.println("times: "+(timeEnd-timeCur)+"ms");
    }
}

Sample Input
3
5 8
1 1 1 1 0 0 0 0
0 0 0 3 0 1 1 1
1 1 1 0 0 1 0 0 
0 0 0 0 0 0 1 0
2 1 1 1 1 1 1 1
5 6
0 1 1 1 0 0
3 1 0 1 1 0
0 0 0 0 1 1
0 0 0 0 0 1
2 1 1 1 1 1
9 13
0 1 1 1 1 1 1 1 1 1 1 1 1 
1 1 0 0 0 0 0 0 0 0 0 1 1 
0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 
1 1 0 0 0 0 0 0 0 0 0 1 3 
0 0 0 0 0 0 0 0 0 0 0 0 0 
1 1 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0
2 1 1 1 1 1 1 1 1 1 1 1 1
Sample output
Case #1
2
Case #2
1
Case #3
3



/////////////hugogiaohang
package week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HugoGiaoHang {
	static int T, xFrom,yFrom,xTo,yTo,location,res;
	static int[] dx =new int[1000];
	static int[] dy = new int[1000];
	static boolean[] visited = new boolean[1000];
	
	public static int kc(int x1,int y1, int x2,int y2){
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
	public static void bt(int x,int y, int sum,int count){
//		System.out.println(x+" "+y+" "+sum+" "+count);
		if (count==location) {
			int kq =sum + kc(x, y, xTo, yTo);
			if (kq<res) {
				res=kq;
			}
			return;
		}
		if (sum + kc(x, y, xTo, yTo)>res) {
			return;
		}
		// xu ly
		for (int i = 1; i <= location; i++) {
			if (!visited[i]) {
				visited[i]=true;
				bt(dx[i], dy[i], sum+kc(x, y, dx[i], dy[i]), count+1);
				visited[i]=false;
			}
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner scanner = new Scanner(System.in);
		T =scanner.nextInt();
		for (int tc = 1; tc <= T ; tc++) {
			xFrom =scanner.nextInt();
			yFrom= scanner.nextInt();
			xTo = scanner.nextInt();
			yTo = scanner.nextInt();
			location =scanner.nextInt();
			for (int i = 1; i <= location; i++) {
				dx[i]=scanner.nextInt();
				dy[i] = scanner.nextInt();
				visited[i] = false;
			}
			res=999999;
			bt(xFrom,yFrom,0,0);
			System.out.println("Case #"+tc+" "+res);
		}
	}
}
//
//10
//57 61 50 61
//5 86 53 4 104 27 3 55 34 69 0
//96 47 60 28
//5 0 6 43 84 84 35 44 57 95 50
//48 32 67 42
//5 53 51 92 1 48 19 8 3 82 37
//97 28 66 41
//5 93 72 9 79 46 31 12 66 54 11
//38 62 93 86
//5 87 83 40 83 83 26 98 11 74 103
//23 42 71 16
//5 12 76 47 74 24 5 88 82 45 85
//96 85 14 6
//5 86 91 104 60 72 35 59 22 58 39
//99 49 68 1
//5 48 80 96 101 56 88 75 56 25 81
//51 14 75 51
//5 29 62 103 15 75 84 67 94 96 57
//87 39 57 77
//5 105 85 31 40 1 88 83 89 29 18

#include<iostream>
using namespace std;

int dx[4]={0,-1,0,1};
int dy[4]={-1,0,1,0};

int map[55][55];
int T,tc,n,m;
int x_start, y_start;
int x_gold, y_gold;
int Visit[55][55];
int res;
void reset(){
	for(int i=0; i<n; i++){
		for(int j=0; j<m; j++){
			Visit[i][j]=0;
		}
	}
}

bool check(int x, int y){
	return (x>=0 && x<n && y>=0 && y<m);
}


void BT(int step, int x, int y){
	Visit[x][y]=1;
	if(check(x,y-1) && Visit[x][y-1]==0 && map[x][y-1]!=0){
		BT(step,x,y-1);
	}
	if(check(x,y+1) && Visit[x][y+1]==0 && map[x][y+1]!=0){
		BT(step,x,y+1);
	}
	for(int i=1; i<=step; i++){
		if(check(x-i,y) && Visit[x-i][y]==0 && map[x-i][y]!=0){
			BT(step,x-i,y);
		}
	}
	for(int i=1; i<=step; i++){
		if(check(x+i,y) && Visit[x+i][y]==0 && map[x+i][y]!=0){
			BT(step,x+i,y);
		}
	}
}


int main(){
	freopen("input.txt", "r", stdin);
	cin >> T;
	for(tc=1;tc<=T;tc++){
		cin >> n >> m;
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
					cin >> map[i][j];
					if(map[i][j]==2){
						x_start=i;
						y_start=j;
					}
					if(map[i][j]==3){
						x_gold=i;
						y_gold=j;
					}
			}
		}
		res=0;
		int l=0;
		int r=n;
		int mid=0;
		while(l<=r){
			mid=(l+r)/2;
			reset();
			BT(mid,x_start,y_start);
			if(Visit[x_gold][y_gold]!=0){
				res=mid;
				r=mid-1;
			}else{
				l=mid+1;
			}
		}
		cout << res << endl;

	}


	return 0;
}

