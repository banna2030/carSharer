package de.unidue.inf.is;

import com.sun.org.apache.bcel.internal.generic.InstructionListObserver;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ArrayList<Drive> listOfSearchDrives = new ArrayList<Drive>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pagetitle", "-Fahrt suchen");
        req.setAttribute("searchDrive", listOfSearchDrives);
        req.getRequestDispatcher("viewSearch.ftl").forward(req, resp);
        listOfSearchDrives.clear();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        listOfSearchDrives.clear();

        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String fahrtdatum = req.getParameter("Fahrtdatum");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"),
                sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = null;
        try {
            parse = sdf.parse(fahrtdatum.replace("T", " "));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp dateTime = Timestamp.valueOf(sdf2.format(parse));

        System.out.println("--------------------------------------" + dateTime);
        System.out.println(from + to + fahrtdatum);

        DriveStore store = new DriveStore();

        Drive search = new Drive();
        search.setStartort(from);
        search.setZielort(to);
        search.setFahrtdatumzeit(dateTime);

        if (from == "" || to == "") {
            try {
                store.complete();
                store.close();
                MessageServlet messageServlet = new MessageServlet("Bitte Start- oder Zielort eingeben!", "Leer Textuelle", false);
                messageServlet.doGet(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            listOfSearchDrives = store.getSearchDrives(search);

            while (listOfSearchDrives.size() == 0) {
                try {
                    store.complete();
                    store.close();
                    MessageServlet messageServlet = new MessageServlet("Es gibt keine Ergebnisse! versuch noch mal!", "keinen Ergebnissen", false);
                    messageServlet.doGet(req, resp);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
            // System.out.println(listOfSearchDrives.get(0).getStartort());

            store.complete();
            store.close();
        }
        doGet(req, resp);
    }
}
