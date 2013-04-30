<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Een nieuwe aanvraag plaatsen</h1>
<h2>Les toevoegen</h2>
<springforms:form id="nieuweOpnameForm" modelAttribute="nieuweOpname" method="POST">
    <table>    
        <tr>
            <td><label for="OOD">OOD</label></td>
            <td><springforms:input path="OOD" autofocus="true" type="text" id="OOD"/></td>
            <td id="vak_error" class="error">
                <springforms:errors path="OOD" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td><label for="reeks">Klas/Reeks</label></td>
            <td><springforms:input path="reeks" type="text" id="reeks"/></td>
            <td><springforms:errors path="reeks" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="lokaal">Lokaal</label></td>
            <td><springforms:input path="lokaal.naam" type="text" id="lokaal"/></td>
            <td><springforms:errors path="lokaal.naam" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="beginUurH">Begin uur</label></td>
            <td>
                <springforms:select path="tijdstip.beginHour" id="beginUurH">
                    <c:forEach var="h" begin="8" end="22" step="1">
                        <option value="${h}">${h}</option>
                    </c:forEach>
                </springforms:select>
                
                <springforms:select path="tijdstip.beginMinute" id="beginUurM">
                    <c:forEach var="m" begin="0" end="45" step="15">
                         <option value="${m}">${m}</option>
                    </c:forEach>
                </springforms:select>
            </td>
            <td id="uren_error" class="error"></td>
        </tr>
        <tr>
            <td><label>Eind uur</label> </td>
            <td>
            <springforms:select path="tijdstip.endHour" id="eindUurH">
              <c:forEach var="h" begin="8" end="22" step="1">
                    <option value="${h}">${h}</option>
              </c:forEach>
            </springforms:select>
            
            <springforms:select path="tijdstip.endMinute" id="eindUurM">
                <c:forEach var="m" begin="0" end="45" step="15">
                    <option value="${m}">${m}</option>
                </c:forEach>
            </springforms:select>
            </td>
        </tr>
        <tr>
            <td><label for="methode">Opname methode</label> </td>
            <td>
            <springforms:select path="methode" name="methode" id="methode">
                <springforms:options items="${opnameMethodes}" itemValue="id" itemLabel="beschrijving"/>
            </springforms:select>
            </td>
        </tr>
        <tr>
            <td><label for="lector">Email lector</label></td> 
            <td>
                <springforms:input path="lector" type="text" id="lector" list="lectoren"/>
                <datalist id="lectoren">
                    <c:forEach items="${alleLectoren}" var="lector">
                        <option value="${lector.emailadres}"/>
                    </c:forEach>
                </datalist>
            </td>
            <td id="lector_error" class="error"><springforms:errors path="lector"/></td>
        </tr>
</table>
<input type="submit" name="action" value="Nog een les toevoegen"/>
<input type="submit" name="action" value="Gereed"/>
</springforms:form>