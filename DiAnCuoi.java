 package week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DiAnCuoi {
    static int T,n,m,res,idx;
    static int[][] a =new int[21][21];
    static int[] visited = new int[21];
    

    public static void reset(){
        for (int i = 1; i <= n; i++) {
            visited[i] =0;
            for (int j = 1; j <= n; j++) {
                a[i][j]=0;
            }
        }
    }
    public static void resetVisited(){
        for (int i = 1; i <= n; i++) {
            visited[i] =0;
        }
    }
    public static int demSoDinh(){
    	int cnt=0;
    	for (int i = 1; i <=n; i++) {
			if (visited[i]!=0) {
				cnt++;
			}
		}
    	return cnt;
    }
    
    public static void bt(int index){
//    	System.out.println("visist: "+ visited[index]+" "+index);
    	// dieu kien dung
    	if (index==1&&visited[index]==2){
//    		System.out.println("-------");
    		if (demSoDinh()<res) {
				res=demSoDinh();
			}
    		return;
		}
    	if (demSoDinh()>res) {
			return;
		}
    	///
    	for (int i = 1; i <= n; i++) {
			if (a[index][i]==1) {
				if (visited[2]!=0&&visited[i]<2) {
					visited[i]++;
					bt(i);
					visited[i]--;
					
				}else if (visited[2]==0&&visited[i]<1) {
					visited[i]++;
					bt(i);
					visited[i]--;
				}
			}
		}
    }
    public static void inRa(){
    	System.out.println();
    	for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/input.txt"));
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = scanner.nextInt();
            m = scanner.nextInt();
            reset();
            resetVisited();
            for (int i = 0; i < m; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                a[x][y] =1;
            }
//            inRa();
            res =999;
            visited[1]=1;
            bt(1);
            System.out.println(res);
        }
    }
}

//4
//6 7
//1 3
//3 4
//4 5
//5 1
//4 2
//2 6
//6 3
//9 11
//1 3
//3 4
//4 2
//2 5
//5 3
//3 6
//6 1
//2 7
//7 8
//8 9
//9 1
//12 10
//1 2
//2 3
//3 4
//4 5
//5 6
//6 7
//7 8
//8 9
//9 10
//10 1
//16 144
//3 9
//10 3
//1 14
//6 10
//12 2
//10 9
//11 6
//12 11
//16 11
//3 1
//14 12
//5 10
//15 7
//1 15
//9 16
//6 12
//8 16
//14 8
//13 4
//2 8
//5 11
//16 15
//10 16
//16 3
//8 5
//12 6
//11 12
//4 3
//11 10
//14 3
//9 12
//10 5
//15 11
//13 3
//2 5
//7 11
//4 12
//6 4
//14 13
//8 9
//3 2
//4 6
//4 14
//15 2
//16 7
//13 10
//4 8
//13 9
//3 14
//14 9
//8 1
//4 1
//10 11
//2 16
//1 9
//7 13
//7 6
//9 3
//14 11
//1 11
//12 7
//5 15
//8 15
//4 5
//11 4
//13 1
//2 6
//4 10
//9 1
//4 9
//14 5
//3 15
//14 2
//15 1
//11 7
//15 12
//5 14
//2 7
//16 6
//8 6
//12 10
//6 5
//9 6
//13 6
//6 1
//1 7
//14 15
//10 8
//2 13
//3 8
//6 14
//2 10
//16 14
//15 6
//9 5
//6 9
//11 3
//8 12
//5 4
//1 8
//6 7
//1 5
//15 16
//2 14
//3 12
//7 1
//13 5
//3 16
//7 8
//7 10
//14 7
//7 16
//9 11
//9 10
//5 1
//1 16
//10 4
//6 16
//10 7
//7 3
//11 1
//2 12
//12 13
//13 15
//15 10
//4 15
//5 8
//16 4
//8 13
//7 12
//6 8
//7 2
//4 13
//10 14
//15 13
//15 3
//11 15
//6 15
//16 10
//1 6
//7 5
//8 4
//10 6
//1 13
//6
//6
//10
//3