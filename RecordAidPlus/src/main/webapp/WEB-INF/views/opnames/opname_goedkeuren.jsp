<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1>Opname goedkeuren</h1>
<%--Toon details van de opname--%>
<table class="detailTable">
    <tr>
        <td>Aanvrager</td>
        <td><c:out value="${aanvraag.dossier.gebruiker.voornaam} ${aanvraag.dossier.gebruiker.achternaam}"/></td>
    </tr>
    <c:if test="${aanvraag.getClass().getSimpleName() eq 'DagAanvraag'}">
        <tr>
            <td>Lesdatum</td>
            <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
            <td>Voor hele reeks?</td>
            <td>
                <c:choose>
                    <c:when test="${aanvraag.voorHeleReeks}">
                        Ja
                    </c:when>
                    <c:otherwise>
                        Nee
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:if>
    <c:if test="${aanvraag.getClass().getSimpleName() eq 'MultiPeriodeAanvraag'}">
        <tr>
            <td>Periode</td>
            <td><fmt:formatDate value="${aanvraag.periode.beginTime.time}" pattern="yyyy-MM-dd" />
                tot <fmt:formatDate value="${aanvraag.periode.endTime.time}" pattern="yyyy-MM-dd" />
            </td>
        </tr>
    </c:if>
    <tr>
        <td>Reden</td>
        <td>${aanvraag.reden}</td>
    </tr>
</table>

<%--Toon opties voor de goedkeuring van de opname--%>
<form method="POST">
    <p>Kies welke soort opname RecordAid mag doen. Je kan er ook voor opteren om zelf een opname te voorzien. </p>
 
    <ul>
    <%--OpnameMogelijkheden--%>
    <c:forEach var="opnameMethode" items="${opnameMethodes}">
        <li><input type="checkbox" name="methodes" value="${opnameMethode.id}"/>${opnameMethode.beschrijving}</li>
    </c:forEach>
    </ul>

    <input type="submit" name="action" value="Goedkeuren"/>
    <input type="submit" name="action" value="Weigeren"/>
</form>

