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
}

function browser(page){
	$.ajax({
		url: "/search?&queryBox="+$("[name='queryBox']").val()+"&priceFrom="+
			$("[name='priceFrom']").val()+"&priceTo="+$("[name='priceTo']").val()+
			"&type="+$("[name='type']").val()+"&rooms="+$("[name='rooms']").val()+
			"&bathroom="+$("[name='bathroom']").val()+"&area="+$("[name='area']").val()+
			"&attributes="+$("[name='attributes']").val()+"&page="+page,	
	}).done(function(data){
		console.log(data);
		printOffers(data);
	}).fail(function(data){
		console.log(data);
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
      console.log(i);
		$(".add").append(text);
	}

}

$(document).ready(function(){
	$("#buttonSearch").click(function(){
		$(".add").html("");
		browser(0);
//		$("#numPage").val($("#numPage").val()+1);
	});
//	$("#showMore").click(function(){
//		browser($("#numPage").val());
//		var num  = $("#numPage").val();
//		num+=1;
//		$("#numPage").val(num);
//	});
	
});
