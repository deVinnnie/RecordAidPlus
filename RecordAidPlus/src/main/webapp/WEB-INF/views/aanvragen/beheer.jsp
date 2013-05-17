<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Aanvragen</h1>
<c:choose>
    <c:when test="${empty aanvragen}">
        <p>Er zijn geen aanvragen</p>
    </c:when>
    <c:otherwise>
        <script type="text/javascript"> 
            $(document).ready(function()
            {
                datatable("#aanvragen_tabel");
            });
        </script>
        <table id="aanvragen_tabel">
            <thead>
                <tr>
                    <th>Lesdatum/Periode</th>
                    <th>Aantal Lessen</th>
                    <th>Aanvrager</th>
                    <th>Status</th>
                    <th>Details</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="aanvraag" items="${aanvragen}" >
                    <tr align=center >
                        <td>
                        <%@include file="fragments/aanvraag_datum.jsp" %>
                        </td>
                        <td><c:out value="${aanvraag.opnameMomenten.size()}"/></td>
                        <td>
                            <s:url var="url" value="/gebruikers/dossier?gebruiker=${aanvraag.dossier.gebruiker.emailadres}"/>
                            <a href="${url}">
                            <c:out value="${aanvraag.dossier.gebruiker.voornaam} ${aanvraag.dossier.gebruiker.achternaam}"/>
                            </a>
                        </td>
                        <td><c:out value="${aanvraag.status.name}"/></td>
                        <s:url var="url" value="/aanvragen/detail?id=${aanvraag.id}"/>
                        <td><a href="${url}">Details</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>