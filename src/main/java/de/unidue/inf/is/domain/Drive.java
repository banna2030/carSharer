package de.unidue.inf.is.domain;

import de.unidue.inf.is.stores.StoreException;
import de.unidue.inf.is.utils.DateTimeUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public final class Drive {
    private int FID;
    private int BID;
    private int TID;
    private String Anbieter;
    private float Fahrtkosten;
    private String Startort;
    private String Zielort;
    private String Status;
    private Timestamp fahrtdatumzeit;
    private int Freiplätze;
    private int Maxplätze;
    private String Icon;
    private String Beschreibung;

    public String getAnbieter() {
        return Anbieter;
    }

    public void setAnbieter(String anbieter) {
        Anbieter = anbieter;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        if (beschreibung == null){
            Beschreibung = "Keine Beschreibung";
        }else {
            Beschreibung = beschreibung;
        }
    }

    public Timestamp getFahrtdatumzeit() {
        return fahrtdatumzeit;
    }

    public void setFahrtdatumzeit(Timestamp fahrtdatumzeit) {
        this.fahrtdatumzeit = fahrtdatumzeit;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public int getBID() {
        return BID;
    }

    public void setBID(int BID) {
        this.BID = BID;
    }

    public int getTID() {
        return TID;
    }

    public void setTID(int TID) {
        this.TID = TID;
    }

    public int getFreiplätze() {
        return Freiplätze;
    }

    public void setFreiplätze(int freiplätze) {
        Freiplätze = freiplätze;
    }

    public int getMaxplätze() {
        return Maxplätze;
    }

    public void setMaxplätze(int maxplätze) {
        Maxplätze = maxplätze;
    }

    public Drive(){}

    public void setFID(int FID) {
        this.FID = FID;
    }

    public void setFahrtkosten(float fahrtkosten) {
        Fahrtkosten = fahrtkosten;
    }

    public void setStartort(String startort) {
        Startort = startort;
    }

    public void setZielort(String zielort) {
        Zielort = zielort;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getFID() {
        return FID;
    }

    public float getFahrtkosten() {
        return Fahrtkosten;
    }

    public String getStartort() {
        return Startort;
    }

    public String getZielort() {
        return Zielort;
    }

    public String getStatus() {
        return Status;
    }

    @Override
    public String toString() {
        return "Drive{" +
                "FID=" + FID +
                ", Fahrtkosten=" + Fahrtkosten +
                ", Startort='" + Startort + '\'' +
                ", Zielort='" + Zielort + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
