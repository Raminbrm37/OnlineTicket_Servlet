package ir.maktab.servlet;

import ir.maktab.dao.TicketDao;
import ir.maktab.dao.UserDao;
import ir.maktab.model.Ticket;
import ir.maktab.model.User;
import ir.maktab.servlet.LoginServlet;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ir.maktab.util.JpaUtil.getEntityManagerFactory;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    private EntityManager entityManager = getEntityManagerFactory().createEntityManager();
    private TicketDao ticketDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        ticketDao = new TicketDao(entityManager);
        userDao = new UserDao(entityManager);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean findCookie = false;
        User user = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("authentication")) {
                    String val = cookie.getValue();

                    if (LoginServlet.map.containsKey(val)) {
                        user = userDao.detectUser(((User) (LoginServlet.map.get(val))).getUserName());
                        request.setAttribute("user", user);
                        findCookie = true;
                    }
                }
            }

        }
        String userId = request.getParameter("userId");
        String ticketId = request.getParameter("ticketId");
        // String action = request.getParameter("action");
        // User user=userDao.loadById(Integer.parseInt(userId));
        Ticket ticket = ticketDao.loadById(Integer.parseInt(ticketId));
        entityManager.getTransaction().begin();
        ticket.setUser(user);
        userDao.update(user);
        user.getTickets();
        entityManager.getTransaction().commit();
        //  request.setAttribute("ticket",ticket);
        //request.setAttribute("user",user);
        // request.getRequestDispatcher("/finalBuyTicket.jsp").forward(request,response);
        response.sendRedirect("profile");
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  String userId = request.getParameter("userId");
        String ticketId = request.getParameter("ticketId");
        String action = request.getParameter("action");
        //String u=request.getParameter("u_id");
        boolean findCookie = false;
        User user = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("authentication")) {
                    String val = cookie.getValue();

                    if (LoginServlet.map.containsKey(val)) {
                        user = userDao.detectUser(((User) (LoginServlet.map.get(val))).getUserName());
                        request.setAttribute("user", user);
                        findCookie = true;
                    }
                }
            }

        }

        if (action != null) {
//            if (userId == null || ticketId == null || userId.equals("") || ticketId.equals("")) {
//                response.sendRedirect("usernotfound.html");
//                return;
            //  }

            if (action.equals("view")) {
                Ticket ticket = ticketDao.loadById(Integer.parseInt(ticketId));
                //  user = userDao.loadById(Integer.parseInt(userId));
                request.setAttribute("user", user);
                request.setAttribute("ticket", ticket);

                request.getRequestDispatcher("/viewTicket.jsp").forward(request, response);
                return;
            } else if (action.equals("delete")) {
                entityManager.getTransaction().begin();
                Ticket ticket = ticketDao.loadById(Integer.parseInt(ticketId));
                //   user = userDao.loadById(Integer.parseInt(userId));
                user.getTickets().remove(ticket);
                ticket.setUser(null);


                entityManager.getTransaction().commit();
//                List<Ticket> ticketList = user.getTickets();
//                request.setAttribute("ticketList", ticketList);
                //  request.setAttribute("user", user);


                response.sendRedirect("profile");
                //  request.getRequestDispatcher("/profile.jsp").forward(request, response);
                // request.getRequestDispatcher("/").include(request, response);
                return;
            } else if (action.equals("validateTicket")) {

                Ticket ticket = ticketDao.loadById(Integer.parseInt(ticketId));

                request.setAttribute("user", user);
                request.setAttribute("ticket", ticket);
                request.getRequestDispatcher("/ticketValidation.jsp").forward(request, response);
                return;
            } else if (action.equals("acceptTicket")) {
                //  String ticketId = request.getParameter("ticketId");
                //    String userId = request.getParameter("userId");
                // User user = userDao.loadById(Integer.parseInt(userId));
                Ticket ticket = ticketDao.loadById(Integer.parseInt(ticketId));
                entityManager.getTransaction().begin();
                ticket.setUser(user);
                user.getTickets();
                entityManager.getTransaction().commit();
                request.setAttribute("user", user);
                request.setAttribute("ticket", ticket);
                // response.sendRedirect("profile");

                request.getRequestDispatcher("/finalBuyTicket.jsp").forward(request, response);

                return;

            } else if (action.equals("buy")) {
                Ticket ticket = ticketDao.loadById(Integer.parseInt(ticketId));
                entityManager.getTransaction().begin();
                User user1=userDao.loadById(Integer.parseInt(request.getParameter("userId")));
                ticket.setUser(user);
                entityManager.getTransaction().commit();
              request.getRequestDispatcher("profil.jsp").forward(request,response);
                //  response.sendRedirect("profile");
                return;
            }

        }



        request.getRequestDispatcher("/profile.jsp").forward(request, response);
        // response.sendRedirect("/profile");

    }

}
