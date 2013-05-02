<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${empty aanvraag.opnameMomenten}">
        <p>Er zijn nog geen lessen toegevoegd.</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="opnameMoment" items="${aanvraag.opnameMomenten}">
            
            <h3 class="les">
                <fmt:formatDate value="${opnameMoment.beginTijdstip.time}" pattern="hh:mm" /> tot 
                <fmt:formatDate value="${opnameMoment.eindTijdstip.time}" pattern="hh:mm" />  
                <%@include file="les_status_fragment.jsp" %>
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
                <c:if test="${not empty opnameMoment.opname}">
                    <tr>
                        <td>Opname</td>
                        <td><c:out value="${opnameMoment.opname.locatie.medium}
                               ${opnameMoment.opname.locatie.wegwijzer}"/>
                        </td>
                    </tr>
                </c:if>
            </table>    
        </c:forEach>
    </c:otherwise>
</c:choose>