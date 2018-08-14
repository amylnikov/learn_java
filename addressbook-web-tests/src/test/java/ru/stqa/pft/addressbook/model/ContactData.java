package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nikname;
  private final String title;
  private final String company;
  private final String address;
  private final String homephonenumber;
  private final String mobilephonenumber;
  private final String workphonenumber;
  private final String fax;
  private final String email1;
  private final String email2;
  private final String email3;
  private final String homepage;
  private final String birthyear;
  private final String anniversaryyear;

  public ContactData(String firstname, String middlename, String lastname, String nikname, String title, String company, String address, String homephonenumber, String mobilephonenumber, String workphonenumber, String fax, String email1, String email2, String email3, String homepage, String birthyear, String anniversaryyear) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nikname = nikname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephonenumber = homephonenumber;
    this.mobilephonenumber = mobilephonenumber;
    this.workphonenumber = workphonenumber;
    this.fax = fax;
    this.email1 = email1;
    this.email2 = email2;
    this.email3 = email3;
    this.homepage = homepage;
    this.birthyear = birthyear;
    this.anniversaryyear = anniversaryyear;

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

  public String getEmail() {
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

}
