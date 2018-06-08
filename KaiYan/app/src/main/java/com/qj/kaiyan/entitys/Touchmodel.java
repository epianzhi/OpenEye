package com.qj.kaiyan.entitys;

public class Touchmodel {
    private float left;
    private float top;
    private float right;
    private float bottom;

    private int i;
    public Touchmodel(float left, float top, float right, float bottom,int i) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;

        this.i=i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    @Override
    public String toString() {
        return "Touchmodel{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                ", i=" + i +
                '}';
    }
}
