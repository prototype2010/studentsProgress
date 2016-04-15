<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
    function deleteDisciplines() {
        var items = $("input[type=checkbox]:checked");
        if (items.length == 0) {
            alert("Please select discipline(s)");
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
        var form = '<form id="deleteDisciplinesForm" action="'
                + 'http://'
                + document.location.host
                + '/${role}/disciplines-list/" method="post"><input type="hidden" name="ids" /></form>';
        $("body").append(form);
        $('#deleteDisciplinesForm input').val(ids);
        $('#deleteDisciplinesForm').submit();
    }
    function disciplineModifying() {
        var items = $("input[type=checkbox]:checked");
        if (items.length == 0) {
            alert("Please select discipline");
            return;
        }
        if (items.length > 1) {
            alert("Please select only one discipline");
            return;
        }
        var ids = "";
        for (var i = 0; i < items.length; i++) {
            ids += $(items[i]).attr("id");
        }
        console.log(ids);
        console.log("ids=" + ids);
        var form = '<form id="modifiyngDiscipline" action="'
                + 'http://'
                + document.location.host
                + '/${role}/discipline-modifying/" method="post"><input type="hidden" name="ids" /></form>';
        $("body").append(form);
        $('#modifiyngDiscipline input').val(ids);
        $('#modifiyngDiscipline').submit();
    }
</script>

<a class="toMainPage" href="/${role}/home/">Назад</a>


<div class="tipText">
    <p class="tableName">Список дисциплин</p>

    <div class="DisciplinesListTable">
        <table id="DisciplinesList">
            <tr>
                <c:if test="${role eq 'admin'}">
                    <th class="disciplinesListColumn1"></th>
                </c:if>
                <th class="disciplinesListColumn2">Название дисциплины</th>
            </tr>
            <c:forEach items="${disciplines}" var="disc">
                <tr>
                    <c:if test="${role eq 'admin'}">
                        <td><input type="checkbox" id="${disc.id}"></td>
                    </c:if>
                    <td>${disc.name}</td>
                </tr>
            </c:forEach>
        </table>

    </div>
    <div class="DisciplinesListControlPanel">

        <c:if test="${role eq 'admin'}">
            <input class="controlPanelButton" type="button" onclick="location.href='/${role}/discipline-creating/'"
                   name=""
                   value="Создать дисциплину..."><br>
            <input class="controlPanelButton" type="button" name="" value="Модифицировать выбранную дисциплину..."
                   onclick="disciplineModifying()"><br>
            <input class="controlPanelButton" type="button" name="" value="Удалить выбранную дисциплину..."
                   onclick="deleteDisciplines()"><br>
        </c:if>
    </div>
</div>
