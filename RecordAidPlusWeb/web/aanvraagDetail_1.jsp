<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachten.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="JavaScript/jquery.js" ></script>
        <script type="text/javascript" src="JavaScript/aanvraagDetail_1.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail aanvraag opname ${aanvraag.optenemenVak}</title>
    </head>

    <body>
        <h1>Detail aanvraag opname ${aanvraag.optenemenVak}</h1>

        <div>
            <table class="detailTable">
                <form method="POST" id="aanvraagStatusForm" action="ActionServlet">
                    <input type="hidden" name="aanvraagid" value="${aanvraag.id}"/>
                    <input type="hidden" name="action" value="updateAanvraag"/>
                    <input type="hidden" name="methode" value="updaten"/>
                    <c:if test="${sessionScope.gebruiker.rol != 'BUDDY'}">
                        <tr>
                            <td>Aanvrager</td>
                            <td>${aanvraag.aanvrager}</td>
                            <td>&nbsp;</td>
                        </tr>
                    </c:if>

                    <tr>
                        <td>Datum les</td>
                        <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="dd/MM/yyyy" /></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Beginuur</td>
                        <td>${aanvraag.beginUur}</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Einduur</td>
                        <td>${aanvraag.eindUur}</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Vak</td>
                        <td>${aanvraag.optenemenVak}</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Lokaal</td>
                        <td>${aanvraag.lokaal}</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Reeks</td>
                        <td>${aanvraag.klasReeks}</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Status van goedkeuring</td>
                        <td>
                            <select name="aanvraagStatus" id="aanvraagStatus">
                                <c:forEach items="${statussen}" var="status">
                                    <option value="${status}" <c:if test="${status eq aanvraag.status}">selected</c:if>   >${status}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Verantwoordelijk RecordAid lid</td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.gebruiker.rol ne 'BUDDY'}">
                                    <select name="toegewezenLid" id="toegewezenLid">
                                        <c:if test="${aanvraag.toegewezenLid == null}">
                                            <option value="" selected>Selecteer een buddy...</option>
                                        </c:if>
                                        <c:forEach items="${buddies}" var="buddy">
                                            <option id="buddy" name="buddy" value="${buddy.emailadres}" <c:if test="${buddy eq aanvraag.toegewezenLid}">selected</c:if>>${buddy}</option>
                                        </c:forEach>
                                    </select>   
                                </c:when>
                                <c:otherwise>
                                    ${aanvraag.toegewezenLid}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>E-mail lector</td>
                        <td>${aanvraag.lectorMail}</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Departement</td>
                        <td>${aanvraag.departement}</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Reden</td>
                        <td>${aanvraag.reden}</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Link</td>
                        <td>
                            <input class="tb" type="text" name="linkVideo" value="${aanvraag.linkNaarVideo}"/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Aanvraagdatum</td>
                        <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="dd/MM/yyyy" /></td>
                        <td>&nbsp;</td>
                    </tr>

                    <tr>
                        <td>&nbsp;</td>
                        <td><input type="submit" value="Werk aanvraag bij"/></td>
                        <td>&nbsp;</td>
                    </tr>
                </form>
                <c:if test="${sessionScope.gebruiker.rol ne 'BUDDY'}">
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
                </c:if>

                <c:if test="${sessionScope.gebruiker.rol ne 'BUDDY'}">
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
                </c:if>
            </table>
            <a class="probleema" href="ActionServlet?action=alleAanvragen">Terug naar overzicht</a>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
