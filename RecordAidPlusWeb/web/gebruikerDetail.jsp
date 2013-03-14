<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachtenBuddies.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details van ${gebruiker.voornaam} ${gebruiker.naam}</title>
    </head>

    <body>
        <h1>Details van ${gebruiker.voornaam} ${gebruiker.naam}</h1>
        <form method="POST" action="ActionServlet">
            <table class="accountTable">
                <tr>
                    <td>Voornaam</td>
                    <td><input class="tb" type="text" name="voornaam" value="${gebruiker.voornaam}"/></td>
                </tr>
                <tr>
                    <td>Naam</td>
                    <td><input class="tb" type="text" name="naam" value="${gebruiker.naam}"/></td>
                </tr>
                <tr>
                    <td>Emailadres</td>
                    <td>${gebruiker.emailadres}</td>
                </tr>
                <tr>
                    <td>Rol</td>
                    <td><select name="rol">
                            <option <c:if test="${gebruiker.rol == 'STUDENT'}">selected</c:if>>STUDENT</option>
                            <option <c:if test="${gebruiker.rol == 'LEERKRACHT'}">selected</c:if>>LEERKRACHT</option>
                            <option <c:if test="${gebruiker.rol == 'OPLEIDINGSHOOFD'}">selected</c:if>>OPLEIDINGSHOOFD</option>
                            <option <c:if test="${gebruiker.rol == 'BUDDY'}">selected</c:if>>BUDDY</option>
                            <option <c:if test="${gebruiker.rol == 'KERNLID'}">selected</c:if>>KERNLID</option>
                            <option <c:if test="${gebruiker.rol == 'ADMIN'}">selected</c:if>>ADMIN</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Gevalideerd</td>
                    <td>${gebruiker.gevalideerd}</td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="gebruiker" value="${gebruiker.emailadres}"/>
                        <input type="hidden" name="action" value="gebruikerDetail"/>
                        &nbsp;
                    </td>
                    <td><input type="submit" name="action2" value="Gebruiker bijwerken"/></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="gebruiker" value="${gebruiker.emailadres}"/>
                        <input type="hidden" name="action" value="gebruikerDetail"/>
                        &nbsp;
                    </td>
                    <td><input type="submit" name="action2" value="Gebruiker valideren"/></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="gebruiker" value="${gebruiker.emailadres}"/>
                        <input type="hidden" name="action" value="gebruikerDetail"/>
                        &nbsp;
                    </td>
                    <td><input type="submit" name="action2" value="Gebruiker devalideren"/></td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="gebruiker" value="${gebruiker.emailadres}"/>
                        <input type="hidden" name="action" value="gebruikerDetail"/>
                        &nbsp;
                    </td>
                    <td><input type="submit" name="action2" value="Wachtwoord wijzigen naar temp"/></td>
                </tr>
            </table>
        </form>

        <%@include file="footer.jsp" %>
    </body>
</html>


