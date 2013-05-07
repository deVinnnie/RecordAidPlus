<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/" %>
<h1>Stel een nieuwe vraag</h1>
<m:boodschap/>
<p>Geef in dit tekstveld je vraag in.</p>
<springforms:form id="newFAQ" method="POST" modelAttribute="faq">
    <springforms:textarea path="vraag" rows="6" cols="50"/><br>
    <input type="submit" value="Stel vraag"/>
</springforms:form>