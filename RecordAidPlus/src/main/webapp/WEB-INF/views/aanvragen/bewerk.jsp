<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<div>
    <h1>Bewerken :  <%@include file="fragments/aanvraag_datum.jsp" %></h1>
    <m:boodschap/>
    <a href="<s:url value="/aanvragen/beheer"/>">Terug naar het overzicht</a>
    <c:if test="${aanvraag.status.name ne 'Goedgekeurd'}">
        <a href="<s:url value="/aanvragen/goedkeuren?id=${aanvraag.id}"/>">Goedkeuren</a>
    </c:if>
    <c:if test="${aanvraag.status.name ne 'Afgekeurd'}">
        <a href="<s:url value="/aanvragen/weigeren?id=${aanvraag.id}"/>">Weigeren</a>
    </c:if>
    <a href="<s:url value="/aanvragen/sluiten?id=${aanvraag.id}"/>">Voltooid</a>
</div>

<div>
    <springforms:errors path="*" cssClass="error" />
    <springforms:form method="POST" modelAttribute="aanvraag" id="aanvraagStatusForm">
        <div id="aanvraag_algemeen">
            <h2>Algemeen</h2>
            <%@include file="fragments/aanvraag_detail_fragment.jsp"%>
            <c:if test="${type eq 'MultiPeriodeAanvraag'}">
                <a href="<s:url value="/aanvragen/inform_lectoren?aanvraag=${aanvraag.id}"/>">
                    Verstuur mail naar alle lectoren
                </a>
            </c:if>
        </div>

        <div id="aanvraag_lessen">
            <div>
                <h2>Lessen</h2> 
                <a href="<s:url value="/aanvragen/nieuwe_les?aanvraag=${aanvraag.id}"/>">Les toevoegen</a>
            </div>
            <c:forEach var="opnameMoment" items="${aanvraag.opnameMomenten}" varStatus="counter">
                <h3>
                    <fmt:formatDate value="${opnameMoment.beginTijdstip.time}" pattern="hh:mm" /> tot 
                    <fmt:formatDate value="${opnameMoment.eindTijdstip.time}" pattern="hh:mm" />  
                    <%@include file="fragments/les_status_fragment.jsp" %>
                </h3>

                <a href="<s:url value="/aanvragen/koppel_opname?aanvraag=${aanvraag.id}&opnamemoment=${opnameMoment.id}"/>">Koppel opname</a>

                <table>
                    <tr>
                        <td><label for="OOD">OOD</label></td>
                        <td><springforms:input type="text" path="opnameMomenten[${counter.index}].OOD" id="OOD"/></td>
                    </tr>
                    <tr>
                        <td><label for="${counter.index}_lokaal">Lokaal</label></td>
                        <td><springforms:input path="opnameMomenten[${counter.index}].lokaal.naam" type="text" id="${counter.index}_lokaal"/></td>
                    </tr>
                    <tr>
                        <td><label for="${counter.index}_reeks">Klas/Reeks</label></td>
                        <td><springforms:input path="opnameMomenten[${counter.index}].reeks" type="text" id="${counter.index}_reeks"/></td>
                    </tr>
                    <tr>
                        <td>Verantwoordelijk RecordAid lid</td>
                        <td><springforms:select path="opnameMomenten[${counter.index}].verantwoordelijke">
                                <springforms:options items="${alleBuddies}" itemValue="emailadres" itemLabel="volledigeNaam"/>
                            </springforms:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Lector</td>
                        <td>
                            <springforms:input path="opnameMomenten[${counter.index}].lector.emailadres" type="text"/>
                        </td>
                    </tr>
                </table>
            </c:forEach>
        </div>
        <div class="clearfix">
            <input type="submit" value="Werk aanvraag bij"/>
        </div>
    </springforms:form>
</div>