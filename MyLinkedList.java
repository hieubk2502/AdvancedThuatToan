package onTapVong2;

import java.util.EventListener;

public class MyLinkedList {

    public static class Node{
        int value;
        Node next;
        Node(int value){
            this.value = value;
        }
    }
    public static void printLinkedList(Node head){
        if(head==null){
            System.out.println("List is empty!");
        }
        else {
            Node tempNode = head;
            while(tempNode !=null){
                System.out.print(tempNode.value);
                tempNode = tempNode.next;
                if (tempNode != null){
                    System.out.print("->");
                }
                else {
                    System.out.println();
                }
            }
        }
    }
    // them phan tu vao dau
    public static Node addToHead(Node headNode, int value){
        Node newNode = new Node(value);
        if (headNode !=null){
            newNode.next = headNode;
        }
        return newNode;
    }
    // them phan tu vao cuoi
    public static Node addToTail(Node headNode, int value ){
        Node newNode = new Node(value);
        if (headNode==null){
            return newNode;
        }else {
            Node lastNode = headNode;
            while (lastNode.next !=null){
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }
        return headNode;
    }
    // chen vao giua
    public static Node addToIndex(Node headNode, int value, int index){

        if (index==0){
            return addToHead(headNode,value);
        }else {
            Node newNode  = new Node(value);
            int count =0;
            Node curNode = headNode;
            while (curNode !=null){
                count++;
                if (count==index){
                    newNode.next = curNode.next;
                    curNode.next = newNode;
                    break;
                }
                curNode = curNode.next;
            }
        }
        return headNode;
    }

    // xoa phan tu dau
    public static Node removeAtHead(Node headNode){
        if (headNode!=null){
            return headNode.next;
        }
        return headNode;
    }
    // xoa phan tu dau
    public static Node removeAtTail(Node headNode){
        // tim pre va last
        Node lastNode = headNode;
        Node preNode = null;
        while (lastNode.next !=null){
            preNode = lastNode;
            lastNode = lastNode.next;
        }
        if (preNode ==null){
            return null;
        }else {
            preNode.next= lastNode.next;
        }
        return headNode;
    }
    // xoa phan tu o giua
    public static Node removeAtIndex(Node headNode, int index){
        if(headNode==null||index<0){
            return null;
        }
        if (index==0){
            return removeAtHead(headNode);
        }
        // xac dinh pre, last
        Node curNode = headNode;
        Node preNode = null;
        int count =0;
        boolean bIsFound = false;
        while (curNode !=null){
            if (count==index){
                bIsFound =true;
                break;
            }
            preNode = curNode;
            curNode = curNode.next;
            count++;
        }
        if (bIsFound){
            // ton tai index
            if (preNode==null){
                return null;// chi co 1 node
            }else {
                if (curNode !=null){
                    preNode.next = curNode.next;
                }
            }
        }
        return headNode;
    }
    public static Node xoaphantu(Node headNode, int index){
        if (headNode ==null ||index<0){
            return null;
        }
        if (index ==0){
            return removeAtHead(headNode);
        }
        Node currNode = headNode;
        Node preNode = null;
        boolean bIsFound = false;
        int count =0;
        while (currNode != null){
            if (count ==index){
                bIsFound =true;
                break;
            }
        }
        return currNode;
    }


    public static void main(String[] args){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        Node node0=addToHead(node1,0);
        printLinkedList(node0);
        node0 =addToTail(node0,5);
        printLinkedList(node0);
        node0 = addToIndex(node0,0,0);
        printLinkedList(node0);
        node0 = addToIndex(node0,8,6);
        printLinkedList(node0);
        node0 = addToIndex(node0,2,2);
        printLinkedList(node0);
        node0 = removeAtTail(node0);
        printLinkedList(node0);
        node0 = removeAtTail(node0);
        printLinkedList(node0);
        node0 = removeAtTail(node0);
        printLinkedList(node0);
        node0 = removeAtHead(node0);
        printLinkedList(node0);
        // remove index
        node0 = removeAtIndex(node0,0);
        printLinkedList(node0);
        node0 = removeAtIndex(node0,2);
        printLinkedList(node0);
        node0 = removeAtIndex(node0,2);
        printLinkedList(node0);
    }


}















