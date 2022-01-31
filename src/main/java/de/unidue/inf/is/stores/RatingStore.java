package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.Rating;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RatingStore implements Closeable {
    /**
     * inserting new review to the database with the parameters passed from NewRatingServlet
     *
     * @autor Team-work
     */
    //connection variables and functions
    private Connection connection;
    private boolean complete;
    public RatingStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new StoreException(e);
        }
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

    /**
     * makes a new review "bewertung" and tries to link it to the current user and the drive (in table "screiben"
     * if the linking failed (happens if user has already reviewed the same drive before
     * the function deletes the made review from table "bewertung"
     *
     * @returns true if adding and linking is successful, false otherwise
     * @autor Osama Elsafty
     */
    public boolean sendReview(int bid, int fid, Rating rating) throws RuntimeException {
        final String sqlGetBEID = "SELECT beid FROM dbp105.bewertung " +
                "WHERE textnachricht LIKE ? AND erstellungsdatum=? AND rating=?";
        // like is used for CLOB attributes
        final String SqlInsertToSchreiben = "INSERT INTO dbp105.schreiben " +
                "(benutzer, fahrt, bewertung) VALUES (?,?,?)";
        final String SqlInsertToBewertung = "INSERT INTO dbp105.bewertung " +
                "(textnachricht, erstellungsdatum, rating) VALUES " +
                "(?,?,?)";
        final String sqlDeleteFromBewertung = "DELETE FROM dbp105.bewertung WHERE beid=?";

        try {
            PreparedStatement ps = connection.prepareStatement(SqlInsertToBewertung);
            //setting place holders
            ps.setString(1, rating.getTextnachricht());
            ps.setString(2, rating.getErstellungsdatum());
            ps.setInt(3, rating.getRating());
            ps.executeUpdate();
//            System.out.println("inserted to bewertung");
            //linking to the user and the drive
            //getting the id of the the newly added review
            PreparedStatement ps2 = connection.prepareStatement(sqlGetBEID);
            ps2.setString(1, rating.getTextnachricht());
            ps2.setString(2, rating.getErstellungsdatum());
            ps2.setInt(3, rating.getRating());
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                rating.setBEID(rs2.getInt("beid"));
            }
//            System.out.println("got the value of beid: " + rating.getBEID());
            //saving to table "schreiben"
            PreparedStatement ps3 = connection.prepareStatement(SqlInsertToSchreiben);
            ps3.setInt(1, bid);
            ps3.setInt(2, fid);
            ps3.setInt(3, rating.getBEID());
//            System.out.println("BID: " + bid + "\nfid" + fid + "\nbeid" + rating.getBEID());
            ps3.executeUpdate();
//            System.out.println("inserted to schreiben");
            return true;
        } catch (SQLException e) {
            //exception happens when user tries to add 2nd review for the same drive
            //the exception happens when we link the (Already made) "Bewertung" into schreiben
            // deleting the made row in table bewertung if it can't be linked
            try {
                PreparedStatement ps = connection.prepareStatement(sqlDeleteFromBewertung);
                ps.setInt(1, rating.getBEID());
                ps.executeUpdate();
                //System.out.println("Bewertung deleted");
            } catch (SQLException throwables) {
                //System.out.println("couldn't be deleted");
            }
            return false;
        }
    }

    /**
     * Fetches the database to get all ratings for specific drive.
     *
     * @returns array of type object ratings
     * @autor Ahmed Omran
     */
    public ArrayList<Rating> getDriveRatings(Drive drive) throws StoreException {
        ArrayList<Rating> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select f.fid, s.fahrt, bw.beid, s.bewertung, s.benutzer, bw.textnachricht, bw.erstellungsdatum, bw.rating, benutzer.email from dbp105.bewertung bw INNER JOIN dbp105.schreiben s ON bw.beid = s.bewertung INNER join dbp105.fahrt f ON s.fahrt = f.fid INNER JOIN dbp105.benutzer benutzer ON benutzer.bid = s.benutzer WHERE f.fid = ?");
            ps.setInt(1, drive.getFID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setTextnachricht(rs.getString("textnachricht"));
                rating.setErstellungsdatum(rs.getString("erstellungsdatum"));
                rating.setRating(rs.getInt("rating"));
                rating.setBEID(rs.getInt("beid"));
                rating.setUserEmail(rs.getString("email"));
                result.add(rating);
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return result;
    }

    /**
     * Inserts a new booking in the database
     *
     * @returns true if the booking is saved successfully in the database, otherwise false
     * @autor Ahmed Omran
     */
    public boolean bookDrive(User user, Drive drive) throws RuntimeException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO dbp105.reservieren (kunde, fahrt, anzPlaetze) VALUES (?, ?, ?)");
            //setting place holders
            ps.setInt(1, user.getBID());
            ps.setInt(2, drive.getFID());
            ps.setInt(3, user.getAnplätze());
            ps.executeUpdate();
            System.out.println("I resrved the trip");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Deletes an existing booking in the database
     *
     * @returns true if the booking is deleted successfully from the database, otherwise false
     * @autor Ahmed Omran
     */
    public boolean deleteBooking(User user, Drive drive) throws RuntimeException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM dbp105.reservieren WHERE kunde = ? AND fahrt = ?");
            //setting place holders
            ps.setInt(1, user.getBID());
            ps.setInt(2, drive.getFID());
            ps.executeUpdate();

            PreparedStatement ps2 = connection.prepareStatement("select status from dbp105.fahrt WHERE fid = ?");
            ps2.setInt(1, drive.getFID());
            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                drive.setStatus(rs.getString("status"));
                if (drive.getStatus().equals("geschlossen")) {
                    PreparedStatement ps3 = connection.prepareStatement("update dbp105.fahrt set status = 'offen' WHERE fid = ?");
                    ps3.setInt(1, drive.getFID());
                    ps3.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }



    /**
     * Gets the average rating number for specific trip
     *
     * @returns average float number
     * @autor Ahmed Omran
     */
    public Float getAverageRating(Drive drive) throws RuntimeException {
        float avgRating = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("select CAST(AVG(CAST(Rating AS DECIMAL(10,2))) AS DECIMAL(10,2)) from dbp105.schreiben s INNER JOIN dbp105.bewertung b ON s.bewertung = b.beid WHERE s.fahrt = ? GROUP BY fahrt");
            ps.setInt(1, drive.getFID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                avgRating = rs.getFloat(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return avgRating;
    }

}
