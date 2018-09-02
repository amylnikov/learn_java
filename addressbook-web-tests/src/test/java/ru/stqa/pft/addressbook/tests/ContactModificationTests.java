package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test(enabled = false)
  public void testContactModification() {
    app.goTo().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Иван", "Иванович", "Иванов", "vano", "Друг", "ООО \"Огонёк\"", "Тверь, ул. Ленина, д. 2, кв. 33", "488-44-22", "9091321312", "489-76-88", "455-55-55", "ivan@test.tst", "ivan2@test.tst", null, "\\www.test.tst/vano", "1983", null, "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().openContactForEdit(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Петр", "Петрович", "Петров", "petro", "Враг", "ООО \"Тьма\"", "Москва, ул. Советская, д. 3, кв. 22", "588-44-22", "9061321312", "589-76-88", "555-55-55", "petr@test.tst", "petr2@test.tst", "petr3@test.tst", "\\www.test.tst/petro", "1969", "2000", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().updateContactData();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
