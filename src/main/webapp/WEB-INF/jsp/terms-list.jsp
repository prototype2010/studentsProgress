<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
    function deleteTerm() {
        var id = document.getElementById('selectedTerm').value;
        if (id != '') {
            var form = '<form id="deleteDisciplinesForm" action="'
                    + 'http://'
                    + document.location.host
                    + '/${role}/term-deleting/" method="post"><input type="hidden" name="id" /></form>';
            $("body").append(form);
            $('#deleteDisciplinesForm input').val(id);
            $('#deleteDisciplinesForm').submit();

        }
        else {
            alert("Choose term please")
        }
    }
    function modifyTerm() {
        var id = document.getElementById('selectedTerm').value;
        if (id != '') {
            var form = '<form id="deleteDisciplinesForm" action="'
                    + 'http://'
                    + document.location.host
                    + '/${role}/term-modifying/" method="post"><input type="hidden" name="id" /></form>';
            +'/${role}/term-modifying/" method="post"><input type="hidden" name="id" /></form>';
            $("body").append(form);
            $('#deleteDisciplinesForm input').val(id);
            $('#deleteDisciplinesForm').submit();

        }
        else {
            alert("Choose term please")
        }
    }
</script>

<a class="toMainPage" href="/${role}/home/">Назад</a>

<div class="tipText" >
    <p class="tableName"></p><br>

    <div class="termsListSemester">
        <p>Выберите семестр</p>

        <form action="/${role}/terms-list/" method="post" id="deleteTerm" name="deleteTerm1">
            <select class="semesterListFixTerm" name="term" id="selectedTerm" required="required">

                <option selected><c:out value="${selectedTerm}"></c:out></option>

                <c:forEach items="${terms}" var="tr">

                    <option>${tr.id}</option>

                </c:forEach>

            </select>
            <input type="submit" value="Выбрать" class="selectButtonFixTerm"><br /><br />
        </form>

        <a class="tableName">Длительность семестра: <c:out value="${weeks}"> </c:out> недель(и)</a>
        <br>
        <br>
        <a class="tableName">Список дисциплин семестра</a>
        <br>
        <div class="DisciplineList" >
            <table id="DisciplinesListTermList">
                <tr>
                    <th class="disciplinesListColumn2">Наименование дисциплины</th>
                </tr>

                <c:forEach items="${disciplines}" var="disc">
                    <tr>
                        <td>${disc.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="semesterControlPanel">
            <c:if test="${role eq 'admin'}">
                <input class="controlPanelButton" type="button" onclick="location.href='/${role}/term-creating/'" name=""
                       value="Создать семестр..."><br>
                <input class="controlPanelButton" type="button" name="" value="Модифицировать текущий семестр ..."
                       onclick="modifyTerm()"><br>
                <input class="controlPanelButton" type="button" name="" value="Удалить текущий семестр..."
                       onclick="deleteTerm()"><br>
            </c:if>

        </div>
    </div>

</div>
