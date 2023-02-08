<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/2/2023
  Time: 8:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
    <title>Create customer</title>
</head>
<body>

    <c:if test="${requestScope.message != null}">
        <script>
            window.onload = ()=>{
                $('.toast').toast({delay: 2000});
                $('.toast').toast('show');
            }
        </script>
    </c:if>

    <div class="toast" data-autohide="true" style="position: fixed; top: 0; right: 0">
        <div class="toast-header">
            <strong class="mr-auto text-primary">Toast Header</strong>
            <small class="text-muted">5 mins ago</small>
            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
        </div>
        <div class="toast-body">
            Some text inside the toast body
        </div>
    </div>
    <div class="row">
        <h1 class="col-4 offset-3">Create customer</h1>
    </div>
    <c:if test="${requestScope.errors!=null && requestScope.errors.size()!=0}">
        <div class="alert alert-danger">
            <ul>
                <c:forEach var="e" items="${requestScope.errors}">
                    <li>${e}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <div class="row">
        <form method="post" class="col-4 offset-3">
            <div class="row mt-2">
                <label class="col-3">Name: </label> <input class="col-9" type="text" name="name" value="${requestScope.customer.getName()}">
            </div>
            <div class="row mt-2">
                <label class="col-3">Date: </label> <input class="col-9" type="date" name="createdAt" value="<fmt:formatDate pattern = "yyyy-MM-dd"
                                            value = "${requestScope.customer.getCreatedAt()}" />" >
            </div>
            <div class="row mt-2">
                <label class="col-3">Address: </label> <input class="col-9" type="text" name="address" value="${requestScope.customer.getAddress()}">
            </div>
            <div class="row mt-2">
                <label class="col-3">Image: </label> <input class="col-9" type="text" name="image" value="${requestScope.customer.getImage()}">
            </div>
            <div class="row mt-2">
                <button type="submit" class="col-3 offset-3">Create</button>
            </div>
        </form>
    </div>
</body>
</html>
