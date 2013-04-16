<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!--@include file="paginaCheck/checkGeenStudentenLeerkrachten.jsp" %>-->
<!--<link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables.css"/>
<link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css"/>
<script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.js"></script>
<script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.min.js"></script>-->
<h1>Aanvragen</h1>
<c:choose>
    <c:when test="${empty aanvragen}">
        <p>Er zijn geen aanvragen</p>
    </c:when>
    <c:otherwise>
        <script type="text/javascript"> 
            $(document).ready(function()
            {
                $('#aanvragen_tabel').dataTable();
            });
            </script>
        <table id="aanvragen_tabel">
            <thead>
                <tr>
                    <th>Lesdatum</th>
                    <th>Begin</th>
                    <th>Eind</th>
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
                        <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" /></td>
                        <td><fmt:formatDate value="${aanvraag.beginTijdstip}" pattern="hh:mm"/></td>
                        <td><fmt:formatDate value="${aanvraag.eindTijdstip}" pattern="hh:mm"/></td>
                        <td>${aanvraag.optenemenVak}</td>
                        <td>${aanvraag.lokaal}</td>
                        <td>${aanvraag.reeks}</td>
                        <td>${aanvraag.status}</td>
                        <td><a href="<s:url value="/aanvragen/detail?id=${aanvraag.id}"/>">Details</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>