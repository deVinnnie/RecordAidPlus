<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<h1>Opname goedkeuren</h1>
<m:boodschap/>
<%--Toon details van de opname--%>
<table class="details">
    <tr>
        <td>Aanvrager</td>
        <td><c:out value="${aanvraag.dossier.gebruiker.voornaam} ${aanvraag.dossier.gebruiker.achternaam}"/></td>
    </tr>
    <tr>
        <td>Lesdatum</td>
        <td><fmt:formatDate value="${opnameMoment.tijdstip.beginTime.time}" pattern="yyyy-MM-dd hh:mm" /></td>
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
    <tr>
        <td>Reden</td>
        <td>${aanvraag.reden}</td>
    </tr>
</table>

<%--Toon opties voor de goedkeuring van de opname--%>
<c:if test="${empty boodschap}"><%--Als er een boodschap wilt dit zeggen dat de opname al goedgekeurd of afgekeurd is.--%>
    <form method="POST">
        <p>Kies welke soort opnames RecordAid mag doen.</p>

        <ul id="opmnameMethodes">
            <%--OpnameMogelijkheden--%>
            <c:forEach var="opnameMethode" items="${opnameMethodes}">
                <li><input type="checkbox" name="methodes" value="${opnameMethode.id}"/>${opnameMethode.beschrijving}</li>
                </c:forEach>
        </ul>

        <input type="submit" name="action" value="Goedkeuren"/>
        <input type="submit" name="action" value="Weigeren"/>
    </form>
</c:if>