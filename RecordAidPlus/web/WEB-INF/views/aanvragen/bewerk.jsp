<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<!--"paginaCheck/checkGeenStudentenLeerkrachten.jsp"
    <script type="text/javascript" src="JavaScript/jquery.js" ></script>
    <script type="text/javascript" src="JavaScript/aanvraagDetail_1.js"></script>
-->
<h1>Bewerken : <fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="dd/MM/yyyy"/></h1>
<div>
    <springforms:errors path="*" cssClass="error" />
    <springforms:form method="POST" path="aanvraag" id="aanvraagStatusForm">
    <table class="detailTable">
        <tr>
            <td>Aanvrager</td>
            <td>${aanvraag.dossier.gebruiker.voornaam} ${aanvraag.dossier.gebruiker.achternaam}</td>
        </tr>
        <tr>
            <td>Datum</td>
            <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="dd/MM/yyyy" /></td>
        </tr>
        <tr>
            <td>Status</td>
            <td>
                <springforms:select path="status" name="aanvraagStatus" id="aanvraagStatus">
                    <c:forEach items="${statussen}" var="status">
                <option value="${status}" <c:if test="${status eq aanvraag.status}">selected</c:if>>${status}</option>
                    </c:forEach>
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
            <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="dd/MM/yyyy" /></td>
        </tr>
    </table>
        
    <h2>Lessen</h2>
    <c:forEach var="opnameMoment" items="${aanvraag.opnameMomenten}">
    <h3>
        <fmt:formatDate value="${opnameMoment.beginTijdstip.time}" pattern="hh:mm" /> tot 
        <fmt:formatDate value="${opnameMoment.eindTijdstip.time}" pattern="hh:mm" />    
    </h3>
    <table>
        <tr>
            <td>OOD</td>
            <td>${opnameMoment.OOD}</td>
        </tr>
        <tr>
            <td>Lokaal</td>
            <td><springforms:input path="lokaal" type="text" name="lokaal" id="lokaal"/></td>
        </tr>
        <tr>
            <td>Klas/Reeks</td>
            <td><springforms:input path="reeks" type="text" name="reeks" id="reeks"/></td>
        </tr>
        <tr>
            <td>Verantwoordelijk RecordAid lid</td>
            <c:choose>
                <c:when test="${not empty opnameMoment.verantwoordelijke}">
                    <td>${opnameMoment.verantwoordelijke.voornaam} ${opnameMoment.verantwoordelijke.achternaam}</td>
                </c:when>
                <c:otherwise>
                    <td>Niemand Toegewezen</td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td>Opname methode</td>
            <td>${methode.beschrijving}</td>
        </tr>
        <tr>
            <td>Lector</td>
            <td>
                <springforms:input path="lector" type="text"/>
            </td>
        </tr>
    </table>
    </c:forEach>
    <input type="submit" value="Werk aanvraag bij"/>
    
    <!--</springforms:form>
        <table>
        <tr>
            <form method="POST" action="ActionServlet">
                <input type="hidden" name="aanvraagid" value="${aanvraag.id}"/>
                <input type="hidden" name="action" value="zendMailLeerkracht"/>
                <input type="hidden" name="lectorMail" value="${aanvraag.lectorMail}"/>
                <td>Stuur een mail</td>
                <td>
                    ${aanvraag.lectorMail} 
                </td>
                <td>
                    <input type="submit" value="Stuur mail"/>
                </td>
            </form>
        </tr>

        <tr>
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
    </table>-->
    <a class="link" href="/aanvragen/beheer">Terug naar overzicht</a>
</div>