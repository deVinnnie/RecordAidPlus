<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Mijn aanvragen</h1>
<c:choose>
    <c:when test="${empty aanvragen}">
        <p>U heeft momenteel nog geen aanvragen.</p>
    </c:when>
    <c:otherwise>
        <script type="text/javascript">
            $(document).ready(function()
            {
                datatable("#aanvragen_tabel");
            });
        </script>
        <table id="aanvragen_tabel">
            <thead>
                <tr>
                    <th>Datum/Periode</th>
                    <th>Status</th>
                    <th>Details</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="aanvraag" items="${aanvragen}" >
                    <tr align="center">
                        <td>
                         <c:choose>
                                <c:when test="${aanvraag.getClass().getSimpleName() eq 'DagAanvraag'}">
                                    <fmt:formatDate value="${aanvraag.lesDatum.time}" pattern="yyyy-MM-dd" />
                                </c:when>
                                <c:when test="${aanvraag.getClass().getSimpleName() eq 'MultiPeriodeAanvraag'}">
                                    <fmt:formatDate value="${aanvraag.periode.beginTime.time}" pattern="yyyy-MM-dd" />
                                    tot 
                                    <fmt:formatDate value="${aanvraag.periode.endTime.time}" pattern="yyyy-MM-dd" />
                                </c:when>
                            </c:choose>    
                        </td>
                        <td>${aanvraag.status}</td>
                        <td><a href="<s:url value="/aanvragen/detail?id=${aanvraag.id}"/>">Details</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<p>Dien een <a href="<s:url value="/aanvragen/nieuw"/>">nieuwe aanvraag</a> in.</p>

<h2>Geschiedenis</h2>
<c:choose>
    <c:when test="${not empty dossier.geschiedenis.gebeurtenissen}">
         <script type="text/javascript">
            $('document').ready(function(){
                datatable("#geschiedenis"); 
            }); 
        </script>
            <table id="geschiedenis">
                <thead>
                    <tr>
                        <th>Datum</th>
                        <th>Gebeurtenis</th>
                        <th>Wie?</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach var="gebeurtenis" items="${dossier.geschiedenis.gebeurtenissen}">
                    <tr>
                        <td><fmt:formatDate value="${gebeurtenis.tijdstip.time}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td><c:out value="${gebeurtenis.message}"/></td>
                        <td><c:out value="${gebeurtenis.betrokkenGebruiker.voornaam} ${gebeurtenis.betrokkenGebruiker.achternaam}"/></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
    </c:when>
    <c:otherwise>
        <p>Er is nog geschiedenis voor dit dossier.</p>
    </c:otherwise>
</c:choose>