<%@tag description="Determine is the link is active." pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="currentPage" required="true"%>
<%@attribute name="page" required="true"%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${currentPage eq page}">id="active"</c:if>