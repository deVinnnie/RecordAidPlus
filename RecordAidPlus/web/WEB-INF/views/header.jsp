<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="templates" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<s:url value="/resources" var="resources"/>
<div id="head">
    <security:authorize access="isAuthenticated()">
        <a class="link" href="<s:url value="/gebruikers/account"/>"><security:authentication property="principal.username"/></a>
        <a class="link" href="<s:url value="/static/j_spring_security_logout"/>">Logout</a>
    </security:authorize>

    <security:authorize access="isAnonymous()">
        <a class="link" href="<s:url value="/login"/>">Login</a>
    </security:authorize>
    <img class="logo" src="${resources}/images/recordaid_logo.png" alt="RecordAid logo"/>
</div>

<div id="navigation">
    <ul id="navlist">
        <li><a href="<s:url value="/home"/>">Home</a></li>
        <li><a href="<s:url value="/aanvragen"/>">Mijn Aanvragen</a></li> 
        <li><a href="<s:url value="/support"/>">Probleem melden</a></li>
        <li><a href="<s:url value="/faq"/>">FAQ</a></li> 

        <security:authorize access="isAuthenticated() and not hasRole('STUDENT')">
            <li><a href="<s:url value="/items/reserveer"/>">Reserveren</a></li><br>
            </security:authorize>

        <security:authorize access="isAuthenticated() and not hasRole('STUDENT') and not hasRole('LEERKRACHT')">
            <li><a href="<s:url value="/forum"/>">Berichten</a></li>
            <li><a href="<s:url value="/aanvragen/beheer"/>">Aanvragen-beheer</a></li>
            </security:authorize>

        <security:authorize access="hasRole('KERNLID') or hasRole('ADMIN')">
            <li><a href="<s:url value="/faq/beheer"/>">FAQ-beheer</a></li>
            <li><a href="<s:url value="/gebruikers/beheer"/>">Gebruiker-beheer</a></li>
            <li><a href="<s:url value="/items/beheer"/>">Item-beheer</a></li>
            </security:authorize>
    </ul>
</div>