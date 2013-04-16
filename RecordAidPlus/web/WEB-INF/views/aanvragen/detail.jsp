<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<h1>Detail aanvraag opname ${aanvraag.optenemenVak}</h1>
<div>
    <a href="<s:url value="/aanvragen/bewerk?id=${aanvraag.id}"/>">Bewerken</a>
    <table class="detailTable">
        <tr>
            <td>Datum les</td>
            <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
            <td>Beginuur</td>
            <td><fmt:formatDate value="${aanvraag.beginTijdstip.time}" pattern="hh:mm" /></td>
        </tr>
        <tr>
            <td>Einduur</td>
            <td><fmt:formatDate value="${aanvraag.eindTijdstip.time}" pattern="hh:mm" /></td>
        </tr>
        <tr>
            <td>Vak</td>
            <td>${aanvraag.optenemenVak}</td>
        </tr>
        <tr>
            <td>Lokaal</td>
            <td>${aanvraag.lokaal}</td>
        </tr>
        <tr>
            <td>Reeks</td>
            <td>${aanvraag.reeks}</td>
        </tr>
        <tr>
            <td>Status van goedkeuring</td>
            <td>${aanvraag.status}</td>
        </tr>
        <tr>
            <td>Verantwoordelijk RecordAid lid</td>
            <td>${aanvraag.toegewezenLid.voornaam} ${aanvraag.toegewezenLid.naam}</td>
        </tr>
        <tr>
            <td>E-mail lector</td>
            <td>${aanvraag.lector.emailadres}</td>
        </tr>
        <tr>
            <td>Departement</td>
            <td>${aanvraag.departement}</td>
        </tr>
        <tr>
            <td>Reden</td>
            <td>${aanvraag.reden}</td>
        </tr>
        <tr>
            <td>Link naar de video</td>
            <td>
                <c:choose>
                    <c:when test="${aanvraag.linkNaarVideo != ''}">
                        <a href="http://${aanvraag.linkNaarVideo}">Link</a>
                    </c:when>
                    <c:otherwise>
                        N/A
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>Aanvraagdatum</td>
            <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="yyyy-MM-dd" /></td>
        </tr>
    </table>

    <a class="link" href="<s:url value="/aanvragen/mijnaanvragen"/>">Terug naar het overzicht</a>
</div>