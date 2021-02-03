package ir.maktab.filter;

import ir.maktab.dao.TicketDao;
import ir.maktab.dao.UserDao;
import ir.maktab.model.User;
import ir.maktab.servlet.LoginServlet;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static ir.maktab.util.JpaUtil.getEntityManagerFactory;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/searchticket.jsp", servletNames = {"ShowSearchedTicket", "ProfileServlet"})

public class LoginFilter implements Filter {
    private EntityManager entityManager = getEntityManagerFactory().createEntityManager();
    private TicketDao ticketDao;
    private UserDao userDao;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setContentType("text/html");
        boolean findCookie = false;
        User user = null;
        String val = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("Test")) {
                    val = cookie.getValue();
                    findCookie = true;
                 user=userDao.detectUser(val);
                }
            }}
         if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("authentication")) {
                   String val1 = cookie.getValue();
                  LoginServlet.map.put(val1,user);
                }
            }
        }


        if (findCookie) {

            chain.doFilter(request, response);
        } else {
            out.println("Plz Login!");
            request.getRequestDispatcher("/login.html").include(request, response);
        }


    }


    public void init(FilterConfig config) throws ServletException {
        ticketDao = new TicketDao(entityManager);
        userDao = new UserDao(entityManager);
    }

}
