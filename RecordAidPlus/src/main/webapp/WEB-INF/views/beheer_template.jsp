<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<div id="subnav">
    <h1>Beheer</h1>
    <ul>
        <tiles:useAttribute id="subpage" name="subpage" classname="java.lang.String"/>
        <jsp:useBean id="links" class="java.util.LinkedHashMap"  scope="request"> 
            <c:set target="${links}" property="Aanvragen" value="/aanvragen/beheer"/>  
            <c:set target="${links}" property="FAQ" value="/faq/beheer"/>  
            <c:set target="${links}" property="Gebruikers" value="/gebruikers/beheer"/>  
            <c:set target="${links}" property="Items" value="/items/beheer"/>  
            <c:set target="${links}" property="Opnames" value="/opnames/beheer"/>  
            <c:set target="${links}" property="Mail" value="/mailing/beheer"/>  
            <c:set target="${links}" property="Gegevens" value="/gegevens/beheer"/>  
            <c:set target="${links}" property="Support" value="/support/beheer"/>  
        </jsp:useBean>
        <c:forEach var="link" items="${links}">
            <li><a href="<s:url value="${link.value}"/>" <m:isActiveLink page="${link.value}" currentPage="${subpage}"/>>${link.key}</a></li>
        </c:forEach> 
    </ul>
</div>
<div id="beheer">
    <tiles:insertAttribute name="beheer_content"/>
</div>
<div style="clear: both;"></div>