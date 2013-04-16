<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!--
    <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables.css"/>
    <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css"/>
    <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.js"></script>
    <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="JavaScript/aanvragen.js"></script>
</head>-->

<h1>Mijn aanvragen</h1>
<c:choose>
    <c:when test="${empty aanvragen}">
        <p>U heeft momenteel nog geen aanvragen.</p>
    </c:when>
    <c:otherwise>
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
                        <td><a href="<s:url value="aanvragen/detail?id=${aanvraag.id}"/>">Details</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<p>Dien een <a href="<s:url value="/aanvragen/nieuw"/>">nieuwe aanvraag</a> in.</p>