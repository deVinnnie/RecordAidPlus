<%@tag description="Toon boodschap" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${not empty boodschap}">
    <p class="${boodschap.type}"><c:out value="${boodschap.message}"/></p>
</c:if>