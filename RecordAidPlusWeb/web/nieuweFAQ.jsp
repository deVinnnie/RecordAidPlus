<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkIngelogd.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nieuwe vraag</title>
    </head>
    <body>
         
        <h1>Stel een nieuwe vraag</h1>
        <form id="newFAQ" method="POST" action="ActionServlet">
            <textarea rows="6" cols="50" name="vraag" class="ta"></textarea>
             <br>
             <input type="hidden" name="action" value="nieuweFAQ"/>
             <input type="submit" value="Stel vraag"/>
        </form>
        <%@include file="footer.jsp" %>

    </body>
</html>
