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
import java.util.Date;

public class NewDriveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("newDrive.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        int capacity = Integer.parseInt(req.getParameter("capacity"));
        float cost = Float.parseFloat(req.getParameter("cost"));
        int transportmittel = Integer.parseInt(req.getParameter("Transportmittel"));
        String fahrtdatum = req.getParameter("Fahrtdatum");

        String description = req.getParameter("description");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"),
                sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = null;
        try {
            parse = sdf.parse(fahrtdatum.replace("T", " "));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp dateTime = Timestamp.valueOf(sdf2.format(parse));


        System.out.println("============================================================" + dateTime);
        Drive newDrive = new Drive();
        newDrive.setStartort(from);
        newDrive.setZielort(to);
        newDrive.setMaxplätze(capacity);
        newDrive.setFahrtkosten(cost);
        newDrive.setTransportmittel(transportmittel);
        newDrive.setFahrtdatumzeit(dateTime);
        newDrive.setBeschreibung(description);

        DriveStore store = new DriveStore();
        if (store.checkForLicense(newDrive) && store.checkForDate(newDrive)) {
            store.storeNewDrive(newDrive);
            MessageServlet messageServlet = new MessageServlet("Fahrt erfolgreich erstellt!", "Erfolgreich erstellten", true);
            messageServlet.doGet(req, resp);
        } else {
            MessageServlet messageServlet = new MessageServlet("Sie dürfen keine Fahrt erstellen ohne Erlaubnis zu haben/ falsch Datum", "keine Erlaubnis", false);
            messageServlet.doGet(req, resp);
        }
        store.complete();
        store.close();
        doGet(req, resp);
    }
}
