<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Bevestig Aanvraag</h1>
<p>Ziet dit er goed uit?</p>
<%--Toon details van deze aanvraag.--%>
<%@include file="fragments/aanvraag_detail_fragment.jsp"%>
<%--Toon de lessen (OpnameMomenten) verbonden met deze aanvraag.--%>
<h2>Lessen</h2>
<%@include file="fragments/lessen_fragment.jsp"%>
<h2>Bevestigen</h2>
<form method="POST">
    <input type="submit" value="Ok"/>
    <input type="submit" value="Annuleren"/>
</form>