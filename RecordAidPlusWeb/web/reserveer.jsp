<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudenten.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.1.custom 2/css/blitzer/jquery-ui-1.10.1.custom.css"/>
        <script type="text/javascript" src="jquery-ui-1.10.1.custom 2/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="jquery-ui-1.10.1.custom 2/js/jquery-ui-1.10.1.custom.js"></script>
        <script type="text/javascript" src="jquery-ui-1.10.1.custom 2/js/jquery-ui-1.10.1.custom.min.js"></script>
        <script type="text/javascript" src="JavaScript/reserveren.js"></script>
        <title>Item Reserveren</title>
    </head>

    <body>
        <c:choose>
            <c:when test="${not empty items}">
                <form id="formReserveer" method="POST" action="ActionServlet">
                    <tr>
                        <td>
                            <label for="item">Item</label>
                        </td>
                        <td>
                            <select id="item" name="item">
                                <c:forEach items="${items}" var="item">
                                    <option value="${item.naam}" <c:if test="${param.item == item.naam}">selected</c:if>>${item.naam}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="datum">Datum</label>
                        </td>
                        <td>
                            <input class="tb" type="text" id="datum" name="datum" value="${param.datum}" readonly="true"/>
                            <input type="hidden" name="action" value="reserveren"/>
                        </td>
                    </tr>
                </form>
                <c:choose>
                    <c:when test="${not empty reservaties}">                    
                        <table class="htmlTable" id="reservatieTabel">
                            <thead>
                                <tr>
                                    <th>Slot</th>
                                    <th>Reserveer</th>
                                    <th>Verwijder</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${reservaties}" var="reservatie">
                                    ${reservatie}
                                </c:forEach>
                            </tbody>
                        </c:when>
                        <c:otherwise>
                            <p>Gelieve een datum en een item te selecteren.</p>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <p>Er zijn momenteel nog geen items die gereserveerd kunnen worden.
                    </c:otherwise>
                </c:choose>
        </table>
        
        <%@include file="footer.jsp" %>
    </body>
</html>
