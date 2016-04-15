<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Вход в систему управления студентами и их успеваемостью</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="/resources/js/students.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
<div class="tipTextLogin1">
    <div class="tipTextLogin">
        <form action="/login" method="post">
            <a class="subFix1">Необходима авторизация</a><br>

            <div class="loginField">
                Логин <input name="login" maxlength="10" class="loginInput" required="required" placeholder="Введите логин"> <br>
            </div>
            <div class="loginField">
                Пароль<input name="password" type="password" maxlength="16" class="loginInput" required="required" placeholder="Введите пароль"> <br>
            </div>
            <div class="loginField">
                Роль <select name="role" class="loginInput2" required="required"> <br>
                <option selected value="${selectedRole}"><c:out value="${selectedRole}"></c:out></option>
                <option value="" selected >Выберите роль</option>
                <c:forEach items="${roles}" var="rl">
                    <option value="${rl.name}"><c:out value="${rl.name}"></c:out></option>
                </c:forEach>
                </select>
            </div>
            <input type="submit" value="Войти" id="semesterSubmitButton1">
        </form>
    </div>
</div>
</body>
</html>
