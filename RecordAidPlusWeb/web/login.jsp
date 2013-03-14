<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="JavaScript/jquery.js" ></script>
        <script type="text/javascript" src="JavaScript/loginValidatie.js" ></script>
        <title>Inloggen</title>
    </head>

    <body>
        <%@include file="header.jsp" %>

        <h1>Inloggen</h1>

        <p id="gegevens_onjuist" style="color: red;">${gegevens_onjuist}</p>

        <form id="registratieForm" method="POST" action="ActionServlet">
            <table>
                <tr>
                    <td><label for="email">E-mail adres (KHL)</label></td>
                    <td><input type="text" id="email" name="email" class="tb"/></td>
                    <td>*</td>
                    <td id="email_error" style="color: red;"></td>
                </tr>

                <tr>
                    <td><label for="wachtwoord">Paswoord</label></td>
                    <td><input type="password" id="wachtwoord" name="wachtwoord" class="tb"/></td>
                    <td>*</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="hidden" name="action" value="login"/>
                        <input type="submit" value="Log in" />
                    </td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </form>
        <p> Nog geen recordaid account? Registreer je op onze <a href="registreren.jsp">registratie-pagina.</a></p>
        <%@include file="footer.jsp" %>


    </body>
</html>
