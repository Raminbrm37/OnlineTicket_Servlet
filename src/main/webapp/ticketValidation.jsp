<%@ page import="ir.maktab.model.Ticket" %>
<%@ page import="ir.maktab.model.User" %><%--
  Created by IntelliJ IDEA.
  User: RaMin
  Date: 1/25/2021
  Time: 12:15 PM
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
<div class="jumbotron text-center">

</div>
<div class="container" style="align-content: center">


    <%--    <ul class="nav nav-tabs">--%>
    <%--        <li ><a href="profile"> <span class="glyphicon glyphicon-user btn-lg"></span></a></li>--%>
    <%--        &lt;%&ndash;        <li><a href="profile"> <span class="glyphicon glyphicon-refresh btn-lg"></span></a></li>&ndash;%&gt;--%>
    <%--        <li class="active"><a href="searchticket.jsp"> <span class="glyphicon glyphicon-Search btn-lg"></span></a></li>--%>
    <%--        <li><a href="welcome.html"> <span class="glyphicon glyphicon-log-out btn-lg"></span></a></li>--%>

    <%--    </ul>--%>


    <table class="table table-hover" style="text-align: center">
        <caption style="text-align: center">Validation Ticket</caption>
        <thead>
        <tr>
            <td>
                Name
            </td>
            <td>
                Gender
            </td>
            <td>
                Origin
            </td>
        </tr>
        </thead>

        <tbody>
        <tr>
            <td><%=((User) request.getAttribute("user")).getFirstName()%>
            </td>
            <td><%=((User) request.getAttribute("user")).getGender()%>
            </td>
            <td><%=((Ticket) request.getAttribute("ticket")).getOrigin()%>
            </td>
        </tbody>
        </tr>
        <tr>
            <td>
            <td>
                <a href="${pageContext.request.contextPath}/profile?action=acceptTicket&ticketId=<%=((Ticket)request.getAttribute("ticket")).getId()%>&userId=<%=((User)request.getAttribute("user")).getId()%> "style="color: green">
                    <span class="glyphicon glyphicon-ok-sign btn-lg"></span></a></td>
            </td>
            <td><a href="searchticket.jsp" style="color: red"> <span class="glyphicon glyphicon-fire btn-lg"></span></a></td>
        </tr>
    </table>

    <%--Your buying ticket Done! <%=((Ticket) request.getAttribute("ticket")).getOrigin()%><br>--%>
    <%--Gender: <%=((User) request.getAttribute("user")).getGender()%>--%>
    <%--Name:    <%=((User) request.getAttribute("user")).getFirstName()%>--%>


</div>
</body>
</html>
