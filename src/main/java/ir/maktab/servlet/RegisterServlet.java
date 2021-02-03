package ir.maktab.servlet;

import ir.maktab.dao.UserDao;
import ir.maktab.model.User;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static ir.maktab.util.JpaUtil.getEntityManagerFactory;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private EntityManager entityManager = getEntityManagerFactory().createEntityManager();
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao(entityManager);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        String firstName = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String nationalCode = request.getParameter("nationalCode");
        String email = request.getParameter("email");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String gender= request.getParameter("gender");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastname);
        user.setNationalCode(nationalCode);
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(password);
        user.setGender(gender);
        userDao.save(user);
        out.write("Registering Done"+firstName);
       request.getRequestDispatcher("/login.html").include(request,response);
       return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
