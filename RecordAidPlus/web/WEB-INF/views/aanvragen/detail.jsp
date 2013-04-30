<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<h1>Detail aanvraag opname</h1>
<div>
    <a class="link" href="<s:url value="/aanvragen/mijnaanvragen"/>">Terug naar het overzicht</a>
    <a href="<s:url value="/aanvragen/bewerk?id=${aanvraag.id}"/>">Bewerken</a>
    <table class="detailTable">
        <tr>
            <td>Aanvrager</td>
            <td><c:out value="${aanvraag.dossier.gebruiker.voornaam} ${aanvraag.dossier.gebruiker.achternaam}"/></td>
        </tr>
        <c:if test="${type eq 'DagAanvraag'}">
            <tr>
                <td>Lesdatum</td>
                <td><fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" /></td>
            </tr>
            <tr>
                <td>Voor hele reeks?</td>
                <td>
                    <c:choose>
                        <c:when test="${aanvraag.voorHeleReeks}">
                            Ja
                        </c:when>
                        <c:otherwise>
                            Nee
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:if>
        <c:if test="${type eq 'MultiPeriodeAanvraag'}">
            <tr>
                <td>Periode</td>
                <td><fmt:formatDate value="${aanvraag.periode.beginTime.time}" pattern="yyyy-MM-dd" />
                    tot <fmt:formatDate value="${aanvraag.periode.endTime.time}" pattern="yyyy-MM-dd" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>Status</td>
            <td>${aanvraag.status}</td>
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
            <td>Opmerkingen</td>
            <td>${aanvraag.opmerking}</td>
        </tr>
        <tr>
            <td>Aanvraagdatum</td>
            <td><fmt:formatDate value="${aanvraag.aanvraagDatum.time}" pattern="yyyy-MM-dd" /></td>
        </tr>
    </table>

    <h2>Lessen</h2>
    <c:choose>
        <c:when test="${empty aanvraag.opnameMomenten}">
            <p>Er zijn nog geen lessen toegevoegd.</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="opnameMoment" items="${aanvraag.opnameMomenten}">
                <c:choose>
                    <c:when test="${empty opnameMoment.opname}">
                        <c:set var="klasse" value="nietopgenomen"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="klasse" value="opgenomen"/>
                    </c:otherwise>
                </c:choose>
                <h3 class="les">
                    <fmt:formatDate value="${opnameMoment.beginTijdstip.time}" pattern="hh:mm" /> tot 
                    <fmt:formatDate value="${opnameMoment.eindTijdstip.time}" pattern="hh:mm" />  
                    <span class="${klasse}"></span>  
                </h3>
                <table>
                    <tr>
                        <td>OOD</td>
                        <td>${opnameMoment.OOD}</td>
                    </tr>
                    <tr>
                        <td>Lokaal</td>
                        <td>${opnameMoment.lokaal.naam}</td>
                    </tr>
                    <tr>
                        <td>Klas/Reeks</td>
                        <td>${opnameMoment.reeks}</td>
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
                        <td>${opnameMoment.methode.beschrijving}</td>
                    </tr>
                    <tr>
                        <td>Lector</td>
                        <td>
                            ${opnameMoment.lector.naam}
                            <a href="mailto:${opnameMoment.lector.emailadres}">
                                (${opnameMoment.lector.emailadres})
                            </a>
                        </td>
                    </tr>
                </table>    
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>