package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class DriveStore implements Closeable {
    private Connection connection;
    private boolean complete;

    public DriveStore() throws StoreException{
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

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

    public ArrayList<Drive> getReservedDrives() throws StoreException {
        ArrayList<Drive> result = new ArrayList<>();
        User usr = new User();
        //usr.setBID(User.loggedInBID);

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

    public Drive getDriveInformation(Drive drive) throws StoreException {

        String sqlQuery = "select b.name, f.fahrtdatumzeit, f.startort, f.zielort,f.maxPlaetze - CASE WHEN EXISTS(select anzPlaetze from dbp105.reservieren WHERE fahrt = ?) THEN sum(r.anzPlaetze) ELSE 0 END as freiplätze, f.fahrtkosten, t.icon, f.status \n" +
                "from dbp105.fahrt f LEFT JOIN dbp105.reservieren r ON f.fid = r.fahrt\n" +
                "INNER JOIN dbp105.transportmittel t ON f.transportmittel = t.tid \n" +
                "INNER JOIN dbp105.benutzer b ON b.bid = f.anbieter WHERE fid = ? \n" +
                "GROUP BY f.startort, f.zielort, f.fid, f.maxPlaetze, f.fahrtkosten, t.icon, f.status, b.name, f.fahrtdatumzeit";
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
            }
            while (r2.next()){
                drive.setBeschreibung(r2.getString(1));
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return drive;
    }

    public void storeNewDrive(Drive newDrive) throws StoreException{
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO dbp105.fahrt (startort,zielort,fahrtdatumzeit,maxPlaetze,fahrtkosten,anbieter,transportmittel,beschreibung) VALUES (?,?,?,?,?,?,?,?)");

            ps.setString(1,newDrive.getStartort());
            ps.setString(2,newDrive.getZielort());
            ps.setTimestamp(3,newDrive.getFahrtdatumzeit());
            ps.setInt(4,newDrive.getMaxplätze());
            ps.setFloat(5,newDrive.getFahrtkosten());
            ps.setInt(7,newDrive.getTransportmittel());
            ps.setString(8,newDrive.getBeschreibung());
            ps.setInt(6,user.getBID());
            ps.executeUpdate();

            System.out.println(newDrive.getFahrtkosten());

        } catch (SQLException e) {
            throw new StoreException(e);
        }

    }
    public ArrayList<Drive> getSearchDrives(Drive search) throws StoreException {
        ArrayList<Drive> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT f.fid,f.startort,f.zielort,f.fahrtkosten,f.fahrtdatumzeit, t.icon from dbp105.fahrt f INNER JOIN dbp105.transportmittel t ON f.transportmittel = t.tid WHERE status = 'offen' AND f.startort = ? AND f.zielort =? AND f.fahrtdatumzeit >= ?");


            ps.setString(1, search.getStartort());
            ps.setString(2,search.getZielort());
            ps.setTimestamp(3,search.getFahrtdatumzeit());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                search.setStartort(rs.getString("startort"));
                search.setZielort(rs.getString("zielort"));
                search.setIcon(rs.getString("icon"));
                search.setFahrtkosten(rs.getFloat("fahrtkosten"));
                search.setFahrtdatumzeit(rs.getTimestamp("fahrtdatumzeit"));
                search.setFID(rs.getInt("fid"));

                result.add(search);
                System.out.println(search.getStartort());
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
     * @return true for valid license on the wanted date, false otherwise
     * @autor Osama Elsafty
     */
    public boolean checkForLicense(Drive newDrive) {
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM dbp105.fahrerlaubnis WHERE fahrer=?");
            ps.setInt(1,user.getBID());
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("ablaufdatum").compareTo(String.valueOf(newDrive.getFahrtdatumzeit()))>0) {
                    System.out.println("License ends on: " + rs.getString("ablaufdatum"));
                    System.out.println("Drive date: "+ String.valueOf(newDrive.getFahrtdatumzeit()));
                    return true;
                }

            }
                return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * a finction that brings the open drives of a specific given user
     * @return Array of drives
     * @autors Osama Elsafty, Ahmed Omran
     */
    public ArrayList<Drive> getOpenDrives(User driver) {
        String sqlGetUserOpenDrives = "select f.fid, f.startort, f.zielort, f.fahrtkosten, f.maxPlaetze - SUM(CASE WHEN r.anzPlaetze <> NULL THEN r.anzPlaetze ELSE 0 END) as freiplätze, t.icon from dbp105.fahrt f LEFT JOIN dbp105.reservieren r ON f.fid = r.fahrt INNER JOIN dbp105.transportmittel t ON f.transportmittel = t.tid WHERE f.status = 'offen' AND f.anbieter=? GROUP BY f.startort, f.zielort, f.fid, f.maxPlaetze, f.fahrtkosten, t.icon";
        ArrayList<Drive> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sqlGetUserOpenDrives);
            ps.setInt(1,driver.getBID());
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

    public void complete() {
        complete = true;
    }

    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}
