<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Een nieuwe aanvraag plaatsen</h1>
<springforms:form id="nieuweOpnameForm" modelAttribute="nieuweOpname" method="POST">
    <h2>Les toevoegen</h2>
    <table>    
        <tr>
            <td><label for="OOD">OOD</label></td>
            <td><springforms:input path="OOD" autofocus="true" type="text" name="OOD" id="OOD"/></td>
            <td>*</td>
            <td id="vak_error" class="error">
                <springforms:errors path="OOD" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td><label for="reeks">Klas/Reeks</label></td>
            <td><springforms:input path="reeks" type="text" name="reeks" id="reeks"/></td>
            <td>*</td>
            <td><springforms:errors path="reeks" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="lokaal">Lokaal</label></td>
            <td><springforms:input path="lokaal.naam" type="text" name="lokaal" id="lokaal"/></td>
            <td>*</td>
            <td><springforms:errors path="lokaal.naam" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label>Begin uur</label></td>
            <td>
                <springforms:select path="tijdstip.beginHour" name="beginUurH" id="beginUurH">
                    <c:forEach var="h" begin="8" end="22" step="1">
                        <option value="${h}">${h}</option>
                    </c:forEach>
                </springforms:select>
                
                <springforms:select path="tijdstip.beginMinute" name="beginMinute" id="beginUurM">
                    <c:forEach var="m" begin="0" end="45" step="15">
                         <option value="${m}">${m}</option>
                    </c:forEach>
                </springforms:select>
            </td>
            <td>*</td>
            <td id="uren_error" class="error"></td>
        </tr>
        <tr>
            <td><label>Eind uur</label> </td>
            <td>
            <springforms:select path="tijdstip.endHour" name="endHour" id="eindUurH">
              <c:forEach var="h" begin="8" end="22" step="1">
                    <option value="${h}">${h}</option>
              </c:forEach>
            </springforms:select>
            
            <springforms:select path="tijdstip.endMinute" name="eindUurM" id="eindUurM">
                <c:forEach var="m" begin="0" end="45" step="15">
                    <option value="${m}">${m}</option>
                </c:forEach>
            </springforms:select>
            </td>
            <td>*</td>
        </tr>
        <tr>
            <td><label for="methode">Opname methode</label> </td>
            <td>
            <springforms:select path="methode" name="methode" id="methode">
              <c:forEach var="methode" items="${opnameMethodes}">
                    <option value="${methode.id}">${methode.beschrijving}</option>
              </c:forEach>
            </springforms:select>
            </td>
            <td>*</td>
        </tr>
        
        <tr>
            <td><label for="lector">Email lector</label></td> 
            <td>
                <springforms:input path="lector.emailadres" type="text" name="lector" id="lector" list="lectoren"/>
                <datalist id="lectoren">
                    <c:forEach items="${alleLectoren}" var="lector">
                        <option value="${lector.emailadres}"/>
                    </c:forEach>
                </datalist>
            </td>
            <td>*</td>
            <td id="lector_error" class="error"><springforms:errors path="lector"/></td>
        </tr>
</table>

<input type="submit" name="action" value="Nog een les toevoegen"/>
<input type="submit" name="action" value="Gereed"/>
</springforms:form>