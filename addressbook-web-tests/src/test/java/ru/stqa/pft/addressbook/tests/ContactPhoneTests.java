package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Антон").withLastname("Смирнов").withMiddlename("Ивановыч").withNikname("Ton").withGroup("test1")
              .withMobilephonenumber("89093331111").withWorkphonenumber("89001112222").withHomephonenumber("8982211")
              .withEmail1("ton@mail.ru").withEmail2("a.smirn@ml.com").withEmail3("anton_onton@pt")
              .withAddress("Санкт-Петербургер, ул. Антоныча, Вокзал"), true);
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomephonenumber(), equalTo(cleaned(contactInfoFromEditForm.getHomephonenumber())));
    assertThat(contact.getMobilephonenumber(), equalTo(cleaned(contactInfoFromEditForm.getMobilephonenumber())));
    assertThat(contact.getWorkphonenumber(), equalTo(cleaned(contactInfoFromEditForm.getWorkphonenumber())));
  }

  public String cleaned(String phones){
    return phones.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
