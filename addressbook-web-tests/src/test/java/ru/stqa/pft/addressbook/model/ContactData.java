package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Expose
  @Column(name = "middlename")
  private String middlename;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Column(name = "nickname")
  private String nikname;

  @Transient
  private String title;

  @Transient
  private String company;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homephonenumber;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilephonenumber;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workphonenumber;

  @Transient
  private String fax;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email1;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String homepage;

  @Expose
  @Transient
  private String birthyear;

  @Transient
  private String anniversaryyear;

  @Expose
  @Transient
  private String group;

  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNikname() {
    return nikname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephonenumber() {
    return homephonenumber;
  }

  public String getMobilephonenumber() {
    return mobilephonenumber;
  }

  public String getWorkphonenumber() {
    return workphonenumber;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getBirthyear() {
    return birthyear;
  }

  public String getAnniversaryyear() {
    return anniversaryyear;
  }

  public String getGroup() {
    return group;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }


  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNikname(String nikname) {
    this.nikname = nikname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomephonenumber(String homephonenumber) {
    this.homephonenumber = homephonenumber;
    return this;
  }

  public ContactData withMobilephonenumber(String mobilephonenumber) {
    this.mobilephonenumber = mobilephonenumber;
    return this;
  }

  public ContactData withWorkphonenumber(String workphonenumber) {
    this.workphonenumber = workphonenumber;
    return this;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withHomepage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public ContactData withBirthyear(String birthyear) {
    this.birthyear = birthyear;
    return this;
  }

  public ContactData withAnniversaryyear(String anniversaryyear) {
    this.anniversaryyear = anniversaryyear;
    return this;
  }


  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }


  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }


  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}
