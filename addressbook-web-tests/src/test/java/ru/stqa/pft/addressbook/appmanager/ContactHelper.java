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
    return collectVisibleWebElements("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]").size();
  }

  public void selectGroupById(String group, String format) {
    new Select(wd.findElement(By.name(group))).selectByValue(format);
  }


  Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      contactCache = new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = collectVisibleWebElements("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]");
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

  public int contactWhichCanAddToGroup(Contacts contacts) {
    ArrayList<String> groupsId = elements("//form[@id='right']//select/option","value");
    for (String groupId : groupsId) {
      if (groupId.equals("[none]")) {
        selectGroupById("group", String.format("%s",groupId));
        List<WebElement> contactsInGroup = collectVisibleWebElements("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]");
        if (contactsInGroup.size() != 0) {
          int contactId = Integer.parseInt(wd.findElement(By.name("selected[]")).getAttribute("id"));
          return contactId;
        }
      } else if (!groupId.equals("[none]") && !groupId.equals("")) {
        selectGroupById("group", String.format("%s",groupId)); //Собственно выбираем эту группу, чтобы отобразился список контактов принадлежащий этой группе
        List<WebElement> contactsInGroup = collectVisibleWebElements("//*[@id=\"maintable\"]/tbody/tr[@name=\"entry\"]"); //Собираем список всех контактов в виде ссылок на веб-элементы элементы
        if (contactsInGroup.size() != 0) { //проверяем не пустой ли получился список
          for (WebElement contactInGroup : contactsInGroup) { //Зпускаем первый уровень циклов по отобранным контактам в данной группе, нужен чтобы можно было по очереди выбирать контакты в этой группе
            int inGroupContactId = Integer.parseInt(contactInGroup.findElement(By.name("selected[]")).getAttribute("id")); //Берем идентификатор выбранного контакта для дальнейшей проверки
            click(By.id(String.format("%s", inGroupContactId))); //Выделяем контакт на котором сейчас находимся в цикле
            for (ContactData contact : contacts) { //Запускаем второй уровень циклов по всем имеющимся контактам в базе
              if (contact.getId() == inGroupContactId) { //Условие при выполнении которого находим выделенный контакт
                Groups groups = new Groups(contact.getGroups()); //Получаем информацию о всех группах в которые включен данный контакт
                List<WebElement> groupsToAdd = collectVisibleWebElements("//div[@class='right']//select/option");
                for (WebElement groupToAdd : groupsToAdd) {
                  boolean inGroup = false;
                  int groupToAddId = Integer.parseInt(groupToAdd.getAttribute("value"));
                  for (GroupData group : groups) {
                    int groupIdInBase = group.getId();
                    if (groupToAddId == groupIdInBase) {
                      inGroup = true;
                    }
                  }
                  if (!inGroup) {
                    return inGroupContactId;
                  }
                }
              }
            }
          }
        }
      }
    }
    return 0;
  }


  public int groupToWhichCanAdd(int contactId) {
    ArrayList<String> groupsId = elements("//div[@class='right']//select/option","value");
    for (String groupId : groupsId) {
        boolean inGroupFlag = false;
        selectGroupById("group", String.format("%s",groupId));
        List<WebElement> contactsInGroup = collectVisibleWebElements("//*[@name=\"selected[]\"]");
        if (contactsInGroup.size() == 0) {
          return Integer.parseInt(groupId);
        } else {
          for (WebElement contactInGroup : contactsInGroup) {
            int contactInGroupId = Integer.parseInt(contactInGroup.getAttribute("id"));
            if (contactInGroupId == contactId) {
              inGroupFlag = true;
              break;
            }else {inGroupFlag = false;}
          }
          if (!inGroupFlag){
          return Integer.parseInt(groupId);}
        }
      }
    return 0;
  }

  public void addToGroup(int contactId, int groupId) {
    selectGroupById("group","");
    selectContactById(contactId);
    selectGroupById("to_group", String.format("%s", groupId));
    click(By.name("add"));
  }



  public int contactWhichCanDeleteFromGroup(Contacts contacts){
    ArrayList<String> contactsId = elements("//*[@name=\"selected[]\"]","value");
    for(String contactId : contactsId){
      int id = Integer.parseInt(contactId);
      for(ContactData contact : contacts){
        if(contact.getId() == id){
          Groups groupsOfThisContact = new Groups(contact.getGroups());
          if(groupsOfThisContact.size() != 0){
            return id;
          }
        }
      }
    }
    return 0;
  }

  public int groupFromWhichCanDelete(int contactToDeleteId) {
    ArrayList<String> groupsId =  elements("//div[@class='right']//select/option","value");
    for (String groupId : groupsId) {
      selectGroupById("group", String.format("%s",groupId));
      List<WebElement> contactsInGroup = collectVisibleWebElements("//*[@name=\"selected[]\"]");
      if (contactsInGroup.size() != 0){
        for (WebElement contactInGroup : contactsInGroup) {
          int contactInGroupId = Integer.parseInt(contactInGroup.getAttribute("id"));
          if(contactInGroupId == contactToDeleteId){
            return Integer.parseInt(groupId);
          }
        }
      }
    }
    return 0;
  }

  public void deleteFromGroup(int contactId, int groupId) {
    selectGroupById("group",String.format("%s", groupId));
    selectContactById(contactId);
    click(By.name("remove"));
  }


  private ArrayList<String> elements(String webElement, String attribute) {
    List<WebElement> elements = collectVisibleWebElements(webElement);
    ArrayList<String> id = new ArrayList<String>();
    for (WebElement element : elements) {
      id.add(element.getAttribute(attribute));
    }
    return id;
  }


}
