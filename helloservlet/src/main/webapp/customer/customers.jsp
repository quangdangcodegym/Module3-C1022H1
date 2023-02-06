<%@ page import="model.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/2/2023
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
    <h1>Danh sách khách hàng</h1>
    <table>
        <thead>
            <tr>
                <th>Tên</th>
                <th>Ngày Sinh</th>
                <th>Địa chỉ</th>
                <th>Hình Ảnh</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
<%--            <%--%>
<%--                List<Customer> customers = (List<Customer>) request.getAttribute("customers");--%>
<%--                String str = "";--%>
<%--                for (int i = 0; i < customers.size(); i++) {--%>
<%--                    str += String.format("<tr>\n" +--%>
<%--                            "                <td>%s</td>\n" +--%>
<%--                            "                <td>%s</td>\n" +--%>
<%--                            "                <td>%s</td>\n" +--%>
<%--                            "                <td>%s</td>\n" +--%>
<%--                            "                <td>TTT</td>\n" +--%>
<%--                            "            </tr>", customers.get(i).getName(),customers.get(i).getCreatedAt(), customers.get(i).getAddress(), customers.get(i).getImage() );--%>
<%--                }--%>

<%--                out.println(str);--%>
<%--            %>--%>

        <c:set scope="request" var="temp" value="${0}"></c:set>
        <c:forEach var="c" items="${requestScope.customers}" >

            <c:choose>
                <c:when test="${temp%2==0}">
                    <tr>
                        <td>${c.name}</td>
                        <td>${c.getCreatedAt()}</td>
                        <td>${c.getAddress()}</td>
                        <td>${c.getImage()}</td>
                        <td>...</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr style="color: red">
                        <td>${c.name}</td>
                        <td>${c.getCreatedAt()}</td>
                        <td>${c.getAddress()}</td>
                        <td>${c.getImage()}</td>
                        <td>...</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            <c:set scope="request" var="temp" value="${temp + 1}"></c:set>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
