<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${aanvraag.getClass().getSimpleName() eq 'DagAanvraag'}">
        <fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" />
    </c:when>
    <c:when test="${aanvraag.getClass().getSimpleName() eq 'MultiPeriodeAanvraag'}">
        <fmt:formatDate value="${aanvraag.periode.beginTime.time}" pattern="yyyy-MM-dd" />
        tot 
        <fmt:formatDate value="${aanvraag.periode.endTime.time}" pattern="yyyy-MM-dd" />
    </c:when>
</c:choose>