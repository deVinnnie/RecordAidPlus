<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div id="fb-root"></div>
<script>(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<div class="footer">
    <div class="footerNavigatie">
        <h4>Navigatie</h4>
        <ul>
            <li><a href="<s:url value="/home"/>">Home</a></li>
            <li><a href="<s:url value="/aanvragen"/>">Mijn Aanvragen</a></li>
            <li><a href="<s:url value="/support"/>" >Support</a></li>
            <li><a href="<s:url value="/faq"/>">FAQ</a></li>       
        </ul>
    </div>

    <div class="contact">
        <h4>Contact</h4>
        <p> 
            Je kan ons altijd een vraag stellen via de 
            <a href="<s:url value="/faq"/>">FAQ</a>. 
            Of voor dringende zaken kan je ons ook 
            <a href="mailto:recordaid@khleuven.be?subject=Bericht%20vanaf%20website">mailen</a>
        </p>
    </div>

    <div class="social">
        <h4>Sociaal</h4>
        <ul>
            <li>
                <img src="<s:url value="/resources/images/f_logo30.png"/>" alt="Facebook logo"/>
                <a href="https://www.facebook.com/KhLeuvenScreencasting">Facebook</a>
            </li>
            <li>
                <img src="<s:url value="/resources/images/yt-logo.png"/>" alt="YouTube logo"/>
                <a href="http://www.youtube.com/user/RecordAid">YouTube</a>
            </li>
        </ul>
        <div class="fb-follow" data-href="https://www.facebook.com/KhLeuvenScreencasting" data-show-faces="true" data-font="lucida grande" data-width="450"></div>
    </div>
</div>