package ru.stqa.pft.mantis.model;

public class User {
  private int id;
  private String name;
  private String email;

  public int getId() {
    return id;
  }

  public User withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public User withName(String name) {
    this.name = name;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User withEmail(String email) {
    this.email = email;
    return this;
  }
}
