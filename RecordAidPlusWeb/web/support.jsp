<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkIngelogd.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Technisch probleem melden</title>
    </head>
    <body>

        <h1>Technisch probleem melden</h1>

        <p>
            Heeft u een technisch probleem opgemerkt, zoals bijvoorbeeld een microfoon die niet werkt? Vul dat dit formulier in om de RecordAid medewerkers hiervan op de hoogte te stellen!
        </p>
        
        <p style="color: green;">${succes}</p>
        <p style="color: red;">${error}</p>

        <form id="supportForm" method="POST" action="ActionServlet">
            <table>
                <tr>
                    <td><label for="lokaal">Lokaal</label></td>
                    <td><input type="text" id="lokaal" name="lokaal" class="tb"/></td>
                </tr>
                <tr>
                    <td><label for="opmerking">Probleem</label></td>
                    <td><textarea rows="6" cols="50" name="opmerking" class="ta"></textarea></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="hidden" name="action" value="support" >
                        <input type="submit" value="Verzenden"/>
                    </td>
                </tr>
            </table>
        </form>
        
        
        <%@include file="footer.jsp" %>

    </body>
</html>
