<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account valideren</title>
    </head>
    <body>
        <h1>Emailadres valideren</h1>
        
        <p>We hebben een email gestuurd naar het emailadres dat u opgaf. Gelieve de validatiecode in de email in te geven om uw emailadres te valideren.</p>

        <p style="color: red;">${fout_melding}</p>
        <p style="color: green;">${gelukt_melding}</p>

        <form method="POST" action="ActionServlet">
            <input type="hidden" name="action" value="valideren"/>
            <table>
                <tr>
                    <td><label for="validatiecode">Validatiecode</label></td>
                    <td><input type="text" id="validatiecode" name="validatiecode" maxlength="26"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" value="Account valideren"/></td>
                </tr>

            </table>
        </form>
        
        <%@include file="footer.jsp" %>
    </body>
</html>
