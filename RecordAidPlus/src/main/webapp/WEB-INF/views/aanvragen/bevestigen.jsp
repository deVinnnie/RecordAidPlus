<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h1>Bevestig Aanvraag</h1>
    <form method="POST">
        <span class="notification">Ziet dit er goed uit?</span>
        <input type="submit" name="action" value="Ok"/>
        <input type="submit" name="action" value="Annuleren"/>
    </form>
</div>
<%--Toon details van deze aanvraag.--%>
<div id="aanvraag_algemeen">
    <%@include file="fragments/aanvraag_detail_fragment.jsp"%>
</div>
<%--Toon de lessen (OpnameMomenten) verbonden met deze aanvraag.--%>
<div id="aanvraag_lessen">
    <h2>Lessen</h2>
    <%@include file="fragments/lessen_fragment.jsp"%>
</div>
<div class="clearfix"></div>