package bfsDfsBacktracking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HugoChay {
    static int T,gioiHanNangLuong,quangduong,res;
    static int[] thoiGian =new int[5];
    static int[] nangluong = new int[5];


    public static void BT(int kieuChay,int nangLuong,int qDuong,int time){
        if (qDuong>=quangduong){
            if (nangLuong<=gioiHanNangLuong&&time<res){
                res=time;
            }
            return;
        }
        if (nangLuong>gioiHanNangLuong)return;
        for (int i = 0; i < 5; i++) {
            if (kieuChay<5){
                BT(kieuChay+1,nangLuong+i*nangluong[kieuChay],quangduong+i,time+thoiGian[kieuChay]);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\bfsDfsBacktracking\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            gioiHanNangLuong = scanner.nextInt();
            quangduong = scanner.nextInt();
            for (int i = 0; i < 5; i++) {
                int phut = scanner.nextInt();
                int giay = scanner.nextInt();
                nangluong[i]= scanner.nextInt();
                int giaySeLa = phut*60+giay;
                thoiGian[i] = giaySeLa;
            }
            res = 99999999;
            BT(0,0,0,0);
            int p = res/60;
            int g = res%60;
            if (res!=99999999){
                System.out.println(p+" "+g);
            }else{
                System.out.println(-1);
            }


        }
    }
}
