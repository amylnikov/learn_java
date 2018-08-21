package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Иван", "Иванович", "Иванов", "vano", "Друг", "ООО \"Огонёк\"", "Тверь, ул. Ленина, д. 2, кв. 33", "488-44-22", "9091321312", "489-76-88", "455-55-55", "ivan@test.tst", "ivan2@test.tst", null, "\\www.test.tst/vano", "1983", null, "test1"), true);
    }
    app.getContactHelper().openContactForEdit();
    app.getContactHelper().fillContactForm(new ContactData("Петр", "Петрович", "Петров", "petro", "Враг", "ООО \"Тьма\"", "Москва, ул. Советская, д. 3, кв. 22", "588-44-22", "9061321312", "589-76-88", "555-55-55", "petr@test.tst", "petr2@test.tst", "petr3@test.tst", "\\www.test.tst/petro", "1969", "2000",null), false);
    app.getContactHelper().updateContactData();
    app.getNavigationHelper().gotoHomePage();

  }

}
