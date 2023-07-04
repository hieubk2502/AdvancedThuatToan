 //////////////////////////////////////////Hugo thi chạy
Hugo thi chạy

Sắp tới công ty nên Hugo làm việc tổ chức sự kiện Olympic dành cho toàn bộ nhân viên. Có rất nhiều bộ môn thi đấu như Bóng bàn, cầu long, cờ vua, cờ tướng, bơi lội, và có cả thể thao điện tử nữa. Là một người quan tâm tới sức khỏe của bản thân vì vậy Hugo thường xuyên chạy bộ để rèn luyện sức khỏe. Thật may trong các môn thi đấu Olympic có hạng mục này. Trong quá trình tập luyện Hugo đã luyện cho mình được 5 kiểu chạy khác nhau, mỗi kiểu chạy sẽ tiêu tốn số lượng năng lượng nhất định và thời gian chạy hết 1km là khác nhau. Mỗi kiểu chạy sẽ sử dụng cho 1km.

Năng lượng của Hugo là có hạn, nếu sử dụng vượt Hugo sẽ bị bệnh.


Sau khi tham khảo thông tin Hugo biết được quãng đường cần chạy bộ D của cuộc thi. Nhân viên y tế có thể giúp Hugo tính toán được số năng lượng tối đa của Hugo.
Cho thông tin của 5 kiểu chạy của Hugo bao gồm số phút, số giây (số phút <= 6 và số giây <= 60) và số năng lượng tiêu tốn.
Hãy tính thời gian ngắn nhất để Hugo về đích mà không bị bệnh.

 

Input

Dòng đầu số test T (T<=50)

Mỗi test bao gồm 2 dòng

Dòng 1 chứa số năng lượng tối đa M <=60 và chiều dài quãng đường D<=40km
Dòng thứ 2 chứa thông tin của 5 kiểu chạy lần lượt là số phút, số giây, số năng lượng tiêu tốn

 

Output
In ra thời gian ngắn nhất để Hugo về đích mà không bị bệnh theo dạng số phút, số giây. Nếu không thể hãy in ra -1

 

Input

4

297 10

5 38 23 5 22 12 4 16 6 5 38 20 0 20 17

192 10

2 6 12 6 5 24 2 22 22 4 13 12 4 30 16

503 10

1 42 20 1 8 14 0 33 15 2 6 6 5 3 16

122 10

2 37 21 3 59 22 6 0 22 4 56 5 0 9 10

 

Output

Case #1

3 20

Case #2

21 0

Case #3

5 30

Case #4

1 30
#include <iostream>
using namespace std;

int n,result;
int check[6];
int phut,giay,calo[6],thoigian[6];
int sum,km;
int a;

void backtrack (int kieuchay,int k,int secons,int nang_luong){
	if(nang_luong > sum) return;
	if(result < secons) return;
	if(k >= km){
		if(result > secons) result = secons;
		return;
	}
	for(int i = kieuchay; i < 5; i++){
		backtrack(i,k+1,secons +  thoigian[i], nang_luong + calo[i]);
	}
}

int main(){
//	freopen("Text.txt","r",stdin);
	int T; cin >> T;
	for(int tc = 1; tc <= T; tc ++){
		cin >> sum >> km;
		for(int i = 0; i < 5; i++){
			cin >> phut >> giay >> calo[i];
			a = phut * 60 + giay;
			thoigian[i] = a;
		}
		result = 10000000;
		backtrack(0,0,0,0);
		int p = result / 60;
		int g = result % 60;
		if(result != 10000000){
			cout << "Case #" << tc << endl << p << " " << g << endl;
		}
		else cout << "Case #" << tc << endl << -1 << endl;
	}
	return 0;
}

import java.util.Scanner;

public class Solution {
	
	static int maxEnergy, runDist, minM, minS;
	static int minute = 0, second = 1, energy = 2 , typeRun = 5;
	static int[][] chart = new int[typeRun][3];;
	static int[] maxRun = new int[typeRun];
	static int[] visit;
	
//	private static int calTime(int type) {
//		int tmp = 0
//		for(int i = 0; i < visit[type]; i++) {
//			
//		}
//	}
	
	private static void backtrack(int type, int km, int min, int sec, int sumE) {
		
		if(km == runDist) {
			if(min < minM) {
				minS = sec; minM = min;
			}
			else if(min == minM && sec < minS) minS = sec;
			return;
		}
		
		if(type == typeRun - 1) {
			visit[type] = runDist - km;
			int tm = chart[type][minute] * visit[type];
			int ts = chart[type][second] * visit[type];
			int te = chart[type][energy] * visit[type];
			if(sumE + te < maxEnergy) {
				tm = tm + min +(sec + ts)/60;
				ts = (sec + ts) % 60;
				if(tm < minM) {
					minS = ts; minM = tm;
				}
				else if(tm == minM && ts < minS) minS = ts;
			}
			return;
		}
		
		if(min > minM || (sec > minS && min == minM)) return;
		
		int tmp = maxRun[type] < runDist ? maxRun[type] : runDist;
		int tm = 0, ts = 0, te = 0;
		for(int i = 0; i <= tmp - km; i++) {
			visit[type] = i;
			tm = chart[type][minute] * i;
			ts = chart[type][second] * i;
			te = chart[type][energy] * i;
			
			if(sumE + te < maxEnergy) {
				tm = tm + min +(sec + ts)/60;
				ts = (sec + ts) % 60;
				backtrack(type + 1, km + i, tm, ts, sumE + te);
			}
			else return;
			
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int testcase = sc.nextInt();
		for(int tc = 1; tc <= testcase; tc++) {
			maxEnergy = sc.nextInt();
			runDist = sc.nextInt();
			
			boolean flag = false; int cond = maxEnergy / runDist + 1;
			for(int i = 0; i < typeRun; i++) {
				chart[i][minute] = sc.nextInt();
				chart[i][second] = sc.nextInt();
				chart[i][energy] = sc.nextInt();
				if(chart[i][energy] < cond) flag = true;
				maxRun[i] = maxEnergy / chart[i][energy];
			}
			visit = new int[typeRun];
			
			minM = Integer.MAX_VALUE;
			minS = Integer.MAX_VALUE;
			
			System.out.println("Case #" + tc);
			
			if(!flag) {
				System.out.println(-1);
			}
			else {
				backtrack(0, 0, 0, 0, 0);
				System.out.println(minM + " " + minS);
			}
//			return;
			
		}
	}

}

/////////////////////////// 
Hugo về nhà
 

Hugo đang trền đường về nhà và cần đi qua 1 đoạn đường B.

Trên đoạn đường đi qua có N cổng. Tại mỗi cổng có 1 số lượng binh sĩ và giá để đi qua cổng đó. Muốn đi qua mỗi cổng Hugo có 3 cách lựa chọn.

1. Pass

Trả số tiền quy định ở cổng đó để được đi qua

2. Hire

Trả gấp đôi số tiền ở cổng đó để thuê số binh sĩ gộp vào đoàn quân của mình

3. battle

Điều kiện để đánh nhau là số quân của Hugo >= số lượng lính tại cổng đó. Có các lưu ý:

+ Hugo k được tính vào số lượng của quân

+ Mỗi người lính chỉ tham gia được vào tối đa 3 trận đánh. Sau 3 trận đánh nếu đi nhóm binh sĩ đó còn sống thì cũng giải tán.

+ Mỗi trận đánh thì tất cả số binh sĩ đều tham gia.

+ Đánh nhau chết theo tỉ lệ 1: 1. Ai tham gia trước sẽ bị chết trước

Input:
Dòng đầu tiên là số lượng trường hợp thử nghiệm

Mỗi trường hợp thử nghiệm sẽ có
          Dòng đầu tiên chứa số lượng cổng N

          N dòng tiếp theo chứa 2 số là số binh lính và chi phí tại mỗi cổng

Output: In ra chi phí nhỏ nhất Hugo có thể đi qua đoạn đường B

Điều kiện input: số cổng <=20

-      2 <=Số lính và chi phí đi qua <=1000

VD:

Input
2

7

10 100

70 5

80 15

20 60

50 90

30 80

10 10

9

600 800

300 400

300 400

1000 400

300 600

100 300

600 300

600 500

1000 300

 

Output:

#1 150

#2 3000
#include <stdio.h>
#define MAX_N 21
#define INF 1000000000

int money[MAX_N];
int warrior[MAX_N];
int N;
int teammate[MAX_N];
int old, fresh;
int minCost;

void go(int i, int cost)
{
	if (i == N) {
		if (cost < minCost)
			minCost = cost;
		return;
	}
	if (cost >= minCost)
		return;

	go(i + 1, cost + money[i]);

	teammate[fresh] += warrior[i];
	go(i + 1, cost + 2 * money[i]);
	teammate[fresh] -= warrior[i];

	if (teammate[old] + teammate[old + 1] + teammate[fresh] >= warrior[i]) {
		int remain = warrior[i];
		int dead[3];
		dead[0] = dead[1] = dead[2] = 0;
		for (int j = old; j <= fresh; j++) {
			if (remain > teammate[j]) {
				remain -= teammate[j];
				dead[j - old] = teammate[j];
				teammate[j] = 0;
			} else {
				teammate[j] -= remain;
				dead[j - old] = remain;
				break;
			}
		}
		old++; fresh++;
		teammate[fresh] = 0;
		go(i + 1, cost);
		old--; fresh--;
		for (int j = old; j <= fresh; j++)
			teammate[j] += dead[j - old];
	}
}

int main()
{
	int tc, T;
	scanf("%d", &T);
	for (tc = 1; tc <= T; tc++) {
		scanf("%d", &N);
		for (int i = 0; i < N; i++) 
			scanf("%d%d", &warrior[i], &money[i]);
		old = 0; fresh = 2;
		teammate[0] = teammate[1] = teammate[2] = 0;
		minCost = INF;
		go(0, 0);
		printf("#%d %d\n", tc, minCost);

	}
	return 0;
}
