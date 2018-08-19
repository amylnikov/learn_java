package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void checkInputContactData() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
  }

  public void inputContactData() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNikname());
    type(By.name("title"),contactData.getTitle());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getHomephonenumber());
    type(By.name("mobile"),contactData.getMobilephonenumber());
    type(By.name("work"),contactData.getWorkphonenumber());
    type(By.name("fax"),contactData.getFax());
    type(By.name("email"),contactData.getEmail());
    type(By.name("email2"),contactData.getEmail2());
    type(By.name("email3"),contactData.getEmail3());
    type(By.name("homepage"),contactData.getHomepage());

    if (!select(By.xpath("//div[@id='content']/form/select[1]//option[14]"))) {
      click(By.xpath("//div[@id='content']/form/select[1]//option[14]"));
    }
    if (!select(By.xpath("//div[@id='content']/form/select[2]//option[10]"))) {
      click(By.xpath("//div[@id='content']/form/select[2]//option[10]"));
    }
    type(By.name("byear"),contactData.getBirthyear());

    if (!select(By.xpath("//div[@id='content']/form/select[3]//option[10]"))) {
      click(By.xpath("//div[@id='content']/form/select[3]//option[10]"));
    }
    if (!select(By.xpath("//div[@id='content']/form/select[4]//option[4]"))) {
      click(By.xpath("//div[@id='content']/form/select[4]//option[4]"));
    }
    type(By.name("ayear"),contactData.getAnniversaryyear());



  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    accept();
  }

  public void openContactForEdit() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContactData() {
    click(By.name("update"));
  }
}
