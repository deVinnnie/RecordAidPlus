<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachtenBuddies.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Items beheren</title>
        <script type="text/javascript" src="JavaScript/jquery.js" ></script>
        <script type="text/javascript" src="JavaScript/items.js"></script>
    </head>

    <body>
        <h1>Items beheren</h1>

        <h2>Huidige items</h2>

        <c:choose>
            <c:when test="${not empty items}">
                <table class="htmlTable">
                    <tr>
                        <th>Item naam</th>
                        <th>Verwijderen</th>
                    </tr>

                    <c:forEach items="${items}" var="item">
                        <tr>
                            <td>${item.naam}</td>
                            <td><a class="probleema" href="ActionServlet?action=itemBeheer&itemNaam=${item.naam}">Verwijder</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>Er zijn momenteel nog geen items.</p>
            </c:otherwise>
        </c:choose>
        <h2>
            Voeg een nieuw item toe
        </h2>

        <p style="color: red;" id="error">${error}</p>

        <form id="formAddItem" method="POST">
            <table>
                <tr>
                    <td><label for="naam">Naam van het item</label></td>
                    <td><input class="tb" type="text" name="naam" id="naam"/></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="submit" value="Voeg toe"/>
                        <input type="hidden" name="action" value="itemBeheer"/>
                    </td>
                </tr>
            </table>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
