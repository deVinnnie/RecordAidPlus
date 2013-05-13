<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Support</h1>
<script type="text/javascript">
    $('document').ready(function()
    {
        datatable("#problemen");
    });
</script>
<table id="problemen">
    <thead>
    <th>Probleem</th>
    <th>Lokaal</th>
    <th>Datum</th>
    <th>Actief</th>
</thead>
<tbody>
    <c:forEach var="support" items="${supports}">
        <tr>
            <td>${support.probleem}</td>
            <td>${support.lokaal.naam}</td>
            <td><fmt:formatDate value="${support.datum.time}" pattern="yyyy-MM-dd"/></td>
            <td>
                <c:choose>
                    <c:when test="${support.actief}">
                        <a href="<s:url value="/support/deactiveer?id=${support.id}"/>">Ja</a>
                    </c:when>
                    <c:otherwise>
                        <a href="<s:url value="/support/activeer?id=${support.id}"/>">Nee</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</tbody>
</table>
