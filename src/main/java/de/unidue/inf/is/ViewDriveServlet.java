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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

/**
 * Servlet of the view_drive page which gives the user all trip information also the ability to book or delete
 * an existing booking
 *
 * @autor Ahmed Omran
 */
public class ViewDriveServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pagetitle", "-Fahrt Details");
        ArrayList<Drive> updatedDrive = new ArrayList<>();
        ArrayList<Rating> ratings;
        DriveStore driveStore = new DriveStore();
        RatingStore ratingStore = new RatingStore();
        Drive drive = new Drive();

        //drive.setFID(Integer.parseInt(req.getParameter("FID")));
        drive.setFID(Integer.parseInt(req.getParameter("FID").replaceAll("\\D+", "")));
        updatedDrive.add(driveStore.getDriveInformation(drive));
        ratings = ratingStore.getDriveRatings(drive);
        Float avgRating = ratingStore.getAverageRating(drive);
        System.out.println(avgRating);

        driveStore.complete();
        driveStore.close();
        ratingStore.complete();
        ratingStore.close();

        req.setAttribute("driveInformation", updatedDrive);
        req.setAttribute("icon", updatedDrive.get(0).getIcon());
        req.setAttribute("driveRating", ratings);
        req.setAttribute("avgRating", avgRating);
        req.getRequestDispatcher("viewDrive.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        Drive drive = new Drive();
        String queryString = req.getQueryString();
        RatingStore ratingStore = new RatingStore();
        DriveStore driveStore = new DriveStore();
        Timestamp currentDateTime = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        System.out.println(currentDateTime);

        drive.setFID(Integer.parseInt(req.getParameter("FID").replaceAll("\\D+", "")));


        if (queryString.substring(queryString.lastIndexOf("=") + 1).equals("delete")) {
            if(driveStore.getDriveInformation(drive).getBID() != user.getBID()){
                ratingStore.close();
                driveStore.close();
                MessageServlet messageServlet = new MessageServlet("Nur der Reiseanbieter kann sie löschen", "Fahler", false);
                messageServlet.doGet(req, resp);
            }
            if (driveStore.deleteDrive(user, drive)) {
                ratingStore.complete();
                ratingStore.close();
                driveStore.complete();
                driveStore.close();
                MessageServlet messageServlet = new MessageServlet("Die Reise ist erfolgreich gelöscht worden!", "Erfolgreich gelöscht", true);
                messageServlet.doGet(req, resp);
            } else {
                ratingStore.close();
                driveStore.close();
                MessageServlet messageServlet = new MessageServlet("Ungebuchte Reise kann nicht gelöscht werden!", "Fehler", false);
                messageServlet.doGet(req, resp);
            }
            doGet(req, resp);

        } else if (queryString.substring(queryString.lastIndexOf("=") + 1).equals("book")) {

            user.setAnplätze(Integer.parseInt(req.getParameter("anPlätze")));
            drive = driveStore.getDriveInformation(drive);

            if (drive.getFreiplätze() < user.getAnplätze()) {
                ratingStore.close();
                driveStore.close();
                MessageServlet messageServlet = new MessageServlet("Es gibt nicht genügend Plätze zu buchen!", "Fehler", false);
                messageServlet.doGet(req, resp);

            }else if(currentDateTime.after(drive.getFahrtdatumzeit())){
                ratingStore.close();
                driveStore.close();
                MessageServlet messageServlet = new MessageServlet("Die Reise ist schon vorbei", "Fehler", false);
                messageServlet.doGet(req, resp);
            }
            else {
                if (ratingStore.bookDrive(user, drive)) {
                    ratingStore.complete();
                    ratingStore.close();
                    driveStore.complete();
                    driveStore.close();
                    MessageServlet messageServlet = new MessageServlet("Die Reise ist erfolgreich gebucht worden!", "Erfolgreich gebucht", true);
                    messageServlet.doGet(req, resp);

                } else {
                    ratingStore.close();
                    driveStore.close();
                    MessageServlet messageServlet = new MessageServlet("Die Reise ist bereit gebucht!", "Fehler", false);
                    messageServlet.doGet(req, resp);

                }
            }
            doGet(req, resp);
        }
        ratingStore.close();
        driveStore.close();
    }
}
