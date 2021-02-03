<%@ page import="ir.maktab.model.User" %>
<%@ page import="ir.maktab.servlet.LoginServlet" %>
<%@ page import="ir.maktab.dao.UserDao" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="static ir.maktab.util.JpaUtil.getEntityManagerFactory" %><%--
  Created by IntelliJ IDEA.
  User: RaMin
  Date: 1/25/2021
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%--<%@ page  contentType="text/html;"%>--%>

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
    UserDao userDao=new UserDao(entityManager);

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

    }%>
<div class="jumbotron text-center">
    <h1>Welcome <%= user.getFirstName()+user.getLastName() %> to buying Ticket</h1>

</div>
<div class="container" style="align-content: center">
    <ul class="nav nav-tabs">
        <li class="active"><a href="searchticket.jsp"> <span class="glyphicon glyphicon-Search btn-lg"></span></a></li>

        <li ><a href="profile"> <span class="glyphicon glyphicon-user btn-lg"></span></a></li>
        <li ><a href="welcome.html">  <span class="glyphicon glyphicon-log-out btn-lg"></span></a></li>

    </ul>
</div>



<h2  style="text-align: center">Search Ticket</h2>


<form class="form-horizontal" action="searchedTicket" method="post">
    <div class="form-group">
        <label class="control-label col-sm-4" for="Origin">Origin:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="Origin" placeholder="Enter Origin" name="origin">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="Destination">Destination:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="Destination" placeholder="Enter Destination" name="maghsad">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4" for="Date">Date:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="Date" placeholder="Enter Date" name="tarikh">
        </div>
    </div>


    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-10">
            <button type="submit" class="btn btn-success "> <span class="glyphicon glyphicon-Search btn-lg"></span> </button>
            <input hidden name="userId" value="<%=user.getId() %>">
        </div>
    </div>
</form>

</div>

</body>
</html>
