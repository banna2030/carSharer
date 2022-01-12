package de.unidue.inf.is.domain;

public final class User {

    private String firstname;
    private String lastname;
    private String Name;
    private String Email;
    private int BID;
    private int Anplätze;

    public int getAnplätze() {
        return Anplätze;
    }

    public void setAnplätze(int anplätze) {
        Anplätze = anplätze;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getBID() {
        return BID;
    }

    public void setBID(int BID) {
        this.BID = BID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public User() {}

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;

    }

}