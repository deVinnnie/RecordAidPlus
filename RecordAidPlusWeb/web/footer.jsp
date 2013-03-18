<%-- 
    Document   : footer
    Created on : Mar 4, 2013, 9:18:58 PM
    Author     : Maxime
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/footer.css" />
</div>
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
            <li><a href="home.jsp" >Home</a></li>
            <li><a href="ActionServlet?action=mijnAanvragen">Mijn Aanvragen</a></li>
            <li><a href="support.jsp" >Support</a></li>
            <li><a href="ActionServlet?action=faq">FAQ</a></li>       
        </ul>
    </div>

    <div class="contact">
        <h4>Contact</h4>

        <p>
            Je kan ons altijd een vraag stellen via de <a href="ActionServlet?action=faq">FAQ-pagina</a>. Of voor dringende zaken kan je ons ook <a href="mailto:recordaid@khleuven.be?subject=Bericht vanaf website">mailen</a>
        </p>
    </div>

    <div class="social">
        <h4>Sociaal</h4>
        <ul>
            <li class="fb-li"><a href="https://www.facebook.com/KhLeuvenScreencasting">Facebook</a></li>
            <li class="yt-li"><a href="http://www.youtube.com/user/RecordAid">YouTube</a></li>
            <div class="fb-follow" data-href="https://www.facebook.com/KhLeuvenScreencasting" data-show-faces="true" data-font="lucida grande" data-width="450"></div>
        </ul>
    </div>
</div>
</div>