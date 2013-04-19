<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!--<head>
    <script type="text/javascript" src="JavaScript/loginValidatie.js" ></script>
</head>-->
<h1>Inloggen</h1>
<c:if test="${loginrequired != null}">
    <p class="error">Om deze pagina te bekijken moet u ingelogd zijn.</p>
</c:if>
<c:if test="${error != null}">
    <p class="error">${error}</p>
</c:if>
<s:url var="authUrl" value="/static/j_spring_security_check" />
<form id="registratieForm" method="POST" action="${authUrl}">
    <table>
        <tr>
            <td><label for="email">E-mail adres (KHL)</label></td>
            <td><input type="text" id="email" name="j_username" class="tb"/></td>
            <td id="email_error" class="error"></td>
        </tr>
        <tr>
            <td><label for="wachtwoord">Paswoord</label></td>
            <td><input type="password" id="wachtwoord" name="j_password"/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Log in"/></td>
            <td>&nbsp;</td>
        </tr>
    </table>
</form>
<p>Nog geen RecordAid account? Registreer je op onze <a href="<s:url value="login/registreren"/>">registratie-pagina.</a></p>