package onTapVong2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class trungtohauto {
    private static int SIZE;
    private static char[] stackArray;
    private static char[] queueArray;

    private static int topInt ;
    private static int topChar;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("D:\\CTDLvaTT\\FindThe3rdLargestNumber\\src\\onTapVong2\\input.txt"));
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        Stack<Character> stackArray = new Stack<>();
        Stack<Integer> stackInt = new Stack<>();
        Queue<Character> queueArray = new LinkedList<>();

        for (int tc = 1; tc <= T; tc++) {
            int n = scanner.nextInt();
            String line = scanner.next();
            char[] s = new char[line.length()];
            for (int i = 0; i < line.length(); i++) {
                s[i] = line.charAt(i);
            }
            // xu ly
            for (int i = 0; i < line.length(); i++) {
                System.out.println("xet: "+s[i]);
                //// toan tu
                // 1. stack rong -> push
                // 2. k rong  neu stack.peek ='(" ----> push stack
                // 3. khac : so sanh : vao >stack.peek ---> push stack
                //                     while(vao<=stack.peek) ----> add queue(stack.pop)
                if (s[i]=='+'||s[i]=='*'||s[i]=='-'){
                    if (stackArray.empty()){
                        System.out.println("stack rong");
                        System.out.println("push stack: "+stackArray.push(s[i]));
                    }else {
                        if (stackArray.peek()=='('){
                            System.out.println("stack.peek:='(");
                            System.out.println("push stack: "+stackArray.add(s[i]));
                        }
                        else if (s[i]=='+'&&stackArray.peek()=='*'||s[i]=='-'&&stackArray.peek()=='*'){
                            boolean is=true;
                            while (!stackArray.empty()&&is){
                                if (s[i]=='+'&&stackArray.peek()=='*'||s[i]=='-'&&stackArray.peek()=='*'){
                                    System.out.println("so sanh vao<peek "+queueArray.add(stackArray.pop()));
                                }else {
                                    is=false;
                                }
                            }
                            stackArray.push(s[i]);
                        }else {
                            System.out.println("queueArray.add(s[i]): " + stackArray.add(s[i]));
                        }
                    }
                }
                /// stack.peek ='(' push stack luon
                else if (s[i]=='(') {
                    System.out.println("stackArray.add(s[i]) "+stackArray.push(s[i]));
                }
                // 1. neu stack.peek=='(' pop stack luon
                // 2. stack.pop chuyen sang queue.add
                else if (s[i]==')') {
                    if (stackArray.peek()=='('){
                        System.out.println("stackArray.pop(): "+stackArray.pop());

                    }else {
                        while (stackArray.peek() !='('){
                            char preStack =stackArray.pop();
                            System.out.println("stackArray.pop(): "+preStack);
                            queueArray.add(preStack);
                        }
                        stackArray.pop();
                    }

                }
                /// toan hang add queue luon
                else {
                    System.out.println("queueArray.add(s[i]) "+queueArray.add(s[i]));
                }
                //////// bieu thuc het phan tu
                if (i==line.length()-1){
                    while (!stackArray.empty()){
                        queueArray.add(stackArray.pop());
                    }
                }
            }
            for (char x:
                 queueArray) {
                System.out.print(x);
            }
            int sum=0;
            for (char x:
                 queueArray) {
                if (x>='0'&&x<='9'){
                    int value =x-'0';
                    stackInt.push(value);
                }else {
                    if(x=='+'){
                        int value = stackInt.pop()+stackInt.pop();
                        stackInt.push(value);
                    }else {
                        int value = stackInt.pop()*stackInt.pop();
                        stackInt.push(value);
                    }
                }
            }
            // cong
            while (!stackInt.isEmpty()){
                sum+=stackInt.pop();
            }
            System.out.println(sum);
        }
    }
}
