package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.Rating;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class DriveStore implements Closeable {
    private Connection connection;
    private boolean complete;

    public DriveStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    /**
     * Gets all the open trips exist in the database
     *
     * @returns Arraylist of type Drive object
     * @autor Ahmed Omran
     */
    public ArrayList<Drive> getOpenDrives() throws StoreException {
        ArrayList<Drive> result = new ArrayList<>();
        String sqlQuery = "select f.fid, f.startort, f.zielort, f.fahrtkosten, f.maxPlaetze - CASE WHEN EXISTS(select anzPlaetze from dbp105.reservieren WHERE fahrt = f.fid) THEN sum(r.anzPlaetze) ELSE 0 END as freiplätze, t.icon \n" +
                "from dbp105.fahrt f LEFT JOIN dbp105.reservieren r ON f.fid = r.fahrt \n" +
                "INNER JOIN dbp105.transportmittel t ON f.transportmittel = t.tid \n" +
                "WHERE f.status = 'offen' \n" +
                "GROUP BY f.startort, f.zielort, f.fid, f.maxPlaetze, f.fahrtkosten, t.icon";

        try {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Drive drive = new Drive();
                drive.setStartort(rs.getString("startort"));
                drive.setZielort(rs.getString("zielort"));
                drive.setFreiplätze(rs.getInt("freiplätze"));
                drive.setFahrtkosten(rs.getFloat("fahrtkosten"));
                drive.setIcon(rs.getString("icon"));
                drive.setFID(rs.getInt("fid"));
                result.add(drive);
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return result;
    }

    /**
     * Gets all the reserved trips exist in the database
     *
     * @returns Arraylist of type Drive object
     * @autor Ahmed Omran
     */
    public ArrayList<Drive> getReservedDrives() throws StoreException {
        ArrayList<Drive> result = new ArrayList<>();
        User usr = new User();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from dbp105.reservieren r INNER JOIN dbp105.benutzer b ON b.bid = ? INNER JOIN dbp105.fahrt f ON r.fahrt = f.fid INNER JOIN dbp105.transportmittel t ON t.tid = f.transportmittel WHERE r.kunde = b.bid");
            ps.setInt(1, usr.getBID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Drive drive = new Drive();
                drive.setStartort(rs.getString("startort"));
                drive.setZielort(rs.getString("zielort"));
                drive.setStatus(rs.getString("status"));
                drive.setIcon(rs.getString("icon"));
                drive.setFID(rs.getInt("fid"));

                result.add(drive);
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return result;
    }

    /**
     * Gets all the information about specific trip exists in the database
     *
     * @returns object of type Drive
     * @autor Ahmed Omran
     */
    public Drive getDriveInformation(Drive drive) throws StoreException {

        String sqlQuery = "select b.name, b.bid, f.fahrtdatumzeit, f.startort, f.zielort,f.maxPlaetze - CASE WHEN EXISTS(select anzPlaetze from dbp105.reservieren WHERE fahrt = ?) THEN sum(r.anzPlaetze) ELSE 0 END as freiplätze, f.fahrtkosten, t.icon, f.status \n" +
                "from dbp105.fahrt f LEFT JOIN dbp105.reservieren r ON f.fid = r.fahrt\n" +
                "INNER JOIN dbp105.transportmittel t ON f.transportmittel = t.tid \n" +
                "INNER JOIN dbp105.benutzer b ON b.bid = f.anbieter WHERE fid = ? \n" +
                "GROUP BY f.startort, f.zielort, f.fid, f.maxPlaetze, f.fahrtkosten, t.icon, f.status, b.name, b.bid, f.fahrtdatumzeit";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            PreparedStatement ps2 = connection.prepareStatement("select f.beschreibung from dbp105.fahrt f WHERE f.fid = ?");
            ps.setInt(1, drive.getFID());
            ps.setInt(2, drive.getFID());
            ps2.setInt(1, drive.getFID());
            ResultSet rs = ps.executeQuery();
            ResultSet r2 = ps2.executeQuery();
            while (rs.next()) {
                drive.setStartort(rs.getString("startort"));
                drive.setZielort(rs.getString("zielort"));
                drive.setStatus(rs.getString("status"));
                drive.setIcon(rs.getString("icon"));
                drive.setFahrtkosten(rs.getFloat("fahrtkosten"));
                drive.setFahrtdatumzeit(rs.getTimestamp("fahrtdatumzeit"));
                drive.setFreiplätze(rs.getInt("freiplätze"));
                drive.setAnbieter(rs.getString("name"));
                drive.setBID(rs.getInt("bid"));
            }
            while (r2.next()) {
                drive.setBeschreibung(r2.getString(1));
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return drive;
    }

    public void storeNewDrive(Drive newDrive) throws StoreException {
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO dbp105.fahrt (startort,zielort,fahrtdatumzeit,maxPlaetze,fahrtkosten,anbieter,transportmittel,beschreibung) VALUES (?,?,?,?,?,?,?,?)");

            ps.setString(1, newDrive.getStartort());
            ps.setString(2, newDrive.getZielort());
            ps.setTimestamp(3, newDrive.getFahrtdatumzeit());
            ps.setInt(4, newDrive.getMaxplätze());
            ps.setFloat(5, newDrive.getFahrtkosten());
            ps.setInt(7, newDrive.getTransportmittel());
            ps.setString(8, newDrive.getBeschreibung());
            ps.setInt(6, user.getBID());
            ps.executeUpdate();

            System.out.println(newDrive.getFahrtkosten());

        } catch (SQLException e) {
            throw new StoreException(e);
        }

    }

    public ArrayList<Drive> getSearchDrives(Drive search) throws StoreException {
        ArrayList<Drive> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT f.fid,f.startort,f.zielort,f.fahrtkosten,f.fahrtdatumzeit, t.icon from dbp105.fahrt f INNER JOIN dbp105.transportmittel t ON f.transportmittel = t.tid WHERE status = 'offen' AND UPPER(f.startort) like '%' || UPPER(?) || '%' AND UPPER(f.zielort) like '%' || UPPER(?) || '%'  AND f.fahrtdatumzeit >= ?");

            ps.setString(1, search.getStartort());
            ps.setString(2, search.getZielort());
            ps.setTimestamp(3, search.getFahrtdatumzeit());
//            System.out.println("from store... \nstart: "+ search.getStartort() + "\nziel: "+ search.getZielort() + "\ndate: "+ search.getFahrtdatumzeit());
            ResultSet rs = ps.executeQuery();
            System.out.println("RESULTS>>>>>>>>>>>>>>>>>>");
            while (rs.next()) {
                Drive resultDrive = new Drive();
                resultDrive.setStartort(rs.getString("startort"));
                resultDrive.setZielort(rs.getString("zielort"));
                resultDrive.setIcon(rs.getString("icon"));
                resultDrive.setFahrtkosten(rs.getFloat("fahrtkosten"));
                resultDrive.setFahrtdatumzeit(rs.getTimestamp("fahrtdatumzeit"));
                resultDrive.setFID(rs.getInt("fid"));
                System.out.println(search.getStartort()+ "..."+ search.getZielort() + "////////////");

                result.add(resultDrive);
            }
            for (Drive r:result){
                System.out.println("in for loop >>>>>>>>");
                System.out.println(search.getStartort()+ "..."+ search.getZielort() + "////////////");
            }

        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return result;
    }

    /**
     * a function to check whether a User has license or not (in order to check if he/she is allowed to make new drive)
     * the function checks also if the existing license of that user will be valid on the date that he is trying
     * to make a drive on
     *
     * @return true for valid license on the wanted date, false otherwise
     * @autor Osama Elsafty
     */
    public boolean checkForLicense(Drive newDrive) {
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM dbp105.fahrerlaubnis WHERE fahrer=?");
            ps.setInt(1, user.getBID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("ablaufdatum").compareTo(String.valueOf(newDrive.getFahrtdatumzeit())) > 0) {
                    System.out.println("License ends on: " + rs.getString("ablaufdatum"));
                    System.out.println("Drive date: " + String.valueOf(newDrive.getFahrtdatumzeit()));
                    return true;
                }

            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean checkForDate(Drive newDrive) {
        User user = new User();
        Timestamp currentDateTime = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        if (newDrive.getFahrtdatumzeit().before(currentDateTime)) {
            return false;
        }
        return true;
    }

    /**
     * a finction that brings the open drives of a specific given user
     *
     * @return Array of drives
     * @autors Osama Elsafty, Ahmed Omran
     */
    public ArrayList<Drive> getOpenDrives(User driver) {
        String sqlGetUserOpenDrives = "select f.fid, f.startort, f.zielort, f.fahrtkosten, f.maxPlaetze - SUM(CASE WHEN r.anzPlaetze <> NULL THEN r.anzPlaetze ELSE 0 END) as freiplätze, t.icon from dbp105.fahrt f LEFT JOIN dbp105.reservieren r ON f.fid = r.fahrt INNER JOIN dbp105.transportmittel t ON f.transportmittel = t.tid WHERE f.status = 'offen' AND f.anbieter=? GROUP BY f.startort, f.zielort, f.fid, f.maxPlaetze, f.fahrtkosten, t.icon";
        ArrayList<Drive> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlGetUserOpenDrives);
            ps.setInt(1, driver.getBID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Drive drive = new Drive();
                drive.setStartort(rs.getString("startort"));
                drive.setZielort(rs.getString("zielort"));
                drive.setFreiplätze(rs.getInt("freiplätze"));
                drive.setFahrtkosten(rs.getFloat("fahrtkosten"));
                drive.setIcon(rs.getString("icon"));
                drive.setFID(rs.getInt("fid"));
                result.add(drive);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean deleteDrive(User user, Drive drive) throws RuntimeException {
        RatingStore ratingStore = new RatingStore();
        ArrayList<Rating> listOfRatingsIDs = ratingStore.getDriveRatings(drive);
        try {
            try(PreparedStatement ps = connection.prepareStatement("DELETE FROM dbp105.reservieren WHERE fahrt = ?")){
                //setting place holderss
                ps.setInt(1, drive.getFID());
                ps.executeUpdate();
            }try(PreparedStatement ps3 = connection.prepareStatement("DELETE FROM dbp105.schreiben s WHERE s.fahrt = ?")){
                ps3.setInt(1, drive.getFID());
                ps3.executeUpdate();
            }try(PreparedStatement ps2 = connection.prepareStatement("DELETE FROM dbp105.fahrt WHERE fid = ?")){
                ps2.setInt(1, drive.getFID());
                ps2.executeUpdate();
            }
            for (Rating rate: listOfRatingsIDs) {
                PreparedStatement ps4 = connection.prepareStatement("DELETE FROM dbp105.bewertung b WHERE b.beid = ?");
                ps4.setInt(1, rate.getBEID());
                ps4.executeUpdate();}

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void complete() {
        complete = true;
    }

    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
            } catch (SQLException e) {
                throw new StoreException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}
