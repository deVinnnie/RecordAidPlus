$("document").ready(function()
    {
        $("#datum").datepicker({
            dateFormat: "dd/mm/yy",
            showOtherMonths: true
        });
    
        $("#datum").change(function()
        {
            $("#formReserveer").submit();
        });
        
        $("#item").change(function()
        {
            $("#formReserveer").submit();
        });
    });