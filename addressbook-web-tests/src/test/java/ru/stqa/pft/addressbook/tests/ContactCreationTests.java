package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test(enabled = true)
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Егор").withLastname("Серов").withMiddlename("Ивановыч").withNikname("Vanka").withGroup("test1")
            .withMobilephonenumber("89093331111").withWorkphonenumber("89001112222").withHomephonenumber("8982211")
            .withAddress("г. Тверь, ул. Ленина, д.11/22, кв.45")
            .withEmail1("e.serov@bk.ru").withEmail2("serov@mail.ru").withEmail3("gok@dom.com");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
