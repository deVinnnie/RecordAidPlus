<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<!--"paginaCheck/checkGeenStudentenLeerkrachten.jsp"
    <script type="text/javascript" src="JavaScript/jquery.js" ></script>
    <script type="text/javascript" src="JavaScript/aanvraagDetail_1.js"></script>
-->
<h1>Bewerken : ${aanvraag.optenemenVak}</h1>
<div>
    <springforms:errors path="*" cssClass="error" />
    <table class="detailTable">
        <springforms:form method="POST" path="aanvraag" id="aanvraagStatusForm">

            <tr>
                <td>Aanvrager</td>
                <td>${aanvraag.aanvrager}</td>
            </tr>

            <tr>
                <td>Datum les</td>
                <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="dd/MM/yyyy" /></td>
            </tr>
            <tr>
                <td>Beginuur</td>
                <td>${aanvraag.beginUur}</td>
            </tr>
            <tr>
                <td>Einduur</td>
                <td>${aanvraag.eindUur}</td>
            </tr>
            <tr>
                <td>Vak</td>
                <td>${aanvraag.optenemenVak}</td>
            </tr>
            <tr>
                <td>Lokaal</td>
                <td>${aanvraag.lokaal}</td>
            </tr>
            <tr>
                <td>Reeks</td>
                <td>${aanvraag.klasReeks}</td>
            </tr>
            <tr>
                <td>Status van goedkeuring</td>
                <td>
                    <springforms:select path="status" name="aanvraagStatus" id="aanvraagStatus">
                        <c:forEach items="${statussen}" var="status">
                    <option value="${status}" <c:if test="${status eq aanvraag.status}">selected</c:if>>${status}</option>
                </c:forEach>
            </springforms:select>
            </td>
            </tr>
            <tr>
                <td>Verantwoordelijk RecordAid lid</td>
                <td>
                    <springforms:select path="toegewezenLid" name="toegewezenLid" id="toegewezenLid">
                        <c:if test="${aanvraag.toegewezenLid == null}">
                    <option value="" selected>Selecteer een buddy...</option>
                </c:if>
                <c:forEach items="${buddies}" var="buddy">
                    <option id="buddy" name="buddy" value="${buddy.emailadres}" <c:if test="${buddy eq aanvraag.toegewezenLid}">selected</c:if>>${buddy}</option>
                </c:forEach>
            </springforms:select>   
            </td>
            </tr>
            <tr>
                <td>E-mail lector</td>
                <td>${aanvraag.lectorMail}</td>
            </tr>
            <tr>
                <td>Departement</td>
                <td>${aanvraag.departement}</td>
            </tr>
            <tr>
                <td>Reden</td>
                <td>${aanvraag.reden}</td>
            </tr>
            <tr>
                <td>Link</td>
                <td>
                    <input class="tb" type="text" name="linkVideo" value="${aanvraag.linkNaarVideo}"/>
                </td>
            </tr>
            <tr>
                <td>Aanvraagdatum</td>
                <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="dd/MM/yyyy" /></td>
            </tr>

            <tr>
                <td><input type="submit" value="Werk aanvraag bij"/></td>
            </tr>
        </springforms:form>

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
    </table>
    <a class="link" href="/aanvragen/beheer">Terug naar overzicht</a>
</div>