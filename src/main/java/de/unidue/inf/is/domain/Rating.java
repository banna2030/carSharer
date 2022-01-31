package de.unidue.inf.is.domain;
/**
 * Domain class which stores all ratings information
 *
 * @autor Ahmed Omran
 */
public class Rating {
    private int FID;
    private int BEID;
    private int Rating;
    private String textnachricht;
    private String erstellungsdatum;
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getFID() {
        return FID;
    }

    public void setFID(int FID) {
        this.FID = FID;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getTextnachricht() {
        return textnachricht;
    }

    public void setTextnachricht(String textnachricht) {
        this.textnachricht = textnachricht;
    }

    public String getErstellungsdatum() {
        return erstellungsdatum;
    }

    public void setErstellungsdatum(String erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

    public int getBEID() {
        return BEID;
    }

    public void setBEID(int BEID) {
        this.BEID = BEID;
    }
}
