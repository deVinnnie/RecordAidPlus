<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Een opname toevoegen</h1>
<script type="text/javascript">
$(document).ready(function(){
    $("#datum").datepicker({
        dateFormat: "yy-mm-dd",
        showOtherMonths: true
    }); 
});
</script>
<springforms:form id="nieuweOpnameForm" modelAttribute="opname" method="POST">
    <table>    
        <tr>
            <td><label for="OOD">OOD</label></td>
            <td><springforms:input path="OOD" autofocus="true" type="text" name="OOD" id="OOD"/></td>
            <td id="vak_error" class="error">
                <springforms:errors path="OOD" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td><label for="datum">Datum</label></td>
            <td><springforms:input path="tijdstip.beginTime" name="datum" id="datum"/></td>
            <td id="datum_error" class="error"></td>
        </tr>
        <tr>
            <td><label for="zichtbaar">Zichtbaar</label></td>
            <td><springforms:checkbox path="zichtbaar" id="zichtbaar" name="zichtbaar"/></td>
        </tr>
        <tr>
            <td><label for="lector">Lector (E-mailadres)</label></td> 
            <td>
                <springforms:input path="lector.emailadres" type="text" name="lector" id="lector" list="lectoren"/>
                <datalist id="lectoren">
                    <c:forEach items="${alleLectoren}" var="lector">
                        <option value="${lector.emailadres}"/>
                    </c:forEach>
                </datalist>
            </td>
            <td id="lector_error" class="error"><springforms:errors path="lector"/></td>
        </tr>
</table>
<c:choose>
    <c:when test="${empty opname.id}">
        <c:set var="submit_text" value="Toevoegen" />
    </c:when>
    <c:otherwise>
        <c:set var="submit_text" value="Opslaan" />
    </c:otherwise>
</c:choose>
<input type="submit" name="action" value="${submit_text}"/>
</springforms:form>