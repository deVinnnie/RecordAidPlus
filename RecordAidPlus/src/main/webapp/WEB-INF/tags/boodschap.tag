<%@tag description="Toon boodschap" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- any content can be specified here e.g.: --%>
<c:if test="${not empty boodschap}">
    <p class="${boodschap.type}"><c:out value="${boodschap.message}"/></p>
</c:if>