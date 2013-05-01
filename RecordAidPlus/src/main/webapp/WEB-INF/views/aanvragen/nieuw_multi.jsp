<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Een nieuwe aanvraag plaatsen (Langere periode)</h1>
<script type="text/javascript">
    $("document").ready(function(){
        init(); 
    });
</script>
<springforms:form id="aanvraagForm" modelAttribute="nieuweMultiAanvraag" method="POST">
    <p>Alle velden zijn verplicht.</p>
    <table>
        <tr>
            <td><label for="reden">Reden</label></td>
            <td><springforms:textarea path="reden" id="reden" rows="6" cols="50"/></td>
        </tr>
        <tr>
            <td><label for="reden">Opmerkingen</label></td>
            <td><springforms:textarea path="opmerking" id="opmerkingen" rows="6" cols="50"/></td>
            <td><springforms:errors path="opmerking" id="opmerkingen_error" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="beginTime">Begin</label></td>
            <td><springforms:input path="periode.beginTime" id="beginTime" type="text"/></td>
        </tr>
         <tr>
             <td><label for="endTime">Einde</label></td>
            <td><springforms:input path="periode.endTime" id="endTime" type="text"/></td>
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