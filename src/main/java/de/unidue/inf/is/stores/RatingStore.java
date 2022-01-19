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
     * @autor Osama Elsafty
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


    //Database interaction variables and functions

    private String review;
    private int rating;
    private String dateAndTime;
    private static final String SqlsendReview = "INSERT INTO dbp171.bewertung " +
            "(textnachricht, erstellungsdatum, rating) VALUES " +
            "(?,?,?)";
    //            "('testcode','2022-01-17.11.22.33.123456',3);";
    public void setReview(String review) {
        this.review = review;
       // System.out.println("got a value for review, it is: " + review);
    }
    public void setRating(int rating) {
        this.rating = rating;
       // System.out.println("got a value for rating, it is: " + rating);
    }
    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
       // System.out.println("got a value for dateAndTime, it is: " + dateAndTime);

    }

    public void sendReview() throws RuntimeException{
        try {
            PreparedStatement ps = connection.prepareStatement(SqlsendReview);
            //setting place holders
            ps.setString(1, review);
            ps.setString(2, dateAndTime);
            ps.setInt(3, rating);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
