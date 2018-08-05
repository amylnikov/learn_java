package ru.stqa.prt.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point(2, 4);
    Point p2 = new Point(6, 7);

    System.out.println("Расстояние между точкой А(" + p1.x + ", " + p1.y + ") и точкой B(" + p2.x + ", " + p2.y + ") составляет " + distance(p1, p2));

  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));

  }

}