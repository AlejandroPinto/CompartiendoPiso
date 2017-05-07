## Nombre de la aplicación web:
CompartiendoPiso
	
## Descripción: 
CompartiendoPiso es una aplicación web que permite publicar anuncios para alquilar o compartir piso y habitaciones. Sin exceso de contenido y fácil de entender.
	
- Funcionalidad pública: Cualquier usuario podrá consultar en el buscador pisos para alquilar y visualizar las reseñas de otros usuarios.
	
- Funcionalidad privada: Cada usuario podrá gestionar su propia cuenta y crear publicaciones de pisos.

- Administrador: Será el encargado de actualizar y moderar las ofertas de pisos y habitaciones.

[![Video Demostracion](http://imgur.com/a/m4uM4)](https://www.youtube.com/watch?v=4y_Ea45KGMI&feature=youtu.be "CompartiendoPiso : Video Demostracion")

## Entidades:
- Usuario: El usuario podrá editar y actualizar su perfil al igual que crear y editar ofertas sobre pisos o habitaciones.
		
- Piso: Información asociada a un piso a alquilar, fotos, descripción, reseñas asociadas...

- Habitación: Información asociada a una habitación a alquilar, fotos, descripción, reseñas asociadas...

- Reseña: Valoraciones de los usuarios sobre los pisos o habitaciones basado en su experiencia.
    
- Mensajes: Mensajería o chat entre los usuarios para obtener información o reservas sobre un piso o habitación.


## Integrantes del equipo de desarrollo:	
Mario Francia Rius
m.francia@alumnos.urjc.es https://github.com/M-Francia

Oscar Sánchez Sánchez
o.sanchezsa@alumnos.urjc.es https://github.com/OscarSanchezSanchez

Adrián Martín Sánchez
a.martinsanchez@alumnos.urjc.es https://github.com/adrianmartin16

Nazaret García García-Maroto
n.garciagarci@alumnos.urjc.es https://github.com/Nazaret7

## Enlace a Trello:
https://trello.com/b/92t9qqny/daw

## API-REST documentación:
https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/API.md

## FASE 2

## Diagrama de navegación:
Index
![DiagramaIndex](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/DiagramaNavegacion(index).PNG?raw=true "DiagramaIndex")

Vistas privadas
![DiagramaPrivada](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/DiagramaNavegacion(vistasPrivadas).PNG?raw=true "DiagramaPrivada")

## Capturas:

La vista register.html, permite el registro del usuario en la aplicación web compartiendoPiso.

![Register](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaRegister.PNG?raw=true "Register")

La vista signin.html, permite al usuario iniciar sesión en la aplicación web compartiendoPiso.

![Login](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaSignIn.PNG?raw=true "Login")

En la vista index.html se muestra el buscador principal de la aplicación web y un listado de ofertas y usuarios relacionados con la búsqueda.

![Index1](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaIndex1.PNG?raw=true "Index1")

![Index2](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaIndex2.PNG?raw=true "Index2")

En las vistas offer-house.html y offer-room.html se muestra la oferta en particular de un tipo de casa o habitación con su título, descripción, fotos... además de valoraciones por parte de los demás usuarios y un espacio de conversación entre el usuario interesado y el propietario de la oferta.

![OfferHouse1](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaOfferHouse1.PNG?raw=true "OfferHouse1")
![OfferHouse2](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaOfferHouse2.PNG?raw=true "OfferHouse2")

![OfferRoom](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaOfferRoom1.PNG?raw=true "OfferRoom")
![OfferRoom2](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaofferRoom2.PNG?raw=true "OfferRoom2")

En la vista user.html se muestra los datos personales del usuario junto con la lista de las ofertas añadidas por el propio usuario además de un buscador. En esta vista, cuando el usuario esta logueado permite editar su perfil y ofertas y añadir nuevas ofertas.

![User](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaUser.PNG?raw=true "User")

La vista newAd.html, permite crear nuevos anuncios de ofertas.

![newAd](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaNewAd.PNG?raw=true "newAd")

La vista newAdModify.html, permite modificar anuncios de ofertas.

![newAdModify](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaNewAdModify.PNG?raw=true "newAdModify")

La vista contact.html, permite contactar con los propietarios de la aplicación web por si hubiese cualquier tipo de duda.

![Contact](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaContact.PNG?raw=true "Contact")

La vista admin.html, permite administrar las ofertas creadas por los usuarios, rechazarlas, editarlar o aceptarlas. Incluye un buscador por si el administrator quisiera buscar una oferta en concreto.

![Admin1](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/vistaAdmin1.PNG?raw=true "Admin1")

## Template utlizado:
Hemos usado de plantilla la web Home property (http://www.markups.io/preview/home-property/) y hemos utilizado los siguientes html:
- Signin.html: Hemos utilizado el formulario de inicio de sesión con algunos cambios de título traducido al español y hemos tocado el CSS para cambiar el fondo.
- Register.html: Hemos utilizado el formulario de registro, le hemos añadido nuevos campos en función de los datos que necesitamos del usuario traducido al español y hemos tocado el CSS para cambiar el fondo.
- Index.html: De este html, hemos utilizado una parte del buscador principal, la lista de apartamentos y el footer. Hemos añadido imágenes de fondo, modificado el buscador y añadido una lista de usuarios relacionados con la búsqueda.
- Properties.html: Hemos utilizado el content head y lista de apartamentos de este html en nuestra vista admin.html, le hemos añadido botones de rechazar, editar y aceptar para la administración de las ofertas creadas por los usuarios. El buscador que aparece en esta vista y en otras, lo hemos modificado a nuestras necesidades y lo hemos incorporado en nuestra vista user.html.
- Properties-details.html: Este html lo hemos relacionado con las ofertas de piso y habitaciones, hemos utilizado el carrusel de fotos, el título, descripciones del apartamento y el apartado de propiedades cercanas. Hemos añadido al final de cada oferta un espacio de valoraciones por parte de los usuarios (Comentarios cogidos de blog-single.html) y un apartado de Conversaciones, que lo verán el propietario de la oferta y el usuario que esté interesado en ella.
- Contact.html: Este html lo hemos utilizado para nuestro apartado de contacto, hemos cambiado un poco el diseño tocando el CSS y traduciendo todo al español.

Los html newAd.html y newAdModify.html son creados por nosotros mediante formularios.


## FASE 3 Y FASE 4

# Entidades de la base de datos:

![BBDD](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/uml.png?raw=true "BBDD")

# Diagrama de clases y templates:

![DiagramaClases](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/diagrama.PNG?raw=true "DiagramaClases")

## FASE 5
 
 [![Video Demostracion](http://imgur.com/a/m4uM4)](https://www.youtube.com/watch?v=4y_Ea45KGMI&feature=youtu.be "CompartiendoPiso : Video Demostracion")

## Diagrama de clases y Diagrama Padre Hijo:

[Diagrama Angular](https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/DiagramaAngular.png "DiagramaAngular")

[Diagrama Padre Hijo]((https://github.com/OscarSanchezSanchez/CompartiendoPiso/blob/master/CapturasFase2/DiagramaPadreHijo.png "Diagrama Padre Hijo")

