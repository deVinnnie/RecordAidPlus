<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<script type="text/javascript" src="JavaScript/account.js" ></script>-->
<h1>Mijn account</h1>
<table>
    <tr>
        <td>E-mailadres</td>
        <td>${gebruiker.emailadres}</td>
    </tr>
    <tr>
        <td>Voornaam</td>
        <td>${gebruiker.voornaam}</td>
    </tr>
    <tr>
        <td>Achternaam</td>
        <td>${gebruiker.achternaam}</td>
    </tr>
    <tr>
        <td>Status</td>
        <td>${gebruiker.rol}</td>
    </tr>
</table>

    <h2>Wachtwoord wijzigen</h2>
    <p class="boodschap" id="wachtwoord_fout_error" style="color: red;">${wachtwoord_fout_error}</p>
    <p class="boodschap" id="wachtwoord_verandert_boodschap" style="color: green;">${wachtwoord_verandert_boodschap}</p>
<form id="form_updateAccount" method="POST">
    <table>
        <tr>
            <td><label for="oud_ww">Oud wachtwoord</label></td>
            <td><input type="password" id="oud_ww" name="oud_ww"></td>
        </tr>
        <tr>
            <td><label for="nieuw_ww1">Nieuw wachtwoord</label></td>
            <td><input type="password" id="nieuw_ww1" name="nieuw_ww1" class="tb"></td>
        </tr>
        <tr>
            <td><label for="nieuw_ww2">Wachtwoord herhalen</label></td>
            <td><input type="password" id="nieuw_ww2" name="nieuw_ww2" class="tb"></td>
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

<c:if test="${gebruiker.rol eq 'STUDENT'}">
    <p id="mail_verstuurd_boodschap" style="color: green;">${mail_verstuurd_boodschap}</p>
    <c:if test="${mail_verstuurd_boodschap eq null}">
        <a class="probleema" href="ActionServlet?action=mailen&method=buddyworden" >Ik wil buddy worden!</a>
    </c:if>
</c:if>