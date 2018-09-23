package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Антон").withLastname("Смирнов").withMiddlename("Ивановыч").withNikname("Ton").withGroup("test1")
              .withMobilephonenumber("89093331111").withWorkphonenumber("89001112222").withHomephonenumber("8982211")
              .withEmail1("ton@mail.ru").withEmail2("a.smirn@ml.com").withEmail3("anton_onton@pt")
              .withAddress("Санкт-Петербургер, ул. Антоныча, Вокзал")
              .withPhoto(new File("src/test/resources/foto.jpg")), true);
    }
  }

  @Test(enabled = true)
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListUI();
  }


}
