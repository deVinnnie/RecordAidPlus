<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Mijn account</h1>
<springforms:form method="POST" modelAttribute="gebruiker">
    <table>
        <tr>
            <td>E-mailadres</td>
            <td>${gebruiker.emailadres}</td>
        </tr>
        <%@include file="fragments/naam_editor.jsp" %>
        <tr>
            <td>Rol</td>
            <td>
                <ul>
                    <c:forEach var="rol" items="${gebruiker.rollen}">
                        <li>${rol.name}</li>
                        </c:forEach>
                </ul>
            </td>
        </tr>
    </table>
    <input type="submit" value="Opslaan"/>
</springforms:form>

<h2>Wachtwoord wijzigen</h2>
<p id="error" class="error"></p>
<form id="form_updateAccount" method="POST">
    <table>
        <tr>
            <td><label for="oud_ww">Oud wachtwoord</label></td>
            <td><input type="password" id="oud_ww" name="oud_ww"></td>
        </tr>
        <tr>
            <td><label for="nieuw_ww1">Nieuw wachtwoord</label></td>
            <td><input type="password" id="nieuw_ww1" name="nieuw_ww1"></td>
        </tr>
        <tr>
            <td><label for="nieuw_ww2">Wachtwoord herhalen</label></td>
            <td><input type="password" id="nieuw_ww2" name="nieuw_ww2"></td>
        </tr>
        <tr>
            <td>&nbsp;</td>  
            <td> 
                <input type="hidden" name="action" value="update_password"/>
                <input type="submit" value="Wachtwoord opslaan"/>
            </td>
        </tr>
    </table>
</form> 

<security:authorize access="hasRole('STUDENT') and not hasRole('BUDDY')">
    <m:boodschap/>
    <a href="<s:url value="/gebruikers/buddyworden"/>">Ik wil buddy worden!</a>
</security:authorize>