<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Een FAQ vraag beantwoorden</h1>
<springforms:form id="faqAntwoord" action="faq/update" method="POST" modelAttribute="faq">
    <table>
        <tr>
            <td id="userMail">Aangevraagd door: ${faq.gebruiker.voornaam} ${faq.gebruiker.naam}</td>
        </tr>
        <tr>
            <td>
                <springforms:textarea path="vraag" type="text" id="currentFAQ" name="FAQVRAAG"/>
            </td>
        </tr>
        <tr>
            <td>
                <springforms:textarea path="antwoord" rows="6" cols="50" name="FAQANTWOORD" id="antw"/>
            </td>
        </tr>
        <tr>
            <td>
                <springforms:checkbox path="relevant" name="ISRELEVANT" label="Toon op de website"/>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Beantwoord"/></td>
        </tr>
    </table>
</springforms:form>