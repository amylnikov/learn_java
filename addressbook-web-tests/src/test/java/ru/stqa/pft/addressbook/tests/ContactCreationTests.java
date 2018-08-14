package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Иван", "Иванович", "Иванов", "vano", "Друг", "ООО \"Огонёк\"", "Тверь, ул. Ленина, д. 2, кв. 33", "488-44-22", "9091321312", "489-76-88", "455-55-55", "ivan@test.tst", "ivan2@test.tst", "ivan3@test.tst", "\\www.test.tst/vano", "1983", "2005", "Не дом и не улица", "444-33-22", "Очень странный человек"));
    app.getContactHelper().inputContactData();
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().checkInputContactData();
    app.getNavigationHelper().returnToHomePage();
  }


}
