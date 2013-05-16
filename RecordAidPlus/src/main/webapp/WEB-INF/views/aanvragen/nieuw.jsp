<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Een nieuwe aanvraag plaatsen</h1>
<script type="text/javascript">
$(document).ready(function(){
    datepicker("#datum");
}); 
</script>
<springforms:form modelAttribute="aanvraag" method="POST">
    <table>
        <tr>
            <td><label for="datum">Datum</label></td>
            <td><springforms:input path="lesDatum" id="datum" required="required"/></td>
            <td id="datum_error" class="error"></td>
        </tr>
        <tr>
            <td><label for="reden">Reden</label></td>
            <td><springforms:textarea path="reden" id="reden" rows="6" cols="50" required="required"/></td>
            <td><springforms:errors path="reden" id="reden_error" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="reden">Opmerkingen</label></td>
            <td><springforms:textarea path="opmerking" id="opmerkingen" rows="6" cols="50" placeholder="Opmerkingen of bijkomende informatie, eventueel opgeven als een student uit de klas bereid is om te helpen."/></td>
            <td><springforms:errors path="opmerking" id="opmerkingen_error" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="departement">Departement</label></td>
            <td>
                <springforms:select path="departement.naam" id="departement">
                    <c:forEach var="departement" items="${departementen}">
                        <option value="${departement.naam}" <c:if test="${not departement.actief}">disabled</c:if>>
                            <c:out value="${departement.naam}"/>
                        </option>
                    </c:forEach>
                </springforms:select>
            </td>
        </tr>
        <tr>
            <td><label for="voorHeleReeks">Jouw ganse reeks kan niet aanwezig zijn.</label></td>
            <td>
                <springforms:checkbox path="voorHeleReeks" id="voorHeleReeks"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="Volgende (Lessen Toevoegen)"/>
</springforms:form>