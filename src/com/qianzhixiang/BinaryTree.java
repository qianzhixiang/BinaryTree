package com.qianzhixiang;

public class BinaryTree {
    private Node root=null;

    BinaryTree(int value){
        root = new Node(value);
        root.left = null;
        root.right = null;
    }

    public Node findKey(int value){
        Node current = root;
        while (true){
            if(current.value == value){
                return current;
            }else if(current.value > value){
                current = current.left;
            }else if(value > current.value){
                current = current.right;
            }

            if(current==null){
                return null;
            }
        }
    }

    public void insert(int value){

    }

    public void inOrderTraverse(){
        System.out.println("中序遍历");
        inOrderTraverse(root);
        System.out.println();
    }

    public void inOrderTraverse(Node node){
        if(node == null)
            return;
        inOrderTraverse(node.left);
        node.display();
        inOrderTraverse(node.right);
    }

    public void inOrderByStack(){

    }

}
