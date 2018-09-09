package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

 // public void checkInputContactData() {
 //   click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
 // }

  public void inputContactData() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
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
    type(By.name("email"),contactData.getEmail1());
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

    if(creation){
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    accept();
  }


  public void openContactForEditById(int id) {
    wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[*]/td[8]/a[@href='edit.php?id=" + id + "']/img")).click();
  }

  public void updateContactData() {
    click(By.name("update"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact,creation);
    inputContactData();
    contactCache = null;
    returnToContactPage();
  }

  public void modify(ContactData contact) {
    openContactForEditById(contact.getId());
    fillContactForm(contact, false);
    updateContactData();
    contactCache = null;
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]")).size();
  }

  Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null){
      contactCache = new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("id"));
      String lastname = element.findElement(By.xpath("td[2]")).getText();
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      String[] phones = element.findElement(By.xpath("td[6]")).getText().split("\n");

      String address = element.findElement(By.xpath("td[4]")).getText();

      String[] emails = element.findElement(By.xpath("td[5]")).getText().split("\n");
      if(phones != null && emails != null) {
        contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                .withHomephonenumber(phones[0]).withMobilephonenumber(phones[1]).withWorkphonenumber(phones[2])
                .withAddress(address)
                .withEmail1(emails[0]).withEmail2(emails[1]).withEmail3(emails[2]));
      }
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    openContactForEditById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");

    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");

    String address = wd.findElement(By.name("address")).getAttribute("value");

    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();

    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomephonenumber(home).withMobilephonenumber(mobile).withWorkphonenumber(work)
            .withAddress(address)
            .withEmail1(email1).withEmail2(email2).withEmail3(email3);
  }
}
