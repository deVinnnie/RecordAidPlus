<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Een FAQ vraag beantwoorden</h1>
<springforms:form id="faqAntwoord" action="faq/update" method="POST" modelAttribute="faq">
    <table>
        <tr>
            <td>Aangevraagd door: <c:out value="${faq.gebruiker.voornaam} ${faq.gebruiker.achternaam}"/></td>
        </tr>
        <tr>
            <td><label for="vraag">Vraag</label></td>
            <td>
                <springforms:textarea path="vraag" rows="6" cols="50" id="vraag"/>
            </td>
        </tr>
        <tr>
            <td><label for="antwoord">Antwoord</label></td>
            <td>
                <springforms:textarea path="antwoord" rows="6" cols="50" id="antwoord"/>
            </td>
        </tr>
    </table>   
    <springforms:checkbox path="relevant" label="Toon op de website"/><br>
    <input type="submit" value="Beantwoord"/>
</springforms:form>