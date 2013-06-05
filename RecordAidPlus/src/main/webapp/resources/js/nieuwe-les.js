$('document').ready(function()
{
    $("#nieuweLes").submit(checkUren);
    datepicker("#datum");
});

function checkUren()
{
    $("#errors").empty();
    valid = true;
    
    beginUur = parseInt($("#beginUurH").val()); 
    eindUur = parseInt($("#eindUurH").val()); 
    beginMinuut = parseInt($("#beginUurM").val()); 
    eindMinuut = parseInt($("#eindUurM").val());
 
    if (beginUur > eindUur || (beginUur == eindUur && beginMinuut >= eindMinuut))
    {
        $("#errors").hide()
                .append("Het beginuur kan niet later zijn dan het einduur.")
                .fadeIn("slow");
        valid = false;
    }
    return valid;
}