<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!--<link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables.css"/>
<link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css"/>
<script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.js"></script>
-->
<script type="text/javascript">
$(document).ready(function()
    {
        $('#gebruikersTabel').dataTable();
    } );
</script>
<h1>Gebruikers Beheren</h1>
<table id="gebruikersTabel">
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
                <td>${gebruiker.achternaam}</td>
                <td>${gebruiker.emailadres}</td>
                <td>${gebruiker.rol}</td>
                <td>${gebruiker.gevalideerd}</td>
                <td><a href="<s:url value="/gebruikers/detail"/>&emailadres=${gebruiker.emailadres}">Details</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>