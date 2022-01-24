package de.unidue.inf.is;

import de.unidue.inf.is.domain.Drive;
import de.unidue.inf.is.stores.DriveStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ArrayList<Drive> listOfSearchDrives= new ArrayList<Drive>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("searchDrive",listOfSearchDrives);

        req.getRequestDispatcher("viewSearch.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to =req.getParameter("to");
        String fahrtdatum =req.getParameter("Fahrtdatum");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"),
                sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = null;
        try {
            parse =  sdf.parse(fahrtdatum.replace("T", " "));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp dateTime = Timestamp.valueOf(sdf2.format(parse));

        System.out.println("--------------------------------------"+dateTime);
        System.out.println(from + to+fahrtdatum);

        DriveStore store = new DriveStore();

        Drive search= new Drive();
        search.setStartort(from);
        search.setZielort(to);
        search.setFahrtdatumzeit(dateTime);



         listOfSearchDrives = store.getSearchDrives(search);
        System.out.println(listOfSearchDrives.get(0).getStartort());

        req.setAttribute("searchDrive", listOfSearchDrives);

        store.complete();
        store.close();

        doGet(req, resp);
    }
}
