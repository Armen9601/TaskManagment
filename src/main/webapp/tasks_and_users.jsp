<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.07.2021
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<a href="/logout" style="float: right">Logout</a>

<% List<User> users = (List<User>) request.getAttribute("allUsers");%>
<table class="styled-table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <% for (User user1 : users) { %>
    <tr>
        <td><%= user1.getId() %>
        </td>
        <td><%=user1.getName()%>
        </td>
        <td><%=user1.getSurName()%>
        </td>
        <td><%=user1.getEmail()%>
        </td>
        <td>
            <form action="/delete_user" method="get" style="display: inline-block"><input type="hidden" name="id" value="<%=user1.getId()%>">
                <input type="submit" value="delete">
            </form>
    <% } %>
    <!-- and so on... -->
    </tbody>
</table>

</body>
</html>
