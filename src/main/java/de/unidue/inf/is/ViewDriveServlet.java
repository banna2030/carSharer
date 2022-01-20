package de.unidue.inf.is;

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.Rating;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.DriveStore;
import de.unidue.inf.is.stores.RatingStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ViewDriveServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Drive> updatedDrive = new ArrayList<>();
        ArrayList<Rating> ratings = new ArrayList<>();
        DriveStore driveStore = new DriveStore();
        RatingStore ratingStore = new RatingStore();
        Drive drive = new Drive();

        drive.setFID(Integer.parseInt(String.valueOf(req.getQueryString().charAt(4))));
        updatedDrive.add(driveStore.getDriveInformation(drive));
        ratings = ratingStore.getDriveRatings(drive);

        driveStore.complete();
        driveStore.close();

        req.setAttribute("driveInformation", updatedDrive);
        req.setAttribute("icon", updatedDrive.get(0).getIcon());
        req.setAttribute("driveRating", ratings);
        req.getRequestDispatcher("viewDrive.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getQueryString());
        System.out.println(req.getQueryString().substring(4,5));
        User user = new User();
        Drive drive = new Drive();
        RatingStore ratingStore = new RatingStore();
        drive.setFID(Integer.parseInt(req.getQueryString().substring(4,5)));
        user.setBID(User.loggedInBID);
        System.out.println(drive.getBID() + drive.getFID());

        if(req.getQueryString().substring(6).equals("delete")){
            System.out.println("I am in delete condition");
            if(ratingStore.deleteBooking(user,drive)){
                MessageServlet messageServlet = new MessageServlet("Die Buchung ist erfolgreich gelöscht worden!","Erfolgreich gelöscht", true);
                messageServlet.doGet(req,resp);
            }else{
                MessageServlet messageServlet = new MessageServlet("Ungebuchte Reise kann nicht gelöscht werden!","Fehler", false);
                messageServlet.doGet(req,resp);
            }
            ratingStore.complete();
            ratingStore.close();
            doGet(req,resp);
        }else if(req.getQueryString().substring(6).equals("book")) {
            System.out.println(user.getAnplätze() + drive.getFID());
            user.setAnplätze(Integer.parseInt(req.getParameter("anPlätze")));
            if(ratingStore.bookDrive(user,drive)){
                MessageServlet messageServlet = new MessageServlet("Die Reise ist erfolgreich gebucht worden!","Erfolgreich gebucht", true);
                messageServlet.doGet(req,resp);
            }else {
                MessageServlet messageServlet = new MessageServlet("Die Reise ist nicht gebucht!","Fehler", false);
                messageServlet.doGet(req,resp);
            }
            ratingStore.complete();
            ratingStore.close();
            doGet(req,resp);
        }
    }
}
