<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:choose>
    <c:when test="${not empty items}">
        <s:url value="/items/reservaties?item=${selectedItem.id}" var="feed"/>
        <security:authentication property="principal.voornaam" var="voornaam"/>
        <security:authentication property="principal.achternaam" var="achternaam"/>
        <%--Doesn't work as expected: 
            Twee problemen: 
        1) Een meegegeven datum maakt dat datums ervoor niet geselecteerd kunnen worden, 
            ook al bevinden ze zich in de toekomst. (JavaScript methode van calendar plugin aanpassen)
        2) De datum wordt (vermoedelijk) geinterpreteerd als getal: 2013-01-01 wordt 2011 
            omdat de parameter als wiskundige formule wordt uitgevoerd. Dit geeft verkeerde resultaten. 
        Voorlopige fix: De datum parameter steeds negeren en de huidige datum gebruiken. 
        -->
        <%--<c:if test="${empty datum}">--%>
            <c:set var="datum" value="new Date()"/>
        <%--</c:if>--%>

        <c:if test="${not empty selectedItem}">
            <script type="text/javascript">
                $("document").ready(function()
                {
                    datepicker("#datum_picker", "#datum", ${datum});

                    $("#datum_picker").change(function()
                    {
                        var date = $("#datum_picker").datepicker("getDate");
                        $("#calendar").fullCalendar('gotoDate', date);
                    });

                    var title = "<c:out value="${voornaam} ${achternaam}"/>";
                    initCalendar("#calendar", title, "${feed}");
                });
            </script>
        </c:if>
        <div id="formChooseItem">
            <h2>Kies een item en datum</h2>
            <form id="formReserveer" method="GET">
                <table>
                    <tr>
                        <td><label for="items">Item</label></td>
                        <td>
                            <select name="selected_item" id="items" onchange="submit();">
                                <c:if test="${empty selectedItem}">
                                    <option value="" disabled selected>Kies een item</option>
                                </c:if>
                                <c:forEach var="item" items="${items}">
                                    <option value="${item.id}"
                                            <c:if test="${not empty selectedItem && selectedItem.id == item.id}">
                                                selected
                                            </c:if>>
                                        ${item.naam}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <noscript>
                            <input type="submit" value="Kies"/>
                            </noscript>
                        </td>
                    </tr>
                </table>
            </form>

            <%--Only show the datepicker if an item if selected.--%>
            <c:if test="${not empty selectedItem}">
                <div id="datum_picker"></div>
            </c:if>
        </div>

        <%--Show the reservation-tool if an item if selected.--%>
        <c:if test="${not empty selectedItem}">
            <div id="calendar_wrapper">
                <p>Klik of selecteer een slot om een reservatie toe te voegen.</p>
                <springforms:form modelAttribute="nieuweReservatie" method="POST" style="float:left;">
                    <springforms:input type="hidden" id="startTime" path="slot.beginTime"/>
                    <springforms:input type="hidden" id="endTime" path="slot.endTime"/>
                    <input type="submit" value="Opslaan"/>
                </springforms:form>

                <form method="GET" action="<s:url value="/items/reserveer/verwijder"/>">
                    <input type="hidden" name="reservatie" id="reservatie"/>
                    <input type="hidden" name="selected_item" id="selected_item" value="${selectedItem.id}"/>
                    <input id="remove_button" type="submit" value="Verwijder" disabled="disabled"/>
                </form>

                <div id="calendar"></div>
            </div>
        </c:if>
        <div class="clearfix"></div> 
    </c:when>
    <c:otherwise>
        <p>Er zijn momenteel nog geen items die gereserveerd kunnen worden.</p>
    </c:otherwise>
</c:choose>