$("document").ready(function()
    {
        $("#form_updateAccount").submit(checkAccountUpdate);
        boodschapAnimaties();
    });


function checkAccountUpdate()
{
    if($("#nieuw_ww1").val() != $("#nieuw_ww2").val())
    {
        $("#wachtwoord_fout_error").empty();
        $("#wachtwoord_fout_error").append("De wachtwoorden die u hebt ingegeven komen niet overeen.");
        
        boodschapAnimaties();
        
        return false;
    }  
    else if($("#nieuw_ww1").val().length < 3)
    {
        $("#wachtwoord_fout_error").empty();
        $("#wachtwoord_fout_error").append("Het nieuwe wachtwoord moet minstens drie tekens lang zijn.");
        
        boodschapAnimaties();
        
        return false;
    }
    return true;
}

function boodschapAnimaties()
{
    $("#wachtwoord_fout_error").hide();
    $("#wachtwoord_fout_error").fadeIn(2000);
    
    setTimeout(function()
    {
        $("#wachtwoord_fout_error").fadeOut(1000);
    }, 3000);
    
    
    $("#wachtwoord_verandert_boodschap").hide();
    $("#wachtwoord_verandert_boodschap").fadeIn(2000);
    
    setTimeout(function()
    {
        $("#wachtwoord_verandert_boodschap").fadeOut(1000);
    }, 3000);
}