function datatable(id){
    $("#"+id).dataTable({
            "oLanguage": {
                "sLengthMenu": "Toon _MENU_ items per pagina",
                "sZeroRecords": "Niets gevonden",
                "sInfo": "",
                "sInfoEmpty": "",
                "sInfoFiltered": "", 
                "oPaginate": {
                    "sNext": "Volgende", 
                    "sPrevious": "Vorige"
                }, 
                "sSearch": "Zoeken"
            }
        }
    ); 
}