# tesis-server

![Build Status](https://travis-ci.org/Mariani88/tesis-server.svg?branch=master)


- Sobre esta aplicación

Esta aplicación es parte de un sistema de detección de incendios de tiempo real. Consiste en una API Rest, la cual recibe alertas de incendio de una alarma y las envía a Firebase, para que este último las transmita a dispositivos mobile.


El conjunto de aplicaciones se encuentra en los siguientes repositorios:

Software de la alarma: https://github.com/Mariani88/tesis-alarm
La aplicacion Android que configura la alarma: https://github.com/Mariani88/tesis-android-config
La API Rest que recibe las alertas: https://github.com/Mariani88/tesis-server
La aplicación mobile receptora de alertas: https://github.com/Mariani88/tesis-android


- Dependencias del proyecto

Este proyecto requiere de Java 8 y Gradle 4.1


- Ejecución


Para levantar la API se requiere correr, en el directorio donde está el archivo build.gradle, el siguiente comando:

gradle bootrun
