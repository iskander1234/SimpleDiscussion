<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.04.2021
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Publication list</title>
</head>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<body>
<nav class="navbar navbar-expand-md navbar-dark"
     style="background-color: tomato">
    <div><a href="" class="navbar-brand">Publication App</a></div>
    <ul class="navbar-nav">
        <li>
            <a href="<%=request.getContextPath()%>/list"
               class="nav-link">Publication</a>
        </li>
        <li>
            <a href="<%=request.getContextPath()%>/login.jsp"
               class="nav-link">Login</a>
        </li>
    </ul>
</nav>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Publication</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New Publication</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Comments</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="publications" items="${listPublication}">

                <tr>
                    <td><c:out value="${publications.id}" /></td>
                    <td><c:out value="${publications.name}" /></td>
                    <td><c:out value="${publications.description}" /></td>
                    <td><c:out value="${publications.comments}" /></td>
                    <td><a href="edit?id=<c:out value='${publications.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${publications.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
