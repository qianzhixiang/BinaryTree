package com.qianzhixiang;

import java.util.Stack;

public class BinaryTree {
    private Node root = null;

    BinaryTree(int value) {
        root = new Node(value);
        root.left = null;
        root.right = null;
    }

    /**
     * 依据值查询节点
     *
     * @param value
     * @return
     */
    public Node findKey(int value) {
        Node current = root;
        while (true) {
            if (current.value == value) {
                return current;
            } else if (current.value > value) {
                current = current.left;
            } else if (value > current.value) {
                current = current.right;
            }

            if (current == null) {
                return null;
            }
        }
    }

    public String insert(int value) {
        String error = null;

        Node node = new Node(value);
        if (root == null) {
            root = node;
            root.left  = null;
            root.right = null;
        } else {
            Node current = root;
            Node parent = null;
            while (true) {
                if (value < current.value) {
                    parent = current;
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        break;
                    }
                } else if (value > current.value) {
                    parent = current;
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        break;
                    }
                } else {
                    error = "having same value in binary tree";
                }
            } // end of while
        }
        return error;
    }

    /**
     * 中序遍历--递归
     */
    public void inOrderTraverse() {
        System.out.println("中序遍历");
        inOrderTraverse(root);
        System.out.println();
    }

    public void inOrderTraverse(Node node) {
        if (node == null)
            return;
        inOrderTraverse(node.left);
        node.display();
        inOrderTraverse(node.right);
    }

    /**
     * 中序遍历-非递归
     */
    public void inOrderByStack() {
        System.out.println("中序非递归遍历");
        Node current = root;
        Stack<Node> stack = new Stack<>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                current.display();
                current = current.right;
            }
        }
        System.out.println();
    }

    /**
     * //前序遍历(递归)：
     * 1、访问这个节点
     * 2、调用自身来遍历节点的左子树
     * 3、调用自身来遍历节点的右子树
     */
    public void preOrderTraverse() {
        System.out.print("前序遍历:");
        preOrderTraverse(root);
        System.out.println();
    }

    private void preOrderTraverse(Node node) {
        if (node == null)
            return;

        node.display();
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }


    /**
     * 前序非递归遍历：
     * 1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
     * 2）若左子树为空，栈顶节点出栈，将该节点的右子树置为current
     * 3) 重复1、2步操作，直到current为空且栈内节点为空。
     */
    public void preOrderByStack() {
        System.out.print("前序非递归遍历:");
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current.display();
                current = current.left;
            }

            if (!stack.isEmpty()) {
                current = stack.pop();
                current = current.right;
            }
        }
        System.out.println();
    }

    /**
     * //后序遍历(递归)：
     * 1、调用自身来遍历节点的左子树
     * 2、调用自身来遍历节点的右子树
     * 3、访问这个节点
     */
    public void postOrderTraverse() {
        System.out.print("后序遍历:");
        postOrderTraverse(root);
        System.out.println();
    }

    private void postOrderTraverse(Node node) {
        if (node == null)
            return;

        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        node.display();
    }

    /**
     * 后序非递归遍历：
     * 1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
     * 2）若左子树为空，取栈顶节点的右子树，如果右子树为空或右子树刚访问过，则访问该节点，并将preNode置为该节点
     * 3) 重复1、2步操作，直到current为空且栈内节点为空。
     */
    public void postOrderByStack() {
        System.out.print("后序非递归遍历:");
        Stack<Node> stack = new Stack<Node>();
        Node current = root;
        Node preNode = null;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (!stack.isEmpty()) {
                current = stack.peek().right;
                if (current == null || current == preNode) {
                    current = stack.pop();
                    current.display();
                    preNode = current;
                    current = null;
                }
            }
        }
        System.out.println();
    }

    public int getMinValue() {
        Node current = root;
        while (true) {
            if (current.left == null)
                return current.value;

            current = current.left;
        }
    }

    /**
     *
     * 得到后继节点，即删除节点的左后代
     */
    private Node getSuccessor(Node delNode) {
        Node successor = delNode;
        Node successorParent = null;
        Node current = delNode.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        //如果后继节点不是删除节点的右子节点时，
        if (successor != delNode.right) {
            //要将后继节点的右子节点指向后继结点父节点的左子节点，
            successorParent.left = successor.right;
            //并将删除节点的右子节点指向后继结点的右子节点
            successor.right = delNode.right;
        }
        //任何情况下，都需要将删除节点的左子节点指向后继节点的左子节点
        successor.left = delNode.left;

        return successor;
    }

    public boolean delete(int value) {
        Node current = root;    //需要删除的节点
        Node parent = null;     //需要删除的节点的父节点
        boolean isLeftChild = true;   //需要删除的节点是否父节点的左子树

        while (true) {
            if (value == current.value) {
                break;
            } else if (value < current.value) {
                isLeftChild = true;
                parent = current;
                current = current.left;
            } else {
                isLeftChild = false;
                parent = current;
                current = current.right;
            }

            //找不到需要删除的节点，直接返回
            if (current == null)
                return false;
        }

        //分情况考虑
        //1、需要删除的节点为叶子节点
        if (current.left == null && current.right == null) {
            //如果该叶节点为根节点，将根节点置为null
            if (current == root) {
                root = null;
            } else {
                //如果该叶节点是父节点的左子节点，将父节点的左子节点置为null
                if (isLeftChild) {
                    parent.left  = null;
                } else { //如果该叶节点是父节点的右子节点，将父节点的右子节点置为null
                    parent.right = null;
                }
            }
        }
        //2、需要删除的节点有一个子节点，且该子节点为左子节点
        else if (current.right == null) {
            //如果该节点为根节点，将根节点的左子节点变为根节点
            if (current == root) {
                root = current.left;
            } else {
                //如果该节点是父节点的左子节点，将该节点的左子节点变为父节点的左子节点
                if (isLeftChild) {
                    parent.left = current.left;
                } else {  //如果该节点是父节点的右子节点，将该节点的左子节点变为父节点的右子节点
                    parent.right = current.left;
                }
            }
        }
        //2、需要删除的节点有一个子节点，且该子节点为右子节点
        else if (current.left == null) {
            //如果该节点为根节点，将根节点的右子节点变为根节点
            if (current == root) {
                root = current.right;
            } else {
                //如果该节点是父节点的左子节点，将该节点的右子节点变为父节点的左子节点
                if (isLeftChild) {
                    parent.left = current.right;
                } else {  //如果该节点是父节点的右子节点，将该节点的右子节点变为父节点的右子节点
                    parent.right = current.left;
                }
            }
        }
        //3、需要删除的节点有两个子节点，需要寻找该节点的后续节点替代删除节点
        else {
            Node successor = getSuccessor(current);
            //如果该节点为根节点，将后继节点变为根节点，并将根节点的左子节点变为后继节点的左子节点
            if (current == root) {
                root = successor;
            } else {
                //如果该节点是父节点的左子节点，将该节点的后继节点变为父节点的左子节点
                if (isLeftChild) {
                    parent.left = successor;
                } else {  //如果该节点是父节点的右子节点，将该节点的后继节点变为父节点的右子节点
                    parent.right = successor;
                }
            }
        }
        current = null;
        return true;
    }
}
