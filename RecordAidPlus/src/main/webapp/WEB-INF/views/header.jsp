<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<%--Header with logo and authentication info.--%>
<div id="header">
    <div id="authentication">
        <security:authorize access="isAuthenticated()">
            <a href="<s:url value="/gebruikers/account"/>"><security:authentication property="principal.username"/></a>
            <a href="<s:url value="/static/j_spring_security_logout"/>">Logout</a>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <a href="<s:url value="/login"/>">Login</a>
            <a href="<s:url value="/login/registreren"/>">Registeren</a>
        </security:authorize>
    </div>
    <img class="logo" src="<s:url value="/resources/images/recordaid_logo.png"/>" alt="RecordAid logo"/>
</div>
<%--Primary Navigation--%>
<div id="nav">
    <ul>
        <tiles:useAttribute id="page" name="page" classname="java.lang.String"/>
        <li>
            <%-- Be sure to use single quotes '' around the given String, 
            otherwise you get a "expected equal symbol" error--%>
            <s:url var="url" value="/home"/>
            <a href="${url}"<m:isActiveLink page="home" currentPage="${page}"/>>Home</a>
        </li>
        <li>
            <s:url var="url" value="/info/voorstelling"/>
            <a href="${url}"<m:isActiveLink page="info" currentPage="${page}"/>>Info</a>
        </li>
        <security:authorize access="hasRole('BEGELEIDER')">
            <li>
                <s:url var="url" value="/aanvragen/begeleider"/>
                <a href="${url}"<m:isActiveLink page="aanvragen" currentPage="${page}"/>>Aanvragen</a>
            </li>
        </security:authorize>
        <security:authorize access="not hasRole('BEGELEIDER')">
            <li>
                <s:url var="url" value="/aanvragen"/>
                <a href="${url}"<m:isActiveLink page="aanvragen" currentPage="${page}"/>>Mijn Aanvragen</a>
            </li> 
        </security:authorize>
        <li>
            <s:url var="url" value="/support"/>
            <a href="${url}"<m:isActiveLink page="support" currentPage="${page}"/>>Probleem melden</a>
        </li>
        <li>
            <s:url var="url" value="/faq"/>
            <a href="${url}"<m:isActiveLink page="faq" currentPage="${page}"/>>FAQ</a>
        </li> 
        <security:authorize access="isAuthenticated() and not hasRole('STUDENT')">
            <li>
                <s:url var="url" value="/items/reserveer"/>
                <a href="${url}"<m:isActiveLink page="reserveren" currentPage="${page}"/>>Reserveren</a>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('KERNLID') or hasRole('ADMIN') or hasRole('BUDDY')">
            <li>
                <s:url var="url" value="/beheer"/>
                <a href="${url}"<m:isActiveLink page="beheer" currentPage="${page}"/>>Beheer</a>
            </li>
        </security:authorize>
    </ul>
</div>
<noscript>
<div class="error-balk">
    <p>
        JavaScript is uitgeschakeld of wordt niet ondersteund door jouw browser, 
        sommige functionaliteiten op deze site kunnen hierdoor niet werken.
    </p>
</div>
</noscript>