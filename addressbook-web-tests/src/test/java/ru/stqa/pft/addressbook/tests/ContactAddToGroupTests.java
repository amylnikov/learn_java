package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    Groups groups = app.db().groups();
    if(groups.size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Антон").withLastname("Смирнов").withMiddlename("Ивановыч").withNikname("Ton").inGroup(groups.iterator().next())
              .withMobilephonenumber("89093331111").withWorkphonenumber("89001112222").withHomephonenumber("8982211")
              .withEmail1("ton@mail.ru").withEmail2("a.smirn@ml.com").withEmail3("anton_onton@pt")
              .withAddress("Санкт-Петербургер, ул. Антоныча, Вокзал")
              .withPhoto(new File("src/test/resources/foto.jpg")), true);
    }
  }

  @Test
  public void testContactAddToGroup(){
    int contactToAddId;
    Contacts contactsInBase = app.db().contacts();
    app.goTo().homePage();
    contactToAddId = app.contact().contactWhichCanAddToGroup(contactsInBase);
    if(contactToAddId == 0){
      int num = (int)(Math.random()*100);
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(String.format("test%s",num)));
      app.goTo().homePage();
      contactToAddId = app.contact().contactWhichCanAddToGroup(contactsInBase);
       }
    int addedGroupId = app.contact().groupToWhichCanAdd(contactToAddId);
    ContactData contactBefore = app.db().groupsInSelectedContact(contactToAddId);
    Groups groupsBefore = contactBefore.getGroups();
    app.contact().addToGroup(contactToAddId,addedGroupId);
    GroupData addedGroup = app.db().groupById(addedGroupId);
    ContactData contactAfter = app.db().groupsInSelectedContact(contactToAddId);
    Groups groupsAfter = contactAfter.getGroups();
    assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
    assertThat(groupsAfter, CoreMatchers.equalTo(groupsBefore.withAdded(addedGroup)));
  }
}
