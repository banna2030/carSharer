package de.unidue.inf.is.stores;

import de.unidue.inf.is.utils.DBUtil;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewStore implements Closeable {
    /**
     * inserting new review to the database with the parameters passed from NewRatingServlet
     * @autor Osama Elsafty
     */
    //connection variables and functions
    private Connection connection;
    private boolean complete;
    public ReviewStore() throws StoreException{
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

}
