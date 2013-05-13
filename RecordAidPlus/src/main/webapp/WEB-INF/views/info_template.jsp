<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<div id="subnav">
    <h1>Info</h1>
    <ul>
        <tiles:useAttribute id="subpage" name="subpage" classname="java.lang.String"/>
        <li>
            <a href="<s:url value="/info/voorstelling"/>" <m:isActiveLink page="info.voorstelling" currentPage="${subpage}"/>>Voorstelling</a>
        </li>
        <li>
            <a href="<s:url value="/info/studenten"/>" <m:isActiveLink page="info.studenten" currentPage="${subpage}"/>>Studenten</a>
        </li>
        <li>
            <a href="<s:url value="/info/lectoren"/>" <m:isActiveLink page="info.lectoren" currentPage="${subpage}"/>>Lectoren</a>
        </li>
    </ul>
</div>
<div id="info">
    <tiles:insertAttribute name="info_content"/>
</div>
<div style="clear: both;"></div>