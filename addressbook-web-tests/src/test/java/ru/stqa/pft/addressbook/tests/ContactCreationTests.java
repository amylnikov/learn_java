package ru.stqa.pft.addressbook.tests;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test(enabled = true)
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/foto.jpg") ;
    ContactData contact = new ContactData().withFirstname("Егор").withLastname("Серов").withMiddlename("Ивановыч").withNikname("Vanka").withGroup("test1")
            .withMobilephonenumber("89093331111").withWorkphonenumber("89001112222").withHomephonenumber("8982211")
            .withAddress("г. Тверь, ул. Ленина, д.11/22, кв.45")
            .withEmail1("e.serov@bk.ru").withEmail2("serov@mail.ru").withEmail3("gok@dom.com")
            .withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/foto.jpg") ;
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
