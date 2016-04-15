<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<a class="toMainPage" href="/${role}/terms-list/">Назад</a>

<div class="tipText">
    <p class="tableName">Для создания семестра заполните следующие данные и нажмите кнопку "Cоздать"</p>

    <div class="termCreating">
        <form method="post" action="/${role}/term-creating/">
            <div id="termCreating">
                <sub>Номер семестра</sub><input name="number" class="formInputWeeks" type="number" min="0" required="required">
            </div>
            <div id="termCreating1">
                <sub>Длетельность в неделях</sub><input name="duration" class="formInputWeeks" type="number" min="0" required="required">
            </div>
            <div id="termCreating2">
                <sub id="selectName">Дисциплины в семестре</sub><br>
                <select multiple id="multipleSelect" name="disciplines" required="required">
                    <c:forEach items="${disciplines}" var="disc">
                        <option>${disc.name}</option>
                    </c:forEach>
                </select>
                <input id="createButtonTerm" type="submit" value="Создать">
            </div>
        </form>
    </div>
</div>

