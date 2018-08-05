package ru.stqa.prt.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p = new Point(2, 4, 6,7);

    System.out.println("Расстояние между точкой А(" + p.x1 + ", " + p.y1 + ") и точкой B(" + p.x2 + ", " + p.y2 + ") составляет " + p.distance());

  }


}