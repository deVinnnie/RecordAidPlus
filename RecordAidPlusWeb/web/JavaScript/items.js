var verif = false;


$("document").ready(function()
{
    $("#naam").focus(disapearError);
    $("#naam").focusout(checkNaam);
    
    errorAnimation();
    
    $("#formAddItem").submit(addItem);
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
        verif = false;
    }
    else
    {
        verif = true;
    }
}


function addItem()
{
    checkNaam();
    if(verif)
    {
        return true;
    }
    else
    {
        return false;
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