<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachten.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables.css"/>
        <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css"/>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.js"></script>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="JavaScript/aanvragen.js"></script>
        <title>Aanvragen</title>
    </head>
    
    <body>
        <h1>Aanvragen</h1>

        <p>${geen_aanvragen}</p>

        <c:if test="${geen_aanvragen == null}">
            <table id="aanvragen_tabel">
                <thead>
                    <tr>
                        <th>Lesdatum</th>
                        <th>Begin uur</th>
                        <th>Eind uur</th>
                        <th>Vak</th>
                        <th>Lokaal</th>
                        <th>Reeks</th>
                        <th>Status</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="aanvraag" items="${aanvragen}" >
                        <tr align=center >
                            <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="dd/MM/yyyy" /></td>
                            <td>${aanvraag.beginUur}</td>
                            <td>${aanvraag.eindUur}</td>
                            <td>${aanvraag.optenemenVak}</td>
                            <td>${aanvraag.lokaal}</td>
                            <td>${aanvraag.klasReeks}</td>
                            <td>${aanvraag.status}</td>
                            <td><a href="ActionServlet?action=detail&id=${aanvraag.id}">Details</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <%@include file="footer.jsp" %>
    </body>
</html>
