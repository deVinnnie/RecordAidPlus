<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<security:authentication property="principal" var="currentUser"/>
[
<c:forEach var="reservatie" items="${reservaties}" varStatus="counter">
        <c:if test="${counter.index > 0}">,</c:if>
        {
            "title": "${reservatie.gebruiker.voornaam} ${reservatie.gebruiker.achternaam}", 
            "start": "<fmt:formatDate value="${reservatie.slot.beginTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>",
            "end": "<fmt:formatDate value="${reservatie.slot.endTime.time}" pattern="yyyy-MM-dd HH:mm:ss"/>",
            "allDay": false, 
            "editable": false, 
            "color" : "#3a87ad", 
            "className" : "event", 
            "id" : ${reservatie.id}, 
            <c:choose>
                <c:when test="${currentUser eq reservatie.gebruiker}">
                    "removable" : true
                </c:when>
                <c:otherwise>
                    "removable" : false
                </c:otherwise>
            </c:choose>
        }
</c:forEach>
]