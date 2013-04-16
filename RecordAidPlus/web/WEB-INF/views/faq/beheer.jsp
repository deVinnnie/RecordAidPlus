<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
      <!-- <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables.css"/>
        <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css"/>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.js"></script>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="JavaScript/beantwoordFAQ.js"></script>-->
<h1>Beantwoord een vraag</h1>
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
                    <td><a href="<s:url value="/faq?id=${faq.id}"/>">${faq.vraag}</a></td>
                    <td>${faq.gebruiker.emailadres}</td>
                    <td>${faq.relevant}</td>
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
            <p style="color: blue;">Klik op een vraag om ze te beantwoorden.</p>
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
                    <td><a href="<s:url value="/faq?id=${faq.id}"/>">${faq.vraag}</a></td>
                    <td>${faq.gebruiker.emailadres}</td>
                    <td>${faq.relevant}</td>
                    <td><a href="<s:url value="/faq/beheer?delete&id=${faq.id}"/>">Verwijder</a></td>
                </tr>
            </c:forEach>
                </tbody>
            </table>
    </c:otherwise>
</c:choose>