package de.unidue.inf.is.domain;

public class Rating {
    private int FID;
    private int Rating;
    private String textnachricht;
    private String erstellungsdatum;

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
}
