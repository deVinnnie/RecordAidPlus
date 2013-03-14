<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachten.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nieuw forumbericht</title>
    </head>

    <body>

        <h1>Een nieuw forumbericht aanmaken</h1>

        <form  method="POST" id="berichtenForm" action="ActionServlet">
            <table>
                <tr>
                    <td>Titel</td>
                    <td><input type="text" name="titel"/></td>
                </tr>

                <tr>
                    <td>Bericht</td>
                    <td><textarea class="ta" name="berichtInhoud"></textarea></td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="hidden" name="action" value="berichten"/>
                        <input type="hidden" name="method" value="voegtoe"/>
                        <input type="submit" value="Bericht plaatsen"/>
                    </td>
                </tr>
            </table>
        </form>
        
        <%@include file="footer.jsp" %>
    </body>
</html>
