<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<h1>Registreren</h1>
<m:boodschap/>
<security:authorize access="isAnonymous()">
    <springforms:errors path="*" cssClass="error"/>
    <springforms:form modelAttribute="nieuweGebruiker" id="registratieForm" method="POST">
        <table>
            <tr>
                <td>Alle velden zijn verplicht in te vullen.</td>
            </tr> 
            <tr>
                <td><label for="email">E-mail adres (KHLeuven)</label></td>
                <td><springforms:input path="emailadres" type="text" id="emailadres" required="required"/></td>
                <td id="email_error" class="error"></td>
            </tr>
            <tr>
                <td><label for="voornaam">Voornaam</label></td>
                <td><springforms:input path="voornaam" type="text" id="voornaam" required="required"/></td>
                <td id="voornaam_error" class="error"></td>
            </tr>
            <tr>
                <td><label for="achternaam">Achternaam</label></td>
                <td><springforms:input path="achternaam" type="text" id="achternaam" required="required"/></td>
                <td id="achternaam_error" class="error"></td>
            </tr>
            <tr>
                <td><label for="wachtwoord">Wachtwoord</label></td>
                <td><input type="password" id="wachtwoord" name="wachtwoord" required="required"/></td>
                <td id="wachtwoord_error" class="error"></td>
            </tr>
            <tr>
                <td><label for="wachtwoord_confirmation">Wachtwoord (controle)</label></td>
                <td><input type="password" id="wachtwoord_confirmation" name="wachtwoord_confirmation" required="required"/></td>
                <td id="wachtwoord_confirmation_error" class="error"></td>
            </tr>
            <tr>
                <td><label for="geinteresseerd">Geinteresseerd</label></td>    
                <td><springforms:checkbox path="geinteresseerd" id="geinteresseerd"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>
                    <input type="submit" value="Registreren"/>
                </td>
            </tr>
        </table>
    </springforms:form>
</security:authorize>
<security:authorize access="isAuthenticated()">
    <p class="error">Je bent al registreerd!</p>
</security:authorize>