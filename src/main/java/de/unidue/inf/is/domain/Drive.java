package de.unidue.inf.is.domain;

import de.unidue.inf.is.stores.StoreException;
import de.unidue.inf.is.utils.DateTimeUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class Drive {
    private int FID;
    private int BID;
    private int TID;
    private float Fahrtkosten;
    private String Startort;
    private String Zielort;
    private String Status;
    private DateTimeUtil FahrtDatum;
    private DateTimeUtil Fahrtuhrzeit;
    private int Freiplätze;
    private int Maxplätze;

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

    public void setFahrtDatum(DateTimeUtil fahrtDatum) {
        FahrtDatum = fahrtDatum;
    }

    public void setFahrtuhrzeit(DateTimeUtil fahrtuhrzeit) {
        Fahrtuhrzeit = fahrtuhrzeit;
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

    public DateTimeUtil getFahrtDatum() {
        return FahrtDatum;
    }

    public DateTimeUtil getFahrtuhrzeit() {
        return Fahrtuhrzeit;
    }

    @Override
    public String toString() {
        return "Drive{" +
                "FID=" + FID +
                ", Fahrtkosten=" + Fahrtkosten +
                ", Startort='" + Startort + '\'' +
                ", Zielort='" + Zielort + '\'' +
                ", Status='" + Status + '\'' +
                ", FahrtDatum=" + FahrtDatum +
                ", Fahrtuhrzeit=" + Fahrtuhrzeit +
                '}';
    }
}
