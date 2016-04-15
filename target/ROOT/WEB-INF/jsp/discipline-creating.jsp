<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<a class="toMainPage" href="/${role}/disciplines-list/">Назад</a>


<div class="tipText">
    <p class="tableName">Для того чтобы создать новую дисциплину заполните все поля и нажмите кнопку "Создать"</p>

    <div id="SubjectForm">
        <form action="/${role}/discipline-creating/" method="post">
            <a class="subFix"> Название </a> <input name="name" class="formInput" required="required" placeholder="Введите название дисциплины"><br>
            <input class="createButtonFix" type="submit" value="Создать">
        </form>
    </div>
</div>
