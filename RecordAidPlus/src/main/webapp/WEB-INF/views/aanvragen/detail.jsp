<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<div>
    <h1>Detail aanvraag <%@include file="fragments/aanvraag_datum.jsp" %></h1>
    <m:boodschap/>
    <security:authorize access="hasRole('ADMIN') or hasRole('KERNLID')">
        <a href="<s:url value="/aanvragen/beheer"/>">Terug naar beheer</a>
    </security:authorize>
    <security:authorize access="not hasRole('ADMIN') and not hasRole('KERNLID')">
        <a href="<s:url value="/aanvragen/mijnaanvragen"/>">Terug naar overzicht</a>
    </security:authorize> 
    <security:authorize access="hasRole('ADMIN') or hasRole('KERNLID')">
        <a href="<s:url value="/aanvragen/bewerk?id=${aanvraag.id}"/>">Bewerken</a>
    </security:authorize>
</div>

<div id="aanvraag_algemeen">
    <h2>Algemeen</h2>
    <%--Toon details van deze aanvraag.--%>
    <%@include file="fragments/aanvraag_detail_fragment.jsp"%>
</div>

<div id="aanvraag_lessen">
    <%--Toon de lessen (OpnameMomenten) verbonden met deze aanvraag.--%>
    <h2>Lessen</h2>
    <%@include file="fragments/lessen_fragment.jsp"%>
</div>

<div class="clearfix"></div>