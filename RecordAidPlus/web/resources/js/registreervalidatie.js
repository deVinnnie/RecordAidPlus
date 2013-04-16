var emailOK = false;
var naamOK = false;
var voornaamOK = false;
var wachtwoord1OK = false;
var wachtwoord2OK = false;

$("document").ready(function()
{
    $("#email").focus(emailDisapearError);
    $("#email").focusout(checkEmail);
    $("#naam").focus(naamDisapearError);
    $("#naam").focusout(checkNaam);
    $("#voornaam").focus(voornaamDisapearError);
    $("#voornaam").focusout(checkVoornaam);
    $("#wachtwoord1").focus(wachtwoord1DisapearError);
    $("#wachtwoord1").focusout(checkWachtwoord1);
    $("#wachtwoord2").focus(wachtwoord2DisapearError);
    $("#wachtwoord2").focusout(checkWachtwoord2);
    
    gebruikerBestaatError();
    
    $("#registratieForm").submit(registreer_gebruiker);
});


function registreer_gebruiker()
{
    checkEmail();
    checkNaam();
    checkVoornaam();
    checkWachtwoord1();
    checkWachtwoord2();    
    return (emailOK && naamOK && voornaamOK && wachtwoord1OK && wachtwoord2OK);
}

function gebruikerBestaatError()
{   
    $("#gebruiker_bestaat_error").hide();
    $("#gebruiker_bestaat_error").fadeIn(2000);
}

function gebruikerBestaatErrorDisapear()
{
    $("#gebruiker_bestaat_error").fadeOut(1000);
}

function checkEmail()
{
    $("#email_error").empty();
    if(!($("#email").val().match(/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@khleuven.be$/) || $("#email").val().match(/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@student.khleuven.be$/)))
    {
        $("#email_error").hide();
        $("#email_error").append("Geen goed emailadres");
        $("#email").val("");
        $("#email_error").fadeIn("slow");
        emailOK = false;
    }
    else
    {
        emailOK = true;
    }
}

function checkNaam()
{
    $("#naam_error").empty();
    if(!($("#naam").val().match(/([a-zA-ZáíóúñÑö]+( |'|-)?)+/)))
    {
        $("#naam_error").hide();
        $("#naam_error").append("Geen goede naam");
        $("#naam").val("");
        $("#naam_error").fadeIn("slow");
        naamOK = false;
    }
    else
    {
        naamOK = true;
    }
}

function checkVoornaam()
{
    $("#voornaam_error").empty();
    if(!($("#voornaam").val().match(/([a-zA-ZáíóúñÑö]+( |'|-)?)+/)))
    {
        $("#voornaam_error").hide();
        $("#voornaam_error").append("Geen goede voornaam");
        $("#voornaam").val("");
        $("#voornaam_error").fadeIn("slow");
        voornaamOK = false;
    }
    else
    {
        voornaamOK = true;
    }
}

function checkWachtwoord1()
{
    $("#wachtwoord1_error").empty();
    if($("#wachtwoord1").val().length < 3)
    {
        $("#wachtwoord1_error").hide();
        $("#wachtwoord1_error").append("Uw wachtwoord is te kort");
        $("#wachtwoord1_error").fadeIn("slow");
        wachtwoord1OK = false;
    }
    else
    {
        wachtwoord1OK = true;
    }
}

function checkWachtwoord2()
{
    $("#wachtwoord2_error").empty();
    if($("#wachtwoord1").val() != $("#wachtwoord2").val())
    {
        $("#wachtwoord2_error").hide();
        $("#wachtwoord2_error").append("Uw wachtwoorden komen niet overeen");
        $("#wachtwoord2_error").fadeIn("slow");
        wachtwoord2OK = false;
    }
    else
    {
        wachtwoord2OK = true;
    }
}

function emailDisapearError()
{
    $("#email_error").fadeOut("slow");
    gebruikerBestaatErrorDisapear();
}

function naamDisapearError()
{
    $("#naam_error").fadeOut("slow");
    gebruikerBestaatErrorDisapear();
}

function voornaamDisapearError()
{
    $("#voornaam_error").fadeOut("slow");
    gebruikerBestaatErrorDisapear();
}


function wachtwoord1DisapearError()
{
    $("#wachtwoord1_error").fadeOut("slow");
    gebruikerBestaatErrorDisapear();
}

function wachtwoord2DisapearError()
{
    $("#wachtwoord2_error").fadeOut("slow");
    gebruikerBestaatErrorDisapear();
}