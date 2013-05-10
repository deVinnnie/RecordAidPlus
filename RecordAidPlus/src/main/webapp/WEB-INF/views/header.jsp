<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
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
<div id="navigation">
    <ul id="navlist">
        <tiles:useAttribute id="page" name="page" classname="java.lang.String"/>

        <li>
            <%-- Be sure to use single quotes '' around the given String, 
            otherwise you get a "expected equal symbol" error--%>
            <a href="<s:url value="/home"/>" <m:isActiveLink page="home" currentPage="${page}"/>>
                Home
            </a>
        </li>

        <li>
            <a href="<s:url value="/info/voorstelling"/>" <m:isActiveLink page="info" currentPage="${page}"/>>
                Info
            </a>
        </li>

        <security:authorize access="hasRole('BEGELEIDER')">
            <li>
                <a href="<s:url value="/aanvragen/begeleider"/>" <m:isActiveLink page="aanvragen" currentPage="${page}"/>>
                    Aanvragen
                </a>
            </li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <security:authentication var="lastRead" property="principal.lastRead"/>
            <security:authentication var="lastChange" property="principal.lastChange"/>
            <c:set var="updated" value="false"/>
            <c:if test="${lastChange gt lastRead}">
                <c:set var="updated" value="true"/>
            </c:if>
        </security:authorize>
        <security:authorize access="not hasRole('BEGELEIDER')">
            <li>
                <a href="<s:url value="/aanvragen"/>" 
                   <m:isActiveLink page="aanvragen" currentPage="${page}"/>
                   <c:if test="${updated}"> class="updated"</c:if>
                   >
                    Mijn Aanvragen
                </a>
            </li> 
        </security:authorize>

        <li>
            <a href="<s:url value="/support"/>" <m:isActiveLink page="support" currentPage="${page}"/>>
                Probleem melden
            </a>
        </li>
        <li>
            <a href="<s:url value="/faq"/>" <m:isActiveLink page="faq" currentPage="${page}"/>>
                FAQ
            </a>
        </li> 

        <security:authorize access="isAuthenticated() and not hasRole('STUDENT')">
            <li>
                <a href="<s:url value="/items/reserveer"/>" <m:isActiveLink page="reserveren" currentPage="${page}"/>>
                    Reserveren
                </a>
            </li>
        </security:authorize>

        <security:authorize access="hasRole('KERNLID') or hasRole('ADMIN')">
            <li>
                <a href="<s:url value="/beheer"/>" <m:isActiveLink page="beheer" currentPage="${page}"/>>
                    Beheer
                </a>
            </li>
        </security:authorize>
    </ul>
</div>