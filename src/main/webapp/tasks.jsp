<%@ page import="model.Task" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.07.2021
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tasks</title>
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

<%
    List<Task> tasks = (List<Task>) request.getAttribute("allTasks");
%>
<table class="styled-table">
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>UserName</th>
        <th>Status</th>
        <th>Deadline</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <% for (Task task : tasks) { %>
    <tr>
        <td><%= task.getTitle() %>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getUserId()%>
        </td>
        <td><%=task.getStatus()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td>
            <form action="/delete_task" method="get" style="display: inline-block"><input type="hidden" name="id"
                                                                                          value="<%=task.getId()%>">
                <input type="submit" value="delete">
            </form>
        </td>
    </tr>
    <% } %>
    <!-- and so on... -->
    </tbody>
</table>

</body>
</html>
