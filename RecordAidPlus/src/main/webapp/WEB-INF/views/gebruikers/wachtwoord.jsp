<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/"%>
<form method="POST">
    <h2>Verander je wachtwoord.</h2>
    <m:boodschap/>
    <table>
        <tr>
            <td><label for="wachtwoord">Wachtwoord</label></td>
            <td><input type="password" id="wachtwoord" name="wachtwoord"/></td>
        </tr>
        <tr>
            <td><label for="wachtwoord_confirmation">Herhaal wachtwoord</label></td>
            <td><input type="password" id="wachtwoord_confirmation" name="wachtwoord_confirmation"/></td>
        </tr>
    </table>
    <input type="submit" value="Opslaan"/>
</form>