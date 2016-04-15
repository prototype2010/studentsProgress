<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<a class="toMainPage" href="/${role}/disciplines-list/">Назад</a>


<div class="tipText">
    <p class="tableName">Чтобы модифицировать дисциплину введите новое значение и нажмите кнопку "Применить"</p>

    <div id="SubjectForm">
        <form method="post" name="disciplineModifying" action="/${role}/discipline-modifying-final/">
            <a class="subFix"> Название </a><input name="disciplineName" class="formInput"
                                                   value="${disciplineName}" required="required" placeholder="Введите имя дисциплины"><br>
            <input name="id" id="formInput1" value="${id}" type="hidden">
            <input class="createButtonFix" type="submit" value="Применить">
        </form>

    </div>
</div>
