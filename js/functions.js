//newAd
function hideElements(a){
    if(a==1){
        $('#0').removeClass("btn btn-default btn-block active").addClass("btn btn-default btn-block");
        $("#nHab").prop('disabled', true);
    }
    if(a==0){
        $('#1').removeClass("btn btn-default btn-block active").addClass("btn btn-default btn-block");
        $("#nHab").prop('disabled', false);
    }
} 