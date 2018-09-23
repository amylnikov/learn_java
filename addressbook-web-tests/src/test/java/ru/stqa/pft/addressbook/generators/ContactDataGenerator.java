package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  private SessionFactory sessionFactory;

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data fopmat")
  public String format;


  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try{
      jCommander.parse(args);
      generator.run();
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if(format.equals("csv")){
      saveAsCSV(contacts, new File(file));
    } else if(format.equals("xml")){
      saveAsXML(contacts, new File(file));
    } else if(format.equals("json")){
      saveAsJSON(contacts, new File(file));
    }else{
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try(Writer writer = new FileWriter(file)){
      writer.write(json);
    }
  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    xstream.alias("contacts", List.class);
    String xml = xstream.toXML(contacts);
    try(Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try(Writer writer = new FileWriter(file)){
      for(ContactData contact : contacts){
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",contact.getFirstname(),contact.getLastname(),contact.getMiddlename(),
                contact.getNikname(),
                contact.getGroups().iterator().next().getName(),
                contact.getMobilephonenumber(),contact.getWorkphonenumber(),contact.getHomephonenumber(),
                contact.getAddress(),
                contact.getEmail1(),contact.getEmail2(),contact.getEmail3(),contact.getPhoto()));
      }
    }
  }


  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for(int i =0; i<count; i++){
      contacts.add(new ContactData().withFirstname(String.format("Имя%s", i)).withLastname(String.format("Фамилия%s", i)).withMiddlename(String.format("Отчество%s", i)).withNikname(String.format("Ник%s", i))
              .inGroup(new GroupData().withName(String.format("test%s", i)))
              .withMobilephonenumber(String.format("%s9093331111", i)).withWorkphonenumber(String.format("%s9001112222", i)).withHomephonenumber(String.format("%s982211", i))
              .withAddress("г. Тверь, ул. Ленина, д.11/22, кв." + i)
              .withEmail1(String.format("e.serov%s@bk.ru", i)).withEmail2(String.format("serov%s@mail.ru", i)).withEmail3(String.format("gok%s@dom.com", i)).withPhoto(new File("src/test/resources/foto.jpg")));
    }
    return contacts;
  }

}

