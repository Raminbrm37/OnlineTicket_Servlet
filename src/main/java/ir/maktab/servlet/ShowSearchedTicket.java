package ir.maktab.servlet;

import ir.maktab.dao.TicketDao;
import ir.maktab.dao.UserDao;
import ir.maktab.model.Ticket;
import ir.maktab.model.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ir.maktab.util.JpaUtil.getEntityManagerFactory;

@WebServlet(name = "ShowSearchedTicket", urlPatterns = "/searchedTicket")
public class ShowSearchedTicket extends HttpServlet {
    private EntityManager entityManager = getEntityManagerFactory().createEntityManager();
    private TicketDao ticketDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        ticketDao = new TicketDao(entityManager);
        userDao = new UserDao(entityManager);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean findCookie = false;
        User user = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("authentication")) {
                    String val = cookie.getValue();

                    if (LoginServlet.map.containsKey(val)) {
                        user = userDao.detectUser(((User) (LoginServlet.map.get(val))).getUserName());

                        findCookie = true;
                    }
                }
            }

        }

        String origin = request.getParameter("origin");
        String destination = request.getParameter("maghsad");
        String dateOfDeparture = request.getParameter("tarikh");

        searchTicket(origin, destination, dateOfDeparture, user, request, response);


        //   request.setAttribute("tickets", list);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void searchTicket(String origin, String destination, String dateOfDeparture
            , User user, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("user", user);
        if (origin != null && destination != null && dateOfDeparture != null && !dateOfDeparture.trim().equals("")
                && !origin.trim().equals("") && !destination.trim().equals("")) {

            List<Ticket> list = sortedByTime(ticketDao.searchTicket(origin, destination, dateOfDeparture));


            if (list.isEmpty()) {
                request.setAttribute("origin", origin);
                request.setAttribute("destination", destination);
                request.setAttribute("dateOfDeparture", dateOfDeparture);
                request.getRequestDispatcher("/ticketNotFound.jsp").forward(request, response);
            } else {
                request.setAttribute("tickets", list);
                request.getRequestDispatcher("/ticketThree.jsp").forward(request, response);
            }


        } else if (origin != null && destination != null
                && !destination.trim().equals("") && !origin.trim().equals("")) {

            List<Ticket> list = sortedByTime(ticketDao.searchTicket(origin, destination));
            //  request.setAttribute("user", user);

            if (list.isEmpty()) {
                request.setAttribute("origin", origin);
                request.setAttribute("destination", destination);

                request.getRequestDispatcher("/ticketNotFound.jsp").forward(request, response);
            } else {
                request.setAttribute("tickets", list);
                request.getRequestDispatcher("/ticketTwo.jsp").forward(request, response);
            }

        } else if (origin != null && !origin.trim().equals("")) {

            List<Ticket> list = sortedByTime(ticketDao.searchTicket(origin));
            //   request.setAttribute("user", user);

            if (list.isEmpty()) {
                request.setAttribute("origin", origin);

                request.getRequestDispatcher("/ticketNotFound.jsp").forward(request, response);
            } else {
                request.setAttribute("tickets", list);
                request.getRequestDispatcher("/ticketOne.jsp").forward(request, response);
            }
        } else if (destination != null && dateOfDeparture != null
                && !destination.trim().equals("") && !dateOfDeparture.trim().equals("")) {
            List<Ticket> list = sortedByTime(ticketDao.searchTicketType2(destination, dateOfDeparture));
            //  request.setAttribute("user", user);

            if (list.isEmpty()) {
                request.setAttribute("destination", destination);
                request.setAttribute("dateOfDeparture", dateOfDeparture);

                request.getRequestDispatcher("/ticketNotFound.jsp").forward(request, response);
            } else {
                request.setAttribute("tickets", list);
                request.getRequestDispatcher("/ticketFour.jsp").forward(request, response);
            }


        }else if (origin != null && dateOfDeparture != null
                && !origin.trim().equals("") && !dateOfDeparture.trim().equals("")) {
            List<Ticket> list = sortedByTime(ticketDao.searchTicketType3(origin,dateOfDeparture));
            //  request.setAttribute("user", user);

            if (list.isEmpty()) {
                request.setAttribute("origin", origin);
                request.setAttribute("dateOfDeparture", dateOfDeparture);

                request.getRequestDispatcher("/ticketNotFound.jsp").forward(request, response);
            } else {
                request.setAttribute("tickets", list);
                request.getRequestDispatcher("/ticketSeven.jsp").forward(request, response);
            }


        } else if (dateOfDeparture != null && !dateOfDeparture.trim().equals("")) {

            List<Ticket> list = sortedByTime(ticketDao.searchTicketType2(dateOfDeparture));
            //  request.setAttribute("user", user);

            if (list.isEmpty()) {
                request.setAttribute("dateOfDeparture", dateOfDeparture);

                request.getRequestDispatcher("/ticketNotFound.jsp").forward(request, response);
            } else {
                request.setAttribute("tickets", list);
                request.getRequestDispatcher("/ticketFive.jsp").forward(request, response);
            }

        } else if (destination != null && !destination.trim().equals("")) {

            List<Ticket> list = sortedByTime(ticketDao.searchTicketType3(destination));
            //   request.setAttribute("user", user);

            if (list.isEmpty()) {
                request.setAttribute("destination", destination);

                request.getRequestDispatcher("/ticketNotFound.jsp").forward(request, response);
            } else {
                request.setAttribute("tickets", list);
                request.getRequestDispatcher("/ticketSix.jsp").forward(request, response);
            }

        } else {

            List<Ticket> list = sortedByTime(ticketDao.loadAllTicket());
            // request.setAttribute("user", user);

            if (list.isEmpty()) {

                request.getRequestDispatcher("/ticketNotFound.jsp").forward(request, response);
            } else {
                request.setAttribute("tickets", list);
                request.getRequestDispatcher("/ticketAll.jsp").forward(request, response);
            }
        }
    }

    public List<Ticket> sortedByTime(List<Ticket> list) {

        return list.stream()
                .sorted(Comparator.comparing(Ticket::getTimeOfDeparture))
                .collect(Collectors.toList());
    }
}
