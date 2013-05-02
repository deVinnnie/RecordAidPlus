<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
            <th>Rol</th>
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
                <td>${gebruiker.rol}</td>
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