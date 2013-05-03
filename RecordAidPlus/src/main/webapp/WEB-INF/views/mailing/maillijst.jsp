<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<h1>Bericht sturen naar geïnteresseerden</h1>
<script type="text/javascript">
    $("document").ready(preview);

    function preview() {
        $("#preview").html('<p style="font-weight:bold; font-size:1.5em;">'
                + "${subjectPrefix.subject_prefix} "
                + $("#onderwerp").val() + "</p>" + $("#bericht").val());
    }
</script>
<form>
    <table>
        <tr>
            <td><label for="onderwerp">Onderwerp</label></td>
            <td><input type="text" name="onderwerp" id="onderwerp" onkeyup="preview();" onchange="preview();"/></td>
        </tr>
        <tr>
            <td><label for="bericht">Bericht</label></td>
            <td><textarea id="bericht" rows="15" cols="60" onkeyup="preview();" onchange="preview();" name="bericht"></textarea></td>
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