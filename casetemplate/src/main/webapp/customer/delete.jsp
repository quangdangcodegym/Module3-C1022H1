<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/2/2023
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Confirm delete</h1>
    <form method="post">
        <h1>${requestScope.customer.getName()}</h1>
        <h1>${requestScope.customer.getAddress()}</h1>
        <input type="hidden" name="id" value="${requestScope.customer.getId()}">
        <button >Delete</button>
    </form>


</body>
</html>
