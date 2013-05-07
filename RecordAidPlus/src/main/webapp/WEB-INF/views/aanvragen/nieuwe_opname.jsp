<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Een nieuwe aanvraag plaatsen</h1>
<h2>Les toevoegen</h2>
<springforms:errors path="*" cssClass="error"/>
<springforms:form modelAttribute="nieuweOpname" method="POST">
    <table>    
        <tr>
            <td><label for="OOD">OOD</label></td>
            <td><springforms:input path="OOD" autofocus="true" type="text" id="OOD" required="required"/></td>
        </tr>
        <tr>
            <td><label for="reeks">Klas/Reeks</label></td>
            <td><springforms:input path="reeks" type="text" id="reeks" required="required"/></td>
        </tr>
        <tr>
            <td><label for="lokaal">Lokaal</label></td>
            <td><springforms:input path="lokaal.naam" type="text" id="lokaal" required="required"/></td>
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
            <td><label for="lector">Email lector</label></td> 
            <td>
                <springforms:input path="lector" type="text" id="lector" list="lectoren" required="required"/>
                <datalist id="lectoren">
                    <c:forEach items="${alleLectoren}" var="lector">
                        <option value="${lector.emailadres}"/>
                    </c:forEach>
                </datalist>
            </td>
        </tr>
</table>
<input type="submit" name="action" value="Nog een les toevoegen"/>
<input type="submit" name="action" value="Gereed"/>
</springforms:form>