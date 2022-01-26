package de.unidue.inf.is.domain;
/**
 * Domain class which stores all user information
 *
 * @autor Ahmed Omran
 */
public final class User {

    private String Name;
    private String Email;
    private int BID;
    private int Anplätze;


    public User() {
        this.BID = 2;
    }

    public int getAnplätze() {
        return Anplätze;
    }

    public void setAnplätze(int anplätze) {
        Anplätze = anplätze;
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
}