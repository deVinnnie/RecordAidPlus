<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <tiles:useAttribute name="title" />
        <title><fmt:message key="title"/> | <fmt:message key="${title}"/></title>
        <c:set value="/resources" var="resources" />
        <!--<link rel="stylesheet" type="text/css" href="${resources}/bootstrap/css/bootstrap.css" />-->
        <!--<link rel="stylesheet" type="text/css" href="${resources}/css/reset.css" />-->
        <tiles:useAttribute id="styles" name="styles" classname="java.util.List" />
        <c:forEach items="${styles}" var="style">
            <link rel="stylesheet" type="text/css" href="<s:url value="${resources}${style}"/>"/>
        </c:forEach>
        <link rel="stylesheet" type="text/css" href="<s:url value="${resources}/css/default.css"/>"/>
        <tiles:useAttribute id="scripts" name="scripts" classname="java.util.List" />
        <c:forEach items="${scripts}" var="script">
        <script type="text/javascript" src="<s:url value="${resources}${script}"/>"></script>
        </c:forEach>
        <link rel="shortcut icon" href="<s:url value="${resources}/images/fav_icon.png"/>"/>
    </head>
    <body>
        <div id="content">
            <tiles:insertAttribute name="header"/>          
            
            <div id="page">
            <tiles:insertAttribute name="content"/>
            </div>
            
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>