<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    $(document).ready(function()
    {
        datatable("#tabel");
    });
</script>
<h1>Gebruikers Beheren</h1>
<table id="tabel">
    <thead>
        <tr>
            <th>Voornaam</th>
            <th>Naam</th>
            <th>Emailadres</th>
            <th>Rollen</th>
            <th>Gevalideerd</th>
            <th>Details</th>
            <th>Dossier</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${gebruikers}" var="gebruiker">
            <tr>
                <td>${gebruiker.voornaam}</td>
                <td>${gebruiker.achternaam}</td>
                <td>${gebruiker.emailadres}</td>
                <td>
                    <c:forEach items="${gebruiker.rollen}" var="rol">
                        <c:out value="${rol.name}"/><br>
                    </c:forEach>
                </td>
                <td><c:choose>
                        <c:when test="${gebruiker.gevalideerd}">
                            Ja
                        </c:when>
                        <c:otherwise>
                            Nee
                        </c:otherwise>
                    </c:choose></td>
                <td><a href="<s:url value="/gebruikers/detail?emailadres=${gebruiker.emailadres}"/>">Details</a></td>
                <td><a href="<s:url value="/gebruikers/dossier?gebruiker=${gebruiker.emailadres}"/>">Dossier</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h1>Alle gebeurtenissen</h1>
<script type="text/javascript">
    $('document').ready(function() {
        datatable("#geschiedenis");
    });
</script>
<table id="geschiedenis">
    <thead>
        <tr>
            <th>Datum</th>
            <th>Dossier</th>
            <th>Gebeurtenis</th>
            <th>Wie?</th>
        </tr>
    </thead>
    <tbody> 
        <c:forEach var="dossier" items="${dossiers}">
            <c:forEach var="gebeurtenis" items="${dossier.geschiedenis.gebeurtenissen}">
                <tr>
                    <td><fmt:formatDate value="${gebeurtenis.tijdstip.time}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><c:out value="${dossier.gebruiker.voornaam} ${dossier.gebruiker.achternaam}"/></td>
                    <td><c:out value="${gebeurtenis.message}"/></td>
                    <td><c:out value="${gebeurtenis.betrokkenGebruiker.voornaam} ${gebeurtenis.betrokkenGebruiker.achternaam}"/></td>
                </tr>
            </c:forEach>
        </c:forEach>
    </tbody>
</table>