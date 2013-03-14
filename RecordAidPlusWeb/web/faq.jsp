<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.1.custom 2/css/blitzer/jquery-ui-1.10.1.custom.css"/>
        <script type="text/javascript" src="jquery-ui-1.10.1.custom 2/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="jquery-ui-1.10.1.custom 2/js/jquery-ui-1.10.1.custom.js"></script>
        <script type="text/javascript" src="jquery-ui-1.10.1.custom 2/js/jquery-ui-1.10.1.custom.min.js"></script>
        <script type="text/javascript" src="JavaScript/faq.js"></script>
        <title>Frequently Asked Questions</title>
    </head>
    <body>

        <h1>Frequently Asked Questions</h1>

        <p style="color: green;">${succes}</p>
        
        <c:choose>
            <c:when test="${not empty faqs}">
                <div id="accordion">
                    <c:forEach var="faq" items="${faqs}" >
                        <h3>${faq.vraag}</h3>
                        <p>${faq.antwoord}</p>
                    </c:forEach>
                </div>   
            </c:when>
            <c:otherwise>
                <p>Er zijn momenteel nog geen Frequently asked questions.</p>
            </c:otherwise>
        </c:choose>
        <p>Staat jouw vraag er niet bij? Post je <a class="probleema" href="nieuweFAQ.jsp">nieuwe vraag</a> en een RecordAid-lid zal deze zo snel mogelijk beantwoorden.</p>
        <%@include file="footer.jsp" %>

    </body>
</html>
