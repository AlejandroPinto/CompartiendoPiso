//newAd
var attributes = [];
function setInputType(type){
	//("").removeClass("btn btn-default btn-block active").addClass("btn btn-default btn-block");
	$("#type").val(type);
} 

function setAttributee(attribute){
	
	var indice = attributes.indexOf(attribute);
	if (indice==-1){
		attributes.push(attribute);	
	}
	else{
		attributes.splice(indice,1);
	}
	$("#attributes").val(attributes);
	console.log($("#attributes").val());
}