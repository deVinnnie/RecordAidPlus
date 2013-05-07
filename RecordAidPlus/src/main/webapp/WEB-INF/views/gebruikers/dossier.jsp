<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1><c:out value="Dossier - ${dossier.gebruiker.voornaam} ${dossier.gebruiker.achternaam}"/></h1>
Hoofdverantwoordelijke: <c:out value="${dossier.verantwoordelijke.voornaam} ${dossier.verantwoordelijke.achternaam}" default="Niemand"/>

<h2>Aanvragen</h2>
<c:choose>
    <c:when test="${not empty dossier.aanvragen}">
            <table>
                <thead>
                    <tr>
                        <th>Type</th>
                        <th>Aanvraagdatum</th>
                        <th>Reden</th>
                        <th>Status</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="aanvraag" items="${dossier.aanvragen}">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${aanvraag.getClass().getSimpleName() eq 'DagAanvraag'}">
                                    Dag
                                </c:when>
                                <c:when test="${aanvraag.getClass().getSimpleName() eq 'MultiPeriodeAanvraag'}">
                                    Periode
                                </c:when>
                                <c:otherwise>Onbekend</c:otherwise>
                            </c:choose>        
                        </td>
                        <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="yyyy-MM-dd"/></td>
                        <td>${aanvraag.reden}</td>
                        <td>${aanvraag.status}</td>
                        <td><a href="<s:url value="/aanvragen/detail?id=${aanvraag.id}"/>"/>Details</a></td>
                    </tr> 
                    </c:forEach>
                </tbody>
            </table>
    </c:when>
    <c:otherwise>
        <p>Er zijn nog geen aanvragen in dit dossier. </p>
    </c:otherwise>
</c:choose>
        
<h2>Geschiedenis</h2>
<c:choose>
    <c:when test="${not empty dossier.geschiedenis.gebeurtenissen}">
            <table>
                <thead>
                    <tr>
                        <th>Datum</th>
                        <th>Gebeurtenis</th>
                        <th>Wie?</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach var="gebeurtenis" items="${dossier.geschiedenis.gebeurtenissen}">
                    <tr>
                        <td><fmt:formatDate value="${gebeurtenis.tijdstip.time}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td><c:out value="${gebeurtenis.message}"/></td>
                        <td><c:out value="${gebeurtenis.betrokkenGebruiker.voornaam} ${gebeurtenis.betrokkenGebruiker.achternaam}"/></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
    </c:when>
    <c:otherwise>
        <p>Er is nog geschiedenis voor dit dossier.</p>
    </c:otherwise>
</c:choose>