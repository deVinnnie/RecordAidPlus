<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:choose>
    <c:when test="${empty aanvraag.opnameMomenten}">
        <p>Er zijn nog geen lessen toegevoegd.</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="opnameMoment" items="${aanvraag.opnameMomenten}">
            <h3 class="les">
                <c:if test="${aanvraag.getClass().getSimpleName() eq 'MultiPeriodeAanvraag'}">
                    <fmt:formatDate value="${opnameMoment.beginTijdstip.time}" pattern="yyyy-MM-dd " />
                </c:if>
                <fmt:formatDate value="${opnameMoment.beginTijdstip.time}" pattern="HH:mm" /> tot 
                <fmt:formatDate value="${opnameMoment.eindTijdstip.time}" pattern="HH:mm" />  
                <%@include file="les_status_fragment.jsp" %>
            </h3>
            <security:authorize access="hasRole('ADMIN')">
                <a href="<s:url value="/opnames/opname_goedkeuren?toegangscode=${opnameMoment.toegangsCode}&opname=${opnameMoment.id}&aanvraag=${aanvraag.id}"/>">
                    Link voor Lector
                </a>
            </security:authorize>
            <table>
                <tr>
                    <td>OOD</td>
                    <td><c:out value="${opnameMoment.OOD}"/></td>
                </tr>
                <tr>
                    <td>Lokaal</td>
                    <td><c:out value="${opnameMoment.lokaal.naam}"/></td>
                </tr>
                <tr>
                    <td>Klas/Reeks</td>
                    <td><c:out value="${opnameMoment.reeks}"/></td>
                </tr>
                <tr>
                    <td>Verantwoordelijk RecordAid lid</td>
                    <c:choose>
                        <c:when test="${not empty opnameMoment.verantwoordelijke}">
                            <td><c:out value="${opnameMoment.verantwoordelijke.voornaam} ${opnameMoment.verantwoordelijke.achternaam}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td>Niemand Toegewezen</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <td>Lector</td>
                    <td>
                        <c:out value="${opnameMoment.lector.naam}"/>
                        <a href="<c:out value="mailto:${opnameMoment.lector.emailadres}"/>">
                            (<c:out value="${opnameMoment.lector.emailadres}"/>)
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>Goedgekeurd door lector</td>
                    <td>
                        <c:choose>
                            <c:when test="${empty opnameMoment.goedgekeurd}">
                                Niet van toepassing
                            </c:when>
                            <c:when test="${opnameMoment.goedgekeurd}">
                                Ja
                            </c:when>
                            <c:when test="${not opnameMoment.goedgekeurd}">
                                Nee
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
                <security:authorize access="hasRole('ADMIN') or hasRole('KERNLID') or hasRole('BUDDY')">
                    <tr>
                        <td>Toegestane Opnamemethodes</td>
                        <td>
                            <ul>
                                <c:forEach var="opnameMethode" items="${opnameMoment.mogelijkeOpnameMethodes}">
                                    <li><c:out value="${opnameMethode.naam}"/></li>
                                    </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </security:authorize>

                <c:if test="${not empty opnameMoment.opname}">
                    <tr>
                        <td>Opname</td>
                        <td><c:out value="${opnameMoment.opname.locatie.medium} - 
                               ${opnameMoment.opname.locatie.wegwijzer}"/>
                            <security:authorize access="hasRole('ADMIN') or hasRole('KERNLID') or hasRole('BUDDY')">
                                <c:out value=" - ${opnameMoment.opname.status}"/>
                            </security:authorize>
                        </td>
                    </tr>
                </c:if>
            </table>    
        </c:forEach>
    </c:otherwise>
</c:choose>