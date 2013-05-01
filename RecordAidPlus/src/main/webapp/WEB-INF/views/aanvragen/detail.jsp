<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<h1>Detail aanvraag opname</h1>
<c:if test="${not empty boodschap}">
    <p class="<c:out value="${boodschap.type}"/>">
        <c:out value="${boodschap.message}"/>
    </p>
</c:if>
<a class="link" href="<s:url value="/aanvragen/mijnaanvragen"/>">Terug naar het overzicht</a>
<a href="<s:url value="/aanvragen/bewerk?id=${aanvraag.id}"/>">Bewerken</a>
<%--Toon details van deze aanvraag.--%>
<%@include file="fragments/aanvraag_detail_fragment.jsp"%>
<%--Toon de lessen (OpnameMomenten) verbonden met deze aanvraag.--%>
<h2>Lessen</h2>
<%@include file="fragments/lessen_fragment.jsp"%>