<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Items beheren</h1>
<h2>Huidige items</h2>
<c:choose>
    <c:when test="${not empty items}">
        <script type="text/javascript">
            $('document').ready(function()
            {
                datatable("#items");
            });
        </script>
        <table id="items">
            <thead>
                <tr>
                    <th>Item naam</th>
                    <th>Verwijderen</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td>${item.naam}</td>
                        <td> 
                            <a href="<s:url value="/items/verwijder?id=${item.id}"/>">
                                Verwijder
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Er zijn momenteel nog geen items.</p>
    </c:otherwise>
</c:choose>

<h2>Voeg een nieuw item toe</h2>
<p id="error" class="error"></p>
<springforms:form id="formAddItem" method="POST" modelAttribute="newItem">
    <label for="naam">Naam van het item</label>
    <springforms:input path="naam" type="text" id="naam"/>
    <input type="submit" value="Voeg toe"/>
</springforms:form>

<s:url value="/items/reservaties" var="feed"/>
<script type="text/javascript">
    $('document').ready(function() {
        var calendar = $("#reservaties").fullCalendar({
            header: {
                left: 'title',
                center: 'month,agendaWeek,agendaDay',
                right: 'today prev,next'
            },
            defaultView: "agendaWeek",
            monthNames: maanden,
            dayNames: dagen,
            dayNamesShort: dagenShort,
            timeFormat: {
                // for agendaWeek and agendaDay
                agenda: 'HH:mm{ - HH:mm}', // 5:00 - 6:30

                // for all other views
                '': 'HH:mm:tt'            // 7p
            },
            titleFormat: {
                month: 'MMMM yyyy', // September 2009
                week: "d[ MMMM yyyy]{ '-' d MMMM yyyy}", // Sep 7 - 13 2009
                day: 'dddd d MMMM yyyy'                  // Tuesday, Sep 8, 2009
            },
            buttonText: {
                prev: '&lsaquo;', // <
                next: '&rsaquo;', // >
                prevYear: '&laquo;', // <<
                nextYear: '&raquo;', // >>
                today: 'Vandaag',
                month: 'Maand',
                week: 'Week',
                day: 'Dag'},
            allDaySlot: false,
            snapMinutes: 10,
            minTime: 7,
            maxTime: 23,
            firstHour: 7,
            axisFormat: 'HH:mm',
            events: "${feed}"
        });
    });
</script>
<h2>Reservaties</h2>
<div id="reservaties">
</div>