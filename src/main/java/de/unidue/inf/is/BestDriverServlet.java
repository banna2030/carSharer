package de.unidue.inf.is;

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.DriveStore;
import de.unidue.inf.is.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class BestDriverServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore userStore = new UserStore();
        User bestDriver = userStore.getBestDriver();
//        System.out.println("best driver id: " + bestDriver.getBID());
        float averageRating = userStore.getAverageRating(bestDriver);
//        System.out.println("in doGet \n Email:" + bestDriver.getEmail() + "\nAVGRT: "+ averageRagting);
        userStore.complete();
        userStore.close();
        //setting best driver data
        req.setAttribute("driverName", bestDriver.getName());
        req.setAttribute("averageRating", String.format("%.2f", averageRating));

        //setting his open drives
        DriveStore driveStore = new DriveStore();
        ArrayList<Drive> listOfBestDriverOpenDrives;
        listOfBestDriverOpenDrives = driveStore.getOpenDrives(bestDriver);
        req.setAttribute("bestDriverOpenDrives", listOfBestDriverOpenDrives);
        driveStore.complete();
        driveStore.close();
        req.getRequestDispatcher("bestDriver.ftl").forward(req, resp);
    }
}
