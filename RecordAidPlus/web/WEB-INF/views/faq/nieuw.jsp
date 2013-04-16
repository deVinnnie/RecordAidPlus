<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<h1>Stel een nieuwe vraag</h1>
<springforms:form id="newFAQ" method="POST" modelAttribute="faq">
    <springforms:textarea path="vraag" rows="6" cols="50" name="vraag" class="ta"/>
    <input type="submit" value="Stel vraag"/>
</springforms:form>