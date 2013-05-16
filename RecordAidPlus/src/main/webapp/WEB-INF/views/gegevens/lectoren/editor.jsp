<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<c:choose>
    <c:when test="${not empty lector}">
        <c:set var="actie" value="Wijzigen"/>
    </c:when>
    <c:otherwise>
        <c:set var="actie" value="Toevoegen"/>
    </c:otherwise>
</c:choose>

<h1>Lector ${actie}</h1>
<springforms:form modelAttribute="lector" method="POST">
    <table>
        <tr>
            <td><label for="emailadres">E-mailadres</label></td>
            <td><springforms:input type="text" path="emailadres" id="emailadres"/></td>
        </tr>
        <tr>
            <td><label for="naam">Naam</label></td>
            <td><springforms:input type="text" path="naam" id="naam"/></td>
        </tr>
        <tr>
            <td><label for="autonoom">Autonoom opnemen</label></td>
            <td>
                <springforms:checkbox path="autonoomOpnemen" id="autonoom"/>
            </td>
        </tr>
    </table>
    
    <input type="submit" value="${actie}"/>
</springforms:form>