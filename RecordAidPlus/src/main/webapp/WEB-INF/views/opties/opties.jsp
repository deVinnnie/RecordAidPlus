<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<h1>Setup</h1>
<m:boodschap/>
<p>Dit is de eerste keer dat de administrator inlogd. Er zijn nog enkele opties die ingevuld moeten worden.</p>

<form method="POST">
    <h2>Verander het wachtwoord van de administrator.</h2>
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

    <h2>Vul de gegevens van het mail-account in.</h2>
    <p>Via de opgegeven account worden automatische mails verzonden door de site.</p>
    <table>
        <tr>
            <td><label for="email">E-mailadres</label></td>
            <td><input type="email" id="email" name="email"/></td>
        </tr>
        <tr>
            <td><label for="email_password">Wachtwoord</label></td>
            <td><input type="password" id="email_password" name="email_wachtwoord"/></td>
        </tr>
    </table>
    <input type="submit" value="Opslaan"/>
</form>