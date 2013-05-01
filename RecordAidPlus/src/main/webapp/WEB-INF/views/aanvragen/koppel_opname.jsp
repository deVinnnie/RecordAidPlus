<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="springforms" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<h1>Opname koppelen aan les</h1>
<springforms:form modelAttribute="nieuweOpname" method="POST">
    <table>
        <tr>
            <td>Waar staat de opname? (Youtube, Toledo&hellip;)</td>
            <td><springforms:input type="text" path="locatie.medium"/></td>
        </tr>
        <tr>
            <td>Hoe kan men de opname vinden? (Link, pad&hellip;)</td>
            <td><springforms:input type="text" path="locatie.wegwijzer"/></td>
        </tr>
        <tr>
            <td>Status</td>
            <td>
                <springforms:select path="status">
                    <springforms:options/>
                </springforms:select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Opslaan"</td>
        </tr>
        
    </table>
</springforms:form>