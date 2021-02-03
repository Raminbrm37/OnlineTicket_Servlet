<%@ page import="ir.maktab.model.Ticket" %>
<%@ page import="ir.maktab.model.User" %><%--
  Created by IntelliJ IDEA.
  User: RaMin
  Date: 1/25/2021
  Time: 12:52 PM
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
    <h1>kharid Ba movafaqiat Anjam shod</h1>
</div>
<div class="container" style="align-content: center">

    <table class="table table-hover" style="text-align: center">
        <tr>
            <td>
                Your buying ticket Done! <%=((Ticket) request.getAttribute("ticket")).getOrigin()%><br>
                Gender: <%=((User) request.getAttribute("user")).getGender()%>
                Name:    <%=((User) request.getAttribute("user")).getFirstName()%>
            </td>
            <td>
                <a href="profile" style="color: green"> <span class="glyphicon glyphicon-user btn-lg"></span></a>
            </td>
        </tr>

    </table>


</div>

</body>
</html>
