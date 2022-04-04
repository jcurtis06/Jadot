package me.jcurtis.javaengine.engine.utils;

public class Vector2 {
    public double x = 0;
    public double y = 0;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) Math.round(x);
    }

    public int getY() {
        return (int) Math.round(y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double magnitude() {
        return Math.sqrt((x * x) + (y * y));
    }

    public Vector2 normalized() {
        if (this.x == 0 && this.y == 0) return new Vector2(0, 0);
        double magnitude = this.magnitude();
        return new Vector2(this.x /= magnitude, this.y /= magnitude);
    }

    public Vector2 multiplyAll(double num) {
        return new Vector2(this.x * num, this.y * num);
    }

    public Vector2 addAll(double num) {
        return new Vector2(this.x + num, this.y + num);
    }

    public Vector2 addVec(Vector2 vector2) {
        return new Vector2(this.x + vector2.x, this.y + vector2.y);
    }

    public Vector2 subVec(Vector2 vector2) {
        return new Vector2(this.x - vector2.x, this.y - vector2.y);
    }

    public String toString() {
        return this.x + ", " + this.y;
    }
}
