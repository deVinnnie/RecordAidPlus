<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<div id="subnav">
    <h1>Beheer</h1>
    <ul>
        <li><a href="<s:url value="/aanvragen/beheer"/>">Aanvragen</a></li>
        <li><a href="<s:url value="/faq/beheer"/>">FAQ</a></li>
        <li><a href="<s:url value="/gebruikers/beheer"/>">Gebruikers</a></li>
        <li><a href="<s:url value="/items/beheer"/>">Items</a></li>
        <li><a href="<s:url value="/opnames/beheer"/>">Opnames</a></li>
        <li><a href="<s:url value="/mailing/beheer"/>">Mail</a></li>
        <li><a href="<s:url value="/gegevens/beheer"/>">Gegevens</a></li>
    </ul>
</div>
<div id="beheer">
    <tiles:insertAttribute name="beheer_content"/>
</div>
<div style="clear: both;"></div>