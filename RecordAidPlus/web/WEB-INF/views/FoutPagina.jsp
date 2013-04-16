<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Er trad een fout op</title>
    </head>
    
    <body>
        <p style="color: red;">${error}</p>
    </body>
    
    <%@include file="footer.jsp" %>
</html>