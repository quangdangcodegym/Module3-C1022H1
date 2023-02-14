<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/dashboard/layout/right_bar.jsp"></jsp:include>

<!-- Vendor js -->
<script src="/dashboard/assets\js\vendor.min.js"></script>

<c:if test="${param.page == 'index'}">
    <script src="/dashboard/assets\libs\morris-js\morris.min.js"></script>
    <script src="/dashboard/assets\libs\raphael\raphael.min.js"></script>

    <script src="/dashboard/assets\js\pages\dashboard.init.js"></script>
</c:if>

<!-- App js -->
<script src="/dashboard/assets\js\app.min.js"></script>

<script src="/dashboard/assets\libs\toastr\toastr.min.js"></script>
<script src="/dashboard/assets\js\pages\toastr.init.js"></script>
