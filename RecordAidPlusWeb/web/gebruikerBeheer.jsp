<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachtenBuddies.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gebruikers Beheren</title>
        <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables.css"/>
        <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css"/>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.js"></script>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="JavaScript/gebruikerBeheer.js"></script>
    </head>

    <body>
        <h1>Gebruikers Beheren</h1>
        <table id="gebruikersTabel" >
            <thead>
                <tr>
                    <th>Voornaam</th>
                    <th>Naam</th>
                    <th>Emailadres</th>
                    <th>Rol</th>
                    <th>Gevalideerd</th>
                    <th>Details</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${gebruikers}" var="gebruiker">
                    <tr>
                        <td>${gebruiker.voornaam}</td>
                        <td>${gebruiker.naam}</td>
                        <td>${gebruiker.emailadres}</td>
                        <td>${gebruiker.rol}</td>
                        <td>${gebruiker.gevalideerd}</td>
                        <td><a href="ActionServlet?action=gebruikerDetail&gebruiker=${gebruiker.emailadres}">Details</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <%@include file="footer.jsp" %>
    </body>
</html>