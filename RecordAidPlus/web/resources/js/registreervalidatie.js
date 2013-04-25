var emailOK = false; 
var achternaamOK = false;
var voornaamOK = false;
var wachtwoordOK = false;
var wachtwoordConfirmatieOK = false;

$("document").ready(function()
{
    $("#email").focus(hide).focusout(checkEmail);
    $("#naam").focus(hide).focusout(checkAchternaam);
    $("#voornaam").focus(hide).focusout(checkVoornaam);
    $("#wachtwoord").focus(hide).focusout(checkWachtwoord);
    $("#wachtwoordConfirmation").focus(hide).focusout(checkWachtwoordConfirmation);
    
    gebruikerBestaatError();
    
    $("#registratieForm").submit(registreer_gebruiker);
});

function registreer_gebruiker()
{
    checkEmail();
    checkAchternaam();
    checkVoornaam();
    checkWachtwoord1();
    checkWachtwoordConfirmation();    
    return (emailOK && achternaamOK && voornaamOK 
            && wachtwoordOK && wachtwoordConfirmatieOK);
}

function gebruikerBestaatError()
{   
    $("#gebruiker_bestaat_error").hide().fadeIn("slow");
}

function gebruikerBestaatErrorDisapear()
{
    $("#gebruiker_bestaat_error").fadeOut("slow");
}

function checkEmail()
{
    emailOK = true;
    $("#email_error").empty();
    if(!($("#email").val().match(/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@khleuven.be$/) 
            || $("#email").val().match(/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@student.khleuven.be$/)))
    {
        $("#email_error").hide()
            .append("Geen goed emailadres")
            .fadeIn("slow");
        emailOK = false;
    }
}

function checkAchternaam()
{ 
    achternaamOK = true;
    $("#naam_error").empty();
    if(!($("#naam").val().match(/([a-zA-ZáíóúñÑö]+( |'|-)?)+/)))
    {
        $("#naam_error").hide()
            .append("Geen goede naam")
            .fadeIn("slow");
        achternaamOK = false;
    }
}

function checkVoornaam()
{
    voornaamOK = true;
    $("#voornaam_error").empty();
    if(!$("#voornaam").val().match(/([a-zA-ZáíóúñÑö]+( |'|-)?)+/))
    {
        $("#voornaam_error").hide()
            .append("Geen goede voornaam")
            .fadeIn("slow");
        voornaamOK = false;
    }
}

function checkWachtwoord()
{
    wachtwoordOK = true;
    $("#wachtwoord_error").empty();
    if($("#wachtwoord").val().length < 6)
    {
        $("#wachtwoord_error").hide()
            .append("Uw wachtwoord is te kort")
            .fadeIn("slow");
        wachtwoordOK = false;
    }
}

function checkWachtwoordConfirmation()
{
    wachtwoordConfirmatieOK = true;
    $("#wachtwoordConfirmation_error").empty();
    if($("#wachtwoord1").val() !== $("#wachtwoord2").val())
    {
        $("#wachtwoordConfirmation_error").hide()
            .append("De wachtwoorden komen niet overeen")
            .fadeIn("slow");
        wachtwoordConfirmatieOK = false;
    }
}

function hide(event){
    $("#"+event.target.id+"_error").fadeOut("slow"); 
    gebruikerBestaatErrorDisapear();
}