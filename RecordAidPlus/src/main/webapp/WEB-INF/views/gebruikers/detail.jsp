<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<h1>Details van <c:out value="${gebruiker.voornaam} ${gebruiker.achternaam}"/></h1>
<security:authorize access="hasRole('ADMIN')">
    <a href="<s:url value="/gebruikers/valideer?emailadres=${gebruiker.emailadres}"/>">Valideren</a>
    <a href="<s:url value="/gebruikers/devalideer?emailadres=${gebruiker.emailadres}"/>">Devalideren</a>
    <a href="<s:url value="/gebruikers/wachtwoord_reset?emailadres=${gebruiker.emailadres}"/>">Wachtwoord wijzigen naar temp</a>
</security:authorize>
<springforms:form  modelAttribute="gebruiker" method="POST">
    <table>
        <%@include file="fragments/naam_editor.jsp" %>
        <tr>
            <td>E-mailadres</td>
            <td>${gebruiker.emailadres}</td>
        </tr>
        <tr>
            <td>Rollen</td>
            <td>
                <security:authorize access="hasRole('ADMIN')">
                    <springforms:checkboxes path="rollen" itemLabel="name" items="${rollen}" delimiter="<br/>"/>
                </security:authorize>
                <security:authorize access="not hasRole('ADMIN')">
                    <ul>
                        <c:forEach var="rol" items="${gebruiker.rollen}">
                            <li>${rol.name}</li>
                            </c:forEach>
                    </ul>
                </security:authorize>
            </td> 
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
    </table>
    <input type="submit" name="action" value="Bijwerken"/>
</springforms:form>

<security:authentication var="currentGebruiker" property="principal"/>
<c:if test="${currentGebruiker eq gebruiker}">
    <h2>Wachtwoord wijzigen</h2>
    <p id="error" class="error"></p>
    <s:url var="action" value="/gebruikers/wachtwoord"/>
    <form id="form_updateAccount" action="${action}" method="POST">
        <table>
            <tr>
                <td><label for="wachtwoord">Nieuw wachtwoord</label></td>
                <td><input type="password" id="wachtwoord" name="wachtwoord"/></td>
            </tr>
            <tr>
                <td><label for="wachtwoord_confirmation">Nieuw wachtwoord herhalen</label></td>
                <td><input type="password" id="wachtwoord_confirmation" name="wachtwoord_confirmation"/></td>
            </tr>
        </table> 
        <input type="submit" value="Wachtwoord opslaan"/>
    </form> 
</c:if>

<security:authorize access="hasRole('STUDENT') and not hasRole('BUDDY')">
    <m:boodschap/>
    <a href="<s:url value="/gebruikers/buddyworden"/>">Ik wil buddy worden!</a>
</security:authorize>