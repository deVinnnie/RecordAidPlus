<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<!--<script type="text/javascript" src="JavaScript/jquery.js" ></script>
<script type="text/javascript" src="JavaScript/registreervalidatie.js" ></script>-->
<h1>Registreren</h1>
<p id="gebruiker_bestaat_error" class="error">${gebruiker_bestaat_error}</p>
<springforms:form modelAttribute="nieuweGebruiker" id="registratieForm" method="POST">
    <table>
        <tr>
            <td>(*) = verplicht invulveld.</td>
        </tr> 
        <tr>
            <td><label for="email">E-mail adres (KHL)</label></td>
            <td><springforms:input path="email" type="text" id="email" name="email"/></td>
            <td>*</td>
            <td id="email_error" class="error"></td>
        </tr>
        
        <tr>
            <td><label for="voornaam">Voornaam</label></td>
            <td><<springforms:input path="voornaam" type="text" id="voornaam" name="voornaam"/></td>
            <td>*</td>
            <td id="voornaam_error" class="error"></td>
        </tr>
        
        <tr>
            <td><label for="naam">Achternaam</label></td>
            <td><springforms:input path="achternaam" type="text" id="achternaam" name="achternaam"/></td>
            <td>*</td>
            <td id="naam_error" class="error"></td>
        </tr>

        <tr>
            <td><label for="wachtwoord">Wachtwoord</label></td>
            <td><input type="password" id="wachtwoord" name="wachtwoord"/></td>
            <td>*</td>
            <td id="wachtwoord1_error" class="error"></td>
        </tr>
        <tr>
            <td><label for=""wachtwoord_confirmation">Wachtwoord (controle)</label></td>
            <td><input type="password" id="wachtwoord_confirmation" name="wachtwoord_confirmation"/></td>
            <td>*</td>
            <td id=wachtwoord_confirmation_error" class="error"></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>
                <input type="submit" value="Registreren"/>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</springforms:form>