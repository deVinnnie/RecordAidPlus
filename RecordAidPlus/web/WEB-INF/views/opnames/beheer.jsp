<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Opnames Beheren</h1>
<script>
$(document).ready(function()
{
    datatable("opnames"); 
}); 
</script>
<table id="opnames">
    <thead>
        <tr>
            <th>Datum</th>
            <th>OOD</th>
            <th>Lector</th>
            <th>Zichtbaar</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="opname" items="${opnames.opnameMomenten}" varStatus="loop">
            <tr>
                <td><fmt:formatDate value="${opname.beginTijdstip.time}" pattern="yyyy-MM-dd" /></td>
                <td>${opname.OOD}</td>
                <td>${opname.lector.naam}(${opname.lector.emailadres})</td>
                <td><c:choose>
                        <c:when test="${opname.zichtbaar}">
                            Zichtbaar
                        </c:when>
                        <c:otherwise>
                            Niet zichtbaar
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="<s:url value="/opnames/editor?id=${opname.id}"/>">Bewerken</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>    
</table>
<a href="<s:url value="/opnames/editor"/>">Nieuwe Opname</a>