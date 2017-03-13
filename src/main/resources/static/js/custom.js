/** 
  * Template Name: Home Property
  * Version: 1.2  
  * Template Scripts
  * Author: MarkUps
  * Author URI: http://www.markups.io/

  Custom JS
  

  1. FIXED NAVBAR 
  2. AGENTS SLIDER
  3. TESTIMONIAL SLIDER  
  4. CLIENT BRAND SLIDER (SLICK SLIDER)
  5. TOP SLIDER (SLICK SLIDER)
  6. LATEST PRODUCT SLIDER (SLICK SLIDER)
  7. HOVER DROPDOWN MENU
  8. ADVANCE SEARCH FILTER  (noUiSlider SLIDER)
  9. LIGHTBOX ( FOR PORTFOLIO POPUP VIEW ) 
  10. SCROLL TOP BUTTON
  11. PRELOADER
  12. GRID AND LIST LAYOUT CHANGER 
  13.RELATED ITEM SLIDER (SLICK SLIDER)

  
**/
jQuery(function($){


  /* ----------------------------------------------------------- */
  /*  1. FIXED NAVBAR 
  /* ----------------------------------------------------------- */
    
    
  jQuery(window).bind('scroll', function () {
    if (jQuery(window).scrollTop() > 200) {
        jQuery('.main-navbar').addClass('navbar-fixed-top');
        
      } else {
          jQuery('.main-navbar').removeClass('navbar-fixed-top');          
      }
  });
  
  /* ----------------------------------------------------------- */
  /*  2. AGENTS SLIDER
  /* ----------------------------------------------------------- */    

    jQuery('.aa-agents-slider').slick({
      dots: false,
      arrows: false,
      infinite: true,
      speed: 300,
      slidesToShow: 4,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 2500,
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 3,
            slidesToScroll: 3,
            infinite: true,
            dots: true
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 2
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        }
        // You can unslick at a given breakpoint now by adding:
        // settings: "unslick"
        // instead of a settings object
      ]
    });

  /* ----------------------------------------------------------- */
  /*  3. TESTIMONIAL SLIDER 
  /* ----------------------------------------------------------- */    

    jQuery('.aa-testimonial-slider').slick({
        dots: false,      
        infinite: true,
        speed: 500,      
        cssEase: 'linear'
      });

  /* ----------------------------------------------------------- */
  /*  4. CLIENT BRAND SLIDER (SLICK SLIDER)
  /* ----------------------------------------------------------- */  

   jQuery('.aa-client-brand-slider').slick({
      dots: false,
      arrows: false,
      infinite: true,
      speed: 300,
      slidesToShow: 5,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 2500,
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 4,
            slidesToScroll: 4,
            infinite: true,
            dots: true
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 2
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        }
        // You can unslick at a given breakpoint now by adding:
        // settings: "unslick"
        // instead of a settings object
      ]
    });

  
  /* ----------------------------------------------------------- */
  /*  5. TOP SLIDER (SLICK SLIDER)
  /* ----------------------------------------------------------- */    

    jQuery('.aa-top-slider').slick({
      dots: false,
      infinite: true,
      arrows: true,
      speed: 500,
      fade: true,
      cssEase: 'linear'
    });
    
  /* ----------------------------------------------------------- */
  /*  6. LATEST PRODUCT SLIDER (SLICK SLIDER)
  /* ----------------------------------------------------------- */      

    jQuery('.aa-properties-details-img').slick({
      dots: false,
      infinite: true,
      arrows: true,
      speed: 500,      
      cssEase: 'linear'
    });

      
  /* ----------------------------------------------------------- */
  /*  7. HOVER DROPDOWN MENU
  /* ----------------------------------------------------------- */ 
  
  // for hover dropdown menu
    jQuery('ul.nav li.dropdown').hover(function() {
      jQuery(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(200);
    }, function() {
      jQuery(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(200);
    });

 
  /* ----------------------------------------------------------- */
  /* 8. ADVANCE SEARCH FILTER  (noUiSlider SLIDER)
  /* ----------------------------------------------------------- */        

    jQuery(function(){
      if(jQuery('body').is('.aa-price-range')){
        // FOR AREA SECTION
       var skipSlider = document.getElementById('aa-sqrfeet-range');
        noUiSlider.create(skipSlider, {
            range: {
              'min': 0,
              '10%': 100,
              '20%': 200,
              '30%': 300,
              '40%': 400,
              '50%': 500,
              '60%': 600,
              '70%': 700,
              '80%': 800,
              '90%': 900,
              'max': 1000
            },
            snap: true,
            connect: true,
            start: [80, 500]
        });
        // for value print
        var skipValues = [
          document.getElementById('skip-value-lower'),
          document.getElementById('skip-value-upper')
        ];

        skipSlider.noUiSlider.on('update', function( values, handle ) {
          skipValues[handle].innerHTML = values[handle];
        });

        // FOR PRICE SECTION

        var skipSlider2 = document.getElementById('aa-price-range');
        noUiSlider.create(skipSlider2, {
            range: {
                'min': 0,
                '10%': 100,
                '20%': 200,
                '30%': 300,
                '40%': 400,
                '50%': 500,
                '60%': 600,
                '70%': 700,
                '80%': 800,
                '90%': 900,
                'max': 1000
            },
            snap: true,
            connect: true,
            start: [200, 700]
        });
        // for value print
        var skipValues2 = [
          document.getElementById('skip-value-lower2'),
          document.getElementById('skip-value-upper2')
        ];

        skipSlider2.noUiSlider.on('update', function( values, handle ) {
          skipValues2[handle].innerHTML = values[handle];
        });
      }
    });

	/* ----------------------------------------------------------- */
	/*  9. LIGHTBOX ( FOR PORTFOLIO POPUP VIEW ) 
	/* ----------------------------------------------------------- */ 
	
	$('body').append("<div id='portfolio-popup'><div class='portfolio-popup-area'><div class='portfolio-popup-inner'></div></div></div>");
	
	// WHEN CLICK PLAY BUTTON 
	
    jQuery('.aa-single-gallery-info').on('click', function(event) {
      event.preventDefault();
      $('#portfolio-popup').addClass("portfolio-popup-show");
      $('#portfolio-popup').animate({
	      "opacity": 1
      },500);   
      var portfolio_detailscontent = $(this).parent(".aa-single-gallery-item").find(".portfolio-detail").html();
	  $(".portfolio-popup-inner").html(portfolio_detailscontent);     

    });  
           
    // WHEN CLICK CLOSE BUTTON
    
    $(document).on('click','.modal-close-btn', function(event) {     
	    event.preventDefault();
		$('#portfolio-popup').removeClass("portfolio-popup-show");
		$('#portfolio-popup').animate({
		      "opacity": 0
	    },500);  

    });
       
   
    
  /* ----------------------------------------------------------- */
  /*  10. SCROLL TOP BUTTON
  /* ----------------------------------------------------------- */

  //Check to see if the window is top if not then display button

    jQuery(window).scroll(function(){
      if (jQuery(this).scrollTop() > 300) {
        jQuery('.scrollToTop').fadeIn();
      } else {
        jQuery('.scrollToTop').fadeOut();
      }
    });
     
    //Click event to scroll to top

    jQuery('.scrollToTop').click(function(){
      jQuery('html, body').animate({scrollTop : 0},800);
      return false;
    });
  
  /* ----------------------------------------------------------- */
  /*  11. PRELOADER
  /* ----------------------------------------------------------- */

   jQuery(window).load(function() { // makes sure the whole site is loaded      
      jQuery('#aa-preloader-area').delay(300).fadeOut('slow'); // will fade out      
    })
   


  /* ----------------------------------------------------------- */
  /*  12. GRID AND LIST LAYOUT CHANGER 
  /* ----------------------------------------------------------- */

  jQuery("#aa-list-properties").click(function(e){
    e.preventDefault(e);
    jQuery(".aa-properties-nav").addClass("aa-list-view");
  });
  jQuery("#aa-grid-properties").click(function(e){
    e.preventDefault(e);
    jQuery(".aa-properties-nav").removeClass("aa-list-view");
  });


  /* ----------------------------------------------------------- */
  /*  13. RELATED ITEM SLIDER (SLICK SLIDER)
  /* ----------------------------------------------------------- */      

    jQuery('.aa-related-item-slider').slick({
      dots: false,
      infinite: false,
      speed: 300,
      slidesToShow: 4,
      slidesToScroll: 4,
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 3,
            slidesToScroll: 3,
            infinite: true,
            dots: true
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 2
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        }
        // You can unslick at a given breakpoint now by adding:
        // settings: "unslick"
        // instead of a settings object
      ]
    }); 

 
});

function modifyUser(){
	$("#name").show();
	$("#editName").hide();
	$("#label_name").show();	  
	$("#fisrtLastName").show();
	$("#label_firstLastName").show();
	$("#secondLastName").show();
	$("#label_secondLastName").show();
	$("#description").show();
	$("#label_description").show();	
	$("#editDescription").hide();
	$("#phone").show();
	$("#label_phone").show();	
	$("#editPhone").hide();
    $("#email").show();
    $("#label_email").show();	
    $("#editMail").hide();
    $(".saveButton").show(); 
}

function reloadUserModified(data){
	$("#name").hide();
	$("#label_name").hide();	  
	$("#fisrtLastName").hide();
	$("#label_firstLastName").hide();
	$("#secondLastName").hide();
	$("#label_secondLastName").hide();
	$("#editName").text(data["name"]+ " " + data["firstLastName"] + " " + data["secondLastName"]);
	$("#editName").show();
	
	$("#description").hide();
	$("#label_description").hide();	
	$("#editDescription").text(data["description"]);
	$("#editDescription").show();
	
	$("#phone").hide();
	$("#label_phone").hide();
	$("#editPhone").text(data["phone"]);
	$("#editPhone").show();
	
    $("#email").hide();
    $("#label_email").hide();	
    $("#editMail").text(data["email"]);
    $("#editMail").show();
    
    $(".saveButton").hide();
}

$(document).ready(function(){
  $(".editButton").click(function(){  
	  modifyUser();
  });

  $(".guardarCambios").click(function(){
	  var param = 
		  {	  
			  name: $("#name").val(),
			  firstLastName: $("#fisrtLastName").val(),
			  secondLastName: $("#secondLastName").val(),
			  description: $("#description").val(),
			  email: $("#email").val(),
			  phone: $("#phone").val(),
			  pass: $("#pass").val(),
			  admin: $("#admin").val(),
		  }
	  $.ajax({
		  method:"PUT",
		  url: "/user",
		  data: JSON.stringify(param),
		  headers:{
			  "Content-type": "application/json",
			  "Accept": "application/json"
		  }
	  }).done(function(response){
		  reloadUserModified(response);
	  }).fail(function(response){
		  console.log(response);
	  });
  });

  $(".logued").click(function(){
    $(".logedUser").show();
  });
  $(".unlogued").click(function(){
    window.location.href = "user.html";
  });

//  $(".delete-offert").click(function(){
//    bootbox.confirm({
//      message: "¿Está seguro de que desea eliminar la oferta?",
//      buttons: {
//          confirm: {
//              label: 'Confirmar',
//              className: 'btn-primary'
//          },
//          cancel: {
//              label: 'Cancelar',
//              className: 'btn-danger'
//          }
//      },
//      callback: function (result) {
//        bootbox.alert("La oferta ha sido eliminado");
//      }
//    });
//  });
    $(".discard-offert").click(function(){
    bootbox.confirm({
      message: "¿Está seguro de que desea descartar la oferta?",
      buttons: {
          confirm: {
              label: 'Confirmar',
              className: 'btn-primary'
          },
          cancel: {
              label: 'Cancelar',
              className: 'btn-danger'
          }
      },
      callback: function (result) {
        if(result){
          bootbox.prompt({
            title: "Comentario de descarte",
            placeholder:"Escriba aquí su comentario de descarte",
            buttons: {
              confirm: {
                label: "Guardar comentario"
              },
              cancel: {
                label: "Cancelar"
              }
            },
            callback: function(value) {
              bootbox.alert(value);
            }
          });
        }
      }
    });
  });
})

$(window).load(function(){

 $(function() {
  $('#file-input').change(function(e) {
      addImage(e); 
     });

     function addImage(e){
      var file = e.target.files[0],
      imageType = /image.*/;
    
      if (!file.type.match(imageType))
       return;
  
      var reader = new FileReader();
      reader.onload = fileOnload;
      reader.readAsDataURL(file);
     }
  
     function fileOnload(e) {
      var result=e.target.result;
      $('#imgSalida').attr("src",result);
     }
    });
  });

$(function () {
        $("#chk1").click(function () {
            $("#advancedSearch").toggle();
        
        });
    });

$("#fileUpload").on('change', function () {

     //Get count of selected files
     var countFiles = $(this)[0].files.length;

     var imgPath = $(this)[0].value;
     var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
     var image_holder = $("#image-holder");
     image_holder.empty();

     if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
         if (typeof (FileReader) != "undefined") {

             //loop for each file selected for uploaded.
             for (var i = 0; i < countFiles; i++) {

                 var reader = new FileReader();
                 reader.onload = function (e) {
                     $("<img />", {
                         "src": e.target.result,
                             "class": "thumb-image",
                     }).appendTo(image_holder);
                 }

                 image_holder.show();
                 reader.readAsDataURL($(this)[0].files[i]);
             }

         } else {
             alert("This browser does not support FileReader.");
         }
     } else {
         alert("Pls select only images");
     }
 });
