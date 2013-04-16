<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<h1>Een nieuwe aanvraag plaatsen</h1>
<springforms:form id="aanvraagForm" modelAttribute="nieuweAanvraag" method="POST">
    <p>(*) = verplicht invulveld.</p>
    <table>
        <tr>
            <td><label for="vak">Vak</label></td>
            <td><springforms:input path="optenemenVak" autofocus="true" type="text" name="vak" id="vak" class="tb"/></td>
            <td>*</td>
            <td id="vak_error" class="error">
                <springforms:errors path="optenemenVak" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td><label for="reeks">Klas/Reeks</label></td>
            <td><springforms:input path="reeks" type="text" name="reeks" id="reeks"/></td>
            <td>*</td>
            <td><springforms:errors path="optenemenVak" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="lokaal">Lokaal</label></td>
            <td><springforms:input path="lokaal" type="text" name="lokaal" id="lokaal"/></td>
            <td>*</td>
            <td><springforms:errors path="lokaal" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="datum">Datum</label></td>
            <td><springforms:input path="timespan.beginTime" name="datum" id="datum"/></td> <!--readonly="true"-->
            <td>*</td>
            <td id="datum_error" class="error"></td>
        </tr>
        
        <tr>
            <td><label>Begin uur</label> </td>
            <td>
                <springforms:select path="timespan.beginHour" name="beginUurH" id="beginUurH">
                    <c:forEach var="h" begin="8" end="22" step="1">
                <option value="${h}">${h}</option>
            </c:forEach>
        </springforms:select>
        <springforms:select path="timespan.beginMinute" name="beginMinute" id="beginUurM">
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
    <springforms:select path="timespan.endHour" name="endHour" id="eindUurH">
        <c:forEach var="h" begin="8" end="22" step="1">
        <option value="${h}">${h}</option>
        </c:forEach>
    </springforms:select>
    <springforms:select path="timespan.endMinute" name="eindUurM" id="eindUurM">
        <c:forEach var="m" begin="0" end="45" step="15">
            <option value="${m}">${m}</option>
        </c:forEach>
    </springforms:select>
    </td>
    <td>*</td>
</tr>
<tr>
    <td><label>Email lector</label></td> 
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
<tr>
    <td><label for="departement">Departement</label></td>
    <td>
    <springforms:select path="departement" id="departement" name="departement">
    <option value="G&T">G&amp;T</option>
    <option value="SSH">SSH</option>
    <option value="DLO Diest">DLO Diest</option>
    <option value="Naamse steenweg">Naamse steenweg</option>
    <option value="Hertogstraat">Hertogstraat</option>
    </springforms:select>
</td>
<td>*</td>
</tr>
</table>

<h3>Reden</h3>
<springforms:textarea path="reden" name="reden" id="reden" rows="6" cols="50"/><br>
<springforms:errors path="reden" id="reden_error" cssClass="error"/>
<input type="submit" value="Verzenden"/>
</springforms:form>