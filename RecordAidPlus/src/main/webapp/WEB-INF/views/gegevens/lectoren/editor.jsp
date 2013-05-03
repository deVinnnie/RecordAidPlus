<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<c:choose>
    <c:when test="${not empty lector}">
        <c:set var="actie" value="Wijzigen"/>
    </c:when>
    <c:otherwise>
        <c:set var="actie" value="Toevoegen"/>
    </c:otherwise>
</c:choose>

<h1>Lector ${actie}</h1>
<springforms:form modelAttribute="lector" method="POST">
    <springforms:input type="text" path="emailadres" id="emailadres"/>
    <springforms:input type="text" path="naam" id="naam"/>
    <input type="submit" value="${actie}"/>
</springforms:form>