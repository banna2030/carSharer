package de.unidue.inf.is;

import de.unidue.inf.is.domain.Rating;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.RatingStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * takes the end user inputs from the page newRating.ftl
 * and pass it to the ReviewStore where it gets stored in the database
 *
 * @autor Osama Elsafty
 */
public class NewRatingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // fid will be set in doGet and used later in doPost
    private int fid;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("in do post");
        RatingStore ratingStore = new RatingStore();
        User user = new User();
        Rating rating = new Rating();
        boolean state = false;

        if (req.getParameter("review") != "" && req.getParameter("rating") != null) {
//            System.out.println("passed empty check... adding rating");
            rating.setRating(Integer.parseInt(req.getParameter("rating")));
            rating.setTextnachricht(req.getParameter("review"));
            rating.setErstellungsdatum(getCurrentDateAndTime());
            state = ratingStore.sendReview(user.getBID(), fid, rating);
            if (state == false) {
//                System.out.println("didn't pass double rating check... directing to error page");
                ratingStore.close();
                MessageServlet messageServlet = new MessageServlet("Sie dürfen dieselbe Fahrt nicht zwei Mal bewerten", "zweite Bewertung", false);
                messageServlet.doGet(req, resp);
            }else {
                ratingStore.complete();
                ratingStore.close();
                ViewDriveServlet viewDriveServlet = new ViewDriveServlet();
                viewDriveServlet.doGet(req,resp);
            }
        }
        else{
//            System.out.println("didn't pass empty check... directing to error page");
            ratingStore.close();
            MessageServlet messageServlet = new MessageServlet("Eingaben dürfen nicht leer sein", "leere Eingaben", false);
            messageServlet.doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pagetitle", "-Fahrt bewerten");

        if (req.getParameter("FID") != null) {
            fid = Integer.parseInt(req.getParameter("FID"));
        }
        req.setAttribute("fid",fid);
        req.getRequestDispatcher("newRating.ftl").forward(req, resp);
    }

    //gets the current date and time as string in the wanted timestamp format
    private String getCurrentDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }


}