package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    if(!app.getContactHelper().isThereAContact()){
    app.getContactHelper().createContact(new ContactData("Иван", "Иванович", "Иванов", "vano", "Друг", "ООО \"Огонёк\"", "Тверь, ул. Ленина, д. 2, кв. 33", "488-44-22", "9091321312", "489-76-88", "455-55-55", "ivan@test.tst", "ivan2@test.tst", null, "\\www.test.tst/vano", "1983", null, "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomePage();
  }

}
