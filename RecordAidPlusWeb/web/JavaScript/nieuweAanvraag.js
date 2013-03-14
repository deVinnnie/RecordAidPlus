var vakOK = false;
var reeksOK = false;
var lokaalOK = false;
var datumOK = false;
var urenOK = false;
var lectorOK = false;
var redenOK = false;


$("document").ready(function()
{
    $("#datum").datepicker({
        dateFormat: "dd/mm/yy",
        showOtherMonths: true
    });
        
    $("#vak").focusout(checkVak);
    $("#vak").focus(vakErrorDisapear);
    $("#reeks").focusout(checkReeks);
    $("#reeks").focus(reeksErrorDisapear);
    $("#lokaal").focusout(checkLokaal);
    $("#lokaal").focus(lokaalErrorDisapear);
    $("#datum").focusout(checkDatum);
    $("#datum").focus(datumErrorDisapear);
    $("#lector").focusout(checkLector);
    $("#lector").focus(lectorErrorDisapear);
    $("#reden").focusout(checkReden);
    $("#reden").focus(redenErrorDisapear);
    
    $("#aanvraagForm").submit(checkAanvraag);
    
});


function checkAanvraag()
{
    checkVak();
    checkReeks();
    checkLokaal();
    checkDatum();
    checkLector();
    checkReden();
    checkUren();
    
    return (vakOK && reeksOK && lokaalOK && datumOK && lectorOK && redenOK && urenOK);
}


function checkVak()
{
    $("#vak_error").empty();
    if(!($("#vak").val().match(/([a-zA-Z0-9]+( |'|-)?)+/)))
    {
        $("#vak_error").hide();
        $("#vak_error").append("Dit veld moet ingevuld worden");
        $("#vak_error").fadeIn("slow");
    
        vakOK = false;
    }
    else
    {
        vakOK = true;
    }
}

function vakErrorDisapear()
{
    $("#vak_error").fadeOut("slow");
}


function checkReeks()
{
    $("#reeks_error").empty();
    if(!($("#reeks").val().match(/([a-zA-Z0-9]+( |'|-)?)+/)))
    {
        $("#reeks_error").hide();
        $("#reeks_error").append("Dit veld moet ingevuld worden");
        $("#reeks_error").fadeIn("slow");
    
        reeksOK = false;
    }
    else
    {
        reeksOK = true;
    }
}

function reeksErrorDisapear()
{
    $("#reeks_error").fadeOut("slow");
}


function checkLokaal()
{
    $("#lokaal_error").empty();
    if(!($("#lokaal").val().match(/([a-zA-Z0-9]+( |'|-)?)+/)))
    {
        $("#lokaal_error").hide();
        $("#lokaal_error").append("Dit veld moet ingevuld worden");
        $("#lokaal_error").fadeIn("slow");
    
        lokaalOK = false;
    }
    else
    {
        lokaalOK = true;
    }
}

function lokaalErrorDisapear()
{
    $("#lokaal_error").fadeOut("slow");
}


function checkDatum()
{
    $("#datum_error").empty();
    
    setTimeout(function()
    {
        if($("#datum").val() == "")
        {
            $("#datum_error").hide();
            $("#datum_error").append("Dit veld moet ingevuld worden");
            $("#datum_error").fadeIn("slow");
        
            datumOK = false;
        }
        else
        {
            datumOK = true;
        }
    }, 2000);
}

function datumErrorDisapear()
{
    $("#datum_error").fadeOut("slow");
}


function checkLector()
{
    $("#lector_error").empty();
    
    setTimeout(function()
    {
        if(!($("#lector").val().match(/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@khleuven.be$/)))
        {
            $("#lector_error").hide();
            $("#lector_error").append("Dit is geen goed emailadres voor een leerkracht");
            $("#lector_error").fadeIn("slow");
        
            lectorOK = false;
        }
        else
        {
            lectorOK = true;
        }
    }, 1);
}

function lectorErrorDisapear()
{
    $("#lector_error").fadeOut("slow");
}


function checkReden()
{
    $("#reden_error").empty();
    
    if($("#reden").val() == "")
    {
        $("#reden_error").hide();
        $("#reden_error").append("Er moet een reden ingevuld worden");
        $("#reden_error").fadeIn("slow");
    
        redenOK = false;
    }
    else
    {
        redenOK = true;
    }
}

function redenErrorDisapear()
{
    $("#reden_error").fadeOut("slow");
}


function checkUren()
{
    $("#uren_error").empty();
    
    if($("#beginUurH").val() == $("#eindUurH").val())
    {
        if($("#beginUurM").val() >= $("#eindUurM").val())
        {
            $("#uren_error").hide();
            $("#uren_error").append("Het beginuur kan niet later zijn dan het einduur");
            $("#uren_error").fadeIn("slow");
        
            urenOK = false;
        }
        else
        {
            urenOK = true;
        }
    }
    else if($("#beginUurH").val() > $("#eindUurH").val())
    {
        $("#uren_error").hide();
        $("#uren_error").append("Het beginuur kan niet later zijn dan het einduur");
        $("#uren_error").fadeIn("slow");
        
        urenOK = false;
    }
    else
    {
        urenOK = true;
    }
}