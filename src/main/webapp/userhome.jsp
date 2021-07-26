<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>
<%@page import="servlet.UserHomeServlet" %>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.07.2021
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
</head>
<style>
    .styled-table {
        border-collapse: collapse;
        margin: 25px 0;
        font-size: 0.9em;
        font-family: sans-serif;
        min-width: 400px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    }

    .styled-table thead tr {
        background-color: #009879;
        color: #ffffff;
        text-align: left;
    }

    .styled-table th,
    .styled-table td {
        padding: 12px 15px;
    }

    .styled-table tbody tr {
        border-bottom: 1px solid #dddddd;
    }

    .styled-table tbody tr:nth-of-type(even) {
        background-color: #f3f3f3;
    }

    .styled-table tbody tr:last-of-type {
        border-bottom: 2px solid #009879;
    }

    .styled-table tbody tr.active-row {
        font-weight: bold;
        color: #009879;
    }
</style>
<body>
<%--<form action="/userhome" method="post">--%>
<%--    <input type="submit" value="see all tasks">--%>
<%--</form>--%>

<a href="/logout" style="float: right">Logout</a>

<%
    List<Task> tasks = (List<Task>) request.getAttribute("allUserTasks");
%>
<% User user = (User) request.getSession().getAttribute("user");%>
<div style="display: inline-block">
<img src="/getImage?picUrl=<%=user.getPicUrl()%>" width="400">
</div>
<div style="display: inline-block">
       <h1> <%=user.getName() + " " + user.getSurName()%><br> </h1>
    <%=user.getBiography()%>

</div>
<table class="styled-table">
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>UserName</th>
        <th>Status</th>
        <th>Deadline</th>
    </tr>
    </thead>
    <tbody>
    <% for (Task task : tasks) { %>
    <tr>
        <td><%= task.getTitle() %>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=task.getStatus()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
    </tr>
    <% } %>
    <!-- and so on... -->
    </tbody>
</table>


</body>
</html>