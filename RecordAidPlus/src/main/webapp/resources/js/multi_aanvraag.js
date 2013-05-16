var teller = 1; 

function init(){
    document.getElementById("lectoren_remove_button0").onclick = removeLector;
    $("#beginTime").datepicker({
        dateFormat: "yy-mm-dd",
        showOtherMonths: true
    });
     $("#endTime").datepicker({
        dateFormat: "yy-mm-dd",
        showOtherMonths: true
    });
    $("#addlector").click(function(){
        $("#lectoren_list")
                .append($('<li></li>')
                    .html('<button id="lectoren_remove_button'+teller+'" type="button">-</button>\n\
                            <input type="text" id="lectoren'+teller+'" name="lectoren['+teller+']" list="lectoren"/>')
                ); 
        document.getElementById("lectoren_remove_button"+teller).onclick = removeLector;
        teller++;
    }); 
    aanvraagForm.onsubmit = checkInput; 
};

function removeLector(event){
    var id = event.target.id; 
    var index = id.substring(22); 
    remove(index); 
}

function remove(index){
    $("#lectoren"+index).parent().remove(); 
    teller--;
    var j=0; 
    for(var i =0; i<=teller; i++){
        if(i!=index){
            $("#lectoren"+i)
                    .attr('name','lectoren['+j+']')
                    .attr('id', 'lectoren'+j);
            $("#lectoren_remove_button"+i)
                    .attr('id', 'lectoren_remove_button'+j); 
            j++; 
        }
    }
}

function checkInput(){
    for(var i = 0; i<= teller;i++){
        if($("#lectoren"+i).val()==''){
            $("#lectoren_error").html("Geen lege lectoren"); 
            return false; 
        }
    }
    return true; 
}