<%@page import="be.khleuven.eindwerk.domain.Rollen"%>
<%@page import="be.khleuven.eindwerk.domain.Gebruiker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="css/default.css" />

<div id="content">
    <c:choose>
        <c:when test="${sessionScope.gebruiker == null}" >
            <div id="head">
                <a class="probleema" href="login.jsp">Login</a>
            </div>

            <div id="navigation">
                <ul id="navlist">
                    <li><a href="home.jsp" >Home</a></li>
                    <li><a href="ingelogdZijn.jsp">Mijn Aanvragen</a></li>
                    <li><a href="ingelogdZijn.jsp" >Probleem melden</a></li>
                    <li><a href="ActionServlet?action=faq" >FAQ</a></li>
                </ul>
            </div>
        </c:when>

        <c:otherwise>
            <div id="head">
                <a class="probleema" href="account.jsp">${sessionScope.gebruiker.voornaam}</a>
                <a class="probleema" href="ActionServlet?action=logout">Log uit</a>
            </div>

            <div id="navigation">
                <ul id="navlist">
                    <li><a href="home.jsp" >Home</a></li>
                    <li><a href="ActionServlet?action=mijnAanvragen">Mijn Aanvragen</a></li>
                    <li><a href="support.jsp" >Probleem melden</a></li>
                    <li><a href="ActionServlet?action=faq">FAQ</a></li>
                 
                    <c:if test="${sessionScope.gebruiker != null && sessionScope.gebruiker.rol ne 'STUDENT'}">
                        <li><a href="ActionServlet?action=reserveren">Reserveren</a></li><br>
                        </c:if>

                    <c:if test="${sessionScope.gebruiker != null && sessionScope.gebruiker.rol ne 'STUDENT' && sessionScope.gebruiker.rol ne 'LEERKRACHT'}">
                        <li><a href="ActionServlet?action=berichten&method=krijg">Berichten</a></li>
                        <li><a href="ActionServlet?action=alleAanvragen">Aanvragen-beheer</a></li>
                        </c:if>

                    <c:if test="${sessionScope.gebruiker != null && (sessionScope.gebruiker.rol eq 'KERNLID' || sessionScope.gebruiker.rol eq 'ADMIN')}">
                        <li><a href="ActionServlet?action=beantwoordFAQ">FAQ-beheer</a></li>
                        <li><a href="ActionServlet?action=gebruikerBeheer">Gebruiker-beheer</a></li>
                        <li><a href="ActionServlet?action=itemBeheer">Item-beheer</a></li>
                        </c:if>
                </ul>
            </div>
        </c:otherwise>
    </c:choose>
    <div class="page">