<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
    function deleteStudents() {
        var items = $("input[type=checkbox]:checked");
        if (items.length == 0) {
            alert("Please select student(s)");
            return;
        }
        var ids = "";
        for (var i = 0; i < items.length; i++) {
            ids += $(items[i]).attr("id");
            if (i < items.length - 1) {
                ids += ",";
            }
        }
        console.log(ids);
        console.log("ids=" + ids);
        var form = '<form id="deleteStudentForm" action="'
                + 'http://'
                + document.location.host
                + '/${role}/students-list/" method="post"><input type="hidden" name="ids" /></form>';
        $("body").append(form);
        $('#deleteStudentForm input').val(ids);
        $('#deleteStudentForm').submit();
    }
    function studentModifying() {
        var items = $("input[type=checkbox]:checked");
        if (items.length == 0) {
            alert("Please select student");
            return;
        }
        if (items.length > 1) {
            alert("Please select only one student");
            return;
        }
        var ids = "";
        for (var i = 0; i < items.length; i++) {
            ids += $(items[i]).attr("id");
        }
        console.log(ids);
        console.log("ids=" + ids);
        var form = '<form id="deleteStudentForm" action="'
                + 'http://'
                + document.location.host
                + '/${role}/student-modifying/" method="post"><input type="hidden" name="ids" /></form>';
        $("body").append(form);
        $('#deleteStudentForm input').val(ids);
        $('#deleteStudentForm').submit();
    }
    function studentProgress() {
        var items = $("input[type=checkbox]:checked");
        if (items.length == 0) {
            alert("Please select student");
            return;
        }
        if (items.length > 1) {
            alert("Please select only one student");
            return;
        }
        var ids = "";
        for (var i = 0; i < items.length; i++) {
            ids += $(items[i]).attr("id");
        }
        console.log(ids);
        console.log("ids=" + ids);
        var form = '<form id="deleteStudentForm" action="'
                + 'http://'
                + document.location.host
                + '/${role}/student-progress/" method="post"><input type="hidden" name="ids" /></form>';
        $("body").append(form);
        $('#deleteStudentForm input').val(ids);
        $('#deleteStudentForm').submit();
    }
</script>

<a class="toMainPage" href="/${role}/home/">Назад</a>


<div id="tip3">
    <input class="buttonColumn1" type="button" name="" value="Просмотреть успеваемость выбраного студента" onclick="studentProgress()">

    <c:if test="${role eq 'admin'}">
        <input class="buttonColumn2" type="button" name="" value="Создать Студента"  onclick="location.href='/${role}/student-creating/'"> <br>
        <input class="buttonColumn1" type="button" name="" value="Модифицировать выбранного студента"   onclick="studentModifying()">
        <input class="buttonColumn2" type="button" name="" value="Удалить студента" onclick="deleteStudents()">
    </c:if>

</div>
<div id="tip2">
    <p class="tableName">Список студентов</p>

    <table id="tableStudentsList">
        <tr>
            <th class="colunm1"></th>
            <th class="column2">Фамилия</th>
            <th class="column3">Имя</th>
            <th class="column4">Группа</th>
            <th class="column5">Дата поступления</th>
        </tr>
        <c:forEach items="${students}" var="st">
            <tr>
                <td><input type="checkbox" id="${st.id}"></td>
                <td>${st.surname}</td>
                <td>${st.name}</td>
                <td>${st.groupId}</td>
                <td><fmt:formatDate value="${st.date}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
            </tr>
        </c:forEach>

    </table>
</div>
