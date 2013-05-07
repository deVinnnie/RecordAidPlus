<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<div id="subnav">
    <h1>Info</h1>
    <ul>
        <li><a href="<s:url value="/info/voorstelling"/>">Voorstelling</a></li>
    </ul>
</div>
<div id="info">
    <tiles:insertAttribute name="info_content"/>
</div>
<div style="clear: both;"></div>