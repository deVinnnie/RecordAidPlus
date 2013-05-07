<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<h1>Nieuwe Opnamemethode</h1>
<springforms:form modelAttribute="opnameMethode" method="POST">
    <table>
        <tr>
            <td>Naam</td>
            <td><springforms:input type="text" path="naam"/></td>
        </tr>
        <tr>
            <td>Beschrijving</td>
            <td><springforms:textarea rows="6" cols="50" type="text" path="beschrijving"/></td>
        </tr>
    </table>
    <input type="submit" value="Toevoegen">
</springforms:form>