package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangeUserPassHelper extends HelperBase {
  public ChangeUserPassHelper(ApplicationManager app) {
    super(app);
    wd = app.getDriver();
  }


  public void login(String username, String password) {
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }


  public void start(String id) {
    click(By.xpath("/html/body/div[2]/p/span[1]/a"));
    click(By.xpath(String.format("/html/body/table[3]/tbody/tr/td/a[@href ='manage_user_edit_page.php?user_id=%s']",id)));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

}
