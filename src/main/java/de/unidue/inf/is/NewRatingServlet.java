package de.unidue.inf.is;

import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.RatingStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewRatingServlet extends HttpServlet {
    /**
     * takes the end user inputs from the page newRating.ftl
     * and pass it to the ReviewStore where it gets stored in the database
     * @autor Osama Elsafty
     */
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in doPost");
        RatingStore ratingStore = new RatingStore();
        String textReview;
        boolean state = false;
        int rating;
        System.out.println("received fid: "+fid);
        int currentUserId = User.loggedInBID;
        if(req.getParameter("review")!="" && req.getParameter("rating")!=null ) {
            textReview = req.getParameter("review");
            rating = Integer.parseInt(req.getParameter("rating"));
            state = ratingStore.sendReview(currentUserId,fid,textReview,rating,getCurrentDateAndTime());
            ratingStore.complete();
            ratingStore.close();
            // System.out.println("sent to store!");
        }
        if (state == true) {
            MessageServlet messageServlet = new MessageServlet("Danke für Ihre Bewertung!","Erfolgreich Bewertung", true);
            messageServlet.doGet(req,resp);
        } else{
            MessageServlet messageServlet = new MessageServlet("Sie dürfen dieselbe Fahrt nicht zwei Mal bewerten","zweite Bewertung", false);
            messageServlet.doGet(req,resp);
        }
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in doGet");
        req.getRequestDispatcher("newRating.ftl").forward(req, resp);
        if(req.getParameter("FID")!=null){
            fid=Integer.parseInt(req.getParameter("FID"));
        }

        //testing values passing:
       // System.out.println("\n"+ req.getParameter("review") + " "+ req.getParameter("rating") + "\n");
    }
    private String getCurrentDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.000000");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    private int fid;


}