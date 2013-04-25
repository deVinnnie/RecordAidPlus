var maanden = ['Januari', 'Februari', 'Maart', 'April', 'Mei', 'Juni', 'Juli', 'Augustus', 'September', 'Oktober', 'November', 'December'];
var dagen = ['Zondag', 'Maandag', 'Dinsdag', 'Woensdag', 'Donderdag', 'Vrijdag', 'Zaterdag'];
var dagenShort = ['Zo', 'Ma', 'Di', 'Wo', 'Do', 'Vr', 'Za'];

function datatable(id) {
    $(id).dataTable({
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

function datepicker(id) {
    $(id).datepicker({
        dateFormat: "yy-mm-dd",
        dayNamesMin: dagenShort,
        firstDay: 1,
        monthNames: maanden,
        showOtherMonths: true,
        minDate: new Date()
    });
}

function datepicker(id, altField, startDate) {
    $(id).datepicker({
        dateFormat: "yy-mm-dd",
        dayNamesMin: dagenShort,
        firstDay: 1,
        monthNames: maanden,
        showOtherMonths: true,
        minDate: startDate,
        altField: altField
    });
}