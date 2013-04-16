<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tr>
        <th>Les</th>
        <th>Datum</th>
        <th>Lector</th>
    </tr>
    <c:forEach items="${opnames}" var="opname">
        <tr>
            <td>Les</td>
            <td>Datum</td>
            <td>Lector</td>
        </tr>
    </c:forEach>
</table>