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

<div>

    <% if (request.getAttribute("message") != null) { %>
    <p style="color: red"><%= request.getAttribute("message")%>
    </p>
    <% } %>
</div>

<% User user = (User) request.getAttribute("user");%>
<img src="/getImage?picUrl=<%=user.getPicUrl()%>" width="400"><br>
picture: <input type="file" name="picture" value="<%=user.getPicUrl()%>">


<div style="">
    <div style="display: inline-block">
        <form action="/update" method="post" enctype="multipart/form-data">
            <h3>Add User:</h3><br>
             <input type="hidden" name="id" value="<%=user.getId()%>"> <br>
            name: <input type="text" name="name" value="<%=user.getName()%>"> <br>
            surname: <input type="text" name="surname" value="<%=user.getSurName()%>"> <br>
            email: <input type="text" name="email" value="<%=user.getEmail()%>"> <br>
            password: <input type="text" name="password" value="<%=user.getPassword()%>"> <br>
            biography: <input type="text" name="biography" value="<%=user.getBiography()%>"> <br>
            <input type="submit" value="Submit">
        </form>
    </div>
</div>
</body>
</html>
