$("document").ready(function()
{
    $("#naam").focus(disapearError);
    $("#naam").focusout(checkNaam);
    
    errorAnimation();
    
    $("#formAddItem").submit(checkNaam);
});

function checkNaam()
{
    $("#error").empty();
    if(!($("#naam").val().match(/([a-zA-Z0-9]+( |_)?)+/)))
    {
        $("#error").hide();
        $("#error").append("Dit is geen goede naam.");
        $("#naam").val("");
        $("#error").fadeIn("slow");
        return false;
    }
    else
    {
        return true;
    }
}

function errorAnimation()
{
    $("#error").hide();
    $("#error").fadeIn("slow");
}

function disapearError()
{
    $("#error").fadeOut("slow");
}