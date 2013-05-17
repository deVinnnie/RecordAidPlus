var emailOK = true;
var achternaamOK = true;
var voornaamOK = true;
var wachtwoordOK = true;
var wachtwoordConfirmatieOK = true;

$('document').ready(function()
{
    $("#emailadres").focus(hide).focusout(checkEmail);
    $("#achternaam").focus(hide).focusout(checkAchternaam);
    $("#voornaam").focus(hide).focusout(checkVoornaam);
    $("#wachtwoord").focus(hide).focusout(checkWachtwoord);
    $("#wachtwoord_confirmation").focus(hide).focusout(checkWachtwoordConfirmation);
    $("#registratieForm").submit(registreer_gebruiker);
});

function registreer_gebruiker()
{
    checkEmail();
    checkAchternaam();
    checkVoornaam();
    checkWachtwoord();
    checkWachtwoordConfirmation();
    console.log(""+emailOK+voornaamOK+achternaamOK+ wachtwoordOK+ wachtwoordConfirmatieOK); 
    return (emailOK && achternaamOK && voornaamOK && wachtwoordOK && wachtwoordConfirmatieOK);
}

function checkEmail()
{
    emailOK = true;
    $("#email_error").empty();
    if (!($("#emailadres").val().match(/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@khleuven.be$/)
            || $("#emailadres").val().match(/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@student.khleuven.be$/)))
    {
        showError("#email_error", "Geen goed emailadres");
        emailOK = false;
    }
    else if ($("#voornaam").val() == '' && $("#achternaam").val() == '') {
        var arrayStrings = $("#emailadres").val().split('@');
        var arrayNaam = arrayStrings[0].split('.');
        var voornaam = arrayNaam[0];
        var achternaam = "";
        for (var i = 1; i < arrayNaam.length; i++) {
            if (i !== 1) {
                achternaam += " ";
            }
            achternaam += arrayNaam[i].charAt(0).toUpperCase() + arrayNaam[i].slice(1);
            ;
        }

        voornaam = voornaam.charAt(0).toUpperCase() + voornaam.slice(1);

        $("#voornaam").val(voornaam);
        $("#achternaam").val(achternaam);
    }
}

function checkAchternaam()
{
    achternaamOK = true;
    $("#achternaam_error").empty();
    if (!($("#achternaam").val().match(/([a-zA-ZáíóúñÑö]+( |'|-)?)+/)))
    {
        showError("#achternaam_error", "Geen goede achternaam");
        achternaamOK = false;
    }
}
function checkVoornaam()
{
    var voornaamOK = true;
    $("#voornaam_error").empty();
    if (!$("#voornaam").val().match(/([a-zA-ZáíóúñÑö]+( |'|-)?)+/))
    {
        showError("#voornaam_error", "Geen goede voornaam");
        voornaamOK = false;
    }
}
function checkWachtwoord()
{
    wachtwoordOK = true;
    $("#wachtwoord_error").empty();
    if ($("#wachtwoord").val().length < 6)
    {
        showError("#wachtwoord_error", "Uw wachtwoord is te kort");
        wachtwoordOK = false;
    }
}
function checkWachtwoordConfirmation()
{
    wachtwoordConfirmatieOK = true;
    $("#wachtwoord_confirmation_error").empty();
    if ($("#wachtwoord").val() !== $("#wachtwoord_confirmation").val())
    {
        showError("#wachtwoord_confirmation_error", "De wachtwoorden komen niet overeen");
        wachtwoordConfirmatieOK = false;
    }
}
function hide(event) {
    $("#" + event.target.id + "_error").fadeOut("slow");
}

function showError(id, message) {
    $(id).hide().append(message).fadeIn("slow");
}