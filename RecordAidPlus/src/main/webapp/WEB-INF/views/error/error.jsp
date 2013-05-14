<%@ page isErrorPage="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Er is een fout opgetreden!</h1>
<p class="error">${exception.getMessage()}</p>