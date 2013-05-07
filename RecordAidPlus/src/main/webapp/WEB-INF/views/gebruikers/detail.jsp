<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<%--"paginaCheck/checkGeenStudentenLeerkrachtenBuddies.jsp" --%>
<h1>Details van <c:out value="${gebruiker.voornaam} ${gebruiker.achternaam}"/></h1>
<a href="<s:url value="/gebruikers/valideer?emailadres=${gebruiker.emailadres}"/>">Valideren</a>
<a href="<s:url value="/gebruikers/devalideer?emailadres=${gebruiker.emailadres}"/>">Devalideren</a>
<a href="<s:url value="/gebruikers/wachtwoord_reset?emailadres=${gebruiker.emailadres}"/>">Wachtwoord wijzigen naar temp</a>
<springforms:form  modelAttribute="gebruiker" method="POST">
    <table>
        <tr>
            <td>Voornaam</td>
            <td><springforms:input type="text" path="voornaam"/></td>
        </tr>
        <tr>
            <td>Achternaam</td>
            <td><springforms:input type="text" path="achternaam"/></td>
        </tr>
        <tr>
            <td>E-mailadres</td>
            <td>${gebruiker.emailadres}</td>
        </tr>
        <tr>
            <td>Rollen</td>
            <td><springforms:checkboxes path="rollen" itemLabel="name" items="${rollen}" delimiter="<br/>"/></td>
        </tr>
        <tr>
            <td>Gevalideerd</td>
            <td>
                <c:choose>
                    <c:when test="${gebruiker.gevalideerd}">
                        Ja
                    </c:when>
                    <c:otherwise>
                        Nee
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" name="action" value="Bijwerken"/></td>
        </tr>
    </table>
</springforms:form>