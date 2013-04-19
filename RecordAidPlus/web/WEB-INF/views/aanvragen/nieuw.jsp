<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Een nieuwe aanvraag plaatsen</h1>
<script type="text/javascript">
$(document).ready(function(){
    $("#datum").datepicker({
        dateFormat: "yy-mm-dd",
        showOtherMonths: true
    }); 
});
</script>
<springforms:form id="aanvraagForm" modelAttribute="nieuweAanvraag" method="POST">
    <p>(*) = verplicht invulveld.</p>
    <table>
        <tr>
            <td><label for="datum">Datum</label></td>
            <td><springforms:input path="lesDatum" name="datum" id="datum"/></td>
            <td>*</td>
            <td id="datum_error" class="error"></td>
        </tr>
        
        <tr>
            <td><label for="reden">Reden</label></td>
            <td><springforms:textarea path="reden" name="reden" id="reden" rows="6" cols="50"/></td>
            <td>*</td>
            <td><springforms:errors path="reden" id="reden_error" cssClass="error"/></td>
        </tr>
     
        <tr>
            <td><label for="departement">Departement</label></td>
            <td>
                <springforms:select path="departement.naam" id="departement" name="departement">
                    <c:forEach var="departement" items="${departementen}">
                        <option value="${departement.naam}"><c:out value="${departement.naam}"/></option>
                    </c:forEach>
                </springforms:select>
            </td>
            <td>*</td>
        </tr>
    </table>
    <input type="submit" value="Volgende"/>
</springforms:form>