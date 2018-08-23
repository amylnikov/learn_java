package ru.stqa.prt.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args){
    String[] langs = {"Java","C#","Python","PHP"};
   // langs[0] = "Java";
   // langs[1] = "C#";
   // langs[2] = "Python";
   // langs[3] = "PHP";

   // List<String> languages = new ArrayList<String>();
   // languages.add("Java");
   // languages.add("C#");
   // languages.add("PHP");
   // languages.add("C++");

    List<String> languages = Arrays.asList("Java","C#","Python","PHP");

    for (String l:languages){
  System.out.println("Язык программирования " + l + " скоро попадет в мою копилку изученных...");
}

    for (int i=0; i<languages.size();i++){
      System.out.println("Язык программирования " + languages.get(i) + " скоро попадет в мою копилку изученных...");
    }

  }
}
