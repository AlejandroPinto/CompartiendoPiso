## INFORMACION RELEVANTE
Todas las consultas a la API han de ir precedidas de /api

## Búsquedas
A continuación se muestran consultas relativa a la búsqueda de ofertas

### BÚSQUEDA DE OFERTAS

Muestra todas las ofertas que contengan, los atributos establecidos, si estos están en blanco por defecto muestra todas las ofertas.

* ##### URL

	< /search >

* ##### Método:

	`GET`

* ##### URL Params
	* `queryBox=[String]`
	* `priceTo=[float]`
	* `priceFrom=[float]`
	* `type=[String]`
	* `bathroom=[int]`
	* `rooms=[int]`
	* `area=[int]`
	* `page=[int]`
	* `attributes=[String]`


* ##### Ejemplo de búsqueda :
	* AJAX 

          $.ajax({
            url: "/search?&queryBox="+$("[name='queryBox']").val()+"&priceFrom="+
            $("[name='priceFrom']").val()+"&priceTo="+$("[name='priceTo']").val()+
            "&type="+$("[name='type']").val()+"&rooms="+$("[name='rooms']").val()+
            "&bathroom="+$("[name='bathroom']").val()+"&area="+$("[name='area']").val()+
            "&attributes="+$("[name='attributes']").val()+"&page="+page,	
              }).done(function(data){
                printOffers(data);
                $('#showMore').show();
                $('#spinner').empty();
              }).fail(function(data){
                $("#showMore").css("display","none");
                bootbox.alert("No hay más resultados");
              })
	* URL
	
			/search?&queryBox=&priceFrom=0&priceTo=0&type=empty&rooms=0&bathroom=0&area=0&attributes=&page=0
 
* ##### Success Response:
        [
          {
            "id": 1,
            "title": "Chalé bastante moderno",
            "price": 222,
            "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
            "province": "Madrid",
            "location": "Navalcarnero",
            "neighborhood": "el olivar",
            "area": 200,
            "bathroom": 2,
            "rooms": 1,
            "type": "Chale",
            "user": {
              "id": 1,
              "name": "JUAN",
              "firstLastName": "Sanchez",
              "secondLastName": "Sanchez",
              "email": "c@c.com",
              "phone": 918115789,
              "description": "Soy una maquina"
            },
            "characteristics": [
              {},
              {},
              {}
            ]
          },
          {
            "id": 2,
            "title": "Piso bastante guapo1",
            "price": 222,
            "description": "Se alquila piso en Móstoles, en la zona del Soto. Piso de 4 dormitorios reformado, con armarios empotrados, calefacción central, y cocina eléctrica. Zona tranquila y segura, especialmente para familias.",
            "province": "Madrid",
            "location": "El alamo",
            "neighborhood": "callejuelos",
            "area": 200,
            "bathroom": 2,
            "rooms": 1,
            "type": "Piso",
            "user": {
              "id": 5,
              "name": "JUAN",
              "firstLastName": "Sanchez",
              "secondLastName": "Sanchez",
              "email": "w@w.com",
              "phone": 918115789,
              "description": "Soy una maquina"
            },
            "characteristics": []
          }
* ##### Error Response:

	**Code:** 404 NOT FOUND

## Usuario
A continuación se muestran consultas relativas a la entidad usuario. Estas han de ir precedidas de /user.
Todos los métodos ligados a User devolverán las mismas respuestas a excepción de los indicados.

* ##### Success Response:

	* HttpStatus.OK

* ##### Error Response:

	* Code: 404 NOT FOUND

### OBTENER DATOS DE USUARIO

Muestra todos los datos de un usuario cualquiera.

* ##### URL

	< / >

* ##### Método:

	`GET`

* ##### URL Params

	* Required:

		`id=[long]`

* ##### Success Response:
      {
      "name": "JUAN",
      "firstLastName": "Sanchez",
      "secondLastName": "Sanchez",
      "email": "c@c.com",
      "phone": 918115789,
      "description": "Soy una maquina",
      "offers": [
        {
          "id": 1,
          "title": "Chalé bastante moderno",
          "price": 222,
          "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
          "province": "Madrid",
          "location": "Navalcarnero",
          "neighborhood": "el olivar",
          "area": 200,
          "bathroom": 2,
          "rooms": 1,
          "type": "Chale",
          "reviews": [
            {
            	"valoration": "4",
                "comment":"Muy Bonita",
                "date": "2017-03-21"
            }
          ]
        },
        {
          "id": 31,
          "title": "Nueva casa Móstoles",
          "price": 222,
          "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
          "province": "Madrid",
          "location": "Navalcarnero",
          "neighborhood": "el olivar",
          "area": 200,
          "bathroom": 2,
          "rooms": 1,
          "type": "Chale",
          "reviews": []
        }
      ]
    }


### OBTENER USUARIO LOGUEADO

Devuelve un objeto User con los datos dele usuario logueado.

* ##### URL

	< / >

* ##### Método:

	`GET`
* ##### Success Response:
      {
      "name": "JUAN",
      "firstLastName": "Sanchez",
      "secondLastName": "Sanchez",
      "email": "c@c.com",
      "phone": 918115789,
      "description": "Soy una maquina",
      "offers": [
        {
          "id": 1,
          "title": "Chalé bastante moderno",
          "price": 222,
          "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
          "province": "Madrid",
          "location": "Navalcarnero",
          "neighborhood": "el olivar",
          "area": 200,
          "bathroom": 2,
          "rooms": 1,
          "type": "Chale",
          "reviews": [
            {
            	"valoration": "4",
                "comment":"Muy Bonita",
                "date": "2017-03-21"
            }
          ]
        },
        {
          "id": 31,
          "title": "Nueva casa Móstoles",
          "price": 222,
          "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
          "province": "Madrid",
          "location": "Navalcarnero",
          "neighborhood": "el olivar",
          "area": 200,
          "bathroom": 2,
          "rooms": 1,
          "type": "Chale",
          "reviews": []
        }
      ]
    	}
	

### AÑADIR USUARIO
Añade un usuario al sistema.

* ##### URL

	< /addUser >

* ##### Método:

	`POST`

* ##### Data Params
      {
        "name": "anacleto",
        "firstLastName": "Sanchez",
        "secondLastName": "Sanchez",
        "email": "n@n.com",
        "phone": 918115789,
        "description": "Me gusta la playa",
        "pass": "1234",
        "admin": "false",
        "roles":["ROLE_USER"],
        "offers": [],
        "reviews": []
      }	
		
* ##### Success Response:
      {
        "id": 6,
        "name": "anacleto",
        "firstLastName": "Sanchez",
        "secondLastName": "Sanchez",
        "email": "n@n.com",
        "phone": 918115789,
        "description": "Me gusta la playa"
      }
### AÑADIR FOTO A UN USUARIO
Añade una foto a una usuario existente, se añade la foto en el usuario logueado.

* ##### URL

	< /setUserPhoto >

* ##### Método:

	`PUT`

* ##### URL Params
	* Required:
		* `id=[long]`

* ##### Data Params
	* Required:
		* `files=[List<MultipartFile>]`
		
* ##### Success Response:

     {
	"id": 4,
	"name": "Oscar",
	"firstLastName": "Sanchez",
	"secondLastName": "Sanchez",
	"email": "b@b.com",
	"phone": 918115789,
	"description": "Soy una maquina"
      }
### MODIFICAR USUARIO
Modifica los datos de un usuario previamente creado, esta se podrá llevar a cabo por el usuario con rol de admin o el mismo usuario.

* ##### URL

	< /editUser >

* ##### Método:

	`PUT`

* ##### URL Params
	* Required:

		* `id=[long]`

* ##### Data Params

      {
        "name": "anacleto",
        "firstLastName": "Sanchez",
        "secondLastName": "Sanchez",
        "email": "n@n.com",
        "phone": 918115789,
        "description": "Me gusta la playa",
        "pass": "1234",
        "admin": "false",
        "roles":["ROLE_USER"],
        "offers": [],
        "reviews": []
      }

* ##### Success response:

      {
        "name": "anacleto",
        "firstLastName": "Sanchez",
        "secondLastName": "Sanchez",
        "email": "n@n.com",
        "phone": 918115789,
        "description": "Me gusta la playa",
        "pass": "1234",
        "admin": "false",
        "roles":["ROLE_USER"],
        "offers": [],
        "reviews": []
      }


    
### BORRAR UN USUARIO
Elimina un usuario previamente creado, esta se podrá llevar a cabo por el usuario con rol de admin o el mismo usuario.

* ##### URL

	< /deleteUser >

* ##### Método:

	`DELETE`

* ##### URL Params

	* Required:

		* `id=[long]`

* ##### Success Response:
		{ null }


## Oferta
A continuación se muestran consultas relativas a la entidad oferta. Estas han de ir precedidas de /offer.
Todos los métodos ligados a Offer devolverán las mismas respuestas a excepción de los indicados.

* ##### Success Response:

	* HttpStatus.OK

* ##### Error Response:

	* Code: 404 NOT FOUND

### VER OFERTA

Muestra todos los datos de la oferta.

* ##### URL

	< / >

* ##### Método:

	`GET`

* ##### URL Params

	* Required:

		`id=[long]`

* ##### Success Response:
      {
        "id": 1,
        "title": "Chalé bastante moderno",
        "price": 222,
        "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
        "province": "Madrid",
        "location": "Navalcarnero",
        "neighborhood": "el olivar",
        "area": 200,
        "bathroom": 2,
        "rooms": 1,
        "type": "Chale",
        "user": {
        "id": 1,
        "name": "JUAN",
        "firstLastName": "Sanchez",
        "secondLastName": "Sanchez",
        "email": "c@c.com",
        "phone": 918115789,
        "description": "Soy una maquina"
      },
      "reviews": [
      {
        "id": 1,
        "valoration": 5,
        "comment": "regular0",
        "date": "02 abril 2017 11:38"
      },
      {
        "id": 2,
        "valoration": 5,
        "comment": "regular1",
        "date": "02 abril 2017 11:38"
      }
      ],
      "characteristics": [
        {
          "name": "Terraza"
        },
        {
          "name": "Balcón"
        },
        {
          "name": "Alarma"
        }
      ]
      }


### AÑADIR OFERTA

Añade una oferta a la BBDD. El usuario ha de estar previamente logueado y se le asignará a este.

* ##### URL

	< /addOffer >

* ##### Método:

	`POST`

* ##### Data Params

		{
        "title": "Nueva casa Móstoles",
        "price": 222,
        "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
        "province": "Madrid",
        "location": "Navalcarnero",
        "neighborhood": "el olivar",
        "area": 200,
        "bathroom": 2,
        "rooms": 1,
        "type": "Chale",
        "reviews": [
        ],
        "characteristics": [
          {
          "value":true,
            "name": "Alarma"
          },
           {
              "value":true,
            "name": "Fumador"
          }
        ]
      }

* ##### Success response:

          {
      "id": 31,
      "title": "Nueva casa Móstoles",
      "price": 222,
      "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
      "province": "Madrid",
      "location": "Navalcarnero",
      "neighborhood": "el olivar",
      "area": 200,
      "bathroom": 2,
      "rooms": 1,
      "type": "Chale",
      "user": {
        "id": 1,
        "name": "JUAN",
        "firstLastName": "Sanchez",
        "secondLastName": "Sanchez",
        "email": "c@c.com",
        "phone": 918115789,
        "description": "Soy una maquina"
      },
      "reviews": [],
      "characteristics": [
        {
          "name": "Alarma"
        },
        {
          "name": "Fumador"
        }
      ]
      }
	

### AÑADIR FOTOS A UNA OFERTA
Añade una o varias fotos a una oferta previamente creada por el usuario, este ha de ser el creador de la misma.

* ##### URL

	< /setOfferPhoto >

* ##### Método:

	`PUT`

* ##### URL Params
	* Required:
		* `id=[long]`

* ##### Data Params
	* Required:
		* `files=[List<MultipartFile>]`
		
* ##### Success Response:
      {
      "id": 31,
      "title": "Nueva casa Móstoles",
      "price": 222,
      "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
      "province": "Madrid",
      "location": "Navalcarnero",
      "neighborhood": "el olivar",
      "area": 200,
      "bathroom": 2,
      "rooms": 1,
      "type": "Chale",
      "user": {
      "id": 1,
      "name": "JUAN",
      "firstLastName": "Sanchez",
      "secondLastName": "Sanchez",
      "email": "c@c.com",
      "phone": 918115789,
      "description": "Soy una maquina"
      },
      "reviews": [],
      "characteristics": [
        {
        "name": "Alarma"
        },
        {
        "name": "Fumador"
        }
      ]
    	}
### MODIFICAR OFERTA
Modifica los datos de una oferta previamente creada, el usuario ha de ser el creador de esta.

* ##### URL

	< /updateOffer >

* ##### Método:

	`PUT`

* ##### URL Params
	* Required:

		* `id=[long]`

* ##### Data Params

      {
      "title": "Nueva casa Móstoles",
      "price": 222,
      "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
      "province": "Madrid",
      "location": "Navalcarnero",
      "neighborhood": "el olivar",
      "area": 200,
      "bathroom": 2,
      "rooms": 1,
      "type": "Chale",
      "reviews": [
      ],
      "characteristics": [
        {
        "value":true,
        "name": "Alarma"
        },
        {
        "value":true,
        "name": "Fumador"
        }
      ]
      }

* ##### Success response:

      {
      "id": 31,
      "title": "Nueva casa Móstoles",
      "price": 222,
      "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
      "province": "Madrid",
      "location": "Navalcarnero",
      "neighborhood": "el olivar",
      "area": 200,
      "bathroom": 2,
      "rooms": 1,
      "type": "Chale",
      "user": {
      "id": 1,
      "name": "JUAN",
      "firstLastName": "Sanchez",
      "secondLastName": "Sanchez",
      "email": "c@c.com",
      "phone": 918115789,
      "description": "Soy una maquina"
      },
      "reviews": [],
      "characteristics": [
        {
        "name": "Alarma"
        },
        {
        "name": "Fumador"
        }
      ]
      }


    
### BORRAR UNA OFERTA
Elimina una oferta previamente creada, el usuario ha de ser el dueño de esta o poseer el rol de admin.

* ##### URL

	< /deleteOffer >

* ##### Método:

	`DELETE`

* ##### URL Params

	* Required:

		* `id=[long]`

* ##### Success Response:
		{ null }

## Review
A continuación se muestran consultas relativas a la entidad Review. Estas han de ir precedidas de /review.
Todos los métodos ligados a Review devolverán las mismas respuestas a excepción de los indicados.

* ##### Success Response:

	* HttpStatus.OK

* ##### Error Response:

	* Code: 404 NOT FOUND

### AÑADIR REVIEW
Añade una review a una oferta previamente creada, esta unicamente la podrán agregar usuarios logueados.

* ##### URL

	< /addReview >

* ##### Método:

	`POST`
    
* ##### URL Params

	* Required:

		`id=[long]`

* ##### Data Params
      {
          "valoration":4,
          "comment":"bastante bonita",
          "date": "2017-04-10"
      }
		
* ##### Success Response:
      {
        "id": 31,
        "title": "Nueva casa Móstoles",
        "price": 222,
        "description": "Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.",
        "province": "Madrid",
        "location": "Navalcarnero",
        "neighborhood": "el olivar",
        "area": 200,
        "bathroom": 2,
        "rooms": 1,
        "type": "Chale",
        "user": {
          "id": 1,
          "name": "JUAN",
          "firstLastName": "Sanchez",
          "secondLastName": "Sanchez",
          "email": "c@c.com",
          "phone": 918115789,
          "description": "Soy una maquina"
        },
        "reviews": [
          {
            "id": 11,
            "valoration": 4,
            "comment": "bastante bonita",
            "date": "10 abril 2017 02:00"
          }
        ],
        "characteristics": [
          {
            "name": "Alarma"
          },
          {
            "name": "Fumador"
          }
        ]
      }
