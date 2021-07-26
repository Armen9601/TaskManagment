<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.07.2021
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Home</title>

</head>
<style> input[type=text], input[type=date], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20;
} </style>


<body>
<a href="/logout" style="float: right">Logout</a>

<% List<User> users = (List<User>) request.getAttribute("allusers");%>
<div>

    <% if (request.getAttribute("message") != null) { %>
    <p style="color: red"><%= request.getAttribute("message")%>
    </p>
    <% } %>
</div>

<%--<% User user = (User) request.getSession().getAttribute("user");%>--%>
<div style="">
    <div style="display: inline-block">
        <form action="/managerHome" method="post" enctype="multipart/form-data">
            <h3>Add User:</h3><br>
            name: <input type="text" name="name"> <br>
            surname: <input type="text" name="surname"> <br>
            email: <input type="text" name="email"> <br>
            password: <input type="text" name="password"> <br>
            picture: <input type="file" name="picture"> <br>
            biography: <input type="text" name="biography"> <br>
            <input type="submit" value="Submit">
        </form>
    </div>
    <div style="display: inline-block">

        <form action="/addtask" method="post">
            <h3>Add Tasks:</h3><br>
            title: <input type="text" name="title"> <br>
            description: <input type="text" name="description"> <br>
            user: <select name="user"><%
            for (User user1 : users) {%>
            <option value=<%=user1.getId()%>><%=user1.getName() %>
            </option>

            <% }%>
        </select><br>
            status: <select name="status">
            <option value="NEW">New</option>
            <option value="INPROGRES">In progress</option>
            <option value="FINISHED">Finished</option>

        </select><br>
            deadline: <input type="date" name="deadline"><br>
            <input type="submit" value="Submit">
        </form>
    </div>
</div>
<form action="/all_users" method="get">
    <input type="submit" value="see all users"></form>
<form action="/all_tasks" method="get">
    <input type="submit" value="see all tasks"></form>
</body>
</html>
