package de.unidue.inf.is;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet of the message page which gives the user, positive or negative feedback depending on the action
 *
 * @autor Ahmed Omran
 */
public class MessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String message;
    private String pageTitle;
    private boolean value;

    public MessageServlet(String msg, String title, boolean vl) {
        this.message = msg;
        this.pageTitle = title;
        this.value = vl;
    }

    public MessageServlet(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", this.message);
        req.setAttribute("value", this.value);
        req.setAttribute("pagetitle", this.pageTitle);
        req.getRequestDispatcher("feedbackMessage.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
