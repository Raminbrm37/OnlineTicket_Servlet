<%@ page import="ir.maktab.model.Ticket" %>
<%@ page import="ir.maktab.model.User" %><%--
  Created by IntelliJ IDEA.
  User: RaMin
  Date: 1/25/2021
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> View Ticket</title>
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




<table class="table table-bordered" style="text-align: center">
<caption style="text-align: center">Result</caption>
    <tr >
        <td>Ticket Id</td>
        <td><%=((Ticket)request.getAttribute("ticket")).getTicketId()   %> </td>
    </tr>
    <tr>
        <td>Full Name </td>
        <td><%=((User)request.getAttribute("user")).getFirstName()+((User)request.getAttribute("user")).getLastName()  %> </td>
    </tr>
      <tr>
        <td>Origin </td>
          <td><%=((Ticket)request.getAttribute("ticket")).getOrigin()   %> </td>
      </tr>
    <tr>
        <td>Destination </td>
        <td><%=((Ticket)request.getAttribute("ticket")).getDestination()   %> </td>
    </tr>
    <tr><td>tarikhe harekat </td>
        <td><%=((Ticket)request.getAttribute("ticket")).getDateOfDeparture()   %> </td>
    </tr>
        <tr>
            <td>sate harekat </td>
            <td><%=((Ticket)request.getAttribute("ticket")).getTimeOfDeparture()   %> </td>
        </tr>
       <tr>
           <td>Travel ID </td>
           <td><%=((Ticket)request.getAttribute("ticket")).getTravelId()   %> </td>
       </tr>

<tr>
    <td >  <a href="profile" style="color: green"> <span class="glyphicon glyphicon-user btn-lg"></span></a></td>
    <td><a href="${pageContext.request.contextPath}/profile?action=delete&ticketId=<%=((Ticket)request.getAttribute("ticket")).getId()%>&userId=<%=((User)request.getAttribute("user")).getId()%>" style="color: red" > <span class="glyphicon glyphicon-fire btn-lg"></span></a></td>
</tr>




</table>
</div>
</body>
</html>
