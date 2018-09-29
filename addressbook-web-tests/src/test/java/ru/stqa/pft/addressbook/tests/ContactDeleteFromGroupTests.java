package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeleteFromGroupTests extends TestBase {
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
  public void testContactDeleteFromGroup (){
    int contactToDeleteId;
    int groupFromWhichDeleteId;
    Contacts contactsInBase = app.db().contacts();
    Groups groupsInBase = app.db().groups();
    app.goTo().homePage();
    contactToDeleteId = app.contact().contactWhichCanDeleteFromGroup(contactsInBase);
    System.out.println(contactToDeleteId);
    if(contactToDeleteId == 0){
      app.contact().addToGroup(contactsInBase.iterator().next().getId(),groupsInBase.iterator().next().getId());
      app.goTo().homePage();
      app.contact().selectGroupById("group","");
      contactsInBase = app.db().contacts();
      contactToDeleteId = app.contact().contactWhichCanDeleteFromGroup(contactsInBase);
    }
    groupFromWhichDeleteId = app.contact().groupFromWhichCanDelete(contactToDeleteId);
    ContactData contactBefore = app.db().groupsInSelectedContact(contactToDeleteId);
    Groups groupsBefore = contactBefore.getGroups();

    app.contact().deleteFromGroup(contactToDeleteId,groupFromWhichDeleteId);

    GroupData deletedGroup = app.db().groupById(groupFromWhichDeleteId);
    ContactData contactAfter = app.db().groupsInSelectedContact(contactToDeleteId);
    Groups groupsAfter = contactAfter.getGroups();
    assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
    assertThat(groupsAfter, CoreMatchers.equalTo(groupsBefore.without(deletedGroup)));
  }

}
