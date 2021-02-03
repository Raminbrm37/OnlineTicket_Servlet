package ir.maktab.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "StaticPageFilter", urlPatterns = {"/deleteTicket.jsp", "/finalBuyTicket.jsp"
       , "/profile.jsp", "/searchTicket", "/test.jsp", "/ticketAll.jsp",
        "/ticketAll.jsp", "/ticketOne.jsp", "/ticketTwo.jsp", "/ticketThree.jsp", "/ticketFour.jsp",
        "/ticketFive.jsp", "/ticketSix.jsp", "/ticketValidation.jsp", "/viewTicket.jsp"})
public class StaticPageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setContentType("text/html");
        boolean findCookie = false;

        if (request.getSession() != null && request.getSession().getAttribute("secure") != null) {

            chain.doFilter(request, response);
        } else {
            out.println("PLZ LOGIN");
            request.getRequestDispatcher("login.html").include(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
