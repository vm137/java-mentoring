package com.epam.tickets.model.dto;

import com.epam.tickets.model.UserInterface;

public class User implements UserInterface {
  private long id;
  private String name;
  private String email;

  public User() {}

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "User: { " +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' + " }";
  }
}
