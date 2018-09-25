package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
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
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNikname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephonenumber());
    type(By.name("mobile"), contactData.getMobilephonenumber());
    type(By.name("work"), contactData.getWorkphonenumber());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());

    if (!select(By.xpath("//div[@id='content']/form/select[1]//option[14]"))) {
      click(By.xpath("//div[@id='content']/form/select[1]//option[14]"));
    }
    if (!select(By.xpath("//div[@id='content']/form/select[2]//option[10]"))) {
      click(By.xpath("//div[@id='content']/form/select[2]//option[10]"));
    }
    type(By.name("byear"), contactData.getBirthyear());

    if (!select(By.xpath("//div[@id='content']/form/select[3]//option[10]"))) {
      click(By.xpath("//div[@id='content']/form/select[3]//option[10]"));
    }
    if (!select(By.xpath("//div[@id='content']/form/select[4]//option[4]"))) {
      click(By.xpath("//div[@id='content']/form/select[4]//option[4]"));
    }
    type(By.name("ayear"), contactData.getAnniversaryyear());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    attach(By.name("photo"), contactData.getPhoto());

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

  public void openDetailsById(int id) {
    wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[*]/td[7]/a[@href='view.php?id=" + id + "']/img")).click();
  }

  public void updateContactData() {
    click(By.name("update"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
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
    if (contactCache != null) {
      contactCache = new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("id"));
      String lastname = element.findElement(By.xpath("td[2]")).getText();
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      String allPhones = element.findElement(By.xpath("td[6]")).getText();

      String address = element.findElement(By.xpath("td[4]")).getText();

      String allEmails = element.findElement(By.xpath("td[5]")).getText();
      if (allPhones != null && allEmails != null) {
        contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                .withAllPhones(allPhones)
                .withAddress(address)
                .withAllEmails(allEmails));
      } else {
        contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
      }
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

  public void addToNewGroup(Contacts contacts) {
    boolean outFlag = false;
    List<WebElement> elements = wd.findElements(By.xpath("//form[@id='right']//select/option"));
    ArrayList<String> names = new ArrayList<String>();
    for (WebElement element : elements) {
      names.add(element.getText());
      System.out.println(element);
    }

    // try {
    for (String name : names) {
      if(outFlag){break;}
      System.out.println(name);
      if (name.equals("[none]")) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
        //System.out.println(name);
        List<WebElement> contactsInGroup = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]"));
        if (contactsInGroup.size() != 0) {
          click(By.name("selected[]"));
          //int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("id"));
          click(By.name("add"));
          outFlag = true;
        }

      } else if (!name.equals("[none]") && !name.equals("[all]")) {      //Условие при выполнении которого определяем, что выбран список для какой-то конкретной группы
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(name); //Собственно выбираем эту группу, чтобы отобразился список контактов принадлежащий этой группе
        List<WebElement> contactsInGroup = wd.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]")); //Собираем список всех контактов в виде ссылок на веб-элементы элементы
        if (contactsInGroup.size() != 0) { //проверяем не пустой ли получился список
          for (WebElement contactInGroup : contactsInGroup) { //Зпускаем первый уровень хреноциклов по отобранным контактам в данной группе, нужен чтобы можно было по очереди выбирать контакты в этой группе
            click(By.name("selected[]")); //Выделяем контакт на котором сейчас находимся в цикле
            int id = Integer.parseInt(wd.findElement(By.name("selected[]")).getAttribute("id")); //Берем идентификатор выбранного контакта для дальнейшей проверки
            for (ContactData contact : contacts) { //Запускаем второй уровень хреноциклов по всем имеющимся контактам в базе
              if (contact.getId() == id) { //Условие при выполнении которого находим выделенный контакт
                Groups groups = new Groups(contact.getGroups()); //Получаем информацию о всех группах в которые включен данный контакт
                System.out.println(groups);
                List<WebElement> groupsToAdd = wd.findElements(By.xpath("//div[@class='right']//select/option"));
                for (WebElement groupToAdd : groupsToAdd) {
                  if(outFlag){break;}
                  boolean inGroup = false;
                  String groupToAddName = groupToAdd.getText();
                  System.out.println("===========================================================================");
                  System.out.println("Выбрана группа " + groupToAddName + " из списка групп в которые можно включить контакт\n");
                  System.out.println("Проверяем включен ли контакт уже в эту группу или нет:\n");
                  for (GroupData group : groups) {
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    String groupName = group.getName();
                    if (groupToAddName.equals(groupName)) {
                      System.out.println("Группа " + groupToAddName + " совпала с группой контакта " + groupName + " переходим к проверке следующей группы в которую включен контакт\n");
                      inGroup = true;
                    } else {
                      System.out.println("Группа " + groupToAddName + " не совпала с группой контакта " + groupName);
                    }
                  }
                  if (!inGroup) {
                    System.out.println("Группа " + groupToAddName + " не совпала ни с одной группой в контакте, значит можно включить в эту группу данный контакт\n");
                    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupToAddName);
                    click(By.name("add"));
                    outFlag = true;
                  }else{
                    System.out.println("Группа " + groupToAddName + " уже используется контактом");
                  }
                }
              }
            }
            click(By.name("selected[]"));
          }
        }
      }

    }
    //   } catch (Exception e) {
    //    System.out.println(e.getMessage());
    //  }
  }

}
