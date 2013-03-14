<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%@include file="paginaCheck/checkIngelogd.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="jquery-ui-1.10.1.custom 2/css/blitzer/jquery-ui-1.10.1.custom.css"/>
        <script type="text/javascript" src="jquery-ui-1.10.1.custom 2/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="jquery-ui-1.10.1.custom 2/js/jquery-ui-1.10.1.custom.js"></script>
        <script type="text/javascript" src="jquery-ui-1.10.1.custom 2/js/jquery-ui-1.10.1.custom.min.js"></script>
        <script type="text/javascript" src="JavaScript/nieuweAanvraag.js"></script>
        <title>Nieuwe aanvraag</title>
    </head>

    <body>
        

        <h1>Een nieuwe aanvraag plaatsen</h1>
        
        <p id="nieuwe_aanvraag_error" style="color: red;">${nieuwe_aanvraag_error}</p>

        <form id="aanvraagForm" method="POST" action="ActionServlet">
            (*) = verplicht invulveld.
            <table>
                <tr>
                    <td><label for="vak">Vak</label></td>
                    <td><input autofocus="true" type="text" name="vak" id="vak" value="${param.vak}" class="tb"/></td>
                    <td>*</td>
                    <td id="vak_error" style="color: red;"></td>
                </tr>

                <tr>
                    <td><label for="reeks">Klas/Reeks</label></td>
                    <td><input type="text" name="reeks" id="reeks" value="${param.reeks}" class="tb"/></td>
                    <td>*</td>
                    <td id="reeks_error" style="color: red;"></td>
                </tr>

                <tr>
                    <td><label for="lokaal">Lokaal</label></td>
                    <td><input type="text" name="lokaal" id="lokaal" value="${param.lokaal}" class="tb"/></td>
                    <td>*</td>
                    <td id="lokaal_error" style="color: red;"></td>
                </tr>
                <tr>
                    <td><label for="datum">Datum</label></td>
                    <td><input name="datum" id="datum" readonly="true" value="${param.datum}" class="tb"></input></td>
                    <td>*</td>
                    <td id="datum_error" style="color: red;"></td>
                </tr>
                <tr>
                    <td><label>Begin uur</label> </td>
                    <td>
                        <select name="beginUurH" id="beginUurH">
                            <option value="08" <c:if test="${param.beginUurH == '08'}">selected</c:if>>08</option>
                            <option value="09" <c:if test="${param.beginUurH == '09'}">selected</c:if>>09</option>
                            <option value="10" <c:if test="${param.beginUurH == '10'}">selected</c:if>>10</option>
                            <option value="11" <c:if test="${param.beginUurH == '11'}">selected</c:if>>11</option>
                            <option value="12" <c:if test="${param.beginUurH == '12'}">selected</c:if>>12</option>
                            <option value="13" <c:if test="${param.beginUurH == '13'}">selected</c:if>>13</option>
                            <option value="14" <c:if test="${param.beginUurH == '14'}">selected</c:if>>14</option>
                            <option value="15" <c:if test="${param.beginUurH == '15'}">selected</c:if>>15</option>
                            <option value="16" <c:if test="${param.beginUurH == '16'}">selected</c:if>>16</option>
                            <option value="17" <c:if test="${param.beginUurH == '17'}">selected</c:if>>17</option>
                            <option value="18" <c:if test="${param.beginUurH == '18'}">selected</c:if>>18</option>
                            <option value="19" <c:if test="${param.beginUurH == '19'}">selected</c:if>>19</option>
                            <option value="20" <c:if test="${param.beginUurH == '20'}">selected</c:if>>20</option>
                            <option value="21" <c:if test="${param.beginUurH == '21'}">selected</c:if>>21</option>
                            <option value="22" <c:if test="${param.beginUurH == '22'}">selected</c:if>>22</option>
                        </select>
                        <select name="beginUurM" id="beginUurM">
                            <option value="00" <c:if test="${param.beginUurM == '00'}">selected</c:if>>00</option>
                            <option value="15" <c:if test="${param.beginUurM == '15'}">selected</c:if>>15</option>
                            <option value="30" <c:if test="${param.beginUurM == '30'}">selected</c:if>>30</option>
                            <option value="45" <c:if test="${param.beginUurM == '45'}">selected</c:if>>45</option>
                        </select>
                    </td>
                    <td>*</td>
                    <td id="uren_error" style="color: red;"></td>
                </tr>
                <tr>
                    <td><label>Eind uur</label> </td>
                    <td>
                        <select name="eindUurH" id="eindUurH">
                            <option value="08" <c:if test="${param.eindUurH == '08'}">selected</c:if>>08</option>
                            <option value="09" <c:if test="${param.eindUurH == '09'}">selected</c:if>>09</option>
                            <option value="10" <c:if test="${param.eindUurH == '10'}">selected</c:if>>10</option>
                            <option value="11" <c:if test="${param.eindUurH == '11'}">selected</c:if>>11</option>
                            <option value="12" <c:if test="${param.eindUurH == '12'}">selected</c:if>>12</option>
                            <option value="13" <c:if test="${param.eindUurH == '13'}">selected</c:if>>13</option>
                            <option value="14" <c:if test="${param.eindUurH == '14'}">selected</c:if>>14</option>
                            <option value="15" <c:if test="${param.eindUurH == '15'}">selected</c:if>>15</option>
                            <option value="16" <c:if test="${param.eindUurH == '16'}">selected</c:if>>16</option>
                            <option value="17" <c:if test="${param.eindUurH == '17'}">selected</c:if>>17</option>
                            <option value="18" <c:if test="${param.eindUurH == '18'}">selected</c:if>>18</option>
                            <option value="19" <c:if test="${param.eindUurH == '19'}">selected</c:if>>19</option>
                            <option value="20" <c:if test="${param.eindUurH == '20'}">selected</c:if>>20</option>
                            <option value="21" <c:if test="${param.eindUurH == '21'}">selected</c:if>>21</option>
                            <option value="22" <c:if test="${param.eindUurH == '22'}">selected</c:if>>22</option>
                        </select>
                        <select name="eindUurM" id="eindUurM">
                            <option value="00" <c:if test="${param.eindUurM == '00'}">selected</c:if>>00</option>
                            <option value="15" <c:if test="${param.eindUurM == '15'}">selected</c:if>>15</option>
                            <option value="30" <c:if test="${param.eindUurM == '30'}">selected</c:if>>30</option>
                            <option value="45" <c:if test="${param.eindUurM == '45'}">selected</c:if>>45</option>
                        </select>
                    </td>
                    <td>*</td>
                </tr>
                <tr>
                    <td><label>Email lector </label> </td> <td><input type="text" name="lector" id="lector" value="${param.lector}" class="tb"/></td>
                    <td>*</td>
                    <td id="lector_error" style="color: red;"></td>
                </tr>
                <tr>
                    <td><label>Departement</label> </td>
                    <td>
                        <select name="departement">
                            <option value="G&T">G&T</option>
                            <option value="SSH">SSH</option>
                            <option value="DLO Diest">DLO Diest</option>
                            <option value="Naamse steenweg">Naamse steenweg</option>
                            <option value="Hertogstraat">Hertogstraat</option>
                        </select>
                    </td>
                    <td>*</td>
                </tr>
            </table>
            
            <h3>Reden</h3>
            <textarea rows="6" cols="50" name="reden" id="reden" value="${param.reden}" class="ta"></textarea><br>
            <p id="reden_error" style="color: red;"></p>
            <input type="hidden" name="action" value="nieuweAanvraag"/>
            <input type="submit" value="Verzenden"/>
        </form>
            
           <%@include file="footer.jsp" %>
 
    </body>
</html>