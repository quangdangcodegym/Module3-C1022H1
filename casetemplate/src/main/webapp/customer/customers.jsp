
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/2/2023
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.1/sweetalert2.css" integrity="sha512-JzSVRb7c802/njMbV97pjo1wuJAE/6v9CvthGTDxiaZij/TFpPQmQPTcdXyUVucsvLtJBT6YwRb5LhVxX3pQHQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.1/sweetalert2.min.js" integrity="sha512-vCI1Ba/Ob39YYPiWruLs4uHSA3QzxgHBcJNfFMRMJr832nT/2FBrwmMGQMwlD6Z/rAIIwZFX8vJJWDj7odXMaw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
    <div class="container">
        <h1>Danh sách khách hàng</h1>
        <a href="/customers?action=create"></a>
        <div class="row" >
            <div class="col-4">
                <button class="btn btn-primary" >Create</button>
            </div>
                <form method="get" class="col-8 row" style="justify-content: flex-end; align-items: center">
                    <input type="text" class="form-control mr-2" placeholder="search" style="width:200px;display: inline-block">
                    <select class="form-control mr-2" style="width:150px;display: inline-block">
                        <option>All</option>
                        <option>VIP</option>
                        <option>Normal</option>
                    </select>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
        </div>

        <c:if test="${requestScope.message == 'delete'}">
            <script>
                window.onload = ()=>{
                    Swal.fire(
                        'Deleted!',
                        'Your file has been deleted.',
                        'success'
                    )
                }
            </script>
        </c:if>
        <c:if test="${requestScope.message == 'edit'}">
            <script>
                window.onload = ()=>{
                    Swal.fire(
                        'Edit!',
                        'Thông tin không hợp lệ.',
                        'success'
                    )
                }
            </script>
        </c:if>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Tên</th>
                <th>Ngày Sinh</th>
                <th>Địa chỉ</th>
                <th>Hình Ảnh</th>
                <th>Loại khách hàng</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:set scope="request" var="temp" value="${0}"></c:set>
            <c:forEach var="c" items="${requestScope.customers}" >
                <tr>
                    <td>${c.name}</td>
                    <td><fmt:formatDate pattern = "dd-MM-yyyy hh:mm"
                                        value = "${c.getCreatedAt()}" /></td>
                    <td>${c.getAddress()}</td>
                    <td>
                        <img src="/images/${c.getImage()}" style="width: 100px; height: auto" >
                    </td>
                    <td>
                        <c:forEach var="cType" items="${requestScope.customerTypes}">
                            <c:if test="${cType.getId()==c.getIdType()}">
                                ${cType.getName()}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="/customers?action=edit&id=${c.getId()}"><i class="fa-regular fa-pen-to-square"></i></a>
                        <a  onclick="handleDeleteClick(${c.getId()})"><i class="fa-regular fa-trash-can"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>


        </table>
        <form id="frmDelete" method="post" action="/customers?action=delete">
            <input type="hidden" name="idDelete" value="" id="idDelete">
        </form>
    </div>
    <script>
        function handleDeleteClick(idCustomer) {
            document.getElementById("idDelete").value = idCustomer;
            Swal.fire({
                title: 'Are you sure?',
                // text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    document.getElementById("frmDelete").submit();
                    // Swal.fire(
                    //     'Deleted!',
                    //     'Your file has been deleted.',
                    //     'success'
                    // )
                }
            })
            return false;
        }
    </script>
</body>
</html>
