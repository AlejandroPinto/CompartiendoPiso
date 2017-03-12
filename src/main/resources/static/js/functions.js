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
	$.ajax({
		url: "/search?&queryBox="+$("[name='queryBox']").val()+"&priceFrom="+
			$("[name='priceFrom']").val()+"&priceTo="+$("[name='priceTo']").val()+
			"&type="+$("[name='type']").val()+"&rooms="+$("[name='rooms']").val()+
			"&bathroom="+$("[name='bathroom']").val()+"&area="+$("[name='area']").val()+
			"&attributes=''&page="+page,	
	}).done(function(data){
		console.log(data);
		printOffers(data);
	}).fail(function(data){
		console.log(data);
	})
}

function reviews(page,size,idoffer){
	$.ajax({
		url: "/reviews?idoffer="+idoffer+"&page="+page+"&size="+size,	
	}).done(function(data){
		printReviews(data,size,idoffer);
	}).fail(function(data){
		console.log(1);
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

function printReviews(data,size,idOffer){
	$(".addReviews").html("");
	$(".addNextPage").html("");
	$(".addPrevPage").html();
	console.log(data["prevPage"]<=0);
	console.log(data["nextPage"]<=data["numpages"]);
	
	if(data["prevPage"]<=0){
		var prev ='<li>';
		    prev+='<form action="/reviews" method="GET" id="review">';
		    	prev+='<input value="'+idOffer+'" name="idoffer" hidden/>';
		    	prev+='<input value="'+data["prevPage"]+'" name="page" hidden/>';
		    	prev+='<input value="4" name="size" hidden/>';
		    	prev+='<a id="prevPageButton" aria-label="Previous" type="button">';
		        prev+='<span aria-hidden="true">«</span>';
		      prev+='</a>';
		    prev+='</form>';
		prev+='</li>';
		$(".addPrevPage").append(prev);
	}
	$("#numPage").text(data["numPage"])
	if(data["nextPage"]<=data["numpages"]){
		var prev ='<li>';
		    prev+='<form action="/reviews" method="GET" id="review">';
		    	prev+='<input value="'+idOffer+'" name="idoffer" hidden/>';
		    	prev+='<input value="'+data["nextPage"]+'" name="page" hidden/>';
		    	prev+='<input value="4" name="size" hidden/>';
		    	prev+='<a id="nextPageButton" aria-label="Previous" type="button">';
		        prev+='<span aria-hidden="true">»</span>';
		      prev+='</a>';
		    prev+='</form>';
		prev+='</li>';
		$(".addNextPage").append(prev);
		}
	for(var i=0; i<1; i++){
		var text='<li>';
		    text+='<div class="media">';
		        text+='<div class="media-left">';   
		            text+='<img alt="img" src="/img/users/perfil1.jpg" class="media-object news-img">';    
		        text+='</div>';
		        text+='<div class="media-body">';
		             text+='<h4 class="author-name">'+data["content"][i]["userReview"]["name"]+' '+data["content"][i]["userReview"]["firstLastName"]+' '+data["content"][i]["userReview"]["secondLastName"]+'</h4>';
				     text+='<input id="input-21e" value="'+data["content"][i]["valoration"]+'" type="text" class="rating" data-min=0 data-max=5 data-step=0.5 data-size="xs" readonly>';
		            text+='<span class="comments-date">'+data["content"][i]["date"]+'</span>';
		            text+='<p>'+data["content"][i]["comment"]+'</p>';
		        text+='</div>';
		    text+='</div>';
		text+='</li>';
		$(".addReviews").append(text);
	}
	var $input = $('input.rating');
    if ($input.length) {
        $input.removeClass('rating-loading').addClass('rating-loading').rating();
    }
}

$(document).ready(function(){
	$("#buttonSearch").click(function(){
		$(".add").html("");
		browser(0);
	});
	$("#nextPageButton").click(function(){
		reviews($("[name='page']").val(),$("[name='size']").val(),$("[name='idoffer']").val());
	});

});
