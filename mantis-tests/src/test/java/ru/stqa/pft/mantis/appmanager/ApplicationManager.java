package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd;

  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private JamesHelper jamesHelper;
  private ChangeUserPassHelper changeUserPassHelper;
  private DbHelper dbHelper;
  private SoapHelper soapHelper;


  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target =  System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
  }

  public void stop() {
    if(wd != null){
    wd.quit();
    }
  }

  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if(registrationHelper == null){
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public FtpHelper ftp(){
    if(ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }


  public WebDriver getDriver() {
    if(wd == null){

      if(browser.equals(BrowserType.FIREFOX)){
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary(properties.getProperty("web.firefoxPath")));
      }else if(browser.equals(BrowserType.CHROME)){
        wd = new ChromeDriver(new ChromeOptions().setBinary(properties.getProperty("web.chromePath")));
      }else if(browser.equals(BrowserType.IE)){
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseURL"));
    }
    return wd;
  }

  public MailHelper mail(){
    if(mailHelper == null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james(){
    if(jamesHelper == null){
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }

  public ChangeUserPassHelper changePass() {
    if(changeUserPassHelper == null){
      changeUserPassHelper = new ChangeUserPassHelper(this);
    }
    return changeUserPassHelper;
  }

  public DbHelper db(){

    return dbHelper;
  }

  public SoapHelper soap(){
    if(soapHelper == null){
      soapHelper = new SoapHelper(this);
    }

    return soapHelper;
  }

}