<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<a class="toMainPage" href="/${role}/students-list/">Назад</a>


<div id="tipText">
    <p class="tableName">Отображена успеваемость следующего студента</p>

<div class="blinkFix1">
    <table id="tableStudentInfo">
        <tr>
            <th id="colunm1StudentInfo">Фамилия</th>
            <th id="column2StudentInfo">Имя</th>
            <th id="column3StudentInfo">Группа</th>
            <th id="column4StudentInfo">Дата поступления</th>
        </tr>
        <tr>
            <td><c:out value="${surname}"></c:out></td>
            <td><c:out value="${name}"></c:out></td>
            <td><c:out value="${groupId}"></c:out></td>
            <td><fmt:formatDate value="${date}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
        </tr>
    </table>
</div>
    <div class="discipline">
        <table id="DisciplineMark">
            <tr>
                <th class="disciplineColumn1">Дисциплина</th>
                <th class="disciplineColumn2">Оценка</th>
            </tr>

            <c:forEach items="${disciplinesMarks}" var="dm">
                <tr>
                    <td>${dm.disciplineName}</td>
                    <td>${dm.mark}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="semesterAndAverage">
        <form method="post" action="/${role}/student-progress-final/">
            Выбрать семестр
            <select id="semesterList" name="term" required="required">
                <option selected><c:out value="${selectedTerm}"></c:out></option>

                <c:forEach items="${terms}" var="tr">

                    <option>${tr.id}</option>

                </c:forEach>
            </select>
            <input type="hidden" value="${id}" name="id" class="semesterSubmitButton">
            <input type="submit" value="Выбрать" class="semesterSubmitButton">
        </form>
    </div>
    <div id="avarage"><br><a>Средняя оценка за семестр ${average} балла</a></div>
</div>
