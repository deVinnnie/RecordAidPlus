<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<h1>Mail</h1>
<a href="<s:url value="/mailing/maillijst"/>">Nieuw bericht versturen naar gebruikers</a>
<h2>Berichten</h2>
<form method="GET">
    <table>
        <tr>
            <td><label for="messages">Bericht</label></td>
            <td>
                <select id="messages" name="selected_message" onchange="submit();">
                    <c:if test="${empty selectedMessage}">
                        <option value="" disabled selected>Kies een bericht</option>
                    </c:if>
                    <c:forEach var="message" items="${messages}">
                        <option value="${message.id}" 
                                <c:if test="${not empty selectedMessage && selectedMessage.id == message.id}">
                                    selected  
                                </c:if>>
                            ${message.subject}
                        </option> 
                    </c:forEach>
                </select>
            </td> 
            <td>
                <noscript>
                <input type="submit" value="Kies"/>
                </noscript>
            </td>
        </tr>
    </table>
</form>

<c:choose>
    <c:when test="${not empty selectedMessage}">
        <script type="text/javascript">
            $("document").ready(preview);
    
            function preview(){ 
                $("#preview").html('<p style="font-weight:bold; font-size:1.5em;">' 
                        +"${selectedMessage.subjectPrefix.subject_prefix} "
                        +$("#subject").val()+"</p>"+$("#message").val());
            }
        </script>
        <springforms:form id="formEditMailMessage" modelAttribute="selectedMessage" method="POST">
            <table>
                <tr>
                    <td><label for="subject">Onderwerp</label></td>
                    <td><springforms:input type="text" id="subject" path="subject" 
                                        onkeyup="preview();" onchange="preview();"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="message">Inhoud</label></td>
                    <td><springforms:textarea id="message" onkeyup="preview();" onchange="preview();" 
                                          rows="15" cols="60" path="message"/></td>
                </tr>
                <tr>
                    <td>Preview</td>
                    <td>
                        <div id="preview">
                        </div>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Opslaan"/>
        </springforms:form>
    </c:when>
    <c:otherwise>
        Selecteer een bericht om te bewerken. 
    </c:otherwise>
</c:choose>