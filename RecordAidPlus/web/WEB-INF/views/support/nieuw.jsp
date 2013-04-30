<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Technisch probleem melden</h1>
<p>Heeft u een technisch probleem opgemerkt, zoals bijvoorbeeld een microfoon die niet werkt? 
Vul dan dit formulier in om de RecordAid medewerkers hiervan op de hoogte te stellen!</p>

<p class="succes">${succes}</p>
<p class="error">${error}</p>

<springforms:form id="supportForm" modelAttribute="nieuwSupport" method="POST">
    <table>
        <tr>
            <td><label for="lokaal">Lokaal</label></td>
            <td><springforms:input type="text" id="lokaal" path="lokaal"/></td>
        </tr>
        <tr>
            <td><label for="opmerking">Probleem</label></td>
            <td><springforms:textarea rows="6" cols="50" path="opmerking"/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>
                <input type="submit" value="Verzenden"/>
            </td>
        </tr>
    </table>
</springforms:form>