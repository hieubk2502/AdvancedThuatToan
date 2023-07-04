package onTapVong2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    // tao stack int
    private static int SIZE=500;
    private static String[] stackArray1;
    private static String[] stackArray2;
    private static int top1;
    private static int top2;
    Solution(int value){
        SIZE =value;
        stackArray1= new String[SIZE];
        stackArray2= new String[SIZE];
        top1=-1;
        top2=-1;
    }
    public static void push1(String value){
       stackArray1[++top1]=value;
       System.out.println("push1: "+value);
    }
    public static void push2(String value){
        stackArray2[++top2]=value;
        System.out.println("push2: "+value);
    }
    public static String pop1(){
        String stackCur =stackArray1[top1];
        top1--;
        System.out.println("pop:"+ stackCur);
        return stackCur;
    }
    public static String pop2(){
        String stackCur =stackArray2[top2];
        top2--;
        System.out.println("pop2:"+ stackCur);
        return stackCur;
    }
    public static String peek1(){
        return stackArray1[top1];
    }
    public static String peek2(){
        return stackArray2[top2];
    }
    public static boolean isEmpty1(){
        return top1==-1;
    }
    public static boolean isEmpty2(){
        return top2==-1;
    }
    public static boolean isFull1(){
        return top1==SIZE-1;
    }
    public static boolean isFull2(){
        return top2==SIZE-1;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\onTapVong2\\input.txt");
        Scanner scanner = new Scanner(fileInputStream);
        for (int tc = 1; tc <=1; tc++) {
            int n =scanner.nextInt();
            String s = scanner.next();
            Solution solution = new Solution(500);
            String[] arr =s.split("");
            for (int i = 0; i < n; i++) {
                System.out.println("arr[i]: "+arr[i]);
                if (arr[i].equals("+")||arr[i].equals("(")||arr[i].equals("*")){
                    if (arr[i].equals("*")){
                        if(!arr[i+1].equals("(")&&i+1<n){
                            int intTopStack = Integer.parseInt(pop2());
                            int nextValue =Integer.parseInt(arr[i+1]);
                            System.out.println(intTopStack+"---"+nextValue);
                            String valueCur = Integer.toString(nextValue*intTopStack);
                            System.out.println("valueCur: "+ valueCur);
                            push2(valueCur);
                            i++;
                        }
                    }else{
                        push1(arr[i]);
                    }

                }
                else if (arr[i].equals("0")||arr[i].equals("1")||arr[i].equals("2")||arr[i].equals("3")||arr[i].equals("4")
                        ||arr[i].equals("5")||arr[i].equals("6")||arr[i].equals("7")||arr[i].equals("8")||arr[i].equals("9")){
                    System.out.println("??");
                    push2(arr[i]);
                }
                else if (arr[i].equals(")")){
                    int sum =0;
                    while (!peek1().equals("(")){
                        sum += Integer.parseInt(pop2())+Integer.parseInt(pop2());
                        System.out.println("sum: "+sum);
                        pop1();
                    }
                    push2(Integer.toString(sum));
                }
            }
            // xu ly
            int res =0;
            while (!isEmpty2()){
                res+=Integer.parseInt(pop2());
            }
            System.out.println(res);

        }
    }

}
