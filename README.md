# computacion_distribuida
Repositorio que contiene codigo fuente de programas en JAVA que exploran diferentes paradigmas de la computación distribuida.

## aplicacion_colaborativa

Codigo que pertenecea a una aplicacion colaborativa multicast que permite el envío y recepción de mensajes de texto entre un grupo de personas. Para ello se utiliza el envio de paquetes UDP multicast a la dirección 224.0.0.100 al puerto 6703. Y se muesttra en pantalla el contenido de los mensajes recibidos en esa dirección y puerto.

## cliente-servidor java rmi

Utilizando la herramienta basada en el paradigma de objetos distribuidos Java RMI se crean objetos cliente entre los cuales se paraleliza el calculo de PI mediante el método de Monte Carlo y un objeto servidor que unifica todos los calculos para obtener un resultado global.

## soap

Utilizando el prtocolo SOAP se crea un programa servidor que ofrece dos servicios, uno que es una calculadora y otro que es una analizador de textos, y un programa cliente que permite utilizar los servicios ofertados por el servidor.

## publica-susbcribe

Aplicación basada en el modelo publica-susbcribe en la cual existe un objeto servidor que obtiene información de manera periodica lo indices bursatiles del IBEX-35 y multiples objetos cliente que en cualquier momento puedan conectarse al objeto servidor y crear una alerta tanto de compra como de venta. Cuando el evento se produzca el objeto servidor notificará a los clientes y la alerta será eliminada. Para ello se utiliza el middleware RabbitMQ.

## jade_agentes

Sistma multiagente para la compra/venta de libros mediante el protocolo FIPA English Auction. Utiliza la herramienta JADE que implementa los servicios basicos y la infraestructura necesara para una aplicación multiagente distribuida.
