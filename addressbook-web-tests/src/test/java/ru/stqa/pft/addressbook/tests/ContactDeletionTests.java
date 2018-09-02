package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)
  public void testContactDeletion() {
    app.goTo().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Иван", "Иванович", "Иванов", "vano", "Друг", "ООО \"Огонёк\"", "Тверь, ул. Ленина, д. 2, кв. 33", "488-44-22", "9091321312", "489-76-88", "455-55-55", "ivan@test.tst", "ivan2@test.tst", null, "\\www.test.tst/vano", "1983", null, "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
