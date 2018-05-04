package com.qianzhixiang;

public class Node {
    int value;
    Node right;
    Node left;

    Node(int value){
        this.value = value;
    }

    public void display(){
        System.out.println(this.value + "\t");
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
