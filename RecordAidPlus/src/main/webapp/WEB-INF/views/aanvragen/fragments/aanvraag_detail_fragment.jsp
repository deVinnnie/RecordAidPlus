<%@ page pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="detailTable">
    <tr>
        <td>Student</td>
        <td><c:out value="${aanvraag.dossier.gebruiker.voornaam} ${aanvraag.dossier.gebruiker.achternaam}"/></td>
    </tr>
    <c:if test="${type eq 'DagAanvraag'}">
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
    <c:if test="${type eq 'MultiPeriodeAanvraag'}">
        <tr>
            <td>Periode</td>
            <td><fmt:formatDate value="${aanvraag.periode.beginTime.time}" pattern="yyyy-MM-dd" />
                tot <fmt:formatDate value="${aanvraag.periode.endTime.time}" pattern="yyyy-MM-dd" />
            </td>
        </tr>
        <c:if test="${not empty aanvraag.begeleider}">
            <tr>
                <td>Begeleider</td>
                <td><c:out value="${aanvraag.begeleider.voornaam} ${aanvraag.begeleider.achternaam}"/></td>
            </tr>
        </c:if>
    </c:if>
    <tr>
        <td>Status</td>
        <td>${aanvraag.status.name}</td>
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
        <td>Opmerkingen</td>
        <td>${aanvraag.opmerking}</td>
    </tr>
    <tr>
        <td>Aanvraagdatum</td>
        <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="yyyy-MM-dd" /></td>
    </tr>
</table>
