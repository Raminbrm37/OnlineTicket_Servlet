<%@ page import="ir.maktab.model.User" %>
<%@ page import="ir.maktab.model.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="ir.maktab.dao.UserDao" %>
<%@ page import="static ir.maktab.util.JpaUtil.getEntityManagerFactory" %>
<%@ page import="ir.maktab.servlet.LoginServlet" %><%--
  Created by IntelliJ IDEA.
  User: RaMin
  Date: 1/25/2021
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Search Ticket</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
<%
    EntityManager entityManager = getEntityManagerFactory().createEntityManager();
    UserDao userDao = new UserDao(entityManager);

    User user = null;
    if (request.getCookies() != null) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("authentication")) {
                String val = cookie.getValue();

                if (LoginServlet.map.containsKey(val)) {

                    user = userDao.detectUser(((User) (LoginServlet.map.get(val))).getUserName());
                    //  request.setAttribute("user", user);

                }
            }
        }

    }

%>
<div class="jumbotron text-center">
    <h1><%= user.getFirstName()%>
    </h1>

</div>
<div class="container" style="align-content: center">
    <ul class="nav nav-tabs">
        <li class="active"><a href="profile"> <span class="glyphicon glyphicon-user btn-lg"></span></a></li>
        <li><a href="profile"> <span class="glyphicon glyphicon-refresh btn-lg"></span></a></li>
        <li><a href="searchticket.jsp"> <span class="glyphicon glyphicon-Search btn-lg"></span></a></li>
        <li><a href="welcome.html"> <span class="glyphicon glyphicon-log-out btn-lg"></span></a></li>

    </ul>



<table class="table table-bordered" style="text-align: center">
    <h2>List Of Ticket</h2>
    <thead>
    <tr>
        <td>Ticket Id</td>

        <td>Tarikhe harekat</td>

        <td >Action</td>

    </tr>
    </thead>
    <tbody>
    <% List<Ticket> ticketList = user.getTickets();
        for (Ticket ticket : ticketList) {
    %>

    <tr>
        <td><%=ticket.getTicketId()   %>
        </td>
        <td><%=ticket.getDateOfDeparture()   %>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/profile?action=view&ticketId=<%=ticket.getId()%>&userId=<%=((User)request.getAttribute("user")).getId()%>">
                <span class="glyphicon glyphicon-folder-open"></span></a></td>

    </tr>
    <%
        }%>
    </tbody>
</table>
</div>
</body>
</html>
