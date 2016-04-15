<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<a class="toMainPage" href="/${role}/home/">Назад</a>


<div id="tipText">
    <a class="tableName ">Выберите студента и семестр для отобажения дисциплин</a>

    <form action="/${role}/set-mark/" method="post">
        <div class="marks">
            Выберите студента
            <select class="semesterList1" name="student" required="required">
                <option selected><c:out value="${selectedStudent}"></c:out></option>

                <c:forEach items="${students}" var="st">
                    <option>${st.id}. ${st.surname} ${st.name} (${st.groupId})</option>
                </c:forEach>

            </select>

        </div>

        <div class="marks2">
            Выберите семестр
            <select class="semesterList2" name="terms" id="selectedTerm" required="required">

                <option selected><c:out value="${selectedTerm}"></c:out></option>

                <c:forEach items="${terms}" var="tr">

                    <option>${tr.id}</option>

                </c:forEach>
            </select>
            <input type="hidden" value="" name="selectedStudentId">
        </div>
        <input type="submit" value="Выбрать" id="selectButtonSetMark">
    </form>
    <div class="lastDiv">
        <form action="/${role}/set-mark-final/" method="post">
            <table id="DisciplineMark" name="DisciplineMark">
                <tr>
                    <th class="disciplineColumn1">Дисциплина</th>
                    <th class="disciplineColumn2">Оценка</th>
                </tr>
                <c:forEach items="${disciplineMarks}" var="dm">
                    <tr>
                        <td name="test">${dm.disciplineName}</td>
                        <td>
                            <input class="inputsForMarks"
                                   <c:if test="${role eq 'admin'}">type="number"</c:if> min="0" max="5" step="1"
                                   name="${dm.id}"
                                   value="<c:out value="${dm.mark}"></c:out>" id="${dm.id}"
                                   <c:if test="${role ne 'admin'}">disabled="disabled"</c:if>>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <input type="hidden" value="${idsForMarks}" name="idsForMarks">
            <input type="hidden" value="${selectedStudent}" name="studentId">
            <input type="hidden" value="${selectedTerm}" name="termId">

            <c:if test="${role eq 'admin'}">
                <input type="submit" value="Подтвердить" class="selectButtonSetMark">
            </c:if>
        </form>
    </div>

</div>
