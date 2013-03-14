<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.gebruiker == null || sessionScope.gebruiker.rol eq 'STUDENT' || sessionScope.gebruiker.rol eq 'LEERKRACHT' || sessionScope.gebruiker.rol eq 'BUDDY' || sessionScope.gebruiker.rol eq 'KERNLID'}">
    <c:redirect url="ingelogdZijn.jsp"/>
</c:if>