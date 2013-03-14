$("document").ready(function()
{
    gegevensOnjuistError();
    
    $("#email").focus(gegevensOnjuistDisapear);
    $("#wachtwoord").focus(gegevensOnjuistDisapear);
});


function gegevensOnjuistError()
{
    $("#gegevens_onjuist").hide();
    $("#gegevens_onjuist").fadeIn(2000);
}


function gegevensOnjuistDisapear()
{
    $("#gegevens_onjuist").fadeOut(1000);
}