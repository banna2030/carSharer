package de.unidue.inf.is;

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.stores.DriveStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewMainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DriveStore drive = new DriveStore();
        ArrayList<Drive> listOfReservedDrives = drive.getReservedDrives();
        ArrayList<Drive> listOfOpenDrives = drive.getOpenDrives();

        //listOfReservedDrives.stream().forEach(System.out::println);
        drive.complete();
        drive.close();
       req.setAttribute("reservedDrive", listOfReservedDrives);
        req.setAttribute("openDrive", listOfOpenDrives);
       req.getRequestDispatcher("/viewMain.ftl").forward(req, resp);
    }
}
