<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.gebruiker == null}">
    <c:redirect url="ingelogdZijn.jsp"/>
</c:if>