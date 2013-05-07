<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<h1>Technisch probleem melden</h1>
<m:boodschap/>
<p>Heeft u een technisch probleem opgemerkt, zoals bijvoorbeeld een microfoon die niet werkt? 
Vul dan dit formulier in om de RecordAid medewerkers hiervan op de hoogte te stellen!</p>
<springforms:form modelAttribute="support" method="POST">
    <table>
        <tr>
            <td><label for="lokaal">Lokaal</label></td>
            <td><springforms:input type="text" id="lokaal" path="lokaal" required="required"/></td>
        </tr>
        <tr>
            <td><label for="probleem">Probleem</label></td>
            <td><springforms:textarea rows="6" cols="50" path="probleem" required="required"/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>
                <input type="submit" value="Verzenden"/>
            </td>
        </tr>
    </table>
</springforms:form>