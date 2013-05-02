<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${not empty opnameMoment.verantwoordelijke}">
        <c:set var="klasse" value="toegewezen"/>
        <c:set var="titel" value="Toegewezen"/>
    </c:when>
    <c:when test="${empty opnameMoment.opname}">
        <c:set var="klasse" value="nietopgenomen"/>
        <c:set var="titel" value="Nog geen opname"/>
    </c:when>
    <c:otherwise>
        <c:set var="klasse" value="opgenomen"/>
        <c:set var="titel" value="Opgenomen"/>
    </c:otherwise>
</c:choose>
<span title="${titel}" class="lesstatus ${klasse}"></span>  