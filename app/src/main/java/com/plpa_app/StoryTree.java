package com.plpa_app;

import java.util.ArrayList;

public class StoryTree {
    private StoryNode root, current;
    ArrayList<StoryNode> nodeList = new ArrayList<StoryNode>();

    public StoryTree() {
        root = new StoryNode();
        current = root;
        nodeList.add(root);
    }

    public void fillNode(int value) {
        current.fillNode(value);
    }

    public void fillNode(int value, int question, int decision1, int decision2) {
        current.fillNode(value, question, decision1, decision2);
    }

    public void createLeft(int value) {
        current.createLeft(value);
        nodeList.add(current.getLeft());
    }

    public void createLeft(int value, int question, int decision1, int decision2) {
        current.createLeft(value, question, decision1, decision2);
        nodeList.add(current.getLeft());
    }

    public void createRight(int value) {
        current.createRight(value);
        nodeList.add(current.getRight());
    }

    public void createRight(int value, int question, int decision1, int decision2) {
        current.createRight(value, question, decision1, decision2);
        nodeList.add(current.getRight());
    }

    public boolean moveLeft() {
        StoryNode left = current.getLeft();
        if (left == null)
            return false;
        else {
            current = left;
            return true;
        }
    }

    public boolean moveRight() {
        StoryNode right = current.getRight();
        if (right == null)
            return false;
        else {
            current = right;
            return true;
        }
    }

    public boolean moveBack() {
        StoryNode back = current.getParent();
        if (back == null)
            return false;
        else {
            current = back;
            return true;
        }
    }

    public void toRoot() {
        current = root;
    }

    public void printInfo() {
        System.out.println(current.getValue());
        System.out.println(current.getQuestion());
        System.out.println(current.getDecision1());
        System.out.println(current.getDecision2());
    }

    public int getValue() {return current.getValue();}
    public int getQuestion() {return current.getQuestion();}
    public int getDecision1() {return current.getDecision1();}
    public int getDecision2() {return current.getDecision2();}
    public boolean isLeaf() {return current.isLeaf();}

    public StoryNode findNode(int value) {
        for (StoryNode n : nodeList) {
            if (n.getValue() == value)
                return n;
        }
        return null;
    }

    public boolean linkRight(int value) {
        StoryNode n = findNode(value);
        if (n == null)
            return false;
        else
            current.setRight(n);
            return true;
    }

    public boolean isRoot() {
        return current == root;
    }

}

class StoryNode {
    private int value, question, decision1, decision2;
    private StoryNode left = null, right = null, parent = null;
    private boolean leaf = false, empty = false;

    public StoryNode(int value, int question, int decision1, int decision2) {
        this.value = value;
        this.question = question;
        this.decision1 = decision1;
        this.decision2 = decision2;
        this.leaf = leaf;
    }

    public StoryNode(int value) {
        this.value = value;
        this.leaf = true;
    }

    public StoryNode() {
        empty = true;
    }

    public void createLeft(int value) {
        this.left = new StoryNode(value);
        this.left.parent = this;
        this.left.setLeaf(true);
    }

    public void createLeft(int value, int question, int decision1, int decision2) {
        this.left = new StoryNode(value, question, decision1, decision2);
        this.left.parent = this;
    }

    public void createRight(int value) {
        this.right = new StoryNode(value);
        this.right.parent = this;
        this.right.setLeaf(true);
    }

    public void createRight(int value, int question, int decision1, int decision2) {
        this.right = new StoryNode(value, question, decision1, decision2);
        this.right.parent = this;
    }

    public void fillNode(int value, int question, int decision1, int decision2) {
        this.value = value;
        this.question = question;
        this.decision1 = decision1;
        this.decision2 = decision2;
        this.empty = false;
    }

    public void fillNode(int value) {
        this.value = value;
        this.leaf = true;
        this.empty = false;
    }

    public int getValue() {return value;}
    public int getQuestion() {return question;}
    public int getDecision1() {return decision1;}
    public int getDecision2() {return decision2;}
    public StoryNode getLeft() {return left;}
    public StoryNode getRight() {return right;}
    public StoryNode getParent() {return parent;}

    public boolean isLeaf() {return this.leaf;}

    public void setLeaf(Boolean val) {this.leaf = val;}
    public void setRight(StoryNode node) {this.right = node;}
    public void setLeft(StoryNode node) {this.left = node;}

}
