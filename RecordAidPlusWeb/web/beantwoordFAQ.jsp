<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachtenBuddies.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Beantwoord FAQ</title>
        <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables.css"/>
        <link type="text/css" rel="stylesheet" href="DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css"/>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.js"></script>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="DataTables-1.9.4/media/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="JavaScript/beantwoordFAQ.js"></script>
    </head>

    <body>
        <h1>Beantwoord een vraag</h1>
        <h2>Beantwoorde vragen</h2>
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
                <c:forEach var="vraag" items="${bLijst}" >
                    <tr>
                        <td><a href="ActionServlet?action=faqDetails&faqID=${vraag.id}">${vraag.vraag}</a></td>
                        <td>${vraag.gebruiker.emailadres}</td>
                        <td>${vraag.relevant}</td>
                        <td><a href="ActionServlet?action=deleteFAQ&faqID=${vraag.id}">Verwijder</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <h2>Onbeantwoorde vragen</h2>
        <p style="color: blue;">Klik op een vraag om ze te beantwoorden.</p>
        <table id="onbeantwoord" >
            <thead>
                <tr>
                    <th>Vraag</th>
                    <th>Gebruiker</th>
                    <th>Zichtbaar op de website</th>
                    <th>Verwijder</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vraag" items="${onbLijst}" >
                    <tr>
                        <td><a href="ActionServlet?action=faqDetails&faqID=${vraag.id}">${vraag.vraag}</a></td>
                        <td>${vraag.gebruiker.emailadres}</td>
                        <td>${vraag.relevant}</td>
                        <td><a href="ActionServlet?action=deleteFAQ&faqID=${vraag.id}">Verwijder</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <%@include file="footer.jsp" %>
    </body>
</html>