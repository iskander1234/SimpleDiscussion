<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.04.2021
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Publication form</title>
</head>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
            style="background-color: tomato">
        <div><a href="" class="navbar-brand">Publication App</a></div>
        <ul class="navbar-nav">
            <li>
                <a href="<%=request.getContextPath()%>/publication-list.jsp"
                class="nav-link">Publication</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/login.jsp"
                class="nav-link">Login</a>
            </li>
        </ul>
    </nav>

    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:if test="${publications != null}">
                <form action="update" method="post">
                    </c:if>
                    <c:if test="${publications == null}">
                    <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${publications != null}">
                                    Edit Publication
                                </c:if>
                                <c:if test="${publications == null}">
                                    Add New Publication
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${publications != null}">
                            <input type="hidden" name="id" value="<c:out value='${publications.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Publication Name</label> <input type="text"
                                                            value="<c:out value='${publications.name}' />" class="form-control"
                                                            name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Publication Description</label> <input type="text"
                                                             value="<c:out value='${publications.description}' />" class="form-control"
                                                             name="description">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Publication Country</label> <input type="text"
                                                               value="<c:out value='${publications.comments}' />" class="form-control"
                                                               name="comments">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
            </div>
        </div>
    </div>
</header>
</body>
</html>
