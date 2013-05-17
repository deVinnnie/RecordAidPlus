<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <tr>
            <th>Probleem</th>
            <th>Lokaal</th>
            <th>Datum</th>
            <th>Actief</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="support" items="${supports}">
            <tr>
                <td><c:out value="${support.probleem}"/></td>
                <td><c:out value="${support.lokaal.naam}"/></td>
                <td><fmt:formatDate value="${support.datum.time}" pattern="yyyy-MM-dd"/></td>
                <td>
                    <c:choose>
                        <c:when test="${support.actief}">
                            <s:url var="url" value="/support/deactiveer?id=${support.id}"/>
                            <a href="${url}">Ja</a>
                        </c:when>
                        <c:otherwise>
                            <s:url var="url" value="/support/activeer?id=${support.id}"/>
                            <a href="${url}">Nee</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>