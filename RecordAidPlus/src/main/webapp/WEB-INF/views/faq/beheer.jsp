<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<h1>Veelgestelde Vragen</h1>
<m:boodschap/>

<h2>Beantwoorde vragen</h2>
<c:choose>
    <c:when test="${empty beantwoordeVragen}">
        <p>Er zijn geen beantwoorde vragen.</p>
    </c:when>

    <c:otherwise>
        <table id="beantwoord" >
            <thead>
                <tr>
                    <th>Vraag</th>
                    <th>Gebruiker</th>
                    <th>Zichtbaar op de website</th>
                    <th>Verwijder</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="faq" items="${beantwoordeVragen}" >
                    <tr>
                        <td>
                            <s:url var="url" value="/faq?id=${faq.id}"/>
                            <a href="${url}"><c:out value="${faq.vraag}"/></a>
                        </td>
                        <td><c:out value="${faq.gebruiker.emailadres}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${faq.relevant}">
                                    Ja
                                </c:when>   
                                <c:otherwise>
                                    Nee
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><a href="<s:url value="/faq/beheer?delete&id=${faq.id}"/>">Verwijder</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

<h2>Onbeantwoorde vragen</h2>
<c:choose>
    <c:when test="${empty nietBeantwoordeVragen}">
        <p>Er zijn geen onbeantwoorde vragen.</p>
    </c:when>

    <c:otherwise>
        <p class="notification">Klik op een vraag om ze te beantwoorden.</p>
        <table id="onbeantwoord">
            <thead>
                <tr>
                    <th>Vraag</th>
                    <th>Gebruiker</th>
                    <th>Zichtbaar op de website</th>
                    <th>Verwijder</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="faq" items="${nietBeantwoordeVragen}" >
                    <tr>
                        <td>
                            <s:url var="url" value="/faq?id=${faq.id}"/>
                            <a href="${url}"><c:out value="${faq.vraag}"/></a>
                        </td>
                        <td><c:out value="${faq.gebruiker.emailadres}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${faq.relevant}">
                                    Ja
                                </c:when>   
                                <c:otherwise>
                                    Nee
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <s:url var="url" value="/faq/beheer?delete&id=${faq.id}"/>
                            <a href="${url}">Verwijder</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>