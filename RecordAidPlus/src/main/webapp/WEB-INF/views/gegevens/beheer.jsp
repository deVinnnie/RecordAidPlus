<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">
    $('document').ready(function()
    {
        datatable("#lectoren");
        datatable("#departementen");
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
                <td><a href="<s:url value="/gegevens/lectoren/verwijder?id=${lector.id}"/>">Verwijder</a></td>
                <td><a href="<s:url value="/gegevens/lectoren/bewerk?id=${lector.id}"/>">Bewerk</a></td>
            </tr>  
        </c:forEach>
    </tbody>
</table>

<h2>Departementen</h2>
<table id="departementen">
    <thead>
        <tr>
            <th>Naam</th>
            <th>Actief</th>
        </tr>
    </thead> 
    <tbody>
        <c:forEach var="departement" items="${departementen}">
            <tr>
                <td>${departement.naam}</td>
                <td>
                    <c:choose>
                        <c:when test="${departement.actief}">
                            <s:url var="deactiveer" value="/gegevens/departementen/deactiveer">
                                <s:param name="naam" value="${departement.naam}"/>
                            </s:url>
                            <a href="${deactiveer}">Ja</a>
                        </c:when>
                        <c:otherwise>
                            <s:url var="activeer" value="/gegevens/departementen/activeer">
                                <s:param name="naam" value="${departement.naam}"/>
                            </s:url>
                            <a href="${activeer}">Nee</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>  
        </c:forEach>
    </tbody>
</table>