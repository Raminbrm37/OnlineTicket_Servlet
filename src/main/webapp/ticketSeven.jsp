<%@ page import="ir.maktab.model.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page import="ir.maktab.model.User" %><%--
  Created by IntelliJ IDEA.
  User: RaMin
  Date: 1/24/2021
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title> Search Ticket</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>


<div class="jumbotron text-center">
    <h2><%=((User) (request.getAttribute("user"))).getFirstName()+ ((User) (request.getAttribute("user"))).getLastName() %></h2>


</div>
<% List<Ticket> list = (List<Ticket>) (request.getAttribute("tickets"));%>
<div class="container" style="align-content: center">
    <ul class="nav nav-tabs">
        <li ><a href="profile"> <span class="glyphicon glyphicon-user btn-lg"></span></a></li>
        <%--        <li><a href="profile"> <span class="glyphicon glyphicon-refresh btn-lg"></span></a></li>--%>
        <li class="active"><a href="searchticket.jsp"> <span class="glyphicon glyphicon-Search btn-lg"></span></a></li>
        <li><a href="welcome.html"> <span class="glyphicon glyphicon-log-out btn-lg"></span></a></li>

    </ul>

    <table class="table table-bordered" style="text-align: center">
        <caption style="text-align: center"><%=list.get(0).getOrigin()+" "+list.get(0).getTimeOfDeparture()%></caption>
        <thead>
        <tr >
            <td>Ticket id</td>
            <td>travel id</td>
<%--            <td>origin</td>--%>
            <td>Destination</td>
<%--            <td>tarikh</td>--%>
            <td>saat</td>
            <td>Action</td>


        </tr>
        </thead>
        <tbody>
            <%
        for (Ticket ticket : list) {
    %>

        <tr>
            <td><%=ticket.getTicketId()   %>
            <td><%=ticket.getTravelId()   %>
<%--            <td><%=ticket.getOrigin()%>--%>
<%--            </td>--%>
            <td><%=ticket.getDestination()   %>
            </td>
<%--            <td><%=ticket.getDateOfDeparture()   %>--%>
            <td><%=ticket.getTimeOfDeparture()   %>
            </td>
            <td><a href="${pageContext.request.contextPath}/profile?action=validateTicket&ticketId=<%=ticket.getId()%>&userId=<%=((User)request.getAttribute("user")).getId()%>"> <span class="glyphicon glyphicon-shopping-cart"></span></a></td>

        </tr>
            <%
        }%>
    </table>
    </tbody>
</div>
</body>
</html>
