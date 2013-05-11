<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/"%>
<h1>Bericht sturen naar bepaalde groepen</h1>
<m:boodschap/>
<script type="text/javascript">
    $("document").ready(preview);

    function preview() {
        $("#preview").html('<p style="font-weight:bold; font-size:1.5em;">'
                + "${subjectPrefix.subject_prefix} "
                + $("#onderwerp").val() + "</p>" + $("#bericht").val());
    }
</script>
<form method="POST">
    <table>
        <tr>
            <td>Doelgroep</td>
            <td>
                <ul>
                <c:forEach var="groep" items="${groepen}">
                    <li><input type="checkbox" value="${groep}" name="groepen"/>${groep.name}</li>
                </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td><label for="onderwerp">Onderwerp</label></td>
            <td><input type="text" name="onderwerp" id="onderwerp" onkeyup="preview();" onchange="preview();" required="required"/></td>
        </tr>
        <tr>
            <td><label for="bericht">Bericht</label></td>
            <td><textarea id="bericht" rows="15" cols="60" onkeyup="preview();" onchange="preview();" name="bericht" required="required"></textarea></td>
        </tr>
        <tr>
            <td>Preview</td>
            <td>
                <div id="preview">
                </div>
            </td>
        </tr>
    </table>
    <input type="submit" value="Verzenden"/>
</form>