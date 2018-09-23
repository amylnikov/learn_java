package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
   // app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Антон").withLastname("Смирнов").withMiddlename("Ивановыч").withNikname("Ton").withGroup("test1")
              .withMobilephonenumber("89093331111").withWorkphonenumber("89001112222").withHomephonenumber("8982211")
              .withEmail1("ton@mail.ru").withEmail2("a.smirn@ml.com").withEmail3("anton_onton@pt")
              .withAddress("Санкт-Петербургер, ул. Антоныча, Вокзал")
              .withPhoto(new File("src/test/resources/foto.jpg")), true);
    }
  }

  @Test(enabled = true)
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Семен").withLastname("Яковлев").withMiddlename("Селиванович").withNikname("Sema")
            .withAddress("Не дом и не улица")
            .withHomephonenumber("89098723123").withMobilephonenumber("911").withWorkphonenumber("112")
            .withEmail1("aaa@test.tst").withEmail2("a23a@test.tst").withEmail3("213@test.tst")
            .withPhoto(new File("src/test/resources/foto.jpg"));
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListUI();
  }


}
