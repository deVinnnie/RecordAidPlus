$("document").ready(function()
    {
        $("#berichtInhoud").keypress(function (e)
        {
            if(e.which == 13)
            {                    
                if($(this).val() != "" || $(this).val() != " ")
                {
                    $("#berichtenForm").submit();
                }  
            }
        });
           
           
        $('#berichtInhoud').focus(function()
        {
            $("#berichtInhoud").text("");
        });


        $('#berichtInhoud').blur(function()
        {
            $("#berichtInhoud").text("Beantwoord");

        });
    });