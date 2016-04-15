<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<script>
    $(function () {
        $("#datepicker").datepicker({
            dateFormat: "yy-mm-dd"
        });
        $("#anim").change(function () {
            $("#datepicker").datepicker("option", "showAnim", $(this).val());
        });
    });
</script>

<a class="toMainPage" href="/${role}/students-list/">Назад</a>

<div class="tipText">
    <p class="tableName">Для модификации введите новые значения и нажмите кнопку "Применить"</p>

    <div class="form" >
        <form action="/${role}/student-modifying-final/" method="post">
        <a class="subFix"> Фамилия </a><input name="surname" class="formInput" value="${surname}" required="required" placeholder="Введите фамилию"><br>
        <a class="subFix"> Имя </a><input value="${name}" name="name" class="formInput" required="required" placeholder="Введите имя"><br>
        <a class="subFix"> Группа </a><input value="${groupId}" name="groupId" class="formInput" required="required" placeholder="Введите группу"><br>
        <a class="subFix"> Дата поступления </a><input value="${date}" id="datepicker" name="date" class="formInput" required="required" placeholder="Введите дату"><br>
        <input id="id" name="id" type="hidden" value="${id}" >
        <input type="submit" name="submit" value="Применить" id="createButton" >
        </form>
    </div>
</div>

