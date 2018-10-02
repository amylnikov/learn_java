package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPassTests extends TestBase {
  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangeUserPass() throws IOException {
    long now = System.currentTimeMillis();
    HttpSession session = app.newSession();
    String newPassword = String.format("newpassword%s",now);
    User user = app.db().user();
    app.changePass().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.changePass().start(String.format("%s", user.getId()));
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    app.changePass().finish(confirmationLink, newPassword);
    session.login(user.getName(), newPassword);
    assertTrue(session.login(user.getName(),newPassword));
    assertTrue(session.isLoggedInAs(user.getName()));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }

}
