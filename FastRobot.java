 package week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FastRobot {
	static int n;
	static int T,row, col,x1,y1,x2,y2,font,rear;
	static int[][] arr = new int[201][201];
	static int[][] visited = new int[201][201];
	static int[][] direct = new int[201][201];
	static int[] queueX =new int[10000];
	static int[] queueY =new int[10000];
	static int[] dx ={-1,0,1,0};
	static int[] dy ={0,1,0,-1};
	
	/// reset
	public static void resetVisited(){
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				visited[i][j]=0;
			}
		}
	}
	public static void resetDirect(){
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				direct[i][j]=0;
			}
		}
	}
	// in ra
	public static void inVisited(){
		System.out.println("visited");
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				System.out.print(visited[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void inDirect(){
		System.out.println("direct");
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				System.out.print(direct[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void inArray(){
		System.out.println("array");
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static boolean checkBien(int x,int y){
		return x>=1&&x<=row&&y>=1&&y<=col;
		}
	// xu ly
	public static void bfs(int x1, int y1){
        font =rear = 0;
        queueX[rear%10000]=x1;
        queueY[rear%10000] =y1;
        direct[x1][y1]=-1;
        rear++;
        while (font!=rear){
            int tempX= queueX[font%10000];
            int tempY= queueY[font%10000];
            font++;
            for (int i = 0; i < 4; i++) {
                int xx = tempX + dx[i];
                int yy = tempY + dy[i];
                if (checkBien(xx,yy)&&arr[xx][yy]==0&&(visited[xx][yy]==0||visited[xx][yy]>visited[tempX][tempY])&&direct[xx][yy]!=-1){
                	if (direct[tempX][tempY]==-1||direct[tempX][tempY]==i){
                		direct[xx][yy]=i;
						visited[xx][yy]=visited[tempX][tempY];
                		queueX[rear%10000]=xx;
						queueY[rear%10000]=yy;
						rear++;
					}
                	else{
                		visited[xx][yy]=visited[tempX][tempY]+1;
                		direct[xx][yy]=i;
                		queueX[rear%10000]=xx;
						queueY[rear%10000]=yy;
						rear++;
                	}
                }
            }
        }
    }
	
	public static void main(String[] args) throws FileNotFoundException{
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner scanner = new Scanner(System.in);
		T =scanner.nextInt();
		for (int tc = 1; tc <=T; tc++) {
			col = scanner.nextInt();
			row = scanner.nextInt();
			y1 = scanner.nextInt();
			x1 =scanner.nextInt();
			y2 = scanner.nextInt();
			x2 = scanner.nextInt();
			resetDirect();
			resetVisited();
			for (int i = 1; i <= row; i++) {
				String line =scanner.next();
				for (int j = 1; j <= col; j++) {
					arr[i][j]=line.charAt(j-1)-'0';	
				}
			}
//			inArray();
//			inDirect();
//			inVisited();
			// xu ly
			bfs(x1,y1);
//			inDirect();
//			inVisited();
			int b =visited[x2][y2];
			System.out.println(b==0?-1:b);
		}
	}
	
}
