package ru.stqa.pft.mantis.appmanager;



import ru.stqa.pft.mantis.model.User;

import java.sql.*;
import java.util.ArrayList;


public class DbHelper {
  public User user(){
    Connection conn = null;
    User userData = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" + "user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table where username not like 'administrator'");
      ArrayList<User> users = new ArrayList<User>();
      while (rs.next()){
        users.add(new User().withId(Integer.parseInt(rs.getString("id"))).withName(rs.getString("username")).withEmail(rs.getString("email")));
      }
      userData = users.iterator().next();
      rs.close();
      st.close();
      conn.close();
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
   return userData;
  }
}
