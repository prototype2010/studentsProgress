<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="/resources/js/students.js"></script>
    <title>Система управления студентами и их успеваемостью</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

</head>
<body>


<div class="header"> Система управления студентами и их успеваемостью</div>
<c:if test="${role ne null}">
<a class="logout" href="/logout">Logout</a><br>
<a class="toMainPage" href="/${role}/home/">На главную</a>

</c:if>


<jsp:include page="${currentPage}"></jsp:include>
<footer class="WARNING"><c:out value="${WARNING}"></c:out></footer>
</body>
</html>