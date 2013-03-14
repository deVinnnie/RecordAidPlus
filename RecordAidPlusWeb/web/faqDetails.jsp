<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachtenBuddies.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Een FAQ vraag beantwoorden</title>
    </head>
    <body>
        <h1>Een FAQ vraag beantwoorden</h1>

        <form id="faqAntwoord" method="POST" action="ActionServlet?action=updateFAQ">
            <table>
                <tr>
                    <td id="userMail">Aangevraagd door: ${faq.gebruiker}</td>
                </tr>
                <tr>
                    <td><input class="tb" type="text" id="currentFAQ" value="${faq.vraag}" name="FAQVRAAG"></input></td>
                </tr>
                <tr>
                    <td><textarea class="ta" rows="6" cols="50" name="FAQANTWOORD" id="antw" >${faq.antwoord}</textarea></td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="ISRELEVANT" <c:if test="${faq.relevant}">checked</c:if>>Toon op de website</td>
                    </tr>
                    <tr>
                    <input id="questionID" type="hidden" name="FAQID" value="${faq.id}"/>
                <td><input type="submit" value="Beantwoord"/></td>
                </tr>
            </table>
        </form>
        <%@include file="footer.jsp" %>
    </body>
</html>
