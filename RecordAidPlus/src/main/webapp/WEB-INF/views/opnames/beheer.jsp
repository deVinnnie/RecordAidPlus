<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<h1>Opnames Beheren</h1>
<script>
$(document).ready(function()
{
    datatable("#opnames"); 
    datatable("#opnameMethoden"); 
}); 
</script>
<h2>Overzicht Opnames</h2>
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
        <c:forEach var="opname" items="${opnames}">
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

<h2>Overzicht Opnamemethoden</h2>
<table id="opnameMethoden">
    <thead>
        <tr>
            <th>Naam</th>
            <th>Beschrijving</th>
            <th>Verwijderen</th>
        </tr>
    </thead>
    <tbody>
       <c:forEach var="opnameMethode" items="${opnameMethodes}">
            <tr>
                <td>${opnameMethode.naam}</td>
                <td>${opnameMethode.beschrijving}</td>
                <td><a href="<s:url value="/opnames/verwijdermethode?id=${opnameMethode.id}"/>">Verwijder</a></td>
            </tr>
        </c:forEach>
    </tbody>    
</table>
<a href="<s:url value="/opnames/nieuw_opnamemethode"/>">Nieuwe Opnamemethode</a>
