<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<h1>Een nieuwe aanvraag plaatsen (Langere periode)</h1>
<script type="text/javascript">
    $("document").ready(function() {
        init();
    });
</script>
<springforms:form id="aanvraagForm" modelAttribute="nieuweMultiAanvraag" method="POST">
    <springforms:errors path="*" cssClass="error"/>
    <table>
        <security:authorize access="hasRole('BEGELEIDER')">
            <tr>
                <td><label for="student">Student (e-mailadres KHL)</label></td>
                <td><input type="email" id="student" name="student" required="required"/></td>
            </tr>
        </security:authorize>
        <tr>
            <td><label for="reden">Reden</label></td>
            <td><springforms:textarea path="reden" id="reden" rows="6" cols="50" required="required"/></td>
        </tr>
        <tr>
            <td><label for="reden">Opmerkingen</label></td>
            <td><springforms:textarea path="opmerking" id="opmerkingen" rows="6" cols="50" placeholder="Opmerkingen of bijkomende informatie, eventueel opgeven als een student uit de klas bereid is om te helpen."/></td>
        </tr>
        <tr>
            <td><label for="beginTime">Begin</label></td>
            <td><springforms:input path="periode.beginTime" id="beginTime" type="text" required="required"/></td>
        </tr>
        <tr>
            <td><label for="endTime">Einde</label></td>
            <td><springforms:input path="periode.endTime" id="endTime" type="text" required="required"/></td>
        </tr>
        <tr>
            <td><label for="departement">Departement</label></td>
            <td>
                <springforms:select path="departement.naam" id="departement">
                    <c:forEach var="departement" items="${departementen}">
                <option value="${departement.naam}" <c:if test="${not departement.actief}">disabled</c:if>><c:out value="${departement.naam}"/></option>
            </c:forEach>
        </springforms:select>
    </td>
</tr>
<tr>
    <td>Lectoren</td>
    <td> 
        <p class="error" id="lectoren_error"></p>
        <datalist id="lectoren">
            <c:forEach items="${alleLectoren}" var="lector">
                <option value="${lector.emailadres}"/>
            </c:forEach>
        </datalist> 
        <ul id="lectoren_list">
            <li>
                <button id="lectoren_remove_button0" type="button">-</button>
                <springforms:input path="lectoren[0]" type="text" list="lectoren"/>
            </li>   
        </ul>
        <button type="button" id="addlector">+</button>
    </td>
</tr>
</table>
<input type="submit" value="Opslaan"/>
</springforms:form>