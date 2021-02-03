<%@ page import="ir.maktab.model.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="jdk.nashorn.internal.ir.debug.JSONWriter" %>
<%@ page import="ir.maktab.model.User" %><%--
  Created by IntelliJ IDEA.
  User: RaMin
  Date: 1/27/2021
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello
<%=((User) request.getAttribute("user")).getFirstName()%>
<%
    if (request.getAttribute("origin") != null && request.getAttribute("destination") != null && request.getAttribute("dateOfDeparture") != null && !request.getAttribute("dateOfDeparture").equals("")
            && !request.getAttribute("origin").equals("") && !request.getAttribute("destination").equals("")) {
%>


Not Found Ticket For Origin: <%=request.getAttribute("origin")%> To Destination : <%=request.getAttribute("destination")%>
 At Date :<%=request.getAttribute("dateOfDeparture")%>
<%
} else if (request.getAttribute("origin") != null && request.getAttribute("destination") != null
        && !request.getAttribute("origin").equals("") && !request.getAttribute("destination").equals("")) {

%>
Not Found Ticket For Origin : <%=request.getAttribute("origin")%> To Destionation : <%=request.getAttribute("destination")%>
<%
} else if (request.getAttribute("destination") != null && request.getAttribute("dateOfDeparture") != null
        && !request.getAttribute("destination").equals("") && !request.getAttribute("dateOfDeparture").equals("")) {

%>
Not Found Ticket For Destination: <%=request.getAttribute("destination")%> At Date : <%=request.getAttribute("dateOfDeparture")%>

<%
} else if (request.getAttribute("origin") != null
        && !request.getAttribute("origin").equals("")) {

%>

Not Found Ticket For Origin: <%=request.getAttribute("origin")%>

<%
} else if (request.getAttribute("destination") != null
        && !request.getAttribute("destination").equals("")) {

%>

Not Found Ticket For Destination: <%=request.getAttribute("destination")%>
<%
} else if (request.getAttribute("dateOfDeparture") != null
        && !request.getAttribute("dateOfDeparture").equals("")) {

%>

Not Found Ticket For At Date : <%=request.getAttribute("dateOfDeparture")%>

<%
} else {

%>
No Ticket For Selling!
<%
    }
%>.

<a href="login">Try Search!</a>


</body>
</html>
