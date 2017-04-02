## INFORMACION RELEVANTE
Todas las consultas a la API han de ir precedidas de /api

## Oferta
A continuación se muestran consultas relativas a la entidad oferta. Estas han de ir precedidas de /offer.
Todos los métodos ligados a Offer devolverán las mismas respuestas a excepción de los indicados.

* **Success Response:**

	HttpStatus.OK

* **Error Response:**

	**Code:** 404 NOT FOUND

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

Añade una oferta a la BBDD. El usuario ha de estar previamente logueado y se le asignara a este.

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
Añade una o varias fotos a una oferta previamente creada.

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
Modifica los datos de una oferta previamente creada.

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
Elimina una oferta previamente creada.

* ##### URL

	< /deleteOffer >

* ##### Método:

	`DELETE`

* ##### URL Params

	* Required:

		* `id=[long]`

* ##### Success Response:
		{ null }
