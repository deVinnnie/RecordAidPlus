<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<div id="subnav">
    <h1>Info</h1>
    <ul>
        <tiles:useAttribute id="subpage" name="subpage" classname="java.lang.String"/>
        <c:forEach var="page" items="${links}">
            <li>
                <a href="<s:url value="${page.value}"/>" <m:isActiveLink page="${page.value}" currentPage="${subpage}"/>>${page.key}</a>
            </li>
        </c:forEach>
    </ul>
</div>
<div id="info">
    <tiles:insertAttribute name="info_content"/>
</div>
<div style="clear: both;"></div>