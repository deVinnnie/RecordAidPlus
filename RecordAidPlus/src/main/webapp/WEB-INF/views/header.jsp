<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<s:url value="/resources" var="resources"/>
<div id="head">
    <div id="auth">
    <security:authorize access="isAuthenticated()">
            <a href="<s:url value="/gebruikers/account"/>"><security:authentication property="principal.username"/></a>
            <a href="<s:url value="/static/j_spring_security_logout"/>">Logout</a>
    </security:authorize>
    <security:authorize access="isAnonymous()">
           <a href="<s:url value="/login"/>">Login</a>
           <a href="<s:url value="/login/registreren"/>">Registeren</a>
    </security:authorize>
    </div>
    <img class="logo" src="${resources}/images/recordaid_logo.png" alt="RecordAid logo"/>
</div>
<div id="navigation">
    <ul id="navlist">
        <li><a href="<s:url value="/home"/>">Home</a></li>
        <li><a href="<s:url value="/aanvragen"/>">Mijn Aanvragen</a></li> 
        <li><a href="<s:url value="/support"/>">Probleem melden</a></li>
        <li><a href="<s:url value="/faq"/>">FAQ</a></li> 
        <security:authorize access="isAuthenticated() and not hasRole('STUDENT')">
            <li><a href="<s:url value="/items/reserveer"/>">Reserveren</a></li>
        </security:authorize>
        <security:authorize access="hasRole('KERNLID') or hasRole('ADMIN')">
            <li><a href="<s:url value="/beheer"/>">Beheer</a></li>
        </security:authorize>
    </ul>
</div>