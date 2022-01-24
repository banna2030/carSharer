package de.unidue.inf.is.stores;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;


public final class UserStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public UserStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    /**
     * a function to get the driver (user) that has the best average rating on database
     *
     * @return an instance from class User with the best driver data
     * @autor Osama Elsafty
     */
    public User getBestDriver() {

        String bestDriverSQL = "SELECT * FROM( dbp105.benutzer b INNER JOIN (SELECT anbieter,avgRating FROM( (SELECT anbieter, AVG(rating) AS avgRating FROM( SELECT f.fid, f.anbieter, b.rating FROM( dbp105.fahrt f INNER JOIN dbp105.schreiben s ON f.fid= s.fahrt) INNER JOIN dbp105.bewertung b ON b.beid= s.bewertung ) GROUP BY anbieter) INNER JOIN (SELECT MAX(avgRating) as maxAvgRating FROM ( SELECT anbieter, AVG(rating) AS avgRating FROM( SELECT f.fid, f.anbieter, b.rating FROM( dbp105.fahrt f INNER JOIN dbp105.schreiben s ON f.fid= s.fahrt ) INNER JOIN dbp105.bewertung b ON b.beid= s.bewertung ) GROUP BY anbieter ) ) ON avgRating=maxAvgRating ) )ON b.bid=anbieter )";

        User bestDriver = new User();
        try {
            PreparedStatement ps = connection.prepareStatement(bestDriverSQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bestDriver.setBID(rs.getInt("bid"));
                bestDriver.setName(rs.getString("name"));
                bestDriver.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bestDriver;
    }

    /**
     * a function to get the average rating of the best driver
     *
     * @return a float represents the average rating
     * @autor Osama Elsafty
     */
    public float getAverageRating(User driver) {

        String bestDriverSQL = "SELECT AVG(rating) as AVGRATING FROM (dbp105.fahrt f INNER JOIN dbp105.schreiben s ON f.fid= s.fahrt ) INNER JOIN dbp105.bewertung b ON b.beid = s.bewertung where anbieter = ?";
        float averageRating = -1;
        try {
            PreparedStatement ps = connection.prepareStatement(bestDriverSQL);
            ps.setInt(1, driver.getBID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                averageRating = rs.getFloat("AVGRATING");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageRating;
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