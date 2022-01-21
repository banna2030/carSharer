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
     * @autor Team-work
     */
    //connection variables and functions
    private Connection connection;
    private boolean complete;
    public RatingStore() throws StoreException{
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
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

    /**
     * makes a new review "bewertung" and tries to link it to the current user and the drive (in table "screiben"
     * if the linking failed (happens if user has already reviewed the same drive before
     * the function deletes the made review from table "bewertung"
     * @returns true if adding and linking is successful, false otherwise
     * @autor Osama Elsafty
     */
    public boolean sendReview(int bid, int fid, String review, int rating, String dateAndTime) throws RuntimeException{
        final String sqlGetBEID="SELECT beid FROM dbp105.bewertung " +
                "WHERE textnachricht LIKE ? AND erstellungsdatum=? AND rating=?";
        // like is used for CLOB attributes
        final String SqlInsertToSchreiben ="INSERT INTO dbp105.schreiben " +
                "(benutzer, fahrt, bewertung) VALUES (?,?,?)";
        final String SqlInsertToBewertung = "INSERT INTO dbp105.bewertung " +
                "(textnachricht, erstellungsdatum, rating) VALUES " +
                "(?,?,?)";
        final String sqlDeleteFromBewertung = "DELETE FROM dbp105.bewertung WHERE beid=?";

//        System.out.println("recived values \n bid: "+bid + "\n fid: "+ fid + "\nreview: " + review + "\nrating: " + rating + "\ndate and time: "+ dateAndTime);

        int beid=-1;
        try {
            PreparedStatement ps = connection.prepareStatement(SqlInsertToBewertung);
            //setting place holders
            ps.setString(1, review);
            ps.setString(2, dateAndTime);
            ps.setInt(3, rating);
            ps.executeUpdate();
            System.out.println("inserted to bewertung");

            //linking to the user and the drive
               //getting the id of the the newly added review
            PreparedStatement ps2 = connection.prepareStatement(sqlGetBEID);
            ps2.setString(1, review);
            ps2.setString(2, dateAndTime);
            ps2.setInt(3, rating);
            ResultSet rs2= ps2.executeQuery();

            if (rs2.next()) {
                beid = rs2.getInt("beid");
            }

            System.out.println("got the value of beid: " + beid);

            //saving to table "schreiben"
            PreparedStatement ps3 = connection.prepareStatement(SqlInsertToSchreiben);
            ps3.setInt(1,bid);
            ps3.setInt(2,fid);
            ps3.setInt(3,beid);
            System.out.println("BID: "+ bid + "\nfid" + fid + "\nbeid"+ beid);
            ps3.executeUpdate();
            System.out.println("inserted to schreiben");
            return true;

        } catch (SQLException e) {
            //exception happens when user tries to add 2nd review for the same drive
            //the exception happens when we link the (Already made) "Bewertung" into schreiben
            // deleting the made row in table bewertung if it can't be linked
            try {
                PreparedStatement ps = connection.prepareStatement(sqlDeleteFromBewertung);
                ps.setInt(1, beid);
                ps.executeUpdate();
                System.out.println("Bewertung deleted");
            } catch (SQLException throwables) {
                System.out.println("couldn't be deleted");
            }

            return false;
        }
    }

    public ArrayList<Rating> getDriveRatings(Drive drive) throws StoreException {
        ArrayList<Rating> result = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select f.fid, s.fahrt, bw.beid, s.bewertung, s.benutzer, bw.textnachricht, bw.erstellungsdatum, bw.rating from dbp105.bewertung bw INNER JOIN dbp105.schreiben s ON bw.beid = s.bewertung INNER join dbp105.fahrt f ON s.fahrt = f.fid WHERE f.fid = ?");
            ps.setInt(1, drive.getFID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rating rating = new Rating();
                rating.setTextnachricht(rs.getString("textnachricht"));
                rating.setErstellungsdatum(rs.getString("erstellungsdatum"));
                rating.setRating(rs.getInt("rating"));
                result.add(rating);
            }
        } catch (SQLException e) {
            throw new StoreException(e);
        }
        return result;
    }

    public boolean bookDrive(User user, Drive drive) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO dbp105.reservieren (kunde, fahrt, anzPlaetze) VALUES (?, ?, ?)");
            //setting place holders
            ps.setInt(1, user.getBID());
            ps.setInt(2, drive.getFID());
            ps.setInt(3, user.getAnplätze());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteBooking(User user, Drive drive) throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM dbp105.reservieren WHERE kunde = ? AND fahrt = ?");
            //setting place holders
            ps.setInt(1, user.getBID());
            ps.setInt(2, drive.getFID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
