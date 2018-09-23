package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

public class ContactAddToGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    Groups groups = app.db().groups();
    if(groups.size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
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
    Contacts before = app.db().contacts();
    ContactData selectedContact = before.iterator().next();
    Groups mog = app.contact().memberOfGroups(selectedContact);
    app.goTo().homePage();
    app.contact().addToNewGroup(mog);
  }
}
