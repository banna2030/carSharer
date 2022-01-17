package de.unidue.inf.is;

import de.unidue.inf.is.stores.ReviewStore;
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
    ReviewStore reviewStore = new ReviewStore();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("newRating.ftl").forward(req, resp);
        String textReview;
        int rating;
        if(req.getParameter("review")!="" && req.getParameter("rating")!=null ) {
            textReview = req.getParameter("review");
            rating = Integer.parseInt(req.getParameter("rating"));
            reviewStore.setReview(textReview);
            reviewStore.setRating(rating);
            reviewStore.setDateAndTime(getCurrentDateAndTime());
            reviewStore.sendReview();
            reviewStore.complete();
            reviewStore.close();
           // System.out.println("sent to store!");
        }
        //testing values passing:
       // System.out.println("\n"+ req.getParameter("review") + " "+ req.getParameter("rating") + "\n");
    }
    private String getCurrentDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.000000");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }


}