<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachten.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/berictDetailOpmaak.css" />
        <script type="text/javascript" src="JavaScript/jquery.js" ></script>
        <script type="text/javascript" src="JavaScript/berichtDetail.js" ></script>
        <title>${topic.titel}</title>
    </head>

    <body>
        <h1>${topic.titel}</h1>
        <div>
            <b>${topic.messages[0].zender.voornaam} ${topic.messages[0].zender.naam}</b>  <span class="datum">zei op <fmt:formatDate value="${topic.messages[0].datum.time}" pattern="dd/MM/yyyy" /></span>
            <p>
                ${topic.messages[0].text}
                <br>
            </p>

        </div>
        <c:forEach var="message" items="${topic.messages}">

            <c:if test="${message ne topic.messages[0]}">
                <div class="reactie">
                    <b>${message.zender.voornaam} ${message.zender.naam}</b>  <span class="datum"> zei op <fmt:formatDate value="${message.datum.time}" pattern="dd/MM/yyyy" /></span>
                    <p>
                        ${message.text}
                        <br>
                    </p> 

                </div>
            </c:if>
        </c:forEach>
        <div class="reactie">
            <form method="POST" id="berichtenForm" action="ActionServlet">
                <input type="hidden" name="topicid" value="${topic.id}"/>
                <input type="hidden" name="action" value="berichten"/>
                <input type="hidden" name="method" value="beantwoord"/>
                <textarea name="berichtInhoud" id="berichtInhoud" title="Beantwoord" class="ta">Beantwoord</textarea>
            </form>
        </div>
        <a class="probleema" href="ActionServlet?action=berichten&method=krijg">Terug</a>
        <c:if test="${sessionScope.gebruiker.rol eq 'ADMIN'}">
            <a class="probleema" href="ActionServlet?action=berichten&method=verwijder&topicid=${topic.id}">Verwijder</a>
        </c:if>

        <%@include file="footer.jsp" %>

    </body>


</html>
