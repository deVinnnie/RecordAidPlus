<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
$('document').ready(function()
    {
        datatable("#lectoren"); 
    });
</script>
<h1>Gegevens</h1>
<h2>Lectoren</h2>
<table id="lectoren">
    <thead>
        <tr>
            <th>Naam</th>
            <th>E-mailadres</th>
            <th>Zelf Opnemen?</th>
            <th>Verwijder</th>
            <th>Bewerk</th>
        </tr>
    </thead> 
    <tbody>
        <c:forEach var="lector" items="${lectoren}">
            <tr>
                <td>${lector.naam}</td>
                <td>${lector.emailadres}</td>
                <td>
                    <c:choose>
                        <c:when test="${lector.autonoomOpnemen}">
                            Ja
                        </c:when>
                        <c:otherwise>
                            Nee
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><a href="<s:url value="/gegevens/lectoren/verwijder?lector=${lector.emailadres}"/>">Verwijder</a></td>
                <td><a href="<s:url value="/gegevens/lectoren/bewerk?lector=${lector.emailadres}"/>">Bewerk</a></td>
            </tr>  
        </c:forEach>
    </tbody>
</table>