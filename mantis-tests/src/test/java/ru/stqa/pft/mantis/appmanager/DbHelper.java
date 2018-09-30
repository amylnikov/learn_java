package ru.stqa.pft.mantis.appmanager;



import java.sql.*;
import java.util.ArrayList;


public class DbHelper {
  public String userId(){
    Connection conn = null;
    ArrayList<String> userids = new ArrayList<String>();
    String userid = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" + "user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id from mantis_user_table where username not like 'administrator'");
      while (rs.next()){
        userids.add(rs.getString("id"));
      }
      userid = userids.iterator().next();
      rs.close();
      st.close();
      conn.close();
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
   return userid;
  }

  public String userMail(String userId) {
    Connection conn = null;
    ArrayList<String> emails = new ArrayList<String>();
    String email = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" + "user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select email from mantis_user_table where id like '" + userId + "'");
      while (rs.next()){
        emails.add(rs.getString("email"));
      }
      email = emails.iterator().next();
      rs.close();
      st.close();
      conn.close();
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return email;
  }

  public String userName(String userId) {
    Connection conn = null;
    ArrayList<String> usernames = new ArrayList<String>();
    String username = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" + "user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select username from mantis_user_table where id like '" + userId + "'");
      while (rs.next()){
        usernames.add(rs.getString("username"));
      }
      username = usernames.iterator().next();
      rs.close();
      st.close();
      conn.close();
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return username;
  }
}
