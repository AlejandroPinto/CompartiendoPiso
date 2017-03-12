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
		bootbox.alert("no hay mas comentarios");
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
	if(data[3]<=0){
		var prev = "<button id='prevPageButton'><<</button>"
		$(".addPrevPage").html(prev);
	}
	$("#numPage").text(data[1])
	if(data[2] <= data[4]){
		var prev = "<button id='nextPageButton'>>></button>"
		$(".addNextPage").html(prev);
	}
	for(var i=0; i<size; i++){
		var text='<li>';
		    text+='<div class="media">';
		        text+='<div class="media-left">';   
		            text+='<img alt="img" src="/img/users/perfil1.jpg" class="media-object news-img"/>';    
		        text+='</div>';
		        text+='<div class="media-body">';
		             text+='<h4 class="author-name">'+data[0][i]["userReview"]["name"]+' '+data[0][i]["userReview"]["firstLastName"]+' '+data[0][i]["userReview"]["secondLastName"]+'</h4>';
				     text+='<input id="input-21e" value="'+data[0][i]["valoration"]+'" type="text" class="rating" data-min=0 data-max=5 data-step=0.5 data-size="xs" readonly>';
		            text+='<span class="comments-date">'+data[0][i]["date"]+'</span>';
		            text+='<p>'+data[0][i]["comment"]+'</p>';
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
	var pageReviews = 1;
	var idOffer = $("#idOfferReview").val();
	$("#nextPageButton").click(function(){
		reviews(pageReviews,4,idOffer);
		pageReviews++;
		console.log(pageReviews);
	});
	$("#prevPageButton").click(function(){
		reviews(pageReviews,4,idOffer);
		pageReviews--;
		console.log(pageReviews);
	});

});
