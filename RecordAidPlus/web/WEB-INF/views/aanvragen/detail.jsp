<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<h1>Detail aanvraag opname <fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" /></h1>
<div>
    <a href="<s:url value="/aanvragen/bewerk?id=${aanvraag.id}"/>">Bewerken</a>
    <table class="detailTable">
        <tr>
            <td>Aanvrager</td>
            <td>${aanvraag.dossier.gebruiker.voornaam} ${aanvraag.dossier.gebruiker.achternaam}</td>
        </tr>
        <tr>
            <td>Datum</td>
            <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" /></td>
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
        
    <h2>Lessen</h2>
    <c:forEach var="opnameMoment" items="${aanvraag.opnameMomenten}">
        <h3>
            <fmt:formatDate value="${opnameMoment.beginTijdstip.time}" pattern="hh:mm" /> tot 
            <fmt:formatDate value="${opnameMoment.eindTijdstip.time}" pattern="hh:mm" />    
        </h3>
        <table>
            <tr>
                <td>OOD</td>
                <td>${opnameMoment.OOD}</td>
            </tr>
            <tr>
                <td>Lokaal</td>
                <td>${opnameMoment.lokaal.naam}</td>
            </tr>
            <tr>
                <td>Klas/Reeks</td>
                <td>${opnameMoment.reeks}</td>
            </tr>
            <tr>
                <td>Verantwoordelijk RecordAid lid</td>
                <c:choose>
                    <c:when test="${not empty opnameMoment.verantwoordelijke}">
                        <td>${opnameMoment.verantwoordelijke.voornaam} ${opnameMoment.verantwoordelijke.achternaam}</td>
                    </c:when>
                    <c:otherwise>
                        <td>Niemand Toegewezen</td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <td>Opname methode</td>
                <td>${methode.beschrijving}</td>
            </tr>
            <tr>
                <td>Lector</td>
                <td>
                    ${opnameMoment.lector.naam}
                    <a href="mailto:${opnameMoment.lector.emailadres}">
                        (${opnameMoment.lector.emailadres})
                    </a>
                </td>
            </tr>
        </table>
    </c:forEach>
    <a class="link" href="<s:url value="/aanvragen/mijnaanvragen"/>">Terug naar het overzicht</a>
</div>