<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!--"paginaCheck/checkGeenStudentenLeerkrachten.jsp"-->
<h1>Bewerken : <fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd"/></h1>
<a class="link" href="<s:url value="/aanvragen/beheer"/>">Terug naar het overzicht</a>
<div>
    <springforms:errors path="*" cssClass="error" />
    <springforms:form method="POST" modelAttribute="aanvraag" id="aanvraagStatusForm">
        <table class="detailTable">
            <tr>
                <td>Aanvrager</td>
                <td>${aanvraag.dossier.gebruiker.voornaam} ${aanvraag.dossier.gebruiker.achternaam}</td>
            </tr>
            <tr>
                <td>Datum</td>
                <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" /></td>
            </tr>
            <tr>
                <td>Status</td>
                <td>
                    <springforms:select path="status" name="aanvraagStatus" id="aanvraagStatus">
                        <springforms:options />
                    </springforms:select>
                </td>
            </tr>
            <tr>
                <td>Departement</td>
                <td>${aanvraag.departement.naam}</td>
            </tr>
            <tr>
                <td>Reden</td>
                <td>${aanvraag.reden}</td>
            </tr>
            <tr>
                <td>Aanvraagdatum</td>
                <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="yyyy-MM-dd" /></td>
            </tr>
        </table>

        <h2>Lessen</h2>
        <c:forEach var="opnameMoment" items="${aanvraag.opnameMomenten}" varStatus="counter">
            <c:choose>
                <c:when test="${empty opnameMoment.opname}">
                    <c:set var="klasse" value="nietopgenomen"/>
                </c:when>
                <c:otherwise>
                    <c:set var="klasse" value="opgenomen"/>
                </c:otherwise>
            </c:choose>

            <h3 style="float:left;">
                <fmt:formatDate value="${opnameMoment.beginTijdstip.time}" pattern="hh:mm" /> tot 
                <fmt:formatDate value="${opnameMoment.eindTijdstip.time}" pattern="hh:mm" />  
                <span class="${klasse}"></span>  
            </h3>
            <a href="<s:url value="/aanvragen/koppel_opname?aanvraag=${aanvraag.id}&opnamemoment=${opnameMoment.id}"/>">Koppel opname</a>
            <div class="clearfix"></div>
            <table>
                <tr>
                    <td><label for="OOD">OOD</label></td>
                    <td><springforms:input type="text" path="opnameMomenten[${counter.index}].OOD" id="OOD"/></td>
                </tr>
                <tr>
                    <td><label for="${counter.index}_lokaal">Lokaal</label></td>
                    <td><springforms:input path="opnameMomenten[${counter.index}].lokaal" type="text" id="${counter.index}_lokaal"/></td>
                </tr>
                <tr>
                    <td><label for="${counter.index}_reeks">Klas/Reeks</label></td>
                    <td><springforms:input path="opnameMomenten[${counter.index}].reeks" type="text" id="${counter.index}_reeks"/></td>
                </tr>
                <tr>
                    <td>Verantwoordelijk RecordAid lid</td>
                    <springforms:select path="opnameMomenten[${counter.index}].verantwoordelijke">
                        <springforms:options items="${alleBuddies}" itemValue="id" itemLabel="voornaam"/>
                    </springforms:select>
                    <%--<c:choose>
                        <c:when test="${not empty opnameMoment.verantwoordelijke}">
                            <td>${opnameMoment.verantwoordelijke.voornaam} ${opnameMoment.verantwoordelijke.achternaam}</td>
                        </c:when>
                        <c:otherwise>
                            <td>Niemand Toegewezen</td>
                        </c:otherwise>
                    </c:choose>--%>
                </tr>
                <tr>
                    <td>Opname methode</td>
                    <td>${opnameMoment.methode.beschrijving}</td>
                </tr>
                <tr>
                    <td>Lector</td>
                    <td>
                        <springforms:input path="opnameMomenten[${counter.index}].lector.emailadres" type="text"/>
                    </td>
                </tr>
            </table>
        </c:forEach>
        <input type="submit" value="Werk aanvraag bij"/>
    </springforms:form>
    <%--<tr>
    <form method="POST" id="opleidingsHoofd" action="ActionServlet">
        <input type="hidden" name="aanvraagid" value="${aanvraag.id}"/>
        <input type="hidden" name="action" value="zendMailOpleidingshoofd"/>
        <td>Stuur een mail</td>
        <td>
            <select name="OPHDEN" id="OPHDEN">
                <option value="-1" selected>Selecteer een opleidingshoofd...</option>
                <c:forEach items="${opleidingsh}" var="oplh">
                    <option id="oplh" name="oplh" value="${oplh.emailadres}">${oplh.voornaam}  ${oplh.naam}  ${oplh.wachtwoord}</option>
                </c:forEach>
            </select>    
        </td>
        <td>
            <input type="submit" value="Stuur mail"/>
        </td>
    </form>
    </tr>
</table>--%>
</div>