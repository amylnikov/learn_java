package ru.stqa.prt.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point(2, 4);
    Point p2 = new Point(6, 7);

    System.out.println("Расстояние между точкой А(" + p1.x + ", " + p1.y + ") и точкой B(" + p2.x + ", " + p2.y + ") составляет " + p1.distance(p2));

  }


}