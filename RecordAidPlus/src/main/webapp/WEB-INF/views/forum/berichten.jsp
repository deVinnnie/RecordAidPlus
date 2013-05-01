<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="../header.jsp" %>
<%@include file="paginaCheck/checkGeenStudentenLeerkrachten.jsp" %>
<title>RecordAid Forum</title>
        <h1>RecordAid Forum</h1>
        <div id="berichten">
            <c:choose>
                <c:when test="${topics ne null}">
                    <c:forEach var="topic"  items="${topics}">
                        <div class="bericht" id="${topic.id}">
                            <h2>${topic.titel}</h2>
                            Geplaats door <b>${topic.messages[0].zender.voornaam} ${topic.messages[0].zender.naam}</b> op <fmt:formatDate value="${topic.messages[0].datum.time}" pattern="dd/MM/yyyy" />
                            <p>
                                ${topic.messages[0].text}
                                <br>
                            </p>   <c:choose>
                                <c:when test="${fn:length(topic.messages) eq 1}">
                                    <p>Er heeft nog niemand geantwoord op dit bericht.</p>
                                </c:when>

                                <c:otherwise>
                                    <p>Er werd <b>${fn:length(topic.messages)-1}</b> keer geantwoord op dit bericht.</p>
                                </c:otherwise>
                            </c:choose>
                            <a class="link" href="ActionServlet?action=berichten&method=antwoord&topicid=${topic.id}">Antwoorden</a>

                            <c:if test="${sessionScope.gebruiker.rol eq 'ADMIN'}">
                                <a class="link" href="ActionServlet?action=berichten&method=verwijder&topicid=${topic.id}">Verwijder</a>
                            </c:if>
                        </div>

                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>Er zijn nog geen berichten geplaatst</p>
                </c:otherwise>
            </c:choose>
            <p><a href="ActionServlet?action=berichten&method=nieuw">Nieuw bericht plaatsen.</a></p>
        </div>