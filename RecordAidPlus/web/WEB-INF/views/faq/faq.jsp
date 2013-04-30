<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<h1>Frequently Asked Questions</h1>
<c:if test="${not empty faq_toegevoegd}">
    <p class="succes">Bedankt voor uw vraag, ze werd goed ontvangen. U zal spoedig een email ontvangen met een antwoord.</p>
</c:if>

<c:choose>
    <c:when test="${not empty faqList}">
        <div id="accordion">
            <c:forEach var="faq" items="${faqList}">
                <h3>${faq.vraag}</h3>
                <p>${faq.antwoord}</p>
            </c:forEach>
        </div>
        <script type="text/javascript">
            $("document").ready(function()
            {
                $("#accordion").accordion(
                        {
                            active: false,
                            collapsible: true
                        });
            });
        </script>
    </c:when>
    <c:otherwise>
        <p>Er zijn momenteel nog geen Frequently asked questions.</p>
    </c:otherwise>
</c:choose>
<p>
    Staat jouw vraag er niet bij? 
    Post je <a href="<s:url value="/faq?nieuw"/>">nieuwe vraag</a> 
    en een RecordAid-lid zal deze zo snel mogelijk beantwoorden.
</p>