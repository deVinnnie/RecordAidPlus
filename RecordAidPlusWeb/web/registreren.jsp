<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="JavaScript/jquery.js" ></script>
        <script type="text/javascript" src="JavaScript/registreervalidatie.js" ></script>
        <title>Registreren</title>
    </head>

    <body>
        <h1>Registreren</h1>

        <p id="gebruiker_bestaat_error" style="color: red;">${gebruiker_bestaat_error}</p>

        <form id="registratieForm" method="POST" action="ActionServlet">
            <table>
                <tr>
                    <td>(*) = verplicht invulveld.</td>
                </tr> 
                <tr>
                    <td><label for="email">E-mail adres (KHL)</label></td>
                    <td><input type="text" id="email" name="email" value="${param.email}" class="tb"/></td>
                    <td>*</td>
                    <td id="email_error" style="color: red;"></td>
                </tr>

                <tr>
                    <td><label for="naam">Naam</label></td>
                    <td><input type="text" id="naam" name="naam" value="${param.naam}"class="tb"/></td>
                    <td>*</td>
                    <td id="naam_error" style="color: red;"></td>
                </tr>

                <tr>
                    <td><label for="voornaam">Voornaam</label></td>
                    <td><input type="text" id="voornaam" name="voornaam" value="${param.voornaam}"class="tb"/></td>
                    <td>*</td>
                    <td id="voornaam_error" style="color: red;"></td>
                </tr>

                <tr>
                    <td><label for="wachtwoord1">Wachtwoord</label></td>
                    <td><input type="password" id="wachtwoord1" name="wachtwoord1"class="tb"/></td>
                    <td>*</td>
                    <td id="wachtwoord1_error" style="color: red;"></td>
                </tr>
                <tr>
                    <td><label for="wachtwoord2">Wachtwoord (controle)</label></td>
                    <td><input type="password" id="wachtwoord2" name="wachtwoord2"class="tb"/></td>
                    <td>*</td>
                    <td id="wachtwoord2_error" style="color: red;"></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="hidden" name="action" value="registreer"/>
                        <input type="submit" value="Registreren"/>
                    </td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </form>
        <%@include file="footer.jsp" %>

    </body>
</html>
