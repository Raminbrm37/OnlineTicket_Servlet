package ir.maktab.servlet;

import ir.maktab.dao.UserDao;
import ir.maktab.model.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static ir.maktab.util.JpaUtil.getEntityManagerFactory;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    public static Map<String, User> map = new ConcurrentHashMap<>();
    private EntityManager entityManager = getEntityManagerFactory().createEntityManager();
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao(entityManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean findCookie = false;
        User user = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("authentication")) {

                    String val = cookie.getValue();

                    if (map.containsKey(val)) {
                        user = userDao.detectUser(((User) (map.get(val))).getUserName());
                        findCookie = true;
                    }
                }
            }
        }
        if (findCookie) {


            response.sendRedirect("searchticket.jsp");
        } else {

            request.getRequestDispatcher("login.html").include(request, response);

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        User user = userDao.detectUser(userName);


        if (user == null) {
            response.sendRedirect("/usernotfound.html");
        } else {
            if (user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("secure", user);
                request.setAttribute("user", user);
                String randomValue = UUID.randomUUID().toString();
                map.put(randomValue, user);
                Cookie cookie1 = new Cookie("Test", user.getUserName());
                cookie1.setMaxAge(10000);
                response.addCookie(cookie1);

                Cookie cookie = new Cookie("authentication", randomValue);

                cookie.setMaxAge(5555);
                response.addCookie(cookie);
                //  request.getRequestDispatcher("/searchticket.jsp").forward(request, response);
                response.sendRedirect("searchticket.jsp");

            } else {
                response.sendRedirect("incorrectpassword.html");
            }

        }


    }

}
