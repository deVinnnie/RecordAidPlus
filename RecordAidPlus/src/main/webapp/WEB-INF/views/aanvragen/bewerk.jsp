<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div>
    <h1>Bewerken :  <%@include file="fragments/aanvraag_datum.jsp" %></h1>
    <a href="<s:url value="/aanvragen/beheer"/>">Terug naar het overzicht</a>
    <a href="<s:url value="/aanvragen/goedkeuren?id=${aanvraag.id}"/>">Goedkeuren</a>
    <a href="<s:url value="/aanvragen/weigeren?id=${aanvraag.id}"/>">Weigeren</a>
    <a href="<s:url value="/aanvragen/sluiten?id=${aanvraag.id}"/>">Voltooid</a>
</div>

<div>
    <springforms:errors path="*" cssClass="error" />
    <springforms:form method="POST" modelAttribute="aanvraag" id="aanvraagStatusForm">
        <div id="aanvraag_algemeen">
            <h2>Algemeen</h2>
            <table class="detailTable">
                <tr>
                    <td>Aanvrager</td>
                    <td>${aanvraag.dossier.gebruiker.voornaam} ${aanvraag.dossier.gebruiker.achternaam}</td>
                </tr>
                <tr>
                    <c:choose>
                        <c:when test="${aanvraag.getClass().getSimpleName() eq 'DagAanvraag'}">
                        <td>Lesdatum</td>
                        <td>
                            <fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" />
                        </td>
                        </c:when>
                        <c:when test="${aanvraag.getClass().getSimpleName() eq 'MultiPeriodeAanvraag'}">
                            <td>Periode</td>
                                <td>
                            <fmt:formatDate value="${aanvraag.periode.beginTime.time}" pattern="yyyy-MM-dd" />
                            tot 
                            <fmt:formatDate value="${aanvraag.periode.endTime.time}" pattern="yyyy-MM-dd" />
                                </td>
                        </c:when>
                    </c:choose>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>${aanvraag.status}</td>
                </tr>
                <tr>
                    <td>Departement</td>
                    <td>${aanvraag.departement.naam}</td>
                </tr>
                <tr>
                    <td>Reden</td>
                    <td>${aanvraag.reden}</td>
                </tr>
                <tr>
                    <td>Aanvraagdatum</td>
                    <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="yyyy-MM-dd" /></td>
                </tr>
            </table>
        </div>

        <div id="aanvraag_lessen">
            <div>
                <h2>Lessen</h2> 
                <a href="<s:url value="/aanvragen/nieuwe_opname?aanvraag=${aanvraag.id}"/>">Les toevoegen</a>
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