package org.example;

public class Rectangle {
    private float width;
    private float height;

    public Rectangle(float width, float height){
        this.width=width;
        this.height=height;

    }
    public float area(){
        return  width * height;
    }

    public float perimeter(){
        return 2 *(width + height);
    }

    public void show(){
        System.out.println("Area:"+area());
        System.out.print("Perimeter:"+perimeter());
    }

    public static void main(String[] args){
        Rectangle rectangle = new Rectangle(10,5);
        rectangle.show();
    }
}