//newAd
var attributes = [];
function setInputType(type){
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

function browser(page){
	$('#spinner').html("<img src='/img/slider/spinner.gif>");
	$.ajax({
		url: "/search?&queryBox="+$("[name='queryBox']").val()+"&priceFrom="+
			$("[name='priceFrom']").val()+"&priceTo="+$("[name='priceTo']").val()+
			"&type="+$("[name='type']").val()+"&rooms="+$("[name='rooms']").val()+
			"&bathroom="+$("[name='bathroom']").val()+"&area="+$("[name='area']").val()+
			"&attributes=''&page="+page,	
	}).done(function(data){
		printOffers(data);
		$('#spinner').empty();
	}).fail(function(data){
		$("#showMore").remove();
		bootbox.alert("No hay más resultados");
		
		
	})
}

function printOffers(data){
	for(var i=0; i<data.length; i++){
		var text ='<div class="col-md-4">';
		text+='<article class="aa-properties-item">';
		  text+='<a class="aa-properties-item-img" href="#">';
		    text+='<img src="/img/users/'+data[i]["user"]["id"]+'/'+data[i]["id"]+'/0.jpg" alt="img" width="370" height="220">';
		  text+='</a>';
		      text+='<div class="aa-properties-item-content">';
                text+='<div class="aa-properties-info">';
                  text+='<span>'+data[i]["area"]+' m2</span>';
                  text+='<span>'+data[i]["rooms"]+' Habitaciones</span>';
                  text+='<span>'+data[i]["bathroom"]+' Baño</span>';                 
                text+='</div>';
                text+='<div class="aa-properties-about">';
                text+='<h3><a href="/offer/'+data[i]["id"]+'?page=0&size=4">'+data[i]["location"]+', '+data[i]["province"]+'<br>'+data[i]["title"]+'</a></h3>';
                text+='<p class="offerParagraph">'+data[i]["description"]+'</p>';                  
              text+='</div>';
              text+='<div class="aa-properties-detial">';
                text+='<span class="aa-price">';
                  text+=''+data[i]["price"]+' €';
                text+='</span>';
                text+='<a class="aa-secondary-btn" href="/offer/'+data[i]["id"]+'?page=0&size=4">Ver</a>';
              text+='</div>';
            text+='</div>';
          text+='</article>';
      text+='</div>';
		$(".add").append(text);
	}

}

$(document).ready(function(){
	var counter = 0;
	$("#buttonSearch").click(function(){
		$(".add").html("");
		browser(counter);
		counter++;
		
	});
	$("#showMore").click(function(){
		browser(counter);
		counter++;
	});
	
});
