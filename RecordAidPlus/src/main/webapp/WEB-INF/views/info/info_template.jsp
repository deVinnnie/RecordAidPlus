<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<div id="subnav">
    <h1>Info</h1>
    <ul>
        <tiles:useAttribute id="subpage" name="subpage" classname="java.lang.String"/>
        <jsp:useBean id="links" class="java.util.LinkedHashMap"  scope="request"> 
            <c:set target="${links}" property="Voorstelling" value="/info/voorstelling"/>  
            <c:set target="${links}" property="Studenten" value="/info/studenten"/>  
            <c:set target="${links}" property="Lectoren" value="/info/lectoren"/>  
            <c:set target="${links}" property="Problemen" value="/info/problemen"/>  
            <c:set target="${links}" property="Buddies" value="/info/buddies"/>  
            <c:set target="${links}" property="Studentenbegeleiding" value="/info/studentenbegeleiding"/>  
            <c:set target="${links}" property="Media" value="/info/media/"/>   
            <c:set target="${links}" property="Contact" value="/info/contact"/>  
        </jsp:useBean>
        <c:forEach var="link" items="${links}">
            <li><a href="<s:url value="${link.value}"/>" <m:isActiveLink page="${link.value}" currentPage="${subpage}"/>>${link.key}</a></li>
        </c:forEach> 
    </ul>
</div>
<div id="subcontent">
    <tiles:insertAttribute name="subcontent"/>
</div>
<div style="clear: both;"></div>