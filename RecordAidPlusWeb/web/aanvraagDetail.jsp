<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkIngelogd.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail aanvraag opname ${aanvraag.optenemenVak}</title>
    </head>
    <body>
        <h1>Detail aanvraag opname ${aanvraag.optenemenVak}</h1>

        <div>

            <table class="detailTable">
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
                    <td>${aanvraag.status}</td>
                </tr>
                <tr>
                    <td>Verantwoordelijk RecordAid lid</td>
                    <td>${aanvraag.toegewezenLid.voornaam} ${aanvraag.toegewezenLid.naam}</td>
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
                    <td>Link naar de video</td>
                    <td>
                        <c:choose>
                            <c:when test="${aanvraag.linkNaarVideo != ''}">
                                <a href="http://${aanvraag.linkNaarVideo}">Link</a>
                            </c:when>
                            <c:otherwise>
                                N/A
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
                <tr>
                    <td>Aanvraagdatum</td>
                    <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="dd/MM/yyyy" /></td>
                </tr>
            </table>

            <a class="probleema" href="ActionServlet?action=mijnAanvragen">Terug naar het overzicht</a>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
