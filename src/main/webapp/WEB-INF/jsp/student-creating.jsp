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
        var context = "${CONTEXT}";
        function createStudentButton() {
            var inputSurname = document.getElementById('surname').value;
            alert(inputSurname.value);

        }
    </script>

<a class="toMainPage" href="/${role}/students-list/">Назад</a>

<div class="tipText">
    <p class="tableName">Для создания нового студента заполните все поля и нажмите кнопку "Создать"</p>

    <div class="form">
        <form action="/${role}/student-creating/" method="post">
            <a class="subFix">Фамилия </a><input name="surname" class="formInput" required="required" placeholder="Введите фамилию"><br>
            <a class="subFix"> Имя </a><input name="name" class="formInput" required="required" placeholder="Введите имя"><br>
            <a class="subFix"> Группа </a><input name="group" class="formInput" required="required" placeholder="Введите группу"><br>
            <a class="subFix"> Дата поступления </a><input name="signDate" class="formInput" id="datepicker" required="required" placeholder="Введите дату"><br>
            <input type="submit" name="submit" value="Создать" id="createButton" onclick="createStudentButton()">
        </form>

    </div>
</div>
